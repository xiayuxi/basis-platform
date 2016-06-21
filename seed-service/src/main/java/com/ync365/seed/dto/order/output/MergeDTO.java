package com.ync365.seed.dto.order.output;

import java.util.ArrayList;
import java.util.List;

public class MergeDTO {
    private Integer mergeOrderId;
    private String mergeOrderNo;
    private List <OrderDTO> orderList = new ArrayList<OrderDTO>();
    public Integer getMergeOrderId() {
        return mergeOrderId;
    }
    public void setMergeOrderId(Integer mergeOrderId) {
        this.mergeOrderId = mergeOrderId;
    }
    public String getMergeOrderNo() {
        return mergeOrderNo;
    }
    public void setMergeOrderNo(String mergeOrderNo) {
        this.mergeOrderNo = mergeOrderNo;
    }
    public List<OrderDTO> getOrderList() {
        return orderList;
    }
    public void setOrderList(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }
   
}
