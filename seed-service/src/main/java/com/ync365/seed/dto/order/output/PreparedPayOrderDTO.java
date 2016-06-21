package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PreparedPayOrderDTO {
    
    private Integer orderId;//订单Id
    
    private String orderNo;//订单编号
    
    private BigDecimal payFee;//订单金额
    
    private Integer status;//订单状态
    
    private Long createTime;//下单时间
    
    private Integer storeId;//店铺ID
    
    private String popNum;//卖家num
    
    private String ynbMemberId;
    
    private Integer payWay;//支付方式 农行支付 余额 红包 混合支付
    
    private List<UserCouponDTO> couponList = new ArrayList<UserCouponDTO>();
    
    private List<OrderGoodsDTO> goodsList = new ArrayList<OrderGoodsDTO>();//订单商品表

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getPopNum() {
        return popNum;
    }

    public void setPopNum(String popNum) {
        this.popNum = popNum;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public List<OrderGoodsDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsDTO> goodsList) {
        this.goodsList = goodsList;
    }

    public List<UserCouponDTO> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<UserCouponDTO> couponList) {
        this.couponList = couponList;
    }

    public String getYnbMemberId() {
        return ynbMemberId;
    }

    public void setYnbMemberId(String ynbMemberId) {
        this.ynbMemberId = ynbMemberId;
    }

}
