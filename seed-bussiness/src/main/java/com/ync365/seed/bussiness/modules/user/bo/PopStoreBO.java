package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;
import java.util.List;

/**
 * @author liukai
 *
 */
public class PopStoreBO {
    /**
     * 主键id 自增
     */
    private Integer id;
	
	/**
     * 店铺名称
     */
    private String popStoreName;

    /**
     * 供应商编号
     */
    private String popStoreNum;
    
    /**
     * 联系人
     */
    private String popStoreContact;
    
    /**
     * 联系电话 
     */
    private String popStoreMobile;
    
    /**
     * 负责人
     */
    private String popStorePrincipal;
    
    /**
     * 负责人电话
     */
    private String popStorePrincipalMobile;
    
    /**
     * 负责人编号
     */
    private String popStorePrincipaNum;
    
    /**
     * 供应商类型（DS/SP/FS）
     */
    private String popStoreType;
    
    /**
     * 激活状态
     */
    private Boolean isFrozen;

    /**
     * 注册时间
     */
    private Date createTime;
    
    /**
     * 地址
     */
    private String popStoreAddress;
    
    /**
     * 详细地址
     */
    private String popStoreAddressDetail;
    
    /**
     * 装修状态
     */
    private Integer popStoreDecorate;
    
    
    /**
     * 商城域名
     */
    private String popStoreDomain;
    
    /**
     * 供应商帐号
     */
    private String popLoginName;
    
    /**
     * 售后服务信息
     */
    private String popAfterService;

    /**
     * 品牌优势
     */
    private String popBrandAdvantage;
    
    /**
     * ssinfo 与  popstore 关系 主键id
     */
    private Integer ssPopStoreRelationshipId;

    /**
     * 品牌ids
     */
    private List<String> brands;
    
    /**
     * 品类ids
     */
    private List<String> categorys;

    /**
     * 图片路径
     */
    private String logoPath;
    

    /**
     * 是否删除
     */
    private Boolean isDel;

    /**
     * 是否审核
     */
    private Boolean isAuthentication;

    
	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}


	public Boolean getIsAuthentication() {
		return isAuthentication;
	}


	public void setIsAuthentication(Boolean isAuthentication) {
		this.isAuthentication = isAuthentication;
	}


	public String getPopStoreName() {
		return popStoreName;
	}


	public void setPopStoreName(String popStoreName) {
		this.popStoreName = popStoreName;
	}
	
	

	public String getLogoPath() {
		return logoPath;
	}


	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}


	public String getPopStoreNum() {
		return popStoreNum;
	}


	public void setPopStoreNum(String popStoreNum) {
		this.popStoreNum = popStoreNum;
	}


	public String getPopStorePrincipal() {
		return popStorePrincipal;
	}


	public void setPopStorePrincipal(String popStorePrincipal) {
		this.popStorePrincipal = popStorePrincipal;
	}


	public String getPopStoreType() {
		return popStoreType;
	}


	public void setPopStoreType(String popStoreType) {
		this.popStoreType = popStoreType;
	}


	public Boolean getIsFrozen() {
		return isFrozen;
	}


	public void setIsFrozen(Boolean isFrozen) {
		this.isFrozen = isFrozen;
	}

	public String getPopStoreMobile() {
		return popStoreMobile;
	}


	public void setPopStoreMobile(String popStoreMobile) {
		this.popStoreMobile = popStoreMobile;
	}


	public String getPopStoreContact() {
		return popStoreContact;
	}

	public void setPopStoreContact(String popStoreContact) {
		this.popStoreContact = popStoreContact;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public Integer getPopStoreDecorate() {
		return popStoreDecorate;
	}


	public void setPopStoreDecorate(Integer popStoreDecorate) {
		this.popStoreDecorate = popStoreDecorate;
	}


	public String getPopStoreDomain() {
		return popStoreDomain;
	}


	public void setPopStoreDomain(String popStoreDomain) {
		this.popStoreDomain = popStoreDomain;
	}


	public String getPopStorePrincipalMobile() {
		return popStorePrincipalMobile;
	}


	public void setPopStorePrincipalMobile(String popStorePrincipalMobile) {
		this.popStorePrincipalMobile = popStorePrincipalMobile;
	}


	public String getPopLoginName() {
		return popLoginName;
	}


	public void setPopLoginName(String popLoginName) {
		this.popLoginName = popLoginName;
	}


	public String getPopStoreAddress() {
		return popStoreAddress;
	}


	public void setPopStoreAddress(String popStoreAddress) {
		this.popStoreAddress = popStoreAddress;
	}


    public Integer getSsPopStoreRelationshipId() {
        return ssPopStoreRelationshipId;
    }

    public void setSsPopStoreRelationshipId(Integer ssPopStoreRelationshipId) {
        this.ssPopStoreRelationshipId = ssPopStoreRelationshipId;
    }


	public String getPopStorePrincipaNum() {
		return popStorePrincipaNum;
	}

	public void setPopStorePrincipaNum(String popStorePrincipaNum) {
		this.popStorePrincipaNum = popStorePrincipaNum;
	}


	public String getPopAfterService() {
		return popAfterService;
	}


	public void setPopAfterService(String popAfterService) {
		this.popAfterService = popAfterService;
	}


	public String getPopBrandAdvantage() {
		return popBrandAdvantage;
	}


	public void setPopBrandAdvantage(String popBrandAdvantage) {
		this.popBrandAdvantage = popBrandAdvantage;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public List<String> getBrands() {
		return brands;
	}


	public void setBrands(List<String> brands) {
		this.brands = brands;
	}


	public List<String> getCategorys() {
		return categorys;
	}


	public void setCategorys(List<String> categorys) {
		this.categorys = categorys;
	}


	public String getPopStoreAddressDetail() {
		return popStoreAddressDetail;
	}


	public void setPopStoreAddressDetail(String popStoreAddressDetail) {
		this.popStoreAddressDetail = popStoreAddressDetail;
	}


	public Boolean getIsDel() {
		return isDel;
	}

	
}
