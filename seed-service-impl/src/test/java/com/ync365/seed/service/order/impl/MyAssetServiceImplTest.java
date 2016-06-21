package com.ync365.seed.service.order.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class MyAssetServiceImplTest {
    String locIpHost  =    "192.168.87.61:8001"; 
//  String locIpHost  = "192.168.87.61:8001";    "127.0.0.1:8001";
    @Test
    public void testGetMyHoldGold(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://"
                + locIpHost+"/services/myAsset/getMyHoldGold/0000123");
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("beginTime", "1437926400");
        jsonMap.put("endTime", "1445875200");
        jsonMap.put("startIndex", "0");
        jsonMap.put("pageSize","10");
        String jsonStr = JSON.toJSONString(jsonMap);
        
        try {
            httpPost.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(jsonStr, Charset.forName("utf8"));
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                System.out.println("response >>>>>testGetMyHoldGold>>>>>>>>>>>>>>>\n"+reString);

            } else {
                Assert.fail("addbooktest===》" + status);
            }

        } catch (Exception e) {
            Assert.fail("testDelCart:" + e.getMessage() + e.getStackTrace());
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testGetMyCoupon(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://"
                + locIpHost+"/services/myAsset/getMyCoupon/0115100900000001");
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("CouponStatus", "2");
        String jsonStr = JSON.toJSONString(jsonMap);
        
        try {
            httpPost.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(jsonStr, Charset.forName("utf8"));
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                System.out.println("response >>>>>testGetMyCoupon>>>>>>>>>>>>>>>\n"+reString);

            } else {
                Assert.fail("addbooktest===》" + status);
            }

        } catch (Exception e) {
            Assert.fail("testDelCart:" + e.getMessage() + e.getStackTrace());
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testGetMyBalance(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://"
                + locIpHost+"/services/myAsset/getMyBalance/1");
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("balanceType ", "1");
        String jsonStr = JSON.toJSONString(jsonMap);
        
        try {
            httpPost.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(jsonStr, Charset.forName("utf8"));
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                System.out.println("response >>>>>testGetMyBalance>>>>>>>>>>>>>>>\n"+reString);

            } else {
                Assert.fail("addbooktest===》" + status);
            }

        } catch (Exception e) {
            Assert.fail("testDelCart:" + e.getMessage() + e.getStackTrace());
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }}
