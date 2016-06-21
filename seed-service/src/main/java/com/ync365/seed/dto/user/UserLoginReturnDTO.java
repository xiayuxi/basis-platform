package com.ync365.seed.dto.user;

import java.util.Date;

public class UserLoginReturnDTO {

    private String loginName;
    private String loginState;
    private String usernum;
    private Integer roleId;
    private Date createTime;
    private Integer vsRole;
    private String userMobile;
    /**
     * 是否冻结
     */
    private Boolean isFrozen;
    public Integer getVsRole() {
        return vsRole;
    }

    public void setVsRole(Integer vsRole) {
        this.vsRole = vsRole;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUsernum() {
        return usernum;
    }

    public void setUsernum(String usernum) {
        this.usernum = usernum;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginState() {
        return loginState;
    }

    public void setLoginState(String loginState) {
        this.loginState = loginState;
    }

    public Boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

}
