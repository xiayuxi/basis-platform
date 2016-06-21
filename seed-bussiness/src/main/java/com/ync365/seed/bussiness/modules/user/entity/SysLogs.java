package com.ync365.seed.bussiness.modules.user.entity;

import java.util.Date;

public class SysLogs {
    private Integer id;

    private Date createTime;

    private String groupName;

    private String roleName;

    private String operatorName;

    private String operatingContent;

    private String parameter;
    private Integer state;
    
    /**
     * 登录用户编号
     */
    private String operatingAdminNum;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getOperatingContent() {
        return operatingContent;
    }

    public void setOperatingContent(String operatingContent) {
        this.operatingContent = operatingContent == null ? null : operatingContent.trim();
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    public String getOperatingAdminNum() {
        return operatingAdminNum;
    }

    public void setOperatingAdminNum(String operatingAdminNum) {
        this.operatingAdminNum = operatingAdminNum;
    }
    
}