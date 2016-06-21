package com.ync365.seed.bussiness.modules.order.bo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfoBO {
	private Integer id;

    private String orderNo;

    private BigDecimal totalAmount;

    private BigDecimal couponAmount;

    private BigDecimal saveAmount;

    private BigDecimal actualAmount;//订单金额

    private Date createTime;//订单时间

    private Integer orderStatus;//订单状态
    /**
     * storeId = -1表示自营订单
     * storeId != -1表示入住商订单
     */
    private Integer storeId;//店铺id

    private Integer orderSource;

    private Integer hasSub;

    private String userNum;

    private Integer payType;

    private Integer payWay;

    private Date payTime;

    private Date signinTime;

    private Integer isDel;

    private Date delTime;

    private Integer isRecDel;

    private Date recDelTime;

    private Integer version;
    
    private String orderId;

    private String orderSubNo;  

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public BigDecimal getSaveAmount() {
		return saveAmount;
	}

	public void setSaveAmount(BigDecimal saveAmount) {
		this.saveAmount = saveAmount;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	public Integer getHasSub() {
		return hasSub;
	}

	public void setHasSub(Integer hasSub) {
		this.hasSub = hasSub;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getSigninTime() {
		return signinTime;
	}

	public void setSigninTime(Date signinTime) {
		this.signinTime = signinTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public Integer getIsRecDel() {
		return isRecDel;
	}

	public void setIsRecDel(Integer isRecDel) {
		this.isRecDel = isRecDel;
	}

	public Date getRecDelTime() {
		return recDelTime;
	}

	public void setRecDelTime(Date recDelTime) {
		this.recDelTime = recDelTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
    
    
}
