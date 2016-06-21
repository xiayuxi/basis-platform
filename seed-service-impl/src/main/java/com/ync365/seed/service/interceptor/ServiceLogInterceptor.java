package com.ync365.seed.service.interceptor;

import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;

@Component
@Aspect
@Order(2)
public class ServiceLogInterceptor {
    @Pointcut("execution(* com.ync365.seed.service.*.impl.*.*(..))")
    public void aspect() {

    }

    /**
     * 处理Service接口日志
     * @Title: doAround
     * @Description: 
     * @author: duan.zhao.qian
     * @date: 2015年10月22日 下午1:30:32       
     * @version: 
     *
     * @param pjp
     * @return
     *
     */
    @Around("aspect()")
    public Object doAround(ProceedingJoinPoint pjp) {
        Object obj = null;
        Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());
        StringBuilder sbargs = new StringBuilder();
        StopWatch stopWatch = new StopWatch();
        try {
            Object[] objargs = pjp.getArgs();
            for (Object object : objargs) {
                sbargs.append(JSON.toJSON(object));
            }
            stopWatch.start();
            obj = pjp.proceed();
            stopWatch.stop();
            log.info("方法名:{},消耗时间:{},传入参数:{},返回数据:{}", pjp.getSignature().getName(), stopWatch.toString(), sbargs,
                    JSON.toJSON(obj));
        } catch (Throwable e) {
            ResponseDTO dto = new ResponseDTO();
            dto.setMsg(ResponseCode.CommonCode.UNKNOWN_FAIL.c());
            dto.setCode(ResponseCode.CommonCode.UNKNOWN_FAIL.v());
            dto.setData(e.toString());
            obj = dto;
            log.error("方法名:{},消耗时间:{},传入参数:{},返回数据:{}", pjp.getSignature().getName(), stopWatch.toString(), sbargs,
                    JSON.toJSON(obj), e);
        }
        return obj;
    }
}
