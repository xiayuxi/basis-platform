/**    
 * 文件名：CouponMapperManual.java    
 *    
 * 版本信息：    
 * 日期：2015年10月30日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.promotion.dao;

import com.ync365.seed.bussiness.modules.promotion.entity.Coupon;
import com.ync365.seed.commons.annotation.MyBatisRepository;

/**    
 *     
 * @Title：CouponMapperManual  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月30日 下午2:38:50      
 * @version     
 *     
 */
@MyBatisRepository
public interface CouponMapperManual {
    
    Coupon selectByPromotionId(int promotionId);
    
}
