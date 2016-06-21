package com.ync365.seed.admin.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
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
import com.ync365.seed.bussiness.modules.user.biz.SysAdminBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminServiceBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysLcInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysLcSeRelationshipBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysSeARelationshipBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysVsInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.AdminInfoSearchBO;
import com.ync365.seed.bussiness.modules.user.bo.SysAdminInfoBO;
import com.ync365.seed.bussiness.modules.user.entity.SysAdminInfo;
import com.ync365.seed.bussiness.modules.user.entity.SysSeARelationship;
import com.ync365.seed.utils.Constants;

import freemarker.template.utility.NullArgumentException;



/**
 * adminINfo controller
 * @author leixc
 *
 */
@Controller
@RequestMapping("/sysadmininfo")
public class SysAdminInfoController {

	
	@Autowired
    private SysAdminInfoBiz sysAdminInfoBiz;
	
	@Autowired
    private SysLcInfoBiz sysLcInfoBiz;
    
    @Autowired
    SysUserInfoBiz sysUserInfoBiz ;
    
    @Autowired
    SysVsInfoBiz sysVsInfoBiz;
    @Autowired
    RegionBiz sysRegionBiz;
    @Autowired
	private SysAdminServiceBiz sysAdminServiceBiz;
    @Autowired
    private SysSeARelationshipBiz sysSeARelationshipBiz;
    @Autowired
    private SysLcSeRelationshipBiz sysLcSeRelationshipBiz;
    
    @Autowired
    private SysAdminBiz adminBiz;
    
    
    
	@RequestMapping("/seList")
	@LogAnnotation(operatingContent="用户管理模块>SE列表>SE用户列表页面")
    public String seList(HttpServletRequest request) {
        return "user/sysadmininfo/sysSeInfoList";
    }
	@RequestMapping("/aList")
	public String aList(HttpServletRequest request) {
		return "user/sysadmininfo/sysAInfoList";
	}
	
	
	@RequestMapping("/seGrid")
    @ResponseBody
    @LogAnnotation(operatingContent="用户管理模块>SE列表>查询")
    public Grid seGrid(HttpServletRequest request , AdminInfoSearchBO adminInfoSearchBO ,PageFilter filter) {
        Grid grid = new Grid();
        adminInfoSearchBO.setPageIndex(filter.getStartIndex());
        adminInfoSearchBO.setPageSize(filter.getRows());
        
        AdminBO adminBO =LoginUserUtils.getUser();
        Integer roleId = adminBO.getRoleIdd();
        if(roleId==Constants.UserRole.APlus.v()){
        	adminInfoSearchBO.setaAiAdminNum(adminBO.getAdminNum());
        }else if(roleId==Constants.UserRole.ADMIN.v()){
        }else{
        	return grid;
        }
        
        List<SysAdminInfoBO> list = sysAdminInfoBiz.selectSysSeInfoByParam(adminInfoSearchBO);
        long count = sysAdminInfoBiz.selectSysSeInfoByParamCount(adminInfoSearchBO);
        grid.setPage(filter.getPage());
        grid.setRows(list);
        grid.setRecords(count);
        grid.setPageSize(filter.getRows());
        return grid;
    }
	
	@RequestMapping("/aGrid")
	@ResponseBody
	@LogAnnotation(operatingContent="用户管理模块>A+列表>查询")
	public Grid aGrid(HttpServletRequest request , AdminInfoSearchBO adminInfoSearchBO ,PageFilter filter) {
		Grid grid = new Grid();
		adminInfoSearchBO.setPageIndex(filter.getStartIndex());
		adminInfoSearchBO.setPageSize(filter.getRows());
		
		AdminBO adminBO =LoginUserUtils.getUser();
        Integer roleId = adminBO.getRoleIdd();
        if(roleId==Constants.UserRole.ADMIN.v()){
        }else{
        	return grid;
        }
		List<SysAdminInfoBO> list = sysAdminInfoBiz.selectSysAInfoByParam(adminInfoSearchBO);
		long count = sysAdminInfoBiz.selectSysAInfoByParamCount(adminInfoSearchBO);
		grid.setPage(filter.getPage());
		grid.setRows(list);
		grid.setRecords(count);
		grid.setPageSize(filter.getRows());
		return grid;
	}
	
