package com.ync365.seed.dto.order.output;

import java.util.ArrayList;
import java.util.List;

public class OrderSplitPageDTO {

    private OrderDetailDTO orderDetail;//订单详情
    
    private List<OrderGoodsDTO> goodsList = new ArrayList<OrderGoodsDTO>();//商品列表
    
    public OrderDetailDTO getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailDTO orderDetail) {
        this.orderDetail = orderDetail;
    }

    public List<OrderGoodsDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsDTO> goodsList) {
        this.goodsList = goodsList;
    }
}
