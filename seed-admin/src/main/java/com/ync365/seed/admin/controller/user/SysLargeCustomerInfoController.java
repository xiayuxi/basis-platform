package com.ync365.seed.admin.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.user.biz.SysLargeCustomerInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysLargeCustomerPropertyInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.LargeCustomerInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.LargeCustomerInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.LargeCustomerValueInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.entity.SysAuthenInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLargeCustomerPropertyInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysRole;
import com.ync365.seed.utils.Constants;



/**
 * 
 * @author leixc
 * 大客户认证 controller
 *
 */
@Controller
@RequestMapping("/syslargecust")
public class SysLargeCustomerInfoController {
    
    @Autowired
    private SysLargeCustomerInfoBiz sysLargeCustomerInfoBiz;

    @Autowired
    private SysLargeCustomerPropertyInfoBiz sysLargeCustomerPropertyInfoBiz;
    
    @Autowired
    private SysUserInfoBiz sysUserInfoBiz;

    /**
     * list列表
    * @param request
    * @return
    */
   @RequestMapping("/list")
   @LogAnnotation(operatingContent="用户管理模块>大客户认证>客户列表页")
   public String list(HttpServletRequest request) {
       
       return "user/syslargecustomerinfo/sysLargeCustomerInfoList";
   }
   
   
   @RequestMapping("/grid")
   @ResponseBody
   @LogAnnotation(operatingContent="用户管理模块>大客户认证>查询")
   public Grid dataGrid(HttpServletRequest request , LargeCustomerInfoSearchBO largeCustomerInfoSearchBO , PageFilter filter) {
       Grid grid = new Grid();
       List<LargeCustomerInfoBO> list = new ArrayList<LargeCustomerInfoBO>();
       
       long count = 0;
       largeCustomerInfoSearchBO.setPageIndex(filter.getStartIndex());
       largeCustomerInfoSearchBO.setPageSize(filter.getRows());
       
       AdminBO adminBO =LoginUserUtils.getUser();
       Integer roleId = adminBO.getRoleIdd();
       if(roleId==Constants.UserRole.SE.v()){
           largeCustomerInfoSearchBO.setSeAdminNumSearch(adminBO.getAdminNum());
       }else if(roleId==Constants.UserRole.ADMIN.v()){
           
       }else{
    	   return grid;
       }
       
       try {
           list = sysLargeCustomerInfoBiz.selectlargeCustomerInfoByPrimary(largeCustomerInfoSearchBO);
           count = sysLargeCustomerInfoBiz.selectlargeCustomerInfoByPrimaryCount(largeCustomerInfoSearchBO);
       } catch (Exception e) {
           e.printStackTrace();
       }
       grid.setRows(list);
       grid.setRecords(count);
       grid.setPageSize(filter.getRows());
       grid.setPage(filter.getPage());
       return grid;
   }
   
   /**
    * 获取大客户认证设置
    * @param request
    * @return
    */
   @RequestMapping("/getrecommended")
   @LogAnnotation(operatingContent="用户管理模块>大客户认证>认证设置")
   public String getRecommended(HttpServletRequest request){
       List<SysLargeCustomerPropertyInfo>  listLarge = new ArrayList<SysLargeCustomerPropertyInfo>();
       try {
           listLarge = sysLargeCustomerPropertyInfoBiz.selectAll();
       } catch (Exception e) {
           e.printStackTrace();
       }
       request.setAttribute("listLarge", listLarge);
       return "user/syslargecustomerinfo/sysLargeCustomerInfo_recom";
   }
   
   /**
    * 逻辑删除认证信息
    * @param request
    * @return
    */
   @RequestMapping("/deleteInfoById")
   public String deletePropertyInfoById(Integer id){
       SysLargeCustomerPropertyInfo record = new SysLargeCustomerPropertyInfo();
       record.setId(id);
       record.setIsDel(true);
       try {
           int a  = sysLargeCustomerPropertyInfoBiz.updateByPrimaryKeySelective(record);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return "redirect:/syslargecust/getrecommended";
   }
   
   /**
    * 添加认证设置信息
    * @param request
    * @return
    */
   @RequestMapping("/addPage")
   public String addPage(SysLargeCustomerPropertyInfo record){
       record.setIsDel(false);
       try {
           int a  = sysLargeCustomerPropertyInfoBiz.insertSelective(record);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return "redirect:/syslargecust/getrecommended";
   }
   
 /*  @RequestMapping("/updatePageTg")
   @LogAnnotation(operatingContent="用户管理模块>大客户认证>查看客户页面>通过")
   public String updatePageTg(String userNum){
       SysLargeCustomerInfo record = new SysLargeCustomerInfo();
       AdminBO bo = LoginUserUtils.getUser();
       record.setUserNum(userNum);
       //设置大客户通过  state 为1 
       record.setState(Constants.AuthenticationState.Success.v());
       record.setAuthenticationTime(new Date());
       try {
           int a  = sysLargeCustomerInfoBiz.updateLargeCustomerPrimary(record,null,bo);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return "redirect:/syslargecust/list";
   }*/
   
   
   /**
    * 大客户审核 通过或拒绝 方法
    * @param request
    * @return
    */
   @RequestMapping("/updatePageFailOrTg")
   @LogAnnotation(operatingContent="用户管理模块>大客户认证>查看客户页面>审核")
   public String updatePageFailOrTg(String userNum,String failInfo,String stat){
       SysLargeCustomerInfo record = new SysLargeCustomerInfo();
       AdminBO bo = LoginUserUtils.getUser();
       record.setUserNum(userNum);
       //设置大客户通过  state 为2
      /* record.setState(Constants.AuthenticationState.Failure.v());*/
       record.setAuthenticationTime(new Date());
       try {
           int a  = sysLargeCustomerInfoBiz.updateLargeCustomerPrimary(record,failInfo,bo,stat);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return "redirect:/syslargecust/list";
   }
   
   /**
    * 查看审核资料
    * @param userNum
    * @return
    */
   @RequestMapping("/cheackPage")
   @LogAnnotation(operatingContent="用户管理模块>大客户认证>查看客户页面")
   public String cheackPage(@RequestParam("userNum") String userNum ,HttpServletRequest request){
       UserInfoBO userInfoBO = null;
       List<LargeCustomerValueInfoBO> largeCustomerValueInfoBOList = null ;
       List<SysAuthenInfo> sysAuthenInfoList =null;
       try {
           userInfoBO = sysUserInfoBiz.getUserInfoAllData(userNum);
           largeCustomerValueInfoBOList = sysLargeCustomerInfoBiz.getLargeCustomerInfoByUserNum(userNum);
           //审核失败查询原因
           if(null !=userInfoBO && null != userInfoBO.getState() 
                   && userInfoBO.getState() == Constants.AuthenticationState.Failure.v() ){
               sysAuthenInfoList =  sysLargeCustomerInfoBiz.selectSysAuthenInfoListByPrimary(userNum ,0);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       request.setAttribute("userInfoBO", userInfoBO != null? userInfoBO: new UserInfoBO());
       request.setAttribute("largeCustomerValueInfoBOList", largeCustomerValueInfoBOList);
       request.setAttribute("sysAuthenInfoList", sysAuthenInfoList);
       return "user/syslargecustomerinfo/sysLargeCustomerInfo_page";
   }
   
   /**
    * 时间自动格式化
    * @param bin
    */
   @InitBinder
   public void InitBinder(ServletRequestDataBinder bin) {
       bin.registerCustomEditor(Date.class, new CustomDateEditor( new SimpleDateFormat("yyyy-MM-dd"), true));

   }
    
}
