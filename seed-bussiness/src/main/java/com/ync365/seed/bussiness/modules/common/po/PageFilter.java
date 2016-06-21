package com.ync365.seed.bussiness.modules.common.po;

/** 分页查询的基类 */
public class PageFilter implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 当前页码，从1开始 */
	private int pageIndex;
	/** 每页显示记录数 */
	private int pageSize;
	/** 排序字段 */
	private String sort;
	/** asc/desc */
	private String order;
	/** 查询起始行(计算值) */
	private int startIndex;
	public final int getStartIndex() {
		return (pageIndex - 1) * pageSize;
	}

	public final int getPageIndex() {
		return pageIndex;
	}

	public final void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public final int getPageSize() {
		return pageSize;
	}

	public final void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public final String getSort() {
		return sort;
	}

	public final void setSort(String sort) {
		this.sort = sort;
	}

	public final String getOrder() {
		return order;
	}

	public final void setOrder(String order) {
		this.order = order;
	}

}
