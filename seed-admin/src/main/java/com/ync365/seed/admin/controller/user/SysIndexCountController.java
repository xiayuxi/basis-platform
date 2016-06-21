package com.ync365.seed.admin.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysLargeCustomerInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysLcInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.AdminInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.LargeCustomerInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;
import com.ync365.seed.utils.Constants;



/**
 * indexCount controller
 * @author leixc
 *
 */
@Controller
@RequestMapping("/sysindexcount")
public class SysIndexCountController {

    @Autowired
    private SysLcInfoBiz  sysLcInfoBiz;
    
    @Autowired
    private SysVsInfoBiz sysVsInfoBiz;
    
    @Autowired
    private SysUserInfoBiz sysUserInfoBiz ;
    
    @Autowired
    private SysAdminInfoBiz sysAdminInfoBiz ;
    
    @Autowired
    private SysPopStoreBiz sysPopStoreBiz;
    
    @Autowired
    private SysLargeCustomerInfoBiz sysLargeCustomerInfoBiz;
    
    /**
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        
        //通过session获取当前登录 账号 判断 角色  用于 查询 
        //session
        AdminBO adminBO =  LoginUserUtils.getUser();
        //统计lc数量
        LcInfoSearchBO lcInfoSearchBO = new LcInfoSearchBO();
        //  为管理员则 不添加值
        int lcCount= sysLcInfoBiz.selectCountSysLcInfoByPrimary(lcInfoSearchBO);
        
        //统计vs数量
        VsInfoSearchBO vsInfoSearchBO = new VsInfoSearchBO();
        //为管理员则 不添加值
        int vsCount = sysVsInfoBiz.selectCountSysVsInfoByPrimary(vsInfoSearchBO);
        
        //统计us数量
        UserUsInfoSearchBo userUsInfoSearchBo = new UserUsInfoSearchBo();
        // 为管理员则 不添加值
        int usCount = sysUserInfoBiz.selectUserInfoByParamCount(userUsInfoSearchBo);
        
        //统计大客户列表
        LargeCustomerInfoSearchBO largeCustomerInfoSearchBO = new LargeCustomerInfoSearchBO();
        int largeCount = sysLargeCustomerInfoBiz.selectlargeCustomerInfoByPrimaryCount(largeCustomerInfoSearchBO);
        
        //统计se数量
        AdminInfoSearchBO adminInfoSearchBO = new AdminInfoSearchBO();
        //当前用为 管理员时 需要之传入 角色id  
        int seCount = sysAdminInfoBiz.selectSysSeInfoByParamCount(adminInfoSearchBO);
        
        // 统计a+数量
        AdminInfoSearchBO adminInfoSearchBORecord = new AdminInfoSearchBO();
        int aCount = sysAdminInfoBiz.selectSysAInfoByParamCount(adminInfoSearchBORecord);
        
        //统计DS数量
        PopStoreSearchBO popStoreSearchBoDS  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds  
        popStoreSearchBoDS.setPopStoreTypeSearch(Constants.PopStoreType.DS.v());
        int dsCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoDS);
        
        //统计SP数量
        PopStoreSearchBO popStoreSearchBoSP  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds  
        popStoreSearchBoSP.setPopStoreTypeSearch(Constants.PopStoreType.SP.v());
        int spCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoSP);
        
        //统计fs数量
        PopStoreSearchBO popStoreSearchBoSF  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds 
        popStoreSearchBoSF.setPopStoreTypeSearch(Constants.PopStoreType.FS.v());
        int fsCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoSF);
        
        
        request.setAttribute("lcCount", lcCount);
        request.setAttribute("vsCount", vsCount);
        request.setAttribute("usCount", usCount);
        request.setAttribute("seCount", seCount);
        request.setAttribute("aCount", aCount);
        request.setAttribute("spCount", spCount);
        request.setAttribute("dsCount", dsCount);
        request.setAttribute("fsCount", fsCount);
        request.setAttribute("largeCount", largeCount);
        //当前用户存在name展示name否则展示 登录名
        if(null != adminBO.getAdminName() && "" != adminBO.getAdminName()){
            request.setAttribute("userName", adminBO.getAdminName());
        }else{
            request.setAttribute("userName", adminBO.getAdminLoginName());
        }
        //编号
        request.setAttribute("user_num", adminBO.getAdminNum());
        return "user/sysindexcount/sysIndexCountPage";
    }
    
    /**
     * se获取列表
     * @param request
     * @return
     */
    @RequestMapping("/listse")
    public String listSe(HttpServletRequest request) {
        
        //通过session获取当前登录 账号 判断 角色  用于 查询 
        //session
         
        AdminBO adminBO = LoginUserUtils.getUser();
        String user_num = adminBO.getAdminNum();
        //统计lc数量
        LcInfoSearchBO lcInfoSearchBO = new LcInfoSearchBO();
        //当前用户角色为se时  setSeAdminNum()  
        lcInfoSearchBO.setSeAdminNum(user_num);
        int lcCount= sysLcInfoBiz.selectCountSysLcInfoByPrimary(lcInfoSearchBO);
        
        //统计lc未审核数量
        LcInfoSearchBO lcInfoSearchBORrcord = new LcInfoSearchBO();
        //当前用户角色为se时  setSeAdminNum()  
        lcInfoSearchBORrcord.setSeAdminNum(user_num);
        lcInfoSearchBORrcord.setAuthenticationStateSearch(Constants.AuthenticationState.Audit.v());// 存入未审核
        int lcNoCount= sysLcInfoBiz.selectCountSysLcInfoByPrimary(lcInfoSearchBORrcord);
        
        //统计vs数量
        VsInfoSearchBO vsInfoSearchBO = new VsInfoSearchBO();
        //当前用户角色为se时  setSeAdminNum()  
        vsInfoSearchBO.setSeAdminNum(user_num);
        int vsCount = sysVsInfoBiz.selectCountSysVsInfoByPrimary(vsInfoSearchBO);
        
        //统计vs未审核数量
        VsInfoSearchBO vsInfoSearchBORecord = new VsInfoSearchBO();
        //当前用户角色为se时  setSeAdminNum()  
        vsInfoSearchBORecord.setSeAdminNum(user_num);
        vsInfoSearchBORecord.setAuthenticationStateSearch(Constants.AuthenticationState.Audit.v());// 存入未审核
        int vsNoCount = sysVsInfoBiz.selectCountSysVsInfoByPrimary(vsInfoSearchBORecord);
        
        //统计us数量
        UserUsInfoSearchBo userUsInfoSearchBo = new UserUsInfoSearchBo();
        //当前用户角色为se时  userUsInfoSearchBo.setSeNum(seNum);  
        userUsInfoSearchBo.setSeNum(user_num);
        int usCount = sysUserInfoBiz.selectUserInfoByParamCount(userUsInfoSearchBo);
        
        //统计DS数量
        PopStoreSearchBO popStoreSearchBoDS  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds  当前 用户角色为se 时 需要 添加 popstore类型 及 setSeAdminNumSearch
        popStoreSearchBoDS.setPopStoreTypeSearch(Constants.PopStoreType.DS.v());
        popStoreSearchBoDS.setSeAdminNumSearch(user_num);
        int dsCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoDS);
        
