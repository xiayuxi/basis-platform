package com.ync365.seed.admin.security.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class JdbcAuthenticationRealm extends AuthorizingRealm  
{  
  
 
  
    List<String> defaultPermission = new ArrayList<String>();  
  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(  
            AuthenticationToken token) throws AuthenticationException  
    {  
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;  
  
        String username = usernamePasswordToken.getUsername();  
        String tmpPassword = null;  
        if (username == null)  
        {  
            throw new AccountException("用户名不能为空");  
        }  
  
 
  
        return new SimpleAuthenticationInfo(null, tmpPassword, getName());  
    }  
  
    /** 
     *  
     * 当用户进行访问链接时的授权方法 
     *  
     */  
  
    protected AuthorizationInfo doGetAuthorizationInfo(  
            PrincipalCollection principals)  
    {  
  
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
   
        return info;  
    }  
  
    public void setDefaultPermissionString(String defaultPermissionString)  
    {  
        String[] perms = defaultPermissionString.split(",");  
         
  
    }  
  
}
