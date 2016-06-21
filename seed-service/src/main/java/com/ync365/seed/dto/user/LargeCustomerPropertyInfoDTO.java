package com.ync365.seed.dto.user;

public class LargeCustomerPropertyInfoDTO {
    private Integer propertyId;
    private Integer valueId;

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    private String propertyName;
    private String propertyHint;
    private String propertyType;

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer integer) {
        this.propertyId = integer;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyHint() {
        return propertyHint;
    }

    public void setPropertyHint(String propertyHint) {
        this.propertyHint = propertyHint;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    private String propertyValue;
}
