package com.ync365.seed.dto.base;

public class ResponseListDTO {

	/**
	 * 返回列表时 存放 list列表
	 */
	private Object datas;
	
	/**
	 * 返回列表时 存放 count
	 */
	private Integer count;

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
}
