package com.ync365.seed.bussiness.modules.user.entity;

public class SysSsPopStoreRelationship {
    private Integer id;

    private String ssNum;

    private String popStoreNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSsNum() {
        return ssNum;
    }

    public void setSsNum(String ssNum) {
        this.ssNum = ssNum == null ? null : ssNum.trim();
    }

    public String getPopStoreNum() {
        return popStoreNum;
    }

    public void setPopStoreNum(String popStoreNum) {
        this.popStoreNum = popStoreNum == null ? null : popStoreNum.trim();
    }
}