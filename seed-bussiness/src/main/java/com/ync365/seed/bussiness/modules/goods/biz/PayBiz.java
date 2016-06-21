/**    
 * 文件名：PayBiz.java    
 *    
 * 版本信息：    
 * 日期：2015年11月6日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.bussiness.modules.goods.biz;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ync365.seed.commons.rsa.RSAEncrypt;
import com.ync365.seed.commons.rsa.RSASignature;
import com.ync365.seed.utils.Configuration;
import com.ync365.seed.utils.HttpClientUtil;
import com.ync365.seed.utils.UUIDGenerator;

/**    
 *     
 * @Title：PayBiz  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年11月6日 下午2:31:32      
 * @version     
 *     
 */
@Service

public class PayBiz {
    
    private static final Logger logger = LoggerFactory.getLogger(PayBiz.class);
    
    @Autowired
    private Configuration configuration;
    
    @Value("${ynb_pay_url}")
    private String ynbPayUrl;
    @Value("${ynb_partner_id}")
    private String ynbPartnerId;
    
    @Async
    public Boolean cancelOrderSynchronousYnb (String ordersSn,String cancelReason)  {
      //请求基本信息
        Map<String,Object> parameters = new TreeMap<String, Object>(new Comparator <String> (){
            @Override
            public int compare(String obj1,String obj2) {
                // 降序排序
                return obj1.compareTo(obj2);
            }
        });
        parameters.put("service", "cancel_trade");//接口名称
        parameters.put("version", "1.0");//接口版本
        parameters.put("partner_id", ynbPartnerId);//合作者身份ID
        parameters.put("_input_charset", "UTF-8");
        parameters.put("memo", "C网订单取消");//备注
        parameters.put("return_url", "#");//备注
        //交易取消信息
        parameters.put("request_no", UUIDGenerator.getUUID());
        parameters.put("outer_trade_no", ordersSn);
        parameters.put("reason", cancelReason);     //订单取消原因
        Set<Entry<String, Object>> set = parameters.entrySet();
        Iterator<Entry<String, Object>> it = set.iterator();
        System.out.println(configuration.getPrivatekeyStore());
        System.out.println(ynbPayUrl);
        System.out.println(ynbPartnerId);
        
        StringBuffer strbody = new StringBuffer();
        while(it.hasNext()){
            Entry<String, Object> str = it.next();  
            strbody.append(str.toString());
            strbody.append("&");
        }
        strbody.deleteCharAt(strbody.length()-1); 
        String sign = null;
        try {
            sign = RSASignature.sign(strbody.toString(),RSAEncrypt.loadPrivateKeyByFile(configuration.getPrivatekeyStore()));
        } catch (Exception e) {
            logger.error("###########【RSA加密失败】："+e.getMessage(),e);
        }
        parameters.put("sign", sign);
        parameters.put("sign_type", "RSA");
        List<NameValuePair> parmList = new ArrayList<NameValuePair>();  
        Iterator<Entry<String, Object>> iterator = parameters.entrySet().iterator();  
        while(iterator.hasNext()){  
            Entry<String,Object> elem = (Entry<String, Object>) iterator.next();  
            try {
                parmList.add(new BasicNameValuePair(elem.getKey(),URLEncoder.encode(elem.getValue().toString(),"UTF-8")));
            } catch (UnsupportedEncodingException e) {
                logger.error("拼接取消订单参数出错"+e.getMessage(),e);
            }
        }  
        logger.info("##########【云农宝取消订单请求参数】############："+ parmList);  
        String returnBody = HttpClientUtil.post(ynbPayUrl,parmList);
        logger.info("##########【云农宝取消订单返回参数】############："+ returnBody);
        ObjectMapper mapper = new ObjectMapper();  
        Map<String, Object> map;
        try {
            map = mapper.readValue(returnBody,Map.class);
            return map.get("is_success").toString().equals("T");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    
}
