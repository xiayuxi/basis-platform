package com.ync365.seed.bussiness.modules.user.entity;

public class SysLcSeRelationship {
    private String lcNum;

    private String seNum;

    public String getLcNum() {
        return lcNum;
    }

    public void setLcNum(String lcNum) {
        this.lcNum = lcNum == null ? null : lcNum.trim();
    }

    public String getSeNum() {
        return seNum;
    }

    public void setSeNum(String seNum) {
        this.seNum = seNum == null ? null : seNum.trim();
    }
}