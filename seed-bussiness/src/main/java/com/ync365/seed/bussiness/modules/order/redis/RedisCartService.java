package com.ync365.seed.bussiness.modules.order.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ync365.seed.bussiness.modules.order.bo.CartBO;
import com.ync365.seed.commons.mapper.JsonMapper;
import com.ync365.seed.commons.redis.JedisTemplate;
/**
 *     
 * @Title：RedisCartService  
 * @Description: 封装购物车在Redis中的操作
 * @author: Ken        
 * @date: 2015年9月22日 下午5:52:57      
 * @version     
 *
 */
@Component("redisCartService")
public class RedisCartService {
    
    // private static final Integer EXPIRE_DAY = 30;
    private static final Integer seconds = 30 * 24 * 60 * 60 ;
    
    /**
     * Redis模板对象
     */
    @Autowired
    private JedisTemplate jedisTemplate;
    
    /**
     * 
     * @Title: addCart
     * @Description: 添加到购物车  
     * @author: Ken    
     * @date: 2015年9月28日 下午2:36:06       
     * @version: 
     *
     * @param cartBO
     *
     */
    public void addCart(CartBO cartBO){
        String key = KeyGenerator.getCartKey(cartBO.getUserNum());
        JsonMapper mapper = new JsonMapper() ;
        jedisTemplate.set(key, mapper.toJson(cartBO));
        // jedisTemplate.expireAt(key, expireTime());
        jedisTemplate.expire(key, seconds);
    }
    
    /**
     * 
     * @Title: getCart
     * @Description: 获取购物车    
     * @author: Ken    
     * @date: 2015年9月28日 下午2:35:49       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    public CartBO getCart(String userNum){
        String key = KeyGenerator.getCartKey(userNum);
        String jsonStr = jedisTemplate.get(key);
        if(StringUtils.isBlank(jsonStr)){
            return null;
        }
        JsonMapper mapper = new JsonMapper() ;
        CartBO cartPO = mapper.fromJson(jsonStr, CartBO.class);
        return cartPO;
    }
    
    /**
     * 
     * @Title: delCart
     * @Description: 删除购物车  
     * @author: Ken    
     * @date: 2015年9月28日 下午2:35:35       
     * @version: 
     *
     * @param userNum
     * @return
     *
     */
    public Boolean delCart(String userNum){
        String key = KeyGenerator.getCartKey(userNum);
        return jedisTemplate.del(key);
    } 
    
    /**
     * 
     * @Title: expireTime
     * @Description: 计算Key的失效时间 
     * @author: Ken    
     * @date: 2015年9月28日 下午2:35:17       
     * @version: 
     *
     * @return
     *
     */
    /*private Long expireTime(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, EXPIRE_DAY);
        return cal.getTimeInMillis();
    }*/
}
