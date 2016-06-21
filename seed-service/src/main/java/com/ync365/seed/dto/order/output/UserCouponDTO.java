package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;

public class UserCouponDTO {
    
    private Integer userCouponId;
    private BigDecimal couponAmount;
    public Integer getUserCouponId() {
        return userCouponId;
    }
    public void setUserCouponId(Integer userCouponId) {
        this.userCouponId = userCouponId;
    }
    public BigDecimal getCouponAmount() {
        return couponAmount;
    }
    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }
    
}
