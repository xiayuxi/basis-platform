package com.ync365.seed.dto.order.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ManageOrderPageDTO {
    
    private List<ManageOrderDTO> orderList= new ArrayList<ManageOrderDTO>();//订单列表
    
    private Long orderNum;//订单总数
    
    private BigDecimal orderPrice;//订单总金额
    
    private Long totalNum;//总页数
    
    private String mergeOrderNo;//合并支付订单变好；
    
    private Integer mergeOrderId;//合并支付订单ID；
    
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

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public List<ManageOrderDTO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<ManageOrderDTO> orderList) {
		this.orderList = orderList;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

}
