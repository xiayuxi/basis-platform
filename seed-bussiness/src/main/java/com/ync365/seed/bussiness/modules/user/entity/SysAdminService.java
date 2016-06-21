package com.ync365.seed.bussiness.modules.user.entity;

public class SysAdminService {
    private String adminNum;

    private Integer adminServiceDistinct;

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum == null ? null : adminNum.trim();
    }

    public Integer getAdminServiceDistinct() {
        return adminServiceDistinct;
    }

    public void setAdminServiceDistinct(Integer adminServiceDistinct) {
        this.adminServiceDistinct = adminServiceDistinct;
    }
}