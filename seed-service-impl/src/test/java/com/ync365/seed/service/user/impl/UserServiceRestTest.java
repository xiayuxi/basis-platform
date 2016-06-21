package com.ync365.seed.service.user.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.ync365.seed.dto.user.LcInfoDTO;
import com.ync365.seed.dto.user.SysReceiveGoodsInfoDTO;
import com.ync365.seed.dto.user.UserInfoDTO;
import com.ync365.seed.dto.user.UserLoginDTO;
import com.ync365.seed.dto.user.UserRegisterDTO;
import com.ync365.seed.dto.user.VsInfoDTO;

public class UserServiceRestTest {

    @Test
    public void test() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://192.168.87.61:8001/services/users/login");
        //UserDTO vo = new UserDTO();
        UserLoginDTO dto = new UserLoginDTO();
        dto.setUserLoginName("aa");
        dto.setPassword("bb");
        //		vo.setLoginName("login");
        //vo.setAddress(11);
        //vo.setMobile("123123132");
        //vo.setPassword("aaa");
        //vo.setRecommend("aaaaaa");
        //vo.setRepassword("aaa");
        //vo.setValidateCode("a1b2");
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

    //VS获取绑定US列表
    @Test
    public void testOneSelectSysUsListByVsNum() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/users/selectsysuslistbyvsnum");
        UserInfoDTO vo = new UserInfoDTO();
        vo.setVsNum("2");
        //vo.setUserMobile("111");
        //vo.setSysUserRealName("cs");
        //vo.setMobile("123123132");
        //vo.setPassword("aaa");
        //vo.setRecommend("aaaaaa");
        //vo.setRepassword("aaa");
        //vo.setValidateCode("a1b2");
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

    //LC获取绑定VS列表
    @Test
    public void testOneGetSysVsInfoListByLcNum() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/users/getsysvsinfolistbylcnum");
        VsInfoDTO vo = new VsInfoDTO();
        vo.setLcNum("1");
        //vo.setMobile("123123132");
        //vo.setPassword("aaa");
        //vo.setRecommend("aaaaaa");
        //vo.setRepassword("aaa");
        //vo.setValidateCode("a1b2");
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

    //LC获取绑定US列表
    @Test
    public void testOnegGetSysUsInfoListByLcNum() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/users/getsysusinfolistbylcnum");
        UserInfoDTO vo = new UserInfoDTO();
        //vo.setMobile("123123132");
        //vo.setPassword("aaa");
        //vo.setRecommend("aaaaaa");
        //vo.setRepassword("aaa");
        //vo.setValidateCode("a1b2");
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

    //US修改用户信息 （头像 昵称 性别 真实姓名 身份证号 出生日期）
    @Test
    public void testOneUpdateUserInfoByUserNum() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/users/updateuserinfobyusernum");
        UserInfoDTO vo = new UserInfoDTO();
        vo.setUserNum("22");
        vo.setSysUserName("tttt");
        vo.setSysUserRealName("test");
        vo.setSysUserIdCard("111111111111111111");
        vo.setSysUserGender(0);
        vo.setSysUserBirthday(new Date());
        //vo.setValidateCode("a1b2");
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

    //VS修改帐号信息
    @Test
    public void testOneUpdateVsInfoByUsNum() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/users/updatevsinfobyusnum");
        VsInfoDTO vo = new VsInfoDTO();
        vo.setUserNum("55");
        vo.setName("ceshi");
        vo.setTelephone("9999999999999");
        vo.setIdCard("88888888888888");
        //vo.setValidateCode("a1b2");
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

    // TODO LC修改帐号信息
    @Test
    public void testOneUpdateSysLcInfoByLcNum() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/users/updatesyslcinfobylcnum");
        LcInfoDTO vo = new LcInfoDTO();
        vo.setUserNum("55");
        vo.setName("ceshi");
        vo.setTelephone("9999999999999");
        vo.setIdCard("88888888888888");
        //vo.setValidateCode("a1b2");
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

    // TODO US 添加收货地址
    @Test
    public void testOneInsertSysReceiveGoodsInfo() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/users/insertsysreceivegoodsinfo");
        SysReceiveGoodsInfoDTO vo = new SysReceiveGoodsInfoDTO();
        vo.setUserNum("55");
        vo.setSysReceiveGoodsAddress(1111);
        vo.setSysReceiveGoodsAddressDetail("朝阳区");
        vo.setSysReceiveGoodsName("ceshi");
        vo.setSysReceiveGoodsMobile("11111111");
        //vo.setValidateCode("a1b2");
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

    // TODO US 编辑保存收货地址
    @Test
    public void testOneUpdateSysReceiveGoodsInfoById() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8001/services/users/updatesysreceivegoodsinfobyid");
        SysReceiveGoodsInfoDTO vo = new SysReceiveGoodsInfoDTO();
        vo.setId(20);
        vo.setSysReceiveGoodsAddress(333);
        vo.setSysReceiveGoodsAddressDetail("fyyyyyy");
        vo.setSysReceiveGoodsName("kkkk");
        vo.setSysReceiveGoodsMobile("888899");
        //vo.setValidateCode("a1b2");
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

    public static String hosturl = "http://192.168.87.61:8001/services/users/";

    public static String httpClient(String url, String data) {
        String reString = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(hosturl + url);
        try {
            httpPost.addHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
            HttpEntity entity = new StringEntity(data, Charset.forName("utf8"));
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                reString = EntityUtils.toString(response.getEntity());
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
        return reString;
    }

    private String getToken() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(hosturl + "token");
        String result = null;
        try {
            CloseableHttpResponse response = client.execute(get);
             result=EntityUtils.toString(response.getEntity());
             System.out.println(result);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String token = String.valueOf(JSON.parseObject(result).get("data"));
        return token;

    }

    @Test
    public void registerTest() {
        String token = getToken();
        System.out.println(token);
        String path = "register?token=" + token;
        UserRegisterDTO userDTO = new UserRegisterDTO();
        userDTO.setUserName("aaa");
        userDTO.setUserPassword("aaa");
        userDTO.setUserRePassword("aaa");
        userDTO.setRegisterLocation(1);
        userDTO.setMobileValidateCode("5555");
        userDTO.setRecommendedNum("123");
        userDTO.setUserMobile("15000000000");
        System.out.println(JSON.toJSONString(userDTO));
        httpClient(path, JSON.toJSONString(userDTO));
    }

}
