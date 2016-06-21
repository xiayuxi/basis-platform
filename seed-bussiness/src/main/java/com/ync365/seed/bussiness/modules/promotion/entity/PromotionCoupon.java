package com.ync365.seed.bussiness.modules.promotion.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PromotionCoupon {
    private Integer promotionId;

    private Integer couponChannel;

    private Integer couponType;

    private BigDecimal couponAmount;

    private Integer couponCount;

    private Integer couponUsedCount;

    private Date grantStartTime;

    private Date grantEndTime;

    private Long limitedAmount;

    private Integer isDelete;

    private BigDecimal couponAllAmount;

    private BigDecimal couponUsedAmount;

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getCouponChannel() {
        return couponChannel;
    }

    public void setCouponChannel(Integer couponChannel) {
        this.couponChannel = couponChannel;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Integer getCouponUsedCount() {
        return couponUsedCount;
    }

    public void setCouponUsedCount(Integer couponUsedCount) {
        this.couponUsedCount = couponUsedCount;
    }

    public Date getGrantStartTime() {
        return grantStartTime;
    }

    public void setGrantStartTime(Date grantStartTime) {
        this.grantStartTime = grantStartTime;
    }

    public Date getGrantEndTime() {
        return grantEndTime;
    }

    public void setGrantEndTime(Date grantEndTime) {
        this.grantEndTime = grantEndTime;
    }

    public Long getLimitedAmount() {
        return limitedAmount;
    }

    public void setLimitedAmount(Long limitedAmount) {
        this.limitedAmount = limitedAmount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public BigDecimal getCouponAllAmount() {
        return couponAllAmount;
    }

    public void setCouponAllAmount(BigDecimal couponAllAmount) {
        this.couponAllAmount = couponAllAmount;
    }

    public BigDecimal getCouponUsedAmount() {
        return couponUsedAmount;
    }

    public void setCouponUsedAmount(BigDecimal couponUsedAmount) {
        this.couponUsedAmount = couponUsedAmount;
    }
}