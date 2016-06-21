package com.ync365.seed.dto.user;

public class PopStoreSearchDTO {
    private String nameSearch;
    /**
     * 页码 
     */
    private Integer pageIndex;
    
    /**
     * 条数
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
}
