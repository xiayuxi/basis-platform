
package com.ync365.seed.admin.controller.promotion;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.Json;
import com.ync365.seed.admin.vo.promotion.PromotionGiftVo;
import com.ync365.seed.admin.vo.promotion.PromotionGoodsSkuVo;
import com.ync365.seed.bussiness.modules.goods.biz.GoodsBiz;
import com.ync365.seed.bussiness.modules.goods.biz.HoldGoldBiz;
import com.ync365.seed.bussiness.modules.goods.biz.SkuBiz;
import com.ync365.seed.bussiness.modules.goods.biz.StocksBiz;
import com.ync365.seed.bussiness.modules.goods.bo.SkuBO;
import com.ync365.seed.bussiness.modules.goods.entity.Goods;
import com.ync365.seed.bussiness.modules.goods.entity.HoldGold;
import com.ync365.seed.bussiness.modules.goods.entity.Sku;
import com.ync365.seed.bussiness.modules.goods.entity.Stocks;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionGoodsSkuBiz;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGift;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGoodsSku;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionGoodSkuSearch;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/promotionGoodsSku")
public class PromotionGoodsSkuController {

    private static final Logger logger = LoggerFactory.getLogger(PromotionGoodsSkuController.class);

    @Autowired
    private PromotionGoodsSkuBiz promotionGoodsSkuBiz;

    @Autowired
    private PromotionBiz promotionBiz;

    @Autowired
    private SkuBiz skuBiz;

    @Autowired
    private HoldGoldBiz holdGoldBiz;

    @Autowired
    private GoodsBiz goodsBiz;
    
    @Autowired
    private StocksBiz stocksBiz;

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    /**
     * 
     * @Title: list
     * @Description: TODO  活动列表入口方法，调取活动页面  ：    
     * @author: sunyf    
     * @date: 2015年9月29日 下午2:42:25       
     * @version: 
     *
     * @param promotionId
     * @param version
     * @param promotionType
     * @return url /promotion/promotionGoodsSku/promotionGoodsSkuList
     *
     */
    @RequestMapping("/list")
    @ResponseBody
    public ModelAndView list(@RequestParam("id") Integer promotionId, @RequestParam("version") String version,
            @RequestParam("promotionType") String promotionType) {
        ModelAndView mav = new ModelAndView("/promotion/promotionGoodsSku/promotionGoodsSkuList");

        Promotion promotion = promotionBiz.selectById(promotionId);
        if (!promotion.getVersion().equals(version)) {
            logger.error("活动已经更新，请刷新页面重试");
        }

        if (!promotion.getStatus().equals(Constants.PromotionStatus.New.v())) {
            logger.error("活动状态非新建状态，无法设置活动商品");
        }
        mav.addObject("promotionId", promotionId);
        mav.addObject("promotionVersion", version);
        mav.addObject("promotionType", promotionType);
        return mav;
    }
    /**
     * 
     * @Title: view
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月1日 下午5:54:39       
     * @version: 
     *
     * @param promotionId
     * @param version
     * @param promotionType
     * @return
     *
     */
    @RequestMapping("/view")
    @ResponseBody
    public ModelAndView view(@RequestParam("id") Integer promotionId, @RequestParam("version") String version,
            @RequestParam("promotionType") String promotionType) {
        ModelAndView mav = new ModelAndView("/promotion/promotionGoodsSku/promotionGoodsSkuView");

        Promotion promotion = promotionBiz.selectById(promotionId);
        if (!promotion.getVersion().equals(version)) {
            logger.error("活动已经更新，请刷新页面重试");
        }

        if (!promotion.getStatus().equals(Constants.PromotionStatus.New.v())) {
            logger.error("活动状态非新建状态，无法设置活动商品");
        }
        mav.addObject("promotionId", promotionId);
        mav.addObject("promotionVersion", version);
        mav.addObject("promotionType", promotionType);
        return mav;
    }
    /**
     * 
     * @Title: viewGrid
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2015年11月1日 下午5:55:30       
     * @version: 
     *
     * @param search
     * @return
     *
     */
    @RequestMapping("/viewGrid")
    @ResponseBody
    public Grid viewGrid(PromotionGoodSkuSearch search) {
        Promotion promotion = promotionBiz.selectById(search.getPromotionId());
        List<PromotionGoodsSku> listPro = new ArrayList<PromotionGoodsSku>();//活动商品
        List<Sku> listSku = new ArrayList<Sku>();//商品列表
        List<PromotionGoodsSkuVo> listSkuVo = new ArrayList<PromotionGoodsSkuVo>();//页面活动商品表
        List<String> listSkuInPromotion = new ArrayList<String>();//获取参加活动商品列表

        int promotionType = promotion.getPromotionType();
        /**************
         * 秒杀需要获取秒杀的限购数量
         */

        long count = 0;
        //根据promotionId、isDelete查询活动商品表中
        listSkuInPromotion = promotionGoodsSkuBiz.searchIsPromotionSku();
        listPro = promotionGoodsSkuBiz.searchPageByPromotionId(search.getPromotionId(), Constants.Whether.No.v(),
                search.getStartIndex(), search.getPageSize());
        count = promotionGoodsSkuBiz.searchPageCount(search.getPromotionId(), Constants.Whether.No.v());
        //根据页面查询条件查询商品列表
        for (PromotionGoodsSku pro : listPro) {
            PromotionGoodsSkuVo tempVo = new PromotionGoodsSkuVo();
            CloneUtils.cloneObject(pro, tempVo);
            listSkuVo.add(tempVo);
            Sku sku = skuBiz.selectSkuById(pro.getSkuId());
            Stocks stocks = stocksBiz.selectStocksBySkuId(sku.getSkuId());
            CloneUtils.cloneObject(sku, tempVo);
            tempVo.setStockNum(stocks.getStockNum());
            tempVo.setPromotionType(promotionType);

        }
        //遍历活动商品表和商品表，
        Grid grid = new Grid();
        grid.setRows(listSkuVo);
        grid.setPage(search.getPageIndex());
        grid.setPageSize(search.getPageSize());
        grid.setRecords(count);

        return grid;

    }

