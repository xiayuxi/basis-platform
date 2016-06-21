package com.ync365.seed.bussiness.modules.order.entity;

import java.util.Date;

public class OrderEngineer {
    private Integer id;

    private String orderNo;

    private String engineerNum;

    private String engineerName;

    private String engineerPhone;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getEngineerNum() {
        return engineerNum;
    }

    public void setEngineerNum(String engineerNum) {
        this.engineerNum = engineerNum == null ? null : engineerNum.trim();
    }

    public String getEngineerName() {
        return engineerName;
    }

    public void setEngineerName(String engineerName) {
        this.engineerName = engineerName == null ? null : engineerName.trim();
    }

    public String getEngineerPhone() {
        return engineerPhone;
    }

    public void setEngineerPhone(String engineerPhone) {
        this.engineerPhone = engineerPhone == null ? null : engineerPhone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}