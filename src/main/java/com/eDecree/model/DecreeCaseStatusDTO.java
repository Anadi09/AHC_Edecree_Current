package com.eDecree.model;

import java.util.Date;

public class DecreeCaseStatusDTO {

    private Long dfFdMid;
    private String stageName;
    private Date stageDate;

    //  default constructor
    public DecreeCaseStatusDTO() {
    }

    //  REQUIRED constructor
    public DecreeCaseStatusDTO(Long dfFdMid, String stageName, Date stageDate) {
        this.dfFdMid = dfFdMid;
        this.stageName = stageName;
        this.stageDate = stageDate;
    }

    public Long getDfFdMid() {
        return dfFdMid;
    }

    public void setDfFdMid(Long dfFdMid) {
        this.dfFdMid = dfFdMid;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Date getStageDate() {
        return stageDate;
    }

    public void setStageDate(Date stageDate) {
        this.stageDate = stageDate;
    }
}