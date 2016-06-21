package com.ync365.seed.admin.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;

import com.alibaba.fastjson.JSON;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;

/**
 *
 * 
 */
public  class LoginUserUtils {

    /**
     * 获取当前登录 对象信息
     * @param request
     * @return
     */
    public static AdminBO getUser(){
        String adminStr = String.valueOf( SecurityUtils.getSubject().getSession().getAttribute("c_user"));
        AdminBO adminBO = JSON.parseObject(adminStr, AdminBO.class);
        return  adminBO;
    }
    
}
