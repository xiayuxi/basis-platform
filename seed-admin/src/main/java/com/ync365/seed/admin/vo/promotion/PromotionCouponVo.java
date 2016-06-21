package com.ync365.seed.admin.vo.promotion;

import java.math.BigDecimal;
import java.util.Date;

public class PromotionCouponVo {
	private Integer couponId;
	private String promotionName;

	private Integer promotionType;

    private BigDecimal couponAmount;
    
    private Date startTime;//红包使用时间
    
    private Date endTime;//红包使用时间
    
    private Date validStart;

    private Date validEnd;

    private Integer promotionId;

    private Integer couponUsedCount;

    private Integer isDelete;

    private Integer status;   

    private Integer couponChannel;

    private Integer couponType;  

    private Integer couponCount;   

    private Date grantStartTime;//红包发行时间

    private Date grantEndTime;//红包发行时间

    private Long limitedAmount; 

    private BigDecimal couponAllAmount;

    private BigDecimal couponUsedAmount;
    private String remark;
    private Integer storeId;//店铺id   
    
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public Date getValidStart() {
		return validStart;
	}

	public void setValidStart(Date validStart) {
		this.validStart = validStart;
	}

	public Date getValidEnd() {
		return validEnd;
	}

	public void setValidEnd(Date validEnd) {
		this.validEnd = validEnd;
	}

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public Integer getCouponUsedCount() {
		return couponUsedCount;
	}

	public void setCouponUsedCount(Integer couponUsedCount) {
		this.couponUsedCount = couponUsedCount;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCouponChannel() {
		return couponChannel;
	}

	public void setCouponChannel(Integer couponChannel) {
		this.couponChannel = couponChannel;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public Date getGrantStartTime() {
		return grantStartTime;
	}

	public void setGrantStartTime(Date grantStartTime) {
		this.grantStartTime = grantStartTime;
	}

	public Date getGrantEndTime() {
		return grantEndTime;
	}

	public void setGrantEndTime(Date grantEndTime) {
		this.grantEndTime = grantEndTime;
	}

	public Long getLimitedAmount() {
		return limitedAmount;
	}

	public void setLimitedAmount(Long limitedAmount) {
		this.limitedAmount = limitedAmount;
	}

	public BigDecimal getCouponAllAmount() {
		return couponAllAmount;
	}

	public void setCouponAllAmount(BigDecimal couponAllAmount) {
		this.couponAllAmount = couponAllAmount;
	}

	public BigDecimal getCouponUsedAmount() {
		return couponUsedAmount;
	}

	public void setCouponUsedAmount(BigDecimal couponUsedAmount) {
		this.couponUsedAmount = couponUsedAmount;
	}
    
    
}
