package com.ync365.seed.bussiness.modules.order.bo;

import java.util.Date;

public class OrderOptLogBO {
	private Integer id;
	
	private Integer orderId;
	
	private String orderNo;
	
	private Integer orderType;
	
	private Integer orderStatus;
	
	private Integer delStatus;
	
	private String optNum;
	
	private Date optTime;
	
	private String optName;
	
	private Integer changeId;
	
	private String preparedNo;
	private String stockNo;
	private String delivelyNo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
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
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	public String getOptNum() {
		return optNum;
	}
	public void setOptNum(String optNum) {
		this.optNum = optNum;
	}
	public Date getOptTime() {
		return optTime;
	}
	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public Integer getChangeId() {
		return changeId;
	}
	public void setChangeId(Integer changeId) {
		this.changeId = changeId;
	}
	public String getPreparedNo() {
		return preparedNo;
	}
	public void setPreparedNo(String preparedNo) {
		this.preparedNo = preparedNo;
	}
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getDelivelyNo() {
		return delivelyNo;
	}
	public void setDelivelyNo(String delivelyNo) {
		this.delivelyNo = delivelyNo;
	}
	
	

}
