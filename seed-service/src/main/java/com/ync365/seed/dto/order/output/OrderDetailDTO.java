package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDTO {

    private Integer status;//订单状态1

    private String orderNo;//订单编号1

    private BigDecimal payFee;//订单金额1

    private Integer payWay;//付款方式 农行支付1

    private Integer payType;//付款类型 合并支付1

    private String serverName;//服务工程师

    private String serverTel;//服务工程师电话

    private String serviceTel;//客服电话

    private String deliveryNo;//配送单号1

    private Long createTime;//下单时间1

    private Long splitTime;//拆单时间

    private String splitReason;//拆单原因

    private String userName;//下单人姓名1

    private String userTel;//下单人电话1

    private String userAddress;//下单人地址1

    private String vsName;//村站姓名1

    private String vsTel;//村站电话1

    private String vsAddress;//村站地址1

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getSplitTime() {
        return splitTime;
    }

    public void setSplitTime(Long splitTime) {
        this.splitTime = splitTime;
    }

    public String getSplitReason() {
        return splitReason;
    }

    public void setSplitReason(String splitReason) {
        this.splitReason = splitReason;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getVsName() {
        return vsName;
    }

    public void setVsName(String vsName) {
        this.vsName = vsName;
    }

    public String getVsTel() {
        return vsTel;
    }

    public void setVsTel(String vsTel) {
        this.vsTel = vsTel;
    }

    public String getVsAddress() {
        return vsAddress;
    }

    public void setVsAddress(String vsAddress) {
        this.vsAddress = vsAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerTel() {
        return serverTel;
    }

    public void setServerTel(String serverTel) {
        this.serverTel = serverTel;
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

}
