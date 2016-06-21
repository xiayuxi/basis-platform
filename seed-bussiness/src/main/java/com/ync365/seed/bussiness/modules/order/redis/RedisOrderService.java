package com.ync365.seed.bussiness.modules.order.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ync365.seed.commons.redis.JedisTemplate;
/**
 *     
 * @Title：RedisOrderService
 * @Description: 封装订单在Redis中的操作
 * @author: Ken        
 * @date: 2015年9月22日 下午5:52:57      
 * @version     
 *
 */
@Component("redisOrderService")
public class RedisOrderService {
    
    // private static final Integer EXPIRE_DAY = 1;
    
    private static final Integer seconds = 24 * 60 * 60;
    
    private static final Integer order_expire_seconds = 12 * 60 * 60;
    
    /**
     * Redis模板对象
     */
    @Autowired
    private JedisTemplate jedisTemplate;
    
    /**
     * @Title: getOrderCount
     * @Description: 获取订单数量
     * @author: Ken    
     * @date: 2015年10月19日 上午10:39:07       
     * @version: 
     *
     * @param dataStr
     * @return
     *
     */
    public Long getOrderCount(String dataStr){
        String key = KeyGenerator.getOrderCountKey(dataStr);
        Long result = jedisTemplate.incr(key);
        if(result == 1){
            // jedisTemplate.expireAt(key, expireTime());
            jedisTemplate.expire(key, seconds);
        }
        return result;
    }
    
    public Long getPreparedCount(String dataStr){
        String key = KeyGenerator.getPreparedCountKey(dataStr);
        Long result = jedisTemplate.incr(key);
        if(result == 1){
            jedisTemplate.expire(key, seconds);
        }
        return result;
    } 
    
    public Long getStockCount(String dataStr){
        String key = KeyGenerator.getStockCountKey(dataStr);
        Long result = jedisTemplate.incr(key);
        if(result == 1){
            jedisTemplate.expire(key, seconds);
        }
        return result;
    } 
    
    public Long getDeliveryCount(String dataStr){
        String key = KeyGenerator.getDeliveryCountKey(dataStr);
        Long result = jedisTemplate.incr(key);
        if(result == 1){
            jedisTemplate.expire(key, seconds);
        }
        return result;
    } 
    
    public Long getCouponCount(String dataStr) {
        String key = KeyGenerator.getCouponCountKey(dataStr);
        Long result = jedisTemplate.incr(key);
        if(result == 1){
            // jedisTemplate.expireAt(key, expireTime());
            jedisTemplate.expire(key, seconds);
        }
        return result;
    }
    
    
    public void setOrderPayInfo(String orderNo,String value){
        String key = KeyGenerator.getOrderPayKey(orderNo);
        String result = jedisTemplate.getSet(key, value);
        if(result != null){
            jedisTemplate.expire(key, order_expire_seconds);
        }
    }
    
    public String getOrderPayInfo(String orderNo){
        String key = KeyGenerator.getOrderPayKey(orderNo);
        return jedisTemplate.get(key);
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
