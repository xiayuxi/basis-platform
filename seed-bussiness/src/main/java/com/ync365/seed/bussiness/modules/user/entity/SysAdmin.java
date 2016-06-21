package com.ync365.seed.bussiness.modules.user.entity;

import java.util.Date;

/**
 * @author xieang
 * 2015年9月24日
 */
public class SysAdmin {

    /**
     * 用户编号
     */
    private String adminNum;

    /**
     * 登录名
     */
    private String adminLoginName;

    /**
     * 用户密码
     */
    private String adminPassword;

    /**
     * 手机号
     */
    private String adminMobile;

    /**
     * 保留字段
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
     * 是否删除
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

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum == null ? null : adminNum.trim();
    }

    public String getAdminLoginName() {
        return adminLoginName;
    }

    public void setAdminLoginName(String adminLoginName) {
        this.adminLoginName = adminLoginName == null ? null : adminLoginName.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getAdminMobile() {
        return adminMobile;
    }

    public void setAdminMobile(String adminMobile) {
        this.adminMobile = adminMobile == null ? null : adminMobile.trim();
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