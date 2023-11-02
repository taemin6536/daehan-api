package com.nuri.mys.bems.service.jwt.security;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

	private JwtTokenProvider jwtTokenProvider;

	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request == null || response == null || chain == null) {
		    throw new IllegalArgumentException("Checks one of arguments !");
		}

		String path = ((HttpServletRequest) request).getRequestURI();
		if(path != null && !excludeUrl(path)) {
		    chain.doFilter(request,response);
			return;
		}

		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		log.debug("[Token Filter] path: {}, accessToken: {}", path, token);

        String result = "";

		if(token == null) {
		    throw new JwtException("[JWT exception] Token is Null");
		}

		try {
		    token = token.replace("Bearer ", "");
		    result = jwtTokenProvider.validateToken(token);
		    log.debug("[Token Filter] token validation result : {}", result);

		    SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(result));
		} catch(ParseException e) {
		    log.error(e.getMessage());
		}

		if("fail".equals(result)) {
		    throw new JwtException("[JWT exception] Wrong Token, Token Authentication Fail");
		}

		String perm = jwtTokenProvider.roleCheck(token);

		if("false".equals(perm)) {
			throw new JwtException("[JWT exception] Not Match Permission, please login again.");
		} else {
			if(adminUrl(path) && perm.contains("USER")) {
				throw new JwtException("[JWT exception] Access Denied, only admin can access");
			}
		}

		// Ok, authorized, then do the next process !
		chain.doFilter(request, response);
	}

	/**
	 * 오직 이 클래스 내에서만 사용되기 때문에 입력을 검사하지 않는다.
	 * 왜냐하면, 호출 전에 이미 검사하기 때문이다.
	 *
	 */
	private boolean excludeUrl(String uri) {
        if(uri.startsWith("/pms") && !(uri.contains("loginCheck") || uri.contains("getSystemSetting") || uri.contains("websocket") || uri.contains("getPmsUserSmsInfo"))) {
            return true;
        }
        return false;
	}

	/***
	 * permission에 따라 ROLE_SYSTEM만 접근 가능한 uri를 확인한다. (관리 및 제어)
	 */
	private boolean adminUrl(String uri) {
		if(uri.contains("management") || uri.contains("control")) {
			return true;
		}
		return false;
	}

}