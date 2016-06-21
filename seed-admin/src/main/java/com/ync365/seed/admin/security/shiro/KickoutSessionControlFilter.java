package com.ync365.seed.admin.security.shiro;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.fastjson.JSON;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;

/**
 * 同一个帐号 并发控制登录人数
 * Shiro对Servlet容器的FilterChain进行了代理，即ShiroFilter在继续Servlet容器的Filter链的执行之前，
 * 通过ProxiedFilterChain对Servlet容器的FilterChain进行了代理；即先走Shiro自己的Filter体系，
 * 然后才会委托给Servlet容器的FilterChain进行Servlet容器级别的Filter链执行
 * ；Shiro的ProxiedFilterChain执行流程：
 * 1、先执行Shiro自己的Filter链；2、再执行Servlet容器的Filter链（即原始的Filter）。
 * 而ProxiedFilterChain是通过FilterChainResolver根据配置文件中[urls]部分是否与请求的URL是否匹配解析得到的。
 * 
 * @author q
 * 
 */
public class KickoutSessionControlFilter extends AccessControlFilter {

	private String kickoutUrl; // 踢出后到的地址
	private boolean kickoutAfter = false; // 踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
	private int maxSession = 1; // 同一个帐号最大会话数 默认1
	private SessionManager sessionManager;
	private Cache<String, Deque<Serializable>> cache;

	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}

	public void setKickoutAfter(boolean kickoutAfter) {
		this.kickoutAfter = kickoutAfter;
	}

	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cache = cacheManager.getCache("shiro_redis_cache:");
	}

	/**
	 * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		return false;
	}

	/**
	 * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
	 */
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		// Subject subject = getSubject(request, response);
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			// 如果没有登录，直接进行之后的流程
			return true;
		}
		Session session = subject.getSession();
		 String key_username = (String) subject.getPrincipal();  
		 AdminBO adminBO = JSON.parseObject(key_username, AdminBO.class);
		 String username = adminBO.getAdminNum();
		 
		Serializable sessionId = session.getId();

		// TODO 同步控制
		Deque<Serializable> deque = cache.get(username);
		if (deque == null) {
			deque = new LinkedList<Serializable>();
			cache.put(username, deque);
		}

		// 如果队列里没有此sessionId，且用户没有被踢出；放入队列
		if (!deque.contains(sessionId)
				&& session.getAttribute("kickout") == null) {
			deque.push(sessionId);
		}

		// 如果队列里的sessionId数超出最大会话数，开始踢人
		while (deque.size() > maxSession) {
			Serializable kickoutSessionId = null;
			if (kickoutAfter) { // 如果踢出后者
				kickoutSessionId = deque.removeFirst();
			} else { // 否则踢出前者
				kickoutSessionId = deque.removeLast();
			}
			try {
				Session kickoutSession = sessionManager
						.getSession(new DefaultSessionKey(kickoutSessionId));
				if (kickoutSession != null) {
					// 设置会话的kickout属性表示踢出了
					kickoutSession.setAttribute("kickout", true);
				}
			} catch (Exception e) {// ignore exception
			}
		}

		// 如果被踢出了，直接退出，重定向到踢出后的地址
		if (session.getAttribute("kickout") != null) {
			// 会话被踢出了
			try {
				subject.logout();
			} catch (Exception e) { // ignore
			}
			// Convenience method merely delegates to
			// WebUtils.saveRequest(request) to save the request state
			// for reuse later. This is mostly used to retain user request state
			// when a redirect is issued to return
			// the user to their originally requested url/resource.
			// If you need to save and then immediately redirect the user to
			// login, consider
			// using saveRequestAndRedirectToLogin(request,response) directly.
			saveRequest(request);// 将请求保存起来，如登录成功后再重定向回该请求
			// Issues an HTTP redirect to the specified URL after subject
			// logout.
			// This implementation simply calls
			// WebUtils.issueRedirect(request,response,redirectUrl).
			WebUtils.issueRedirect(request, response, kickoutUrl);
			return false;
		}
		return true;
	}
}
