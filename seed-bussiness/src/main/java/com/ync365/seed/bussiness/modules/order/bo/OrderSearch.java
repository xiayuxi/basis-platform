package com.ync365.seed.bussiness.modules.order.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ync365.seed.bussiness.modules.common.po.PageFilter;

public class OrderSearch extends PageFilter{	

	private Integer id;
	
	private Integer orderSubId;//子订单id

    private String orderNo;
    
    private String orderSubNo;//子订单号

    private BigDecimal totalAmount;//订单总金额

    private BigDecimal couponAmount;//红包总金额

    private BigDecimal saveAmount;//优惠总金额

    private BigDecimal actualAmount;//实际支付金额
    
    private Integer orderGoodsCount;//订单中商品的总个数

    private Date createTime;

    private Integer orderStatus;

    private Integer orderSource;

    private Integer payStatus;

    private Integer hasSub;

    private Integer payType;

    private Integer payWay;

    private Date payTime;

    private Date signinTime;

    private Integer isDel;

    private Date delTime;

    private Integer isRecDel;

    private Date recDelTime;

    private Integer version;
    
    private Integer goodsId;
   
	/**以下属性是和订单联系人相关的 */
    private String usName;
    private String vsName;
    private String lcName;
    private String usTel;
    private String vsTel;
    private String lcTel;   
    private String usAddress;   
    private String vsAddress;   
    private String vsAddressCode;   
    
    private String usNum;
    private String vsNum;
    private String lcNum;

    private String engineerNum;//服务工程师的编号
    private String engineerName;//服务工程师的名字
    private String engineerPhone;//服务工程师的手机号
    
    private String username;//下单人姓名
    private String userTel;//下单手机号
    
    private Integer storeId;//店铺id
    private Integer orderType;//订单类型
    //三种用户的编号集合(如果需要模糊查询则需要这三个属性)
    private List<String> usNumList;
    private List<String> vsNumList;
    private List<String> lcNumList;     
    
    
	
	public Integer getOrderSubId() {
		return orderSubId;
	}
	public void setOrderSubId(Integer orderSubId) {
		this.orderSubId = orderSubId;
	}
	public String getOrderSubNo() {
		return orderSubNo;
	}
	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public Integer getOrderGoodsCount() {
		return orderGoodsCount;
	}
	public void setOrderGoodsCount(Integer orderGoodsCount) {
		this.orderGoodsCount = orderGoodsCount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public Integer getHasSub() {
		return hasSub;
	}
	public void setHasSub(Integer hasSub) {
		this.hasSub = hasSub;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getPayWay() {
		return payWay;
	}
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getSigninTime() {
		return signinTime;
	}
	public void setSigninTime(Date signinTime) {
		this.signinTime = signinTime;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Date getDelTime() {
		return delTime;
	}
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	public Integer getIsRecDel() {
		return isRecDel;
	}
	public void setIsRecDel(Integer isRecDel) {
		this.isRecDel = isRecDel;
	}
	public Date getRecDelTime() {
		return recDelTime;
	}
	public void setRecDelTime(Date recDelTime) {
		this.recDelTime = recDelTime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getUsName() {
		return usName;
	}
	public void setUsName(String usName) {
		this.usName = usName;
	}
	public String getVsName() {
		return vsName;
	}
	public void setVsName(String vsName) {
		this.vsName = vsName;
	}
	public String getLcName() {
		return lcName;
	}
	public void setLcName(String lcName) {
		this.lcName = lcName;
	}
	public String getUsTel() {
		return usTel;
	}
	public void setUsTel(String usTel) {
		this.usTel = usTel;
	}
	public String getVsTel() {
		return vsTel;
	}
	public void setVsTel(String vsTel) {
		this.vsTel = vsTel;
	}
	public String getLcTel() {
		return lcTel;
	}
	public void setLcTel(String lcTel) {
		this.lcTel = lcTel;
	}
	public String getUsAddress() {
		return usAddress;
	}
	public void setUsAddress(String usAddress) {
		this.usAddress = usAddress;
	}
	public String getVsAddress() {
		return vsAddress;
	}
	public void setVsAddress(String vsAddress) {
		this.vsAddress = vsAddress;
	}
	public String getVsAddressCode() {
		return vsAddressCode;
	}
	public void setVsAddressCode(String vsAddressCode) {
		this.vsAddressCode = vsAddressCode;
	}
	public String getUsNum() {
		return usNum;
	}
	public void setUsNum(String usNum) {
		this.usNum = usNum;
	}
	public String getVsNum() {
		return vsNum;
	}
	public void setVsNum(String vsNum) {
		this.vsNum = vsNum;
	}
	public String getLcNum() {
		return lcNum;
	}
	public void setLcNum(String lcNum) {
		this.lcNum = lcNum;
	}
	public String getEngineerNum() {
		return engineerNum;
	}
	public void setEngineerNum(String engineerNum) {
		this.engineerNum = engineerNum;
	}
	public String getEngineerName() {
		return engineerName;
	}
	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	
	public String getEngineerPhone() {
		return engineerPhone;
	}
	public void setEngineerPhone(String engineerPhone) {
		this.engineerPhone = engineerPhone;
	}	
	public List<String> getUsNumList() {
		return usNumList;
	}
	public void setUsNumList(List<String> usNumList) {
		this.usNumList = usNumList;
	}
	public List<String> getVsNumList() {
		return vsNumList;
	}
	public void setVsNumList(List<String> vsNumList) {
		this.vsNumList = vsNumList;
	}
	public List<String> getLcNumList() {
		return lcNumList;
	}
	public void setLcNumList(List<String> lcNumList) {
		this.lcNumList = lcNumList;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}   
	
}
