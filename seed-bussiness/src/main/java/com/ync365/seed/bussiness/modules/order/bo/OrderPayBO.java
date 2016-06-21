package com.ync365.seed.bussiness.modules.order.bo;

public class OrderPayBO {
    private String orderNo;
    
    private Integer orderType; // 订单类型 0、主；1、子；2、合并
    
    private Integer payType; // 0、自主；1、
    
    private String userNum;

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
    
}
