package com.ync365.seed.bussiness.modules.goods.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Sku {
	
    private Integer skuId;

    /**
     * 商品主键
     */
    private Integer goodsId;
 
    /**
     * 商品货号
     */
    private String sn;
    
    /**
     *库存(goods_stocks表)
     *(sku表不存)
     * 
     */
    private Integer stockNum;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 售价
     */
    private BigDecimal sellPrice;
    
    /**
     * 平台价
     */
    private BigDecimal costPrice ;

    /**
     * 下限
     */
    private BigDecimal lowerLimit;

    /**
     * 上限
     */
    private BigDecimal upperLimit;

    /**
     * 商品名称 
     */
    private String goodsName;

    private int ftype;

    private Integer sort;

    /**
     * 销售数量 
     */
    private Long salesCount;
    
    private int isGood;
    
    private int isHot;
   
    /**
     * 重量
     */
    private Integer weight;

    /**
     * 1:通过  2：未通过  3：上架  4：下架   0：默认未审核
     */
    private int status;

    /**
     * 商品条码
     */
    private String barCode;

    /**
     * 商品编码
     */
    private String goodsCode;

    private Date createTime;

    private Date optDate;

    private Integer optUser;

    private String optTerm;
    
    private String fullName;
    
    /**
     * 删除标识符  1删除 0未删除
     */
    private Integer isDelete;
    
    /**
     * 实体不映射
     */
    private List<SkuFeature> skuFeatures;
    
    /**
     * 店铺Id
     */
    private Integer storeId;
 
    /**
     * 访问数量
     */
    private Integer visitCount ;
    
    /**
     * 商品副标题
     */
    private String subtitle;
    
    /**------------------不映射数据库字段-----------------*/
    /**
     * 属性值[实体不映射]
     */
    private String attrValue;
    
    /**
     * sku对应的佣金
     * @return
     */
    private HoldGold holdGold;
       
    /**
     * sku对应的多个区域的id数组
     * @Title: getRegion
     * @Description: TODO    ：    
     * @author: guanfl    
     * @date: 2015年9月29日 上午11:29:15       
     * @version: 
     *
     * @return
     *
     */
    
    private List<Integer> regionId;
    
    /**
     * sku对应的不同区域的多个价格prices数组
     */
    private List<BigDecimal> prices;
   
    public List<Integer> getRegionId() {
        return regionId;
    }
    public void setRegionId(List<Integer> regionId) {
        this.regionId = regionId;
    }
    public List<BigDecimal> getPrices() {
        return prices;
    }
    public void setPrices(List<BigDecimal> prices) {
        this.prices = prices;
    }
    public HoldGold getHoldGold() {
		return holdGold;
	}
	public void setHoldGold(HoldGold holdGold) {
		this.holdGold = holdGold;
	}
	public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public List<SkuFeature> getSkuFeatures() {
		return skuFeatures;
	}
	public void setSkuFeatures(List<SkuFeature> skuFeatures) {
		this.skuFeatures = skuFeatures;
	}
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
 
	
	
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	public BigDecimal getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(BigDecimal lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public BigDecimal getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getFtype() {
		return ftype;
	}
	public void setFtype(int ftype) {
		this.ftype = ftype;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	 
	public Long getSalesCount() {
		return salesCount;
	}
	public void setSalesCount(Long salesCount) {
		this.salesCount = salesCount;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getOptDate() {
		return optDate;
	}
	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}
	public Integer getOptUser() {
		return optUser;
	}
	public void setOptUser(Integer optUser) {
		this.optUser = optUser;
	}
	public String getOptTerm() {
		return optTerm;
	}
	public void setOptTerm(String optTerm) {
		this.optTerm = optTerm;
	}
	public int getIsGood() {
		return isGood;
	}
	public void setIsGood(int isGood) {
		this.isGood = isGood;
	}
	public int getIsHot() {
		return isHot;
	}
	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public Integer getStockNum() {
		return stockNum;
	}
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}
}