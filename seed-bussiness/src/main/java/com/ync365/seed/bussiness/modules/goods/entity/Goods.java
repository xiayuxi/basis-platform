
package com.ync365.seed.bussiness.modules.goods.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Goods {

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
    private Integer isNew;

    private Integer isGood;

    private Integer isHot;

    /**
     * 关键字
     */
    private String keywords;
    
    /**
     * 商品是否删除  已删除1 正常0
     * */
    private Integer isDel;

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
    private Integer salesCount;

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
     * 是否虚拟商品（1：是，2：否）
     */
    private Integer isVirtual;

    /**
     * 是否在综合专区显示（1：是，2：否）
     */
    private Integer isSupplyArea;

    /**
     * 是否海外购（1：是  0：否）
     */
    private Integer isOverseas;

    /**
     * 送达天数
     */
    private String sendTime;
    
    private String unit;
    
    /**
     * 商品类型   1:入驻商 2 直营 3海外购 4测土培肥
     */
    private Integer goodsType;

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

    public String getFertilizerStation() {
        return fertilizerStation;
    }

    public void setFertilizerStation(String fertilizerStation) {
        this.fertilizerStation = fertilizerStation;
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

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsGood() {
        return isGood;
    }

    public void setIsGood(Integer isGood) {
        this.isGood = isGood;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
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

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
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

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Integer getIsSupplyArea() {
        return isSupplyArea;
    }

    public void setIsSupplyArea(Integer isSupplyArea) {
        this.isSupplyArea = isSupplyArea;
    }

    public Integer getIsOverseas() {
        return isOverseas;
    }

    public void setIsOverseas(Integer isOverseas) {
        this.isOverseas = isOverseas;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
    
    
}