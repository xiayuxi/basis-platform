package com.ync365.seed.bussiness.modules.user.entity;

public class SysRoleModule {
	
	/**
	 * 角色 id
	 */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer moduleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}