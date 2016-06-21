package com.ync365.seed.bussiness.modules.user.entity;

import java.util.Date;

public class SysUserBrowse {
    private Integer id;

    private String userNum;

    private String skuId;

    private Date browseDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public Date getBrowseDate() {
        return browseDate;
    }

    public void setBrowseDate(Date browseDate) {
        this.browseDate = browseDate;
    }
}