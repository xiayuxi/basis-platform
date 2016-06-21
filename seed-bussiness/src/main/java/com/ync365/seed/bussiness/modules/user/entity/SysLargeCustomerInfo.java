package com.ync365.seed.bussiness.modules.user.entity;

import java.util.Date;

public class SysLargeCustomerInfo {
    /**
     * 用户编号
     */
    private String userNum;


    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 审核时间
     */
    private Date authenticationTime;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Date getAuthenticationTime() {
		return authenticationTime;
	}

	public void setAuthenticationTime(Date authenticationTime) {
		this.authenticationTime = authenticationTime;
	}
    
}