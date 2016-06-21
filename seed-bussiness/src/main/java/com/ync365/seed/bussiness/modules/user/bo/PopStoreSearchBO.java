package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;

/**
 * popStore 查询model
 * @author liukai
 *
 */
public class PopStoreSearchBO {
	
    /**
     * 店铺名称
     */
    private String popStoreNameSearch;

    /**
     * 店铺编号
     */
    private String popStoreNumSearch;

    /**
     * 负责人
     */
    private String popStorePrincipalSearch;
    
    /**
     * 供应商类型（DS/SP/FS） DS 经销商 sp 厂家 fs 配肥站
     */
    private String popStoreTypeSearch;
    
    /**
     * 激活状态
     */
    private Boolean isFrozenSearch;

    /**
     * 搜索结束时间
     */
    private Date endTimeSearch;
    
    /**
     * 搜索开始时间
     */
    private Date beginTimeSearch;
    
    
    /**
     * 所属区域 
     */
    private Integer addressSearch;
    
    /**
     * 联系人
     */
    private String contactSearch;

    /**
     * 页码 
     */
    private Integer pageIndex;
    
    /**
     * 条数
     */
    private Integer pageSize;
    
    /**
     * se 角色查询popstore 时传入的参数
     */
    private String seAdminNumSearch;
    
    /**
     * A+ 角色查询popstore 时传入的参数
     */
    private String aAdminNumSearch;
    
    /**
     * 是否审核
     */
    private Boolean isAuthentication;
    
    /**
     * 店铺装修状态
     */
    private Integer popStoreDecorateSearch;
    
    /**
     * 是否删除
     */
    private Boolean isDel;
    
    /**
     * 手机号码
     */
    private String mobileSearch;
    
    /**
     * 域名
     */
    private String domainSearch;
    
	public String getDomainSearch() {
		return domainSearch;
	}


	public void setDomainSearch(String domainSearch) {
		this.domainSearch = domainSearch;
	}


	public String getMobileSearch() {
		return mobileSearch;
	}


	public void setMobileSearch(String mobileSearch) {
		this.mobileSearch = mobileSearch;
	}


	public void setAuthentication(boolean isAuthentication) {
		this.isAuthentication = isAuthentication;
	}


	public Boolean isDel() {
		return isDel;
	}

	public void setDel(Boolean isDel) {
		this.isDel = isDel;
	}
	
	public Integer getPopStoreDecorateSearch() {
		return popStoreDecorateSearch;
	}


	public void setPopStoreDecorateSearch(Integer popStoreDecorateSearch) {
		this.popStoreDecorateSearch = popStoreDecorateSearch;
	}


	public String getPopStoreNameSearch() {
		return popStoreNameSearch;
	}


	public void setPopStoreNameSearch(String popStoreNameSearch) {
		this.popStoreNameSearch = popStoreNameSearch;
	}


	public String getPopStoreNumSearch() {
		return popStoreNumSearch;
	}


	public void setPopStoreNumSearch(String popStoreNumSearch) {
		this.popStoreNumSearch = popStoreNumSearch;
	}


	public String getPopStorePrincipalSearch() {
		return popStorePrincipalSearch;
	}


	public void setPopStorePrincipalSearch(String popStorePrincipalSearch) {
		this.popStorePrincipalSearch = popStorePrincipalSearch;
	}


	public String getPopStoreTypeSearch() {
		return popStoreTypeSearch;
	}


	public void setPopStoreTypeSearch(String popStoreTypeSearch) {
		this.popStoreTypeSearch = popStoreTypeSearch;
	}


	public Boolean getIsFrozenSearch() {
		return isFrozenSearch;
	}


	public void setIsFrozenSearch(Boolean isFrozenSearch) {
		this.isFrozenSearch = isFrozenSearch;
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

	
	public Integer getAddressSearch() {
		return addressSearch;
	}


	public void setAddressSearch(Integer addressSearch) {
		this.addressSearch = addressSearch;
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

	public String getContactSearch() {
		return contactSearch;
	}

	public void setContactSearch(String contactSearch) {
		this.contactSearch = contactSearch;
	}

    public String getSeAdminNumSearch() {
        return seAdminNumSearch;
    }

    public void setSeAdminNumSearch(String seAdminNumSearch) {
        this.seAdminNumSearch = seAdminNumSearch;
    }

    public String getaAdminNumSearch() {
        return aAdminNumSearch;
    }

    public void setaAdminNumSearch(String aAdminNumSearch) {
        this.aAdminNumSearch = aAdminNumSearch;
    }


	public Boolean isAuthentication() {
		return isAuthentication;
	}


	public void setIsAuthentication(Boolean isAuthentication) {
		this.isAuthentication = isAuthentication;
	}

    
}
