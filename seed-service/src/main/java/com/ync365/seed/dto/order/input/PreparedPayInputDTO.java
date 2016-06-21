package com.ync365.seed.dto.order.input;

public class PreparedPayInputDTO {
    
    private Integer orderId; // 订单ID

    private Integer orderType;//订单类型 0、主；1、子；2、合并
    
    private String userNum; // 用户编号
    
    private Integer payType; // 支付类型
    
    public String getUserNum() {
        return userNum;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

}
