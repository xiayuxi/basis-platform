package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;

public class OrderPayDetailDTO {

    private BigDecimal integralMoney;//积分金额
    
    private BigDecimal bounsFee;//红包金额
    
    private BigDecimal payFee;//支付金额
    
    private BigDecimal saveFee;//返现金额

    public BigDecimal getIntegralMoney() {
        return integralMoney;
    }

    public void setIntegralMoney(BigDecimal integralMoney) {
        this.integralMoney = integralMoney;
    }

    public BigDecimal getBounsFee() {
        return bounsFee;
    }

    public void setBounsFee(BigDecimal bounsFee) {
        this.bounsFee = bounsFee;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    public BigDecimal getSaveFee() {
        return saveFee;
    }

    public void setSaveFee(BigDecimal saveFee) {
        this.saveFee = saveFee;
    }
    
  
}
