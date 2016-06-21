package com.ync365.seed.bussiness.modules.common.entity;

public class Region {
	/**
	 * 主键
	 */
    private Integer id;

    /**
     * 上级区域
     */
    private Integer pid;

    /**
     * 区域名
     */
    private String name;

    /**
     * 邮政编码
     */
    private String code;

    /**
     * 区域码
     */
    private String codeV;

    /**
     * 级别
     */
    private Byte level;

    /**
     * 是否显示
     */
    private Byte isShow;

    /**
     * 经纬度
     */
    private String points;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCodeV() {
        return codeV;
    }

    public void setCodeV(String codeV) {
        this.codeV = codeV == null ? null : codeV.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points == null ? null : points.trim();
    }
}