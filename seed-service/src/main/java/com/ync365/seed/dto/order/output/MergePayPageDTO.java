package com.ync365.seed.dto.order.output;

import java.util.ArrayList;
import java.util.List;

public class MergePayPageDTO {

    private Integer payWay; //付款方式 云农保 农行支付
    
    private List <ManageOrderDTO> manageOrderList = new ArrayList<ManageOrderDTO>();

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public List<ManageOrderDTO> getManageOrderList() {
        return manageOrderList;
    }

    public void setManageOrderList(List<ManageOrderDTO> manageOrderList) {
        this.manageOrderList = manageOrderList;
    }


    
}
