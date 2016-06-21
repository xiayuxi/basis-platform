package com.ync365.seed.bussiness.modules.user.entity;

public class SysUserRecommended {
    private String userNum;

    private String recommendedNum;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public String getRecommendedNum() {
        return recommendedNum;
    }

    public void setRecommendedNum(String recommendedNum) {
        this.recommendedNum = recommendedNum == null ? null : recommendedNum.trim();
    }
}