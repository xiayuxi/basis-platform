package com.ync365.seed.dto.goods;

/**
 * 入驻商商品查询列表
 *     
 * @Title：GoodsMemberDTO  
 * @Description: TODO   
 * @author: robo        
 * @date: 2015年11月1日 下午2:29:22      
 * @version     
 *
 */
public class GoodsMemberDTO {

	/**
	 * 商品名称 
	 */
	private String goodsName;
	
	/**
	 * 上架时间区间
	 */
	private String startDate;
	private String endDate;
	
	/**
	 * 分类 Id
	 */
	private Integer categoryId;
	
	/**
	 * 品牌名称 
	 */
	private String brandName;
	
	/**
	 * 店铺Id
	 */
	private Integer storeId;
	
	/**
	 * 状态   0:未上架 1：审核  2：审核不通过  3:上架  4：下架
	 */
	private Integer status ;
	
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
	
}
