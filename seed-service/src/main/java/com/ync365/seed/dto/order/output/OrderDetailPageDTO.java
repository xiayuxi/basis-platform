package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailPageDTO {
    
    private List<OrderGoodsDTO> goodsList = new ArrayList<OrderGoodsDTO>();
    
    private OrderDetailDTO orderDetail;
    
    private OrderPayDetailDTO orderPayDetail;
    
    private List<OrderTrackDTO> trackList = new ArrayList<OrderTrackDTO>();
    
    private Long createTime ;
    
    private Long payTime;
    
    private Long affirmTime;
    
    private Long completeTime;
    
    private Integer orderId;
    
    private Integer orderType;
    
    private BigDecimal orderFee;//订单金额
    
    public BigDecimal getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(BigDecimal orderFee) {
        this.orderFee = orderFee;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public Long getAffirmTime() {
        return affirmTime;
    }

    public void setAffirmTime(Long affirmTime) {
        this.affirmTime = affirmTime;
    }

    public Long getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Long completeTime) {
        this.completeTime = completeTime;
    }

    public List<OrderGoodsDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsDTO> goodsList) {
        this.goodsList = goodsList;
    }

    public OrderDetailDTO getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailDTO orderDetail) {
        this.orderDetail = orderDetail;
    }

    public OrderPayDetailDTO getOrderPayDetail() {
        return orderPayDetail;
    }

    public void setOrderPayDetail(OrderPayDetailDTO orderPayDetail) {
        this.orderPayDetail = orderPayDetail;
    }

    public List<OrderTrackDTO> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<OrderTrackDTO> trackList) {
        this.trackList = trackList;
    }

  
    
    
    
}
