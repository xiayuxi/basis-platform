package com.ync365.seed.admin.vo.base;

import java.util.ArrayList;
import java.util.List;

public class Grid implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 总页数
	 */
	private Long total = 0L;  
	@SuppressWarnings("rawtypes")
	private List rows = new ArrayList();
	
	/**
	 * 当前页
	 */
	private int page ;
	
	/**
	 * 每页显示记录数
	 */
	private int pageSize;
	
	/**
	 * 总记录数
	 */
	private Long records ;

	public Long getTotal() {
		
		// 计算总页数 只取整数
		total = records / pageSize;
		// 判断是否还有数据
		if (records % pageSize != 0) {
			// 当前页数加1
			total++;
		}
		// 判断总页数是否为0.
		if (records == 0) {
			// 为0时修改为1.
			total = 1l;
		}
		return total;
				
				
	 
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