	/**
     * 查看
     * @param userNum
     * @param request
     * @return
     */
    @RequestMapping("/seCheackPage")
    @LogAnnotation(operatingContent="用户管理模块>SE列表>查看")
    public String seCheackPage(@RequestParam("adminNum") String adminNum,HttpServletRequest request) {
        SysAdminInfoBO sysAdminInfoBO = null; 
        List<String> serviceList = new ArrayList<String>();
        try {
            sysAdminInfoBO = sysAdminInfoBiz.getSeUserInfoAllData(adminNum);
            List<Integer> list = sysAdminInfoBO.getServiceserviceDistincts();
            if(list!=null){
            	for(Integer service:list){
            		serviceList.add(sysRegionBiz.selectAllParentRegionStringById(service));
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null != sysAdminInfoBO){
            request.setAttribute("sysAdminInfoBO", sysAdminInfoBO);
            request.setAttribute("serviceList", serviceList);
        }else{
            request.setAttribute("sysAdminInfoBO", new SysAdminInfoBO());
            request.setAttribute("serviceList", serviceList);
        }
        
        return "user/sysadmininfo/sysSeInfoPage";
    }
    /**
     * 查看
     * @param userNum
     * @param request
     * @return
     */
    @RequestMapping("/aCheackPage")
    @LogAnnotation(operatingContent="用户管理模块>A+列表>查看")
    public String aCheackPage(@RequestParam("adminNum") String adminNum,HttpServletRequest request) {
    	SysAdminInfoBO sysAdminInfoBO = null; 
    	List<String> serviceList = new ArrayList<String>();
    	try {
    		sysAdminInfoBO = sysAdminInfoBiz.getAUserInfoAllData(adminNum);
    		List<Integer> list = sysAdminInfoBO.getServiceserviceDistincts();
            if(list!=null){
            	for(Integer service:list){
            		serviceList.add(sysRegionBiz.selectAllParentRegionStringById(service));
            	}
            }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	if(null != sysAdminInfoBO){
    		request.setAttribute("sysAdminInfoBO", sysAdminInfoBO);
    		request.setAttribute("serviceList", serviceList);
    	}else{
    		request.setAttribute("sysAdminInfoBO", new SysAdminInfoBO());
    		request.setAttribute("serviceList", serviceList);
    	}
    	
    	return "user/sysadmininfo/sysAInfoPage";
    }
    /**
     * SE编辑页
     * @param userNum
     * @param request
     * @return
     */
    @RequestMapping("/seEditPage")
    @LogAnnotation(operatingContent="用户管理模块>SE列表>编辑")
    public String seEditPage(@RequestParam("adminNum") String adminNum,HttpServletRequest request) {
    	SysAdminInfoBO sysAdminInfoBO = null; 
    	try {
    		sysAdminInfoBO = sysAdminInfoBiz.getSeUserInfoAllData(adminNum);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	if(null != sysAdminInfoBO){
    		request.setAttribute("sysAdminInfoBO", sysAdminInfoBO);
    	}else{
    		request.setAttribute("sysAdminInfoBO", new SysAdminInfoBO());
    	}
    	
    	return "user/sysadmininfo/sysSeInfoEditPage";
    }
    /**
     * A+编辑页
     * @param userNum
     * @param request
     * @return
     */
    @RequestMapping("/aEditPage")
    @LogAnnotation(operatingContent="用户管理模块>A+列表>编辑")
    public String aEditPage(@RequestParam("adminNum") String adminNum,HttpServletRequest request) {
    	SysAdminInfoBO sysAdminInfoBO = null; 
    	try {
    		sysAdminInfoBO = sysAdminInfoBiz.getAUserInfoAllData(adminNum);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	if(null != sysAdminInfoBO){
    		request.setAttribute("sysAdminInfoBO", sysAdminInfoBO);
    	}else{
    		request.setAttribute("sysAdminInfoBO", new SysAdminInfoBO());
    	}
    	
    	return "user/sysadmininfo/sysAInfoEditPage";
    }
    
    /**编辑保存
     * @author xieang
     * 2015年10月12日
     * @param sysAdminInfo
     * @return
     */
    @RequestMapping("/seEdit")
    @LogAnnotation(operatingContent="用户管理模块>SE列表>保存编辑SE")
    public String seEdit(SysAdminInfoBO sysAdminInfoBO){
    	sysAdminInfoBiz.updateSeInfo(sysAdminInfoBO);
    	return "redirect:seList";
    }
    /**编辑保存
     * @author xieang
     * 2015年10月12日
     * @param sysAdminInfo
     * @return
     */
    @RequestMapping("/aEdit")
    @LogAnnotation(operatingContent="用户管理模块>A+列表>编辑>保存")
    public String aEdit(SysAdminInfoBO sysAdminInfoBO){
    	int a = sysAdminInfoBiz.updateByPrimary(sysAdminInfoBO);
    	return "redirect:/sysadmininfo/aList";
    }
    
    /**
	 * 根据编号或者手机号查询se信息
	 * @author xieang
	 * 2015年10月11日
	 * @param adminNum编号或者手机号
	 * @return
	 */
	@RequestMapping("/searchSeObject")
	@ResponseBody
	public Object searchSeObject(String userNum){
		SysAdminInfoBO adminInfoBO = null;
		if (StringUtils.isNotBlank(userNum)) {
			adminInfoBO = sysAdminInfoBiz.getSeInfoByUserNumOrMobile(userNum);
		}
		List<String> sysAdminServiceList = new ArrayList<String>();
        if(StringUtils.isNotBlank(adminInfoBO.getAdminNum())){
        	sysAdminServiceList = sysAdminServiceBiz.selectStringByNumLevel(adminInfoBO.getAdminNum(),3);
        }
		if(adminInfoBO==null||adminInfoBO.getIsAuthentication()==null||adminInfoBO.getIsFrozen()==null
				||adminInfoBO.getIsAuthentication()!=true||adminInfoBO.getIsFrozen()){
			return null;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("adminNum", adminInfoBO.getAdminNum());
        map.put("name", adminInfoBO.getName());
        map.put("mobile", adminInfoBO.getMobile());
        map.put("sysAdminServiceList", sysAdminServiceList);
		return map;
	}	
	/**
	 * 根据编号或者手机号查询A+信息
	 * @author xieang
	 * 2015年10月11日
	 * @param adminNum编号或者手机号
	 * @return
	 */
	@RequestMapping("/searchAObject")
	@ResponseBody
	public Object searchAObject(String userNum){
		SysAdminInfoBO adminInfoBO = sysAdminInfoBiz.getAPlusInfoByUserNumOrMobile(userNum);
		List<String> sysAdminServiceList = new ArrayList<String>();
        if(StringUtils.isNotBlank(adminInfoBO.getAdminNum())){
        	sysAdminServiceList = sysAdminServiceBiz.selectStringByNumLevel(adminInfoBO.getAdminNum(),2);
        }
		if(adminInfoBO==null||adminInfoBO.getIsAuthentication()==null||adminInfoBO.getIsFrozen()==null
				||adminInfoBO.getIsAuthentication()!=true||adminInfoBO.getIsFrozen()){
			return null;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("adminNum", adminInfoBO.getAdminNum());
        map.put("name", adminInfoBO.getName());
        map.put("mobile", adminInfoBO.getMobile());
        map.put("sysAdminServiceList", sysAdminServiceList);
		return map;
	}	
	
	/**se绑定A+
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 */
	@RequestMapping("/bindingA")
	public String bindingA(SysSeARelationship sysSeARelationship){
		if(StringUtils.isNotBlank(sysSeARelationship.getaNum())&&StringUtils.isNotBlank(sysSeARelationship.getSeNum())){
			sysAdminInfoBiz.bindingA(sysSeARelationship);
		}else{
			throw new NullArgumentException("参数为空！");
		}
		return "redirect:seList";
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
	 * 根据编号或者手机号查询se信息
	 * @author xieang
	 * 2015年10月11日
	 * @param adminNum编号或者手机号
	 * @return
	 */
	@RequestMapping("/aAddPage")
	public Object aAddPage(String userNum){
		return "user/sysadmininfo/sysAInfoAddPage";
	}
    /**编辑保存
     * @author xieang
     * 2015年10月12日
     * @param sysAdminInfo
     * @return
     */
    @RequestMapping("/aAdd")
    public String aAdd(SysAdminInfoBO sysAdminInfoBO){
    	sysAdminInfoBiz.addAUser(sysAdminInfoBO);
    	return "redirect:aList";
    }
    
    /**
     * SE编辑页
     * @param userNum
     * @param request
     * @return
     */
    @RequestMapping("/mySeEditPage")
    public String mySeEditPage(HttpServletRequest request) {
    	SysAdminInfoBO sysAdminInfoBO = null; 
    	 AdminBO bo = LoginUserUtils.getUser();
    	try {
    		sysAdminInfoBO = sysAdminInfoBiz.getSeUserInfoAllData(bo.getAdminNum());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	request.setAttribute("sysAdminInfoBO", sysAdminInfoBO != null ? sysAdminInfoBO : new SysAdminInfoBO());
    	return "user/sysadmininfo/mySeInfoEditPage";
    }
    
    /**编辑保存
     * @author xieang
     * 2015年10月12日
     * @param sysAdminInfo
     * @return
     */
    @RequestMapping("/mySeEdit")
    public String mySeEdit(SysAdminInfoBO sysAdminInfoBO){
        sysAdminInfoBiz.updateSeInfo(sysAdminInfoBO);
    	return "redirect:/sysadmininfo/mySeEditPage";
    }
    /**
     * A+编辑页
     * @param userNum
     * @param request
     * @return
     */
    @RequestMapping("/myAEditPage")
    public String myAEditPage(HttpServletRequest request) {
    	SysAdminInfoBO sysAdminInfoBO = null; 
    	AdminBO bo = LoginUserUtils.getUser();
    	try {
    		sysAdminInfoBO = sysAdminInfoBiz.getAUserInfoAllData(bo.getAdminNum());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	request.setAttribute("sysAdminInfoBO", sysAdminInfoBO != null ? sysAdminInfoBO :new SysAdminInfoBO());
  
    	return "user/sysadmininfo/myAInfoEditPage";
    }
    
    /**编辑保存
     * @author xieang
     * 2015年10月12日
     * @param sysAdminInfo
     * @return
     */
    @RequestMapping("/myAEdit")
    public String myAEdit(SysAdminInfoBO sysAdminInfoBO){
        sysAdminInfoBiz.updateByPrimary(sysAdminInfoBO);
    	return "redirect:/sysadmininfo/myAEditPage";
    }
    
    
    
	/**se绑定下的lc查看页
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 */
	@RequestMapping("/seChangeList")
	public String seChangeList(String userNum,HttpServletRequest request){
		if(userNum==null){
			return null;//此处应跳到错误页面 TODO
		}
		request.setAttribute("userNum", userNum);
		return "user/sysadmininfo/seChangeList";
	}
	/**se绑定下的lc转移
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/seChange")
	@ResponseBody
	public String seChange(ChangeVo changeVo){
		if(StringUtils.isBlank(changeVo.getStateC())
				||StringUtils.isBlank(changeVo.getNewNum())||StringUtils.isBlank(changeVo.getOldNum())){
			throw new NullArgumentException("参数传入错误NULL");
		}
		int i = sysAdminInfoBiz.seChange(changeVo.getStateC(), changeVo.getLcInfoSearchBO(),
				changeVo.getUserNumList(), changeVo.getOldNum(), changeVo.getNewNum());
		return String.valueOf(i);
	}
	
	
	/**lc绑定下的vs查看页
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 */
	@RequestMapping("/aChangeList")
	public String lcChangeList(String userNum,HttpServletRequest request){
		if(userNum==null){
			return null;//此处应跳到错误页面 TODO
		}
		request.setAttribute("userNum", userNum);
		return "user/sysadmininfo/aChangeList";
	}
	/**vs绑定下的us转移
	 * @author xieang
	 * 2015年10月11日
	 * @param sysUsVsRelationship
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/aChange")
	@ResponseBody
	public String aChange(ChangeVo changeVo){
		if(StringUtils.isBlank(changeVo.getStateC())
				||StringUtils.isBlank(changeVo.getNewNum())||StringUtils.isBlank(changeVo.getOldNum())){
			throw new NullArgumentException("参数传入错误NULL");
		}
		int i = sysAdminInfoBiz.aChange(changeVo.getStateC(), changeVo.getAdminInfoSearchBO(),
				changeVo.getUserNumList(), changeVo.getOldNum(), changeVo.getNewNum());
		return String.valueOf(i);
	}
	
	/**查询是否有下级US
	 * @author xieang
	 * 2015年11月3日
	 * @param userNum
	 * @return
	 */
	@RequestMapping("/haveSubordinateSe")
	@ResponseBody
	public Object haveSubordinateSe(String userNum){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("seNum", userNum);
		int sum = sysLcSeRelationshipBiz.selectPageCount(map);
		if(sum>0){
			return "true";
		}
		return "false";
	}
	/**查询是否有下级US
	 * @author xieang
	 * 2015年11月3日
	 * @param userNum
	 * @return
	 */
	@RequestMapping("/haveSubordinateA")
	@ResponseBody
	public Object haveSubordinateA(String userNum){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("aNum", userNum);
		int sum = sysSeARelationshipBiz.selectPageCount(map);
		if(sum>0){
			return "true";
		}
		return "false";
	}
	
	
	/**
	 * se登录后台时 没有完善资料 添加se资料
	 * @param record
	 * @return
	 */
	@RequestMapping("/updateSeInfo")
    public String updateSeInfo(SysAdminInfoBO record){
	    int a = 0;
	    String ulr_i = "redirect:/login?t=3";
        try {
            a = sysAdminInfoBiz.perfectSeInfo(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(a == 1){
            ulr_i = "redirect:/index";
        }else{
            SecurityUtils.getSubject().logout();
        }
        return ulr_i;
    }
	
	
	@RequestMapping("/isAuthentication")
    public String isAuthentication(String adminNum,Boolean authenticationState,String infoMeg,String stat){
        AdminBO bo = LoginUserUtils.getUser();
        if(StringUtils.isNotBlank(adminNum)&&authenticationState!=null){
            int a = adminBiz.isAuthentication(adminNum,authenticationState,infoMeg,bo);
        }
        if(null != stat && "" != stat ){
            if("SE".equals(stat)){
                return "redirect:seList";
            }
            if("A+".equals(stat)){
                return "redirect:aList";
            }
        }
        return null;
    }
}
