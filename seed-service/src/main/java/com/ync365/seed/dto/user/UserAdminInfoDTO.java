package com.ync365.seed.dto.user;

import java.util.List;

public class UserAdminInfoDTO {
    /**
     * 用户编号
     */
    private String adminNum;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 地址编号
     */
    private Integer address;

    /**
     * 地址详情
     */
    private String addressDetail;

    /**
     * 身份证号
     */
    private String idCard;
    private List<Integer> serviceserviceDistincts;
    private String aNum;

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public List<Integer> getServiceserviceDistincts() {
        return serviceserviceDistincts;
    }

    public void setServiceserviceDistincts(List<Integer> serviceserviceDistincts) {
        this.serviceserviceDistincts = serviceserviceDistincts;
    }

    public String getaNum() {
        return aNum;
    }

    public void setaNum(String aNum) {
        this.aNum = aNum;
    }


}
