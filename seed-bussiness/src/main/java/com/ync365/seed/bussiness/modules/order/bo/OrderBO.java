package com.ync365.seed.bussiness.modules.order.bo;

public class OrderBO {
	private String orderNo;//订单编号
	
	private Integer orderId;//订单ID
	
	private Integer orderType;//订单类型

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
