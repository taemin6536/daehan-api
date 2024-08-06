package com.nuri.mys.bems.service.jwt.security;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.nuri.mys.bems.domain.entity.common.LoginUserInfoRes;
import com.nuri.mys.bems.domain.jwt.ComUtil;
import com.nuri.mys.bems.domain.jwt.entity.Token;
import com.nuri.mys.bems.domain.jwt.entity.User;
import com.nuri.mys.bems.domain.logic.common.LoginLogic;
import com.nuri.mys.bems.domain.store.common.LoginStore;
import com.nuri.mys.bems.domain.store.common.TokenStore;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.util.*;

@Slf4j
@Component
@PropertySource("classpath:jwt.properties")
public class JwtTokenProvider {

    @Value("${nuri.jwt.name}")
    private String name;

    @Value("${nuri.jwt.alg:HS256}")
    private String algorithm;

    @Value("${nuri.jwt.keystore.name}")
    private String keystoreName;
    @Value("${nuri.jwt.keystore.secret}")
    private String keystoreSecret;
    @Value("${nuri.jwt.key.alias}")
    private String keyAlias;
    @Value("${nuri.jwt.key.secret}")
    private String keySecret;

    @Value("${nuri.jwt.sign.secret}")
    private String signSecret;

    @Value("${nuri.jwt.issuer:https://nuritelecom.com/dex/}")
    private String issuer;

    private static String key = "https://nuritelecom.com/";

//    @Value("${nuri.jwt.userId:TOKEN-USERID}")
//    private String userId;

    /**
     * valid time 60min
     */
    @Value("${nuri.jwt.valid-time.admin:3600000}")
    private long adminTokenValidTime;

    /**
     * valid time 5min
     */
    @Value("${nuri.jwt.valid-time.user:300000}")
    private long userTokenValidTime;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private LoginStore loginStore;

    @Autowired
    private LoginLogic loginLogic;

    @PostConstruct
    protected void init() {
        signSecret = Base64.getEncoder().encodeToString(signSecret.getBytes());
    }

    public String createTokenUserinfo(JsonObject jsonObject) {
        String userId = "";
        String role = "";
        String url = "";
        String value = "";
        userId = jsonObject.get("userId").getAsString();
        if (jsonObject.get("role") != null) {
            role = jsonObject.get("role").getAsString();
        } else {
            role = null;
        }
        if (jsonObject.get("url") != null && jsonObject.get("url").getAsJsonNull() != JsonNull.INSTANCE) {
            url = jsonObject.get("url").getAsString();
        } else {
            url = null;
        }
        if (jsonObject.get("value") != null) {
            value = jsonObject.get("value").toString();
        } else {
            value = null;
        }
        Date now = new Date();
        Token t = Token.builder().tokenId(
                UUID.randomUUID().toString().replace("-", "")).issuer(this.issuer)
                .issuedAt(now).expiration(new Date(now.getTime() + this.userTokenValidTime)).audience(userId).role(StringUtils.arrayToCommaDelimitedString((Object[])StringUtils.commaDelimitedListToStringArray(role))).url(StringUtils.arrayToCommaDelimitedString((Object[])StringUtils.commaDelimitedListToStringArray(url))).build();
        t = this.tokenStore.save(t);
        Claims claims = Jwts.claims().setSubject(value);
        claims.put("role", t.getRole());
        claims.put("url", t.getUrl());
        log.debug("role, {}, url, {}", t.getRole(), t.getUrl());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder
                .setClaims(claims)
                .setIssuer(t.getIssuer())
                .setIssuedAt(t.getIssuedAt())
                .setExpiration(t.getExpiration())
                .setId(t.getTokenId())
                .setAudience(t.getAudience());
        if (this.algorithm.equals(SignatureAlgorithm.HS256.getValue()))
            return jwtBuilder.signWith(SignatureAlgorithm.HS256, this.signSecret).compact();
        return jwtBuilder.signWith(SignatureAlgorithm.RS256, getKeyPair().getPrivate()).compact();
    }

