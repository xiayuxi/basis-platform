package com.ync365.seed.dto.user;

import java.util.List;

public class PopStoreInfoDTO {
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
     * 店铺域名
     */
    private String popStoreDomain;
    /**
     * 所处区域 
     */
    private String popStoreAddress;
    private String popStoreAddressDetail;
    public String getPopStoreAddressDetail() {
        return popStoreAddressDetail;
    }

    public void setPopStoreAddressDetail(String popStoreAddressDetail) {
        this.popStoreAddressDetail = popStoreAddressDetail;
    }

    /**
     * 装修状态
     */
    private String popDecorate;
    private List<String> brands;
    private List<String> categorys;
    
    /**
     * 售后服务
     */
    private String popAfterService;
    
    /**
     * 品牌优势
     */
    private String popBrandAdvantage;

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
		this.popStoreNum = popStoreNum;
	}

	public String getPopStoreName() {
		return popStoreName;
	}

	public void setPopStoreName(String popStoreName) {
		this.popStoreName = popStoreName;
	}

	public String getPopStoreContact() {
		return popStoreContact;
	}

	public void setPopStoreContact(String popStoreContact) {
		this.popStoreContact = popStoreContact;
	}

	public String getPopStoreMobile() {
		return popStoreMobile;
	}

	public void setPopStoreMobile(String popStoreMobile) {
		this.popStoreMobile = popStoreMobile;
	}

	public String getPopStoreType() {
		return popStoreType;
	}

	public void setPopStoreType(String popStoreType) {
		this.popStoreType = popStoreType;
	}

	public String getPopStoreDomain() {
		return popStoreDomain;
	}

	public void setPopStoreDomain(String popStoreDomain) {
		this.popStoreDomain = popStoreDomain;
	}

	public String getPopStoreAddress() {
		return popStoreAddress;
	}

	public void setPopStoreAddress(String popStoreAddress) {
		this.popStoreAddress = popStoreAddress;
	}

	public String getPopDecorate() {
		return popDecorate;
	}

	public void setPopDecorate(String popDecorate) {
		this.popDecorate = popDecorate;
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
    
    
    
}
