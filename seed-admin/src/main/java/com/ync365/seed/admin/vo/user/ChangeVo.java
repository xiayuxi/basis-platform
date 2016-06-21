package com.ync365.seed.admin.vo.user;

import java.util.List;

import com.ync365.seed.bussiness.modules.user.bo.AdminInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;

public class ChangeVo {
	private String stateC;
	private UserUsInfoSearchBo userUsInfoSearchBo;
	private VsInfoSearchBO VsInfoSearchBO;
	private LcInfoSearchBO lcInfoSearchBO;
	private AdminInfoSearchBO adminInfoSearchBO;
	private List<String> userNumList;
	private String oldNum;
	private String newNum;
	public String getStateC() {
		return stateC;
	}
	public void setStateC(String stateC) {
		this.stateC = stateC;
	}
	public UserUsInfoSearchBo getUserUsInfoSearchBo() {
		return userUsInfoSearchBo;
	}
	public void setUserUsInfoSearchBo(UserUsInfoSearchBo userUsInfoSearchBo) {
		this.userUsInfoSearchBo = userUsInfoSearchBo;
	}
	public List<String> getUserNumList() {
		return userNumList;
	}
	public void setUserNumList(List<String> userNumList) {
		this.userNumList = userNumList;
	}
	public LcInfoSearchBO getLcInfoSearchBO() {
		return lcInfoSearchBO;
	}
	public void setLcInfoSearchBO(LcInfoSearchBO lcInfoSearchBO) {
		this.lcInfoSearchBO = lcInfoSearchBO;
	}
	public AdminInfoSearchBO getAdminInfoSearchBO() {
		return adminInfoSearchBO;
	}
	public void setAdminInfoSearchBO(AdminInfoSearchBO adminInfoSearchBO) {
		this.adminInfoSearchBO = adminInfoSearchBO;
	}
	public String getOldNum() {
		return oldNum;
	}
	public void setOldNum(String oldNum) {
		this.oldNum = oldNum;
	}
	public String getNewNum() {
		return newNum;
	}
	public void setNewNum(String newNum) {
		this.newNum = newNum;
	}
	public VsInfoSearchBO getVsInfoSearchBO() {
		return VsInfoSearchBO;
	}
	public void setVsInfoSearchBO(VsInfoSearchBO vsInfoSearchBO) {
		VsInfoSearchBO = vsInfoSearchBO;
	}
	
}
