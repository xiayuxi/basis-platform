package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface CategoryDao {
	
	/**
	 * 根据主键删除分类
	 * @Title: deleteByPrimaryKey
	 * @Description:  
	 * @param categoryId   主键
	 * @return   int 
	 *     
	 * @throws
	 */
	//int deleteByPrimaryKey(Integer categoryId);
	//设置is_del为1
	int deleteByCategoryId(Integer categoryId);

	/**
	 * 新增分类
	 * @Title: insert
	 * @Description: 
	 * @param record
	 * @return   int  
	 *    
	 * @throws
	 */
	int insert(Category record);

	int insertSelective(Category record);

	Category selectByPrimaryKey(Integer categoryId);

	int updateByPrimaryKeySelective(Category record);

	int updateByPrimaryKey(Category record);

	List<Category> selectPage(Map<String, Object> map);

	int selectPageCount(Map<String, Object> map);
	
	List<Category> selectCategoryAll();

	int selectCountByParentId(Integer parentId);
	//List<Tree> selectCategoryByParentId(Long parentId);
	
	List<Category> getCategoryByBrandId(Integer brandId);
	
	List<Category> getCategoryByParentId(Integer parentId);//看这个
	
	/**
	 * 查询分类列表
	 * @Title: selectCategoryList
	 * @Description: 
	 * @param map      主键、层级
	 * @return    List<Category>
	 *     
	 * @throws
	 */
	List<Category> selectCategoryList(Integer categoryId );
//    select
//    <include refid="Base_Column_List" />
//    from goods_category 
//    where is_del = 0 and parent_id = #{parentId};
	
	
	/**
	 * 查询所有第一级分类
	 * @Title: selectRootCategoryList
	 * @Description:     
	 * @author: robo   
	 * @date: 2015年10月1日 上午11:14:34       
	 * @version: 
	 *
	 * @return
	 *
	 */
	List<Category> selectRootCategoryList();
	
	/**
	 * 根据categoryId 得到所有下级分类(只包含三级)
	 * @Title: selectCategoryListAllByParentId
	 * @Description:     
	 * @author: robo   
	 * @date: 2015年10月15日 下午2:25:24       
	 * @version: 
	 *
	 * @param categoryId
	 * @return
	 *
	 */
	List<Integer> selectCategoryListAllByParentId(Integer categoryId);


	/**
	 * 功能描述：根据 popStoreNum 获取Category列表
	 * @author liukai
	 * @param popStoreNum
	 * @return
	 */
	List<Category> selectCategoryListByPopStoreNum(String popStoreNum);

	
	/**
	 * 搜索 根据关键字反推对应的分类
	 * @Title: selectCategoryListByKeywords
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月16日 下午1:36:38       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	List<Category> selectCategoryListByKeywords(Map<String,Object> map);
	
	/**
	 * 查询出全部的分类*/
	List<Category> selectAll();
	
	/**
	 * 根据店铺得到分类
	 * @Title: selectCategoryListByStoreId
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月28日 上午9:53:53       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	List<Category> selectCategoryListByStoreId(Map<String,Object> map);
	
	/**
	 * 根据分类Ids得到所有分类集合
	 * @Title: selectCategoryListByIds
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年11月6日 下午5:50:18       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	List<Category> selectCategoryListByIds(Map<String,Object> map);
	

}