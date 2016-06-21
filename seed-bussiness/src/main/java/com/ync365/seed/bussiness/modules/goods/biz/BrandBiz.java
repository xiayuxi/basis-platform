package com.ync365.seed.bussiness.modules.goods.biz;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.goods.dao.BrandCategoryDao;
import com.ync365.seed.bussiness.modules.goods.dao.BrandDao;
import com.ync365.seed.bussiness.modules.goods.entity.Brand;
import com.ync365.seed.utils.StringUtils;

@Service
public class BrandBiz {

	@Autowired
	private BrandDao brandDao;
	
	@Autowired
	private BrandCategoryDao brandCategoryDao;
	
	/**
	 * 添加品牌
	 * @return 
	 */
	public int addBrand(Brand brand) {
	    brand.setCreateTime(new Timestamp(System.currentTimeMillis()));
	    brand.setIsDel(0);
		return brandDao.insert(brand);
	} 
	
	/**
	 * （根据条件）分页查询
	 * @param qChName
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Brand> selectPage(String qChName,int startIndex,int pageSize){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("chName", qChName);
		map.put("pageIndex", startIndex);
		map.put("pageSize", pageSize);
		return brandDao.selectPage(map);
	}
 
	
	/**
	 * （根据条件）计数
	 * @param chName
	 * @return
	 */
	public long selectPageCount(String chName) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("chName", chName);
		return brandDao.selectPageCount(map);
	}

	/**
	 * 根据主键删除一条记录,is_del字段设为1
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id) {
		return brandDao.deleteByBrandId(id);
	}

	/**
	 * 根据主键查询一条记录
	 * @param id
	 * @return
	 */
	public Brand selectById(Integer id) {
		return brandDao.selectByPrimaryKey(id);
	}
	
	/**
	 * 
	 * @Title: selectByChName
	 * @Description:     根据中文名查询brand对象
	 * @author: liugl    
	 * @date: 2015年10月15日 下午2:31:46       
	 * @version: 
	 *
	 * @param chName
	 * @return 查询不到返回null
	 *
	 */
	public Brand selectByChName(String chName) {
	    return brandDao.selectByChName(chName);
	}
	
	/**
	 * 编辑一条记录
	 * @param brand
	 * @return
	 */
	public int edit(Brand brand) {
		return brandDao.updateByPrimaryKeySelective(brand);
	}
	
	public List<Brand> getAllbrandList(){
		return brandDao.getAllbrandList();
	}
	
	public List<Brand> selectBrandbyCategoryId(Integer categoryId){
		return brandDao.selectBrandbyCategoryId(categoryId);
	}
	

	/**
	 * 功能描述：根据 popStoreNum 查询 Brand
	 * @author liukai
	 * @param popStoreNum
	 * @return
	 */
	public List<Brand> selectBrandByPopStoreNum(String popStoreNum) {
		return brandDao.selectBrandByPopStoreNum(popStoreNum);
	}
	

	/**
	 * 根据关键字，地区查询对应的品牌列表
	 * @Title: selectBrandListByQuery
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月16日 下午2:06:28       
	 * @version: 
	 *
	 * @param keywords
	 * @param regionId
	 * @return
	 *
	 */
	public List<Brand> selectBrandListByQuery(String keywords,Integer regionId){
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
		
		return brandDao.selectBrandListByQuery(map);
	}
	
	
	public List<Brand> selectBrandListByIds(List<Integer> brandIds){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", brandIds);
		return brandDao.selectBrandListByIds(map);
	}

}
