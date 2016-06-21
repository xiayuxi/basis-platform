package com.ync365.seed.bussiness.modules.user.bo;

public class UserRegisterBO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String userMobile;
    /**
     * US/VS/LC
     */
    private String userRole;

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
     * 推荐人编号
     */
    private String recommendedNum;
    /**
     * 所在地
     */
    private Integer registerLocation;
    /**
     * 安全级别
     */
    private Integer safeScore;

    public Integer getRegisterLocation() {
        return registerLocation;
    }

    public void setRegisterLocation(Integer registerLocation) {
        this.registerLocation = registerLocation;
    }

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
