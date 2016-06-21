package com.ync365.seed.bussiness.modules.user.entity;

public class SysPopStoreSeRelationship {
    private String popStoreNum;

    private String seNum;

    public String getPopStoreNum() {
        return popStoreNum;
    }

    public void setPopStoreNum(String popStoreNum) {
        this.popStoreNum = popStoreNum == null ? null : popStoreNum.trim();
    }

    public String getSeNum() {
        return seNum;
    }

    public void setSeNum(String seNum) {
        this.seNum = seNum == null ? null : seNum.trim();
    }
}