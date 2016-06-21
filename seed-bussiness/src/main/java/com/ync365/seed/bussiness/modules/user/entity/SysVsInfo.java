package com.ync365.seed.bussiness.modules.user.entity;

import java.util.Date;

public class SysVsInfo {
	
	/**
	 * 用户编号
	 */
    private String userNum;

    /**
     * 座机电话
     */
    private String telephone;

    /**
     * 工作年限
     */
    private Integer workYear;

    /**
     * 本地农户数量
     */
    private Integer localFarmerCount;

    /**
     * 本地农地面积
     */
    private Integer localCroplandArea;

    /**
     * 创建时间
     */
    private Date createTime;

    private Integer authenticationState;

    private Date authenticationTime;
    
    
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    public Integer getLocalFarmerCount() {
        return localFarmerCount;
    }

    public void setLocalFarmerCount(Integer localFarmerCount) {
        this.localFarmerCount = localFarmerCount;
    }

    public Integer getLocalCroplandArea() {
        return localCroplandArea;
    }

    public void setLocalCroplandArea(Integer localCroplandArea) {
        this.localCroplandArea = localCroplandArea;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAuthenticationState() {
        return authenticationState;
    }

    public void setAuthenticationState(Integer authenticationState) {
        this.authenticationState = authenticationState;
    }

    public Date getAuthenticationTime() {
        return authenticationTime;
    }

    public void setAuthenticationTime(Date authenticationTime) {
        this.authenticationTime = authenticationTime;
    }

	
}