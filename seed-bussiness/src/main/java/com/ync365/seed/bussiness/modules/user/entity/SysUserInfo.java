package com.ync365.seed.bussiness.modules.user.entity;

import java.util.Date;

public class SysUserInfo {
	
	/**
	 * 用户编号
	 */
    private String userNum;

    /**
     * 用户昵称
     */
    private String sysNickname;

    /**
     * 用户真实姓名
     */
    private String sysUserRealName;

    /**
     * 性别
     */
    private Integer sysUserGender;

    /**
     * 身份证号
     */
    private String sysUserIdCard;

    /**
     * 生日
     */
    private Date sysUserBirthday;

    /**
     * 农场面积
     */
    private Integer sysUserFarmArea;

    /**
     * 用户地址编号
     */
    private Integer sysUserAddress;

    /**
     * 用户地址详情
     */
    private String sysUserAddressDetail;
    /**
     * 用户头像地址
     */
    private String sysUserPhotoPath;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getSysNickname() {
        return sysNickname;
    }

    public void setSysNickname(String sysNickname) {
        this.sysNickname = sysNickname;
    }

    public String getSysUserRealName() {
        return sysUserRealName;
    }

    public void setSysUserRealName(String sysUserRealName) {
        this.sysUserRealName = sysUserRealName;
    }

    public Integer getSysUserGender() {
        return sysUserGender;
    }

    public void setSysUserGender(Integer sysUserGender) {
        this.sysUserGender = sysUserGender;
    }

    public String getSysUserIdCard() {
        return sysUserIdCard;
    }

    public void setSysUserIdCard(String sysUserIdCard) {
        this.sysUserIdCard = sysUserIdCard;
    }

    public Date getSysUserBirthday() {
        return sysUserBirthday;
    }

    public void setSysUserBirthday(Date sysUserBirthday) {
        this.sysUserBirthday = sysUserBirthday;
    }

    public Integer getSysUserFarmArea() {
        return sysUserFarmArea;
    }

    public void setSysUserFarmArea(Integer sysUserFarmArea) {
        this.sysUserFarmArea = sysUserFarmArea;
    }

    public Integer getSysUserAddress() {
        return sysUserAddress;
    }

    public void setSysUserAddress(Integer sysUserAddress) {
        this.sysUserAddress = sysUserAddress;
    }

    public String getSysUserAddressDetail() {
        return sysUserAddressDetail;
    }

    public void setSysUserAddressDetail(String sysUserAddressDetail) {
        this.sysUserAddressDetail = sysUserAddressDetail;
    }

	public String getSysUserPhotoPath() {
		return sysUserPhotoPath;
	}

	public void setSysUserPhotoPath(String sysUserPhotoPath) {
		this.sysUserPhotoPath = sysUserPhotoPath;
	}

	
}