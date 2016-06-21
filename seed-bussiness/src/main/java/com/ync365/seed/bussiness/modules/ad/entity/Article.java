package com.ync365.seed.bussiness.modules.ad.entity;

import java.util.Date;


/**
 * 文章
 * 
 * @ClassName: Article
 * @Description: 
 * @author robo
 * @date 2015年9月25日 下午3:57:45
 *
 */
public class Article {

	private Integer articleId;

	/**
	 * 文章的分类
	 */
	private Short categoryId;

	private Integer expertId;

	/**
	 * 文章标题
	 */
	private String title;

	private String author;

	/**
	 * 标签
	 */
	private String tags;

	/**
	 * 关键字
	 */
	private String keywords;

	/**
	 * 文章重要性
	 * 1、普通；2、置顶；3、首页推荐
	 */
	private Integer articleType;

	/**
	 *  是否显示
	 *  1、显示；0、不显示
	 */
	private Boolean isShow;

	private Date addTime;

	private String fileUrl;

	private Boolean openType;

	/**
	 * 外部链接
	 */
	private String link;

	/**
	 * 文件简介
	 */
	private String brief;

	/**
	 * 网页描述
	 */
	private String description;

	private Integer visitNum;

	private Integer hot;

	private String articleImg;

	/**
	 * 文章来源
	 */
	private String source;
	
	/**
	 * 文章内容
	 */
	private String content;

	/**
	 * 推荐位图片
	 */
	private String bigImg;

	/**
	 * 缩略图
	 */
	private String smallImg;
	
	private int seq;
	
	private int isDel;

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Short getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Short categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getExpertId() {
		return expertId;
	}

	public void setExpertId(Integer expertId) {
		this.expertId = expertId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags == null ? null : tags.trim();
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords == null ? null : keywords.trim();
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}


	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl == null ? null : fileUrl.trim();
	}

	public Boolean getOpenType() {
		return openType;
	}

	public void setOpenType(Boolean openType) {
		this.openType = openType;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link == null ? null : link.trim();
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief == null ? null : brief.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Integer getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public String getArticleImg() {
		return articleImg;
	}

	public void setArticleImg(String articleImg) {
		this.articleImg = articleImg == null ? null : articleImg.trim();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source == null ? null : source.trim();
	}

	public String getBigImg() {
		return bigImg;
	}

	public void setBigImg(String bigImg) {
		this.bigImg = bigImg == null ? null : bigImg.trim();
	}

	public String getSmallImg() {
		return smallImg;
	}

	public void setSmallImg(String smallImg) {
		this.smallImg = smallImg == null ? null : smallImg.trim();
	}

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}