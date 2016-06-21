package com.ync365.seed.dto.user;

import javax.validation.constraints.Pattern;

public class AdminRegisterDTO {
    /**
     * 登录名
     */
    @Pattern.List({@Pattern(regexp="\\D+.*|\\d+\\D+.*",message="不能全数字"),@Pattern(regexp="(\\w|\\-|[\u4e00-\u9fa5]){4,20}",message="4-20位字符，支持汉字、字母、数字及“-”、“_”组合")})
    private String adminLoginName;

    /**
     * 用户密码
     */
    private String adminPassword;
    /**
     * 确认密码
     */
    private String adminRePassword;

    public String getAdminRePassword() {
        return adminRePassword;
    }

    public void setAdminRePassword(String adminRePassword) {
        this.adminRePassword = adminRePassword;
    }

    public String getMobileValidateCode() {
        return mobileValidateCode;
    }

    public void setMobileValidateCode(String mobileValidateCode) {
        this.mobileValidateCode = mobileValidateCode;
    }

    /**
     * 手机号
     */
    private String adminMobile;
    /**
     * 手机验证码
     */
    private String mobileValidateCode;

    public String getAdminLoginName() {
        return adminLoginName;
    }

    public void setAdminLoginName(String adminLoginName) {
        this.adminLoginName = adminLoginName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminMobile() {
        return adminMobile;
    }

    public void setAdminMobile(String adminMobile) {
        this.adminMobile = adminMobile;
    }

}
