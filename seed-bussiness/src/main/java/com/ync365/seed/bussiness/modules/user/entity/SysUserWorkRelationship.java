package com.ync365.seed.bussiness.modules.user.entity;

public class SysUserWorkRelationship {
	
	/**
	 * 用户编号
	 */
    private String userNum;

    /**
     * 工作编号
     */
    private String work;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}