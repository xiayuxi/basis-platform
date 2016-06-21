package com.ync365.seed.dto.order.output;

import java.util.ArrayList;
import java.util.List;

public class PreparedPayOutputDTO {
    
    private List<PreparedPayOrderDTO> orders = new ArrayList<PreparedPayOrderDTO>();

    public List<PreparedPayOrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<PreparedPayOrderDTO> orders) {
        this.orders = orders;
    }
    
    
    
    /*private Integer orderType;
    
    private OrderDTO orderDTO;
    
    private MergeDTO margeDTO;

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public MergeDTO getMargeDTO() {
        return margeDTO;
    }

    public void setMargeDTO(MergeDTO margeDTO) {
        this.margeDTO = margeDTO;
    }*/
    
   
    
}
