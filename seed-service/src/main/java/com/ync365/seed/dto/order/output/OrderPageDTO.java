package com.ync365.seed.dto.order.output;

import java.util.ArrayList;
import java.util.List;

public class OrderPageDTO {
    
    private List<OrderDTO> orderList = new ArrayList<OrderDTO>();//订单清单
    
    private Long totalNum;//总记录数
    
    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

   
}
