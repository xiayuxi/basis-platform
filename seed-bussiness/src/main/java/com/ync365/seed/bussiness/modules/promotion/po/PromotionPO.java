package com.ync365.seed.bussiness.modules.promotion.po;

import java.util.Date;

public class PromotionPO {
    
    private Integer promotionId;

    private String promotionName;

    private Integer promotionType;

    private String promotionDesc;

    private Integer isAllUser;

    private Integer isAllSku;

    private String memberLevel;

    private Integer status;

    private Integer isDelete;

    private Date startTime;

    private Date endTime;

    private String remark;

    private String version;

    private String createUserNum;

    private Date createTime;

    private String updateUserNum;

    private Date updateTime;
    
    private String popStoreName;//店铺名称   
    
    public String getPopStoreName() {
        return popStoreName;
    }

    public void setPopStoreName(String popStoreName) {
        this.popStoreName = popStoreName;
    }
    
    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public String getPromotionDesc() {
        return promotionDesc;
    }

    public void setPromotionDesc(String promotionDesc) {
        this.promotionDesc = promotionDesc;
    }

    public Integer getIsAllUser() {
        return isAllUser;
    }

    public void setIsAllUser(Integer isAllUser) {
        this.isAllUser = isAllUser;
    }

    public Integer getIsAllSku() {
        return isAllSku;
    }

    public void setIsAllSku(Integer isAllSku) {
        this.isAllSku = isAllSku;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreateUserNum() {
        return createUserNum;
    }

    public void setCreateUserNum(String createUserNum) {
        this.createUserNum = createUserNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserNum() {
        return updateUserNum;
    }

    public void setUpdateUserNum(String updateUserNum) {
        this.updateUserNum = updateUserNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
}
