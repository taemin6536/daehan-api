package com.nuri.mys.bems.domain.entity.ums;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "UMS history 저장용 Model")
public class UmsResultModel {
    @Schema(required = true, description = "받는사람 또는 chatId 등의 정보")
    private String id;
    @Schema(required = true, description = "메시지 내용")
    private String contents;
    @Schema(required = true, description = "MSA구분자(LGSMS, TELEGRAM등")
    private String vUrlDevision;
    @Schema(required = true, description = "프로젝트 구분")
    private String project;
    @Schema(description = "암호화 파라메타 정보")
    private String data;
    @Schema(description = "sess, fail")
    private String status;
    @Schema(description = "fail Excetion")
    private String errorExcetion;
    @Schema(description = "테이블명")
    private String tableNm;
    @Schema(description = "받는 사람 정보 리스트")
    private String[] idList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getvUrlDevision() {
        return vUrlDevision;
    }

    public void setvUrlDevision(String vUrlDevision) {
        this.vUrlDevision = vUrlDevision;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorExcetion() {
        return errorExcetion;
    }

    public void setErrorExcetion(String errorExcetion) {
        this.errorExcetion = errorExcetion;
    }

    public String getTableNm() {
        return tableNm;
    }

    public void setTableNm(String tableNm) {
        this.tableNm = tableNm;
    }

    public String[] getIdList() {
        return idList;
    }

    public void setIdList(String[] idList) {
        this.idList = idList;
    }
}
