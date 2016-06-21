package com.ync365.seed.bussiness.modules.promotion.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Promotion {
	private Integer promotionId;

	private String promotionName;

	private Integer promotionType;

	private String promotionDesc;

	private Integer isAllUser;

	private Integer isAllSku;

	private String memberLevel;

	private Integer status;

	private Integer isDelete;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	private String remark;

	private String version;

	private String createUserNum;

	private Date createTime;

	private String updateUserNum;

	private Date updateTime;
	
	private Integer storeId;//店铺id
	
	
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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
		this.promotionName = promotionName == null ? null : promotionName.trim();
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
		this.promotionDesc = promotionDesc == null ? null : promotionDesc.trim();
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
		this.memberLevel = memberLevel == null ? null : memberLevel.trim();
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
		this.remark = remark == null ? null : remark.trim();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
	}

	public String getCreateUserNum() {
		return createUserNum;
	}

	public void setCreateUserNum(String createUserNum) {
		this.createUserNum = createUserNum == null ? null : createUserNum.trim();
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
		this.updateUserNum = updateUserNum == null ? null : updateUserNum.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}