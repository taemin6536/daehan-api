package com.nuri.mys.bems.service.common.rest;

import com.nuri.mys.bems.domain.entity.common.*;
import com.nuri.mys.bems.domain.logic.common.LoginLogic;
import com.nuri.mys.bems.domain.entity.common.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Tag(name = "1.PMS-LOGIN", description = "PMS-LOGIN")
@CrossOrigin("*")
@RestController
public class LoginResource {

    @Autowired
    private LoginLogic loginService;

    @PostMapping("/pms/login/loginCheck")
    @Operation(summary = "LOGIN", description = "ID/PASSWORD 에 따른 로그인 여부 및 토큰 생성")
    public LoginResultRes loginCheck(@RequestBody @Valid LoginCheckDto model, HttpServletRequest request){
        return loginService.loginCheck(model, request);
    }

    @PostMapping("/pms/login/logout")
    @Operation(summary = "LOGOUT", description = "로그아웃")
    public LoginResultRes logout(@RequestBody @Valid LogoutDto model, HttpServletRequest request){
        return loginService.logout(model, request, "web");
    }

    @PostMapping("/pms/login/getSystemSetting")
    @Operation(summary = "LOGIN",description = "시스템 세팅 값 조회(알람 사용 여부, 로그인 실패 횟수 제한 등)")
    public LoginSystemSettingRes getSystemSetting(){
        System.out.printf("자동배포 테스트1111");
        System.out.printf("test");
        return loginService.getSystemSetting();
    }


    @PostMapping("/pms/login/checkToken")
    @Operation(summary = "TOKEN", description = "토큰 유효성 체크", hidden = true)
    public ResponseEntity<String> checkToken(@RequestBody String token){
        return new ResponseEntity<String>(loginService.checkToken(token), HttpStatus.OK);
    }

    @PostMapping("/pms/login/getUserRole")
    @Operation(summary = "LOGIN", description = "유저 권한 조회(Manager/User)")
    public LoginUserInfoRes getUserRole(@ApiIgnore Authentication authentication) {
        return loginService.getUserRole(authentication);
    }

    @PostMapping("/pms/login/roleCheck")
    @Operation(summary = "LOGIN", description = "token의 role과 DB role 일치 여부(사용자 권한 변경 후 토근 재발급 받지 않은 경우를 위함)")
    public Boolean roleCheck(@ApiIgnore Authentication authentication) {
        return loginService.roleCheck(authentication);
    }
}
