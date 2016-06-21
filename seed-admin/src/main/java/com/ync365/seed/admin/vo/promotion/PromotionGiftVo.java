package com.ync365.seed.admin.vo.promotion;

import java.math.BigDecimal;
import java.util.Date;

public class PromotionGiftVo {

	
	
	/** serialVersionUID **/ 
	private static final long serialVersionUID = 1L;
	
	/********************************满赠特有信息********************************/
	
	/**活动ID  **/ 
	private Integer promotionId;

    /**满赠类型  **/ 
	private Integer giftType;
	
    /**优惠方式  **/ 
	private Integer giftWay;
	
    /**优惠条件  **/ 
	private BigDecimal goodsAmount;
	
    /**赠品数量  **/ 
	private Integer goodsCount;
	
    /**是否删除  **/ 
	private Integer isDelete;
	
	
	/******************************活动共有信息******************************/
	
    /**活动名称  **/ 
	private String promotionName;
	
    /**活动类型  **/ 
	private Integer promotionType;
	
    /**活动描述  **/ 
	private String promotionDesc;
	
    /**活动是否对用户分级  **/ 
	private Integer isAllUser;
	
    /**是否全品  **/ 
	private Integer isAllSku;
	
    /**会员等级  **/ 
	private String memberLevel;
	
    /**活动状态  **/ 
	private Integer status;
	
    /**活动开始时间  **/ 
	private Date startTime;
	
    /**活动结束时间  **/ 
	private Date endTime;
	
    /**活动备注  **/ 
	private String remark;
	
    /**版本  **/ 
	private String version;
	
    /**创建人ID  **/ 
	private String createUserId;
	
    /**创建时间  **/ 
	private Date createTime;
	
    /**修改人ID  **/ 
	private String updateUserId;
	
    /**修改人时间  **/ 
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

	public Integer getGiftType() {
		return giftType;
	}

	public void setGiftType(Integer giftType) {
		this.giftType = giftType;
	}

	public Integer getGiftWay() {
		return giftWay;
	}

	public void setGiftWay(Integer giftWay) {
		this.giftWay = giftWay;
	}

	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
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

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
}