    /**
     * 
     * @Title: changeList
     * @Description: TODO    ：    修改商品列表
     * @author: sunyf    
     * @date: 2015年10月1日 下午2:12:46       
     * @version: 
     *
     * @param promotionId
     * @param version
     * @param promotionType
     * @return
     *
     */
    @RequestMapping("/changeList")
    @ResponseBody
    public ModelAndView changeList(@RequestParam("id") Integer promotionId, @RequestParam("version") String version,
            @RequestParam("promotionType") String promotionType) {
        ModelAndView mav = new ModelAndView("/promotion/promotionGoodsSku/promotionGoodsSkuChangeList");

        Promotion promotion = promotionBiz.selectById(promotionId);
        if (!promotion.getVersion().equals(version)) {
            logger.error("活动已经更新，请刷新页面重试");
        }

        if (!promotion.getStatus().equals(Constants.PromotionStatus.New.v())) {
            logger.error("活动状态非新建状态，无法设置活动商品");
        }
        mav.addObject("promotionId", promotionId);
        mav.addObject("promotionVersion", version);
        mav.addObject("promotionType", promotionType);
        return mav;
    }

    /**
     * 
     * @Title: grid
     * @Description: TODO    ：    根据查询条件返回商品列表
     * @author: sunyf    
     * @date: 2015年9月29日 下午3:11:33       
     * @version: 
     *
     * @param search
     * @return 活动页信息
     *
     */
    @RequestMapping("/grid")
    @ResponseBody
    public Grid grid(PromotionGoodSkuSearch search) {
        Promotion promotion = promotionBiz.selectById(search.getPromotionId());
        List<PromotionGoodsSku> listPro = new ArrayList<PromotionGoodsSku>();//活动商品
        List<PromotionGoodsSku> listInPro = new ArrayList<PromotionGoodsSku>();//已参加活动商品
        List<Sku> listSku = new ArrayList<Sku>();//商品列表
        List<Integer> listSkuId = new ArrayList<Integer>();//商品列表
        List<PromotionGoodsSkuVo> listSkuVo = new ArrayList<PromotionGoodsSkuVo>();//页面活动商品表
        List<String> listSkuInPromotion = new ArrayList<String>();//获取参加活动商品列表
        List<Integer> listSkuIdIn = new ArrayList<Integer>();

        if (search.getSkuIds() != null && !search.getSkuIds().equals("")) {
            search.setSkuIds(search.getSkuIds().replaceAll("，", ","));
            String[] skuIds = search.getSkuIds().split(",");
            for (String skuId : skuIds) {
                listSkuIdIn.add(Integer.parseInt(skuId));
            }
        }

        int promotionType = promotion.getPromotionType();
        /**************
         * 秒杀需要获取秒杀的限购数量
         */

        long count = 0;
        //根据当前活动开始结束时间查询在期间内的商品列表
        listInPro = promotionGoodsSkuBiz.searchIsPromotionSkuByDate(promotion.getStartTime(), promotion.getEndTime());
        for (PromotionGoodsSku pgs : listInPro) {
            listSkuId.add(pgs.getSkuId());
        }
        //根据promotionId、isDelete查询活动商品表中
        listSkuInPromotion = promotionGoodsSkuBiz.searchIsPromotionSku();
        listPro = promotionGoodsSkuBiz.searchByPromotionId(search.getPromotionId(), Constants.Whether.No.v());
        //根据页面查询条件查询商品列表
        listSku = skuBiz.selectSkuPromotionPage(search.getStartIndex(), search.getPageSize(), Constants.Whether.No.v(),
                search.categoryId, search.brandId, listSkuIdIn, search.startQty, search.endQty, search.name, listSkuId,
                1, 1);
        //根据页面查询条件查询商品总数
        count = skuBiz.selectSkuPromotionPageCount(null, search.categoryId, search.brandId, listSkuIdIn,
                search.startQty, search.endQty, search.name, listSkuId);
        //遍历活动商品表和商品表，
        for (Sku sku : listSku) {
            PromotionGoodsSkuVo tempVo = new PromotionGoodsSkuVo();
            CloneUtils.cloneObject(sku, tempVo);
            tempVo.setPromotionType(promotionType);
            Stocks stocks = stocksBiz.selectStocksBySkuId(sku.getSkuId());
            CloneUtils.cloneObject(sku, tempVo);
            tempVo.setStockNum(stocks.getStockNum());
            listSkuVo.add(tempVo);
        }
        Grid grid = new Grid();
        grid.setRows(listSkuVo);
        grid.setPage(search.getPageIndex());
        grid.setPageSize(search.getPageSize());
        grid.setRecords(count);

        return grid;

    }

