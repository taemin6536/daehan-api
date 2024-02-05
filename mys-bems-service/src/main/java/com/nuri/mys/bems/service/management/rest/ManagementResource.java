package com.nuri.mys.bems.service.management.rest;

import com.nuri.mys.bems.domain.entity.common.CommonCodeRes;
import com.nuri.mys.bems.domain.entity.common.CommonResultRes;
import com.nuri.mys.bems.domain.entity.management.*;
import com.nuri.mys.bems.domain.logic.management.ManagementLogic;
import com.nuri.mys.bems.service.common.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;


@Tag(name = "7.PMS-MANAGEMENT", description = "[HTTP Method] POST:조회 및 등록/PATCH:일부 수정/DELETE:삭제")
@CrossOrigin("*")
@RestController
public class ManagementResource {

    private static final Logger logger = LogManager.getLogger(ManagementResource.class);

    @Autowired
    private ManagementLogic managementService;

    @Autowired
    private CommonService commonService;

    @PostMapping("/pms/management/connectionhistory/tableData")
    @Operation(summary = "관리 > 접속 이력 > 접속 이력 테이블", description = "조회 기간 사이에 접속한 사용자 이력 조회")
    public List<ManagementConnectionHistoryRes> getConnectionHistoryTable(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementConnectionHistoryDto params) {
        return managementService.getConnectionHistoryTable(params);
    }

    @PostMapping(value = "/pms/management/user/getSystemSetting")
    @Operation(summary = "관리 > 사용자 > 시스템 설정 가져오기", description = "시스템 설정 가져오기(SMS/Email/Telegram)")
    public List<ManagementUserSystemSettingRes> getSystemSetting(@ApiIgnore Authentication authentication) {
        return managementService.getSystemSetting();
    }

    @PostMapping("/pms/management/user/getPermType")
    @Operation(summary = "관리 > 사용자 > 사용자 권한 타입 select box", description = "사용자 권한 타입 조회(Manager/User)")
    public List<CommonCodeRes> getPermType(@ApiIgnore Authentication authentication) {
        return managementService.getPermType();
    }

    @PostMapping("/pms/management/user/tableData")
    @Operation(summary = "관리 > 사용자 > 사용자 테이블", description = "사용자 목록 조회")
    public List<ManagementUserTableDataRes> getUserTable(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementUserTableDataDto params) {
        return managementService.getUserTable(params);
    }

    @PostMapping("/pms/management/user/saveUser")
    @Operation(summary = "관리 > 사용자 > 사용자 등록", description = "사용자 등록")
    public CommonResultRes saveUser(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementUserSaveDto params) throws Exception {
        params.setUserId(commonService.getUserId(authentication));
        return managementService.saveUser(params);
    }

    @PostMapping("/pms/management/user/idCheck")
    @Operation(summary = "관리 > 사용자 > 사용자 등록시 ID 중복검사", description = "사용자 등록 시 동일한 userId가 있는지 중복 체크")
    public CommonResultRes idCheck(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementUserIdCheckDto params) {
        return managementService.idCheck(params);
    }

    @PatchMapping("/pms/management/user/updateUser")
    @Operation(summary = "관리 > 사용자 > 사용자 수정", description = "등록된 사용자 정보 수정")
    public CommonResultRes updateUser(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementUserUpdateDto params) throws Exception {
        params.setUserId(commonService.getUserId(authentication));
        return managementService.updateUser(params);
    }

    @PatchMapping(value = "/pms/management/user/initUserPass")
    @Operation(summary = "관리 > 사용자 > 사용자 패스워드 초기화", description = "사용자 패스워드 초기화 임시 비밀번호 생성")
    public CommonResultRes initUserPass(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementUserDetailDto params){
        return managementService.initUserPass(params);
    }

