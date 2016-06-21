package com.ync365.seed.bussiness.modules.user.entity;

public class SysReceiveGoodsInfo {
	
	/**
	 * 主键id 自增 
	 */
	private Integer id;
	/**
	 * 用户编号
	 */
    private String userNum;

    /**
     * 接收人姓名
     */
    private String sysReceiveGoodsName;

    /**
     * 接收人手机号
     */
    private String sysReceiveGoodsMobile;

    /**
     * 接收人5级地址
     */
    private Integer sysReceiveGoodsAddress;
//    /**
//     * 接收人5级地址处理结果
//     */
//    private String sysReceiveGoodsAddressString;

    /**
     * 接收人详细地址
     */
    private String sysReceiveGoodsAddressDetail;

    /**
     * 是否默认
     */
    private Integer sysReceiveGoodsIsDefault;

    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public String getSysReceiveGoodsName() {
        return sysReceiveGoodsName;
    }

    public void setSysReceiveGoodsName(String sysReceiveGoodsName) {
        this.sysReceiveGoodsName = sysReceiveGoodsName == null ? null : sysReceiveGoodsName.trim();
    }

    public String getSysReceiveGoodsMobile() {
        return sysReceiveGoodsMobile;
    }

    public void setSysReceiveGoodsMobile(String sysReceiveGoodsMobile) {
        this.sysReceiveGoodsMobile = sysReceiveGoodsMobile == null ? null : sysReceiveGoodsMobile.trim();
    }

    public Integer getSysReceiveGoodsAddress() {
        return sysReceiveGoodsAddress;
    }

    public void setSysReceiveGoodsAddress(Integer sysReceiveGoodsAddress) {
        this.sysReceiveGoodsAddress = sysReceiveGoodsAddress;
    }

    public String getSysReceiveGoodsAddressDetail() {
        return sysReceiveGoodsAddressDetail;
    }

    public void setSysReceiveGoodsAddressDetail(String sysReceiveGoodsAddressDetail) {
        this.sysReceiveGoodsAddressDetail = sysReceiveGoodsAddressDetail == null ? null : sysReceiveGoodsAddressDetail.trim();
    }

	public Integer getSysReceiveGoodsIsDefault() {
		return sysReceiveGoodsIsDefault;
	}

	public void setSysReceiveGoodsIsDefault(Integer sysReceiveGoodsIsDefault) {
		this.sysReceiveGoodsIsDefault = sysReceiveGoodsIsDefault;
	}

  
}