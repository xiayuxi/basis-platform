package com.ync365.seed.admin.controller.ad;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.Json;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.ad.biz.NavigationBiz;
import com.ync365.seed.bussiness.modules.ad.entity.Navigation;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.web.vo.ZTreeVo;

/**
 * 
 * @ClassName: NavigationController
 * @Description: 导航控制类
 * @author robo
 * @date 2015年9月25日 上午11:42:36
 *
 */
@Controller
@RequestMapping("/navigation")
public class NavigationController {

    @Autowired
    NavigationBiz navigationBiz;

	/**
	 * 跳转到列表页面
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model) {
		return "ad/navigationList";
	}
	
	/**
	 * 加载数据
	 */
    @RequestMapping(value = "/grid", method = RequestMethod.POST)
    @ResponseBody
    public Grid dataGrid(PageFilter filter) {
        Grid grid = new Grid();
        List<Navigation> list = new ArrayList<Navigation>();
        long count = 0;

        try {
            list = navigationBiz.selectPage(filter.getStartIndex(), filter.getRows());
            count = navigationBiz.selectPageCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        grid.setRows(list);
		grid.setPageSize(filter.getRows());
		grid.setRecords(count);
		grid.setPage(filter.getPage());
        return grid;
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage(ModelMap model) {
        //查询所有导航
        try{
            List<Navigation> nCList = navigationBiz.selectNavigationCategoryList();
            model.addAttribute("nCList", nCList);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "ad/navigationAdd";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Navigation navigation,RedirectAttributes redirectAttributes) {
        try{
            navigationBiz.adNavigation(navigation);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:list";
    }
    
    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    public String editPage(Integer id, ModelMap model,HttpServletRequest request) {
        Navigation navigation;
        try{
            List<Navigation> nCList = navigationBiz.selectNavigationCategoryList();
            navigation = navigationBiz.selectById(id);
            model.addAttribute("navigation", navigation);
            model.addAttribute("nCList", nCList);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "ad/navigationEdit";
    }
    
    /**
     * 更新
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Navigation navigation, RedirectAttributes redirectAttributes) {
        try{
            navigationBiz.edit(navigation);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:list";
    }
	
    
    
    @RequestMapping("/delete")
    @ResponseBody
    public Json delete(@RequestParam("id") int id) {
        Json json = new Json();
        int temp = 0;
        try {
            Navigation navigation = navigationBiz.selectById(id);
            if(navigation.getEnable()!=1){
                temp = navigationBiz.deleteById(id);
                if (temp == 1) {
                    json.setSuccess(true);
                    json.setMsg("yes");
                } else {
                    json.setSuccess(false);
                    json.setMsg("删除失败");
                }
            } else {
                json.setSuccess(false);
                json.setMsg("当前状态不可删除");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    
}
