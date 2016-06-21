package com.ync365.seed.bussiness.modules.user.entity;

public class SysPopStoreText {
    private Integer id;

    private String popStoreNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPopStoreNum() {
        return popStoreNum;
    }

    public void setPopStoreNum(String popStoreNum) {
        this.popStoreNum = popStoreNum == null ? null : popStoreNum.trim();
    }
}