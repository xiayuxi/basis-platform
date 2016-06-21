package com.ync365.seed.admin.vo.promotion;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReturnDetail;

public class PromotionReturnVo {
	private Integer promotionId;

    private String promotionName;

    private Integer promotionType;   

    private Integer status;

    private Integer isDelete;
    
    private Integer returnType;//优惠方式   
    
    private Date startTime;

    private Date endTime;

    private String remark;

    private String version;

    private String createUserNum;

    private Date createTime;

    private String updateUserNum;   
    
    private Date updateTime;   
    private PromotionReturnDetailVo nomarlReturnVo;
    
    private Integer storeId;//店铺id   
    
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
    private List<PromotionReturnDetailVo> promotionReturnDetalis = new ArrayList<PromotionReturnDetailVo>();
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
	public Integer getReturnType() {
		return returnType;
	}
	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
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
	
	public PromotionReturnDetailVo getNomarlReturnVo() {
		return nomarlReturnVo;
	}
	public void setNomarlReturnVo(PromotionReturnDetailVo nomarlReturnVo) {
		this.nomarlReturnVo = nomarlReturnVo;
	}
	public List<PromotionReturnDetailVo> getPromotionReturnDetalis() {
		return promotionReturnDetalis;
	}
	public void setPromotionReturnDetalis(
			List<PromotionReturnDetailVo> promotionReturnDetalis) {
		this.promotionReturnDetalis = promotionReturnDetalis;
	}
	
}
