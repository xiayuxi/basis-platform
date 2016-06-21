package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;

public class PopLoginReturnBO {

    private String popNum;

    private String popLoginName;

    private String popPassword;

    private String popMobile;

    private String popStoreNum;

    private Boolean isDel;

    private Boolean isFrozen;

    private Boolean isAuthentication;

    private Integer address;
    
    private String decorate;
    
    private Date beforeLoginTime;
    
    
    public Date getBeforeLoginTime() {
		return beforeLoginTime;
	}

	public void setBeforeLoginTime(Date beforeLoginTime) {
		this.beforeLoginTime = beforeLoginTime;
	}

	public String getPopNum() {
        return popNum;
    }

    public void setPopNum(String popNum) {
        this.popNum = popNum == null ? null : popNum.trim();
    }

    public String getPopLoginName() {
        return popLoginName;
    }

    public void setPopLoginName(String popLoginName) {
        this.popLoginName = popLoginName == null ? null : popLoginName.trim();
    }

    public String getPopPassword() {
        return popPassword;
    }

    public void setPopPassword(String popPassword) {
        this.popPassword = popPassword == null ? null : popPassword.trim();
    }

    public String getPopMobile() {
        return popMobile;
    }

    public void setPopMobile(String popMobile) {
        this.popMobile = popMobile == null ? null : popMobile.trim();
    }

    public String getPopStoreNum() {
        return popStoreNum;
    }

    public void setPopStoreNum(String popStoreNum) {
        this.popStoreNum = popStoreNum == null ? null : popStoreNum.trim();
    }


    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public Boolean getIsAuthentication() {
        return isAuthentication;
    }

    public void setIsAuthentication(Boolean isAuthentication) {
        this.isAuthentication = isAuthentication;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getDecorate() {
        return decorate;
    }

    public void setDecorate(String decorate) {
        this.decorate = decorate;
    }
    
}
