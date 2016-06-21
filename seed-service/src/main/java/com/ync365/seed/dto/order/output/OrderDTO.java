package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    
	private Integer orderId;//订单Id
	
    private String orderNo;//订单编号
    
    private BigDecimal payFee;//订单金额
    
    private Integer status;//订单状态
    
    private Integer orderType;//是否包含子订单
    
    private String bookOrderUserName;//下单人
    
    private Date createTime;//下单时间
    
    private Integer storeId;//店铺ID
    
    private String popNum;//卖家num
    
    private Integer payWay;//支付方式 农行支付 余额 红包 混合支付
    
    private List<SubOrderDTO> subList = new ArrayList<SubOrderDTO>();//子订单列表
    
    private List<OrderGoodsDTO> goodsList = new ArrayList<OrderGoodsDTO>();//订单商品表
    
    public String getPopNum() {
        return popNum;
    }

    public void setPopNum(String popNum) {
        this.popNum = popNum;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getBookOrderUserName() {
        return bookOrderUserName;
    }

    public void setBookOrderUserName(String bookOrderUserName) {
        this.bookOrderUserName = bookOrderUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public List<SubOrderDTO> getSubList() {
        return subList;
    }

    public void setSubList(List<SubOrderDTO> subList) {
        this.subList = subList;
    }

    public List<OrderGoodsDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsDTO> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public String toString() {
        return "OrderDTO [orderId=" + orderId + ", orderNo=" + orderNo + ", payFee=" + payFee + ", status=" + status
                + ", orderType=" + orderType + ", bookOrderUserName=" + bookOrderUserName + ", createTime=" + createTime
                + ", storeId=" + storeId + ", popNum=" + popNum + ", payWay=" + payWay + ", subList=" + subList
                + ", goodsList=" + goodsList + "]";
    }
}
