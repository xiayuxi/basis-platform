package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;

public class UserSearchBO {
    
    /**
     *手机号
     */
    private String userMobile;
    
    /**
     * 开始时间
     */
    private Date beginTime;
    
    /**
     * 结束时间
     */
    private Date endTime;
    
    /**
     * 姓名
     */
    private String sysUserRealName;
    
    /**
     * 用户编号
     */
    private String userNumSearch;
    
    /**
     * 激活状态
     */
    private String isFrozenSearch;
    
    /**
     * 所属vs 
     */
    private String vsInfoSearch;
    
    /**
     * 所属lc
     */
    private String lcInfoSearch;
    

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSysUserRealName() {
        return sysUserRealName;
    }

    public void setSysUserRealName(String sysUserRealName) {
        this.sysUserRealName = sysUserRealName;
    }

    public String getUserNumSearch() {
        return userNumSearch;
    }

    public void setUserNumSearch(String userNumSearch) {
        this.userNumSearch = userNumSearch;
    }

    public String getIsFrozenSearch() {
        return isFrozenSearch;
    }

    public void setIsFrozenSearch(String isFrozenSearch) {
        this.isFrozenSearch = isFrozenSearch;
    }

    public String getVsInfoSearch() {
        return vsInfoSearch;
    }

    public void setVsInfoSearch(String vsInfoSearch) {
        this.vsInfoSearch = vsInfoSearch;
    }

    public String getLcInfoSearch() {
        return lcInfoSearch;
    }

    public void setLcInfoSearch(String lcInfoSearch) {
        this.lcInfoSearch = lcInfoSearch;
    }

    
}
