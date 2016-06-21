package com.ync365.seed.bussiness.modules.goods.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ync365.seed.bussiness.modules.goods.entity.Sku;
import com.ync365.seed.bussiness.modules.goods.entity.SkuFeature;

public class GoodsBO {
    /**
     * 主键
     */
    private Integer goodsId;

    /**
     * 商品名称 
     */
    private String name;
    
    /***
     * 配肥站编号
     */
    private String fertilizerStation;
    /**
     * 商品编码
     */
    private String code;

    /**
     * 商品备注
     */
    private String remark;

    /**
     * 商品图片
     */
    private String tagImgId;
    
    private BigDecimal price;

    /**
     * 商品价格 平台价 不映射
     */
    private BigDecimal costPrice;

    /**
     * 是否最新
     */
    private int isNew;

    private int isGood;

    private int isHot;

    /**
     * 关键字
     */
    private String keywords;
    
    /**
     * 商品是否删除  已删除1 正常0
     * */
    private int isDel;

    private Date createTime;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 分类Id
     */
    private Integer categoryId;
    
    /**
     * 店铺编号
     */
    private Integer storeId ;
    
    /**
     * 店铺名称
     * */
    private String popStoreName;

    private String optUser;

    private Date optDate;

    private String optTerm;
    
    /**
     * 1:通过  2：未通过  3：上架  4：下架   0：默认未审核
     */
    private Integer status;

    /**
     * 销售数量 
     */
    private int salesCount;

    /**
     * 图片ID
     */
    private String uuid;

    /**
     * 不在实体映射
     */
    private List<Sku> skus;

    /**
     * 不在实体映射
     */
    private List<SkuFeature> skuFeatures;

    /**
     * 商品副标题
     */
    private String subtitle;

    /**
     * 是否虚拟商品（1：是，0：否）
     */
    private int isVirtual;

    /**
     * 是否在综合专区显示（1：是，0：否）
     */
    private int isSupplyArea;

    /**
     * 是否海外购（1：是  0：否）
     */
    private int isOverseas;

    /**
     * 送达天数
     */
    private String sendTime;
    
    /*
     * 审核页面预览需要拼字符串用
     */
    private Integer preView;
    

    private String unit;
    
    /**
     * 市场价格 【数据库库里没有字段对应，前台显示用】
     */
    private BigDecimal marketPrice ;
    
    private String brandName;
    
    private BigDecimal minPrice;
    
    private BigDecimal minStockNum;
    
    //最低下限
    private BigDecimal minLowerLimit;
    
    private String releaseUserId;
    
    private String adminName;
    
    private Date releaseTime;

    private Integer goodsType;
    
    private String storeName;
    
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTagImgId() {
        return tagImgId;
    }

    public void setTagImgId(String tagImgId) {
        this.tagImgId = tagImgId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public String getOptTerm() {
        return optTerm;
    }

    public void setOptTerm(String optTerm) {
        this.optTerm = optTerm;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    public List<SkuFeature> getSkuFeatures() {
        return skuFeatures;
    }

    public void setSkuFeatures(List<SkuFeature> skuFeatures) {
        this.skuFeatures = skuFeatures;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(int isVirtual) {
        this.isVirtual = isVirtual;
    }

    public int getIsSupplyArea() {
        return isSupplyArea;
    }

    public void setIsSupplyArea(int isSupplyArea) {
        this.isSupplyArea = isSupplyArea;
    }

    public int getIsOverseas() {
        return isOverseas;
    }

    public void setIsOverseas(int isOverseas) {
        this.isOverseas = isOverseas;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getPreView() {
        return preView;
    }

    public void setPreView(Integer preView) {
        this.preView = preView;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMinStockNum() {
        return minStockNum;
    }

    public void setMinStockNum(BigDecimal minStockNum) {
        this.minStockNum = minStockNum;
    }

    public String getReleaseUserId() {
        return releaseUserId;
    }

    public void setReleaseUserId(String releaseUserId) {
        this.releaseUserId = releaseUserId;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getFertilizerStation() {
        return fertilizerStation;
    }

    public void setFertilizerStation(String fertilizerStation) {
        this.fertilizerStation = fertilizerStation;
    }

    public BigDecimal getMinLowerLimit() {
        return minLowerLimit;
    }

    public void setMinLowerLimit(BigDecimal minLowerLimit) {
        this.minLowerLimit = minLowerLimit;
    }

	public String getPopStoreName() {
		return popStoreName;
	}

	public void setPopStoreName(String popStoreName) {
		this.popStoreName = popStoreName;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
}
