package com.ync365.seed.admin.vo.promotion;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PromotionSeckillVo {
	private Integer promotionId;

    private Integer seckillType;

    private Integer limitPerson;

    private Integer limitPerCount;

    private Integer limitTotalCount;

    private Integer currentCount;

    private Integer isDelete;
    
    private String promotionName;

	private Integer promotionType;

	private String promotionDesc;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	private String remark;
	
	private Integer status;

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

	public Integer getSeckillType() {
		return seckillType;
	}

	public void setSeckillType(Integer seckillType) {
		this.seckillType = seckillType;
	}

	public Integer getLimitPerson() {
		return limitPerson;
	}

	public void setLimitPerson(Integer limitPerson) {
		this.limitPerson = limitPerson;
	}

	public Integer getLimitPerCount() {
		return limitPerCount;
	}

	public void setLimitPerCount(Integer limitPerCount) {
		this.limitPerCount = limitPerCount;
	}

	public Integer getLimitTotalCount() {
		return limitTotalCount;
	}

	public void setLimitTotalCount(Integer limitTotalCount) {
		this.limitTotalCount = limitTotalCount;
	}

	public Integer getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
