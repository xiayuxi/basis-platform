/**    
 * 文件名：PromotionGoodsSkuMapperManual.java    
 *    
 * 版本信息：    
 * 日期：2015年10月27日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.promotion.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGoodsSku;
import com.ync365.seed.commons.annotation.MyBatisRepository;

/**    
 *     
 * @Title：PromotionGoodsSkuMapperManual  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月27日 下午8:11:07      
 * @version     
 *     
 */
@MyBatisRepository
public interface PromotionGoodsSkuMapperManual {

    
    /**
     * 
     * @Title: searchByPromotionType
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年9月21日 下午5:37:30       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public List<PromotionGoodsSku> searchByPromotionId(Map<String,Object> map);
    /**
     * 
     * @Title: searchPageByPromotionType
     * @Description: TODO    ：    分页查找
     * @author: sunyf    
     * @date: 2015年9月21日 下午5:37:30       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public List<PromotionGoodsSku> searchPageByPromotionId(Map<String,Object> map);
    /**
     * 
     * @Title: searchPageCount
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年9月21日 下午5:58:36       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public int searchPageCount(Map<String,Object> map);
    /**
     * 
     * @Title: updateIsdeleteByPromotionId
     * @Description: TODO    ：    根据promotionid 将 活动商品表中的全部记录置为已删除
     * @author: sunyf    
     * @date: 2015年9月26日 下午6:33:33       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public int updateIsdeleteByPromotionId(Map<String,Object> map);
    public void insertPromotionGoodsSkuBatch(List <PromotionGoodsSku> list);
    /**
     * 
     * @Title: searchIsPromotionSku
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年10月1日 上午10:31:09       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public List<String> searchIsPromotionSku(Map<String,Object> map);
    /**
     * 
     * @Title: selectByPromotionIdAndSkuId
     * @Description: TODO    ：    根据promotionId、skuId返回商品信息
     * @author: sunyf    
     * @date: 2015年10月1日 下午3:35:12       
     * @version: 
     *
     * @param map <promotionId、skuId>
     * @return
     *
     */
    public PromotionGoodsSku selectByPromotionIdAndSkuId (Map<String,Object> map);
    /**
     * 
     * @Title: updatePromotionGoodIsDel
     * @Description: TODO    ：    根据主键更新isDelete值
     * @author: sunyf    
     * @date: 2015年10月1日 下午3:30:51       
     * @version: 
     *
     * @param map <isDelte,promotionGoodsSkuId>
     * @return
     *
     */
    public int updatePromotionGoodIsDel(Map<String,Object> map);
    /**
     * 
     * @Title: updateHoldGold
     * @Description: TODO    ：    更新佣金信息
     * @author: sunyf    
     * @date: 2015年10月1日 下午4:38:12       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public int updateHoldGold(Map<String,Object> map);
    /**
     * 
     * @Title: searchIsPromotionSkuByDate
     * @Description: TODO    ：    根据日期查询在日期范围内参加活动的商品
     * @author: sunyf    
     * @date: 2015年10月2日 下午1:17:10       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public List<PromotionGoodsSku> searchIsPromotionSkuByDate(Map<String,Object> map);
    
    /**
     * 
     * @Title: findPromGoodsSkusBySkuIds
     * @Description: 查询活动商品是否在指定活动中 
     * @author: Ken    
     * @date: 2015年10月29日 下午7:02:49       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    public List<PromotionGoodsSku> findPromGoodsSkusBySkuIds(Map<String,Object> map);

}
