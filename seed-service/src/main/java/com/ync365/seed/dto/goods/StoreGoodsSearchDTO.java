package com.ync365.seed.dto.goods;

public class StoreGoodsSearchDTO {

	public Integer categoryId;
	
	public Integer storeId;
	
	public Integer regionId;
	
	/**
	 * 起订量
	 */
	private Integer moq ;
	
	/**
	 * 排序字段
	 */
	private int sortColumn;
	
	/**
	 * 升序、降序
	 */
	private int sortDirection;
	
	/**
	 * 每页显示条数
	 */
	private int pageSize ;
	
	/**
	 * 第几页
	 */
	private int pageNum ;
	
	private int startIndex;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getMoq() {
		return moq;
	}

	public void setMoq(Integer moq) {
		this.moq = moq;
	}

	public int getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(int sortColumn) {
		this.sortColumn = sortColumn;
	}

	public int getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(int sortDirection) {
		this.sortDirection = sortDirection;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStartIndex() {
		startIndex = (pageNum - 1) * pageSize;
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	
	
}
