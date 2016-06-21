package com.ync365.seed.service.goods.impl;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
import com.ync365.seed.bussiness.modules.goods.entity.SaleRegion;
import com.ync365.seed.dto.goods.BrowseSearchDTO;
import com.ync365.seed.dto.goods.GoodsSearchDTO;

public class GoodsServiceImplTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	 @Test
    public void getCategoryProductList() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/goods/getCategoryProductList");
        GoodsSearchDTO vo = new GoodsSearchDTO();
      
        vo.setCategoryId(12);
         
        
        vo.setSortColumn(2);
        vo.setSortDirection(2);
        vo.setPageNum(1);
        vo.setPageSize(12);
        vo.setRegionId(2);
        
        
        String str = JSON.toJSONString(vo);
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
	 public void getImageList(){
		 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/goods/getImageList/320");
	        
	    
	        try {
	            httpPost.addHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
	            HttpEntity entity = new StringEntity("", Charset.forName("utf8"));
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
	 public void getSkuDetail(){
		 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/goods/getSkuDetail/320");
	        
	    
	        try {
	            httpPost.addHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
	            HttpEntity entity = new StringEntity("", Charset.forName("utf8"));
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
	 public void getBrowseRecoderList(){
		 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost httpPost = new HttpPost("http://192.168.87.61:8001/services/goods/getBrowseRecord");
	        
	        List<Integer> skuList = new ArrayList<Integer>();
	        skuList.add(320);
	        skuList.add(374);
	        
	        skuList.add(7);
	        
	        skuList.add(372);
	        
	        skuList.add(8);
	        
	        skuList.add(9);
	        
	        skuList.add(360);
	        
	        BrowseSearchDTO dto = new BrowseSearchDTO() ;
	        dto.setRegionId(2);
	        dto.setSkuList(skuList);
	        
	        String str = JSON.toJSONString(dto);
	    
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
	 public void getBrandListByQuery(){
		 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/goods/getBrandListByQuery/化肥/2");
	        
	        List<Integer> skuList = new ArrayList<Integer>();
	        skuList.add(320);
	        String str = JSON.toJSONString(skuList);
	    
	        try {
	            httpPost.addHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
	            HttpEntity entity = new StringEntity("", Charset.forName("utf8"));
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
	 public void getCategoryListByQuery(){
		 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/goods/getCategoryListByQuery/化肥/2");
	        
	        List<Integer> skuList = new ArrayList<Integer>();
	        skuList.add(320);
	        String str = JSON.toJSONString(skuList);
	    
	        try {
	            httpPost.addHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
	            HttpEntity entity = new StringEntity("", Charset.forName("utf8"));
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
	 public void getManufactorListNext(){
		 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost httpPost = new HttpPost("http://192.168.87.61:8001/services/goods/getManufactorListNext/2/48/10/1");
	        
	        List<Integer> skuList = new ArrayList<Integer>();
	        skuList.add(320);
	        String str = JSON.toJSONString(skuList);
	    
	        try {
	            httpPost.addHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
	            HttpEntity entity = new StringEntity("", Charset.forName("utf8"));
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
	 public void getRandomSkuList(){
		 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/goods/getRandomSkuList/10/2");
	        
	        List<Integer> skuList = new ArrayList<Integer>();
	        skuList.add(320);
	        String str = JSON.toJSONString(skuList);
	    
	        try {
	            httpPost.addHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
	            HttpEntity entity = new StringEntity("", Charset.forName("utf8"));
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
	 public void getSaleRegionListBySkuId(){
		 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/goods/getSaleRegionListBySkuId/360");
	        
	        List<Integer> skuList = new ArrayList<Integer>();
	        skuList.add(320);
	        String str = JSON.toJSONString(skuList);
	    
	        try {
	            httpPost.addHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
	            HttpEntity entity = new StringEntity("", Charset.forName("utf8"));
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
	 public void selectSaleRegionList(){
		 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/goods/getSaleRegionList/360/2");
	        
	    
	        try {
	            httpPost.addHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
	            HttpEntity entity = new StringEntity("", Charset.forName("utf8"));
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
