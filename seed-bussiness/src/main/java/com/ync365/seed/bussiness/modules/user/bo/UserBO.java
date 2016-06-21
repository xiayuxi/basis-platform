package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;

public class UserBO {

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 登录名
     */
    private String userLoginName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 手机号
     */
    private String userMobile;

    /**
     * 
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 最后一次操作人
     */
    private String lastOptName;

    /**
     * 是否删除 1删除0未删除
     */
    private Boolean isDel;

    /**
     * 是否冻结
     */
    private Boolean isFrozen;

    /**
     * 是否审核
     */
    private Boolean isAuthentication;
    /**
     * 注册地
     */
    private Integer registerLocation;

    public Integer getRegisterLocation() {
        return registerLocation;
    }

    public void setRegisterLocation(Integer registerLocation) {
        this.registerLocation = registerLocation;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName == null ? null : userLoginName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastOptName() {
        return lastOptName;
    }

    public void setLastOptName(String lastOptName) {
        this.lastOptName = lastOptName == null ? null : lastOptName.trim();
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
}