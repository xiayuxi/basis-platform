package com.ync365.seed.bussiness.modules.user.entity;

public class SysLargeCustomerPropertyInfo {
    private Integer id;

    private String propertyName;

    private String propertyType;

    private String propertyHint;

    private Boolean isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType == null ? null : propertyType.trim();
    }

    public String getPropertyHint() {
        return propertyHint;
    }

    public void setPropertyHint(String propertyHint) {
        this.propertyHint = propertyHint == null ? null : propertyHint.trim();
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}