package com.ync365.seed.bussiness.modules.ad.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.ad.dao.ArticleCategoryDao;
import com.ync365.seed.bussiness.modules.ad.dao.ArticleDao;
import com.ync365.seed.bussiness.modules.ad.entity.Article;
import com.ync365.seed.bussiness.modules.ad.entity.ArticleCategory;

/**
 * 文章分类业务类
 * 
 * @ClassName: ArticleCategoryBiz
 * @Description: 
 * @author robo
 * @date 2015年9月26日 下午3:26:15
 *
 */
@Service
public class ArticleCategoryBiz {

	@Autowired
	ArticleCategoryDao articleCategoryDao ;
	
	@Autowired
	ArticleDao articleDao;
	
	/**
	 * 根据业务主键查询文章分类
	 * @Title: selectArticleCategory
	 * @Description: 
	 * @param id
	 * @return ArticleCategory    
	 * @throws
	 */
	public ArticleCategory selectByPrimaryKey(Integer id){
		return articleCategoryDao.selectByPrimaryKey(id) ;
	}
	
	//如果在使用的类别不能删除
	//TODO
	public  int deleteByPrimaryKey(Integer id){
	    if(articleDao.countByArticleCategory(id)>0){
	       return 2; 
	    }else{
	        return articleCategoryDao.deleteById(id);
	    }
	}

    public int insert(ArticleCategory record){
        record.setIsDel(0);
    	return articleCategoryDao.insertSelective(record);
    }
    
	public int updateByPrimaryKey(ArticleCategory record){
		return articleCategoryDao.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 查询所有文章分类栏目
	 * @Title: selectArticleCategoryList
	 * @Description: 
	 * @return    
	 * List<ArticleCategory>    
	 * @throws
	 */
	public List<ArticleCategory> selectArticleCategoryList(){
		return articleCategoryDao.selectArticleCategoryList();
	}

	/**
	 * 分页查询
	 * @Title: selectPage
	 * @Description:     
	 * @author: liugl    
	 * @date: 2015年9月28日 下午5:38:01       
	 * @version: 
	 *
	 * @param qName
	 * @param startIndex
	 * @param rows
	 * @return
	 *
	 */
    public List<ArticleCategory> selectPage(String qName, int startIndex, int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", qName);
        map.put("pageIndex", startIndex);
        map.put("pageSize", rows);
        return articleCategoryDao.selectPage(map);
    }

    public int selectPageCount(String qName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", qName);
        return articleCategoryDao.selectPageCount(map);
    }

}
