package com.ync365.seed.bussiness.modules.ad.entity;

import java.util.Date;

/**
 * 文章分类
 * 
 * @ClassName: ArticleCategory
 * @Description: TODO
 * @author robo
 * @date 2015年9月25日 下午3:57:56
 *
 */
public class ArticleCategory {
	
	/**
	 * 分类主键
	 */
    private Integer id;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 排序号
     */
    private Integer orders;

    /**
     * 层级
     */
    private Integer grade;

    /**
     * 名称 
     */
    private String name;

    /**
     * 页面描述
     */
    private String seoDescription;

    /**
     * 页面关键字
     */
    private String seoKeywords;

    /**
     * 页面标题
     */
    private String seoTitle;

    /**
     * 树路径 
     */
    private String treePath;

    /**
     * 上级分类
     */
    private Integer parent;
    
    //默认值为0，删除为1
    private Integer isDel;

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

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription == null ? null : seoDescription.trim();
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords == null ? null : seoKeywords.trim();
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle == null ? null : seoTitle.trim();
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath == null ? null : treePath.trim();
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}