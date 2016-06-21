package com.ync365.seed.admin.vo.base;

public class PageFilter implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段
	private String order;// asc/desc
	
	private int startIndex;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	
	/**
	 * 说明： 计算起始记录数 
	 * 
	 * @return
	 */
	public int getStartIndex() {
		startIndex = (page - 1) * rows;
		return startIndex;
	}

	
	public void setStartIndex(int startIndex) {
	
		this.startIndex = startIndex;
	}
	
	
	 

}
