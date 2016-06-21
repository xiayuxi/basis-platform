package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.entity.SaleRegion;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface SaleRegionDao {
    int deleteByPrimaryKey(Integer saleRegionId);
    
    int deleteBySkuId(Integer skuId);

    int insert(SaleRegion record);
    
    int insertSelective(SaleRegion record);

    SaleRegion selectByPrimaryKey(Integer saleRegionId);

    int updateByPrimaryKeySelective(SaleRegion record);

    int updateByPrimaryKey(SaleRegion record);
    
    List<SaleRegion> getSaleRegionsBySkuId(Integer skuId );
    
    SaleRegion getSaleRegionsBySkuIdAndRegionId(Map<String,Object> map);
    
    /**
     * 根据skuId集合，地区查询对应的销售区域价格列表
     * @Title: getSaleRegionBySkuIdsAndRegion
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月12日 下午3:25:28       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<SaleRegion> getSaleRegionBySkuIdsAndRegion(Map<String, Object> map);
    
    /**
     * 前台【php】 查询商品对应的销售区域
     * @Title: selectSaleRegionListFront
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月24日 下午4:35:43       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List<SaleRegion> selectSaleRegionListFront(Map<String,Object> map);
    
    
}