package com.ync365.seed.bussiness.modules.user.bo;


import java.util.Date;
import java.util.List;

import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;

public class SsInfoSearchBO {
    
    /**
     * 搜索条件 服务店铺名称
     */
    private String popStoreNameSearch;
    
    /**
     * 搜索条件 用户名
     */
    private String userNameSearch;
    
    /**
     * 搜索条件 ss联系人
     */
    private String userRealNameSearch;
    
    /**
     * 搜索条件 ss联系电话
     */
    private String userMobileSearch;
    
    /**
     * 搜索条件 ss编号
     */
    private String userNumSearch;
    
    /**
     * 搜索条件 ss状态
     */
    private Boolean isFrozenSearch;
    
    /**
     * 搜索条件 ss注册时间 开始
     */
    private Date beginDateSearch;
    
    /**
     * 搜索条件 ss注册时间 结束
     */
    private Date endDateSearch;
    
    /**
     * 开始页数
     */
    private Integer pageIndex;
    
    /**
     * 取多少条
     */
    private Integer pageSize;
    
    /**
     * 存放PopStoreNum 编号数组
     */
    private List<String>  popStoreNums;
    
 

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

    public String getPopStoreNameSearch() {
        return popStoreNameSearch;
    }

    public void setPopStoreNameSearch(String popStoreNameSearch) {
        this.popStoreNameSearch = popStoreNameSearch;
    }

    public String getUserRealNameSearch() {
        return userRealNameSearch;
    }

    public void setUserRealNameSearch(String userRealNameSearch) {
        this.userRealNameSearch = userRealNameSearch;
    }

    public String getUserMobileSearch() {
        return userMobileSearch;
    }

    public void setUserMobileSearch(String userMobileSearch) {
        this.userMobileSearch = userMobileSearch;
    }

    public Boolean getIsFrozenSearch() {
        return isFrozenSearch;
    }

    public void setIsFrozenSearch(Boolean isFrozenSearch) {
        this.isFrozenSearch = isFrozenSearch;
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

    public List<String> getPopStoreNums() {
        return popStoreNums;
    }

    public void setPopStoreNums(List<String> popStoreNums) {
        this.popStoreNums = popStoreNums;
    }

	public String getUserNumSearch() {
		return userNumSearch;
	}

	public void setUserNumSearch(String userNumSearch) {
		this.userNumSearch = userNumSearch;
	}

    public String getUserNameSearch() {
        return userNameSearch;
    }

    public void setUserNameSearch(String userNameSearch) {
        this.userNameSearch = userNameSearch;
    }
    
    
}
