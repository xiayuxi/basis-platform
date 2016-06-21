/**    
 * 文件名：promotionCouponMapperManual.java    
 *    
 * 版本信息：    
 * 日期：2015年10月30日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.promotion.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.promotion.entity.PromotionCoupon;
import com.ync365.seed.commons.annotation.MyBatisRepository;

/**    
 *     
 * @Title：promotionCouponMapperManual  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月30日 下午4:24:36      
 * @version     
 *     
 */
@MyBatisRepository
public interface PromotionCouponMapperManual {
    /**
     * 根据红包类型获取红包列表
     * @Title: getPromotionCouponByCouponType
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年10月31日 下午3:07:04       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    List <PromotionCoupon> getPromotionCouponByCouponType(Map <String ,Object> map) ;
    /**
     * 根据活动ID获取活动红包信息
     * @Title: getPromotionCouponByPromotionId
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年10月31日 下午3:08:05       
     * @version: 
     *
     * @param map
     * @return
     *
     */
    PromotionCoupon getPromotionCouponByPromotionId(Map<String,Object>map);
}
