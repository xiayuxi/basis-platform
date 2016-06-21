package com.ync365.seed.bussiness.modules.ad.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Data;
import com.ync365.seed.bussiness.modules.ad.dao.ArticleContentDao;
import com.ync365.seed.bussiness.modules.ad.dao.ArticleDao;
import com.ync365.seed.bussiness.modules.ad.entity.Article;
import com.ync365.seed.bussiness.modules.ad.entity.ArticleContent;

/**
 * 文章业务类
 * 
 * @ClassName: ArticleBiz
 * @Description: 
 * @author robo
 * @date 2015年9月26日 下午3:26:36
 *
 */
@Service
public class ArticleBiz {

	@Autowired
	ArticleDao articleDao ;
	
	@Autowired
	ArticleContentDao acDao;
	
	@Transactional
	public int deleteByArticleId(Integer id){
	    int rtn = 0;
	    try {
	        rtn = acDao.deleteByPrimaryKey(id);
	        rtn = articleDao.deleteByArticleId(id);
        } catch (Exception e) {
            e.printStackTrace();
            rtn = 0;
        }
		return rtn;
	}

	@Transactional
    public int insert(Article record, ArticleContent articleContent){
		int rtn = 0 ;
		try{
		    record.setAddTime(new Date(System.currentTimeMillis()));
		    rtn =  articleDao.insertSelective(record);
		    articleContent.setArticleId(record.getArticleId());
		    rtn = acDao.insert(articleContent);
		}catch(Exception e){
			e.printStackTrace();
			rtn = 0 ;
		}
		return rtn;
    }

    public Article selectByPrimaryKey(Integer id){
    	return articleDao.selectByPrimaryKey(id);
    }

    @Transactional
    public int updateByPrimaryKey(Article record, ArticleContent articleContent){
        acDao.updateByArticleId(articleContent);
        return articleDao.updateByPrimaryKeySelective(record);
    }
    
    /**
     * 查询文章列表[分页]
     * @Title: selectArticlePageList
     * @Description: 
     * @param title
     * @param startIndex
     * @param pageSize
     * @return  List<Article>    
     * @throws
     */
    public List<Article> selectArticlePageList(String title,int startIndex,int pageSize){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("name", title);
    	map.put("startIndex", startIndex);
    	map.put("pageSize", pageSize );
    	
    	return articleDao.selectArticlePageList(map);// articleDao.selectArticlePageList(map);
    }
    
    /**
     * 查询文章列表记录总数
     * @Title: selectArticleListCount
     * @Description: 
     * @param title
     * @param startIndex
     * @param pageSize
     * @return int    
     * @throws
     */
    public int selectArticleListCount(String title,int startIndex,int pageSize){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("title", title);
    	return 0;// articleDao.selectArticlePageListCount(map);
    }
    
    /**
     * 前台查询文章列表[首页：比如公告]
     * @Title: selectArticleList
     * @Description:     
     * @author: robo   
     * @date: 2015年9月29日 下午2:04:15       
     * @version: 
     *
     * @param pageSize
     * @param categoryId
     * @return
     *
     */
    public List<Article> selectArticleList(int pageSize,Integer categoryId){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("pageSize", pageSize) ;
    	map.put("categoryId", categoryId);
    	return articleDao.selectArticleList(map);
    }
	
}
