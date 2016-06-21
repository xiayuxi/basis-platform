package com.ync365.seed.bussiness.modules.order.entity;

public class OrderAddress {
    private Integer id;

    private String orderNo;

    private String receiverNum;

    private String receiverName;

    private String receiverTel;

    private String receiverAddress;

    private String vsNum;

    private String vsName;

    private String vsAddress;

    private String vsTel;

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
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getReceiverNum() {
        return receiverNum;
    }

    public void setReceiverNum(String receiverNum) {
        this.receiverNum = receiverNum == null ? null : receiverNum.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel == null ? null : receiverTel.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getVsNum() {
        return vsNum;
    }

    public void setVsNum(String vsNum) {
        this.vsNum = vsNum == null ? null : vsNum.trim();
    }

    public String getVsName() {
        return vsName;
    }

    public void setVsName(String vsName) {
        this.vsName = vsName == null ? null : vsName.trim();
    }

    public String getVsAddress() {
        return vsAddress;
    }

    public void setVsAddress(String vsAddress) {
        this.vsAddress = vsAddress == null ? null : vsAddress.trim();
    }

    public String getVsTel() {
        return vsTel;
    }

    public void setVsTel(String vsTel) {
        this.vsTel = vsTel == null ? null : vsTel.trim();
    }
}