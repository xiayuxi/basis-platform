package com.ync365.seed.bussiness.modules.user.entity;

public class SysFarmInfo {
    private Integer id;

    private String userNum;

    private String farmLocation;

    private String growKind;

    private Integer farmArea;

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

    public String getFarmLocation() {
        return farmLocation;
    }

    public void setFarmLocation(String farmLocation) {
        this.farmLocation = farmLocation == null ? null : farmLocation.trim();
    }

    public String getGrowKind() {
        return growKind;
    }

    public void setGrowKind(String growKind) {
        this.growKind = growKind == null ? null : growKind.trim();
    }

    public Integer getFarmArea() {
        return farmArea;
    }

    public void setFarmArea(Integer farmArea) {
        this.farmArea = farmArea;
    }
}