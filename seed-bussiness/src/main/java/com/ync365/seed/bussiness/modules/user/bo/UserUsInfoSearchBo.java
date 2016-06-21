package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;

public class UserUsInfoSearchBo {

	/**
	 * 用户编号
	 */
	private String userNum;
	/**
	 * 用户手机号
	 */
	private String userMobile;
	/**
	 * 用户真实姓名
	 */
	private String userRealName;
	/**
	 * 地址编号
	 */
	private Integer userAddress;
	/**
	 * 是否激活
	 */
	private Boolean isFrozen;
	/**
	 * 创建开始时间
	 */
	private Date createTimeStart;
	/**
	 * 创建结束时间
	 */
	private Date createTimeEnd;
	/**
	 * vs真实姓名
	 */
	private String vsName;
	/**
	 * lc真实姓名
	 */
	private String lcName;
	/**
	 * se真实姓名
	 */
	private String seName;
	/**
	 * A+真实姓名
	 */
	private String aName;
	
	/**
	 * 开始编号
	 */
	private Integer pageIndex;
	
	/**
	 * 取多少条
	 */
	private Integer pageSize;
	
	/**
	 * 通过此字段过滤此se下的us
	 */
	private String seNum;
	/**
	 * 通过此字段过滤此A+下的us
	 */
	private String aNum;
	private String vsNum;
	private String lcNum;
	public String getLcNum() {
        return lcNum;
    }
    public void setLcNum(String lcNum) {
        this.lcNum = lcNum;
    }
    public String getVsNum() {
        return vsNum;
    }
    public void setVsNum(String vsNum) {
        this.vsNum = vsNum;
    }
    /**
	 * 测土配肥 推荐人 编号 
	 */
	private String tjUserNumSearch;
	
	/**
	 * 搜索条件 测土培肥 推荐人 手机号
	 */
	private String tjUserMobileSearch;
	
    /**
     * 搜索条件 测土培肥 推荐人 姓名
     */
	private String tjUserRealNameSearch;
	
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public Integer getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(Integer userAddress) {
		this.userAddress = userAddress;
	}
	public Boolean getIsFrozen() {
		return isFrozen;
	}
	public void setIsFrozen(Boolean isFrozen) {
		this.isFrozen = isFrozen;
	}
	public String getVsName() {
		return vsName;
	}
	public void setVsName(String vsName) {
		this.vsName = vsName;
	}
	public String getLcName() {
		return lcName;
	}
	public void setLcName(String lcName) {
		this.lcName = lcName;
	}
	public String getSeName() {
		return seName;
	}
	public void setSeName(String seName) {
		this.seName = seName;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
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
	public String getSeNum() {
		return seNum;
	}
	public void setSeNum(String seNum) {
		this.seNum = seNum;
	}
	public String getaNum() {
		return aNum;
	}
	public void setaNum(String aNum) {
		this.aNum = aNum;
	}
    public String getTjUserNumSearch() {
        return tjUserNumSearch;
    }
    public void setTjUserNumSearch(String tjUserNumSearch) {
        this.tjUserNumSearch = tjUserNumSearch;
    }
    public String getTjUserMobileSearch() {
        return tjUserMobileSearch;
    }
    public void setTjUserMobileSearch(String tjUserMobileSearch) {
        this.tjUserMobileSearch = tjUserMobileSearch;
    }
    public String getTjUserRealNameSearch() {
        return tjUserRealNameSearch;
    }
    public void setTjUserRealNameSearch(String tjUserRealNameSearch) {
        this.tjUserRealNameSearch = tjUserRealNameSearch;
    }
	
}
