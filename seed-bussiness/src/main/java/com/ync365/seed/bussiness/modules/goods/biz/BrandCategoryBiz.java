package com.ync365.seed.bussiness.modules.goods.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.goods.dao.BrandCategoryDao;
import com.ync365.seed.bussiness.modules.goods.entity.BrandCategory;

@Service
public class BrandCategoryBiz   {

	@Autowired
	BrandCategoryDao brandCategoryDao ;
	
	/**
	 * 
	 * @Title: addBrandCategory
	 * @Description: TODO    ：    没啥用
	 * @author: xudawei/14070696    
	 * @date: 2015年9月21日 下午6:44:17       
	 * @version: 
	 *
	 * @param brandCategory
	 * @return
	 *
	 */
	public int addBrandCategory(BrandCategory brandCategory){
		return brandCategoryDao.insert(brandCategory);
	}
	
	/**
	 * 
	 * @Title: addBrandCategorys
	 * @Description: TODO    ：    关联操作
	 * @author: xudawei/14070696    
	 * @date: 2015年9月21日 下午6:44:57       
	 * @version: 
	 *
	 * @param brandId
	 * @param categoryIds
	 * @return
	 *
	 */
	@Transactional
	public int addBrandCategorys(Integer brandId,String categoryIds ){
		String [] ids = categoryIds.split(",");
		int returnValue = 0 ;
		for(String id:ids){
			/**
			 * 判断是否保存过
			 */
			BrandCategory brandCategory = searchBrandCategory(brandId,new Integer(id));
			if(brandCategory == null ){
			
				BrandCategory bc = new BrandCategory();
				bc.setBrandId(brandId);
				bc.setCategoryId(new Integer(id));
				returnValue = brandCategoryDao.insert(bc);
			}
		}
		return returnValue ;
	}
	
	/**
	 * 
	 * @Title: deleteById
	 * @Description: TODO    ：不知道干吗用的    
	 * @author: xudawei/14070696    
	 * @date: 2015年9月21日 下午7:25:12       
	 * @version: 
	 *
	 * @param brandId
	 * @param categoryId
	 * @return
	 *
	 */
	public int deleteById(Long brandId,Long categoryId){
		return 1;
	}
	
	/**
	 * 
	 * @Title: deleteById
	 * @Description: TODO    ：    根据品牌Id删除
	 * @author: xudawei/14070696    
	 * @date: 2015年9月21日 下午7:27:54       
	 * @version: 
	 *
	 * @param brandId
	 * @return
	 *
	 */
	public int deleteById(Integer brandId){
		return brandCategoryDao.deleteById(brandId);
	}
	
	public List<BrandCategory> searchBrandCategoryList(Integer brandId){
		return brandCategoryDao.searchBrandCategoryList(brandId);
	}
	
	/**
	 * 
	 * @Title: searchBrandCategory
	 * @Description: TODO    ：查询关联关系    
	 * @author: xudawei/14070696    
	 * @date: 2015年9月21日 下午7:42:55       
	 * @version: 
	 *
	 * @param brandId
	 * @param categoryId
	 * @return
	 *
	 */
	public BrandCategory searchBrandCategory(Integer brandId,Integer categoryId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("brandId", brandId);
		map.put("categoryId", categoryId);
		
		return brandCategoryDao.searchBrandCategory(map);
	}
	
}
