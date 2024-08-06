package com.nuri.mys.bems.domain.entity.operation;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "OperationDeviceCommonDto")
public class OperationDeviceCommonDto {
    /** 장비 아이디. 형식은 3자리 그룹아이디와 4자리 일렬번호로 구성 */
    @Schema(required = true, description = "장비 ID, 7자리 숫자로 제한한다.", example = "1000001")
    @NotBlank
    @Pattern(regexp="^(\\d{7})")
    private String deviceId;
    @Schema(required = true, description = "장비 현황&통계, 조회 컬럼 배열")
    @NotNull
    private String[] columnArray;
    @Schema(required = false, hidden = true)
    private String dateFrom;
    @Schema(required = false, hidden = true)
    private String dateTo;

    /**
     * 장비 아이디를 반환한다.
     *
     * @return 장비 아이디
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 장비 아이디를 저장한다.
     * <br>
     * 장비 아이디가 <code>null</code>, 빈문자열(""), 7자리 숫자가 아니면 {@link IllegalArgumentException} 예외가 전파된다.
     *
     * @param deviceId 장비 아이디
     * @throws IllegalArgumentException 장비 아이디가 원하는 값이 아닐 때 발생
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String[] getColumnArray() {
        return columnArray;
    }

    public void setColumnArray(String[] columnArray) {
        this.columnArray = columnArray;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
