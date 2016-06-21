package com.ync365.seed.bussiness.modules.user.entity;

import java.util.Date;

public class SysUserYnb {
    private Integer id;

    private String userNum;

    private String ynbId;

    private String ynbMemberid;

    private Integer state;

    private Date createTime;

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

    public String getYnbId() {
        return ynbId;
    }

    public void setYnbId(String ynbId) {
        this.ynbId = ynbId == null ? null : ynbId.trim();
    }

    public String getYnbMemberid() {
        return ynbMemberid;
    }

    public void setYnbMemberid(String ynbMemberid) {
        this.ynbMemberid = ynbMemberid == null ? null : ynbMemberid.trim();
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
}