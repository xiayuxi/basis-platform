package com.ync365.seed.admin.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsLcRelationshipBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoBO;
import com.ync365.seed.bussiness.modules.user.bo.LcInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysDoWork;
import com.ync365.seed.bussiness.modules.user.entity.SysLcInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysLcSeRelationship;
import com.ync365.seed.utils.Constants;

import freemarker.template.utility.NullArgumentException;


/**
 * lc controller
 * @author leixc
 *
 */
@Controller
@RequestMapping("/syslcinfo")
public class SysLcInfoController {

	@Autowired
	private SysLcInfoBiz sysLcInfoBiz;
	
	@Autowired
	private SysUserInfoBiz sysUserInfoBiz ;
	
	@Autowired
	private SysVsInfoBiz sysVsInfoBiz;
	@Autowired
	private RegionBiz sysRegionBiz;
	@Autowired
	private SysDoWorkBiz sysDoWorkBiz;
	@Autowired
	private OrderInfoBiz orderInfoBiz;
	@Autowired
	private SysAdminServiceBiz sysAdminServiceBiz;
	@Autowired
	private SysVsLcRelationshipBiz sysVsLcRelationshipBiz;
	/**
	 * list请求  se 获取lc 信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		return "user/syslcinfo/sysLcInfoList";
	}
	
	@RequestMapping("/grid")
	@ResponseBody
	public Grid dataGrid(HttpServletRequest request , LcInfoSearchBO lcInfoSearchBO, PageFilter filter) {
	    Grid grid = new Grid();
        List<LcInfoBO> list = new ArrayList<LcInfoBO>();
        //获取当前登录账号
        //request.getSession()
        long count = 0;
        try {
        	lcInfoSearchBO.setPageIndex(filter.getStartIndex());
            lcInfoSearchBO.setPageSize(filter.getRows());
            
        	AdminBO adminBO =LoginUserUtils.getUser();
            Integer roleId = adminBO.getRoleIdd();
            if(roleId==Constants.UserRole.SE.v()){
            	lcInfoSearchBO.setSeAdminNum(adminBO.getAdminNum());
	        }else if(roleId==Constants.UserRole.ADMIN.v()){
	        }else if(roleId==Constants.UserRole.APlus.v()){
	        	lcInfoSearchBO.setAiAdminNum(adminBO.getAdminNum());
	        }else{
	        	return grid;
	        }
            
            list= sysLcInfoBiz.selectSysLcInfoListByPrimary(lcInfoSearchBO);
            count = sysLcInfoBiz.selectCountSysLcInfoByPrimary(lcInfoSearchBO);
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
	public String cheackPage(@RequestParam("userNum") String userNum,HttpServletRequest request) {
		LcInfoBO  lcInfoBO = null;
		List<String> serviceList = new ArrayList<String>();
		List<OrderInfoBO> OrderInfoBOList = new ArrayList<OrderInfoBO>();
		try {
			lcInfoBO = sysLcInfoBiz.getUserInfoAllData(userNum,4);
			List<Integer> list = lcInfoBO.getServiceDistincts();
            if(list!=null){
            	for(Integer service:list){
            		serviceList.add(sysRegionBiz.selectAllParentRegionStringById(service));
            	}
            }
            OrderInfoBOList = orderInfoBiz.getOrderListByUserNum(userNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("lcInfoBO", lcInfoBO != null ? lcInfoBO : new SysLcInfo() );
		request.setAttribute("serviceList", serviceList);
		request.setAttribute("OrderInfoBOList", OrderInfoBOList);
		
		return "user/syslcinfo/sysLcInfoPage";
	}
	
	
	/**编辑页面
	 * @author xieang
	 * 2015年10月10日
	 * @return
	 */
	@RequestMapping("/editPage")
	public ModelAndView editPage(@RequestParam("userNum") String userNum,HttpServletRequest request) {
		LcInfoBO lcInfoBO = null;
		List<SysDoWork> sysDoWorkList = null;
        try {
        	lcInfoBO = sysLcInfoBiz.getUserInfoAllData(userNum,4);
        	sysDoWorkList = sysDoWorkBiz.getDoWork();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null != lcInfoBO){
            request.setAttribute("lcInfoBO", lcInfoBO);
            request.setAttribute("sysDoWorkList", sysDoWorkList);
        }else{
            request.setAttribute("lcInfoBO", new LcInfoBO());
            request.setAttribute("sysDoWorkList", sysDoWorkList);
        }
		return new ModelAndView("user/syslcinfo/sysLcInfoEditPage");
	}
	