        //统计SP数量
        PopStoreSearchBO popStoreSearchBoSP  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds  当前 用户角色为se 时 需要 添加 popstore类型 及 setSeAdminNumSearch
        popStoreSearchBoSP.setPopStoreTypeSearch(Constants.PopStoreType.SP.v());
        popStoreSearchBoSP.setSeAdminNumSearch(user_num);
        int spCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoSP);
        
        //统计fs数量
        PopStoreSearchBO popStoreSearchBoSF  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds  当前 用户角色为se 时 需要 添加 popstore类型 及 setSeAdminNumSearch
        popStoreSearchBoSF.setPopStoreTypeSearch(Constants.PopStoreType.FS.v());
        popStoreSearchBoSF.setSeAdminNumSearch(user_num);
        int fsCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoSF);
        
        request.setAttribute("lcCount", lcCount);
        request.setAttribute("vsCount", vsCount);
        request.setAttribute("usCount", usCount);
        
        request.setAttribute("spCount", spCount);
        request.setAttribute("dsCount", dsCount);
        request.setAttribute("fsCount", fsCount);
        //lc未审核状态数量
        request.setAttribute("lcNoCount", lcNoCount);
        //vs未审核状态数量
        request.setAttribute("vsNoCount", vsNoCount);
        
      //当前用户存在name展示name否则展示 登录名
        if(null != adminBO.getAdminName() && "" != adminBO.getAdminName()){
            request.setAttribute("userName", adminBO.getAdminName());
        }else{
            request.setAttribute("userName", adminBO.getAdminLoginName());
        }
        //手机号
        request.setAttribute("user_moble", adminBO.getAdminMobile());
        //编号
        request.setAttribute("user_num", adminBO.getAdminNum());
        return "user/sysindexcount/sysIndexCountPage_se";
    } 
    
    
    /**
     * A+用户查询首页
     * @param request
     * @return
     */
    @RequestMapping("/listai")
    public String listAi(HttpServletRequest request) {
        
        //通过session获取当前登录 账号 判断 角色  用于 查询 
        //session
        AdminBO adminBO = LoginUserUtils.getUser();
        String user_num = adminBO.getAdminNum();
        //统计lc数量
        LcInfoSearchBO lcInfoSearchBO = new LcInfoSearchBO();
        //  当前用户为a+角色时 setaAdminNum(aiAdminNum)  为管理员则 不添加值
        lcInfoSearchBO.setAiAdminNum(user_num);
        int lcCount= sysLcInfoBiz.selectCountSysLcInfoByPrimary(lcInfoSearchBO);
        
        //统计lc未审核数量
        LcInfoSearchBO lcInfoSearchBORrcord = new LcInfoSearchBO();
        // 当前用户为a+角色时 setaAdminNum(aiAdminNum)  为管理员则 不添加值
        lcInfoSearchBORrcord.setAiAdminNum(user_num);
        lcInfoSearchBORrcord.setAuthenticationStateSearch(Constants.AuthenticationState.Audit.v());// 存入未审核
        int lcNoCount= sysLcInfoBiz.selectCountSysLcInfoByPrimary(lcInfoSearchBORrcord);
        
        //统计vs数量
        VsInfoSearchBO vsInfoSearchBO = new VsInfoSearchBO();
        //  当前用户为a+角色时 setaAdminNum(aiAdminNum)  为管理员则 不添加值
        vsInfoSearchBO.setaAdminNum(user_num);
        int vsCount = sysVsInfoBiz.selectCountSysVsInfoByPrimary(vsInfoSearchBO);
        
        //统计vs未审核数量
        VsInfoSearchBO vsInfoSearchBORecord = new VsInfoSearchBO();
        //  当前用户为a+角色时 setaAdminNum(aiAdminNum)  为管理员则 不添加值
        vsInfoSearchBORecord.setaAdminNum(user_num);
        vsInfoSearchBORecord.setAuthenticationStateSearch(Constants.AuthenticationState.Audit.v());// 存入未审核
        int vsNoCount = sysVsInfoBiz.selectCountSysVsInfoByPrimary(vsInfoSearchBORecord);
        
        //统计us数量
        UserUsInfoSearchBo userUsInfoSearchBo = new UserUsInfoSearchBo();
        //  当前用户为a+角色时 userUsInfoSearchBo.setaNum(aNum)  为管理员则 不添加值
        userUsInfoSearchBo.setaNum(user_num);
        int usCount = sysUserInfoBiz.selectUserInfoByParamCount(userUsInfoSearchBo);
        
        //统计se数量
        AdminInfoSearchBO adminInfoSearchBO = new AdminInfoSearchBO();
        //当前用户角色为A+时 需要传入 角色id  及  adminInfoSearchBO.setaAiAdminNum(aAiAdminNum);
        adminInfoSearchBO.setaAiAdminNum(user_num);
        int seCount = sysAdminInfoBiz.selectSysSeInfoByParamCount(adminInfoSearchBO);
        
        //统计se未审核数量
        AdminInfoSearchBO adminInfoSearchBONo = new AdminInfoSearchBO();
        //当前用户角色为A+时 需要传入 角色id  及  adminInfoSearchBO.setaAiAdminNum(aAiAdminNum);
        adminInfoSearchBONo.setaAiAdminNum(user_num);
        adminInfoSearchBONo.setIsAuthentication(false);
        int seNoCount = sysAdminInfoBiz.selectSysSeInfoByParamCount(adminInfoSearchBONo);
        
        //统计DS数量
        PopStoreSearchBO popStoreSearchBoDS  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds  
        // 当前登录用户角色为A+ 时  添加 popstore类型 及setaAdminNumSearch
        popStoreSearchBoDS.setPopStoreTypeSearch(Constants.PopStoreType.DS.v());
        popStoreSearchBoDS.setaAdminNumSearch(user_num);
        int dsCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoDS);
        
        //统计SP数量
        PopStoreSearchBO popStoreSearchBoSP  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds  
        // 当前登录用户角色为A+ 时  添加 popstore类型 及setaAdminNumSearch
        popStoreSearchBoSP.setPopStoreTypeSearch(Constants.PopStoreType.SP.v());
        popStoreSearchBoSP.setaAdminNumSearch(user_num);
        int spCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoSP);
        
        //统计fs数量
        PopStoreSearchBO popStoreSearchBoSF  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds  
        // 当前登录用户角色为A+ 时  添加 popstore类型 及setaAdminNumSearch
        popStoreSearchBoSF.setPopStoreTypeSearch(Constants.PopStoreType.FS.v());
        popStoreSearchBoSF.setaAdminNumSearch(user_num);
        int fsCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoSF);
        
        
        request.setAttribute("lcCount", lcCount);
        request.setAttribute("vsCount", vsCount);
        request.setAttribute("usCount", usCount);
        request.setAttribute("seCount", seCount);
        request.setAttribute("spCount", spCount);
        request.setAttribute("dsCount", dsCount);
        request.setAttribute("fsCount", fsCount);
        //lc未审核状态数量
        request.setAttribute("lcNoCount", lcNoCount);
        //vs未审核状态数量
        request.setAttribute("vsNoCount", vsNoCount);
        //se未审核状态数量
        request.setAttribute("seNoCount", seNoCount);
        
        //当前用户存在name展示name否则展示 登录名
        if(null != adminBO.getAdminName() && "" != adminBO.getAdminName()){
            request.setAttribute("userName", adminBO.getAdminName());
        }else{
            request.setAttribute("userName", adminBO.getAdminLoginName());
        }
        //手机号
        request.setAttribute("user_moble", adminBO.getAdminMobile());
        //编号
        request.setAttribute("user_num", adminBO.getAdminNum());
        return "user/sysindexcount/sysIndexCountPage_a";
    }
	
    /**
     * @param request
     * @return
     */
    @RequestMapping("/listpop")
    public String listPop(HttpServletRequest request) {
        
        //通过session获取当前登录 账号 判断 角色  用于 查询 
        //统计DS数量
        PopStoreSearchBO popStoreSearchBoDS  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds  
        popStoreSearchBoDS.setPopStoreTypeSearch(Constants.PopStoreType.DS.v());
        int dsCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoDS);
        
        //统计SP数量
        PopStoreSearchBO popStoreSearchBoSP  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds  
        popStoreSearchBoSP.setPopStoreTypeSearch(Constants.PopStoreType.SP.v());
        int spCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoSP);
        
        //统计fs数量
        PopStoreSearchBO popStoreSearchBoSF  = new PopStoreSearchBO();
        //当前登录用户 为管理员 需要添加 查询popstore类型为 ds 
        popStoreSearchBoSF.setPopStoreTypeSearch(Constants.PopStoreType.FS.v());
        int fsCount = sysPopStoreBiz.selectPopStroeDsSpFsByByParamCount(popStoreSearchBoSF);
        
        request.setAttribute("spCount", spCount);
        request.setAttribute("dsCount", dsCount);
        request.setAttribute("fsCount", fsCount);
        
        AdminBO adminBO =  LoginUserUtils.getUser();
        //当前用户存在name展示name否则展示 登录名
        if(null != adminBO.getAdminName() && "" != adminBO.getAdminName()){
            request.setAttribute("userName", adminBO.getAdminName());
        }else{
            request.setAttribute("userName", adminBO.getAdminLoginName());
        }
        //编号
        request.setAttribute("user_num", adminBO.getAdminNum());
        return "user/sysindexcount/sysIndexCountPage_pop";
    }
    
    
}
