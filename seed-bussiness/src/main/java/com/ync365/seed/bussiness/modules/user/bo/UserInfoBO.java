package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;
import java.util.List;

import com.ync365.seed.bussiness.modules.user.entity.SysReceiveGoodsInfo;

public class UserInfoBO {
    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 用户昵称
     */
    private String sysNickname;

    /**
     * 用户真实姓名
     */
    private String sysUserRealName;

    /**
     * 性别
     */
    private Integer sysUserGender;

    /**
     * 身份证号
     */
    private String sysUserIdCard;

    /**
     * 生日
     */
    private Date sysUserBirthday;

    /**
     * 农场面积
     */
    private Integer sysUserFarmArea;

    /**
     * 用户地址编号
     */
    private Integer sysUserAddress;
    
//    /**
//     * 用户地址编号处理结果
//     */
//    private String sysUserAddressString;

    /**
     * 用户地址详情
     */
    private String sysUserAddressDetail;

    /**
     * 用户头像地址
     */
    private String sysUserPhotoPath;

    /**
     * 是否冻结
     */
    private Boolean isFrozen;

    /**
     * 是否审核
     */
    private Boolean isAuthentication;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 注册所在地编号
     */
    private Integer registerLocation;
//    /**
//     * 注册所在地编号处理结果
//     */
//    private String registerLocationString;
    
    /**
     * 手机号
     */
    private String userMobile;
    /**
     * 登录名
     */
    private String userLoginName;
    /**
     * 安全级别
     */
    private Integer safeScore;

    /**
     * vs编号
     */
    private String vsNum;
    /**
     * vs手机号
     */
    private String vsMobile;
    /**
     * vs真实姓名
     */
    private String vsName;
    private Integer vsAddress;
    private String vsAddressDetail;
    private Integer lcAddress;
    private String lcAddressDetail;
    
    /**
     * 大客户审核状态
     */
    private Integer state;

    public Integer getVsAddress() {
        return vsAddress;
    }

    public void setVsAddress(Integer vsAddress) {
        this.vsAddress = vsAddress;
    }

    public String getVsAddressDetail() {
        return vsAddressDetail;
    }

    public void setVsAddressDetail(String vsAddressDetail) {
        this.vsAddressDetail = vsAddressDetail;
    }

    public Integer getLcAddress() {
        return lcAddress;
    }

    public void setLcAddress(Integer lcAddress) {
        this.lcAddress = lcAddress;
    }

    public String getLcAddressDetail() {
        return lcAddressDetail;
    }

    public void setLcAddressDetail(String lcAddressDetail) {
        this.lcAddressDetail = lcAddressDetail;
    }

    /**
     * lc编号
     */
    private String lcNum;
    /**
     * lc手机号
     */
    private String lcMobile;
    /**
     * lc真实姓名
     */
    private String lcName;
    /**
     * se编号
     */
    private String seNum;
    /**
     * se手机号
     */
    private String seMobile;
    /**
     * se真实姓名
     */
    private String seName;
    /**
     * A+编号
     */
    private String aNum;
    /**
     * A+手机号
     */
    private String aMobile;
    /**
     * A+真实姓名
     */
    private String aName;

    /**
     * 种植种类
     */
    private List<String> sysUserGrowKinds;

    /**
     * 推荐人编号
     */
    private String tjUserNum;

    /**
     * 推荐人手机
     */
    private String tjUserMobile;

    /**
    * 推荐人姓名
    */
    private String tjUserRealName;
    
    /**
     * 收货地址
     */
    private List<SysReceiveGoodsInfo> sysReceiveGoodsInfos;

    public String getVsNum() {
        return vsNum;
    }

