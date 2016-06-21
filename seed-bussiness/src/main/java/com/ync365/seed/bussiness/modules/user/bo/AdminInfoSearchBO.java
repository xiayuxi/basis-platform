package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;

/**
 * 
 * 查询model seinfo
 *
 */
public class AdminInfoSearchBO {

    /**
     * 名称
     */
    private String name;
    
    /**
     * 手机号码
     * 
     */
    private String mobile;
    
    /**
     * 注册开始时间
     */
    private Date beginDate;
    
    /**
     * 注册结束时间
     */
    private  Date endDate;
    
    /**
     *  编号
     */
    private String adminNum;
    
    /**
     * 激活状态
     */
    private Boolean isFrozen;
    
    /**
     * a+姓名
     */
    private String aName;
    
    
    /**
     * 是否审核
     */
    private Boolean isAuthentication;
    /**
     * 地址编号
     */
    private Integer address;
    /**
     * 服务区域编号
     */
    private Integer serviceDistinct;
    
    /**
     * us 开始搜索条件
     */
    private Integer usCountBegin;
    
    /**
     * us数量 结束搜索条件
     */
    private Integer usCountEnd;
    
    /**
     * vs数量 开始搜索条件
     */
    private Integer vsCountBegin;
    
    /**
     * vs数量 结束搜索条件
     */
    private Integer vsCountEnd;
    
    /**
     * lc数量 开始搜索条件
     */
    private Integer lcCountBegin ;
    
    /**
     * lc数量 结束搜索条件
     */
    private Integer lcCountEnd;
    
    /**
     * se数量 开始搜索条件
     */
    private Integer seCountBegin;
    
    /**
     * se数量 结束搜索条件
     */
    private Integer seCountEnd;

    
    /**
     * 分页
     */
    private Integer pageIndex;
    
    /**
     * 分页
     */
    private Integer pageSize;
    
    /**
     * 角色Id
     */
    private Integer roleId;
    
    /**
     * A+角色 查询se 信息时 传入的a+编号 
     */
    private String aAiAdminNum;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Integer getUsCountBegin() {
        return usCountBegin;
    }

    public void setUsCountBegin(Integer usCountBegin) {
        this.usCountBegin = usCountBegin;
    }

    public Integer getUsCountEnd() {
        return usCountEnd;
    }

    public void setUsCountEnd(Integer usCountEnd) {
        this.usCountEnd = usCountEnd;
    }

    public Integer getVsCountBegin() {
        return vsCountBegin;
    }

    public void setVsCountBegin(Integer vsCountBegin) {
        this.vsCountBegin = vsCountBegin;
    }

    public Integer getVsCountEnd() {
        return vsCountEnd;
    }

    public void setVsCountEnd(Integer vsCountEnd) {
        this.vsCountEnd = vsCountEnd;
    }

    public Integer getLcCountBegin() {
        return lcCountBegin;
    }

    public void setLcCountBegin(Integer lcCountBegin) {
        this.lcCountBegin = lcCountBegin;
    }

    public Integer getLcCountEnd() {
        return lcCountEnd;
    }

    public void setLcCountEnd(Integer lcCountEnd) {
        this.lcCountEnd = lcCountEnd;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdminNum() {
		return adminNum;
	}

	public void setAdminNum(String adminNum) {
		this.adminNum = adminNum;
	}

	public Boolean getIsFrozen() {
		return isFrozen;
	}

	public void setIsFrozen(Boolean isFrozen) {
		this.isFrozen = isFrozen;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}

	public Boolean getIsAuthentication() {
		return isAuthentication;
	}

	public void setIsAuthentication(Boolean isAuthentication) {
		this.isAuthentication = isAuthentication;
	}

	public Integer getSeCountBegin() {
		return seCountBegin;
	}

	public void setSeCountBegin(Integer seCountBegin) {
		this.seCountBegin = seCountBegin;
	}

	public Integer getSeCountEnd() {
		return seCountEnd;
	}

	public void setSeCountEnd(Integer seCountEnd) {
		this.seCountEnd = seCountEnd;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getServiceDistinct() {
		return serviceDistinct;
	}

	public void setServiceDistinct(Integer serviceDistinct) {
		this.serviceDistinct = serviceDistinct;
	}

    public String getaAiAdminNum() {
        return aAiAdminNum;
    }

    public void setaAiAdminNum(String aAiAdminNum) {
        this.aAiAdminNum = aAiAdminNum;
    }
    
}
