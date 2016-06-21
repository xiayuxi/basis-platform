package com.ync365.seed.dto.user;

import javax.validation.constraints.Pattern;

public class PopRegisterDTO {

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

    public Integer getSafeScore() {
        return safeScore;
    }

    public void setSafeScore(Integer safeScore) {
        this.safeScore = safeScore;
    }

    @Pattern.List({ @Pattern(regexp = "\\D+.*|\\d+\\D+.*", message = "不能全数字"),
            @Pattern(regexp = "(\\w|\\-|[\u4e00-\u9fa5]){4,20}", message = "4-20位字符，支持汉字、字母、数字及“-”、“_”组合") })
    private String popLoginName;

    private String popPassword;

    private String popMobile;

    private Integer safeScore;

}
