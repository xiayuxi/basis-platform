package com.ync365.seed.bussiness.modules.goods.biz;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.goods.dao.CategoryDao;
import com.ync365.seed.bussiness.modules.goods.dao.FeatureDao;
import com.ync365.seed.bussiness.modules.goods.dao.FeatureValueDao;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.bussiness.modules.goods.entity.Feature;
import com.ync365.seed.bussiness.modules.goods.entity.FeatureValue;
import com.ync365.seed.utils.StringUtils;
@Service
public class CategoryBiz  {

	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	FeatureDao featureDao; 
	
	@Autowired
	FeatureValueDao featureValueDao;
	/**
	 * 新增保存
	 * @param category
	 * @return
	 */
	public int add(Category category){
		//插入数据
		int a = categoryDao.insert(category);
		int categoryId = category.getCategoryId();
		
		return categoryId;
	}
	
	/**
	 * 更新path字段
	 * @param temp
	 */
	public void updatePathByCategoryId(int categoryId) {
		//通过category查询category对象
		Category category = categoryDao.selectByPrimaryKey(categoryId);
		//创建对象用于update 
		Category caa = new Category();
		
		if(0 == category.getParentId()){
			caa.setCategoryId(category.getCategoryId());
			//设置path字段  为 category.getCategoryId() 转换成字符串 
			caa.setPath(category.getCategoryId()+",");
		}else{
			//获取值  path字段  为 category.getCategoryId() 转换成字符串 
			String tem = category.getCategoryId()+",";
			//递归循环 
			String temp = this.getCategoryIds(tem ,category);
									
			caa.setCategoryId(category.getCategoryId());
			caa.setPath(temp);
		}
		//更新：Category  主要更新  path字段
		categoryDao.updateByPrimaryKeySelective(caa);
	}
	
	
	/**
	 * 递归循环 category.getParentId() = 0时终止递归循环   由 cate.getCategoryId()拼成字符串 tem 返回 
	 * @param tem
	 * @param category
	 * @return
	 */
	private String getCategoryIds(String tem, Category category) {
		Category cate = categoryDao.selectByPrimaryKey(category.getParentId());
		tem = cate.getCategoryId()+","+tem;
		if(0== cate.getParentId()){
			 return  tem;
		}else{
			return this.getCategoryIds(tem, cate);
		}
	}


	public List<Category> selectPage(String qName,int startIndex,int pageSize){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name", qName);
		map.put("pageIndex", startIndex);
		map.put("pageSize", pageSize);
		return categoryDao.selectPage(map);
	}

	public int selectPageCount(String categoryName) {
		Map<String,Object> map=new HashMap<String ,Object>();
		return categoryDao.selectPageCount(map);
		
	}
	
	public List<Category> selectCategoryAll() {
		return categoryDao.selectCategoryAll();
	}

	public int deleteByPrimaryKey(Integer CategoryId) {
		return categoryDao.deleteByCategoryId(CategoryId);
	}

	public int updateByPrimaryKey(Category c) {
		return categoryDao.updateByPrimaryKeySelective(c);
	}

	public Category selectByPrimaryKey(Integer id) {
		return categoryDao.selectByPrimaryKey(id);
	}

	public int selectCountByParentId(Integer parentId) {
		return categoryDao.selectCountByParentId(parentId);
	}
	
	public List<Category> getCategoryByBrandId(Integer brandId){
		return categoryDao.getCategoryByBrandId(brandId);
	}
	
	public List<Category> getCategoryByParentId(Integer parentId){
		return categoryDao.getCategoryByParentId(parentId);
	}
	/**
	 * 
	 * @Title: add
	 * @Description: 应该是不用了    
	 * @author: xudawei/14070696    
	 * @date: 2015年9月22日 上午11:56:31       
	 * @version: 
	 *
	 * @param category
	 * @param fList
	 * @return
	 *
	 */
	@Transactional
	public int add(Category category, List<Feature> fList){
		//插入数据
		int a = categoryDao.insert(category);
		int categoryId = category.getCategoryId();
		//addFeatures(fList);
		if(fList!=null && fList.size()>0){
			for (Feature feature : fList) {
				feature.setCategoryId(categoryId);
				int b = featureDao.insert(feature);
				if(b==1){
					Integer featureId = feature.getFeatureId();
					String[] optionals = feature.getOptionals().split(",");
					FeatureValue featureValue = new FeatureValue();
					for (String attrValue : optionals) {
						featureValue.setFeatureId(featureId);
						featureValue.setAttrValue(attrValue);
						featureValue.setIsDel(0);
						int c = featureValueDao.insert(featureValue);
					}
				}
			}
		}
		return categoryId;
	}

