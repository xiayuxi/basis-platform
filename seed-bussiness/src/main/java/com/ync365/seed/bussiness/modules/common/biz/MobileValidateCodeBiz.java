package com.ync365.seed.bussiness.modules.common.biz;


import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ync365.seed.commons.redis.JedisTemplate;
import com.ync365.seed.commons.sms.SMSHelper;
import com.ync365.seed.utils.RandomUtil;

@Service
public class MobileValidateCodeBiz {
    @Autowired
    private JedisTemplate jedisTemplate;
    private Integer expireSeconds = 60 * 60;
    private String pre = "mobile_validateCode_";
    @Value("${validateCodeSmsTemplate}")
    private String validateCodeSmsTemplate;
    @Value("${sendGoodsRemindMsg}")
    private String sendGoodsRemindMsg;
    @Autowired
    private SMSHelper smsHelper;
    @Value("${isSendSms}")
    private Boolean isSendSms;
    private Logger log = LoggerFactory.getLogger(MobileValidateCodeBiz.class);

    // 您注册云农场网站的验证码：%%(如非本人操作请忽略本短信！)【云农场】
    /**
     * 获取手机验证码
     * @Title: getMobileValidateCode
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月8日 下午4:54:27       
     * @version: 
     *
     * @param mobile
     * @return 手机验证码
     *
     */
    public String getMobileValidateCode(String mobile) {
        String key = pre + mobile;
        List<String> list = jedisTemplate.lrange(key, 0, 5);
        String code = null;

        if (list != null && list.size() > 0) {
            String[] strs = new String[list.size()];
            list.toArray(strs);
            code = strs[0].split(",")[0];
        }
        return code;
    }

    /**
     * 验证手机验证码
     * @Title: validateMobileValidateCode
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月8日 下午4:55:07       
     * @version: 
     *
     * @param mobile
     * @param validateCode
     * @return 正确返回true
     *
     */
    public Boolean validateMobileValidateCode(String mobile, String validateCode) {
        boolean result = false;
        String key = pre + mobile;
        List<String> list = jedisTemplate.lrange(key, 0, 5);
        String code = null;
        if (list != null && list.size() > 0) {
            String[] strs = new String[list.size()];
            list.toArray(strs);
            code = strs[0].split(",")[0];
            long expiretime = Long.valueOf(strs[0].split(",")[1]);
            Calendar ca = Calendar.getInstance();
            if (ca.getTimeInMillis() <= expiretime && validateCode.equals(code)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 给入驻商发信息提醒发货
     * @Title: sendValidateCode
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月8日 下午4:54:40       
     * @version: 
     *
     * @param mobile,storeId   
     *
     */
    public String sendGoodsRemindMsg(String storeMobile,String storeName,String userName,String orderNo,String payTime) {
    	
        String msgContent = storeName+"商家你好，用户"+userName+"于"+payTime+"订单号为："+orderNo+"已支付完毕。请尽快发货！";
        String key = pre + storeMobile;
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MINUTE, 30);
        jedisTemplate.lpush(key, msgContent + "," + ca.getTimeInMillis());
        jedisTemplate.expire(key, expireSeconds);
        if (isSendSms) {            
        	String content = sendGoodsRemindMsg.replace("storeName", storeName);
        	content = sendGoodsRemindMsg.replace("userName", userName);
        	content = sendGoodsRemindMsg.replace("payTime", payTime); 
        	content = sendGoodsRemindMsg.replace("orderNo", orderNo);       	
        	
            String rs = smsHelper.sendSms(storeMobile, content);
            log.info("发送短信提醒入驻商发货,mobile:{},sendContent:{},return:{}", storeMobile, content, rs);
        }
        return msgContent;
    }
    /**
     * 发送手机验证码
     * @Title: sendValidateCode
     * @Description: TODO    ：    
     * @author: duan.zhao.qian
     * @date: 2015年10月8日 下午4:54:40       
     * @version: 
     *
     * @param mobile
     * @return 手机验证码
     *
     */
    public String sendValidateCode(String mobile) {
        String code = RandomUtil.createRandom(true, 6);
        String key = pre + mobile;
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MINUTE, 30);
        jedisTemplate.lpush(key, code + "," + ca.getTimeInMillis());
        jedisTemplate.expire(key, expireSeconds);
        if (isSendSms) {
            String content = validateCodeSmsTemplate.replaceAll("%%", code);
            String rs = smsHelper.sendSms(mobile, content);
            log.info("发送手机验证码,mobile:{},sendContent:{},return:{}", mobile, content, rs);
        }
        return code;
    }
}
