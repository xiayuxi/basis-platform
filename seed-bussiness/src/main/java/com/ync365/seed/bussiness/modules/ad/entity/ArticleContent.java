package com.ync365.seed.bussiness.modules.ad.entity;

/**
 * 文章内容
 * @ClassName: ArticleContent
 * @Description: TODO
 * @author robo
 * @date 2015年9月25日 下午3:58:14
 *
 */
public class ArticleContent {
	
	/**
	 * 文章主键
	 */
    private Integer articleId;

    /**
     * 内容 
     */
    private String content;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}