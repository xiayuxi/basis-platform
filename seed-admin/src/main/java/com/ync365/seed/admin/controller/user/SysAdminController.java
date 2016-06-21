package com.ync365.seed.admin.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.controller.base.BaseController;
import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.Json;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysRoleBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.AdminSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.SysAdminInfoBO;
import com.ync365.seed.bussiness.modules.user.entity.SysAdmin;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminService;
import com.ync365.seed.bussiness.modules.user.entity.SysRole;

@Controller
@RequestMapping("/admin")
public class SysAdminController extends BaseController{
	@Autowired
	private SysAdminBiz adminBiz;
	
	@Autowired
	private SysRoleBiz sysRoleBiz;
	
	
	/**
     * list请求  
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        return "user/admin/sysAdminList";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public Grid dataGrid(HttpServletRequest request , AdminSearchBO adminSearchBO ,PageFilter filter) {
        Grid grid = new Grid();
        List<AdminBO> list = new ArrayList<AdminBO>();
        long count = 0;
        try {
            adminSearchBO.setPageIndex(filter.getStartIndex());
            adminSearchBO.setPageSize(filter.getRows());
            list = adminBiz.selectSysAdminListByPrimary(adminSearchBO);
            count = adminBiz.selectSysAdminCountByPrimary(adminSearchBO);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        grid.setRows(list);
        grid.setRecords(count);
        grid.setPageSize(filter.getRows());
        grid.setPage(filter.getPage());
        return grid;
    }
    
    
	@RequestMapping("/changepwdPage")
	public String chagePwdPage() {
		
		return "user/admin/sysChangePwd";
	}

	@RequestMapping("/changepwd")
	@LogAnnotation(operatingContent="修改密码")
	public String chagePwd( @RequestParam("oldPassword") String oldPwd,
			@RequestParam("newPassword") String newPwd, @RequestParam("rePassword") String rePwd, Model model) {
		//@RequestParam("adminNum") String adminNum,
	    AdminBO adminBO =LoginUserUtils.getUser();
	    String adminNum = adminBO.getAdminNum();
	    boolean bool = false;
		if (newPwd.equals(rePwd)) {
			bool = adminBiz.changePwd(adminNum, oldPwd, newPwd);
		}
		if (bool) {
			SecurityUtils.getSubject().logout();
			return "redirect:/login";
		} else {
			model.addAttribute("adminNum", adminNum);
			model.addAttribute("error", "修改失败");
			return "user/admin/sysChangePwd";
		}
	}
	
	/**重置密码
	 * @author xieang
	 * 2015年10月10日
	 * @param sysUser
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resetPassword")
	public String resetPassword(SysAdmin sysUser){
		SysAdmin record = new SysAdmin();
		record.setAdminNum(sysUser.getAdminNum());
		record.setAdminPassword(sysUser.getAdminPassword());
		int i = adminBiz.updateByPrimaryKeySelective(record);
		if(i==1){
			return "OK";
		}else{
			return "ERR";
		}
	}
	@ResponseBody
	@RequestMapping("/lockedUser")
	public String lockedUser(String adminNum){
		SysAdmin record = new SysAdmin();
		record.setAdminNum(adminNum);
		SysAdmin sysUser = adminBiz.selectByNum(adminNum);
		if(sysUser==null||sysUser.getIsFrozen()==null){
			return "ERR";
		}
		if(sysUser.getIsFrozen()){
			record.setIsFrozen(false);
		}else{
			record.setIsFrozen(true);
		}
		int i = adminBiz.updateByPrimaryKeySelective(record);
		if(i==1){
			return "OK";
		}else{
			return "ERR";
		}
	}
	@RequestMapping("/isAuthentication")
	public String isAuthentication(String adminNum,Boolean authenticationState,String infoMeg){
	    AdminBO bo = LoginUserUtils.getUser();
        if(StringUtils.isNotBlank(adminNum)&&authenticationState!=null){
            int a = adminBiz.isAuthentication(adminNum,authenticationState,infoMeg,bo);
        }
        return "redirect:list";
	}
	
	
	/**
     * 新增
     * @param request
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage(HttpServletRequest request){
        return "user/admin/sysAdminAdd";
    }
    
    
    /**
     * 新增保存方法
     * @param request
     * @return
     */
    @RequestMapping("/add")
    @LogAnnotation(operatingContent="权限管理>账号管理>新增账号")
    public String add(HttpServletRequest request,AdminBO record){
        int temp = 0 ; 
        try {
            temp = adminBiz.insertAdminByPrimary(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/list";
    }
    
    
    
    /**
     * 编辑
     * @param request
     * @return
     */
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,String adminNum){
        SysAdmin sysAdmin = null ;
        List<SysRole> sysRolelist = new ArrayList<SysRole>();
        List<SysRole> listsysRole = new ArrayList<SysRole>();
        List<SysAdminService> sysAdminServiceList = new ArrayList<SysAdminService>();
        List<SysAdminService> sysAdminServiceListA = new ArrayList<SysAdminService>();
        SysAdmin sysAdminRocred = null;
        try { 
            sysAdmin = adminBiz.selectByNum(adminNum);
            //查询关联的角色
            sysRolelist = sysRoleBiz.selectSysRoleByAdminNum(adminNum);
            if(null != sysRolelist && sysRolelist.size() > 0 ){
                for(SysRole temp: sysRolelist){
                    if(null != temp.getRoleName() && "" != temp.getRoleName() && "SE".equals(temp.getRoleName())){
                        sysAdminServiceList = adminBiz.selectServiceListByNum(adminNum);
                        Map<String,Object> map1= new HashMap<String,Object>();
                        map1.put("seNum", adminNum);
                        sysAdminRocred = adminBiz.selectAdminByParmartSeNum(map1);
                    }
                    if(null != temp.getRoleName() && "" != temp.getRoleName() && "A+".equals(temp.getRoleName())){
                        sysAdminServiceListA = adminBiz.selectServiceListByNum(adminNum);
                    }
                }
            }
            //查询所有角色列表
            Map<String,Object> map = new HashMap<String,Object>();
            listsysRole = sysRoleBiz.selectRoleAllByParam(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("sysAdmin", sysAdmin!= null ?sysAdmin : new SysAdmin());
        request.setAttribute("sysRolelist", sysRolelist);
        request.setAttribute("listsysRole", listsysRole);
        request.setAttribute("sysAdminServiceList", sysAdminServiceList);
        request.setAttribute("sysAdminServiceListA", sysAdminServiceListA);
        request.setAttribute("sysAdminRocred", sysAdminRocred != null ? sysAdminRocred : new SysAdmin());
        return "user/admin/sysAdminEdit";
    }
    
    
    /**
     * 编辑保存方法
     * @param request
     * @return
     */
    @RequestMapping("/editPage")
    @LogAnnotation(operatingContent="权限管理>账号管理>编辑账号")
    public String editPage(HttpServletRequest request,AdminBO record){
        int temp = 0 ; 
        try {
            temp = adminBiz.updateAdminByPrimary(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/list";
    }
    
    /**
     * 首页 统计页面
     * @param request
     * @return
     */
    @RequestMapping("/indexCount")
    public String indexCount(HttpServletRequest request){
        AdminSearchBO record = new AdminSearchBO();
        int userCount = adminBiz.selectSysAdminCountByPrimary(record);
        Map<String,Object> map = new HashMap<String ,Object>();
        int roleCount = sysRoleBiz.selectPageCount(map);
        
        request.setAttribute("userCount",userCount);
        request.setAttribute("roleCount", roleCount);
        return "user/admin/sysAdmin_index";
    }
    
    /**
     * 根据编号 查询A+ 信息
     * @param request
     * @return
     */
    @RequestMapping("/selectAiInfo")
    @ResponseBody
    public SysAdminInfoBO selectAiInfo(HttpServletRequest request,String aAdminNum){
        SysAdminInfoBO record = null;
        try {
            record = adminBiz.selectAiInfo(aAdminNum);
            if(record==null||record.getIsAuthentication()==null||record.getIsFrozen()==null
                    ||record.getIsAuthentication()!=true||record.getIsFrozen()){
                record = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null == record){
            record = new SysAdminInfoBO();
        }
        return record;
    }
    
    /**
     * 用户账号  手机号添加 时 校验是否已经注册 
     * @param request
     * @param str
     * @param pho
     * @return
     */
    @RequestMapping("/selectAdminByNameOrNum")
    @ResponseBody
    public Json selectAdminByNameOrNum(HttpServletRequest request,String str,String pho){
        Json json = new Json();
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("adminLoginName", str);
            map.put("adminMobile", pho);
            int   temp= adminBiz.selectCountAdminByNameOrNum(map);
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
}