	@RequestMapping("/edit")
	public String edit(LcInfoBO lcInfoBO){
		sysLcInfoBiz.updateSysLcInfo(lcInfoBO);
		return "redirect:list";
	}
	/**
	 * 根据编号或者手机号查询lc信息
	 * @author xieang
	 * 2015年10月10日
	 * @param userNum编号或者手机号
	 * @return
	 */
	@RequestMapping("/searchLcObject")
	@ResponseBody
	public Object searchLcObject(String userNum){
		LcInfoBO lcInfoBO = sysLcInfoBiz.selectLcInfoByNumOrMobile(userNum);
		List<String> sysAdminServiceList = new ArrayList<String>();
        if(StringUtils.isNotBlank(lcInfoBO.getUserNum())){
        	sysAdminServiceList = sysAdminServiceBiz.selectStringByNumLevel(lcInfoBO.getUserNum(),4);
        }
		if(lcInfoBO==null||lcInfoBO.getAuthenticationState()==null||lcInfoBO.getIsFrozen()==null
				||lcInfoBO.getAuthenticationState()!=Constants.AuthenticationState.Success.v()||lcInfoBO.getIsFrozen()){
			return null;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userNum", lcInfoBO.getUserNum());
        map.put("name", lcInfoBO.getName());
        map.put("userMobile", lcInfoBO.getUserMobile());
        map.put("sysAdminServiceList", sysAdminServiceList);
		return map;
	}
	
	/**
	 * 根据编号或者手机号查询lc信息
	 * @author xieang
	 * 2015年10月10日
	 * @param userNum编号或者手机号
	 * @return
	 */
	@RequestMapping("/searchLcHave")
	@ResponseBody
	public Object searchLcHave(String userNum){
		SysLcInfo lcInfoBO = sysLcInfoBiz.selectByPrimaryKey(userNum);
		if(lcInfoBO!=null&&StringUtils.isNotBlank(lcInfoBO.getUserNum())){
			return "true";
		}else{
			return "false";
		}
	}
	
	/**lc绑定se
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 */
	@RequestMapping("/bindingSe")
	public String bindingSe(SysLcSeRelationship sysLcSeRelationship){
		if(StringUtils.isNotBlank(sysLcSeRelationship.getLcNum())&&StringUtils.isNotBlank(sysLcSeRelationship.getSeNum())){
			sysLcInfoBiz.bindingSe(sysLcSeRelationship);
		}else{
			throw new NullArgumentException("参数为空！");
		}
		return "redirect:list";
	}
	
	
	/**lc绑定下的vs查看页
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 */
	@RequestMapping("/lcChangeList")
	public String lcChangeList(String userNum,HttpServletRequest request){
		if(userNum==null){
			return null;//此处应跳到错误页面 TODO
		}
		request.setAttribute("userNum", userNum);
		return "user/syslcinfo/lcChangeList";
	}
	/**lc绑定下的vs转移
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/lcChange")
	@ResponseBody
	public String lcChange(ChangeVo changeVo){
		if(StringUtils.isBlank(changeVo.getStateC())
				||StringUtils.isBlank(changeVo.getNewNum())||StringUtils.isBlank(changeVo.getOldNum())){
			throw new NullArgumentException("参数传入错误NULL");
		}
		int i = sysLcInfoBiz.lcChange(changeVo.getStateC(), changeVo.getVsInfoSearchBO(),
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
	public String isAuthentication(String userNum,Boolean authenticationState ,String infoMeg){
	    AdminBO bo = LoginUserUtils.getUser();
		if(StringUtils.isNotBlank(userNum)&&authenticationState!=null){
			sysLcInfoBiz.isAuthentication(userNum,authenticationState,infoMeg,bo);
		}
		return "redirect:list";
	}
	
	/**查询是否有下级VS
	 * @author xieang
	 * 2015年11月3日
	 * @param userNum
	 * @return
	 */
	@RequestMapping("/haveSubordinate")
	@ResponseBody
	public Object haveSubordinate(String userNum){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("lcNum", userNum);
		int sum = sysVsLcRelationshipBiz.selectPageCount(map);
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