    /**
     * 
     * @Title: changeGrid
     * @Description: TODO    ：    获取编辑商品列表
     * @author: sunyf    
     * @date: 2015年10月1日 下午4:30:33       
     * @version: 
     *
     * @param search
     * @return
     *
     */
    @RequestMapping("/changeGrid")
    @ResponseBody
    public Grid changeGrid(PromotionGoodSkuSearch search) {
        Promotion promotion = promotionBiz.selectById(search.getPromotionId());
        List<PromotionGoodsSku> listPro = new ArrayList<PromotionGoodsSku>();//活动商品
        List<Sku> listSku = new ArrayList<Sku>();//商品列表
        List<PromotionGoodsSkuVo> listSkuVo = new ArrayList<PromotionGoodsSkuVo>();//页面活动商品表
        List<String> listSkuInPromotion = new ArrayList<String>();//获取参加活动商品列表
        List<Integer> listSkuIdIn = new ArrayList<Integer>();
        
        
        int promotionType = promotion.getPromotionType();
        /**************
         * 秒杀需要获取秒杀的限购数量
         */

        long count = 0;
        //根据promotionId、isDelete查询活动商品表中
        listSkuInPromotion = promotionGoodsSkuBiz.searchIsPromotionSku();
        listPro = promotionGoodsSkuBiz.searchPageByPromotionId(search.getPromotionId(), Constants.Whether.No.v(),
                search.getStartIndex(), search.getPageSize());
        count = promotionGoodsSkuBiz.searchPageCount(search.getPromotionId(), Constants.Whether.No.v());
        //根据页面查询条件查询商品列表
        for (PromotionGoodsSku pro : listPro) {
            PromotionGoodsSkuVo tempVo = new PromotionGoodsSkuVo();
            CloneUtils.cloneObject(pro, tempVo);
            listSkuVo.add(tempVo);
            SkuBO skubo = skuBiz.selectSkuAndGoodsById(pro.getSkuId());
            CloneUtils.cloneObject(skubo, tempVo);
            Stocks stocks = stocksBiz.selectStocksBySkuId(skubo.getSkuId());
            CloneUtils.cloneObject(skubo, tempVo);
            tempVo.setStockNum(stocks.getStockNum());
            tempVo.setPromotionType(promotionType);

        }
        //遍历活动商品表和商品表，
        Grid grid = new Grid();
        grid.setRows(listSkuVo);
        grid.setPage(search.getPageIndex());
        grid.setPageSize(search.getPageSize());
        grid.setRecords(count);

        return grid;

    }

