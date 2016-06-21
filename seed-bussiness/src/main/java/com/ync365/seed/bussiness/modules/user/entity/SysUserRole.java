package com.ync365.seed.bussiness.modules.user.entity;

public class SysUserRole {
	/**
	 * 用户编号
	 */
    private String userNum;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 审核通过状态为：1；审核不通过状态为 2；默认为未审核0；
     */
    private int isAuthentication;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public int getIsAuthentication() {
        return isAuthentication;
    }

    public void setIsAuthentication(int isAuthentication) {
        this.isAuthentication = isAuthentication;
    }
}