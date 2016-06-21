package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;





/**
 * @author leixc
 *
 */
public class PopInfoSearchBO {
    /**
     * 编号
     */
    private String popNum;

    /**
     * pop登录名
     */
    private String popLoginName;
    /**
     * 安全分数
     */
    private Integer safeScore;

    /**
     * pop 手机号
     */
    private String popMobile;

    /**
     * popstore编号
     */
    private String popStoreNum;

    /**
     * pop 创建时间 开始
     */
    private Date createTimeBegin;
    
    /**
     * pop 创建时间 结束
     */
    private Date createTimeEed;

    /**
     * 是否冻结
     */
    private Boolean isFrozen;

    /**
     * 是否审核
     */
    private Boolean isAuthentication;
    
    /**
     * 真实姓名
     */
    private String popRealName;
    
    /**
     * 昵称
     */
    private String popNickname;

    public String getPopNum() {
        return popNum;
    }

    public void setPopNum(String popNum) {
        this.popNum = popNum;
    }

    public String getPopLoginName() {
        return popLoginName;
    }

    public void setPopLoginName(String popLoginName) {
        this.popLoginName = popLoginName;
    }

    public String getPopMobile() {
        return popMobile;
    }

    public void setPopMobile(String popMobile) {
        this.popMobile = popMobile;
    }

    public String getPopStoreNum() {
        return popStoreNum;
    }

    public void setPopStoreNum(String popStoreNum) {
        this.popStoreNum = popStoreNum;
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

    public String getPopRealName() {
        return popRealName;
    }

    public void setPopRealName(String popRealName) {
        this.popRealName = popRealName;
    }

    public String getPopNickname() {
        return popNickname;
    }

    public void setPopNickname(String popNickname) {
        this.popNickname = popNickname;
    }

    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Date getCreateTimeEed() {
        return createTimeEed;
    }

    public void setCreateTimeEed(Date createTimeEed) {
        this.createTimeEed = createTimeEed;
    }

	public Integer getSafeScore() {
		return safeScore;
	}

	public void setSafeScore(Integer safeScore) {
		this.safeScore = safeScore;
	}


}
