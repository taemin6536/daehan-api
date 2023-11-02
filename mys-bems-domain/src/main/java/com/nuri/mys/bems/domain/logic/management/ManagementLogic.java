package com.nuri.mys.bems.domain.logic.management;

import com.nuri.mys.bems.domain.entity.common.CommonCodeRes;
import com.nuri.mys.bems.domain.entity.common.CommonResultRes;
import com.nuri.mys.bems.domain.entity.management.*;
import com.nuri.mys.bems.domain.entity.management.*;

import java.util.List;

public interface ManagementLogic {
    List<ManagementConnectionHistoryRes> getConnectionHistoryTable(ManagementConnectionHistoryDto params);

    List<ManagementUserSystemSettingRes> getSystemSetting();
    List<ManagementUserTableDataRes> getUserTable(ManagementUserTableDataDto params);
    CommonResultRes saveUser(ManagementUserSaveDto params) throws Exception;
    CommonResultRes idCheck(ManagementUserIdCheckDto params);
    CommonResultRes updateUser(ManagementUserUpdateDto params);
    CommonResultRes deleteUser(ManagementUserDeleteDto params);
    ManagementUserDetailRes getUserDetail(ManagementUserDetailDto params);
    ManagementUserDetailRes getHeaderUserDetail(ManagementUserHeaderDetailDto params);
    CommonResultRes saveHeaderUser(ManagementUserHeaderSaveDto params);
    CommonResultRes initUserPass(ManagementUserDetailDto model);
    List<CommonCodeRes> getPermType();
    CommonResultRes saveCapa(List<ManagementSettingSaveCapaDto> params);
    ManagementSettingCapaInfoRes getCapaData();
    List<CommonCodeRes> getSeasonInfo();
    List<CommonCodeRes> getChargeInfo();

    ManagementSiteInfoRes getSiteInfo();

    List<ManagementSiteDeviceInfoRes> getSiteDeviceInfo(ManagementSiteDeviceInfoDto params);

    ManagementSiteInfoRes getSiteDetailInfo(ManagementSiteDetailDto params);
    CommonResultRes updateSiteDetailInfo(ManagementUpdateSiteInfoDto params);

    ManagementSiteDeviceInfoRes getSiteDeviceDetailInfo(ManagementSiteDeviceDetailDto params);

    ManagementSettingEssInfoRes getEssSettingData();
    CommonResultRes saveSetting(ManagementSettingSaveEssDto params);

    CommonResultRes saveSch(ManagementSchSaveDto params);

    List<ManagementSchDataRes> getSchTable(ManagementSchTableDataDto params);

    List<ManagementSchDataRes> getSchChartData(ManagementSchChartDataDto params);

    ManagementHolidayDataRes getHolidayData(ManagementHolidayDataDto params);

    CommonResultRes deleteSch(ManagementSchDeleteDto params);

    CommonResultRes saveHolidayData(ManagementSaveHolidayDataDto params);

    CommonResultRes deleteHolidayData(ManagementDeleteHolidayDataDto params);
}
