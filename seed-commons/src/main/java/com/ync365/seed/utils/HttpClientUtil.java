/**    
 * 文件名：HttpClientUtil.java    
 *    
 * 版本信息：    
 * 日期：2015年11月6日    
 * Copyright  Corporation 2015     
 * 版权所有    
 *    
 */
package com.ync365.seed.utils;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**    
 *     
 * @Title：HttpClientUtil  
 * @Description: TODO   
 * @author: ivan        
 * @date: 2015年11月6日 下午3:12:46      
 * @version     
 *     
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String post(String url, List<NameValuePair> params) {
        String body = null;
        CloseableHttpClient hc = null;
        CloseableHttpResponse httpresponse = null;
        try {
            // Post请求
            HttpPost httppost = new HttpPost(url);
            hc = HttpClients.createDefault();
            // 设置参数
            httppost.setEntity(new UrlEncodedFormEntity(params));
            // 发送请求
            httpresponse = hc.execute(httppost);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            int statusCode = httpresponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new Exception("HttpStatus:" + httpresponse.getStatusLine());
            }
            body = EntityUtils.toString(entity);
            if (entity != null)
                EntityUtils.consume(entity);

        } catch (Exception e) {
            logger.error("HttpClient调用失败" + e.getMessage(), e);
        } finally {
            try {
                if (httpresponse != null)
                    httpresponse.close();
                if (hc != null)
                    hc.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return body;
    }
}
