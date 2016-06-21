package com.ync365.seed.bussiness.modules.order.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.ync365.seed.bussiness.modules.order.entity.OrderShippingInfo;

public class OrderInfoGoodsBO {
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
	
	private BigDecimal villageExpense;//村站佣金

    private BigDecimal platformExpense;//平台佣金

    private BigDecimal lcExpense;//lc佣金

    private BigDecimal integralExpense;//积分费用

    private BigDecimal planExpense;//众筹费用
    
    private BigDecimal goodsPriceChange;//改价后商品单价	
    
    private BigDecimal villageExpenseChange;//改价后村站佣金
    
    private BigDecimal platformExpenseChange;//改价后平台佣金
    
    private BigDecimal lcExpenseChange;//改价后lc佣金
    
    private BigDecimal integralExpenseChange;//改价后积分费用
    
    private BigDecimal planExpenseChange;//改价后众筹费用    
    
    private String changeNum;
    
    private String changeName;//改价人
    
    private Date changeTime;
    
    private String auditNum;
    
    private String auditName;//审核人
    
    private Date auditTime; 
    //这个对象用于显示订单的配货，出库，发货，的单号以及操作时间等信息
    private OrderShippingInfo orderShippingInfo;  	

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

	public OrderShippingInfo getOrderShippingInfo() {
		return orderShippingInfo;
	}

	public void setOrderShippingInfo(OrderShippingInfo orderShippingInfo) {
		this.orderShippingInfo = orderShippingInfo;
	}

	public String getChangeNum() {
		return changeNum;
	}

	public void setChangeNum(String changeNum) {
		this.changeNum = changeNum;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	public String getAuditNum() {
		return auditNum;
	}

	public void setAuditNum(String auditNum) {
		this.auditNum = auditNum;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public BigDecimal getGoodsPriceChange() {
		return goodsPriceChange;
	}

	public void setGoodsPriceChange(BigDecimal goodsPriceChange) {
		this.goodsPriceChange = goodsPriceChange;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public BigDecimal getVillageExpenseChange() {
		return villageExpenseChange;
	}

	public void setVillageExpenseChange(BigDecimal villageExpenseChange) {
		this.villageExpenseChange = villageExpenseChange;
	}

	public BigDecimal getPlatformExpenseChange() {
		return platformExpenseChange;
	}

	public void setPlatformExpenseChange(BigDecimal platformExpenseChange) {
		this.platformExpenseChange = platformExpenseChange;
	}

	public BigDecimal getLcExpenseChange() {
		return lcExpenseChange;
	}

	public void setLcExpenseChange(BigDecimal lcExpenseChange) {
		this.lcExpenseChange = lcExpenseChange;
	}

	public BigDecimal getIntegralExpenseChange() {
		return integralExpenseChange;
	}

	public void setIntegralExpenseChange(BigDecimal integralExpenseChange) {
		this.integralExpenseChange = integralExpenseChange;
	}

	public BigDecimal getPlanExpenseChange() {
		return planExpenseChange;
	}

	public void setPlanExpenseChange(BigDecimal planExpenseChange) {
		this.planExpenseChange = planExpenseChange;
	}
    
    
}
