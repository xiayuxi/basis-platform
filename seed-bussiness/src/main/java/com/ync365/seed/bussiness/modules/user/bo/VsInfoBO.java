package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;
import java.util.List;

public class VsInfoBO {

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 用户真实姓名
     */
    private String name;

    /**
     * 用户昵称
     */
    private String sysNickname;
    
    /**
     * 座机电话
     */
    private String telephone;
    private Integer gender;
    private Integer farmArea;


    private String photoPath;
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
     * 注册所在地编号
     */
    private Integer registerLocation;
//    /**
//     * 注册所在地编号处理结果
//     */
//    private String registerLocationString;
    /**
     * 生日
     */
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

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
     * 手机号
     */
    private String userMobile;
    
    /**
     * 用户名
     */
    private String userLoginName;
    /**
     * 安全级别
     */
    private Integer safeScore;
    
    /**
     * vs绑定的lc信息
     */
    private String lcInfoName;

    /**
     * vs绑定se信息
     */
    private String seInfoName;

    /**
     * A+名称
     */
    private String aInfoName;

    /**
     * 从事工作
     */
    private List<String> doworks;

    /**
     * 服务区域
     */
    private List<Integer> serviceDistincts;

    /**
     * lC编号  lcNum
     */
    private String lcNum;
    /**
     * SE编号  seNum
     */
    private String seNum;
    /**
     * A+编号  aNum
     */
    private String aNum;

    /**
     * lc手机号
     */
    private String lcMobile;
    /**
    * se手机号
    */
    private String seMobile;
    /**
    * A+手机号
    */
    private String aMobile;

    /* 暂时注销 数量 查询
    // 绑定us数量
    private Integer usCount;
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

    public String getLcNum() {
        return lcNum;
    }

    public void setLcNum(String lcNum) {
        this.lcNum = lcNum;
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

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getLcInfoName() {
        return lcInfoName;
    }

    public void setLcInfoName(String lcInfoName) {
        this.lcInfoName = lcInfoName;
    }

    public String getSeInfoName() {
        return seInfoName;
    }

    public void setSeInfoName(String seInfoName) {
        this.seInfoName = seInfoName;
    }

    public String getaInfoName() {
        return aInfoName;
    }

    public void setaInfoName(String aInfoName) {
        this.aInfoName = aInfoName;
    }

    public String getSeNum() {
        return seNum;
    }

    public void setSeNum(String seNum) {
        this.seNum = seNum;
    }

    public String getaNum() {
        return aNum;
    }

    public void setaNum(String aNum) {
        this.aNum = aNum;
    }

    public String getLcMobile() {
        return lcMobile;
    }

    public void setLcMobile(String lcMobile) {
        this.lcMobile = lcMobile;
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

	public String getSysNickname() {
		return sysNickname;
	}

	public void setSysNickname(String sysNickname) {
		this.sysNickname = sysNickname;
	}

	public Integer getRegisterLocation() {
		return registerLocation;
	}

	public void setRegisterLocation(Integer registerLocation) {
		this.registerLocation = registerLocation;
	}

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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public Integer getSafeScore() {
		return safeScore;
	}

	public void setSafeScore(Integer safeScore) {
		this.safeScore = safeScore;
	}
    
}
