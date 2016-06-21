package com.ync365.seed.admin.security.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.utils.StringUtils;
@Component("frameperms")
public class FramePermissionsAuthorizationFilter extends
		PermissionsAuthorizationFilter {
	private Logger logger = LoggerFactory.getLogger(FramePermissionsAuthorizationFilter.class);

//	@Resource
//	private CacheManager shiroCacheManager;

	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		Subject user = SecurityUtils.getSubject();
		
		
		String adminStr = String.valueOf(user.getPrincipal()) ;
	    AdminBO psu=JSON.parseObject(adminStr, AdminBO.class);

		/*
		 * Session session = user.getSession(false); Cache<Object, Object> cache
		 * = shiroCacheManager.getCache(GlobalStatic.authenticationCacheName);
		 * String cachedSessionId =
		 * cache.get(GlobalStatic.authenticationCacheName
		 * +"-"+shiroUser.getAccount()).toString(); String sessionId=(String)
		 * session.getId(); if(!sessionId.equals(cachedSessionId)){
		 * user.logout(); }
		 */

		HttpServletRequest req = (HttpServletRequest) request;
		Subject subject = getSubject(request, response);
		String uri = req.getRequestURI();
		String requestURL = req.getRequestURL().toString();
		String contextPath = req.getContextPath();
//		if (uri.endsWith("/pre")) {// 去掉pre
//			uri = uri.substring(0, uri.length() - 4);
//		}
		int i = uri.indexOf(contextPath);
		if (i > -1) {
			uri = uri.substring(i + contextPath.length());
		}
		if (StringUtils.isBlank(uri)) {
			uri = "/";
		}
		boolean permitted = false;
		if ("/".equals(uri)) {
			permitted = true;
		} else {
			permitted = subject.isPermitted(uri);
		}
		String isqx = "否";
		if (permitted) {
			isqx = "是";
		}

		// 写日志
		//String ip = TCPIPUtil.getIpAddr(req);
		// ...
		logger.info("有人访问了" + uri);
		return permitted;

	}
}
