package com.ync365.seed.bussiness.modules.user.bo;

import java.util.Date;
import java.util.Map;

import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerPropertyInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerValueInfo;

public class LargeCustomerInfoBO {
    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 审核时间
     */
    private Date authenticationTime;
    
    /**
     * 手机号
     */
    private String userMobile;

    /**
     * 用户真实姓名
     */
    private String sysUserRealName;
    
    /**
     * 用户地址编号
     */
    private Integer sysUserAddress;

    /**
     * 用户地址详情
     */
    private String sysUserAddressDetail;
    
    /**
     * vs info 名称
     */
    private String vsUserName;
    
    /**
     * lc info名称
     */
    private String lcUserName;
    
    /**
     * se info 名称
     */
    private String seUserName;
    
    /**
     * a+ info 名称
     */
    private String aUserName;
    
    private Map<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> sysLargeCustomerValueInfo;
    
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Map<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> getSysLargeCustomerValueInfo() {
        return sysLargeCustomerValueInfo;
    }

    public void setSysLargeCustomerValueInfo(
            Map<SysLargeCustomerPropertyInfo, SysLargeCustomerValueInfo> sysLargeCustomerValueInfo) {
        this.sysLargeCustomerValueInfo = sysLargeCustomerValueInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getSysUserRealName() {
        return sysUserRealName;
    }

    public void setSysUserRealName(String sysUserRealName) {
        this.sysUserRealName = sysUserRealName;
    }

    public Integer getSysUserAddress() {
        return sysUserAddress;
    }

    public void setSysUserAddress(Integer sysUserAddress) {
        this.sysUserAddress = sysUserAddress;
    }

    public String getSysUserAddressDetail() {
        return sysUserAddressDetail;
    }

    public void setSysUserAddressDetail(String sysUserAddressDetail) {
        this.sysUserAddressDetail = sysUserAddressDetail;
    }

    public String getVsUserName() {
        return vsUserName;
    }

    public void setVsUserName(String vsUserName) {
        this.vsUserName = vsUserName;
    }

    public String getLcUserName() {
        return lcUserName;
    }

    public void setLcUserName(String lcUserName) {
        this.lcUserName = lcUserName;
    }

    public String getSeUserName() {
        return seUserName;
    }

    public void setSeUserName(String seUserName) {
        this.seUserName = seUserName;
    }

    public String getaUserName() {
        return aUserName;
    }

    public void setaUserName(String aUserName) {
        this.aUserName = aUserName;
    }

	public Date getAuthenticationTime() {
		return authenticationTime;
	}

	public void setAuthenticationTime(Date authenticationTime) {
		this.authenticationTime = authenticationTime;
	}

   
}