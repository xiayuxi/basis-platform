package com.ync365.seed.dto.goods;

/**
 * 商品列表搜索DTO
 *     
 * @Title：GoodsSearchDTO  
 * @Description: TODO   
 * @author: robo        
 * @date: 2015年10月9日 下午4:37:27      
 * @version     
 *
 */
public class GoodsSearchDTO {

	/**
	 * 品牌ID
	 */
	private Integer brandId;
	
	/**
	 * 种类ID
	 */
	private Integer categoryId ;
	
	/**
	 * 起订量
	 */
	private Integer moq ;
	
	/**
	 * 起止价格
	 */
	private Double startPrice;
	
	/**
	 * 起止价格
	 */
	private Double endPrice;
	
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
	
	/**
	 * 区域ID
	 */
	private Integer regionId;
	
	/**
	 * 关键字
	 */
	private String keywords ;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getMoq() {
		return moq;
	}

	public void setMoq(Integer moq) {
		this.moq = moq;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Double endPrice) {
		this.endPrice = endPrice;
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

	/**
	 * 说明： 计算起始记录数 
	 * 
	 * @return
	 */
	public int getStartIndex() {
		startIndex = (pageNum - 1) * pageSize;
		return startIndex;
	}


	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	
	
}
