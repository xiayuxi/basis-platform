package com.ync365.seed.dto.user;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ync365.seed.service.user.IUserService;

public class SysFarmInfoDTO {
    @NotNull(groups = IUserService.UpdateFarmInfo.class)
    private Integer id;
    @NotNull(groups = IUserService.AddFarmInfo.class)
    @Pattern(regexp = "\\d+", message = "userNum 只能是数字")
    private String userNum;
    @NotNull(groups = IUserService.AddFarmInfo.class)
    private String farmLocation;
    @NotNull(groups = IUserService.AddFarmInfo.class)
    private String growKind;
    @Min(0)
    @NotNull(groups = IUserService.AddFarmInfo.class)
    @Max(Integer.MAX_VALUE)
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