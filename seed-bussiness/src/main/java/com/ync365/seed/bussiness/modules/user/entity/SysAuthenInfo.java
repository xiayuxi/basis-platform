package com.ync365.seed.bussiness.modules.user.entity;

import java.util.Date;

public class SysAuthenInfo {
    private Integer id;

    /**
     * 编号
     */
    private String userNum;

    /**
     * 审核 状态 1 大客户 2vs 3lc
     */
    private Integer source;

    /**
     * 1 审核通过 2 审核失败
     */
    private Integer authenState;

    /**
     * 原因
     */
    private String authenReason;

    /** 
     *  审核人 姓名
     */
    private String authenAdminName;

    /**
     * 审核时间
     */
    private Date authenTime;
    
    /**
     * 审核人编号
     */
    private String authenAdminNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getAuthenState() {
        return authenState;
    }

    public void setAuthenState(Integer authenState) {
        this.authenState = authenState;
    }

    public String getAuthenReason() {
        return authenReason;
    }

    public void setAuthenReason(String authenReason) {
        this.authenReason = authenReason == null ? null : authenReason.trim();
    }

    public String getAuthenAdminName() {
        return authenAdminName;
    }

    public void setAuthenAdminName(String authenAdminName) {
        this.authenAdminName = authenAdminName == null ? null : authenAdminName.trim();
    }

    public Date getAuthenTime() {
        return authenTime;
    }

    public void setAuthenTime(Date authenTime) {
        this.authenTime = authenTime;
    }

    public String getAuthenAdminNum() {
        return authenAdminNum;
    }

    public void setAuthenAdminNum(String authenAdminNum) {
        this.authenAdminNum = authenAdminNum;
    }
    
}