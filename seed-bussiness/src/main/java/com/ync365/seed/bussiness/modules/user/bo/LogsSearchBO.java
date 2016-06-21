package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;

public class LogsSearchBO {

    private String groupName;

    private String roleName;

    private String operatorName;

    private String operatingContent;
    
    /**
     * 登录用户编号
     */
    private String operatingAdminNum;

    private String parameter;
    private Date beginOpratingTime;
    private Date endOpratingTime;
    private Integer pageIndex;
    private Integer pageSize;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatingContent() {
        return operatingContent;
    }

    public void setOperatingContent(String operatingContent) {
        this.operatingContent = operatingContent;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Date getBeginOpratingTime() {
        return beginOpratingTime;
    }

    public void setBeginOpratingTime(Date beginOpratingTime) {
        this.beginOpratingTime = beginOpratingTime;
    }

    public Date getEndOpratingTime() {
        return endOpratingTime;
    }

    public void setEndOpratingTime(Date endOpratingTime) {
        this.endOpratingTime = endOpratingTime;
    }

    public String getOperatingAdminNum() {
        return operatingAdminNum;
    }

    public void setOperatingAdminNum(String operatingAdminNum) {
        this.operatingAdminNum = operatingAdminNum;
    }
    
}
