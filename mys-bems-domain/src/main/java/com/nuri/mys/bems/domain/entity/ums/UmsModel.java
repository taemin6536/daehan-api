package com.nuri.mys.bems.domain.entity.ums;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "UmsModel", description = "UMS용 Model")
public class UmsModel implements Serializable {
    @Schema(description = "발신번호 - 제거 후 숫자만 입력")
    private String mobile_no;
    @Schema(description = "메시지 내용")
    private String contents;
    @Schema(description = "프로젝트 구분")
    private String project;
    @Schema(description = "암호화 데이터")
    private String data;
    @Schema(description = "발신번호")
    private String callback_number;
    @Schema(description = "구분")
    private List<String> devisions;

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCallback_number() {
        return callback_number;
    }

    public void setCallback_number(String callback_number) {
        this.callback_number = callback_number;
    }

    public List<String> getDevisions() {
        return devisions;
    }

    public void setDevisions(List<String> devisions) {
        this.devisions = devisions;
    }
}
