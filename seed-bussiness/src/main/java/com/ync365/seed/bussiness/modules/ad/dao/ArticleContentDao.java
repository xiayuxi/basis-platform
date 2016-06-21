package com.ync365.seed.bussiness.modules.ad.dao;

import com.ync365.seed.bussiness.modules.ad.entity.ArticleContent;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface ArticleContentDao {
    int insert(ArticleContent record);

    int insertSelective(ArticleContent record);

    String selectByArticleId(Integer id);
    
    int updateByArticleId(ArticleContent record);
    
    int deleteByPrimaryKey(Integer id);
}