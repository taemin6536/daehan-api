package com.nuri.mys.bems.domain.logic.control;

import com.nuri.mys.bems.domain.entity.common.CommonCodeRes;
import com.nuri.mys.bems.domain.entity.common.CommonResultRes;
import com.nuri.mys.bems.domain.entity.control.*;
import com.nuri.mys.bems.domain.entity.control.*;

import java.util.List;

/**
 * 제어 명령에 대해 처리한다.
 * <br>
 *
 *
 * @version 1.0
 * @since 1.0
 */
public interface ControlLogic {

    /**
     * 제어 페이지에 있는 테이블용 제어 이력을 반환한다.
     * <br>
     * 입력으로 시작 시간( {@link ControlTableDataDto#dateFrom} ), 종료
     * 시간 ( {@link  ControlTableDataDto#dateTo} )를 <code>yyyyMMddHHmmss</code> 형식으로 제공해야 한다.
     * <br>
     * 만약, 시작 시간이 종료시간보다 크면(늦으면), 예외 ("시작 시간은 종표
     * 시간보다 클 수 없습니다.")가 발생한다.
     * <br>
     * 물론 입력이 없을 경우와 형식이 잘못되어도 예외가 전파될 것이다.
     * <br>
     * 조회된 값이 없을 경우라도 <code>null</code>이 아니라 빈 리스트가 반환될 것이다.
     *
     * @param params 시작 시간과 종료 시간
     * @return 시작 시간과 종료 시간 사이에 발생한 모든 제어를 반환
     * @throws IllegalArgumentException 입력이 잘못되었을 때, 시작 시간이 종표 시간보다 클 때 발생
     *
     */
    List<ControlTableDataRes> getControlTableData(ControlTableDataDto params) throws IllegalArgumentException;

    List<CommonCodeRes> getSearchDevice();

    List<ControlDeviceNumberRes> getSearchDeviceNumber();

    CommonResultRes saveEssSetting(ControlEssSettingDto params);

    CommonResultRes sendControl(ControlDeviceDto params);

    ControlDrivingmodeRes getStatusDrivingMode();

}
