package com.ync365.seed.service.order.impl;

import java.io.IOException;
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

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ync365.seed.commons.mapper.JsonMapper;
import com.ync365.seed.dto.order.input.PreparedPayInputDTO;
import com.ync365.seed.utils.Constants;

public class OrderServiceImplTest {
    String locIpHost  =  "127.0.0.1:8001";
//    String locIpHost  =    "127.0.0.1:8001";"192.168.87.61:8001"; 
	@Test
	public void testGetOrderList(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://"
        		+ locIpHost+"/services/order/getOrderList/1115102700000011");
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("status", "");
        jsonMap.put("startIndex", "0");
        jsonMap.put("pageSize","10");
        jsonMap.put("searchInfo", "0030001511034");
        jsonMap.put("beginTime", "1438012800");
        jsonMap.put("endTime", "1545961600");
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
                System.out.println("response >>>>>testGetOrderList>>>>>>>>>>>>>>>\n"+reString);

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
    public void testGetManageOrderList(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://"
        		+ locIpHost+"/services/order/getManageOrderList/1115102400000002");
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("startIndex", "0");
        jsonMap.put("pageSize","15");
        jsonMap.put("userType", "4");
      //  jsonMap.put("userTel", "13888888888");
      //  jsonMap.put("userName", "us2用户名");
       // jsonMap.put("vsName", "vs2");
       // jsonMap.put("vsTel", "13811111111");
      //  jsonMap.put("orderNo", "456003");
      //  jsonMap.put("payWay", "1");
      //  jsonMap.put("orderSource", "0");
        jsonMap.put("beginTime", "");
        jsonMap.put("endTime", "");
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
                System.out.println("response >>>>>testGetManageOrderList>>>>>>>>>>>>>>>>>>>>>>\n"+reString);

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
    public void testMergePay(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://"
        		+ locIpHost+"/services/order/mergePay/vs2/3?token_bf171336-dcb4-418c-b059-f31cd296f7f1");

        List<String> list = new ArrayList<String>();
        list.add("9995");
        list.add("9996");
        list.add("9997");
        String jsonStr = JSON.toJSONString(list);
        
