package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;

/**
 * 
 * 查询model lcinfo
 *
 */
public class LcInfoSearchBO {
    
    /**
     * 搜索名称
     */
    private String lcNameSearch;
    
    /**
     * 移动手机 lc
     */
    private String userMobileSearch;
    
    /**
     * lc 编号
     */
    private String userNumSearch;
    
    /**
     * 激活状态 
     */
    private Boolean isFrozenSearch;
    /**
     * 服务区域
     */
    private Integer serviceDistinct;
    
    /**
     * 注册结束时间
     */
    private Date endDateSearch;
    
   /**
    * 注册开始时间
    */
    private Date beginDateSearch;

    /**
     * 所属se查询条件
     */
    private String seInfoSearch;
    
    /**
     * 所属a+查询条件
     */
    private String aInfoSearch;
    
    /**
     * se角色 查询lc列表 传入参数
     */
    private String seAdminNum ;
    
    /**
     * A+角色 查询lc列表 传入参数
     */
    private String aiAdminNum;
    
    /**
     * 审核状态 0 未审核 1 审核通过 2 审核失败
     */
    private Integer authenticationStateSearch;
    
    /**
     * 开始编号
     */
    private Integer pageIndex;
    
    /**
     * 取多少条
     */
    private Integer pageSize;
    
    /*
    //us 查询开始数量
    private Integer usCountBeginSearch;
    //us 查询结束数量
    private Integer usCountEndSearch;
    //vs 查询开始数量
    private Integer vsCountBeginSearch;
    //vs查询结束数量
    private Integer vsCountEndSearch;
    */
    
    public String getLcNameSearch() {
        return lcNameSearch;
    }

    public void setLcNameSearch(String lcNameSearch) {
        this.lcNameSearch = lcNameSearch;
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

    public Boolean getIsFrozenSearch() {
        return isFrozenSearch;
    }

    public void setIsFrozenSearch(Boolean isFrozenSearch) {
        this.isFrozenSearch = isFrozenSearch;
    }

    public Date getEndDateSearch() {
        return endDateSearch;
    }

    public void setEndDateSearch(Date endDateSearch) {
        this.endDateSearch = endDateSearch;
    }

    public Date getBeginDateSearch() {
        return beginDateSearch;
    }

    public void setBeginDateSearch(Date beginDateSearch) {
        this.beginDateSearch = beginDateSearch;
    }

    public String getSeInfoSearch() {
        return seInfoSearch;
    }

    public void setSeInfoSearch(String seInfoSearch) {
        this.seInfoSearch = seInfoSearch;
    }

    public String getSeAdminNum() {
        return seAdminNum;
    }

    public void setSeAdminNum(String seAdminNum) {
        this.seAdminNum = seAdminNum;
    }

    public String getAiAdminNum() {
        return aiAdminNum;
    }

    public void setAiAdminNum(String aiAdminNum) {
        this.aiAdminNum = aiAdminNum;
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

    public Integer getAuthenticationStateSearch() {
        return authenticationStateSearch;
    }

    public void setAuthenticationStateSearch(Integer authenticationStateSearch) {
        this.authenticationStateSearch = authenticationStateSearch;
    }

    
}
