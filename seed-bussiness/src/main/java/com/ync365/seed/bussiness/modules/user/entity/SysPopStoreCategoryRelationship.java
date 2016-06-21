package com.ync365.seed.bussiness.modules.user.entity;

public class SysPopStoreCategoryRelationship {
	
	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 商铺编号
	 */
    private String popStoreNum;

    /**
     * 类别ID
     */
    private Integer categoryId;

    public String getPopStoreNum() {
        return popStoreNum;
    }

    public void setPopStoreNum(String popStoreNum) {
        this.popStoreNum = popStoreNum == null ? null : popStoreNum.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    
}