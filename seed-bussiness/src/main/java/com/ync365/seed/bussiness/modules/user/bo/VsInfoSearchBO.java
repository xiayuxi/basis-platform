package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;

/**
 * 
 * 查询model vsinfoSearch
 *    
 */
public class VsInfoSearchBO {
    
    /**
     * 搜索条件 名称
     */
    private String nameSearch ;
    
    /**
     * 搜索条件 手机号
     */
    private String userMobileSearch;
    
    /**
     * 搜索条件  编号
     */
    private String userNumSearch;
    
    /**
     * 搜索条件  激活状态
     */
    private Boolean isFrozenSearch;
    
    /**
     * 搜索条件  搜索结束时间
     */
    private Date endTimeSearch;
    
    /**
     *搜索条件  搜索开始时间
     */
    private Date beginTimeSearch;
    
    /**
     * 搜索条件  lc 名称或手机号
     */
    private String lcInfoSearch;
    
    /**
     * 搜索条件  se 名称或手机号
     */
    private String seInfoSearch;
    
    /**
     * 搜索条件 a+名称或手机号
     */
    private String aInfoSearch;
    /**
     * 服务区域Id
     */
    private Integer serviceDistinct;
    
    /**
     * 开始编号
     */
    private Integer pageIndex;
    
    /**
     * 取多少条
     */
    private Integer pageSize;
    
    /**
     * se 角色查询vs列表时 传入参数
     */
    private String seAdminNum;
    
    /**
     * a+ 角色查询时 传入参数
     */
    private String aAdminNum;
    private String lcNum;
    public String getLcNum() {
        return lcNum;
    }

    public void setLcNum(String lcNum) {
        this.lcNum = lcNum;
    }

    /**
     * 审核状态 0 未审核 1审核通过 2审核失败
     */
    private Integer authenticationStateSearch;
    
    /*
    //绑定us数量   结束区间
    private Integer usCountEnd;
    
    //绑定us数量 开始区间
    private Integer usCountBegin;
    */
    
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
    
    public Date getEndTimeSearch() {
        return endTimeSearch;
    }

    public void setEndTimeSearch(Date endTimeSearch) {
        this.endTimeSearch = endTimeSearch;
    }

    public Date getBeginTimeSearch() {
        return beginTimeSearch;
    }

    public void setBeginTimeSearch(Date beginTimeSearch) {
        this.beginTimeSearch = beginTimeSearch;
    }

    public String getLcInfoSearch() {
        return lcInfoSearch;
    }

    public void setLcInfoSearch(String lcInfoSearch) {
        this.lcInfoSearch = lcInfoSearch;
    }

    public Boolean getIsFrozenSearch() {
        return isFrozenSearch;
    }

    public void setIsFrozenSearch(Boolean isFrozenSearch) {
        this.isFrozenSearch = isFrozenSearch;
    }

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

    public String getSeAdminNum() {
        return seAdminNum;
    }

    public void setSeAdminNum(String seAdminNum) {
        this.seAdminNum = seAdminNum;
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

	public Integer getServiceDistinct() {
		return serviceDistinct;
	}

	public void setServiceDistinct(Integer serviceDistinct) {
		this.serviceDistinct = serviceDistinct;
	}

    public String getaAdminNum() {
        return aAdminNum;
    }

    public void setaAdminNum(String aAdminNum) {
        this.aAdminNum = aAdminNum;
    }

    public Integer getAuthenticationStateSearch() {
        return authenticationStateSearch;
    }

    public void setAuthenticationStateSearch(Integer authenticationStateSearch) {
        this.authenticationStateSearch = authenticationStateSearch;
    }

    
}
