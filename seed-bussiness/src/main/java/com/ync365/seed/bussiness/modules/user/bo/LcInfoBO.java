package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;
import java.util.List;

public class LcInfoBO {

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 姓名
     */
    private String name;
    private Date birthday;
    private String nickName;
    private Integer gender;
    private Integer farmArea;

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getFarmArea() {
        return farmArea;
    }

    public void setFarmArea(Integer farmArea) {
        this.farmArea = farmArea;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 座机电话
     */
    private String telephone;
    private String photoPath;

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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
     * 地址
     */
    private Integer address;

    /**
     * 地址详情
     */
    private String addressDetail;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否审核 （1.审核通过 2.审核失败 0.未审核【默认状态】）
     */
    private Integer authenticationState;

    private Date authenticationTime;

    /**
     * 是否冻结
     */
    private Boolean isFrozen;

    /**
     * se info 信息
     */
    private String seInfoName;
    /**
     * a info 信息
     */
    private String aInfoName;

    /**
     * 手机号
     */
    private String userMobile;
    /**
     * 用户名
     */
    private String userLoginName;
    /**
     * 安全
     */
    private String safeScore;

    /**
     * 从事工作
     */
    private List<String> doworks;

    /**
     * 服务区域
     */
    private List<Integer> serviceDistincts;

    /**
     * SE编号  SENum
     */
    private String seNum;
    /**
     * A+编号  aNum
     */
    private String aNum;

    /**
     * SE编号  SEMobile
     */
    private String seMobile;
    /**
     * A+编号  aMobile
     */
    private String aMobile;

    /**
     * 申请VS
     */
    private Boolean applyVs;

    /*//绑定us数量    暂时注销 绑定us vs 数量 显示
    private Integer usCount;
    //绑定vs数量
    private Integer vsCount;
    */

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAuthenticationState() {
        return authenticationState;
    }

    public void setAuthenticationState(Integer authenticationState) {
        this.authenticationState = authenticationState;
    }

    public Date getAuthenticationTime() {
        return authenticationTime;
    }

    public void setAuthenticationTime(Date authenticationTime) {
        this.authenticationTime = authenticationTime;
    }

    public Boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public String getSeInfoName() {
        return seInfoName;
    }

    public void setSeInfoName(String seInfoName) {
        this.seInfoName = seInfoName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getaInfoName() {
        return aInfoName;
    }

    public void setaInfoName(String aInfoName) {
        this.aInfoName = aInfoName;
    }

    public String getaNum() {
        return aNum;
    }

    public void setaNum(String aNum) {
        this.aNum = aNum;
    }

    public String getSeMobile() {
        return seMobile;
    }

    public void setSeMobile(String seMobile) {
        this.seMobile = seMobile;
    }

    public String getaMobile() {
        return aMobile;
    }

    public void setaMobile(String aMobile) {
        this.aMobile = aMobile;
    }

    public Boolean getApplyVs() {
        return applyVs;
    }

    public void setApplyVs(Boolean applyVs) {
        this.applyVs = applyVs;
    }

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getSafeScore() {
		return safeScore;
	}

	public void setSafeScore(String safeScore) {
		this.safeScore = safeScore;
	}

}
