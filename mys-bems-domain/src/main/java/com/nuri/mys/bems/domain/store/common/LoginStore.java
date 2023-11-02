package com.nuri.mys.bems.domain.store.common;

import com.nuri.mys.bems.domain.entity.common.*;
import com.nuri.mys.bems.domain.jwt.entity.User;
import com.nuri.mys.bems.domain.jwt.entity.UserInfoRes;
import com.nuri.mys.bems.domain.entity.common.*;

import java.util.List;
import java.util.Map;

public interface LoginStore {
    List<LoginSystemSettingForLoginRes> getSystemSettingForLogin(String essSysConfig);
    LoginSystemSettingRes getSystemSetting();

    LoginUserRes loginCheck(LoginCheckDto model);

    UserInfoRes getUserLockInfo(LoginUserRes dbData);

    void saveUserLockInfo(LoginCheckDto model);

    UserInfoRes getUserInfo(Map<String, Object> param);

    void saveUserHistory(UserInfoRes user);

    void updateLoginFailCnt(LoginUserRes temp);

    int saveLoginLog(Map<String, Object> params);

    int saveSmsCode(LoginUserRes model);

    List<SiteInfoRes> getSiteInfo(LoginUserRes model);

    LoginUserInfoRes getUserRole(User user);
}
