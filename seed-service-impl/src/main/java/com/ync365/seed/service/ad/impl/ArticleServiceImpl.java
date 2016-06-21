package com.ync365.seed.service.ad.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.ad.biz.ArticleBiz;
import com.ync365.seed.bussiness.modules.ad.biz.ArticleContentBiz;
import com.ync365.seed.bussiness.modules.ad.entity.Article;
import com.ync365.seed.bussiness.modules.ad.entity.ArticleContent;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.service.ad.ArticleService;

public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleBiz articleBiz ;
	
	@Autowired
	ArticleContentBiz articleContentBiz;
	
	public ResponseDTO getArticleList(int pageSize, Integer categoryId) {
		ResponseDTO dto = new ResponseDTO() ;
		try{
			List<Article> list =  articleBiz.selectArticleList(pageSize, categoryId) ;
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setData(list);
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			e.printStackTrace(); 
		}
		return dto;
	}
	
	public ResponseDTO getArticleById(Integer articleId){
		ResponseDTO dto = new ResponseDTO() ;
		try{
			Article article  =  articleBiz.selectByPrimaryKey(articleId) ;
			String content = articleContentBiz.selectByArticleId(articleId);
			article.setContent(content);
			dto.setCode(ResponseCode.CommonCode.OK.v());
			dto.setData(article);
			dto.setMsg(ResponseCode.CommonCode.OK.c());
		}catch(Exception e){
			e.printStackTrace(); 
		}
		return dto;
	}

}
