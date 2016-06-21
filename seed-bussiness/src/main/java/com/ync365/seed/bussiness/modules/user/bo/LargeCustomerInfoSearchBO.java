package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;

public class LargeCustomerInfoSearchBO {
    
    /**
     * 搜索条件  大客户名称
     */
    private String nameSearch;
    
    /**
     * 搜索条件 大客户手机号
     */
    private String userMobileSearch;
    
    /**
     * 搜索条件 大客户状态 
     */
    private Integer stateSearch;
    
    /**
     * 搜索条件 大客户编号
     */
    private String userNumSearch;
    
    /**
     * 搜索条件 大客户认证时间区间开始
     */
    private Date beginDateSearch;
    
    /**
     * 搜索条件 大客户认证时间区间结束
     */
    private Date endDateSearch;
    
    /**
     * 搜索条件  大客户绑定的vs 手机号 或名称
     */
    private String vsInfoSearch;
    
    /**
     * 搜索条件  大客户绑定的lc 手机号 或名称
     */
    private String lcInfoSearch;
    
    /**
     * 搜索条件  大客户绑定的se 手机号 或名称
     */
    private String seInfoSearch;
    
    /**
     * 搜索条件  大客户绑定的a 手机号 或名称
     */
    private String aInfoSearch;
    
    /**
     * 搜索条件 大客户地址查询条件
     */
    private Integer userAddressSearch;
    
    /**
     * se查询大客户列表 查询条件 se编号
     */
    private  String  seAdminNumSearch;

    /**
     * 开始编号
     */
    private Integer pageIndex;
    
    /**
     * 取多少条
     */
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

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getUserMobileSearch() {
        return userMobileSearch;
    }

    public void setUserMobileSearch(String userMobileSearch) {
        this.userMobileSearch = userMobileSearch;
    }

    public String getUserNumSearch() {
        return userNumSearch;
    }

    public void setUserNumSearch(String userNumSearch) {
        this.userNumSearch = userNumSearch;
    }

    public Integer getStateSearch() {
        return stateSearch;
    }

    public void setStateSearch(Integer stateSearch) {
        this.stateSearch = stateSearch;
    }

    public Date getBeginDateSearch() {
        return beginDateSearch;
    }

    public void setBeginDateSearch(Date beginDateSearch) {
        this.beginDateSearch = beginDateSearch;
    }

    public Date getEndDateSearch() {
        return endDateSearch;
    }

    public void setEndDateSearch(Date endDateSearch) {
        this.endDateSearch = endDateSearch;
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

    public String getSeInfoSearch() {
        return seInfoSearch;
    }

    public void setSeInfoSearch(String seInfoSearch) {
        this.seInfoSearch = seInfoSearch;
    }

    public String getaInfoSearch() {
        return aInfoSearch;
    }

    public void setaInfoSearch(String aInfoSearch) {
        this.aInfoSearch = aInfoSearch;
    }

    public Integer getUserAddressSearch() {
        return userAddressSearch;
    }

    public void setUserAddressSearch(Integer userAddressSearch) {
        this.userAddressSearch = userAddressSearch;
    }

    public String getSeAdminNumSearch() {
        return seAdminNumSearch;
    }

    public void setSeAdminNumSearch(String seAdminNumSearch) {
        this.seAdminNumSearch = seAdminNumSearch;
    }

   
}
