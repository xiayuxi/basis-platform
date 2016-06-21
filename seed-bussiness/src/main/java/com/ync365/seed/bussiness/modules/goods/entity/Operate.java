package com.ync365.seed.bussiness.modules.goods.entity;

import java.util.Date;

public class Operate {
    
    /**
     * 主键
     */
    private Integer operateId;

    /**
     * 原来主键，现在没用了
     */
    private Integer skuId;
    
    /**
     * 操作的商品
     */
    private Integer goodsId;

    /**
     * 审核人
     */
    private String auditUserId;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 审核原因
     */
    private String reason;

    /**
     * 上架人
     */
    private String releaseUserId;

    /**
     * 下架人
     */
    private String cancelReleaseUserId;

    /**
     * 上架时间
     */
    private Date releaseTime;

    /**
     * 下架时间
     */
    private Date cancelReleaseTime;

    /**
     * 操作者
     */
    private Integer optUser;

    /**
     * 操作时间
     */
    private Date optDate;

    /**
     * 操作终端
     */
    private String optTerm;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getReleaseUserId() {
        return releaseUserId;
    }

    public void setReleaseUserId(String releaseUserId) {
        this.releaseUserId = releaseUserId;
    }

    public String getCancelReleaseUserId() {
        return cancelReleaseUserId;
    }

    public void setCancelReleaseUserId(String cancelReleaseUserId) {
        this.cancelReleaseUserId = cancelReleaseUserId;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getCancelReleaseTime() {
        return cancelReleaseTime;
    }

    public void setCancelReleaseTime(Date cancelReleaseTime) {
        this.cancelReleaseTime = cancelReleaseTime;
    }

    public Integer getOptUser() {
        return optUser;
    }

    public void setOptUser(Integer optUser) {
        this.optUser = optUser;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public String getOptTerm() {
        return optTerm;
    }

    public void setOptTerm(String optTerm) {
        this.optTerm = optTerm == null ? null : optTerm.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOperateId() {
        return operateId;
    }

    public void setOperateId(Integer operateId) {
        this.operateId = operateId;
    }
}