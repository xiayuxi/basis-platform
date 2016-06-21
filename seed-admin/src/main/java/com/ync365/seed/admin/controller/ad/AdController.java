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

import com.ync365.seed.admin.controller.base.BaseController;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.Json;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.bussiness.modules.ad.biz.AdBiz;
import com.ync365.seed.bussiness.modules.ad.biz.AdPositionBiz;
import com.ync365.seed.bussiness.modules.ad.entity.Ad;
import com.ync365.seed.bussiness.modules.ad.entity.AdPosition;

/**
 * 
 * @ClassName: AdController
 * @Description: 广告控制类
 * @author robo
 * @date 2015年9月25日 上午11:42:36
 *
 */
@Controller
@RequestMapping("/ad")
public class AdController extends BaseController {

	@Autowired
	private AdBiz adBiz;
	 
	@Autowired
	private AdPositionBiz adPositionBiz;


	/**
	 * 跳转到列表页面
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model) {
        //查询所有的广告位
        try{
            List<AdPosition> aPList = adPositionBiz.selectAdPositionAllList();
            model.addAttribute("aPList", aPList);
        } catch (Exception e){
            e.printStackTrace();
        }
		 
		return "ad/adList";
	}
	
	/**
	 * 加载数据
	 */
    @RequestMapping(value = "/grid", method = RequestMethod.POST)
    @ResponseBody
    public Grid dataGrid(String qName,String status ,String adPosition,PageFilter filter) {
        Grid grid = new Grid();
        List<Ad> list = new ArrayList<Ad>();
        long count = 0;

        try {
            list = adBiz.selectPage(qName, status,adPosition,filter.getStartIndex(), filter.getRows());
            count = adBiz.selectPageCount(qName);
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
        
        //查询所有的广告位
        try{
            List<AdPosition> aPList = adPositionBiz.selectAdPositionAllList();
            model.addAttribute("aPList", aPList);
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return "ad/adAdd";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Ad ad, Integer adPositionId, RedirectAttributes redirectAttributes
            ,@RequestParam("beginDate") String beginDate,@RequestParam("endDate") String endDate) {
        try{
            adBiz.addAd(ad,beginDate,endDate);
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
        Ad ad;
        try{
            List<AdPosition> aPList = adPositionBiz.selectAdPositionAllList();
            ad = adBiz.selectById(id);
            if(ad.getUrl().isEmpty()){
                ad.setHasUrl((short)0);
            }else{
                ad.setHasUrl((short)1);
            }
//            request.setAttribute("aPList", aPList);
//            request.setAttribute("ad", ad);
            model.addAttribute("aPList", aPList);
            model.addAttribute("ad", ad);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "ad/adEdit";
    }
    
    /**
     * 更新
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Ad ad, Integer adPositionId, RedirectAttributes redirectAttributes
            ,@RequestParam("beginDate") String beginDate,@RequestParam("endDate") String endDate) {
        try{
            if(ad.getHasUrl()==0){
                ad.setUrl("");
            }
            adBiz.edit(ad,beginDate,endDate);
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
            //改is_del为0
            temp = adBiz.deleteById(id);
            if (temp == 1) {
                json.setSuccess(true);
                json.setMsg("yes");
            } else if(temp == 2){
                json.setSuccess(false);
                json.setMsg("当前状态不可删除");
            }else {
                json.setSuccess(false);
                json.setMsg("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    @RequestMapping("/startAd")
    @ResponseBody
    public Json startAd(@RequestParam("id") int id) {
        Json json = new Json();
        int temp = 0;
        try {
            Integer status = adBiz.selectById(id).getStatus();
            
            //temp = adBiz.deleteById(id);
            if (status == 0||status==2) {
                temp = adBiz.changeStatusById(id);
                if(temp!=1){
                    json.setSuccess(false);
                    json.setMsg("操作失败");
                }else{
                    json.setSuccess(true);
                    json.setMsg("操作成功");
                }
            } else {
                json.setSuccess(false);
                json.setMsg("当前状态不可执行此操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    @RequestMapping("/pauseAd")
    @ResponseBody
    public Json pauseAd(@RequestParam("id") int id) {
        Json json = new Json();
        int temp = 0;
        try {
            Integer status = adBiz.selectById(id).getStatus();
            
            //temp = adBiz.deleteById(id);
            if (status == 1) {
                temp = adBiz.changeStatusById(id);
                if(temp!=1){
                    json.setSuccess(false);
                    json.setMsg("操作失败");
                }else{
                    json.setSuccess(true);
                    json.setMsg("操作成功");
                }
            } else {
                json.setSuccess(false);
                json.setMsg("当前状态不可执行此操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    
    /**
     * 
     * @Title: changeStatus
     * @Description:  改变状态，暂停还是启动
     * @author: liugl    
     * @date: 2015年10月9日 上午11:26:57       
     * @version: 
     *
     * @param id
     * @return
     *
     */
    @RequestMapping("/changeStatus")
    public String changeStatus(@RequestParam("id") int id) {
        try {
            adBiz.changeStatusById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list";
    }
    
    /**
     * 
     * @Title: complete
     * @Description: 完成操作
     * @author: liugl    
     * @date: 2015年10月9日 下午3:19:48       
     * @version: 
     *
     * @param id
     * @return
     *
     */
    @RequestMapping("/complete")
    public String complete(@RequestParam("id") int id) {
        try {
            adBiz.completeById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list";
    }
    
    
    @RequestMapping("/validate")
    @ResponseBody
    public Json validate(@RequestParam("id") int id) {
        Json json = new Json();
        try {
            String status= adBiz.selectById(id).getStatus().toString();
            json.setSuccess(true);
            json.setMsg(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    
}
