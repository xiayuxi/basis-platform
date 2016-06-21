package com.ync365.seed.service.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.RpcContext;
import com.ync365.seed.commons.redis.JedisTemplate;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.service.annotation.FormTokenAnnotation;
import com.ync365.seed.utils.StringUtils;

@Component
@Aspect
@Order(2)
public class FormTokenInterceptor {
    Logger log = Logger.getLogger(FormTokenInterceptor.class);
    @Autowired
    private JedisTemplate template;

    @Pointcut("within(com.ync365.seed.service..*)")
    public void aspect() {

    }

    /**
     * 拦截获取表单Token并验证
     * @Title: doAround
     * @Description:     ：    
     * @author: duan.zhao.qian
     * @date: 2015年9月29日 下午3:29:29       
     * @version: 
     *
     * @param pjp
     * @param formTokenAnnotation
     * @return
     * @throws Throwable
     *
     */
    @Around("aspect()&&@annotation(formTokenAnnotation)")
    public Object doAround(ProceedingJoinPoint pjp, FormTokenAnnotation formTokenAnnotation) throws Throwable {
        ResponseDTO dto = new ResponseDTO();
        dto.setCode(ResponseCode.CommonCode.TOKEN_NOT_EXISTS.v());
        dto.setMsg(ResponseCode.CommonCode.TOKEN_NOT_EXISTS.c());
        String token = null;
        RpcContext context = RpcContext.getContext();
        if (context.getRequest(HttpServletRequest.class) != null) {
            HttpServletRequest request = context.getRequest(HttpServletRequest.class);
            token = request.getParameter("token");
        }
        if (StringUtils.isNotEmpty(token)) {
            String longtime = template.getSet(token, "-1");
            template.expire(token, 60 * 60);

            if (StringUtils.isNotEmpty(token) && StringUtils.isNotEmpty(longtime)) {
                //验证Token是否有效
                if (("-1").equals(longtime)) {
                    dto.setCode(ResponseCode.CommonCode.TOKEN_HAS_USED.v());
                    dto.setMsg(ResponseCode.CommonCode.TOKEN_HAS_USED.c());
                } else if (Calendar.getInstance().getTimeInMillis() > Long.valueOf(longtime)) {
                    //Token过期
                    dto.setCode(ResponseCode.CommonCode.TOKEN_EXPIRE.v());
                    dto.setMsg(ResponseCode.CommonCode.TOKEN_EXPIRE.c());
                } else {
                    dto = (ResponseDTO) pjp.proceed();
                }
            }
        }
        return dto;
    }

    @AfterThrowing(pointcut = "aspect()", throwing = "exception")
    public void doException(JoinPoint jp, Exception exception) {
        log.error("FormTokenInterceptor ", exception);
    }
}
