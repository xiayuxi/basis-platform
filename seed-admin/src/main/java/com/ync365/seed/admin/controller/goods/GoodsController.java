package com.ync365.seed.admin.controller.goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ync365.seed.admin.Interceptor.LogAnnotation;
import com.ync365.seed.admin.controller.base.BaseController;
import com.ync365.seed.admin.utils.LoginUserUtils;
import com.ync365.seed.admin.vo.base.Grid;
import com.ync365.seed.admin.vo.base.Json;
import com.ync365.seed.admin.vo.base.PageFilter;
import com.ync365.seed.admin.vo.goods.GoodsVo;
import com.ync365.seed.bussiness.modules.common.biz.RegionBiz;
import com.ync365.seed.bussiness.modules.common.biz.UnitBiz;
import com.ync365.seed.bussiness.modules.common.entity.Region;
import com.ync365.seed.bussiness.modules.common.entity.Unit;
import com.ync365.seed.bussiness.modules.goods.biz.AuditBiz;
import com.ync365.seed.bussiness.modules.goods.biz.BlobBiz;
import com.ync365.seed.bussiness.modules.goods.biz.BrandBiz;
import com.ync365.seed.bussiness.modules.goods.biz.CategoryBiz;
import com.ync365.seed.bussiness.modules.goods.biz.FeatureBiz;
import com.ync365.seed.bussiness.modules.goods.biz.GoodsBiz;
import com.ync365.seed.bussiness.modules.goods.biz.HoldGoldBiz;
import com.ync365.seed.bussiness.modules.goods.biz.PicInfoBiz;
import com.ync365.seed.bussiness.modules.goods.biz.SaleRegionBiz;
import com.ync365.seed.bussiness.modules.goods.biz.SkuBiz;
import com.ync365.seed.bussiness.modules.goods.biz.StocksBiz;
import com.ync365.seed.bussiness.modules.goods.bo.GoodsBO;
import com.ync365.seed.bussiness.modules.goods.dao.SkuFeatureDao;
import com.ync365.seed.bussiness.modules.goods.entity.Blob;
import com.ync365.seed.bussiness.modules.goods.entity.Brand;
import com.ync365.seed.bussiness.modules.goods.entity.Category;
import com.ync365.seed.bussiness.modules.goods.entity.Feature;
import com.ync365.seed.bussiness.modules.goods.entity.Goods;
import com.ync365.seed.bussiness.modules.goods.entity.HoldGold;
import com.ync365.seed.bussiness.modules.goods.entity.PicInfo;
import com.ync365.seed.bussiness.modules.goods.entity.SaleRegion;
import com.ync365.seed.bussiness.modules.goods.entity.Sku;
import com.ync365.seed.bussiness.modules.goods.entity.SkuFeature;
import com.ync365.seed.bussiness.modules.goods.entity.Stocks;
import com.ync365.seed.bussiness.modules.user.biz.SysAdminInfoBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysPopStoreBiz;
import com.ync365.seed.bussiness.modules.user.biz.SysUserInfoBiz;
import com.ync365.seed.bussiness.modules.user.bo.AdminBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreBO;
import com.ync365.seed.bussiness.modules.user.bo.PopStoreSearchBO;
import com.ync365.seed.bussiness.modules.user.entity.SysPopStore;
import com.ync365.seed.commons.mapper.JsonMapper;
import com.ync365.seed.utils.DateUtils;
import com.ync365.seed.utils.GoodsConstants;
import com.ync365.seed.utils.StringUtils;
import com.ync365.seed.web.vo.ZTreeVo;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController{

    @Autowired
    private GoodsBiz goodsBiz;
    @Autowired
    private FeatureBiz featureBiz;
    @Autowired
    private BrandBiz brandBiz;
    @Autowired
    private CategoryBiz categoryBiz;
    @Autowired
    private PicInfoBiz picInfoBiz;

    @Autowired
    private HoldGoldBiz holdGoldBiz;

    @Autowired
    private RegionBiz regionBiz;

    @Autowired
    private BlobBiz blobBiz;
    
    @Autowired
    private SaleRegionBiz saleRegionBiz;
    
    @Autowired
    private AuditBiz auditBiz;
    
    @Autowired
    private SkuBiz skuBiz;
    
    @Autowired
    private StocksBiz stocksBiz;
    
    @Autowired
    private SkuFeatureDao skuFeatureDao;
    
    @Autowired
    private SysPopStoreBiz sysPopStoreBiz;
    
    @Autowired
    private SysUserInfoBiz sysUserInfoBiz;
    
    @Autowired
    private SysAdminInfoBiz sysAdminInfoBiz;
    
    @Autowired
    private UnitBiz unitBiz;


    @RequestMapping("/list")
    @LogAnnotation(operatingContent="商品管理模块>商品管理>商品列表页面")
    public String list(HttpServletRequest request) {
        /**
         * 请求品牌数据
         */
        List<Brand> brandList = brandBiz.getAllbrandList();
        request.setAttribute("brandList", brandList);
        
        /**
         * 第一次分类列表
         */
        List<Category> catList=categoryBiz.getCategoryByParentId(0);
        request.setAttribute("catList", catList);
        
        return "goods/goodsList";
    }
    
    @RequestMapping("/releaseGrid")
    @ResponseBody
    @LogAnnotation(operatingContent="商品管理模块>商品管理>查询")
    public Grid releaseDataGrid(String status,String name,GoodsVo goodsVo,String categoryId,PageFilter filter) {
        Grid grid = new Grid();
        List<GoodsBO> list = new ArrayList<GoodsBO>();
        String storeName ="";
        String releaseUser ="";
        
        long count = 0;
        int qStatus = Integer.parseInt(status);
        Map<String ,Object> map=new HashMap<String,Object>();
        try {
            //查询所有的状态值为 5
            if(qStatus!=5){
                map.put("status", Integer.parseInt(status));
            }else{
                map.put("allStatus", -1);
            }
            if(name!=null&&name!=""){
                map.put("name", name);              
            }
            if(goodsVo.getBeginDate()!=null&&goodsVo.getBeginDate()!=""){
                map.put("beginDate", DateUtils.stringToDate(goodsVo.getBeginDate(),
                        "yyyy-MM-dd HH:mm:ss"));
            }
            if(goodsVo.getEndDate()!=null&&goodsVo.getBeginDate()!=""){
                map.put("endDate", DateUtils.stringToDate(goodsVo.getEndDate(),
                        "yyyy-MM-dd HH:mm:ss"));
            }
            if(categoryId!=null&&categoryId!=""){
                map.put("categoryId", categoryId);
            }
            if(goodsVo.getStoreName()!=null){
                storeName=goodsVo.getStoreName();
            }
            if(goodsVo.getReleaseUser()!=null){
                releaseUser=goodsVo.getReleaseUser();
            }
            if(goodsVo.getBrandName()!=null&&goodsVo.getBrandName()!=""){
                map.put("brandName",goodsVo.getBrandName());
            }
            if(null!=goodsVo.getGoodsType()&&goodsVo.getGoodsType()!=""){
                map.put("goodsType",goodsVo.getGoodsType());
            }
            list = goodsBiz.selectReleasePage(map, releaseUser,storeName,filter.getStartIndex(),filter.getRows());
            count = goodsBiz.selectReleasePageCount(map,releaseUser,storeName);
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        grid.setRows(list);
        grid.setRecords(count);
        grid.setPageSize(filter.getRows());
        grid.setPage(filter.getPage());
        return grid;
      }
    
    @RequestMapping("/addPage")
    @LogAnnotation(operatingContent="商品管理模块>商品管理>发布商品面页")
    public String addPage(Model model) {
        UUID id=UUID.randomUUID();
        String uuid=id.toString();
        
        model.addAttribute("uuid",uuid);
        
        List<Unit> units=unitBiz.selectAllUnit();
        
        model.addAttribute("units", units);

        return "/goods/goodsAdd";
    }

    @RequestMapping("/add")
    @LogAnnotation(operatingContent="商品管理模块>商品管理>保存发布商品")
    public String insert(GoodsVo vo,HttpServletRequest request) throws IOException {
        vo.getGoods().setCreateTime(Calendar.getInstance().getTime());
        String uuid = vo.getGoods().getUuid();
        Map<String ,Object> map=new HashMap<String,Object>();
        map.put("uuid", uuid);
        //picInfo中所有的图片信息
        List<PicInfo> pil=picInfoBiz.selectPicInfoListByUuid(map);
        
        //提交时图片信息
        List<String> ls=new LinkedList<String>();
        ls.add(vo.getCarouselPicture1());
        ls.add(vo.getCarouselPicture2());
        ls.add(vo.getCarouselPicture3());
        ls.add(vo.getCarouselPicture4());
        ls.add(vo.getCarouselPicture5());
       //比较多于的图片，然后删除
       for(PicInfo p:pil ){
			boolean exits = true;
			String picName = p.getPicName() ;
			for(String u:ls ){
				if(picName.equals(u)){
					exits = true;
					break;
				}else{
					exits = false;
				}
			}
			Integer picId = p.getId() ;
			if(!exits){
				picInfoBiz.deleteByPrimaryKey(picId);
			}
		}
		//添加序号
		for(int i = 0;i<ls.size();i++){
			Map<String ,Object> m =new HashMap<String,Object>();
			m.put("picName", ls.get(i));
			PicInfo p=picInfoBiz.selectPicInfoByPicName(m);
			if(p!=null){
				p.setSeq(i+1);
			}
			
			picInfoBiz.updateByPrimaryKey(p);
		}
		 AdminBO adminBo=LoginUserUtils.getUser();
        goodsBiz.addGoods(vo.getGoods(), vo.getBlob(),adminBo,request);
        return "redirect:list";
    }

    /**
     * 
     * @Title: getCategoryByBrandId
     * @Description:    ：    
     * @author: guanfl    
     * @date: 2015年9月28日 下午2:13:59       
     * @version: 
     *
     * @param brandId
     * @return
     *
     */
    @RequestMapping("/getCategoryByBrandId")
    @ResponseBody
    public List<Category> getCategoryByBrandId(Integer brandId) {
        List<Category> categoryList = categoryBiz.getCategoryByBrandId(brandId);
        return categoryList;
    }
    
    /***
     * 通过品牌获得分类列表
     * @param brandId
     * @return
     */
    @RequestMapping("/getCategoryTreeByBrandId")
    @ResponseBody
    public List<ZTreeVo> getCategoryByParentId(Integer brandId) {
        List<ZTreeVo> list = new ArrayList<ZTreeVo>();
        List<Category> categoryList = null;
        if (brandId == null) {
            categoryList = categoryBiz.selectCategoryAll();
        } else {
            categoryList = categoryBiz.getCategoryByBrandId(brandId);
        }
        for (Category category : categoryList) {
            ZTreeVo zTreeVo = new ZTreeVo();
            zTreeVo.setId(category.getCategoryId().toString());
            zTreeVo.setName(category.getName());
            zTreeVo.setpId(category.getParentId().toString());
            list.add(zTreeVo);
        }
        return list;
    }

    @RequestMapping("/editPage")
    @LogAnnotation(operatingContent="商品管理模块>商品管理>编辑商品面页")
    public String show(Model model, @RequestParam("goodId") Integer goodId,HttpServletRequest request) {
        // 前台展示Vo
        GoodsVo goodView = new GoodsVo();
        Goods good = goodsBiz.selectByPrimaryKey(goodId);
        
        
        //查找出店铺的id和培肥站的编号
        if(good.getStoreId()!=null){
            Integer storeId=good.getStoreId();
            PopStoreBO sps= sysPopStoreBiz.selectPopStoreById(good.getStoreId());
            model.addAttribute("sps", sps);
        } 
           
        // 查询除输入属性的所有属性列表
        List<Feature> listFeatures = featureBiz.getFeaturesByCategoryId(good
                .getCategoryId());
        goodView.setGoods(good);
        goodView.setFeatures(listFeatures);
             
        List<Sku> skus=good.getSkus();
        List<Stocks> ss=new ArrayList<Stocks>();
        //每sku下面可能拥有不同的佣金
        for(Sku sku:skus){
            //设置每一个sku的佣金
            int skuId=sku.getSkuId();
            
            HoldGold holdGold=holdGoldBiz.selectBySkuId(skuId);
             
            sku.setHoldGold(holdGold);
            
            List<SaleRegion> saleRegions=saleRegionBiz.getSaleRegionsBySkuId(sku.getSkuId());
                   
            //设置每一个sku的区域
            List<Integer> regionId=new ArrayList<Integer>();
            for(int i=0;i<saleRegions.size();i++){
                int id=saleRegions.get(i).getRegionId(); 
                regionId.add(id);
            }
            
            sku.setRegionId(regionId);
            
            Stocks s=stocksBiz.selectStocksBySkuId(sku.getSkuId());
            ss.add(s);
            
        }
            
        model.addAttribute("ss",ss);
        
        JsonMapper js = new JsonMapper();
        String str = js.toJson(listFeatures);
        model.addAttribute("model", goodView);
        model.addAttribute("json", str);
        // 查询所有商品对应的规格属性集合
        List<Feature> featuresList = goodsBiz.selectFeaturesIdByGoodsId(goodId,
                1);
        model.addAttribute("featuresList", featuresList);

        // 分类
        List<Category> categoryList =  categoryBiz.selectAll();
        

        // 品牌
        List<Brand> brandList = brandBiz.getAllbrandList();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brandList", brandList);

        // 图片
        PicInfo picInfo = new PicInfo();
        picInfo.setUuid(good.getUuid());
        List<PicInfo> picInfoList = picInfoBiz.selectByPicInfo(picInfo);
        model.addAttribute("picInfoList", picInfoList);

        // 详情
        Blob blob = blobBiz.selectByPrimaryKey(goodId);
        goodView.setBlob(blob);
        
        //单位列表
         List<Unit> units=unitBiz.selectAllUnit();
        
         model.addAttribute("units", units);
        return "/goods/goodsEdit";
    }

    @RequestMapping("/edit")
    @LogAnnotation(operatingContent="商品管理模块>商品管理>保存编辑商品")
    public String edit(GoodsVo view,HttpServletRequest request) {
        view.getGoods().setCreateTime(Calendar.getInstance().getTime());
        AdminBO adminBo=LoginUserUtils.getUser();
        goodsBiz.updateGoodsAndSku(view.getGoods(), view.getBlob(),adminBo,request);
        return "redirect:list";
    }

    @RequestMapping("/delete")
    @LogAnnotation(operatingContent="商品管理模块>商品管理>删除商品")
    @ResponseBody
    public Json delete(@RequestParam("goodsId") Integer goodsId) {
        Json json = new Json();
        int temp = 0;
        try{
            temp = goodsBiz.logicDel(goodsId);
            if(temp == 1){
                json.setSuccess(true);
                json.setMsg("yse");
            } else if(temp ==2 ){
                json.setSuccess(false);
                json.setMsg("当前状态不可删除");
            } else{
                json.setSuccess(false);
                json.setMsg("删除失败");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return json;

    }

    @RequestMapping("/getFeatureByCategoryId")
    @ResponseBody
    public List<Feature> getFeatureByCategoryId(
            @RequestParam("categoryId") Integer categoryId) {
        List<Feature> listFeature = featureBiz
                .getFeaturesByCategoryId(categoryId);
        return listFeature;
    }
    
    /**
     *  跳转到添加佣金属性值对话框
     *   */
    @RequestMapping("/addHoldGoldValuePage")
    public String addValuePage(HttpServletRequest request, Integer id) {
        return "goods/holdGold_values";
    }
    
    
    
    /**
     *  跳转到添加区域属性值对话框
     *   */
    @RequestMapping("/addRegionValuePage")
    public String addRegionValuePage(HttpServletRequest request, Integer id,Model model) {
        List<Region> regionList1 = regionBiz.selectByLevel(1);
        List<Region> regionList2 = regionBiz.selectByLevel(2);
        
        model.addAttribute("regionList1",regionList1);
        model.addAttribute("regionList2",regionList2);
        
        return "goods/region_values";
    }
    
    
    /***
     * 跳转到培肥站搜索的对话框
     */
    @RequestMapping("addFsNamePage")
    public String addFsNamePage(HttpServletRequest request, Integer id,Model model){
        PopStoreSearchBO pb=new PopStoreSearchBO();
        pb.setPopStoreTypeSearch("FS");
        List<SysPopStore> sysFsList=sysPopStoreBiz.selectPopStoreByStoreNamePrimary(pb);
        model.addAttribute("sysFsList", sysFsList);
        return "goods/fertilizer_station";
    }
    
    
    /***
     * 跳转到店搜索的对话框
     * 
     */
    @RequestMapping("/addStoreNamePage")
    public String addStoreNamePage(HttpServletRequest request, Integer id,Model model) {
        PopStoreSearchBO pbds=new PopStoreSearchBO();
        pbds.setPopStoreTypeSearch("DS");
        List<SysPopStore> sysPopStoreListDs=sysPopStoreBiz.selectPopStoreByStoreNamePrimary(pbds);
        
        PopStoreSearchBO pbsp=new PopStoreSearchBO();
        pbsp.setPopStoreTypeSearch("SP");
        List<SysPopStore> sysPopStoreListSp=sysPopStoreBiz.selectPopStoreByStoreNamePrimary(pbsp);
        
        List<SysPopStore> sysPopStoreList=new ArrayList<SysPopStore>();
        
        sysPopStoreList.addAll(sysPopStoreListDs);
        sysPopStoreList.addAll(sysPopStoreListSp);
        
        model.addAttribute("sysPopStoreList", sysPopStoreList);
        return "goods/store_code";
    }
    
    @RequestMapping("/selectFsList")
    @ResponseBody
    public List<SysPopStore> selectFsList(@RequestParam("FsName") String  FsName){
        PopStoreSearchBO pb=new PopStoreSearchBO();
        pb.setPopStoreNameSearch(FsName);
        pb.setPopStoreTypeSearch("FS");
        List<SysPopStore> sysFsList=sysPopStoreBiz.selectPopStoreByStoreNamePrimary(pb);
        return sysFsList;
    }
    
    @RequestMapping("/selectSysPopStoreList")
    @ResponseBody
    public List<SysPopStore> selectSysPopStoreList(@RequestParam("storeName") String  storeName){
        PopStoreSearchBO pbds=new PopStoreSearchBO();
        pbds.setPopStoreNameSearch(storeName);
        pbds.setPopStoreTypeSearch("DS");
        List<SysPopStore> sysPopStoreListDs=sysPopStoreBiz.selectPopStoreByStoreNamePrimary(pbds);
        
        PopStoreSearchBO pbsp=new PopStoreSearchBO();
        pbsp.setPopStoreNameSearch(storeName);
        pbsp.setPopStoreTypeSearch("SP");
        List<SysPopStore> sysPopStoreListSp=sysPopStoreBiz.selectPopStoreByStoreNamePrimary(pbsp);
        
        List<SysPopStore>   sysPopStoreList =new ArrayList<SysPopStore>();
        
        sysPopStoreList.addAll(sysPopStoreListDs);
        sysPopStoreList.addAll(sysPopStoreListSp);
        
        return sysPopStoreList;
    }
    /**
     * 区域回显，通过regionId,获得三级联动的所有区域保存到RegionChain中
     * */
    
    List<Region> regionChain=null;//保存三级联动区域的链表
    
    @RequestMapping("/getRegionChainByRegionId")
    @ResponseBody
    public List<Region> getRegionChainByRegionId(@RequestParam("regionId") int regionId,HttpServletRequest request){
        Region region=regionBiz.selectById(regionId);
        regionChain=regionBiz.selectRegionChain(region); 
        return  regionChain;
    }
    /**
     * 判断商品的状态,只有上架商品不能修改
     * */
    @RequestMapping("/modifyJudgment")
    @ResponseBody
    public Json modifyJudgment( @RequestParam("goodId") Integer goodId){
        Goods good = goodsBiz.selectByPrimaryKey(goodId);
        Json json=new Json();
        /**
         * 1:通过  2：未通过  3：上架  4：下架   0：默认未审核
         */
        if(good.getStatus() !=  GoodsConstants.GoodsStatus.GROUNDING.v()){
            json.setSuccess(true);
        }else{
            json.setSuccess(false);
        }
        
        return json;
    }

  /**
  * 上架
  * @param skuId
  * @return
  */
 @RequestMapping("/grounding")
 @ResponseBody
 @LogAnnotation(operatingContent="商品管理模块>商品管理>上架/下架商品")
 public Json grounding(String goodsId,Integer status){
     Json json = new Json();
     Goods goods = goodsBiz.selectByPrimaryKey(Integer.parseInt(goodsId));
     /**
      * 1:通过  2：未通过  3：上架  4：下架   0：默认未审核
      */
     if(goods.getCode() == null ||goods.getCode().equals("")){
    	 json.setSuccess(false);
    	 json.setMsg("商品编号不能为空");
    	 return json;
     }
     
     try{
         /**
          * 上架状态为：3 下架状态为 4
         */
         AdminBO adminBo=LoginUserUtils.getUser();
         if(status == GoodsConstants.GoodsStatus.GROUNDING.v() ){
        	 if(goods.getStatus() != GoodsConstants.GoodsStatus.PASS.v() ){
        		 if(goods.getStatus() == GoodsConstants.GoodsStatus.UN_GROUNDING.v()){
        			 json.setMsg("此商品已经下架需要到审核中审核");
        		 }
            	 json.setSuccess(false);
             }else{
            	 int update = goodsBiz.groundingFun(goodsId,status,adminBo);
                 if(update == 1 ){
                     json.setSuccess(true);
                 }else{
                     json.setSuccess(false);
                 }
             }
         }else{
        	 int update = goodsBiz.groundingFun(goodsId,status,adminBo);
             if(update == 1 ){
                 json.setSuccess(true);
             }else{
                 json.setSuccess(false);
             }
         }
         
     }catch(Exception e){
         e.printStackTrace();
         json.setSuccess(false);
     }
     return json;
 }
 
 /***
  * 商品管理上下架的复制功能
  * @Title: copyGoods
  * @Description:     ：    
  * @author: guanfl    
  * @date: 2015年10月12日 下午4:22:16       
  * @version: 
  *
  * @param goodsId
  * @return
  *
  */
 @RequestMapping("/copyGoods")
 @LogAnnotation(operatingContent="商品管理模块>商品管理>复制商品")
 public ModelAndView copyGoods(@RequestParam("goodsId") Integer goodsId){
     
     //商品
     Goods goodsSrc=goodsBiz.selectByPrimaryKey(goodsId);
     goodsSrc.setGoodsId(null);
     goodsSrc.setCreateTime(DateUtils.stringToDate(DateUtils.getSystemTime(),
                    "yyyy-MM-dd HH:mm:ss"));
     goodsSrc.setStatus(0);
     //插入一条新商品
     goodsBiz.add(goodsSrc);
     
     //根据goods添加skus
     List<Sku> skuList=goodsSrc.getSkus();
     
     for(int i=0;i<skuList.size();i++){
         
         Sku sku=skuList.get(i);
         int temp=sku.getSkuId();
         sku.setSkuId(null);
         sku.setGoodsId(goodsSrc.getGoodsId());
         sku.setCreateTime(DateUtils.stringToDate(DateUtils.getSystemTime(),
                 "yyyy-MM-dd HH:mm:ss"));
         sku.setOptDate(DateUtils.stringToDate(DateUtils.getSystemTime(),
                 "yyyy-MM-dd HH:mm:ss"));
         sku.setStatus(GoodsConstants.GoodsStatus.UN_AUDIT.v());
         skuBiz.insert(sku);
         
         //插入佣金
         HoldGold holdGold=holdGoldBiz.selectBySkuId(temp);
         holdGold.setSkuId(sku.getSkuId());
         holdGold.setGoodsId(goodsSrc.getGoodsId());
         holdGoldBiz.insert(holdGold);
         
         //插入saleRegion
         
         List<SaleRegion> saleRegions=saleRegionBiz.getSaleRegionsBySkuId(temp);
         for(SaleRegion sr:saleRegions){
             sr.setSaleRegionId(null);
             sr.setGoodsId(goodsSrc.getGoodsId());
             sr.setSkuId(sku.getSkuId());
             saleRegionBiz.insert(sr);
         }
            
         //插入库存
         Stocks stocks=stocksBiz.selectStocksBySkuId(temp);
         stocks.setStocksId(null);
         stocks.setGoodsId(goodsSrc.getGoodsId());
         stocks.setSkuId(sku.getSkuId());
         stocksBiz.insert(stocks);
        
         //插入非规格属性
         List<SkuFeature> skuFeatures= goodsSrc.getSkuFeatures();
         for(SkuFeature sf:skuFeatures){
             sf.setTid(null);
             sf.setSkuId(sku.getSkuId());
             skuFeatureDao.insert(sf);
         }
         //插入规格属性
         List<SkuFeature> skuFeatures2 =sku.getSkuFeatures();
         for(SkuFeature sf:skuFeatures2){
             sf.setTid(null);
             sf.setSkuId(sku.getSkuId());
             skuFeatureDao.insert(sf);
         }
     }
      
     //商品详情
     Blob blob=blobBiz.selectByPrimaryKey(goodsId);
     blob.setGoodsId(goodsSrc.getGoodsId());
     blobBiz.insert(blob);
     return new ModelAndView("redirect:/goods/list");
 }

    //验证编号是否存在
    @RequestMapping("/validateGoodsCode")
    @ResponseBody
    public Json validateGoodsCode(String code){
        Json json = new Json();
       
        if(StringUtils.isBlank(code)){
            json.setSuccess(false);
            json.setMsg("参数错误");
            return json ;
        }
        
        try{
            int update = goodsBiz.validateGoodsCode(code);
            if(update == 0 ){
                json.setSuccess(true);
            }else if(update>0){
                json.setSuccess(false);
                json.setMsg("编号已存在");
            }else{
                json.setSuccess(false);
                json.setMsg("查询失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            json.setSuccess(false);
        }
        
        return json;
    }
    
   /***
    * 预览
    * @param goodId
    * @return
    */
    @RequestMapping("/preview")
    public String preview(@RequestParam("goodId") Integer goodId,@RequestParam("status") Integer status, Model model){
    	 // 前台展示Vo
        GoodsVo goodView = new GoodsVo();
        Goods good = goodsBiz.selectByPrimaryKey(goodId);
        
        
        //查找出店铺的id和培肥站的编号
        if(good.getStoreId()!=null){
            Integer storeId=good.getStoreId();
            PopStoreBO sps= sysPopStoreBiz.selectPopStoreById(good.getStoreId());
            model.addAttribute("sps", sps);
        } 
           
        // 查询除输入属性的所有属性列表
        List<Feature> listFeatures = featureBiz.getFeaturesByCategoryId(good
                .getCategoryId());
        goodView.setGoods(good);
        goodView.setFeatures(listFeatures);
             
        List<Sku> skus=good.getSkus();
        List<Stocks> ss=new ArrayList();
        //每sku下面可能拥有不同的佣金
        for(Sku sku:skus){
            //设置每一个sku的佣金
            int skuId=sku.getSkuId();
            
            HoldGold holdGold=holdGoldBiz.selectBySkuId(skuId);
             
            sku.setHoldGold(holdGold);
            
            List<SaleRegion> saleRegions=saleRegionBiz.getSaleRegionsBySkuId(sku.getSkuId());
                   
            //设置每一个sku的区域
            List<Integer> regionId=new ArrayList<Integer>();
            for(int i=0;i<saleRegions.size();i++){
                int id=saleRegions.get(i).getRegionId(); 
                regionId.add(id);
            }
            
            sku.setRegionId(regionId);
            Stocks s=stocksBiz.selectStocksBySkuId(sku.getSkuId());
            ss.add(s);
        }
              
        model.addAttribute("ss",ss);
        
        JsonMapper js = new JsonMapper();
        String str = js.toJson(listFeatures);
        model.addAttribute("model", goodView);
        model.addAttribute("json", str);

        // 查询所有商品对应的规格属性集合
        List<Feature> featuresList = goodsBiz.selectFeaturesIdByGoodsId(goodId,
                1);
        model.addAttribute("featuresList", featuresList);

        // 分类
        List<Category> categoryList =  categoryBiz.selectAll();
        

        // 品牌
        List<Brand> brandList = brandBiz.getAllbrandList();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brandList", brandList);

        // 图片
        PicInfo picInfo = new PicInfo();
        picInfo.setUuid(good.getUuid());
        List<PicInfo> picInfoList = picInfoBiz.selectByPicInfo(picInfo);
        model.addAttribute("picInfoList", picInfoList);

        // 详情
        Blob blob = blobBiz.selectByPrimaryKey(goodId);
        goodView.setBlob(blob);
        
        //单位列表
         List<Unit> units=unitBiz.selectAllUnit();
        
         model.addAttribute("units", units);
         
         model.addAttribute("status",status);
         
    	return "/goods/goodsPreview";
    }
    
    /**
     * 店铺商品列表
     * @Title: listStore
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月31日 下午7:44:04       
     * @version: 
     *
     * @param request
     * @return
     *
     */
    @RequestMapping("/storeList")
    @LogAnnotation(operatingContent="入驻商品商管理模块>商品管理>商品列表页面")
    public String listStore(HttpServletRequest request) {
        /**
         * 请求品牌数据
         */
        List<Brand> brandList = brandBiz.getAllbrandList();
        request.setAttribute("brandList", brandList);
        
        /**
         * 第一次分类列表
         */
        List<Category> catList=categoryBiz.getCategoryByParentId(0);
        request.setAttribute("catList", catList);
        
        return "goods/goodsStoreList";
    }
    
    @RequestMapping("/storeGrid")
    @ResponseBody
    @LogAnnotation(operatingContent="商品管理模块>商品管理>查询")
    public Grid storeDataGrid(String status,String name,GoodsVo goodsVo,String categoryId,PageFilter filter) {
        Grid grid = new Grid();
        List<GoodsBO> list = new ArrayList<GoodsBO>();
        String storeName ="";
        String releaseUser ="";
        
        long count = 0;
        int qStatus = Integer.parseInt(status);
       
        Map<String ,Object> map=new HashMap<String,Object>();
        
        AdminBO adminBo=LoginUserUtils.getUser();
        //pop对象
        PopStoreBO popBo = sysPopStoreBiz.selectPopStoreBOByPopNum(adminBo.getAdminNum());
        
        try {
            //查询所有的状态值为 5
            if(qStatus!=5){
                map.put("status", Integer.parseInt(status));
            }else{
                map.put("allStatus", -1);
            }
            if(name!=null&&name!=""){
                map.put("name", name);              
            }
            if(goodsVo.getBeginDate()!=null&&goodsVo.getBeginDate()!=""){
                map.put("beginDate", DateUtils.stringToDate(goodsVo.getBeginDate(),
                        "yyyy-MM-dd HH:mm:ss"));
            }
            if(goodsVo.getEndDate()!=null&&goodsVo.getBeginDate()!=""){
                map.put("endDate", DateUtils.stringToDate(goodsVo.getEndDate(),
                        "yyyy-MM-dd HH:mm:ss"));
            }
            if(categoryId!=null&&categoryId!=""){
                map.put("categoryId", categoryId);
            }
            if(goodsVo.getStoreName()!=null){
                storeName=goodsVo.getStoreName();
            }
            if(goodsVo.getReleaseUser()!=null){
                releaseUser=goodsVo.getReleaseUser();
            }
            if(goodsVo.getBrandName()!=null&&goodsVo.getBrandName()!=""){
                map.put("brandName",goodsVo.getBrandName());
            }
            if(null!=goodsVo.getGoodsType()&&goodsVo.getGoodsType()!=""){
                map.put("goodsType",goodsVo.getGoodsType());
            }
            map.put("storeId", popBo.getId());
            list = goodsBiz.selectReleasePage(map, releaseUser,storeName,filter.getStartIndex(),filter.getRows());
            count = goodsBiz.selectReleasePageCount(map,releaseUser,storeName);
                
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
