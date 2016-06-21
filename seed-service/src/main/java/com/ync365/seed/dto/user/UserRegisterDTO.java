package com.ync365.seed.dto.user;

import javax.validation.constraints.Pattern;

public class UserRegisterDTO {

    /**
     * 用户名
     */

    @Pattern.List({ @Pattern(regexp = "\\D+.*|\\d+\\D+.*", message = "不能全数字"),
            @Pattern(regexp = "(\\w|\\-|[\u4e00-\u9fa5]){4,20}", message = "4-20位字符，支持汉字、字母、数字及“-”、“_”组合") })
    private String userName;

    /**
     * 手机号
     */
    private String userMobile;
    /**
     * 手机验证码
     */
    private String mobileValidateCode;
    /**
     * 所在地
     */
    private Integer registerLocation;
    /**
     * US/VS/LC
     */
    private String userRole;

    public Integer getRegisterLocation() {
        return registerLocation;
    }

    public void setRegisterLocation(Integer registerLocation) {
        this.registerLocation = registerLocation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getMobileValidateCode() {
        return mobileValidateCode;
    }

    public void setMobileValidateCode(String mobileValidateCode) {
        this.mobileValidateCode = mobileValidateCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRePassword() {
        return userRePassword;
    }

    public void setUserRePassword(String userRePassword) {
        this.userRePassword = userRePassword;
    }

    public String getRecommendedNum() {
        return recommendedNum;
    }

    public void setRecommendedNum(String recommendedNum) {
        this.recommendedNum = recommendedNum;
    }

    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 确认密码
     */
    private String userRePassword;
    /**
     * 推荐人编号
     */
    private String recommendedNum;

    /**
     * 安全等级
     */
    private Integer safeScore;

    public Integer getSafeScore() {
        return safeScore;
    }

    public void setSafeScore(Integer safeScore) {
        this.safeScore = safeScore;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}
