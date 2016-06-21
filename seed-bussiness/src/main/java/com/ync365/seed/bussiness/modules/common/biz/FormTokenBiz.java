package com.ync365.seed.bussiness.modules.common.biz;

import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.commons.redis.JedisTemplate;

@Service
public class FormTokenBiz {
    @Autowired
    private JedisTemplate jedisTemplate;

    /**
     * 获取表单Token，自动过期时间1小时
     * @Title: getToken
     * @Description:     ：    
     * @author: duan.zhao.qian
     * @date: 2015年9月29日 下午3:28:31       
     * @version: 
     *
     * @return
     *
     */
    public String getToken() {
        String token = "token_" + UUID.randomUUID().toString();
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MINUTE, 20);
        jedisTemplate.setex(token, String.valueOf(ca.getTimeInMillis()), 60 * 60);
        return token;
    }

}
