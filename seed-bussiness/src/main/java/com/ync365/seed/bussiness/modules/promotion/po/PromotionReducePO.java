package com.ync365.seed.bussiness.modules.promotion.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class PromotionReducePO extends PromotionPO{
    
    private Integer reduceType;

    private BigDecimal targetAmount;

    private BigDecimal reduceAmount;
    
    private List <PromotionReduceRegPO> reduceRegs = new ArrayList<PromotionReduceRegPO>();

    public Integer getReduceType() {
        return reduceType;
    }

    public void setReduceType(Integer reduceType) {
        this.reduceType = reduceType;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public BigDecimal getReduceAmount() {
        return reduceAmount;
    }

    public void setReduceAmount(BigDecimal reduceAmount) {
        this.reduceAmount = reduceAmount;
    }

    public List<PromotionReduceRegPO> getReduceRegs() {
        return reduceRegs;
    }

    public void setReduceRegs(List<PromotionReduceRegPO> reduceRegs) {
        this.reduceRegs = reduceRegs;
    }
    
}