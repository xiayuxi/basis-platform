package com.ync365.seed.bussiness.modules.order.entity;

import java.util.Date;

public class OrderPromotionGoods {
    private Integer id;

    private Integer orderId;

    private Integer storeId;

    private Integer orderPromotionId;

    private Integer orderGoodsId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getOrderPromotionId() {
        return orderPromotionId;
    }

    public void setOrderPromotionId(Integer orderPromotionId) {
        this.orderPromotionId = orderPromotionId;
    }

    public Integer getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Integer orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}