    public void setVsNum(String vsNum) {
        this.vsNum = vsNum;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getSysNickname() {
        return sysNickname;
    }

    public void setSysNickname(String sysNickname) {
        this.sysNickname = sysNickname;
    }

    public String getSysUserRealName() {
        return sysUserRealName;
    }

    public void setSysUserRealName(String sysUserRealName) {
        this.sysUserRealName = sysUserRealName;
    }

    public Integer getSysUserGender() {
        return sysUserGender;
    }

    public void setSysUserGender(Integer sysUserGender) {
        this.sysUserGender = sysUserGender;
    }

    public String getSysUserIdCard() {
        return sysUserIdCard;
    }

    public void setSysUserIdCard(String sysUserIdCard) {
        this.sysUserIdCard = sysUserIdCard;
    }

    public Date getSysUserBirthday() {
        return sysUserBirthday;
    }

    public void setSysUserBirthday(Date sysUserBirthday) {
        this.sysUserBirthday = sysUserBirthday;
    }

    public Integer getSysUserFarmArea() {
        return sysUserFarmArea;
    }

    public void setSysUserFarmArea(Integer sysUserFarmArea) {
        this.sysUserFarmArea = sysUserFarmArea;
    }

    public Integer getSysUserAddress() {
        return sysUserAddress;
    }

    public void setSysUserAddress(Integer sysUserAddress) {
        this.sysUserAddress = sysUserAddress;
    }

    public String getSysUserAddressDetail() {
        return sysUserAddressDetail;
    }

    public void setSysUserAddressDetail(String sysUserAddressDetail) {
        this.sysUserAddressDetail = sysUserAddressDetail;
    }

    public List<String> getSysUserGrowKinds() {
        return sysUserGrowKinds;
    }

    public void setSysUserGrowKinds(List<String> sysUserGrowKinds) {
        this.sysUserGrowKinds = sysUserGrowKinds;
    }

    public String getVsName() {
        return vsName;
    }

    public void setVsName(String vsName) {
        this.vsName = vsName;
    }

    public String getLcName() {
        return lcName;
    }

    public void setLcName(String lcName) {
        this.lcName = lcName;
    }

    public String getSeName() {
        return seName;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }

    public Boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public Boolean getIsAuthentication() {
        return isAuthentication;
    }

    public void setIsAuthentication(Boolean isAuthentication) {
        this.isAuthentication = isAuthentication;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getVsMobile() {
        return vsMobile;
    }

    public void setVsMobile(String vsMobile) {
        this.vsMobile = vsMobile;
    }

    public String getLcNum() {
        return lcNum;
    }

    public void setLcNum(String lcNum) {
        this.lcNum = lcNum;
    }

    public String getLcMobile() {
        return lcMobile;
    }

    public void setLcMobile(String lcMobile) {
        this.lcMobile = lcMobile;
    }

    public String getSeNum() {
        return seNum;
    }

    public void setSeNum(String seNum) {
        this.seNum = seNum;
    }

    public String getSeMobile() {
        return seMobile;
    }

    public void setSeMobile(String seMobile) {
        this.seMobile = seMobile;
    }

    public String getaNum() {
        return aNum;
    }

    public void setaNum(String aNum) {
        this.aNum = aNum;
    }

    public String getaMobile() {
        return aMobile;
    }

    public void setaMobile(String aMobile) {
        this.aMobile = aMobile;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getTjUserNum() {
        return tjUserNum;
    }

    public void setTjUserNum(String tjUserNum) {
        this.tjUserNum = tjUserNum;
    }

    public String getTjUserMobile() {
        return tjUserMobile;
    }

    public void setTjUserMobile(String tjUserMobile) {
        this.tjUserMobile = tjUserMobile;
    }

    public String getTjUserRealName() {
        return tjUserRealName;
    }

    public void setTjUserRealName(String tjUserRealName) {
        this.tjUserRealName = tjUserRealName;
    }

    public String getSysUserPhotoPath() {
        return sysUserPhotoPath;
    }

    public void setSysUserPhotoPath(String sysUserPhotoPath) {
        this.sysUserPhotoPath = sysUserPhotoPath;
    }

	public List<SysReceiveGoodsInfo> getSysReceiveGoodsInfos() {
		return sysReceiveGoodsInfos;
	}

	public void setSysReceiveGoodsInfos(
			List<SysReceiveGoodsInfo> sysReceiveGoodsInfos) {
		this.sysReceiveGoodsInfos = sysReceiveGoodsInfos;
	}

	public Integer getRegisterLocation() {
		return registerLocation;
	}

	public void setRegisterLocation(Integer registerLocation) {
		this.registerLocation = registerLocation;
	}

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
