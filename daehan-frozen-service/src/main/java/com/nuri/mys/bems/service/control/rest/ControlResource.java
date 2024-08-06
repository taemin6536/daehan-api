package com.nuri.mys.bems.service.control.rest;

import com.nuri.mys.bems.domain.entity.common.CommonCodeRes;
import com.nuri.mys.bems.domain.entity.common.CommonResultRes;
import com.nuri.mys.bems.domain.entity.control.*;
import com.nuri.mys.bems.domain.entity.control.*;
import com.nuri.mys.bems.domain.jwt.entity.User;
import com.nuri.mys.bems.domain.logic.control.ControlLogic;
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

@Tag(name = "4.PMS-CONTROL", description = "PMS-CONTROL")
@CrossOrigin("*")
@RestController
public class ControlResource {

    private static final Logger logger = LogManager.getLogger(ControlResource.class);

    @Autowired
    private ControlLogic controlService;

    public String getUserId(Authentication authentication) throws ParseException {
        User user = (User)authentication.getPrincipal();
        logger.debug("User = {}", user);
        String id = user.getUserId();
        logger.debug("getUserId : userId = {}", id);
        return id;
    }

    @PostMapping("/pms/control/history/controlTableData")
    @Operation(summary = "Control > Control History", description = "사용자가 검색한 기간동안 발생한 제어/설정 이력")
    public List<ControlTableDataRes> getControlTableData(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid ControlTableDataDto params) {
        return controlService.getControlTableData(params);
    }

    @PostMapping("/pms/control/searchDevice")
    @Operation(summary="Control > Solar/ESS", description = "장비번호 조회(1, 2 등)", hidden=true)
    public List<CommonCodeRes> getSearchDevice(@ApiIgnore Authentication authentication) {
        return controlService.getSearchDevice();
    }

    @PostMapping("/pms/control/searchDeviceNumber")
    @Operation(summary="ESS 제어 > 장비 검색", hidden=true)
    public List<ControlDeviceNumberRes> getSearchDeviceNumber(@ApiIgnore Authentication authentication){
        return controlService.getSearchDeviceNumber();
    }

    @PostMapping("/pms/control/drivingMode")
    @Operation(summary = "Control > 운전모드", description = "현재 EMS 운전모드(Manual/Schedule/Auto)")
    public ControlDrivingmodeRes getStatusDrivingMode(@ApiIgnore Authentication authentication) {
        return controlService.getStatusDrivingMode();
    }

    @PostMapping("/pms/control/essSetting")
    @Operation(summary = "soc, 모드, 목표전력 설정", hidden = true)
    public CommonResultRes saveEssSetting(
            @ApiIgnore Authentication authentication,
            @RequestBody(required = false) ControlEssSettingDto params) throws ParseException {
        params.setUserId(getUserId(authentication));
        return controlService.saveEssSetting(params);
    }

    @PostMapping("/pms/control/sendPmsIf")
    @Operation(summary = "Control > Solar/ESS/HydroPower", description = "PCS Start/Stop, HydroPower 출력 변경 등 장비 제어")
    public CommonResultRes sendControl(
            @ApiIgnore Authentication authentication,
            @RequestBody(required = false) ControlDeviceDto params) throws ParseException {
        params.setUserId(getUserId(authentication));
        return controlService.sendControl(params);
    }
}