    /**
     * 
     * @Title: addGood
     * @Description: TODO    ：  添加商品 
     * @author: sunyf    
     * @date: 2015年10月1日 下午1:55:25       
     * @version: 
     *
     * @param skuId
     * @param promotionId
     * @param promotionPrice
     * @param discount
     * @param version
     * @return
     *
     */
    @RequestMapping("/addGood")
    @ResponseBody
    public Json addGood(@RequestParam("skuId") Integer skuId, @RequestParam("promotionId") Integer promotionId,
            BigDecimal promotionPrice, BigDecimal discount, @RequestParam("version") String version) {
        HoldGold holdGold = holdGoldBiz.selectBySkuId(skuId);
        Sku sku = skuBiz.selectSkuById(skuId);
        Goods goods = goodsBiz.selectByPrimaryKey(sku.getGoodsId());
        Goods haveGoods = null;
        Json reJson = new Json();
        PromotionGoodsSku proGS = new PromotionGoodsSku();
        proGS.setPromotionId(promotionId);
        proGS.setSkuId(skuId);
        proGS.setGoodsId(sku.getGoodsId());
        proGS.setPromotionPrice(promotionPrice);
        proGS.setDiscount(discount);
        proGS.setVillageExpense(holdGold.getVsAmount());//
        proGS.setLcExpense(holdGold.getLcAmount());//
        proGS.setIsDelete(Constants.Whether.No.v());
        proGS.setaExpense(holdGold.getaAmount());//
        proGS.setPlatformExpense(holdGold.getSupplierAmount());//
        proGS.setIntegralExpense(holdGold.getIntegrationCosts());//
        proGS.setManpowercosts(holdGold.getManpowerCosts());//
        proGS.setEngineerExpense(holdGold.getEngineerAmount());
        List<PromotionGoodsSku> proGoodsList = new ArrayList<PromotionGoodsSku>();
        proGoodsList = promotionGoodsSkuBiz.searchByPromotionId(promotionId, Constants.Whether.No.v());
        if (proGoodsList.size() > 0) {
            haveGoods =goodsBiz.selectByPrimaryKey(proGoodsList.get(0).getGoodsId());
        }
        if(haveGoods != null) {
            if (haveGoods.getStoreId() != null && goods.getStoreId() != null && !(haveGoods.getStoreId().equals(goods.getStoreId()))) {
                reJson.setSuccess(false);
                reJson.setMsg("1");
                return reJson;
            }
            if (haveGoods.getStoreId() != null && goods.getStoreId() == null ) {
                reJson.setSuccess(false);
                reJson.setMsg("1");
                return reJson;
            }
            if (haveGoods.getStoreId() == null && goods.getStoreId() != null ) {
                reJson.setSuccess(false);
                reJson.setMsg("1");
                return reJson;
            }
        }
        String newVersion = promotionGoodsSkuBiz.insertPromotionGood(promotionId, proGS, version);
        reJson.setSuccess(true);
        reJson.setObj(newVersion);
        return reJson;

    }

    /**
     * 
     * @Title: delGood
     * @Description: TODO    ：    将商品置为已删除状态
     * @author: sunyf    
     * @date: 2015年10月1日 下午3:10:37       
     * @version: 
     *
     * @param promotionId
     * @param promotionGoodsSkuId
     * @param version
     * @return
     *
     */
    @RequestMapping("/delGood")
    @ResponseBody
    public Json delGood(@RequestParam("promotionId") Integer promotionId,
            @RequestParam("promotionGoodsSkuId") Integer promotionGoodsSkuId, @RequestParam("version") String version) {

        String newVersion = promotionGoodsSkuBiz.updatePromotionGoodIsDel(promotionId, promotionGoodsSkuId, 1, version);
        Json reJson = new Json();
        reJson.setSuccess(true);
        reJson.setObj(newVersion);
        return reJson;

    }

    /**
     * 
     * @Title: changeHoldGold
     * @Description: TODO    ：    更新佣金信息
     * @author: sunyf    
     * @date: 2015年10月1日 下午4:47:52       
     * @version: 
     *
     * @param promotionGoodsSkuVo
     * @return
     *
     */
    @RequestMapping("/changeHoldGold")
    @ResponseBody
    public Json changeHoldGold(PromotionGoodsSkuVo promotionGoodsSkuVo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lcExpense", promotionGoodsSkuVo.getLcExpense());
        map.put("villageExpense", promotionGoodsSkuVo.getVillageExpense());
        map.put("manpowercosts", promotionGoodsSkuVo.getManpowercosts());
        map.put("integralExpense", promotionGoodsSkuVo.getIntegralExpense());
        map.put("engineerExpense", promotionGoodsSkuVo.getEngineerExpense());
        map.put("platformExpense", promotionGoodsSkuVo.getPlatformExpense());
        map.put("aExpense", promotionGoodsSkuVo.getaExpense());
        map.put("promotionGoodsSkuId", promotionGoodsSkuVo.getPromotionGoodsSkuId());
        String newVersion = promotionGoodsSkuBiz.updateHoldGold(promotionGoodsSkuVo.getPromotionGoodsSkuId(),
                promotionGoodsSkuVo.getVersion(), promotionGoodsSkuVo.getPromotionId(), map);

        Json reJson = new Json();
        reJson.setSuccess(true);
        reJson.setObj(newVersion);
        return reJson;
    }

