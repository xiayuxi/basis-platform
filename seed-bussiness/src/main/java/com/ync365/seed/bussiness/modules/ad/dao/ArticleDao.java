package com.ync365.seed.bussiness.modules.ad.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.ad.entity.Article;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface ArticleDao {
	
    //int deleteByPrimaryKey(Integer id);
    
    //is_del设为1
    int deleteByArticleId(Integer articleId);

    int insert(Article record);
    
    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Article record);
    
    int updateByPrimaryKeySelective(Article record);
    
    /**
     * 查询文章列表[分页]
     * @Title: selectArticlePageList
     * @Description: TODO
     * @param title
     * @param startIndex
     * @param pageSize
     * @return    
     * List<Article>    
     * @throws
     */
    public List<Article> selectArticlePageList(Map<String,Object> map);
    
    /**
     * 查询文章列表总记录数
     * @Title: selectArticlePageListCount
     * @Description: TODO
     * @param title
     * @return  int    
     * @throws
     */
    public int selectArticlePageListCount(Map<String,Object> map);
    
    /**
     * 查询文章列表  [前台查询 比如公告]
     * @Title: selectArticleList
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年9月29日 下午2:06:29       
     * @version: 
     *
     * @param map   pageSize 显示 条数  categoryId 栏目Id
     * @return
     *
     */
    public List<Article> selectArticleList(Map<String,Object> map) ;

    int countByArticleCategory(Integer id);
    
}