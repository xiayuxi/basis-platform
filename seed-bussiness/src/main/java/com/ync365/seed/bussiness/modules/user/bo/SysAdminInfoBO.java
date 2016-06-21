package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;
import java.util.List;


/**
 * 自定义model admininfo
 * @author 
 *
 */
public class SysAdminInfoBO {
   
    /**
     * 用户编号
     */
    private String adminNum;

    /**
     * 姓名
     */
    private String name;

    /**
     * 地址编号
     */
    private Integer address;

    /**
     * 地址详情
     */
    private String addressDetail;

    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 座机号码
     */
    private String telephone;
    /**
     * 手机号
     */
    private String mobile;
    
    /**
     * 用户名
     */
    private String adminLoginName;
    private List<Integer> serviceserviceDistincts;

    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 是否冻结
     */
    private Boolean isFrozen;

    /**
     * 是否审核
     */
    private Boolean isAuthentication;
    
    /**
     * A+编号
     */
    private String aNum;
    /**
     * A+手机号
     */
    private String aMobile;
    /**
     * A+姓名
     */
    private String aName;
    /**
     * lc数量
     */
    private Integer lcCount;
    
    /**
     * vs数量
     */
    private Integer vsCount;
    
    /**
     * us数量
     */
    private Integer usCount;
    
    /**
     * se数量
     */
    private Integer seCount;

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getLcCount() {
        return lcCount;
    }

    public void setLcCount(Integer lcCount) {
        this.lcCount = lcCount;
    }

    public Integer getVsCount() {
        return vsCount;
    }

    public void setVsCount(Integer vsCount) {
        this.vsCount = vsCount;
    }

    public Integer getUsCount() {
        return usCount;
    }

    public void setUsCount(Integer usCount) {
        this.usCount = usCount;
    }
    public String getaNum() {
        return aNum;
    }

    public void setaNum(String aNum) {
        this.aNum = aNum;
    }

    public List<Integer> getServiceserviceDistincts() {
        return serviceserviceDistincts;
    }

    public void setServiceserviceDistincts(List<Integer> serviceserviceDistincts) {
        this.serviceserviceDistincts = serviceserviceDistincts;
    }

	public Integer getSeCount() {
		return seCount;
	}

	public void setSeCount(Integer seCount) {
		this.seCount = seCount;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getaMobile() {
		return aMobile;
	}

	public void setaMobile(String aMobile) {
		this.aMobile = aMobile;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getAdminLoginName() {
		return adminLoginName;
	}

	public void setAdminLoginName(String adminLoginName) {
		this.adminLoginName = adminLoginName;
	}
    
}
