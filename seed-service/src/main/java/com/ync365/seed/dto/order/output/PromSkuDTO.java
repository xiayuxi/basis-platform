package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PromSkuDTO {
    private Integer promotionId;
    private String promontionName;
    private Long endTime;
    private Integer promotionType;
    private BigDecimal saveAmount; // 节省金额
    private BigDecimal totalAmount; // 总价格
    private BigDecimal actualAmount; // 实际金额
    private BigDecimal targetAmount; // 触发条件金额
    private BigDecimal differAmount; // 距离触发活动差多少钱
    private BigDecimal reduceAmount; 
    private Integer isMeet;
    private String desc; 
    private List<SkuDTO> skus = new ArrayList<SkuDTO>();
    
    
    public BigDecimal getReduceAmount() {
        return reduceAmount;
    }
    public void setReduceAmount(BigDecimal reduceAmount) {
        this.reduceAmount = reduceAmount;
    }
    public BigDecimal getTargetAmount() {
        return targetAmount;
    }
    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }
    public BigDecimal getDifferAmount() {
        return differAmount;
    }
    public void setDifferAmount(BigDecimal differAmount) {
        this.differAmount = differAmount;
    }
    
    public Integer getPromotionId() {
        return promotionId;
    }
    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }
    public String getPromontionName() {
        return promontionName;
    }
    public void setPromontionName(String promontionName) {
        this.promontionName = promontionName;
    }
    public Long getEndTime() {
        return endTime;
    }
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
    public Integer getPromotionType() {
        return promotionType;
    }
    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Integer getIsMeet() {
        return isMeet;
    }
    public void setIsMeet(Integer isMeet) {
        this.isMeet = isMeet;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public List<SkuDTO> getSkus() {
        return skus;
    }
    public void setSkus(List<SkuDTO> skus) {
        this.skus = skus;
    }
    public BigDecimal getSaveAmount() {
        return saveAmount;
    }
    public void setSaveAmount(BigDecimal saveAmount) {
        this.saveAmount = saveAmount;
    }
    public BigDecimal getActualAmount() {
        return actualAmount;
    }
    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }
    
}
