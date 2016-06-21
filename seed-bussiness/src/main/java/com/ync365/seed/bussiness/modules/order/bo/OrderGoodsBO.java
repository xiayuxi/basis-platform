package com.ync365.seed.bussiness.modules.order.bo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderGoodsBO {

	private Integer id;
	
	private Integer orderId;
	
	private Integer goodsId;
	
	private Integer skuId;
	
	private Integer storeId;
	
	private String orderNo;//订单号
	
	private Integer orderStatus;//订单状态
	
	private Date createTime;//下单时间
	
	private String goodsCode;
	
	private String goodsName;
	
	private BigDecimal marketPrice;
	
	private BigDecimal goodsPrice;
	
	private BigDecimal actualPrice;
	
	private String goodsUrl;
	
	private Integer goodsCount;
	
	private BigDecimal totalAmount;
	
	private BigDecimal saveAmount;
	
	private BigDecimal actualAmount;
	
	private Integer isChange;
	
	private Integer auditStatus;
	
	private Integer version;
	

    private BigDecimal villageExpense;

    private BigDecimal platformExpense;

    private BigDecimal lcExpense;

    private BigDecimal integralExpense;

    private BigDecimal planExpense;

    private BigDecimal seExpense;

    private BigDecimal aExpense;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}

	public String getGoodsUrl() {
		return goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getSaveAmount() {
		return saveAmount;
	}

	public void setSaveAmount(BigDecimal saveAmount) {
		this.saveAmount = saveAmount;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Integer getIsChange() {
		return isChange;
	}

	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BigDecimal getVillageExpense() {
		return villageExpense;
	}

	public void setVillageExpense(BigDecimal villageExpense) {
		this.villageExpense = villageExpense;
	}

	public BigDecimal getPlatformExpense() {
		return platformExpense;
	}

	public void setPlatformExpense(BigDecimal platformExpense) {
		this.platformExpense = platformExpense;
	}

	public BigDecimal getLcExpense() {
		return lcExpense;
	}

	public void setLcExpense(BigDecimal lcExpense) {
		this.lcExpense = lcExpense;
	}

	public BigDecimal getIntegralExpense() {
		return integralExpense;
	}

	public void setIntegralExpense(BigDecimal integralExpense) {
		this.integralExpense = integralExpense;
	}

	public BigDecimal getPlanExpense() {
		return planExpense;
	}

	public void setPlanExpense(BigDecimal planExpense) {
		this.planExpense = planExpense;
	}

	public BigDecimal getSeExpense() {
		return seExpense;
	}

	public void setSeExpense(BigDecimal seExpense) {
		this.seExpense = seExpense;
	}

	public BigDecimal getaExpense() {
		return aExpense;
	}

	public void setaExpense(BigDecimal aExpense) {
		this.aExpense = aExpense;
	}      
    
    
}
