package com.ync365.seed.bussiness.modules.promotion.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PromotionGoodsSku {
    private Integer promotionGoodsSkuId;

    private Integer promotionId;

    private Integer goodsId;

    private Integer skuId;

    private BigDecimal promotionPrice;

    private BigDecimal discount;

    /**村站佣金 **/
    private BigDecimal villageExpense;

    /**平台佣金 **/
    private BigDecimal platformExpense;

    /**A+费用**/
    private BigDecimal aExpense;

    /**促销费用 **/
    private BigDecimal manpowercosts;

    /**LC佣金 **/
    private BigDecimal lcExpense;

    /**积分费用 **/
    private BigDecimal integralExpense;

    /**服务工程师**/
    private BigDecimal engineerExpense;

    private BigDecimal promotionAmount;

    private String createUserNum;

    private Date createTime;

    private String updateUserNum;

    private Date updateTime;

    private Integer isDelete;

    public Integer getPromotionGoodsSkuId() {
        return promotionGoodsSkuId;
    }

    public void setPromotionGoodsSkuId(Integer promotionGoodsSkuId) {
        this.promotionGoodsSkuId = promotionGoodsSkuId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getVillageExpense() {
        return villageExpense;
    }

    public void setVillageExpense(BigDecimal villageExpense) {
        this.villageExpense = villageExpense;
    }

    public BigDecimal getPlatformExpense() {
        return platformExpense;
    }

    public void setPlatformExpense(BigDecimal platformExpense) {
        this.platformExpense = platformExpense;
    }

    public BigDecimal getaExpense() {
        return aExpense;
    }

    public void setaExpense(BigDecimal aExpense) {
        this.aExpense = aExpense;
    }

    public BigDecimal getManpowercosts() {
        return manpowercosts;
    }

    public void setManpowercosts(BigDecimal manpowercosts) {
        this.manpowercosts = manpowercosts;
    }

    public BigDecimal getLcExpense() {
        return lcExpense;
    }

    public void setLcExpense(BigDecimal lcExpense) {
        this.lcExpense = lcExpense;
    }

    public BigDecimal getIntegralExpense() {
        return integralExpense;
    }

    public void setIntegralExpense(BigDecimal integralExpense) {
        this.integralExpense = integralExpense;
    }

    public BigDecimal getEngineerExpense() {
        return engineerExpense;
    }

    public void setEngineerExpense(BigDecimal engineerExpense) {
        this.engineerExpense = engineerExpense;
    }

    public BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public String getCreateUserNum() {
        return createUserNum;
    }

    public void setCreateUserNum(String createUserNum) {
        this.createUserNum = createUserNum == null ? null : createUserNum.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserNum() {
        return updateUserNum;
    }

    public void setUpdateUserNum(String updateUserNum) {
        this.updateUserNum = updateUserNum == null ? null : updateUserNum.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}