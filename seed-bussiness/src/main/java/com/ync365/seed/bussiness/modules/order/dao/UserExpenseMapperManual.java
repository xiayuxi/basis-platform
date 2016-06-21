/**    
 * 文件名：UserExpenseMapperManual.java    
 *    
 * 版本信息：    
 * 日期：2015年10月27日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.order.entity.UserExpense;
import com.ync365.seed.commons.annotation.MyBatisRepository;

/**    
 *     
 * @Title：UserExpenseMapperManual  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月27日 下午10:20:33      
 * @version     
 *     
 */
@MyBatisRepository
public interface UserExpenseMapperManual {
    
    List<UserExpense> getUserExpenseListPageByUserNum(Map<String,Object>map);
    
    BigDecimal getSumExpenseByStatus(Map<String,Object>map);
    
    List<UserExpense> selectUserExpenseStatusByOrderId (Map<String,Object>map);
    
    Long getUserExpenseCountPageByUserNum(Map<String,Object>map);
}