    @PostMapping(value = "/pms/header/getHeaderUserDetail")
    @Operation(summary = "Header > 사용자 정보", description = "현재 로그인 한 사용자 정보 조회")
    public ManagementUserDetailRes getHeaderUserDetail(
            @ApiIgnore Authentication authentication,
            @ApiIgnore @Valid @RequestBody ManagementUserHeaderDetailDto params) throws ParseException {
        params.setUserId(commonService.getUserId(authentication));
        return managementService.getHeaderUserDetail(params);
    }

    @PatchMapping(value = "/pms/header/saveHeaderUser")
    @Operation(summary = "헤더 사용자 정보 수정", description = "현재 로그인 한 사용자 정보 수정")
    public CommonResultRes saveHeaderUser(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementUserHeaderSaveDto params) throws ParseException {
        params.setUserId(commonService.getUserId(authentication));
        return managementService.saveHeaderUser(params);
    }

    @DeleteMapping("/pms/management/user/deleteUser")
    @Operation(summary = "관리 > 사용자 > 사용자 삭제", description = "사용자 삭제")
    public CommonResultRes deleteUser(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementUserDeleteDto params) {
        return managementService.deleteUser(params);
    }

    @PostMapping("/pms/management/user/getUserDetail")
    @Operation(summary = "관리 > 사용자 > 사용자 상세보기", description = "사용자 정보 상세보기")
    public ManagementUserDetailRes getUserDetail(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementUserDetailDto params) {
        return managementService.getUserDetail(params);
    }
    
    @PostMapping("/pms/management/site/getSiteInfo")
    @Operation(summary = "관리 > 사업장 > 사업장 정보", description = "사업장 정보 조회")
    public ManagementSiteInfoRes getSiteInfo(@ApiIgnore Authentication authentication) {
        return managementService.getSiteInfo();
    }

    @PostMapping("/pms/management/site/getSiteDetailInfo")
    @Operation(summary = "관리 > 사업장 > 사업장 정보 조회", description = "사업장 정보 상세 조회")
    public ManagementSiteInfoRes getSiteDetailInfo(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementSiteDetailDto params) {
        return managementService.getSiteDetailInfo(params);
    }

    @PatchMapping("/pms/management/site/updateSiteDetailInfo")
    @Operation(summary = "관리 > 사업장 > 사업장 정보 변경", description = "사업장 정보 수정")
    public CommonResultRes updateSiteDetailInfo(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementUpdateSiteInfoDto params) {
        return managementService.updateSiteDetailInfo(params);
    }
    
    @PostMapping("/pms/management/site/getSiteDeviceInfo")
    @Operation(summary = "관리 > 사업장 > 장비 정보", description = "사업장에 등록된 장비 목록 조회")
    public List<ManagementSiteDeviceInfoRes> getSiteDeviceInfo(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementSiteDeviceInfoDto params) {
        return managementService.getSiteDeviceInfo(params);
    }

    @PostMapping("/pms/management/site/getSiteDeviceDetailInfo")
    @Operation(summary = "관리 > 사업장 > 장비 상세 보기", description = "장비 상세 정보 조회")
    public ManagementSiteDeviceInfoRes getSiteDeviceInfo(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementSiteDeviceDetailDto params) {
        return managementService.getSiteDeviceDetailInfo(params);
    }

    @PostMapping("/pms/management/site/getCapaData")
    @Operation(summary = "관리 > 사업장 > 용량 등록 시 장비목록 Select box", description = "용량 등록 시 장비목록 조회")
    public ManagementSettingCapaInfoRes getCapaData(@ApiIgnore Authentication authentication) {
        return managementService.getCapaData();
    }

    @PatchMapping("/pms/management/site/saveCapaSetting")
    @Operation(summary = "관리 > 사업장 > 용량 등록", description = "장비 용량 데이터 등록")
    public CommonResultRes saveCapa(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody List<ManagementSettingSaveCapaDto> params) throws ParseException {
        params.get(0).setUserId(commonService.getUserId(authentication));
        return managementService.saveCapa(params);
    }

