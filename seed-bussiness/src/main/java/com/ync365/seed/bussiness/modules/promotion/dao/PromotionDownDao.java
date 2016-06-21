package com.ync365.seed.bussiness.modules.promotion.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.commons.annotation.MyBatisRepository;


@MyBatisRepository
public interface PromotionDownDao {
	
    int deleteByPrimaryKey(Integer promotionId);

    int insert(Promotion record);

    int insertSelective(Promotion record);

    Promotion selectByPrimaryKey(Integer promotionId);

    int updateByPrimaryKeySelective(Promotion record);

    int updateByPrimaryKey(Promotion record);
    

    /**
     * 分页按活动类型查找
     */
    public List<Promotion> searchPageByPromotionType(Map<String,Object> map);
    
    /**
     * 按活动分类查询活动总数
     */
    public int searchPageCount(Map<String,Object> map);
    /**
     * 根据活动ID删除活动
     */
    public void deleteById(int id);
    /**
     * 根据ID获取活动信息
     */
    public Promotion selectById(int id);

}