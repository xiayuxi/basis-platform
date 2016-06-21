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
import com.ync365.seed.bussiness.modules.order.biz.OrderInfoBiz;
import com.ync365.seed.bussiness.modules.order.bo.OrderInfoBO;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysGrowKindBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysLcInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUsVsRelationshipBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.UserInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.UserUsInfoSearchBo;
import com.ync365.seed.bussiness.modules.user.entity.SysGrowKind;
import com.ync365.seed.bussiness.modules.user.entity.SysUsVsRelationship;
import com.ync365.seed.utils.Constants;


/**
 * lc controller
 * @author leixc
 *
 */
@Controller
@RequestMapping("/sysuserinfo")
public class SysUserInfoController {

	@Autowired
	private SysUserInfoBiz sysUserInfoBiz;
	
	@Autowired
	private SysUserBiz sysUserBiz;
	
	@Autowired
    private SysVsInfoBiz sysVsInfoBiz;
	
	@Autowired
    private SysLcInfoBiz sysLcInfoBiz;
	
	@Autowired
    private SysAdminInfoBiz sysAdminInfoBiz;
	
	@Autowired
	SysUsVsRelationshipBiz sysUsVsRelationshipBiz;
	
	@Autowired
    OrderInfoBiz orderInfoBiz;
	@Autowired
	SysGrowKindBiz sysGrowKindBiz;
	/**
	 * list请求
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	@LogAnnotation(operatingContent="用户管理模块>US列表>用户列表页面")
	public String list(HttpServletRequest request) {
		return "user/sysuserinfo/sysUserInfoList";
	}
	
	@RequestMapping("/grid")
	@ResponseBody
	@LogAnnotation(operatingContent="用户管理模块>US列表>查询")
	public Grid dataGrid(UserUsInfoSearchBo userUsInfoSearchBo,PageFilter filter) {
		Grid grid = new Grid();
		userUsInfoSearchBo.setPageIndex(filter.getStartIndex());
		userUsInfoSearchBo.setPageSize(filter.getRows());
		
		AdminBO adminBO =LoginUserUtils.getUser();
        Integer roleId = adminBO.getRoleIdd();
        if(roleId==Constants.UserRole.SE.v()){
        	userUsInfoSearchBo.setSeNum(adminBO.getAdminNum());
        }else if(roleId==Constants.UserRole.ADMIN.v()){
        }else if(roleId==Constants.UserRole.APlus.v()){
        	userUsInfoSearchBo.setaNum(adminBO.getAdminNum());
        }else{
        	return grid;
        }
        
		List<UserInfoBO> list = sysUserInfoBiz.selectUserInfoByParam(userUsInfoSearchBo);
		Integer count = sysUserInfoBiz.selectUserInfoByParamCount(userUsInfoSearchBo);
		grid.setRows(list);
		grid.setRecords(count.longValue());
		grid.setPageSize(filter.getRows());
		grid.setPage(filter.getPage());
		return grid;
	}
	
	/**
	 * 查看
	 * @param userNum
	 * @param request
	 * @return
	 */
	@RequestMapping("/cheackPage")
	@LogAnnotation(operatingContent="用户管理模块>US列表>查询用户页面")
    public String cheackPage(@RequestParam("userNum") String userNum,HttpServletRequest request) {
		UserInfoBO  userInfoBO = null; 
		List<OrderInfoBO> OrderInfoBOList = new ArrayList<OrderInfoBO>();
        try {
        	userInfoBO = sysUserInfoBiz.getUserInfoAllData(userNum);
        	OrderInfoBOList = orderInfoBiz.getOrderListByUserNum(userNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("userInfoBO", userInfoBO != null ? userInfoBO :  new UserInfoBO());
        request.setAttribute("OrderInfoBOList", OrderInfoBOList);
        return "user/sysuserinfo/sysUserInfoPage";
    }
	
	/**编辑页面
	 * @author xieang
	 * 2015年10月10日
	 * @return
	 */
	@RequestMapping("/editPage")
	@LogAnnotation(operatingContent="用户管理模块>US列表>编辑用户页面")
	public String editPage(@RequestParam("userNum") String userNum,HttpServletRequest request) {
		UserInfoBO  userInfoBO = null; 
		List<SysGrowKind>  sysGrowKindList = null; 
        try {
        	userInfoBO = sysUserInfoBiz.getUserInfoAllData(userNum);
        	sysGrowKindList = sysGrowKindBiz.getGrowKind();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null != userInfoBO ){
            request.setAttribute("userInfoBO", userInfoBO);
            request.setAttribute("sysGrowKindList", sysGrowKindList);
        }else{
            request.setAttribute("userInfoBO", new UserInfoBO());
            request.setAttribute("sysGrowKindList", sysGrowKindList);
        }
		return "user/sysuserinfo/sysUserInfoEditPage";
	}
	
	/**保存
	 * @author xieang
	 * 2015年10月10日
	 * @param userInfoBO
	 * @return
	 */
	@RequestMapping("/edit")
	@LogAnnotation(operatingContent="用户管理模块>US列表>保存编辑用户")
	public String edit(UserInfoBO userInfoBO){
		sysUserInfoBiz.updateUsInfo(userInfoBO);
		return "redirect:list";
	}
	
	
	
	/**us绑定Vs
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 */
	@RequestMapping("/bindingVs")
	@LogAnnotation(operatingContent="用户管理模块>US列表>绑定Vs")
	public String bindingVs(SysUsVsRelationship sysUsVsRelationship){
		sysUsVsRelationshipBiz.insertAndUpdate(sysUsVsRelationship.getUsNum(), sysUsVsRelationship.getVsNum());
		return "redirect:list";
	}
	 /**
     * 时间自动格式化
     * @param bin
     */
    @InitBinder
    public void InitBinder(ServletRequestDataBinder bin) {
        bin.registerCustomEditor(Date.class, new CustomDateEditor( new SimpleDateFormat("yyyy-MM-dd"), true));

    }
    
    
    
    /**
     * list请求     获取测土培肥统计 
     * @param request
     * @return
     */
    @RequestMapping("/listctpf")
    @LogAnnotation(operatingContent="用户管理模块>测土配肥统计>测土配肥列表页面")
    public String listCtpf(HttpServletRequest request) {
        return "user/sysuserinfo/sysUserInfoList_ctpf";
    }
    
    @RequestMapping("/gridctpf")
    @ResponseBody
    @LogAnnotation(operatingContent="用户管理模块>测土配肥统计>查询")
    public Grid dataGridCtpf(UserUsInfoSearchBo userUsInfoSearchBo,PageFilter filter) {
        Grid grid = new Grid();
        List<UserInfoBO> list = new ArrayList<UserInfoBO>();
        long count = 0;
        try {
            userUsInfoSearchBo.setPageIndex(filter.getStartIndex());
            userUsInfoSearchBo.setPageSize(filter.getRows());
            list = sysUserInfoBiz.selectUserInfoCtpfByParamy(userUsInfoSearchBo);
            count = sysUserInfoBiz.selectUserInfoCtpfByParamyCount(userUsInfoSearchBo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        grid.setRows(list);
        grid.setRecords(count);
        grid.setPageSize(filter.getRows());
        grid.setPage(filter.getPage());
        return grid;
    }
}
