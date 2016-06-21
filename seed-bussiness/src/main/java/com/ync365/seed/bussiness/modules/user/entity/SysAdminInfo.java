package com.ync365.seed.bussiness.modules.user.entity;

import java.util.Date;

/**
 * @author xieang
 * 2015年9月24日
 */
public class SysAdminInfo {
    /**
     * 用户编号
     */
    private String adminNum;

    /**
     * 姓名
     */
    private String name;

    /**
     * 座机号码
     */
    private String telephone;

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

    /**
     * 创建时间
     */
    private Date createTime;
    
    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum == null ? null : adminNum.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
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
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}