        try {
            httpPost.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(jsonStr, Charset.forName("utf8"));
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                System.out.println("response >>>>>testMergePay>>>>>>>>>>>>>>\n"+reString);

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
    public void testCancelOrder(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut("http://"
        		+ locIpHost+"/services/order/cancelOrder/us2");
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("orderNo", "9994");
        jsonMap.put("orderType", "0");
        jsonMap.put("orderId", "9994");
        String jsonStr = JSON.toJSONString(jsonMap);
        
        try {
            httpPut.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(jsonStr, Charset.forName("utf8"));
            httpPut.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPut);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                JsonMapper mapper = new JsonMapper();
                Map obj = mapper.fromJson(reString, Map.class);
                if((Integer)obj.get("code")==502) {
                    Assert.fail("502error===》" + status);
                }
                System.out.println("response >>>>>testCancelOrder>>>>>>>>>>>>>>>\n"+reString);
                

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
    public void testdelOrder(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut("http://"
        		+ locIpHost+"/services/order/delOrder/us2");
        
        Map<String,String> jsonMap = new HashMap<String,String>();
//        jsonMap.put("orderId", "9991");
//        jsonMap.put("orderNo", "345000");
//        jsonMap.put("orderType", "0");
        jsonMap.put("orderId", "9991");
        jsonMap.put("orderNo", "456000");
        jsonMap.put("orderType", "0");

        String jsonStr = JSON.toJSONString(jsonMap);
        System.out.println("jsonStr >>>>>testdelRecycle>>>>>>>>>>>>>>>\n"+jsonStr);

        try {
            httpPut.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(jsonStr, Charset.forName("utf8"));
            httpPut.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPut);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                System.out.println("response >>>>>testdelRecycle>>>>>>>>>>>>>>>\n"+reString);

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
    public void testdelRecycle(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut("http://"
        		+ locIpHost+"/services/order/delRecycleList/us2");
        
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("orderId", "9991");
        jsonMap.put("orderNo", "345000");
        jsonMap.put("orderType", "0");
        Map<String,String> jsonMap1 = new HashMap<String,String>();
        jsonMap1.put("orderId", "9992");
        jsonMap1.put("orderNo", "345001");
        jsonMap1.put("orderType", "0");

        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        list.add(jsonMap);
        list.add(jsonMap1);
        String jsonStr = JSON.toJSONString(list);
        System.out.println("jsonStr >>>>>testdelRecycle>>>>>>>>>>>>>>>\n"+jsonStr);

        try {
            httpPut.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(jsonStr, Charset.forName("utf8"));
            httpPut.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPut);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                System.out.println("response >>>>>testdelRecycle>>>>>>>>>>>>>>>\n"+reString);

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
    public void testrestoreRecycle(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut("http://"
        		+ locIpHost+"/services/order/restoreRecycleList/1");
        
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("orderId", "9991");
        jsonMap.put("orderNo", "345000");
        jsonMap.put("orderType", "0");
        Map<String,String> jsonMap1 = new HashMap<String,String>();
        jsonMap1.put("orderId", "9992");
        jsonMap1.put("orderNo", "345001");
        jsonMap1.put("orderType", "0");

        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        list.add(jsonMap);
        list.add(jsonMap1);
        String jsonStr = JSON.toJSONString(list);
        System.out.println("jsonStr >>>>>testrestoreRecycle>>>>>>>>>>>>>>>\n"+jsonStr);

        try {
            httpPut.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(jsonStr, Charset.forName("utf8"));
            httpPut.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPut);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                System.out.println("response >>>>>testrestoreRecycle>>>>>>>>>>>>>>>\n"+reString);

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
    public void testOrderDetail(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://"
        		+ locIpHost+"/services/order/getOrderDetail/0115101700000001/10117/0");
        

        try {
            httpGet.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpResponse response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                System.out.println("response >>>>>testrestoreRecycle>>>>>>>>>>>>>>>\n"+reString);

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
    public void testSplitOrder(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://"
        		+ locIpHost+"/services/order/getSplitOrderDetail/us2/9993");
        

        try {
            httpGet.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpResponse response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                System.out.println("response >>>>>testSplitOrder>>>>>>>>>>>>>>>\n"+reString);

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
    public void testBuyOrderAgain(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://"
        		+locIpHost+"/services/order/buyOrderAgain/0115101700000002");
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("orderId", "10086");
        jsonMap.put("orderType", "0");
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
                System.out.println("response >>>>>testBuyOrderAgain>>>>>>>>>>>>>>>\n"+reString);

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
    public void testGetRecycleList(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://"
        		+ locIpHost+"/services/order/getRecycleList/0115101700000001");
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("status", "");
        jsonMap.put("startIndex", "0");
        jsonMap.put("pageSize","15");
        jsonMap.put("beginTime", "978278400");
        jsonMap.put("endTime", "1009641600");
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
                System.out.println("response >>>>>testGetRecycleList>>>>>>>>>>>>>>>\n"+reString);

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
    public void testAffirmOrder(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut("http://"
        		+ locIpHost+"/services/order/affirmOrder/1115102400000002");
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("orderNo", "345000");
        jsonMap.put("orderType", "0");
        jsonMap.put("orderId", "5");
        String jsonStr = JSON.toJSONString(jsonMap);
        
        try {
            httpPut.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(jsonStr, Charset.forName("utf8"));
            httpPut.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPut);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                System.out.println("response >>>>>testAffirmOrder>>>>>>>>>>>>>>>\n"+reString);

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
    public void testAffirmDelivery(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut("http://"
        		+ locIpHost+"/services/order/affirmDelivery/us2");
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("orderId", "9991");
        jsonMap.put("orderType", "0");
        jsonMap.put("orderNo", "345000");
        String jsonStr = JSON.toJSONString(jsonMap);

        try {
            httpPut.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(jsonStr, Charset.forName("utf8"));
            httpPut.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPut);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status:" + status);
            if (status == 200) {
                String reString = EntityUtils.toString(response.getEntity());
                System.out.println("response >>>>>testAffirmDelivery>>>>>>>>>>>>>>>\n"+reString);

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
    public void testGetSupplierOrderList(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://"
        		+ locIpHost+"/services/order/getSupplierOrderList/1115110200000013");
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("startIndex", "0");
        jsonMap.put("pageSize","10");
        jsonMap.put("userType", "3");
//        jsonMap.put("userTel", "13623000000");
//        jsonMap.put("userName", null);
//        jsonMap.put("orderNo", null);
//        jsonMap.put("beginTime", "");
//        jsonMap.put("endTime", "");
//        jsonMap.put("status", null);
//        jsonMap.put("status2", "");
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
                System.out.println("response >>>>>testGetSupplierOrderList>>>>>>>>>>>>>>>\n"+reString);

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
    public void testPreparedPay(){
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
//            HttpPost httpPost = new HttpPost("http://192.168.87.61:8001/services/cart/settleCart/0115101700000002/110100");
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/order/preparedPay");
            PreparedPayInputDTO param = new PreparedPayInputDTO();
            param.setOrderId(10025);
            param.setOrderType(Constants.OrderType.MAIN.v());
            
            String str =  JSON.toJSONString(param);
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
            Assert.fail("testPreparedPay:" + e.getMessage() + e.getStackTrace());
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
    public void testOrderTypeNum(){
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
//            HttpPost httpPost = new HttpPost("http://192.168.87.61:8001/services/cart/settleCart/0115101700000002/110100");
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/order/orderTypeNum/us2");
            Map<String,String> jsonMap = new HashMap<String,String>();
            jsonMap.put("userType", "-1");
            
            String str =  JSON.toJSONString(jsonMap);
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
            Assert.fail("testPreparedPay:" + e.getMessage() + e.getStackTrace());
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
