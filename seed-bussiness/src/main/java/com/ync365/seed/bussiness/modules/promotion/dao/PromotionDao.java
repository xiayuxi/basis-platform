package com.ync365.seed.bussiness.modules.promotion.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGoodsSku;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionSearch;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface PromotionDao {

	int deleteByPrimaryKey(Integer promotionId);

	int insert(Promotion record);

	int insertSelective(Promotion record);

	Promotion selectByPrimaryKey(Integer promotionId);

	int updateByPrimaryKeySelective(Promotion record);

	int updateByPrimaryKey(Promotion record);

	/** 根据活动id，进行逻辑删除 */
	int delByIdLogic(Integer id);

	/**
	 * 分页按活动类型查找
	 */
	public List<Promotion> searchPageByPromotionType(Map<String, Object> map);

	/**
	 * 按活动分类查询活动总数
	 */
	public int searchPageCount(Map<String, Object> map);
	/**
	 * 根据活动ID删除活动
	 */
	public void deleteById(int id);
	/**
	 * 根据ID获取活动信息
	 */
	public Promotion selectById(int id);

	/**
	 * 多条件，分页查询活动列表 wangxt
	 * @param pageSize 
	 * @param startIndex 
	 * @promotionType 活动类型
	 * @key 活动名称 查询关键字
	 * @status 活动状态 ，默认为-1:不限
	 * @isDel 是否删除，默认为false:未删除
	 * */
	public List<Promotion> getListByMulti(Map<String, Object> map);
	/** 多条件查询活动总数  wangxt*/
	public int getCountByMulti(PromotionSearch search);
	/**
	 * 
	 * @Title: updatePromotionVersion
	 * @Description: TODO    ：    根据promotionId Version 更新新的Version
	 * @author: sunyf    
	 * @date: 2015年10月1日 下午12:49:58       
	 * @version: 
	 *
	 * @param map
	 * @return
	 *
	 */
	public int updatePromotionVersion(Map<String, Object> map);
	/**
     * 查询活动结束时间小于系统当前时间的活动列表
     * @param map
     * @return
     */
	public List<Promotion> selectListByEndTime(Map<String, Object> map);

	List<Promotion> getListByMulti(PromotionSearch search,
			Map<String, Object> map);
	
}