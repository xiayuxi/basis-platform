package com.ync365.seed.bussiness.modules.user.bo;


import java.util.Date;
import java.util.List;

public class SsInfoBO {
    /**
     * ss编号
     */
    private String userNum;

    /**
     * ss 登录名
     */
    private String userName;

    /**
     * ss真是姓名
     */
    private String userRealName;

    /**
     * ss 手机号
     */
    private String userMobile;

    /**
     * ss身份证号
     */
    private String userIdCard;
    /**
     * 安全得分
     */
    private Integer safeScore;

    /**
     * ss状态
     */
    private Boolean isFrozen;

    /**
     * ss创建时间
     */
    private Date createTime;
    
    /**
     * popstore list
     */
    private List<PopStoreBO> sysPopStoreList;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public Boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<PopStoreBO> getSysPopStoreList() {
        return sysPopStoreList;
    }

    public void setSysPopStoreList(List<PopStoreBO> sysPopStoreList) {
        this.sysPopStoreList = sysPopStoreList;
    }

	public Integer getSafeScore() {
		return safeScore;
	}

	public void setSafeScore(Integer safeScore) {
		this.safeScore = safeScore;
	}
    
}
