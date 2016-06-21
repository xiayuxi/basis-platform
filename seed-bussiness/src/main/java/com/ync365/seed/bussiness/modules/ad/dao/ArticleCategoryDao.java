package com.ync365.seed.bussiness.modules.ad.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.ad.entity.ArticleCategory;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface ArticleCategoryDao {
	
	/**
	 * 删除 
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return int    
	 * @throws
	 */
    //int deleteByPrimaryKey(Integer id);
    
    //设置is_del= 1
    int deleteById(Integer id);

    /**
     * 新增
     * @Title: insert
     * @Description: TODO
     * @param record
     * @return int    
     * @throws
     */
    int insert(ArticleCategory record);

    /**
     * 新增
     * @Title: insertSelective
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年9月28日 下午7:51:14       
     * @version: 
     *
     * @param record
     * @return
     *
     */
    int insertSelective(ArticleCategory record);
    /**
     * 根据主键查询文章分类
     * @Title: selectByPrimaryKey
     * @Description: TODO
     * @param id
     * @return ArticleCategory    
     * @throws
     */
    ArticleCategory selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改文章分类
     * @Title: updateByPrimaryKey
     * @Description: TODO
     * @param record
     * @return  int    
     * @throws
     */
    int updateByPrimaryKey(ArticleCategory record);
    
    /**
     * 根据主键修改文章分类
     * @Title: updateByPrimaryKeySelective
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年9月29日 下午4:00:36       
     * @version: 
     *
     * @param record
     * @return
     *
     */
    int updateByPrimaryKeySelective(ArticleCategory record);
    
    /**
     * 查询所有分类列表
     * @Title: selectArticleCategoryList
     * @Description: TODO
     * @return List<ArticleCategory>    
     * @throws
     */
    List<ArticleCategory> selectArticleCategoryList();

    /**
     * 分页查询
     * @Title: selectPage
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年9月28日 下午5:40:23       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<ArticleCategory> selectPage(Map<String, Object> map);

    /**
     * 查询总数
     * @Title: selectPageCount
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年9月28日 下午5:49:41       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    int selectPageCount(Map<String, Object> map);


}