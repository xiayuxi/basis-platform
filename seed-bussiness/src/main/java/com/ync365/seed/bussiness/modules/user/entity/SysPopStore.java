package com.ync365.seed.bussiness.modules.user.entity;

import java.util.Date;

public class SysPopStore {
	/**
	 * 主键id 自增
	 */
    private Integer id;

    /**
     * 供应商编号
     */
    private String popStoreNum;

    /**
     * 店铺名称
     */
    private String popStoreName;

    /**
     * 店铺联系人
     */
    private String popStoreContact;

    /**
     * 联系电话
     */
    private String popStoreMobile;

    /**
     * 入住商类型（DS/SP/FS）
     */
    private String popStoreType;

    /**
     * 地址
     */
    private String popStoreAddress;
    
    /**
     * 详细地址
     */
    private String popStoreAddressDetail;
    
    /**
     * 店铺域名
     */
    private String popStoreDomain;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除
     */
    private Boolean isDel;

    /**
     * 是否冻结
     */
    private Boolean isFrozen;

    /**
     * 是否审核
     */
    private Boolean isAuthentication;

    /**
     * 装修状态
     */
    private Integer popStoreDecorate; 
    
    /**
     * 品牌ID
     */
    private Integer[] popStoreBrandIds;
    
    /**
     * 分类ID
     */
    private Integer[] popStoreCategoryIds;
    
    /**
     * logo地址
     */
    private String logoPath;
    
    public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPopStoreNum() {
        return popStoreNum;
    }

    public void setPopStoreNum(String popStoreNum) {
        this.popStoreNum = popStoreNum == null ? null : popStoreNum.trim();
    }

    public String getPopStoreName() {
        return popStoreName;
    }

    public void setPopStoreName(String popStoreName) {
        this.popStoreName = popStoreName == null ? null : popStoreName.trim();
    }

    public String getPopStoreContact() {
        return popStoreContact;
    }

    public void setPopStoreContact(String popStoreContact) {
        this.popStoreContact = popStoreContact == null ? null : popStoreContact.trim();
    }

    public String getPopStoreMobile() {
        return popStoreMobile;
    }

    public void setPopStoreMobile(String popStoreMobile) {
        this.popStoreMobile = popStoreMobile == null ? null : popStoreMobile.trim();
    }

    public String getPopStoreType() {
        return popStoreType;
    }

    public void setPopStoreType(String popStoreType) {
        this.popStoreType = popStoreType == null ? null : popStoreType.trim();
    }

    public String getPopStoreDomain() {
        return popStoreDomain;
    }

    public void setPopStoreDomain(String popStoreDomain) {
        this.popStoreDomain = popStoreDomain == null ? null : popStoreDomain.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public Boolean getIsAuthentication() {
        return isAuthentication;
    }

    public void setIsAuthentication(Boolean isAuthentication) {
        this.isAuthentication = isAuthentication;
    }

	public Integer getPopStoreDecorate() {
		return popStoreDecorate;
	}

	public void setPopStoreDecorate(Integer popStoreDecorate) {
		this.popStoreDecorate = popStoreDecorate;
	}

	public Integer[] getPopStoreBrandIds() {
		return popStoreBrandIds;
	}

	public void setPopStoreBrandIds(Integer[] popStoreBrandIds) {
		this.popStoreBrandIds = popStoreBrandIds;
	}

	public Integer[] getPopStoreCategoryIds() {
		return popStoreCategoryIds;
	}

	public void setPopStoreCategoryIds(Integer[] popStoreCategoryIds) {
		this.popStoreCategoryIds = popStoreCategoryIds;
	}

	public String getPopStoreAddress() {
		return popStoreAddress;
	}

	public void setPopStoreAddress(String popStoreAddress) {
		this.popStoreAddress = popStoreAddress;
	}

	public String getPopStoreAddressDetail() {
		return popStoreAddressDetail;
	}

	public void setPopStoreAddressDetail(String popStoreAddressDetail) {
		this.popStoreAddressDetail = popStoreAddressDetail;
	}
	
	

}