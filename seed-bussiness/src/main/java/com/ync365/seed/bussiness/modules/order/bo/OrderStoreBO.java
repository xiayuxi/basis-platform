package com.ync365.seed.bussiness.modules.order.bo;

import java.util.ArrayList;
import java.util.List;

import com.ync365.seed.bussiness.modules.order.entity.OrderCoupon;
import com.ync365.seed.bussiness.modules.order.entity.OrderStore;

public class OrderStoreBO {
    
    private OrderStore orderStore;
    private List<OrderCoupon> orderCouponList = new ArrayList<OrderCoupon>();
    public OrderStore getOrderStore() {
        return orderStore;
    }
    public void setOrderStore(OrderStore orderStore) {
        this.orderStore = orderStore;
    }
    public List<OrderCoupon> getOrderCouponList() {
        return orderCouponList;
    }
    public void setOrderCouponList(List<OrderCoupon> orderCouponList) {
        this.orderCouponList = orderCouponList;
    }
    
}