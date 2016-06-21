package com.ync365.seed.admin.vo.promotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromotionGroupbuyVo {
	private Integer promotionId;

    private String promotionName;

    private Integer promotionType;

    private String promotionDesc;    

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
    
    private Integer groupbuyType;

    private BigDecimal groupbuyMoney;

    private BigDecimal currentMoney;

    private Integer groupNumber;

    private Integer currentNumber;

    private Integer limitPerCount;
    private List<PromotionGroupbuyRegVo> promotionGroupbuyRegList = new ArrayList<PromotionGroupbuyRegVo>();
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
	public Integer getGroupbuyType() {
		return groupbuyType;
	}
	public void setGroupbuyType(Integer groupbuyType) {
		this.groupbuyType = groupbuyType;
	}
	public BigDecimal getGroupbuyMoney() {
		return groupbuyMoney;
	}
	public void setGroupbuyMoney(BigDecimal groupbuyMoney) {
		this.groupbuyMoney = groupbuyMoney;
	}
	public BigDecimal getCurrentMoney() {
		return currentMoney;
	}
	public void setCurrentMoney(BigDecimal currentMoney) {
		this.currentMoney = currentMoney;
	}
	public Integer getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(Integer groupNumber) {
		this.groupNumber = groupNumber;
	}
	public Integer getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}
	public Integer getLimitPerCount() {
		return limitPerCount;
	}
	public void setLimitPerCount(Integer limitPerCount) {
		this.limitPerCount = limitPerCount;
	}
	public List<PromotionGroupbuyRegVo> getPromotionGroupbuyRegList() {
		return promotionGroupbuyRegList;
	}
	public void setPromotionGroupbuyRegList(
			List<PromotionGroupbuyRegVo> promotionGroupbuyRegList) {
		this.promotionGroupbuyRegList = promotionGroupbuyRegList;
	}
    
    
}
