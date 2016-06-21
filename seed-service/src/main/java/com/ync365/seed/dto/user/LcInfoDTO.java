package com.ync365.seed.dto.user;

import java.util.Date;
import java.util.List;

public class LcInfoDTO {
    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 姓名
     */
    private String name;
    private String photoPath;
    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    /**
     * 座机电话
     */
    private String telephone;
    /**
     * 从事工作
     */
    private List<String> doworks;
    private Date birthday;
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<String> getDoworks() {
        return doworks;
    }

    public void setDoworks(List<String> doworks) {
        this.doworks = doworks;
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    public Integer getLocalFarmerCount() {
        return localFarmerCount;
    }

    public void setLocalFarmerCount(Integer localFarmerCount) {
        this.localFarmerCount = localFarmerCount;
    }

    public Integer getLocalCroplandArea() {
        return localCroplandArea;
    }

    public Boolean getApplyVs() {
        return applyVs;
    }

    public void setApplyVs(Boolean applyVs) {
        this.applyVs = applyVs;
    }

    public void setLocalCroplandArea(Integer localCroplandArea) {
        this.localCroplandArea = localCroplandArea;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public List<Integer> getServiceDistincts() {
        return serviceDistincts;
    }

    public void setServiceDistincts(List<Integer> serviceDistincts) {
        this.serviceDistincts = serviceDistincts;
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

    public String getSeNum() {
        return seNum;
    }

    public void setSeNum(String seNum) {
        this.seNum = seNum;
    }

    /**
     * 工作年限
     */
    private Integer workYear;

    /**
     * 本地农户数量
     */
    private Integer localFarmerCount;

    /**
     * 本地农地面积
     */
    private Integer localCroplandArea;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 服务区域
     */
    private List<Integer> serviceDistincts;

    /**
     * 地址
     */
    private Integer address;

    /**
     * 地址详情
     */
    private String addressDetail;

    /**
     * SE编号  SENum
     */
    private String seNum;
    /**
     * 申请VS
     */
    private Boolean applyVs;
}
