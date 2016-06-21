package com.ync365.seed.service.promotion.impl;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

public class PromotionServiceImplTest {

    
    @Test
    public void testQueryPromotionSkus() {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            // HttpGet httpGet = new HttpGet("http://192.168.87.61:8001/services/promotion/queryPromotionSkus/123");
            HttpGet httpGet = new HttpGet("http://127.0.0.1:8001/services/promotion/queryPromotionSkus/123");
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
            Assert.fail("testQueryPromotionSkus:" + e.getMessage() + e.getStackTrace());
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
