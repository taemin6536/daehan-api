package com.nuri.mys.bems.domain.logic.common;

import com.nuri.mys.bems.domain.entity.common.*;
import com.nuri.mys.bems.domain.entity.common.*;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface LoginLogic {
    LoginResultRes loginCheck(LoginCheckDto model, HttpServletRequest request);

    String getToken(Map<String, Object> param);

    LoginResultRes logout(LogoutDto model, HttpServletRequest request, String web);

    LoginSystemSettingRes getSystemSetting();

    String checkToken(String token);

    LoginUserInfoRes getUserRole(Authentication authentication);

    Boolean roleCheck(Authentication authentication);
}
