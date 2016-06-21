package com.ync365.seed.bussiness.modules.order.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderPromotionBO {

    private Integer storeId;

    private Integer promotionId;

    private Integer promotionType;

    private BigDecimal saveAmount;

    private Date createTime;
    
    private List <OrderGoodsBO> promGoodsBOList = new ArrayList<OrderGoodsBO>();

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public BigDecimal getSaveAmount() {
        return saveAmount;
    }

    public void setSaveAmount(BigDecimal saveAmount) {
        this.saveAmount = saveAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<OrderGoodsBO> getPromGoodsBOList() {
        return promGoodsBOList;
    }

    public void setPromGoodsBOList(List<OrderGoodsBO> promGoodsBOList) {
        this.promGoodsBOList = promGoodsBOList;
    }
}