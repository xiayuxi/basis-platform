package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SubOrderDTO {

    private String orderNo;//订单编号

    private BigDecimal payFee;//订单金额

    private Integer status;//订单状态
    
    private Integer storeId;//店铺ID
    
    private String popNum;//卖家用户号
    
    private Integer orderId;//子订单编号
    
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPopNum() {
        return popNum;
    }

    public void setPopNum(String popNum) {
        this.popNum = popNum;
    }

    private List<OrderGoodsDTO> goodsList = new ArrayList<OrderGoodsDTO>();//订单商品表
    
    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public List<OrderGoodsDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsDTO> goodsList) {
        this.goodsList = goodsList;
    }
    
    
}