    @RequestMapping("/resetHoldGold")
    @ResponseBody
    public Json resetHoldGold(@RequestParam("skuId") Integer skuId) {

        HoldGold holdGold = holdGoldBiz.selectBySkuId(skuId);
        Json reJson = new Json();
        reJson.setSuccess(true);
        reJson.setObj(holdGold);
        return reJson;
    }

    /**
     * 
     * @Title: addJSON
     * @Description: TODO    ：    根据页面的返回的json添加商品
     * @author: sunyf    
     * @date: 2015年9月26日 下午6:56:56       
     * @version: 
     *
     * @param jsonStr
     * @param promotionId
     * @param request
     * @return
     *
     */
    @RequestMapping("/addJSON")
    public ModelAndView addJSON(@RequestParam("subJSON") String jsonStr,
            @RequestParam("promotionId") Integer promotionId, @RequestParam("promotionVersion") String version,
            HttpServletRequest request) {
        JSONObject jsonobject = JSONObject.fromObject(jsonStr);
        JSONArray array = jsonobject.getJSONArray("proGoodsList");

        List<PromotionGoodsSku> proGoodslist = new ArrayList<PromotionGoodsSku>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = (JSONObject) array.get(i);
            PromotionGoodsSku promotionGoodsSku = new PromotionGoodsSku();
            PromotionGoodsSkuVo promotionGoodsSkuVo = (PromotionGoodsSkuVo) JSONObject.toBean(object,
                    PromotionGoodsSkuVo.class);
            if (promotionGoodsSkuVo != null) {
                CloneUtils.cloneObject(promotionGoodsSkuVo, promotionGoodsSku);
                proGoodslist.add(promotionGoodsSku);
            }
        }
        promotionGoodsSkuBiz.updateIsdeleteByPromotionId(promotionId, proGoodslist, version);
        //转换PassportLendsEntity 实体类

        return new ModelAndView("redirect:/promotionGift/list");
    }

    /**
     * 新增
     * 
     * @param request
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage(HttpServletRequest request) {
        return "/promotion/promotionGoodsSku/promotionGoodsSkuAdd";
    }

    // 新增
    @RequestMapping("/add")
    public ModelAndView add(PromotionGiftVo promotionGiftVo, Errors errors) {
        Promotion promotion = new Promotion();
        PromotionGift promotionGift = new PromotionGift();

        promotionGiftVo.setCreateTime(new Date());
        promotionGiftVo.setPromotionType(Constants.PromotionType.Gift.v());
        promotionGiftVo.setIsDelete(Constants.Whether.No.v());
        promotionGiftVo.setIsAllUser(Constants.Whether.Yes.v());
        promotionGiftVo.setIsAllSku(Constants.Whether.Yes.v());
        promotionGiftVo.setStatus(Constants.PromotionStatus.New.v());
        //TODO
        promotionGiftVo.setCreateUserId("test");

        CloneUtils.cloneObject(promotionGiftVo, promotion);
        CloneUtils.cloneObject(promotionGiftVo, promotionGift);

        //设置活动类型
        return new ModelAndView("redirect:/promotionGift/list");
    }

    @RequestMapping("/delete")
    public ModelAndView deleteById(@RequestParam("id") int id) {
        return new ModelAndView("redirect:/promotionGift/list");

    }

    @RequestMapping("/editPage")
    public String editPage(String id, HttpServletRequest request) {
        Promotion promotion;
        PromotionGift promotionGift;
        PromotionGiftVo promotionGiftVo = new PromotionGiftVo();
        promotion = promotionBiz.selectById(Integer.parseInt(id));
        CloneUtils.cloneObject(promotion, promotionGiftVo);
        request.setAttribute("promotionGiftVo", promotionGiftVo);
        return "/promotion/promotionGift/promotionGiftEdit";
    }

    @RequestMapping("/edit")
    public ModelAndView edit(PromotionGiftVo promotionGiftVo) {
        Promotion promotion = new Promotion();
        PromotionGift promotionGift = new PromotionGift();

        CloneUtils.cloneObject(promotionGiftVo, promotion);
        CloneUtils.cloneObject(promotionGiftVo, promotionGift);

        promotion.setPromotionType(Constants.PromotionType.Gift.v());

        return new ModelAndView("redirect:/promotionGift/list");
    }

}