	/**
	 * 不用了
	 * @Title: updateByPrimaryKey
	 * @Description: TODO    ：    
	 * @author: xudawei/14070696    
	 * @date: 2015年9月22日 上午11:57:31       
	 * @version: 
	 *
	 * @param category
	 * @param featureList
	 * @return
	 *
	 */
	public int updateByPrimaryKey(Category category, List<Feature> featureList) {
		return categoryDao.updateByPrimaryKeySelective(category);
		
	}
	
	/**
	 * featureController中查找三级联动所需属性链
	 * @return
	 */
	public List<Category> selectCategoryChain(Category category) {
		List<Category> categoryChain1 = new ArrayList<Category>();
		List<Category> categoryChain2 = new ArrayList<Category>();
		//category一共三个级别

		while(category.getParentId()!=0){
			categoryChain1.add(category);
			Category c = categoryDao.selectByPrimaryKey(category.getParentId());
			category =c;
		}
		categoryChain1.add(category);
		for (int i = categoryChain1.size()-1; i >=0 ; i--) {
			categoryChain2.add(categoryChain1.get(i));
		}
		return categoryChain2;
	}

	
	/**
	 * 根据主键及层给查询对应的分类列表
	 * @Title: getCategoryList
	 * @Description: TODO 
	 * @param categoryId       分类主键
	 * @param level            层级
	 * @return  List<Category>
	 *     
	 * @throws
	 */
//	public List<Category> selectCategoryList(Integer categoryId){
//	 
//		return categoryDao.getCategoryByParentId(categoryId) ;
//	}
	
	public List<Category> selectRootCategoryList(){
		
		return categoryDao.selectRootCategoryList();
	}
	
	/**
	 * 根据categoryId得到所有下级分类(只包含三级)
	 * @Title: selectCategoryListAllByParentId
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月15日 下午2:26:50       
	 * @version: 
	 *
	 * @param categoryId
	 * @return
	 *
	 */
	public List<Integer> selectCategoryListAllByParentId(Integer categoryId){
		return categoryDao.selectCategoryListAllByParentId(categoryId);
	}

	
	/**
	 * 功能描述：根据 popStoreNum 获取Category列表
	 * @author liukai
	 * @param popStoreNum
	 * @return
	 */
	public List<Category> selectCategoryListByPopStoreNum(String popStoreNum) {
		return categoryDao.selectCategoryListByPopStoreNum(popStoreNum);
	}

	
	/**
	 * 根据关键字反推所对应的所有分类
	 * @Title: selectCategoryListByKeywords
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月16日 下午1:38:50       
	 * @version: 
	 *
	 * @param keywords  关键字
	 * @param regionId  区域
	 * @return
	 *
	 */
	public List<Category> selectCategoryListByKeywords(String keywords,Integer regionId){
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(StringUtils.isNotBlank(keywords)){
			String key[] = keywords.split(" ");
			
			String sql = "";
			for(String s:key){
				
				if(StringUtils.isNoneBlank(s)){
					sql += "   b.full_name like   concat('%','"+s+"','%' )     or a.keywords like  concat('%','"+s+"','%' )  " +" or "; 
				}
			}
			int position  = sql.lastIndexOf("or");
			sql = sql.substring(0,position);
			map.put("keywords", "("+sql +")");
		}else{
			map.put("keywords", keywords);
		}
		
 
		map.put("regionId", regionId);
		
		return categoryDao.selectCategoryListByKeywords(map);
		
	}
	/**
	 * 查找全部的分类
	 * 
	 */
	public List<Category> selectAll(){
		return categoryDao.selectAll();
	}
	
	public List<Category> selectCategoryListByStoreId(Integer storeId){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("storeId", storeId);
		
		return categoryDao.selectCategoryListByStoreId(map);
	}
	
	public List<Category> selectCategoryListByIds(List<Integer> categoryIds){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", categoryIds);
		return categoryDao.selectCategoryListByIds(map);
	}

}
