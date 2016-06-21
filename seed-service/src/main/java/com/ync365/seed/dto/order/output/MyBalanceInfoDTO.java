package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;

public class MyBalanceInfoDTO {

    private Long timeStamp;//使用日期
    
    private String way;//种类
    
    private Integer balanceType;//类型
    
    private BigDecimal balanceFee;//余额金额

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Integer getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(Integer balanceType) {
        this.balanceType = balanceType;
    }

    public BigDecimal getBalanceFee() {
        return balanceFee;
    }

    public void setBalanceFee(BigDecimal balanceFee) {
        this.balanceFee = balanceFee;
    }
    
    
}
