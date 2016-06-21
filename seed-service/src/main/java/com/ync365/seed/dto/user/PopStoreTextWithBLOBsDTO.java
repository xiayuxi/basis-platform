package com.ync365.seed.dto.user;

public class PopStoreTextWithBLOBsDTO {
	private Integer id;
	
	private String popStoreNum;
	
	private String popAfterService;

	private String popBrandAdvantage;

	public String getPopAfterService() {
		return popAfterService;
	}

	public void setPopAfterService(String popAfterService) {
		this.popAfterService = popAfterService == null ? null : popAfterService.trim();
	}

	public String getPopBrandAdvantage() {
		return popBrandAdvantage;
	}

	public void setPopBrandAdvantage(String popBrandAdvantage) {
		this.popBrandAdvantage = popBrandAdvantage == null ? null : popBrandAdvantage.trim();
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
		this.popStoreNum = popStoreNum;
	}
	
	
}
