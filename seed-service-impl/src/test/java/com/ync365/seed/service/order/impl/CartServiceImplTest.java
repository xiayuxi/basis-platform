package com.ync365.seed.service.order.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.ync365.seed.dto.order.input.CartOrderInputDTO;
import com.ync365.seed.dto.order.input.CartSettleInputDTO;
import com.ync365.seed.dto.order.input.StoreSettleInputDTO;

@SuppressWarnings("unused")
public class CartServiceImplTest {

    
    
    @Test
    public void test() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context-dubbox.xml","spring-context-bussiness.xml","spring-context-redis.xml");
        context.start();
        System.in.read();
    }

    
    @Test
    public void testAddCart(){
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
//            HttpPost httpPost = new HttpPost("http://192.168.87.61:8001/services/cart/addCart/0115101700000002/110100");
             HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/cart/addCart/0115101000000004/501");
            Map<String,String> param = new HashMap<String,String>();
            param.put("storeId", "1110");
            param.put("skuId", "360");
            param.put("num", "8");
            String str =  JSON.toJSONString(param);
            httpPost.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8")); // text/html
            HttpEntity entity = new StringEntity(str, Charset.forName("utf8"));
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String resp = EntityUtils.toString(response.getEntity());
                System.out.println("response : "+ resp);

            } else {
                Assert.fail("status : " + status);
            }
        } catch (Exception e) {
            Assert.fail("testAddCart:" + e.getMessage() + e.getStackTrace());
            e.printStackTrace();
        } finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void testSelCartAll(){
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            
             HttpPut httpPut = new HttpPut("http://127.0.0.1:8001/services/cart/selCartAll/10001");
//            HttpPut httpPut = new HttpPut("http://192.168.87.61:8001/services/cart/selCartAll/10001/501");
            Map<String,String> param = new HashMap<String,String>();
            param.put("isSelect", "0");
            String str =  JSON.toJSONString(param);
            httpPut.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(str, Charset.forName("utf8"));
            httpPut.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPut);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String resp = EntityUtils.toString(response.getEntity());
                System.out.println("response : "+ resp);

            } else {
                Assert.fail("status : " + status);
            }
        } catch (Exception e) {
            Assert.fail("testAddCart:" + e.getMessage() + e.getStackTrace());
            e.printStackTrace();
        } finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void testSelCartStore(){
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpPut httpPut = new HttpPut("http://127.0.0.1:8001/services/cart/selCartStore/10001/501");
            Map<String,String> param = new HashMap<String,String>();
            param.put("storeId", "99");
            param.put("isSelect", "0");
            String str =  JSON.toJSONString(param);
            httpPut.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(str, Charset.forName("utf8"));
            httpPut.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPut);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String resp = EntityUtils.toString(response.getEntity());
                System.out.println("response : "+ resp);

            } else {
                Assert.fail("status : " + status);
            }
        } catch (Exception e) {
            Assert.fail("testAddCart:" + e.getMessage() + e.getStackTrace());
            e.printStackTrace();
        } finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void testSelCartSku(){
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
//            HttpPut httpPut = new HttpPut("http://192.168.87.61:8001/services/cart/selCartSku/10001/501");
             HttpPut httpPut = new HttpPut("http://192.168.87.61:8001/services/cart/queryCart/1");
            
            Map<String,String> param = new HashMap<String,String>();
            param.put("storeId", "99");
            param.put("skuId", "1");
            param.put("isSelect", "0");
            String str =  JSON.toJSONString(param);
            httpPut.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(str, Charset.forName("utf8"));
            httpPut.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPut);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String resp = EntityUtils.toString(response.getEntity());
                System.out.println("response : "+ resp);

            } else {
                Assert.fail("status : " + status);
            }
        } catch (Exception e) {
            Assert.fail("testAddCart:" + e.getMessage() + e.getStackTrace());
            e.printStackTrace();
        } finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    @Test
    public void testQueryCart(){
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
//            HttpGet httpGet = new HttpGet("http://192.168.87.61:8001/services/cart/queryCart/0115101700000002/110100");
             HttpGet httpGet = new HttpGet("http://127.0.0.1:8001/services/cart/queryCart/0115101000000004/501");
            httpGet.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8")); // text/html
            HttpResponse response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String resp = EntityUtils.toString(response.getEntity());
                System.out.println("response : "+ resp);

            } else {
                Assert.fail("status : " + status);
            }
        } catch (Exception e) {
            Assert.fail("testAddCart:" + e.getMessage() + e.getStackTrace());
            e.printStackTrace();
        } finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    @Test
    public void testCountCart(){
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
//            HttpGet httpGet = new HttpGet("http://192.168.87.61:8001/services/cart/countCart/10001/501");
             HttpGet httpGet = new HttpGet("http://127.0.0.1:8001/services/cart/countCart/10001");
            httpGet.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8")); // text/html
            HttpResponse response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String resp = EntityUtils.toString(response.getEntity());
                System.out.println("response : "+ resp);

            } else {
                Assert.fail("status : " + status);
            }
        } catch (Exception e) {
            Assert.fail("testAddCart:" + e.getMessage() + e.getStackTrace());
            e.printStackTrace();
        } finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void testSettleCart(){
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost("http://192.168.87.61:8001/services/cart/settleCart/0115101700000002/110100");
//            HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/cart/settleCart/0115101000000004/501");
            CartSettleInputDTO cartInfo = new CartSettleInputDTO();
            
            List<Map <String,Integer>> skuInfoList = new ArrayList<Map <String,Integer>>();
            Map<String,Integer> map1 = new HashMap<String,Integer>();
            map1.put("skuId", 360);
            map1.put("num", 1000000);
            skuInfoList.add(map1);
            
            Map<String,Integer> map2 = new HashMap<String,Integer>();
            map2.put("skuId", 365);
            map2.put("num", 100);
            skuInfoList.add(map2);
            
            Map<String,Integer> map3 = new HashMap<String,Integer>();
            map3.put("skuId", 367);
            map3.put("num", 10);
            skuInfoList.add(map3);
            
            /*Map<String,Integer> map4 = new HashMap<String,Integer>();
            map4.put("skuId", 320);
            map4.put("num", 4);
            skuInfoList.add(map4);
            
            Map<String,Integer> map5 = new HashMap<String,Integer>();
            map5.put("skuId", 322);
            map5.put("num", 1);
            skuInfoList.add(map5);
            
            Map<String,Integer> map6 = new HashMap<String,Integer>();
            map6.put("skuId", 325);
            map6.put("num", 4);
            skuInfoList.add(map6);*/
            
            List<Map <String,Integer>> skuInfoList1 = new ArrayList<Map <String,Integer>>();
            Map<String,Integer> map8 = new HashMap<String,Integer>();
            map8.put("skuId", 360);
            map8.put("num", 8);
            skuInfoList1.add(map8);
            
            StoreSettleInputDTO storeInfo1 = new StoreSettleInputDTO();
            storeInfo1.setStoreId(-1);
            storeInfo1.setSkuInfoList(skuInfoList1);
            
            
            StoreSettleInputDTO storeInfo = new StoreSettleInputDTO();
            storeInfo.setStoreId(1110);
            storeInfo.setSkuInfoList(skuInfoList);
            List<StoreSettleInputDTO> storeInfoList = new ArrayList<StoreSettleInputDTO>();
            storeInfoList.add(storeInfo);
            storeInfoList.add(storeInfo1);
            cartInfo.setStoreInfoList(storeInfoList);
            
            String str =  JSON.toJSONString(cartInfo);
            System.out.println("str---" + str);
            httpPost.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8")); // text/html
            HttpEntity entity = new StringEntity(str, Charset.forName("utf8"));
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String resp = EntityUtils.toString(response.getEntity());
                System.out.println("response : "+ resp);

            } else {
                Assert.fail("status : " + status);
            }
        } catch (Exception e) {
            Assert.fail("testAddCart:" + e.getMessage() + e.getStackTrace());
            e.printStackTrace();
        } finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void testOrderCart(){
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            // HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/cart/orderCart");
            HttpPost httpPost = new HttpPost("http://192.168.87.61:8001/services/cart/orderCart");
           
            
            CartSettleInputDTO cartInfo = new CartSettleInputDTO();
            List <StoreSettleInputDTO> storeInfoList = new ArrayList<StoreSettleInputDTO>();
            StoreSettleInputDTO store1 = new StoreSettleInputDTO();
            
            store1.setStoreId(1115);
            List<Map <String,Integer>> skuInfoList1 = new ArrayList<Map <String,Integer>>();
            Map <String,Integer> m1 = new HashMap<String,Integer>();
            m1.put("skuId", 380);
            m1.put("num", 1);
            skuInfoList1.add(m1);
            store1.setSkuInfoList(skuInfoList1);
            storeInfoList.add(store1);
            
            StoreSettleInputDTO store2 = new StoreSettleInputDTO();
            store2.setStoreId(1110);
            List<Map <String,Integer>> skuInfoList2 = new ArrayList<Map <String,Integer>>();
            Map <String,Integer> m2 = new HashMap<String,Integer>();
            m2.put("skuId", 374);
            m2.put("num", 2);
            skuInfoList2.add(m2);
            
            Map <String,Integer> m3 = new HashMap<String,Integer>();
            m3.put("skuId", 360);
            m3.put("num", 10);
            skuInfoList2.add(m3);
            
            store2.setSkuInfoList(skuInfoList2);
            storeInfoList.add(store2);
            
            cartInfo.setStoreInfoList(storeInfoList);
            
            CartOrderInputDTO info = new CartOrderInputDTO();
            info.setCartInfo(cartInfo);
            info.setEngineerNum("se1");
            info.setCityId(110100);
            info.setPayType(1);
            info.setUserNum("0115101700000002");
            
            
            
            String str =  JSON.toJSONString(info);
            System.out.println("str-----------------------" + str);
            httpPost.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8")); // text/html
            HttpEntity entity = new StringEntity(str, Charset.forName("utf8"));
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String resp = EntityUtils.toString(response.getEntity());
                System.out.println("response : "+ resp);

            } else {
                Assert.fail("status : " + status);
            }
        } catch (Exception e) {
            Assert.fail("testAddCart:" + e.getMessage() + e.getStackTrace());
            e.printStackTrace();
        } finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   

}
