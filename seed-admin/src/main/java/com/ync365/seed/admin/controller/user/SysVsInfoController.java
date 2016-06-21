package com.ync365.seed.admin.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.ync365.seed.admin.vo.user.ChangeVo;
import com.ync365.seed.bussiness.modules.common.biz.RegionBiz;
import com.ync365.seed.bussiness.modules.order.biz.OrderInfoBiz;
import com.ync365.seed.bussiness.modules.order.bo.OrderInfoBO;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminServiceBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysDoWorkBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysLcInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUsVsRelationshipBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.VsInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysDoWork;
import com.ync365.seed.bussiness.modules.user.entity.SysLcInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysVsInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysVsLcRelationship;
import com.ync365.seed.utils.Constants;

import freemarker.template.utility.NullArgumentException;


/**
 * lc controller
 * @author leixc
 *
 */
@Controller
@RequestMapping("/sysvsinfo")
public class SysVsInfoController {

	@Autowired
	private SysVsInfoBiz sysVsInfoBiz;
	
	@Autowired
	private SysUserInfoBiz sysUserInfoBiz;
	
	@Autowired
	private RegionBiz sysRegionBiz;
	@Autowired
	private SysDoWorkBiz sysDoWorkBiz;
	@Autowired
	private OrderInfoBiz orderInfoBiz;
	@Autowired
	private SysLcInfoBiz sysLcInfoBiz;
	@Autowired
	private SysAdminServiceBiz sysAdminServiceBiz;
	@Autowired
	private SysUsVsRelationshipBiz sysUsVsRelationshipBiz;
	/**
	 * list请求
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	@LogAnnotation(operatingContent="用户管理模块>VS列表>用户列表页面")
	public String list(HttpServletRequest request) {
		return "user/sysvsinfo/sysVsInfoList";
	}
	
	@RequestMapping("/grid")
	@ResponseBody
	@LogAnnotation(operatingContent="用户管理模块>VS列表>查询")
	public Grid dataGrid(HttpServletRequest request , VsInfoSearchBO vsInfoSearchBO, PageFilter filter) {
		Grid grid = new Grid();
		List<VsInfoBO> list = new ArrayList<VsInfoBO>();
		//获取当前登录账号
		//request.getSession()
		long count = 0;
		try {
			AdminBO adminBO =LoginUserUtils.getUser();
	        Integer roleId = adminBO.getRoleIdd();
	        if(roleId==Constants.UserRole.SE.v()){
	        	vsInfoSearchBO.setSeAdminNum(adminBO.getAdminNum());
	        }else if(roleId==Constants.UserRole.ADMIN.v()){
	        }else if(roleId==Constants.UserRole.APlus.v()){
	        	vsInfoSearchBO.setaAdminNum(adminBO.getAdminNum());
	        }else{
	        	return grid;
	        }
            vsInfoSearchBO.setPageIndex(filter.getStartIndex());
            vsInfoSearchBO.setPageSize(filter.getRows());
			list= sysVsInfoBiz.selectSysVsInfoListByPrimary(vsInfoSearchBO);
			count = sysVsInfoBiz.selectCountSysVsInfoByPrimary(vsInfoSearchBO) ;
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
	 * 查看
	 * @param userNum
	 * @param request
	 * @return
	 */
	@RequestMapping("/cheackPage")
	@LogAnnotation(operatingContent="用户管理模块>VS列表>查看用户")
	public String cheackPage(@RequestParam("userNum") String userNum,HttpServletRequest request) {
		VsInfoBO  sysVsInfo = null; 
		List<String> serviceList = new ArrayList<String>();
		List<OrderInfoBO> OrderInfoBOList = new ArrayList<OrderInfoBO>();
        try {
            sysVsInfo = sysVsInfoBiz.getUserInfoAllData(userNum,5);
            List<Integer> list = sysVsInfo.getServiceDistincts();
            if(list!=null){
            	for(Integer service:list){
            		serviceList.add(sysRegionBiz.selectAllParentRegionStringById(service));
            	}
            }
            OrderInfoBOList = orderInfoBiz.getOrderListByUserNum(userNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("vsInfoBO", sysVsInfo != null ?sysVsInfo : new SysVsInfo());
        request.setAttribute("serviceList", serviceList);
        request.setAttribute("OrderInfoBOList", OrderInfoBOList);
       
        return "user/sysvsinfo/sysVsInfoPage";
    }
	
	/**编辑页面
	 * @author xieang
	 * 2015年10月10日
	 * @return
	 */
	@RequestMapping("/editPage")
	@LogAnnotation(operatingContent="用户管理模块>VS列表>编辑用户页面")
	public String editPage(@RequestParam("userNum") String userNum,HttpServletRequest request) {
		VsInfoBO vsInfoBO = null;
		List<SysDoWork> sysDoWorkList = null;
        try {
        	vsInfoBO = sysVsInfoBiz.getUserInfoAllData(userNum,5);
        	sysDoWorkList = sysDoWorkBiz.getDoWork();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null != vsInfoBO){
            request.setAttribute("vsInfoBO", vsInfoBO);
            request.setAttribute("sysDoWorkList", sysDoWorkList);
        }else{
            request.setAttribute("vsInfoBO", new VsInfoBO());
            request.setAttribute("sysDoWorkList", sysDoWorkList);
        }
		return "user/sysvsinfo/sysVsInfoEditPage";
	}
	
	@RequestMapping("/edit")
	@LogAnnotation(operatingContent="用户管理模块>VS列表>保存编辑用户")
	public String edit(VsInfoBO vsInfoBO){
		sysVsInfoBiz.updateVsInfo(vsInfoBO);
		return "redirect:list";
	}
	
	/**
	 * 根据编号或者手机号查询vs信息
	 * @author xieang
	 * 2015年10月10日
	 * @param userNum编号或者手机号
	 * @return
	 */
	@RequestMapping("/searchVsObject")
	@ResponseBody
	@LogAnnotation(operatingContent="用户管理模块>VS列表>分配LC页面")
	public Object searchVsObject(String userNum,String thisUserNum){
		VsInfoSearchBO bo = new VsInfoSearchBO();
        bo.setUserMobileSearch(userNum);
        if(StringUtils.isNotBlank(thisUserNum)){
        	SysLcInfo sysLcInfo = sysLcInfoBiz.selectByPrimaryKey(thisUserNum);
        	if(sysLcInfo!=null&&StringUtils.isNotBlank(sysLcInfo.getUserNum())){
        		bo.setLcNum(thisUserNum);
        	}
        }
        List<VsInfoBO> list = sysVsInfoBiz.selectSysVsInfoListByPrimary(bo);
        if (list == null || list.size() < 1) {
            bo.setUserMobileSearch(null);
            bo.setUserNumSearch(userNum);
            list = sysVsInfoBiz.selectSysVsInfoListByPrimary(bo);
        }
        VsInfoBO vsInfoBO = (list != null && list.size() > 0) ? list.get(0) : new VsInfoBO();
        List<String> sysAdminServiceList = new ArrayList<String>();
        if(StringUtils.isNotBlank(vsInfoBO.getUserNum())){
        	sysAdminServiceList = sysAdminServiceBiz.selectStringByNumLevel(vsInfoBO.getUserNum(),5);
        }
        if(vsInfoBO==null||vsInfoBO.getAuthenticationState()==null||vsInfoBO.getIsFrozen()==null
				||vsInfoBO.getAuthenticationState()!=Constants.AuthenticationState.Success.v()||vsInfoBO.getIsFrozen()){
			return null;
		}
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", vsInfoBO.getUserNum());
        map.put("name", vsInfoBO.getName());
        map.put("userMobile", vsInfoBO.getUserMobile());
        map.put("sysAdminServiceList", sysAdminServiceList);
        return map;
	}
	/**
	 * 根据编号或者手机号查询vs信息
	 * @author xieang
	 * 2015年10月10日
	 * @param userNum编号或者手机号
	 * @return
	 */
	@RequestMapping("/searchVsHave")
	@ResponseBody
	@LogAnnotation(operatingContent="用户管理模块>VS列表>分配LC页面")
	public Object searchVsHave(String userNum){
		SysVsInfo vsInfoBO = sysVsInfoBiz.selectByPrimaryKeyUserNum(userNum);
		if(vsInfoBO!=null&&StringUtils.isNotBlank(vsInfoBO.getUserNum())){
			return "true";
		}else{
			return "false";
		}
	}
	/**vs绑定ls
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 */
	@RequestMapping("/bindingLc")
	@LogAnnotation(operatingContent="用户管理模块>VS列表>绑定LC")
	public String bindingLc(SysVsLcRelationship sysVsLcRelationship){
		if(StringUtils.isNotBlank(sysVsLcRelationship.getVsNum())&&StringUtils.isNotBlank(sysVsLcRelationship.getLcNum())){
			sysVsInfoBiz.bindingLc(sysVsLcRelationship);
		}else{
			throw new NullArgumentException("参数为空！");
		}
		return "redirect:list";
	}
	/**vs绑定下的us查看页
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 */
	@RequestMapping("/vsChangeList")
	public String vsChangeList(String userNum,HttpServletRequest request){
		if(userNum==null){
			return null;//此处应跳到错误页面 TODO
		}
		request.setAttribute("userNum", userNum);
		return "user/sysvsinfo/vsChangeList";
	}
	/**vs绑定下的us转移
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/vsChange")
	@ResponseBody
	@LogAnnotation(operatingContent="用户管理模块>VS列表>转移页面")
	public String vsChange(ChangeVo changeVo){
		if(StringUtils.isBlank(changeVo.getStateC())
				||StringUtils.isBlank(changeVo.getNewNum())||StringUtils.isBlank(changeVo.getOldNum())){
			throw new NullArgumentException("参数传入错误NULL");
		}
		int i = sysVsInfoBiz.vsChange(changeVo.getStateC(), changeVo.getUserUsInfoSearchBo(),
				changeVo.getUserNumList(), changeVo.getOldNum(), changeVo.getNewNum());
		return String.valueOf(i);
	}
	
	/**审核
	 * @author xieang
	 * 2015年10月22日
	 * @param userNum
	 * @return
	 */
	@RequestMapping("/isAuthentication")
	public String isAuthentication(String userNum,Boolean authenticationState,String infoMeg){
	    AdminBO bo = LoginUserUtils.getUser();
		if(StringUtils.isNotBlank(userNum)&&authenticationState!=null){
			sysVsInfoBiz.isAuthentication(userNum,authenticationState,infoMeg,bo);
		}
		return "redirect:list";
	}
	/**查询是否有下级US
	 * @author xieang
	 * 2015年11月3日
	 * @param userNum
	 * @return
	 */
	@RequestMapping("/haveSubordinate")
	@ResponseBody
	public Object haveSubordinate(String userNum){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("vsNum", userNum);
		int sum = sysUsVsRelationshipBiz.selectPageCount(map);
		if(sum>0){
			return "true";
		}
		return "false";
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