    @PostMapping("/pms/management/setting/getEssSettingData")
    @Operation(summary = "관리 > 사업장 > ESS 설정", description = "ESS 설정 데이터 조회(soc, 운전모드 등)")
    public ManagementSettingEssInfoRes getEssSettingData(@ApiIgnore Authentication authentication) {
        return managementService.getEssSettingData();
    }

    @PostMapping("/pms/management/setting/saveEssSetting")
    @Operation(summary = "관리 > 사업장 > ESS 설정 등록", description = "ESS 설정 데이터 저장(soc, 운전모드 등)")
    public CommonResultRes saveSetting(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementSettingSaveEssDto params) throws ParseException {
        params.setUserId(commonService.getUserId(authentication));
        return managementService.saveSetting(params);
    }

    @PostMapping("/pms/management/setting/getSchTable")
    @Operation(summary = "Management > 스케줄 설정", description = "PCS 충방전 스케쥴 데이터 조회")
    public List<ManagementSchDataRes> getSchTable(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementSchTableDataDto params) {
        return managementService.getSchTable(params);
    }

    @PostMapping("/pms/management/setting/saveSch")
    @Operation(summary = "Management > 스케줄 설정", description = "PCS 충방전 스케쥴 등록")
    public CommonResultRes saveSch(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementSchSaveDto params) throws ParseException {
        params.setUserId(commonService.getUserId(authentication));
        return managementService.saveSch(params);
    }

    @PostMapping("/pms/management/setting/getSchChartData")
    @Operation(summary = "Management > 스케줄 설정", description = "PCS 충방전 스케쥴 차트 데이터 조회")
    public List<ManagementSchDataRes> getSchChartData(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementSchChartDataDto params) {
        return managementService.getSchChartData(params);
    }

    @DeleteMapping("/pms/management/setting/deleteSch")
    @Operation(summary = "Management > 스케줄 설정", description = "PCS 충방전 스케쥴 삭제")
    public CommonResultRes deleteSch(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementSchDeleteDto params) {
        return managementService.deleteSch(params);
    }

    @PostMapping("/pms/management/setting/getHolidayData")
    @Operation(summary = "Management > 운휴 설정 ", description = "운전 휴무일 조회")
    public ManagementHolidayDataRes getHolidayData(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementHolidayDataDto params) {
        return managementService.getHolidayData(params);
    }

    @PostMapping("/pms/management/setting/saveHolidayData")
    @Operation(summary = "Management > 운휴 설정 ", description = "운전 휴무일 등록(일괄/개별)")
    public CommonResultRes saveHolidayData(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementSaveHolidayDataDto params) throws ParseException {
        params.setUserId(commonService.getUserId(authentication));
        return managementService.saveHolidayData(params);
    }

    @DeleteMapping("/pms/management/setting/deleteHolidayData")
    @Operation(summary = "Management > 운휴 설정 ", description = "운전 휴무일 삭제")
    public CommonResultRes deleteHolidayData(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody ManagementDeleteHolidayDataDto params) throws ParseException {
        params.setUserId(commonService.getUserId(authentication));
        return managementService.deleteHolidayData(params);
    }

    @PostMapping("/pms/management/setting/saveLegalHoliday")
    @Operation(summary = "Management > 운휴 설정 ", description = "공휴일 등록")
    public CommonResultRes saveLegalHoliday(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody List<ManagementSaveLegalHolidayDto> params) throws ParseException {
        return managementService.saveLegalHoliday(params);
    }

    @PostMapping("/pms/management/setting/getLegalHoliday")
    @Operation(summary = "Management > 운휴 설정 ", description = "공휴일 목록 조회")
    public List<ManagementLegalHolidayTableRes> getLegalHoliday(
            @ApiIgnore Authentication authentication) throws ParseException {
        return managementService.getLegalHoliday();
    }
}
