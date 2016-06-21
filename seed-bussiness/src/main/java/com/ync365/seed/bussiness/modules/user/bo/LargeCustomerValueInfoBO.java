package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;

/**
 * 认证信息 
 * @author leixc
 *
 */
public class LargeCustomerValueInfoBO {
    
    /**
     * 认证名称
     */
    private String propertyName;

    /**
     * 认证类型  text 文本  pic 图片  time  时间
     */
    private String propertyType;

    /**
     * 认证描述
     */
    private String propertyHint;
    
    /**
     * 认证值
     */
    private String propertyValue;
    
    /**
     * 认证值为时间时   认证时间开始
     */
    private String propertyDateBegin;
    
    /**
     * 认证值为时间时  认证时间 结束
     */
    private String propertyDateEnd;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
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

    public String getPropertyDateBegin() {
        return propertyDateBegin;
    }

    public void setPropertyDateBegin(String propertyDateBegin) {
        this.propertyDateBegin = propertyDateBegin;
    }

    public String getPropertyDateEnd() {
        return propertyDateEnd;
    }

    public void setPropertyDateEnd(String propertyDateEnd) {
        this.propertyDateEnd = propertyDateEnd;
    }

   
    
}