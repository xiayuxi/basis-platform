package com.ync365.seed.bussiness.modules.user.bo;

public class SeInfoBO {
	
	/**
	 * 用户编号
	 */
	private String userNum;
	
	/**
	 * 手机号
	 */
	private String userMobile;
	
	/**
	 * SE用户姓名 
	 */
	private String userName;

	/**
	 * 服务区或
	 */
	private String serviceArea;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServiceArea() {
		return serviceArea;
	}

	public void setServiceArea(String serviceArea) {
		this.serviceArea = serviceArea;
	}
	
	
}
