package com.ync365.seed.bussiness.modules.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderGoodsChangeInfo {
    private Integer id;

    private Integer orderGoodsId;

    private BigDecimal afterGoodsPrice;

    private Integer auditStatus;

    private String changeNum;

    private String changeName;

    private Date changeTime;

    private String auditNum;

    private String auditName;

    private Date auditTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Integer orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public BigDecimal getAfterGoodsPrice() {
        return afterGoodsPrice;
    }

    public void setAfterGoodsPrice(BigDecimal afterGoodsPrice) {
        this.afterGoodsPrice = afterGoodsPrice;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(String changeNum) {
        this.changeNum = changeNum == null ? null : changeNum.trim();
    }

    public String getChangeName() {
        return changeName;
    }

    public void setChangeName(String changeName) {
        this.changeName = changeName == null ? null : changeName.trim();
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getAuditNum() {
        return auditNum;
    }

    public void setAuditNum(String auditNum) {
        this.auditNum = auditNum == null ? null : auditNum.trim();
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName == null ? null : auditName.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}