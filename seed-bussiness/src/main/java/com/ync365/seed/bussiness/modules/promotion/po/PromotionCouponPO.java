package com.ync365.seed.bussiness.modules.promotion.po;

import java.math.BigDecimal;
import java.util.Date;

public class PromotionCouponPO extends PromotionPO{
    private Integer couponId;

    private BigDecimal couponAmount;

    private Date validStart;

    private Date validEnd;

    private Integer promotionId;

    private Integer couponUsedCount;

    private Integer isDelete;

    private Integer status;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Date getValidStart() {
        return validStart;
    }

    public void setValidStart(Date validStart) {
        this.validStart = validStart;
    }

    public Date getValidEnd() {
        return validEnd;
    }

    public void setValidEnd(Date validEnd) {
        this.validEnd = validEnd;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getCouponUsedCount() {
        return couponUsedCount;
    }

    public void setCouponUsedCount(Integer couponUsedCount) {
        this.couponUsedCount = couponUsedCount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}