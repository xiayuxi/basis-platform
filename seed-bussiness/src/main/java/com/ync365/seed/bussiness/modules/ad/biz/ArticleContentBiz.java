package com.ync365.seed.bussiness.modules.ad.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.ad.dao.ArticleContentDao;

/**
 * 文章内容业务类
 * 
 * @ClassName: ArticleCategoryBiz
 * @Description: TODO
 * @author robo
 * @date 2015年9月26日 下午3:26:15
 *
 */
@Service
public class ArticleContentBiz {

	@Autowired
	ArticleContentDao articleContentDao ;
	
	/**
	 * 根据文章主键查询文章内容
	 * @Title: selectArticleCategory
	 * @Description: TODO
	 * @param id
	 * @return ArticleCategory    
	 * @throws
	 */
	public String selectByArticleId(Integer id){
		return articleContentDao.selectByArticleId(id) ;
	}

}