    public String createToken(User user) {
        Date now = new Date();
        Token t = Token.builder()
                .tokenId(UUID.randomUUID().toString().replace("-", ""))
                .issuer(issuer)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + userTokenValidTime))
                .audience(user.getUserId())
                .role(StringUtils.arrayToCommaDelimitedString(StringUtils.commaDelimitedListToStringArray(user.getRole())))
                .url(StringUtils.arrayToCommaDelimitedString(StringUtils.commaDelimitedListToStringArray(user.getUrl())))
                .build();
        t = tokenStore.save(t);

        Claims claims = Jwts.claims().setSubject(t.getAudience());
        claims.put("role", t.getRole());
        claims.put("url", t.getUrl());

        log.debug("role, {}, url, {}", t.getRole(), t.getUrl());

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder
                .setClaims(claims)
                .setIssuer(t.getIssuer())
                .setIssuedAt(t.getIssuedAt())
                .setExpiration(t.getExpiration())
                .setId(t.getTokenId())
                .setAudience(t.getAudience());

        if (algorithm.equals(SignatureAlgorithm.HS256.getValue()))
            return jwtBuilder.signWith(SignatureAlgorithm.HS256, signSecret).compact();
        else
            return jwtBuilder.signWith(SignatureAlgorithm.RS256, getKeyPair().getPrivate()).compact();
    }

    public Authentication getAuthentication(String result) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject user = (JSONObject) parser.parse(result);
        UserDetails userDetails = User.builder()
                .userId((String) user.get("userId"))
                .role((String) user.get("role"))
                .build();
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Token getToken(String token) {
        Claims body = algorithm.equals(SignatureAlgorithm.HS256.getValue())
                ?Jwts.parser().setSigningKey(signSecret).parseClaimsJws(token).getBody()
                :Jwts.parser().setSigningKey(getKeyPair().getPublic()).parseClaimsJws(token).getBody();

        Token t = Token.builder()
                .tokenId(body.getId())
                .issuer(body.getIssuer())
                .issuedAt(body.getIssuedAt())
                .expiration(body.getExpiration())
                .audience(body.getAudience())
                .role(body.get("role").toString())
                .url(body.get("url").toString())
                .build();
        return t;
    }


    public String getId(String token) {
        return algorithm.equals(SignatureAlgorithm.HS256.getValue())
                ?Jwts.parser().setSigningKey(signSecret).parseClaimsJws(token).getBody().getId()
                :Jwts.parser().setSigningKey(getKeyPair().getPublic()).parseClaimsJws(token).getBody().getId();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(name);
    }

//    public String resolveUserId(HttpServletRequest request) {
//        return request.getHeader(userId);
//    }

    public String validateToken(String token) throws ParseException {
        Map<String, Object> ob = new HashMap<String, Object>();
        ob.put("token", token);
        String responseBody = String.valueOf(loginLogic.getToken(ob));
        return responseBody;
    }

    public String getAud(String token) {
        return (String) (algorithm.equals(SignatureAlgorithm.HS256.getValue())
                ?Jwts.parser().setSigningKey(signSecret).parseClaimsJws(token).getBody().get("aud")
                :Jwts.parser().setSigningKey(getKeyPair().getPublic()).parseClaimsJws(token).getBody().get("aud"));
    }

    public String getRole(String token) {
        return (String) (algorithm.equals(SignatureAlgorithm.HS256.getValue())
                ?Jwts.parser().setSigningKey(signSecret).parseClaimsJws(token).getBody().get("role")
                :Jwts.parser().setSigningKey(getKeyPair().getPublic()).parseClaimsJws(token).getBody().get("role"));
    }

    public String roleCheck(String token) {
        String tokenUserId = getAud(token);
        String tokenRole = getRole(token);

        User user = new User();
        user.setUserId(tokenUserId);
        LoginUserInfoRes result = loginStore.getUserRole(user);
        if(tokenRole.equals(result.getPermId())) {
            return result.getPermId();
        }
        return "false"; // 토큰의 권한과 nst_user_info의 권한이 일치하지않을 경우 false를 반환한다.
    }

//    public Boolean roleCheck(Authentication authentication) {
//        User user = (User)authentication.getPrincipal();
//
//        LoginUserInfoRes result = loginStore.getUserRole(user);
//        if(user.getRole().equals(result.getPermId())) {
//            return true;
//        }
//        return false;
//    }

    public KeyPair getKeyPair()  {
        String _keySecret = null;
        String _keystoreSecret = null;
        String _keyAlias = null;

        try {
            _keySecret = ComUtil.decryptAES(keySecret, key);
            _keystoreSecret = ComUtil.decryptAES(keystoreSecret, key);
            _keyAlias = ComUtil.decryptAES(keyAlias, key);

        }catch (Exception ex){
            log.error(ex.getMessage());
        }

        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource(keystoreName), _keystoreSecret.toCharArray()).getKeyPair(_keyAlias, _keySecret.toCharArray());
        return keyPair;
    }
}