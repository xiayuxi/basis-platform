package com.ync365.seed.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.fastjson.JSON;
import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.security.shiro.CaptchaServlet;
import com.ync365.seed.admin.security.shiro.UsernamePasswordToken;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.entity.SysAdmin;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysRole;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.MD5Utils;

@Controller
@RequestMapping
public class LoginController {
    
    @Autowired
    private SysAdminBiz adminBiz;
    @Autowired
    private SysAdminInfoBiz sysAdminInfoBiz;
    
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String loginUrl = "redirect:/index";
        String noLoginUrl = "redirect:/login";
        System.out.println("进入登陆页面");
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            response.setStatus(901);
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (password==null){
                password = "";
            }
            //认证  请求 无提交 username及password 跳转到 login页面
            if (null != username && null != password && "" != username && "" != password) {
                // 增加判断验证码逻辑
                String captcha = WebUtils.getCleanParam(request, "captcha");
                String exitCode = (String) SecurityUtils.getSubject().getSession()
                        .getAttribute(CaptchaServlet.KEY_CAPTCHA);
                if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
                    request.setAttribute("error_lgong", "验证码错误！"); 
                    return "login";
                }else{
                    request.setAttribute("error_lgong", "");
                }
                //获取当前的Subject   
                Subject curUser = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Utils.getMD5Str(password).toCharArray(), false, null, captcha, false );
                try {
                    curUser.login(token);
                    return "redirect:/index";
                } catch(UnknownAccountException uae){  
                    System.out.println("对用户[" + username + "]进行登录验证..验证未通过,用户不存在！");  
                    request.setAttribute("message_param", "用户不存在！");  
                    return "login";
                }catch(IncorrectCredentialsException ice){  
                    System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");  
                    request.setAttribute("message_param", "密码不正确");  
                    return "login";
                }catch(LockedAccountException lae){  
                    System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");  
                    request.setAttribute("message_param", "账户已锁定");  
                    return "login";
                }catch(ExcessiveAttemptsException eae){  
                    System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");  
                    request.setAttribute("message_param", "用户名或密码错误次数过多");  
                    return "login";
                }catch(AuthenticationException ae){  
                    //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
                    System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");  
                    ae.printStackTrace();  
                    request.setAttribute("message_param", "用户名或密码不正确");  
                    return "login";
                }  
            } else {
                String a =  request.getParameter("t");
                if(null != a && "" != a){
                    if("1".equals(a)){
                        request.setAttribute("message_param", "用户已冻结！");
                    }
                    if("2".equals(a)){
                        request.setAttribute("message_param", "用户未审核");
                    }
                    if("3".equals(a)){
                        request.setAttribute("message_param", "请求错误，重新登录！");
                    }
                }
                return "login";
            }
            //return "login";
        }
        return "redirect:/index";
    }

    @RequestMapping("/index")
    @LogAnnotation(operatingContent = "登录成功，进入首页")
    public String index(HttpServletRequest request) {
        //获取当前登录信息
        String adminStr = String.valueOf(request.getSession().getAttribute("c_user"));
        AdminBO adminBO = JSON.parseObject(adminStr, AdminBO.class);
        
        if(null != adminBO){
            //冻结账号不允许登录
            if(adminBO.getIsFrozen()==null||adminBO.getIsFrozen()){
                SecurityUtils.getSubject().logout();
                return "redirect:/login?t=1";
            }
            
            if(null != adminBO.getAdminName() && "" != adminBO.getAdminName()){
                request.setAttribute("userName", adminBO.getAdminName());
            }else{
                request.setAttribute("userName", adminBO.getAdminLoginName());
            }
            request.setAttribute("last_login_time", adminBO.getUpdateTime());
            
            //判断当前用户是否是se角色
            if( null != adminBO.getListRole() && adminBO.getListRole().size() > 0 ){//当前登录账号为se时  调用 
                for(SysRole temp : adminBO.getListRole()){
                    if( null != temp.getId() ){
                        if(Constants.UserRole.SE.v() == (int)temp.getId()){
                            SysAdminInfo sysadminInfo = sysAdminInfoBiz.selectByPrimaryKey(adminBO.getAdminNum());
                            if(null != sysadminInfo ){
                                System.out.println(adminBO.getAdminNum()+"---"+"已经完善资料");
                            }else{
                                System.out.println(adminBO.getAdminNum()+"---"+"未善资料");
                                request.setAttribute("adminNum", adminBO.getAdminNum());
                                return "user/admin/sysSeInfoPage";
                            }
                        }
                    }
                }
            }
                
            if(adminBO.getIsAuthentication()==null||adminBO.getIsAuthentication()!=true){
                SecurityUtils.getSubject().logout();
                return "redirect:/login?t=2";
            }
            return "index";
       
        }
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        return "login";
    }
    
    
    @RequestMapping("/noAuth")
    public String noAuth(HttpServletRequest request){
        request.setAttribute("noAuth", "无权限访问此页面！请联系管理员添加相应权限！");
        return "noAuth";
    }
}
