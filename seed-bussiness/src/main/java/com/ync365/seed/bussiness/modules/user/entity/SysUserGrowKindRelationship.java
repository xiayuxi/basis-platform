package com.ync365.seed.bussiness.modules.user.entity;

public class SysUserGrowKindRelationship {
	/**
	 * 用户编号
	 */
    private String userNum;

    /**
     * 种植类型
     */
    private String growKind;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public String getGrowKind() {
        return growKind;
    }

    public void setGrowKind(String growKind) {
        this.growKind = growKind;
    }
}