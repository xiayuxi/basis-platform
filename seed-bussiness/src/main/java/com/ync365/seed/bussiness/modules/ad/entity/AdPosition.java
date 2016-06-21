package com.ync365.seed.bussiness.modules.ad.entity;

import java.util.Date;

/**
 * 广告位
 * 
 * @ClassName: AdPosition
 * @Description: TODO
 * @author robo
 * @date 2015年9月25日 上午11:24:41
 *
 */
public class AdPosition {
	/**
	 * 主键
	 */
    private Integer id;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 描述 
     */
    private String description;

    /**
     * 高
     */
    private Integer height;

    /**
     * 名称 
     */
    private String name;

    /**
     * 宽
     */
    private Integer width;

    /**
     * 模板
     */
    private String template;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }
}