package com.ync365.seed.dto.user;

import java.util.Date;
import java.util.List;

public class LargeCustomerInfoDTO {
    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 状态
     */
    private Integer state;
    /**
     * 审核时间
     */
    private Date authenticationTime;

    private List<LargeCustomerPropertyInfoDTO> propertys;

    public List<LargeCustomerPropertyInfoDTO> getPropertys() {
        return propertys;
    }

    public void setPropertys(List<LargeCustomerPropertyInfoDTO> propertys) {
        this.propertys = propertys;
    }

    public String getUserNum() {
        return userNum;
    }

    public Date getAuthenticationTime() {
		return authenticationTime;
	}

	public void setAuthenticationTime(Date authenticationTime) {
		this.authenticationTime = authenticationTime;
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
}
