/**    
 * 文件名：UserExpenseBiz.java    
 *    
 * 版本信息：    
 * 日期：2015年10月27日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.order.biz;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.order.dao.UserExpenseLogMapper;
import com.ync365.seed.bussiness.modules.order.dao.UserExpenseMapper;
import com.ync365.seed.bussiness.modules.order.dao.UserExpenseMapperManual;
import com.ync365.seed.bussiness.modules.order.entity.UserExpense;
import com.ync365.seed.bussiness.modules.order.entity.UserExpenseLog;

/**    
 *     
 * @Title：UserExpenseBiz  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年10月27日 下午10:31:54      
 * @version     
 *     
 */

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class UserExpenseBiz {

    @Autowired
    private UserExpenseMapperManual userExpenseMapperManual;
    
    @Autowired
    private UserExpenseLogMapper userExpenseLogMapper;
    
    @Autowired
    private UserExpenseMapper userExpenseMapper;
    
    public List<UserExpense> getUserExpenseListPageByUserNum(Map<String,Object>map) {
        return userExpenseMapperManual.getUserExpenseListPageByUserNum(map);
    }
    
    public Long getUserExpenseCountPageByUserNum(Map<String,Object>map) {
        return userExpenseMapperManual.getUserExpenseCountPageByUserNum(map);
    }
    
    public BigDecimal getSumExpenseByStatus(String userNum,Integer status){
        Map<String ,Object> map = new HashMap<String , Object>();
        map.put("userNum", userNum);
        map.put("status", status);
        return userExpenseMapperManual.getSumExpenseByStatus(map);
    }
    public List<UserExpense> selectUserExpenseStatusByOrderId (Integer orderId ,Integer status,Integer orderType) {
        Map<String ,Object> map = new HashMap<String,Object>();
        map.put("orderId", orderId);
        map.put("status", status);
        map.put("orderType", orderType);
        return userExpenseMapperManual.selectUserExpenseStatusByOrderId(map);
    }
    public Integer updateByPrimaryKey(UserExpense useExpense) {
        return userExpenseMapper.updateByPrimaryKeySelective(useExpense);
    }
    public Integer insertUseExpenseLog(UserExpenseLog userExpenseLog) {
        return userExpenseLogMapper.insert(userExpenseLog);
    }
}
