package com.ync365.seed.bussiness.modules.goods.entity;

import java.util.Date;

public class Category {
    private Integer categoryId;

    private String name;

    private String code;

    private String remark;

    private Integer parentId;

    private String keywords;

    private String mark;

    private String path;

    private Short ftype;

    private Short isDisplay;

    private Short seq;

    private Integer optUser;

    private Date optDate;

    private String optTerm;
    
    //默认值为0，1为已删除
    private Integer isDel;

    //父分类名称 
    private String puName;
    
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Short getFtype() {
        return ftype;
    }

    public void setFtype(Short ftype) {
        this.ftype = ftype;
    }

    public Short getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Short isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Short getSeq() {
        return seq;
    }

    public void setSeq(Short seq) {
        this.seq = seq;
    }

    public Integer getOptUser() {
        return optUser;
    }

    public void setOptUser(Integer optUser) {
        this.optUser = optUser;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public String getOptTerm() {
        return optTerm;
    }

    public void setOptTerm(String optTerm) {
        this.optTerm = optTerm == null ? null : optTerm.trim();
    }

	public String getPuName() {
		return puName;
	}

	public void setPuName(String puName) {
		this.puName = puName;
	}

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    
}