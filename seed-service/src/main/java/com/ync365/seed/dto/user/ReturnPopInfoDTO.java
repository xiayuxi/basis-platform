package com.ync365.seed.dto.user;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author leixc
 *
 */
public class ReturnPopInfoDTO {

    /**
     * 主键id
     * 
     */
    private Integer id;
    /**
     * 编号
     */
    private String popNum;

    /**
     * pop登录名
     */
    private String popLoginName;
    private Integer safeScore;

    /**
     * pop密码
     */
    private String popPassword;

    /**
     * pop 手机号
     */
    private String popMobile;

    /**
     * popstore编号
     */
    private String popStoreNum;

    /**
     * pop 创建时间
     */
    private Date createTime;

    /**
     * pop更新时间
     */
    private Date updateTime;

    /**
     * 登录人上次
     */
    private String lastOptName;

    /**
     * 是否删除
     */
    private Boolean isDel;

    /**
     * 是否冻结
     */
    private Boolean isFrozen;

    /**
     * 是否审核
     */
    private Boolean isAuthentication;

    /**
     * 上次登录时间
     */
    private Date beforeLoginTime;

    /**
     * 真实姓名
     */
    private String popRealName;

    /**
     * 昵称
     */
    private String popNickname;

    /**
     * 性别
     */
    private Integer popGender;

    /**
     * pop身份证
     */
    private String popIdCard;

    /**
     * pop生日
     */
   // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ",timezone="GMT+8")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date popBirthday;

    /**
     * pop图片路径
     */
    private String popPhotoPath;

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

    public String getPopPassword() {
        return popPassword;
    }

    public void setPopPassword(String popPassword) {
        this.popPassword = popPassword;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastOptName() {
        return lastOptName;
    }

    public void setLastOptName(String lastOptName) {
        this.lastOptName = lastOptName;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
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

    public Date getBeforeLoginTime() {
        return beforeLoginTime;
    }

    public void setBeforeLoginTime(Date beforeLoginTime) {
        this.beforeLoginTime = beforeLoginTime;
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

    public Integer getPopGender() {
        return popGender;
    }

    public void setPopGender(Integer popGender) {
        this.popGender = popGender;
    }

    public String getPopIdCard() {
        return popIdCard;
    }

    public void setPopIdCard(String popIdCard) {
        this.popIdCard = popIdCard;
    }

    public String getPopPhotoPath() {
        return popPhotoPath;
    }

    public void setPopPhotoPath(String popPhotoPath) {
        this.popPhotoPath = popPhotoPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSafeScore() {
        return safeScore;
    }

    public void setSafeScore(Integer safeScore) {
        this.safeScore = safeScore;
    }

    public Date getPopBirthday() {
        return popBirthday;
    }

    public void setPopBirthday(Date popBirthday) {
        this.popBirthday = popBirthday;
    }
}
