package com.nuri.mys.bems.service.common.rest;

import com.nuri.mys.bems.domain.entity.common.CommonCodeRes;
import com.nuri.mys.bems.domain.entity.common.CommonDeviceDetailDto;
import com.nuri.mys.bems.domain.entity.common.CommonDeviceDetailRes;
import com.nuri.mys.bems.domain.logic.common.CommonLogic;
import com.nuri.mys.bems.domain.entity.common.*;
import com.nuri.mys.bems.service.common.PushTestHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;


@Tag(name = "2.PMS-COMMON", description = "PMS-COMMON")
@CrossOrigin("*")
@RestController
public class CommonResource {

    private static final Logger logger = LogManager.getLogger(CommonResource.class);

    @Autowired
    private CommonLogic commonService;

    @Autowired
    private PushTestHandler pushHandler;

    @PostMapping("/pms/common/deviceInfo")
    @Operation(summary = "장비그룹목록", description = "PCS, BMS 등 장비 그룹 목록")
    public List<CommonCodeRes> getCommonDeviceInfo(@ApiIgnore Authentication authentication) {
        return commonService.getCommonDeviceInfo();
    }

    @PostMapping("/pms/common/deviceDetailInfo")
    @Operation(summary = "장비그룹목록", description = "PCS, BMS 등 장비 상세 목록(PCS-1/BMS-1 등)")
    public List<CommonDeviceDetailRes> getCommonDeviceDetailInfo(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid CommonDeviceDetailDto params) {
        return commonService.getCommonDeviceDetailInfo(params);
    }

    @PostMapping("/pms/common/getEventLevel")
    @Operation(summary = "EVENT > 이벤트 수위 Select box", description = "이벤트 수위 목록 조회(Warning/Fault)")
    public List<CommonCodeRes> getEventLevel(@ApiIgnore Authentication authentication) {
        return commonService.getEventLevel();
    }

    @PostMapping("/pms/common/getEventStatus")
    @Operation(summary = "EVENT > 이벤트 상태 Select box", description = "이벤트 상태 목록 조회(Occur/End)")
    public List<CommonCodeRes> getEventStatus(@ApiIgnore Authentication authentication) {
        return commonService.getEventStatus();
    }
}
