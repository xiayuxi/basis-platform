package com.ync365.seed.admin.security.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.entity.SysAdmin;
import com.ync365.seed.bussiness.modules.user.entity.SysModule;
import com.ync365.seed.bussiness.modules.user.entity.SysRole;
import com.ync365.seed.bussiness.modules.user.entity.SysUser;
import com.ync365.seed.commons.shiro.RedisCacheManager;
import com.ync365.seed.utils.MD5Utils;

/**
 * Subject验证的过程可以有效地划分分以下三个步骤： 1.收集Subject提交的身份和证明； 2.向Authenticating提交身份和证明；
 * 3.如果提交的内容正确，允许访问，否则重新尝试验证或阻止访问
 * Realm：可以有1个或多个Realm，可以认为是安全实体数据源，即用于获取安全实体的；
 * 可以是JDBC实现，也可以是LDAP实现，或者内存实现等等；由用户提供；
 * 注意：Shiro不知道你的用户/权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己的Realm
 * @author q
 * 
 */
@Component("shiroDbRealm")
public class ShiroDbRealm extends AuthorizingRealm {
    @Autowired
    SysAdminBiz sysAdminBiz;

    @Autowired
    RedisCacheManager redisCacheManager;

    private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

    // public static final String HASH_ALGORITHM = "MD5";
    // public static final String HASH_ALGORITHMB = "Sha384";
    // public static final int HASH_INTERATIONS = 1;

    // private static final int SALT_SIZE = 8;

    public ShiroDbRealm() {
        //访问量小的情况下  
        // 认证
        super.setAuthenticationCacheName("shiro-authenticationCacheName");
        super.setAuthenticationCachingEnabled(false); //如果为true  密码会被缓存
        // 授权
        super.setAuthorizationCacheName("shiro-authorizationCacheName");
        super.setAuthorizationCachingEnabled(false);
        // super.setAuthorizationCachingEnabled(false); //测试的时候先关闭缓存

        // -----------------

        // //认证
        // super.setAuthenticationCacheName(GlobalStatic.authenticationCacheName);
        // //
        // super.setAuthenticationCacheName(GlobalStatic.authenticationCacheName);
        // super.setAuthenticationCachingEnabled(false);
        // //授权
        // super.setAuthorizationCacheName(GlobalStatic.authorizationCacheName);
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principalCollection);
            SecurityUtils.getSubject().logout();
            return null;
        }
        String adminStr = String.valueOf(SecurityUtils.getSubject().getPrincipal()) ;
        AdminBO psu=JSON.parseObject(adminStr, AdminBO.class);
        //String username = (String)super.getAvailablePrincipal(principalCollection);  
        //AdminBO psu = sysAdminBiz.selectSysAdminByLoginName(username); 
        // String userId = (String)
        // principalCollection.fromRealm(getName()).iterator().next();
        //Integer userId = psu.getId().intValue();
        // if (StringUtils.isBlank(userId)) {
        // return null;
        // 添加角色及权限信息
        SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
        try {
            Set<String> set = new HashSet<String>();

            if (null != psu && null != psu.getListRole() && psu.getListRole().size() > 0) {
                for (SysRole temp : psu.getListRole()) {
                    set.add(temp.getRoleName());
                }
            }
            sazi.addRoles(set); // 获取当前用户所有的角色,

            // //用于依据角色判断权限的shiro过滤器
            Set<String> sp = new HashSet<String>();
            if (null != psu && null != psu.getModuleList() && psu.getModuleList().size() > 0) {
                for (SysModule te : psu.getModuleList()) {
                    sp.add(te.getUrl());
                }
            }
            sazi.addStringPermissions(sp); // 获取当前用户的所有权限,
            // 权限就是url,一个url的集合
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return sazi;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        /*
         * String pwd = new String(upToken.getPassword()); if
         * (StringUtils.isNotBlank(pwd)) { pwd = DigestUtils.md5Hex(pwd); }
         */
        // 调用业务方法
        AdminBO sysAdmin = null;
        Session session = SecurityUtils.getSubject().getSession(false);
        
        try {
            sysAdmin = sysAdminBiz.selectSysAdminByLoginName(upToken.getUsername());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        AuthenticationInfo authinfo = null ;
        if (sysAdmin != null) {
            String adminStr=JSON.toJSONString(sysAdmin);
            // 要放在作用域中的东西，请在这里进行操作
            session.setAttribute("c_user", adminStr);
            // byte[] salt = EncodeUtils.decodeHex(user.getSalt());
                            
            authinfo = new SimpleAuthenticationInfo(adminStr,
                    sysAdmin.getAdminPassword(), getName());
            Cache<Object, Object> cache = redisCacheManager.getCache("shiro_redis_cache:");
            cache.put(sysAdmin.getAdminNum(), session.getId());
            return authinfo;
        }
        // 认证没有通过
        return authinfo;
    }

    /**
     * 设定Password校验.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        // HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
        // "Sha384");
        // matcher.setHashIterations(HASH_INTERATIONS);

        //setCredentialsMatcher(new CustomCredentialsMatcher());

        // setCredentialsMatcher(matcher);
        // setCredentialsMatcher(new Sha256CredentialsMatcher());

        // setCacheManager(new MemoryConstrainedCacheManager());

        // HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
        // HASH_ALGORITHMB);
        // matcher.setHashIterations(HASH_INTERATIONS);
        // setCredentialsMatcher(matcher);
    }
    
    
}
