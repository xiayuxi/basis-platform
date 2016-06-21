package com.ync365.seed.bussiness.modules.ad.entity;

import java.util.Date;

/**
 * 广告
 * @ClassName: Ad
 * @Description: TODO
 * @author robo
 * @date 2015年9月25日 上午11:24:17
 *
 */
public class Ad {
	
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
     * 排序
     */
    private Integer orders;

    /**
     * 开始日期
     */
    private Date beginDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 图片路径
     */
    private String path;

    /** 
     * 标题 
     */
    private String title;

    /** 
     * 类型     文本text,图片 image,flash flash
     */ 
    private Integer type;

    /**
     * 是否有链接
     */
    private short hasUrl;
    
    /** 
     * 链接地址 
     */
    private String url;

    /** 
     * 广告位 
     */
    private Integer adPosition;
    
    /**
     * 广告位名称
     */
    private String adPositionName;
    
    /**
     * 是否删除
     * 默认值为0
     * 1为已删除
     */
    private int isDel;

    /**
     * 内容 
     */
    private String content;
    
    /**
     * 状态
     * 0：默认未投放
     * 1：在投放
     * 2：已暂停
     * 3：已关闭
     */
    private Integer status;
    
    private Integer oprate;
    
    /**
     * 目标栏目
     */
    private Integer subject ;
    
    /**
     * 目标页面
     */
    private Integer page ;

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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getAdPosition() {
        return adPosition;
    }

    public void setAdPosition(Integer adPosition) {
        this.adPosition = adPosition;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public short getHasUrl() {
        return hasUrl;
    }

    public void setHasUrl(short hasUrl) {
        this.hasUrl = hasUrl;
    }

    public String getAdPositionName() {
        return adPositionName;
    }

    public void setAdPositionName(String adPositionName) {
        this.adPositionName = adPositionName;
    }

    public Integer getOprate() {
        return oprate;
    }

    public void setOprate(Integer oprate) {
        this.oprate = oprate;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
    
    

}