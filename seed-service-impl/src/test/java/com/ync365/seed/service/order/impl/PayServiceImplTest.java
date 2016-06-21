package com.ync365.seed.service.order.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class PayServiceImplTest {

	 

	 @Test
	    public void ayscOrder() {
	        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/pay/asynNotifyPayOrder");
	        Map<String,Object> map = new HashMap<String,Object>();
	        map.put("trade_info", "");
	        map.put("notify_time", "20151026105659");
	        map.put("sign_type", "RSA");
	        map.put("notify_type", "");
	        map.put("trade_status", "TRADE_SUCCESS");
	        map.put("gmt_payment", "20151026105659");
	        map.put("version", "1.0");
	        map.put("sign", "RmCu8S8HuuR6lMWj7//BiUlzKNwDvUy+2Dop70/D9M6bJ80n12F0D5mPa86VFTiafj4lCQj0BXql4u21BNm59aNCRGW9i583xQPNF5ozO+lOeTdTx7vYTsoPou7SpXNJfnfLt5LTBdvcttLTHeAa6Au8CWsBnHz0+hRn5acDSag=");
	        map.put("outer_trade_no", "983f7d515f934425adfe2617f04f8cf0");
	        map.put("trade_amount", "9.70");
	        
	        
	        
	        String str = JSON.toJSONString(map);
	        try {
	            httpPost.addHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
	            HttpEntity entity = new StringEntity(str, Charset.forName("utf8"));
	            httpPost.setEntity(entity);
	            HttpResponse response = httpClient.execute(httpPost);
	            int code = response.getStatusLine().getStatusCode();
	            if (code == 200) {
	                String reString = EntityUtils.toString(response.getEntity());
	                System.out.println("response >>>>>" + reString);

	            } else {
	                Assert.fail("addbooktest===》" + code);
	            }

	        } catch (Exception e) {
	            Assert.fail("addbookTest" + e.getMessage() + e.getStackTrace());
	        } finally {
	            try {
	                httpClient.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 @Test
     public void readeSuccess() {
         CloseableHttpClient httpClient = HttpClientBuilder.create().build();
         HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/pay/asynNotifyPayOrder");
         Map<String,Object> map = new HashMap<String,Object>();
         map.put("trade_info", "");
         map.put("notify_time", "20151026105659");
         map.put("sign_type", "RSA");
         map.put("notify_type", "");
         map.put("trade_status", "TRADE_SUCCESS");
         map.put("gmt_payment", "20151026105659");
         map.put("version", "1.0");
         map.put("sign", "RmCu8S8HuuR6lMWj7//BiUlzKNwDvUy+2Dop70/D9M6bJ80n12F0D5mPa86VFTiafj4lCQj0BXql4u21BNm59aNCRGW9i583xQPNF5ozO+lOeTdTx7vYTsoPou7SpXNJfnfLt5LTBdvcttLTHeAa6Au8CWsBnHz0+hRn5acDSag=");
         map.put("outer_trade_no", "00100015102936");
         map.put("trade_amount", "9.70");
         
         
         
         String str = JSON.toJSONString(map);
         try {
             httpPost.addHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
             HttpEntity entity = new StringEntity(str, Charset.forName("utf8"));
             httpPost.setEntity(entity);
             HttpResponse response = httpClient.execute(httpPost);
             int code = response.getStatusLine().getStatusCode();
             if (code == 200) {
                 String reString = EntityUtils.toString(response.getEntity());
                 System.out.println("response >>>>>" + reString);

             } else {
                 Assert.fail("addbooktest===》" + code);
             }

         } catch (Exception e) {
             Assert.fail("addbookTest" + e.getMessage() + e.getStackTrace());
         } finally {
             try {
                 httpClient.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }

}
