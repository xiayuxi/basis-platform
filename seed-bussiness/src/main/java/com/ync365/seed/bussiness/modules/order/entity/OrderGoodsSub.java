package com.ync365.seed.bussiness.modules.order.entity;

import java.util.Date;

public class OrderGoodsSub {
    private Integer id;

    private Integer orderSubId;

    private Integer orderGoodsId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(Integer orderSubId) {
        this.orderSubId = orderSubId;
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