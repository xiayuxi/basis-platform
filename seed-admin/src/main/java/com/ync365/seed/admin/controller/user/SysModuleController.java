package com.ync365.seed.admin.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.Json;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.user.biz.SysModuleBiz;
import com.ync365.seed.bussiness.modules.user.entity.SysModule;
import com.ync365.seed.web.vo.ZTreeVo;


/**
 * 权限controller
 *
 */
@Controller
@RequestMapping("/sysmodule")
public class SysModuleController {

	@Autowired
	private SysModuleBiz sysModuleBiz;
	
	/**
	 * list请求
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		return "user/sysModuleList";
	}
	
	@RequestMapping("/grid")
	@ResponseBody
	public Grid dataGrid(String qName, PageFilter filter) {
		Grid grid = new Grid();
		List<SysModule> list = new ArrayList<SysModule>();
		long count = 0;
		Map<String ,Object> map=new HashMap<String,Object>();
		try {
			map.put("moduleName", qName);
			list= sysModuleBiz.selectPageByMap(map, filter.getStartIndex(),filter.getRows());
			count = sysModuleBiz.selectPageCount(map);
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
	public String addPage(HttpServletRequest request) {
		return "user/sysModuleAdd";
	}
	
	/**
	 * 新增保存
	 * @param sysModule
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(SysModule sysModule){
		int temp = 0;
		try {
			temp = sysModuleBiz.insert(sysModule);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/sysmodule/list");
	}

	/**
	 * 编辑
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(@RequestParam("id") int id,
			HttpServletRequest request) {
		SysModule sysModule;
		String tem = null;
		try {
			sysModule = sysModuleBiz.selectByPrimaryKey(id);
			if(0 != sysModule.getParentId()){
				tem = sysModuleBiz.selectByPrimaryKey(sysModule.getParentId()).getModuleName();
			}
			request.setAttribute("sysModule", sysModule);
			request.setAttribute("tem", tem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/sysModuleEdit";
	}
	
	/**
	 * 编辑保存
	 * @param sysModule
	 * @return
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(SysModule sysModule){
		int temp = 0;
		try {
			temp = sysModuleBiz.updateByPrimaryKey(sysModule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/sysmodule/list");
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam("id") int id) {
		int temp = 0;
		boolean flag = false;
		try {
			//删除角色权限关系表sysRoleModule  及 权限表 sysModule中的数据
			temp = sysModuleBiz.deleteByPrimaryKey(id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/sysmodule/list");
	}
	
	/**
	 * 分类请求tree 封装成ZTreeVo
	 * @param request
	 * @return
	 */
	@RequestMapping("/getree")
	@ResponseBody
	public List<ZTreeVo> allAjax(HttpServletRequest request) {
		List<ZTreeVo> list = new ArrayList<ZTreeVo>();

		try {
			Map<String ,Object> map=new HashMap<String,Object>();
			List<SysModule> listSysModule =  sysModuleBiz.selectSysModuleAllByMap(map);
			if(null != listSysModule && listSysModule.size()>0){
				for (SysModule c : listSysModule) {
					ZTreeVo vo = new ZTreeVo();
					vo.setId(String.valueOf(c.getId()));
					vo.setName(c.getModuleName());
					vo.setpId(String.valueOf(c.getParentId()));
					list.add(vo);
				}
			}
			/*if(null != id){
				SysModule listSys = sysModuleBiz.selectByPrimaryKey(id);
				for (ZTreeVo t : list) {
					if ( String.valueOf(listSys.getParentId()).equals(t.getId()) ) {
						System.out.println("------------------"
								+ t.getId().equals(listSys.getParentId()));
						t.setChecked(true);
					}
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 删除时查看此分类下是否有子分类存在
	 * @param id
	 * @return
	 */
	@RequestMapping("/getchild")
	@ResponseBody
	public Json getchild(@RequestParam("id") int id) {
		Json json = new Json();
		try {
			Map<String ,Object> map=new HashMap<String,Object>();
			map.put("parentId", id);
			List<SysModule> list = sysModuleBiz.selectSysModuleAllByMap(map);
			if(null != list && list.size()>0){
				json.setSuccess(false);
				json.setMsg("no");
			}else{
				json.setSuccess(true);
				json.setMsg("ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
