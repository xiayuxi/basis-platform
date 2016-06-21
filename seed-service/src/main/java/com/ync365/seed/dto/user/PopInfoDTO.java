package com.ync365.seed.dto.user;

import java.util.Date;

import javax.validation.constraints.Pattern;

public class PopInfoDTO {
    private Integer id;

    private String popNum;
    @Pattern.List({ @Pattern(regexp = "\\D+.*|\\d+\\D+.*", message = "不能全数字"),
            @Pattern(regexp = "(\\w|\\-|[\u4e00-\u9fa5]){4,20}", message = "4-20位字符，支持汉字、字母、数字及“-”、“_”组合") })
    private String popLoginName;
    private String popRealName;
    private String popNickname;

    private Integer popGender;

    private String popIdCard;

    private Date popBirthday;

    private String popPhotoPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPopNum() {
        return popNum;
    }

    public void setPopNum(String popNum) {
        this.popNum = popNum == null ? null : popNum.trim();
    }

    public String getPopRealName() {
        return popRealName;
    }

    public void setPopRealName(String popRealName) {
        this.popRealName = popRealName == null ? null : popRealName.trim();
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
        this.popIdCard = popIdCard == null ? null : popIdCard.trim();
    }

    public Date getPopBirthday() {
        return popBirthday;
    }

    public void setPopBirthday(Date popBirthday) {
        this.popBirthday = popBirthday;
    }

    public String getPopPhotoPath() {
        return popPhotoPath;
    }

    public void setPopPhotoPath(String popPhotoPath) {
        this.popPhotoPath = popPhotoPath == null ? null : popPhotoPath.trim();
    }

    public String getPopNickname() {
        return popNickname;
    }

    public void setPopNickname(String popNickname) {
        this.popNickname = popNickname;
    }

    public String getPopLoginName() {
        return popLoginName;
    }

    public void setPopLoginName(String popLoginName) {
        this.popLoginName = popLoginName;
    }

}