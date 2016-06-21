package com.ync365.seed.admin.Interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.bussiness.modules.user.biz.SysLogsBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.entity.SysLogs;

@Component
@Aspect
public class LogInterceptor {
    @Autowired
    SysLogsBiz sysLogsBiz;
    Logger logger = Logger.getLogger(LogInterceptor.class);
    //    @Pointcut("within(com.ync365.seed.admin..*)")
    //    public void aspect() {
    //
    //    }

    @Around("@annotation(logAnnotation)")
    public Object onAround(ProceedingJoinPoint pjp, LogAnnotation logAnnotation) throws Throwable {
        SysLogs logs = new SysLogs();
        AdminBO bo = LoginUserUtils.getUser();
        logs.setCreateTime(Calendar.getInstance().getTime());
        logs.setState(0);
        logs.setGroupName("");
        logs.setRoleName(bo.getRoleName());
        logs.setOperatorName(bo.getAdminName());
        logs.setOperatingAdminNum(bo.getAdminNum());
        Object[] objs = pjp.getArgs();
        List<String> params = new ArrayList<>();
        for (Object object : objs) {
            if (!(object instanceof HttpServletRequest) && !(object instanceof HttpServletResponse)) {
                params.add(JSON.toJSONString(object));
            }
        }
        logs.setParameter(Arrays.toString(params.toArray()));
        logs.setOperatingContent(logAnnotation.operatingContent());
        sysLogsBiz.insertSelective(logs);
        Object obj = pjp.proceed();
        SysLogs logafter = new SysLogs();
        logafter.setId(logs.getId());
        logafter.setState(1);
        sysLogsBiz.updateByPrimaryKeySelective(logafter);
        return obj;
    }
}
