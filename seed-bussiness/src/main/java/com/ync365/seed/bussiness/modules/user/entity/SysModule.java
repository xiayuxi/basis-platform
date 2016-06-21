package com.ync365.seed.bussiness.modules.user.entity;

public class SysModule {
	/**
	 * 主键id 自动生成 自增
	 */
    private Integer id;
    
	/**
	 * 权限名称
	 */
    private String moduleName;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 权限rul 链接
     */
    private String url;
    
    /**
     * 权限上级节点id
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer moduleOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getModuleOrder() {
        return moduleOrder;
    }

    public void setModuleOrder(Integer moduleOrder) {
        this.moduleOrder = moduleOrder;
    }
}