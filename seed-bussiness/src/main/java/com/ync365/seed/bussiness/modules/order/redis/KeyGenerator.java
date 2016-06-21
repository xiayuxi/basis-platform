package com.ync365.seed.bussiness.modules.order.redis;

import org.apache.commons.lang3.StringUtils;

public class KeyGenerator {

    private final static String split = ":";

    private final static String simple = "simple";
    
    // private final static String hash = "hash";

    // private final static String list = "list";

    // private final static String set = "set";
    
    // private final static String sortset = "sortset";

    private final static String order = "order";
    
    private final static String cart = "cart";
    
    private final static String ordernum = "ordernum";
    
    private final static String orderpay = "orderpay";
    
    private final static String preparednum = "preparednum";
    
    private final static String stocknum = "stocknum";
    
    private final static String deliverynum = "deliverynum";
    
    private final static String coupon = "coupon";
    
    private final static String promotion = "promotion";
    

    private static String getRedisKey(String... name) {
        StringBuffer buffer = new StringBuffer();
        for (String str : name) {
            if (StringUtils.isNotBlank(str)) {
                buffer.append(str);
                buffer.append(split);
            }
        }
        buffer.deleteCharAt(buffer.lastIndexOf(split));
        return buffer.toString();
    }
    
    public static String getCartKey(String userNum) {
        return getRedisKey(simple, order, cart,userNum);
    }
    
    public static String getOrderPayKey(String dataStr) {
        return getRedisKey(simple, order, orderpay,dataStr);
    }
    
    public static String getOrderCountKey(String dataStr) {
        return getRedisKey(simple, order, ordernum,dataStr);
    }
    
    public static String getPreparedCountKey(String dataStr) {
        return getRedisKey(simple, order, preparednum,dataStr);
    }
    
    public static String getStockCountKey(String dataStr) {
        return getRedisKey(simple, order, stocknum,dataStr);
    }
    
    public static String getDeliveryCountKey(String dataStr) {
        return getRedisKey(simple, order, deliverynum,dataStr);
    }
    
    public static String getCouponCountKey(String dataStr) {
        return getRedisKey(simple, promotion , coupon ,dataStr);
    }

}
