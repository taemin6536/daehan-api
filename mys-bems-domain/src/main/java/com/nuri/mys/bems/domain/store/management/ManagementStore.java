package com.nuri.mys.bems.domain.store.management;

import com.nuri.mys.bems.domain.entity.common.CommonCodeRes;
import com.nuri.mys.bems.domain.entity.management.*;
import com.nuri.mys.bems.domain.entity.management.*;

import java.util.List;
import java.util.Set;

public interface ManagementStore {
    // ConnectionHistory
    int getConnectionHistoryTableCnt(ManagementConnectionHistoryDto params);
    List<ManagementConnectionHistoryRes> getConnectionHistoryTable(ManagementConnectionHistoryDto params);

    //User
    List<ManagementUserSystemSettingRes> getSystemSetting();
    int getUserTableCnt(ManagementUserTableDataDto params);
    List<ManagementUserTableDataRes> getUserTable(ManagementUserTableDataDto params);
    int saveUser(ManagementUserSaveDto params);
    int idCheck(ManagementUserIdCheckDto params);
    int updateUser(ManagementUserUpdateDto params);
    int deleteUser(ManagementUserDeleteDto params);
    ManagementUserDetailRes getUserDetail(ManagementUserDetailDto params);
    ManagementUserDetailRes getHeaderUserDetail(ManagementUserHeaderDetailDto params);
    ManagementUserDetailRes getUserDetailAndPass(ManagementUserDetailDto params);
    int updatePass(ManagementUserDetailRes params);
    int updateUserAndPass(ManagementUserHeaderSaveDto params);
    List<CommonCodeRes> getPermType();
    int saveCapa(ManagementSettingSaveCapaDto params);
    List<ManagementCommonCapaInfoRes> getCapaData(String grId);
    List<CommonCodeRes> getSeasonInfo();
    List<CommonCodeRes> getChargeInfo();

    List<ManagementCommonCapaInfoRes> getDeviceCapa(List<ManagementSettingSaveCapaDto> params);

    ManagementSiteInfoRes getSiteInfo();

    int getSiteDeviceInfoCnt(ManagementSiteDeviceInfoDto params);
    List<ManagementSiteDeviceInfoRes> getSiteDeviceInfo(ManagementSiteDeviceInfoDto params);

    ManagementSiteInfoRes getSiteDetailInfo(ManagementSiteDetailDto params);
    int updateSiteDetailInfo(ManagementUpdateSiteInfoDto params);
    ManagementSiteDeviceInfoRes getSiteDeviceDetailInfo(ManagementSiteDeviceDetailDto params);

    int savePmsSetting(ManagementSettingSaveEssDto params);
    ManagementSettingEssInfoRes getPmsSetting();

    int getSchDataCnt(ManagementSchTableDataDto params);

    List<ManagementSchDataRes> getSchTable(ManagementSchTableDataDto params);

    List<ManagementSchDataRes> getSchChartData(ManagementSchChartDataDto params);

    List<ManagementCommonCapaInfoRes> getPcsInfo(ManagementSchPcsInfoDto pcs);

    int saveSch(ManagementSchSaveDto params);

    List<HolidayRes> getHolidayData(ManagementHolidayDataDto params);

    int getYearHolidayCnt(ManagementHolidayDataDto params);

    int deleteSch(ManagementSchCommonDto params);

    List<ClosedDayRes> getClosedDayData(ManagementHolidayDataDto params);

    int saveHolidayData(ManagementSaveHolidayDataDto params);

    int deleteHolidayData(ManagementSaveHolidayDataDto params);

    Set<String> getLegalHolidayData(ManagementSaveHolidayDataDto params);

    int deleteClosedData(ManagementDeleteHolidayDataDto params);

    int saveClosedData(ManagementSaveHolidayDataDto params);

    String getHolidayCd(ManagementDeleteHolidayDataDto params);

    ManagementSchCommonDto getControlSch(ManagementSchDeleteDto params);
}
