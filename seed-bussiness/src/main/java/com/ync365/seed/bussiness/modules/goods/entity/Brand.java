package com.ync365.seed.bussiness.modules.goods.entity;

import java.util.Date;

public class Brand {
    private Integer brandId;

    private String chName;

    private String enName;

    private String remark;
    
    private String uuid; 

    private String logo;

    private Short seq;

    private Short status;

    private String address;

    private String story;

    private Date createTime;

    private Date optDate;

    private Integer optUser;

    private String optTerm;
    
    //默认值为0，1为已删除
    private Integer isDel; 

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName == null ? null : chName.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Short getSeq() {
        return seq;
    }

    public void setSeq(Short seq) {
        this.seq = seq;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story == null ? null : story.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public Integer getOptUser() {
        return optUser;
    }

    public void setOptUser(Integer optUser) {
        this.optUser = optUser;
    }

    public String getOptTerm() {
        return optTerm;
    }

    public void setOptTerm(String optTerm) {
        this.optTerm = optTerm == null ? null : optTerm.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    
    
}