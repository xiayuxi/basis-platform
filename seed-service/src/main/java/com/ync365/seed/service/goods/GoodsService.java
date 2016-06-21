package com.ync365.seed.service.goods;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ync365.seed.dto.base.ResponseDTO;
import com.ync365.seed.dto.goods.BrowseSearchDTO;
import com.ync365.seed.dto.goods.GoodsMemberDTO;
import com.ync365.seed.dto.goods.GoodsSearchDTO;
import com.ync365.seed.dto.goods.StoreGoodsSearchDTO;

/**
 * 商品接口
 * @ClassName: GoodsService
 * @Description: TODO
 * @author robo
 * @date 2015年9月18日 下午6:07:50
 *
 */
@Path("goods")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface GoodsService {

	//首页显示商品,列表页面sku值 
	
	/**----------------------------------首页BEGIN------------------------------------**/
	
	/**
	 * 获得分类列表
	 * 
	 * @Title: getCategoryList
	 * @Description: TODO
	 * @return
	 * @return ResponseDTO
	 * @throws
	 */
	@POST
	@GET
	@Path("getCategoryList")
	public ResponseDTO  getCategoryList();
	
	
	/**
	 * 
	 * 获得子分类列表
	 * @Title: getChildCategoryList
	 * @Description: TODO
	 * @param categoryId   分类id
	 * @param layer        级别
	 * @return ResponseDTO
	 * @throws
	 */
	@POST
	@GET
	@Path("getChildCategoryList")
	public ResponseDTO getChildCategoryList(Integer categoryId, int layer) ;

	/**
	 * 得到热卖商品
	 * @Title: getHotGoodsList
	 * @Description: TODO
	 * @param pageSize
	 * @return ResponseDTO    
	 * @throws
	 */
	@POST
	@GET
	@Path("getHotGoodsList/{pageSize}/{regionId}")
	public ResponseDTO getHotGoodsList(@PathParam("pageSize")int pageSize,@PathParam("regionId")Integer regionId);
	
	/**
	 * 得到新品上架
	 * @Title: getNewGoodsList
	 * @Description: TODO
	 * @param pageSize
	 * @return    
	 * ResponseDTO    
	 * @throws
	 */
	@POST
	@GET
	@Path("getNewGoodsList/{pageSize}/{regionId}")
	public ResponseDTO getNewGoodsList(@PathParam("pageSize") int pageSize,@PathParam("regionId")Integer regionId);

	/**
	 * 根据分类得到对应的商品列表
	 * @Title: getGoodsByCategoryList
	 * @Description: TODO
	 * @param pageSize
	 * @return ResponseDTO    
	 * @throws
	 */
	@POST
	@GET
	@Path("getGoodsByCategoryList/{pageSize}/{categoryId}/{regionId}")
	public ResponseDTO getGoodsByCategoryList(@PathParam("pageSize")int pageSize,@PathParam("categoryId") int categoryId,@PathParam("regionId")Integer regionId) ;
	
	/**
	 * 根据地区得到本周销售明星
	 * @Title: getWeekStar
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月16日 上午9:47:09       
	 * @version: 
	 *
	 * @param regionId   区域Id
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getWeekStar/{regionId}")
	public ResponseDTO getWeekStar(@PathParam("regionId")Integer regionId);
	
 
	/**----------------------------------首页END------------------------------------**/
	
	/**----------------------------------商品列表BEGIN-------------------------------**/
	
	/**
	 * 获得分类关联的品牌
	 * 
	 * @Title: getCategoryBrandList
	 * @Description: TODO
	 * @param categoryId    分类id
	 * @return List<BrandDTO>
	 * @throws
	 */
	@POST
	@GET
	@Path("getBrandListByCategoryId/{categoryId}")
	public ResponseDTO getBrandListByCategoryId(@PathParam("categoryId")Integer categoryId);
	
	/**
	 * 根据分类ID得到对应的下级分类列表
	 * @Title: getCategoryListByCategoryId
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月8日 上午11:58:32       
	 * @version: 
	 *
	 * @param categoryId
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getCategoryListByCategoryId/{categoryId}")
	public ResponseDTO getCategoryListByCategoryId(@PathParam("categoryId")Integer categoryId);
 
	
	/**
	 * 获得分类商品列表
	 * @Title: getCategoryProductList
	 * @Description: TODO
	 * @param pageSize             每页数
	 * @param pageNumber           当前页数
	 * @param cateId               分类id
	 * @param brandId              品牌id
	 * @param moq                  起订量
	 * @param startPrice           价格区间
	 * @param endPrice             价格区间
	 * @param sortColumn           排序列
	 * @param sortDirection        排序方向
	 * @return ResponseDTO
	 *     
	 * @throws
	 */
	@POST
	@GET
	@Path("getCategoryProductList")
	public ResponseDTO getCategoryProductList(GoodsSearchDTO dto);
	
	/**
	 * 根据关键字搜索
	 * @Title: getProductListByQuery
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月16日 下午1:40:47       
	 * @version: 
	 *
	 * @param dto
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getProductListByQuery")
	public ResponseDTO getProductListByQuery(GoodsSearchDTO dto);
	
	/**
	 * 根据关键字搜索得到对应的分类
	 * @Title: getCategoryListByQuery
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月16日 下午1:42:50       
	 * @version: 
	 *
	 * @param keywords
	 * @param regionId
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getCategoryListByQuery/{keywords}/{regionId}")
	public ResponseDTO getCategoryListByQuery(@PathParam("keywords")String keywords,@PathParam("regionId")Integer regionId);
	
	/**
	 * 根据 关键字，地区查询对应的品牌列表
	 * @Title: getBrandListByQuery
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月16日 下午1:48:21       
	 * @version: 
	 *
	 * @param keywords
	 * @param regionId
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getBrandListByQuery/{keywords}/{regionId}")
	public ResponseDTO getBrandListByQuery(@PathParam("keywords")String keywords,@PathParam("regionId")Integer regionId);
	
	/**
	 * 根据品牌集合得到品牌列表
	 * @Title: getBrandListByBrandIds
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年11月6日 下午5:57:42       
	 * @version: 
	 *
	 * @param brandList
	 * @return
	 *
	 */
	@POST
	@Path("getBrandListByBrandIds")
	public ResponseDTO getBrandListByIds(List<Integer> brandList);
	
	/**
	 * 厂家直供
	 * @Title: getManufactorList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月1日 上午10:53:43       
	 * @version: 
	 *
	 * @param regionId  地区ID
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getManufactorList/{regionId}/{pageSize}")
	public ResponseDTO getManufactorList(@PathParam("regionId") Integer regionId,@PathParam("pageSize")Integer pageSize);
	
	/**
	 * 厂家直供下级页面
	 * @Title: getManufactorListNext
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月20日 上午11:00:59       
	 * @version: 
	 *
	 * @param regionId
	 * @param pageSize
	 * @param pageNum
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getManufactorListNext/{regionId}/{categoryId}/{pageSize}/{pageNum}")
	public ResponseDTO getManufactorListNext(@PathParam("regionId") Integer regionId,@PathParam("categoryId")Integer categoryId,@PathParam("pageSize")Integer pageSize,@PathParam("pageNum") Integer pageNum);
	
	
	
	/**
	 * 测土配肥接口
	 * @Title: getSoilDistributionList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月15日 下午2:39:48       
	 * @version: 
	 *
	 * @param categoryId   分类ID
	 * @param regionId     地区Id
	 * @param pageSize     每页显示条数
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getSoilDistributionList/{categoryId}/{regionId}/{pageSize}")
	public ResponseDTO getSoilDistributionList(@PathParam("categoryId")Integer categoryId,@PathParam("regionId") Integer regionId,
			@PathParam("pageSize") Integer pageSize);
	

	/**
	 * 海外购接口
	 * @Title: getOverseasGoodsList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月15日 下午3:36:51       
	 * @version: 
	 *
	 * @param categoryId   分类ID
	 * @param regionId     地区Id
	 * @param pageSize     每页显示条数
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getOverseasGoodsList/{categoryId}/{regionId}/{pageSize}")
	public ResponseDTO getOverseasGoodsList(@PathParam("categoryId")Integer categoryId,@PathParam("regionId") Integer regionId,
			@PathParam("pageSize") Integer pageSize);
	
	
	/**
	 * 得到商品详情页面
	 * @Title: getSkuDetail
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月2日 上午10:39:40       
	 * @version: 
	 *
	 * @param skuId
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getSkuDetail/{skuId}")
	public ResponseDTO getSkuDetail(@PathParam("skuId")Integer skuId) ;
	
	/**
	 * 根据skuId得到对应的销售区域列表
	 * @Title: getSaleRegionListBySkuId
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月22日 下午2:28:02       
	 * @version: 
	 *
	 * @param skuId
	 * @return
	 *
	 */
	@GET
	@POST
	@Path("getSaleRegionListBySkuId/{skuId}")
	public ResponseDTO getSaleRegionListBySkuId(@PathParam("skuId") Integer skuId);
	
	@POST
	@GET
	@Path("getSaleRegionList/{skuId}/{regionId}")
	public ResponseDTO getSaleRegionList(@PathParam("skuId") Integer skuId,@PathParam("regionId") Integer regionId);
	
	/**
	 * 根据商品ID得到对应的商品内容 
	 * @Title: getGoodsContent
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月4日 上午10:01:09       
	 * @version: 
	 *
	 * @param skuId   
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getGoodsContent/{skuId}")
	public ResponseDTO getGoodsContent(@PathParam("skuId") Integer skuId);
	
	/**
	 * 查询商品详情对应的图片
	 * @Title: getImageList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月11日 下午5:52:38       
	 * @version: 
	 *
	 * @param skuId
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getImageList/{skuId}")
	public ResponseDTO getImageList(@PathParam("skuId") Integer skuId);
	
	/**
	 * 浏览记录
	 * @Title: getBrowseRecord
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月21日 下午2:35:04       
	 * @version: 
	 *
	 * @param browseSearchDTO
	 * @return
	 *
	 */
	@POST
	@GET
	@Path("getBrowseRecord")
	public ResponseDTO getBrowseRecord(BrowseSearchDTO browseSearchDTO);
	
	/**
	 * 随机取sku列表数
	 * @Title: getRandomSkuList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月21日 下午2:35:01       
	 * @version: 
	 *
	 * @param pageSize
	 * @return
	 *
	 */
	@GET
	@POST
	@Path("getRandomSkuList/{pageSize}/{regionId}")
	public ResponseDTO getRandomSkuList(@PathParam("pageSize") Integer pageSize,@PathParam("regionId")Integer regionId);
	
	/**---------------------品牌街---------------------------*/
	
	/**
	 * 根据店铺Id查询对应的商品
	 * @Title: getProductListByStoreId
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月27日 下午6:52:40       
	 * @version: 
	 *
	 * @param storeId   店铺Id
	 * @param pageSize  记录条数
	 * @return
	 *
	 */
	@GET
	@POST
	@Path("getProductListByStoreId/{storeId}/{pageSize}/{regionId}")
	public ResponseDTO getProductListByStoreId(@PathParam("storeId")Integer storeId,@PathParam("pageSize")Integer pageSize,@PathParam("regionId")Integer regionId) ;
	
	/**
	 * 店铺所有商品
	 * @Title: getProductListByStore
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月27日 下午8:51:00       
	 * @version: 
	 *
	 * @param searchDto
	 * @return
	 *
	 */
	@GET
	@POST
	@Path("getProductListByStore")
	public ResponseDTO getProductListByStore(StoreGoodsSearchDTO searchDto);
	
	/**
	 * 根据店铺得到对应的分类
	 * @Title: getCategoryListByStore
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月27日 下午9:18:33       
	 * @version: 
	 *
	 * @param storeId
	 * @return
	 *
	 */
	@GET
	@POST
	@Path("getCategoryListByStore/{storeId}")
	public ResponseDTO getCategoryListByStore(@PathParam("storeId")Integer storeId);
	
	@POST
	@Path("getCategoryListByIds")
	public ResponseDTO getCategoryListByIds(List<Integer> categoryIds);
	
	
	/**
	 * 入驻商商品查询
	 * @Title: getMemberGoodsList
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年11月1日 下午2:36:06       
	 * @version: 
	 *
	 * @param memberDTO
	 * @return
	 *
	 */
	@GET
	@POST
	@Path("getMemberGoodsList")
	public ResponseDTO getMemberGoodsList(GoodsMemberDTO memberDTO);
	
}
