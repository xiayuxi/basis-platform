package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;


/**
 * @author leixc
 * 2015年10月17日
 */
public class AdminSearchBO {
    
    /**
     * 搜索条件  用户名搜索 
     */
    private String adminNameSearch;
    
    /**
     * 搜索条件  手机号搜索
     */
    private String adminMobileSearch;
    
    /**
     * 搜索条件  注册时间 搜索区间   开始
     */
    private Date beginTimeSearch;
    
    /**
     * 搜索条件  注册时间 搜索区间   结束
     */
    private Date endTimeSearch;
    
    /**
     * 搜索条件 角色名称查询
     */
    private String roleNameSearch;

    /**
     * 开始页数
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

    public String getAdminNameSearch() {
        return adminNameSearch;
    }

    public void setAdminNameSearch(String adminNameSearch) {
        this.adminNameSearch = adminNameSearch;
    }

    public String getAdminMobileSearch() {
        return adminMobileSearch;
    }

    public void setAdminMobileSearch(String adminMobileSearch) {
        this.adminMobileSearch = adminMobileSearch;
    }

    public Date getBeginTimeSearch() {
        return beginTimeSearch;
    }

    public void setBeginTimeSearch(Date beginTimeSearch) {
        this.beginTimeSearch = beginTimeSearch;
    }

    public Date getEndTimeSearch() {
        return endTimeSearch;
    }

    public void setEndTimeSearch(Date endTimeSearch) {
        this.endTimeSearch = endTimeSearch;
    }

    public String getRoleNameSearch() {
        return roleNameSearch;
    }

    public void setRoleNameSearch(String roleNameSearch) {
        this.roleNameSearch = roleNameSearch;
    }
   
    
}