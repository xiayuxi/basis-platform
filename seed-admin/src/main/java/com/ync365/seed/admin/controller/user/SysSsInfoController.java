package com.ync365.seed.admin.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.Json;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysSsInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysSsPopStoreRelationshipBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.SsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.SsInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.SysAdminInfoBO;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;
import com.ync365.seed.bussiness.modules.user.entity.SysSsInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysSsPopStoreRelationship;
import com.ync365.seed.utils.Constants;


/**
 *ss_info controller
 * @author leixc
 *
 */
@Controller
@RequestMapping("/systwosinfo")
public class SysSsInfoController {
    
    @Autowired
    private SysSsInfoBiz sysSsInfoBiz;
    
    @Autowired
    private SysPopStoreBiz sysPopStoreBiz;
    
    @Autowired
    private SysSsPopStoreRelationshipBiz ssPopStorerelationshipBiz;
    /**
     * list请求
     * @param request
     * @return
     */
    @RequestMapping("/list")
    @LogAnnotation(operatingContent="用户管理模块>SS用户管理>列表页面")
    public String list(HttpServletRequest request) {
        return "user/sysSsinfo/sysSsinfoList";
    }
    
    @RequestMapping("/grid")
    @ResponseBody
    @LogAnnotation(operatingContent="用户管理模块>SS用户管理>查询")
    public Grid dataGrid(PageFilter filter ,SsInfoSearchBO ssInfoSearchBO) {
        Grid grid = new Grid();
        long count = 0;
        List<SsInfoBO> list = new ArrayList<SsInfoBO>();
        try {
            ssInfoSearchBO.setPageIndex(filter.getStartIndex());
            ssInfoSearchBO.setPageSize(filter.getRows());
            
	        AdminBO adminBO =LoginUserUtils.getUser();
	        Integer roleId = adminBO.getRoleIdd();
	        
	        if (roleId!=Constants.UserRole.ADMIN.v()) {
	        	ssInfoSearchBO.setUserNumSearch(adminBO.getAdminNum());
	        }
            
            list = sysSsInfoBiz.selectSsInfoByParam(ssInfoSearchBO);
            count = sysSsInfoBiz.selectSsInfoByParamCount(ssInfoSearchBO);
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
     * 新增
     * @param request
     * @return
     */
    @RequestMapping("/addPage")
    @LogAnnotation(operatingContent="用户管理模块>SS用户管理>添加页面")
    public String addPage(HttpServletRequest request){
        return "user/sysSsinfo/sysSsinfoAdd";
    }
    
    /**
     * 新增保存方法
     * @param request
     * @return
     */
    @RequestMapping("/add")
    @LogAnnotation(operatingContent="用户管理模块>SS用户管理>添加页面>保存添加")
    public String add(HttpServletRequest request,SysSsInfo record ,SsInfoSearchBO ssInfoSearchBO){
        try {
            int a = sysSsInfoBiz.insertSsInfoAndPopStoreRelationship(record,ssInfoSearchBO);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/systwosinfo/list";
    }
    
    /**
     * 编辑
     * @param request
     * @return
     */
    @RequestMapping("/editPage")
    @LogAnnotation(operatingContent="用户管理模块>SS用户管理>编辑页面")
    public String editPage(HttpServletRequest request ,String userNum){
        SysSsInfo sysSsinfo = null;
        List<PopStoreBO> sysPopStoreList = new ArrayList<PopStoreBO>();
        try {
            sysSsinfo = sysSsInfoBiz.selectByPrimaryKey(userNum);
            sysPopStoreList = sysPopStoreBiz.selectPopStoreBySsUserNum(userNum);
        }catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("sysSsinfo", sysSsinfo);
        request.setAttribute("sysPopStoreList", sysPopStoreList);
        return "user/sysSsinfo/sysSsinfoEdit";
    }
    
    /**
     * 编辑保存方法
     * @param request
     * @return
     */
    @RequestMapping("/edit")
    @LogAnnotation(operatingContent="用户管理模块>SS用户管理>编辑页面>保存")
    public String edit(HttpServletRequest request,SysSsInfo record ,SsInfoSearchBO ssInfoSearchBO){
        try {
            int a = sysSsInfoBiz.updateSsInfoAndPopStoreRelationship(record,ssInfoSearchBO);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/systwosinfo/list";
    }
    
    /**
     * 激活 冻结
     * @param request
     * @param userNum
     * @return
     */
    @RequestMapping("/updateFrozen")
    @LogAnnotation(operatingContent="用户管理模块>SS用户管理>激活用户")
    public String updateFrozen(HttpServletRequest request,String userNum){
        SysSsInfo record = new SysSsInfo();
        record.setUserNum(userNum);
        
        int a = 0;
        try {
            SysSsInfo sysSsInfo = sysSsInfoBiz.selectByPrimaryKey(userNum);
            if(sysSsInfo.getIsFrozen()){
                record.setIsFrozen(false);
            }else{
                record.setIsFrozen(true);
            }
            a = sysSsInfoBiz.updateFrozenPrimaryKey(record);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/systwosinfo/list";
    }
    
    /**
     * 重置密码
     * @param request
     * @param userNum
     * @return
     */
    @RequestMapping("/updatePassword")
    @LogAnnotation(operatingContent="用户管理模块>SS用户管理>重置密码")
    public String updatePassword(HttpServletRequest request,String userNum){
        try {
            int a = sysSsInfoBiz.updateSsInfoPasswordPrimaryKey(userNum);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/systwosinfo/list";
    }
    
    /**
     * 查看
     * @param request
     * @param userNum
     * @return
     */
    @RequestMapping("/cheackPage")
    @LogAnnotation(operatingContent="用户管理模块>SS用户管理>查看")
    public String cheackPage(HttpServletRequest request ,String userNum){
        SysSsInfo sysSsinfo = null;
        List<PopStoreBO> sysPopStoreList = new ArrayList<PopStoreBO>();
        try {
            sysSsinfo = sysSsInfoBiz.selectByPrimaryKey(userNum);
            sysPopStoreList = sysPopStoreBiz.selectPopStoreBySsUserNum(userNum);
        }catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("sysSsinfo", sysSsinfo);
        request.setAttribute("sysPopStoreList", sysPopStoreList);
        return "user/sysSsinfo/sysSsinfoCheackPage";
    }
    
    
    /**
     * 分配
     * @param request
     * @param userNum
     * @param popStoreNum
     * @return
     */
    @RequestMapping("/updateDistribution")
    @LogAnnotation(operatingContent="用户管理模块>SS用户管理>分配>保存分配")
    public String updateDistribution(HttpServletRequest request ,String userNum ,String popStoreNum){
        SysSsInfo sysSsinfo = null;
        try {
            sysSsinfo =null ; sysSsInfoBiz.updateDistributionByPrimary(userNum , popStoreNum);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/systwosinfo/list";
    }
    
    /**
     * 获取 popStore by userNum 或 userMobile
     * @param request
     * @param userNumOrMobileSearch
     * @return
     */
    @RequestMapping("/selectStore")
    @ResponseBody
    @LogAnnotation(operatingContent="用户管理模块>SS用户管理>分配>搜索")
    public SysPopStore selectStore(HttpServletRequest request ,String userNumOrMobileSearch, String userNum){
        SysPopStore sysPopStore = null;
        SysSsPopStoreRelationship ssPopStoreRelation = null;
        try {
            sysPopStore = sysSsInfoBiz.selectPopStoreByPrimaryKey(userNumOrMobileSearch);
            if (null != sysPopStore) {
            	ssPopStoreRelation = ssPopStorerelationshipBiz.selectSsRelationByUserNumAndPopStoreNum(userNum, sysPopStore.getPopStoreNum());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
		
        if (null != ssPopStoreRelation) {
        	if (null != ssPopStoreRelation.getId()) {
        		sysPopStore.setId(0);
        	}
        }
        
        if(null == sysPopStore){
            sysPopStore = new SysPopStore();
        }
        return sysPopStore;
    }
    
    
    /**
     * 用户账号  手机号添加 时 校验是否已经注册 
     * @param request
     * @param userName
     * @param userMobile
     * @return
     */
    @RequestMapping("/selectSsInfoByNameOrNum")
    @ResponseBody
    public Json selectSsInfoByNameOrNum(HttpServletRequest request,String userName,String userMobile){
        Json json = new Json();
        try {
            SsInfoSearchBO  ssInfoSearchBO  = new SsInfoSearchBO();
            ssInfoSearchBO.setUserNameSearch(userName);
            ssInfoSearchBO.setUserMobileSearch(userMobile);
            int   temp= sysSsInfoBiz.selectSsInfoByParamCount(ssInfoSearchBO);
            if(0 == temp ){
                json.setSuccess(true);
                json.setMsg("ok");
            }else{
                json.setSuccess(false);
                json.setMsg("no");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
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
