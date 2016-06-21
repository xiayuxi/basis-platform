package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.promotion.dao.CouponMapper;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionCouponMapperManual;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionGoodsSkuMapperManual;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionReduceDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionReduceRegDao;
import com.ync365.seed.bussiness.modules.promotion.entity.Coupon;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionCoupon;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGoodsSku;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReduce;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionReduceReg;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionDownPO;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionReducePO;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionReduceRegPO;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionSearch;
import com.ync365.seed.bussiness.modules.promotion.po.PromotionSkuPO;
import com.ync365.seed.bussiness.modules.promotion.redis.RedisPromotionService;
import com.ync365.seed.commons.mapper.JsonMapper;
import com.ync365.seed.utils.CloneUtils;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.UUIDGenerator;
/**
 *     
 * @Title：PromotionBiz  
 * @Description: 促销活动业务
 * @author: Ken        
 * @date: 2015年9月26日 下午6:22:57      
 * @version     
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PromotionBiz {

	private static final Logger logger = LoggerFactory.getLogger(PromotionBiz.class);

	@Autowired
	private RedisPromotionService redisPromotionService;

	@Autowired
	private PromotionDao promotionDao;

    @Autowired
    private PromotionGoodsSkuMapperManual promotionGoodsSkuMapperManual;

	@Autowired
	private PromotionReduceDao promotionReduceDao;

	@Autowired
	private PromotionReduceRegDao promotionReduceRegDao;
	
	@Autowired
	private PromotionCouponMapperManual promotionCouponMapperManual;
	
	@Autowired
	private CouponMapper couponMapper;

	/**
	 * 
	 * @Title: searchPageByPromotionType
	 * @Description: TODO    ：
	 * @author: ivan
	 * @date: 2015年9月21日 下午4:51:15
	 * @version:
	 *
	 * @param promotionType
	 * @param startIndex
	 * @param pageSize
	 * @return
	 *
	 */
	@Transactional(readOnly = false)
	public List<Promotion> searchPageByPromotionType(int promotionType, int startIndex, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("promotionType", promotionType);
		map.put("pageIndex", startIndex);
		map.put("pageSize", pageSize);
		return promotionDao.searchPageByPromotionType(map);
	}

	/**
	 * 
	 * @Title: searchPageCount
	 * @Description: TODO    ：
	 * @author: ivan
	 * @date: 2015年9月21日 下午4:52:08
	 * @version:
	 *
	 * @param promotionType
	 * @return
	 *
	 */
	@Transactional(readOnly = false)
	public int searchPageCount(int promotionType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("promotionType", promotionType);
		return promotionDao.searchPageCount(map);
	}

	/**
	 * 
	 * @Title: selectById
	 * @Description: 根据活动ID获取活动信息
	 * @author: ivan
	 * @date: 2015年9月21日 下午4:52:14
	 * @version:
	 *
	 * @param id
	 * @return
	 *
	 */
	public Promotion selectById(Integer id) {
		return promotionDao.selectById(id);
	}

	/**
	 * 
	 * @Title: checkStartTimeEarlyEndTime
	 * @Description: 校验活动开始日期是否遭遇活动结束日期
	 * @author: ivan
	 * @date: 2015年9月21日 下午4:52:29
	 * @version:
	 *
	 * @param promotion
	 * @return
	 *
	 */
	public boolean checkStartTimeEarlyEndTime(Promotion promotion) {
		if (promotion != null) {
			return promotion.getStartTime().getTime() < promotion.getEndTime().getTime();
		}
		return false;
	}

	/**
	 * 
	 * @Title: checkEndTimeEarlyNow
	 * @Description: 检查活动启动时时间是否晚于活动结束时间  
	 * @author: Ken    
	 * @date: 2015年9月25日 下午12:09:59       
	 * @version: 
	 *
	 * @param promotion
	 * @return
	 *
	 */
	public Boolean checkEndTimeEarlyNow(Promotion promotion) {
		if (promotion != null) {
			return System.currentTimeMillis() < promotion.getEndTime().getTime();
		}
		return false;
	}
	/**
	 * 验证红包活动合法性
	 * @Title: validPromotionCoupon
	 * @Description: TODO    ：    
	 * @author: ivan    
	 * @date: 2015年10月31日 下午3:13:32       
	 * @version: 
	 *
	 * @param promotion
	 * @param promotionId
	 * @return
	 *
	 */
	public Boolean validPromotionCoupon(Promotion promotion,Integer promotionId,PromotionCoupon promotionCoupon){
	    Boolean result = false;
	    
	    Date curDate = new Date();
	    //活动结束时间(红包有效期)>当前时间
	    if(promotion.getEndTime().compareTo(curDate)!=1) {
	        return result;
	    }
	    //红包领取结束时间>当前时间
	    if(promotionCoupon.getGrantEndTime().compareTo(curDate)!=1) {
	        return result;
	    }
	    //活动结束时间(红包有效期)>红包领取结束时间
	    if(promotion.getEndTime().compareTo(promotionCoupon.getGrantEndTime())!=1) {
	        return result;
	    }
	    return true;
	}
	/**
	 * 
	 * @Title: validPromotion
	 * @Description: 活动合法性判断    
	 * @author: Ken    
	 * @date: 2015年10月14日 下午2:40:27       
	 * @version: 
	 *
	 * @param promotion
	 * @param promotionId
	 * @return
	 *
	 */
	public Boolean validPromotion(Promotion promotion,Integer promotionId){
	    Boolean result = false;
	    
	    if (promotion == null) {
            logger.error("活动，启动，失败，ID，{}，原因，{}", promotionId, "不存在");
            return result;
        }

        if (promotion.getStatus() == Constants.PromotionStatus.Begin.v()) {
            logger.error("活动，启动，失败，ID，{}，原因，{}", promotionId, "已启动");
            return result;
        }
        
        if(promotion.getIsDelete() == Constants.PromotionIsDelete.Disabled.v()){
            logger.error("活动，启动，失败，ID，{}，原因，{}", promotionId, "活动已经删除");
            return result;
        }

        if (!checkStartTimeEarlyEndTime(promotion)) {
            logger.error("活动，启动，失败，ID，{}，原因，{}", promotionId, "结束时间早于开始时间");
            return result;
        }

        if (!checkEndTimeEarlyNow(promotion)) {
            logger.error("活动，启动，失败，ID，{}，原因，{}", promotionId, "结束时间早于当前时间");
            return result;
        }
	    return true;
	}
	
	/**
	 * 
	 * @Title: startPromotion
	 * @Description: 启动活动
	 * @author: Ken    
	 * @date: 2015年9月25日 下午12:10:55       
	 * @version: 
	 *
	 * @param promotionId
	 * @return 
	 *
	 */
	public Boolean startPromotion(Integer promotionId) {
		Boolean result = false;
		Promotion promotion = promotionDao.selectByPrimaryKey(promotionId);
		// 判断活动的合法性
		if(!validPromotion(promotion,promotionId)){
		    return result;
		}
		
		// 获取活动商品
		Map<String, Object> searchParam = new HashMap<String, Object>();
		searchParam.put("promotionId", promotionId);
		searchParam.put("isDelete", Constants.PromotionGoodsSkuIsDelete.Enabled.v());
		List<PromotionGoodsSku> goodsSkus = promotionGoodsSkuMapperManual.searchByPromotionId(searchParam);
		if (goodsSkus == null || goodsSkus.size() == 0) {
			logger.error("活动，启动，失败，ID，{}，原因，{}", promotionId, "商品未添加");
			return result;
		}
		
		// 获取具体类型活动
		Integer promotionType = promotion.getPromotionType();
		JsonMapper mapper = new JsonMapper();
		String promotionStr = "";
		if (Constants.PromotionType.Reduce.v() == promotionType) { // 满减
			PromotionReducePO promotionReducePO = getPromotionReducePO(promotion);
			promotionStr = promotionReducePO == null ? "" : mapper.toJson(promotionReducePO);
		} else if (Constants.PromotionType.Down.v() == promotionType) { // 直降
		    PromotionDownPO promotionDownPO = getPromotionDownPO(promotion);
            promotionStr = promotionDownPO == null ? "" : mapper.toJson(promotionDownPO);
		} else if (Constants.PromotionType.Coupon.v() == promotionType) { // 红包
		    // TODO
		} else {
		    logger.error("活动，启动，失败，ID，{}，原因，{}", promotionId, "不存在");
			return result;
		}

		if (StringUtils.isBlank(promotionStr)) {
			logger.error("活动，启动，失败，ID，{}，原因，{}", promotionId, "活动对象不存在");
			return result;
		}
		// redis中加入活动
		redisPromotionService.addPromotionSet(promotion.getPromotionType(), promotionId);
		
		// redis中加入活动详情
		Long expireTime = promotion.getEndTime().getTime();
		redisPromotionService.addPromotionStr(promotionId, promotionStr, expireTime);
		List<Integer> skuIds = new ArrayList<Integer>();
		
		// redis中加入活动商品详情
		for (PromotionGoodsSku goodsSku : goodsSkus) {
			Integer skuId = goodsSku.getSkuId();
			skuIds.add(skuId);
			String goodsSkuStr = mapper.toJson(goodsSku);
			// 增加活动商品详情
			redisPromotionService.addPromotionGoodsSkuStr(promotionId, skuId, goodsSkuStr, expireTime);
		}
		// redis中加入活动商品
		redisPromotionService.addPromotionSkus(promotionId, skuIds);
		
		// 修改数据库活动状态
        promotion.setStatus(Constants.PromotionStatus.Begin.v());
        promotion.setVersion(UUIDGenerator.getUUID());
        promotionDao.updateByPrimaryKeySelective(promotion);
		result = true;
		logger.info("活动，启动，成功，ID，{}", promotionId);
		return result;
	}

	/**
	 * 
	 * @Title: endPromotion
	 * @Description: 停止活动
	 * @author: Ken    
	 * @date: 2015年9月25日 下午4:40:04       
	 * @version: 
	 *
	 * @param promotionId
	 * @return
	 *
	 */
	public Boolean endPromotion(Integer promotionId) {
		Boolean result = false;
		Promotion promotion = promotionDao.selectByPrimaryKey(promotionId);
		if (promotion == null) {
			logger.error("活动，停止，失败，ID，{}，原因，{}", promotionId, "不存在");
			return result;
		}

		if (promotion.getStatus() != Constants.PromotionStatus.Begin.v()) {
			logger.error("活动，停止，失败，ID，{}，原因，{}", promotionId, "未启动");
			return result;
		}

		// 删除Set中活动
		Integer promotionType = promotion.getPromotionType();
		redisPromotionService.delPromotionIdFromSet(promotionType, promotionId);
		// 删除活动内容
		redisPromotionService.delPromotionStr(promotionId);
		// 删除商品内容
		Set<Integer> skuIds = redisPromotionService.getPromotionSkus(promotionId);
		if (skuIds != null && skuIds.size() > 0) {
			for (Integer skuId : skuIds) {
				redisPromotionService.delPromotionSkuStr(promotionId, skuId);
			}
		}
		// 删除Set中活动商品
		redisPromotionService.delPromotionSkus(promotionId);
		// 更新数据库活动状态
		promotion.setStatus(Constants.PromotionStatus.End.v());
		promotion.setVersion(UUIDGenerator.getUUID());
		promotionDao.updateByPrimaryKeySelective(promotion);
		result = true;
		logger.info("活动，停止，成功，活动ID，{}", promotionId);
		return result;
	}

	/**
	 * 
	 * @Title: getPromotionReducePO
	 * @Description: 获取满减活动PO
	 * @author: Ken    
	 * @date: 2015年9月25日 下午2:16:17       
	 * @version: 
	 *
	 * @param promotion
	 * @return
	 *
	 */
	public PromotionReducePO getPromotionReducePO(Promotion promotion) {
		PromotionReducePO promotionReducePO = null;
		Integer promotionId = promotion.getPromotionId();
		PromotionReduce promotionReduce = promotionReduceDao.selectByPrimaryKey(promotionId);
		if (promotionReduce == null) {
			logger.error("活动，获取满减规则，失败，ID，{}，原因，{}", promotionId, "规则不存在");
			return null;
		}
		promotionReducePO = new PromotionReducePO();
        CloneUtils.cloneObject(promotion, promotionReducePO);
        CloneUtils.cloneObject(promotionReduce, promotionReducePO);
		if(promotionReduce.getReduceType().byteValue() == Constants.PromotionReduceType.JieTiReduce.v()){ // 阶梯
		    List<PromotionReduceReg> regList = promotionReduceRegDao.selectByPromotionId(promotionId);
	        if(regList == null || regList.size() == 0){
	            logger.error("活动，获取满减规则，失败，ID，{}，原因，{}", promotionId, "阶梯满减规则不存在");
	            return null;
	        }
	        List<PromotionReduceRegPO> reduceRegs = new ArrayList<PromotionReduceRegPO>();
	        for (PromotionReduceReg reg : regList) {
	            PromotionReduceRegPO regPO = new PromotionReduceRegPO();
	            CloneUtils.cloneObject(reg, regPO);
	            reduceRegs.add(regPO);
	        }
	        promotionReducePO.setReduceRegs(reduceRegs);
		}
		
		
		return promotionReducePO;
	}
	
	/**
	 * 
	 * @Title: getPromotionDownPO
	 * @Description: 获取直降活动PO  
	 * @author: Ken    
	 * @date: 2015年10月14日 下午5:28:34       
	 * @version: 
	 *
	 * @param promotion
	 * @return
	 *
	 */
	public PromotionDownPO getPromotionDownPO(Promotion promotion) {
	    PromotionDownPO promotionDownPO = new PromotionDownPO();
        CloneUtils.cloneObject(promotion, promotionDownPO);
        return promotionDownPO;
    }

	
	/**
	 * 
	 * @Title: findPromotionTypeBySkuIds
	 * @Description: 通过skuId查找活动类型    
	 * @author: Ken    
	 * @date: 2015年10月10日 下午2:29:43       
	 * @version: 
	 *
	 * @param skuIds 
	 * @return
	 *
	 */
	public Map<Integer,Integer> findSkuIdBeyondPromotionType(List <Integer> skuIds){
	    Map <Integer,Integer> result = new HashMap<Integer,Integer>();
	    if(skuIds == null || skuIds.size() == 0){
	        return result;
	    }
	    Map <Integer,List<PromotionOnLinePO>> promMap = this.getOnlinePromotion();
	    if(promMap == null || promMap.size() == 0){
	        return result;
	    }
	    
	    Set <Integer> set = new HashSet<Integer>();
	    set.addAll(skuIds);
	    
	    // 满减
	    List<PromotionOnLinePO> reduceList = promMap.get(Constants.PromotionType.Reduce.v());
	    if(reduceList != null && reduceList.size() > 0){
	        for(PromotionOnLinePO reduceOnLinePO : reduceList){
	            Set<Integer> retainSet = new HashSet<Integer>();
	            retainSet.addAll(skuIds);
	            Set<Integer> promSkuId = reduceOnLinePO.getSkuMap().keySet();
	            retainSet.retainAll(promSkuId);
	            if(retainSet.size() > 0){
	                for(Integer skuId : retainSet){
	                    result.put(skuId, Constants.PromotionType.Reduce.v());
	                    //skuIds.remove(skuId);
	                }
	            }
	        }
	    }
	    
	    // 直降
	    List<PromotionOnLinePO> downList = promMap.get(Constants.PromotionType.Down.v());
        if(downList != null && downList.size() > 0){
            for(PromotionOnLinePO downOnLinePO : downList){
                Set<Integer> retainSet = new HashSet<Integer>();
                retainSet.addAll(skuIds);
                Set<Integer> promSkuId = downOnLinePO.getSkuMap().keySet();
                retainSet.retainAll(promSkuId);
                if(retainSet.size() > 0){
                    for(Integer skuId : retainSet){
                        result.put(skuId, Constants.PromotionType.Down.v());
                        //skuIds.remove(skuId);
                    }
                }
            }
        }
	    return result;
	}
	
	
	/**
	 * 
	 * @Title: findPromotionBySkuId
	 * @Description: 查看指定商品是否存在活动  
	 * @author: Ken    
	 * @date: 2015年11月4日 下午7:45:15       
	 * @version: 
	 *
	 * @param skuId
	 * @return
	 *
	 */
    public PromotionSkuPO findPromotionBySkuId(Integer skuId){
        PromotionSkuPO result = null;
        Map <Integer,List<PromotionOnLinePO>> promMap = this.getOnlinePromotion();
        // 满减
        List<PromotionOnLinePO> reduceList = promMap.get(Constants.PromotionType.Reduce.v());
        if(reduceList != null && reduceList.size() > 0){
            for(PromotionOnLinePO reduceOnLinePO : reduceList){
                Set<Integer> promSkuIds = reduceOnLinePO.getSkuMap().keySet();
                if(promSkuIds.contains(skuId)){
                    result = new PromotionSkuPO();
                    result.setPromotionType(Constants.PromotionType.Reduce.v());
                    result.setPromotionId(reduceOnLinePO.getPromotionPO().getPromotionId());
                    result.setSkuId(skuId);
                    return result;
                }
            }
        }
        
        // 直降
        List<PromotionOnLinePO> downList = promMap.get(Constants.PromotionType.Down.v());
        if(downList != null && downList.size() > 0){
            for(PromotionOnLinePO downOnLinePO : downList){
                Set<Integer> promSkuIds = downOnLinePO.getSkuMap().keySet();
                if(promSkuIds.contains(skuId)){
                    PromotionGoodsSku promotionGoodsSku = this.getPromotionGoodsSku(downOnLinePO.getPromotionPO().getPromotionId(), skuId);                   
                    result = new PromotionSkuPO();
                    result.setPromotionType(Constants.PromotionType.Down.v());
                    result.setPromotionId(downOnLinePO.getPromotionPO().getPromotionId());
                    result.setSkuId(skuId);
                    result.setDiscount(promotionGoodsSku.getDiscount());
                    return result;
                }
            }
        }
        return result;
    }
	
	
	/**
	 * 
	 * @Title: getOnlinePromotion
	 * @Description: 获取线上活动    
	 * @author: Ken    
	 * @date: 2015年10月14日 下午3:47:41       
	 * @version: 
	 *
	 * @return
	 *
	 */
	public Map <Integer,List<PromotionOnLinePO>> getOnlinePromotion(){
	    Map <Integer,List<PromotionOnLinePO>> map = new HashMap<Integer,List<PromotionOnLinePO>>();
	    // 添加在线的满减活动
	    List<PromotionOnLinePO> promotionReduceList = getOnlinePromotionReduceListPO();
	    if(promotionReduceList != null && promotionReduceList.size() > 0){
	        map.put(Constants.PromotionType.Reduce.v(), promotionReduceList);
	    }
	    // 添加在线的直降活动
	    List<PromotionOnLinePO> promotionDownList = getOnlinePromotionDownListPO();
	    if(promotionDownList != null && promotionDownList.size() > 0){
            map.put(Constants.PromotionType.Down.v(), promotionDownList);
        }
	    // 添加在线的红包活动
        return map;
    }
	
	/**
	 * 
	 * @Title: getOnlinePromotionReduceListPO
	 * @Description: 获取当前正在运行的满减活动
	 * @author: Ken    
	 * @date: 2015年10月14日 下午4:58:16       
	 * @version: 
	 *
	 * @return
	 *
	 */
	public List<PromotionOnLinePO> getOnlinePromotionReduceListPO(){
	    List <PromotionOnLinePO> promotionOnLineList = new ArrayList<PromotionOnLinePO>();
	    JsonMapper mapper = new JsonMapper();
	    // 获取满减活动
	    Set<Integer> promotionIds = redisPromotionService.getPromotionSet(Constants.PromotionType.Reduce.v());
        if(promotionIds == null || promotionIds.size() == 0){
            logger.info("活动，获取满减活动，警告，原因，{}","没有活动");
            return promotionOnLineList;
        }
        for(Integer promotionId : promotionIds){
            // 获取活动
            String promotionStr = redisPromotionService.getPromotionStr(promotionId);
            if (StringUtils.isBlank(promotionStr)) {
                logger.error("活动，获取满减活动，失败，ID，{}，原因，{}", promotionId, "活动不存在");
                continue;
            }
            PromotionReducePO promotionPO = mapper.fromJson(promotionStr, PromotionReducePO.class);
            // 判断活动的有效期
            if(System.currentTimeMillis() > promotionPO.getEndTime().getTime() || System.currentTimeMillis() < promotionPO.getStartTime().getTime()){
                continue;
            }
            // 添加活动
            PromotionOnLinePO promotionOnLinePO = new PromotionOnLinePO();
            promotionOnLinePO.setPromotionPO(promotionPO);
            // 添加活动商品
            List<PromotionGoodsSku> skuList = getOnlinePromotionGoodsSkuList(promotionId);
            Set <Integer> skuIdSet = new HashSet<Integer>();
            Map <Integer,PromotionGoodsSku> skuMap = new HashMap<Integer,PromotionGoodsSku>();
            for(PromotionGoodsSku promotionGoodsSku : skuList){
                skuIdSet.add(promotionGoodsSku.getSkuId());
                skuMap.put(promotionGoodsSku.getSkuId(), promotionGoodsSku);
            }
            promotionOnLinePO.setSkuIdSet(skuIdSet);
            promotionOnLinePO.setSkuMap(skuMap);
            promotionOnLineList.add(promotionOnLinePO);
        }
        return promotionOnLineList;
	}
	
	/**
	 * 
	 * @Title: getOnlinePromotionDownListPO
	 * @Description: 获取当前正在运行的满减活动
	 * @author: Ken    
	 * @date: 2015年10月14日 下午5:20:44       
	 * @version: 
	 *
	 * @return
	 *
	 */
	public List<PromotionOnLinePO> getOnlinePromotionDownListPO(){
        List <PromotionOnLinePO> promotionOnLineList = new ArrayList<PromotionOnLinePO>();
        JsonMapper mapper = new JsonMapper();
        // 获取直降活动
        Set<Integer> promotionIds = redisPromotionService.getPromotionSet(Constants.PromotionType.Down.v());
        if(promotionIds == null || promotionIds.size() == 0){
            logger.info("活动，获取直降活动，警告，原因，{}","没有活动");
            return promotionOnLineList;
        }
        for(Integer promotionId : promotionIds){
            // 获取活动
            String promotionStr = redisPromotionService.getPromotionStr(promotionId);
            if (StringUtils.isBlank(promotionStr)) {
                logger.error("活动，获取直降活动，失败，ID，{}，原因，{}", promotionId, "活动不存在");
                continue;
            }
            PromotionDownPO promotionPO = mapper.fromJson(promotionStr, PromotionDownPO.class);
            // 判断活动的有效期
            if(System.currentTimeMillis() > promotionPO.getEndTime().getTime() || System.currentTimeMillis() < promotionPO.getStartTime().getTime()){
                continue;
            }
            // 添加活动
            PromotionOnLinePO promotionOnLinePO = new PromotionOnLinePO();
            promotionOnLinePO.setPromotionPO(promotionPO);
            // 添加活动商品
            List<PromotionGoodsSku> skuList = getOnlinePromotionGoodsSkuList(promotionId);
            Set <Integer> skuIdSet = new HashSet<Integer>();
            Map <Integer,PromotionGoodsSku> skuMap = new HashMap<Integer,PromotionGoodsSku>();
            for(PromotionGoodsSku promotionGoodsSku : skuList){
                skuIdSet.add(promotionGoodsSku.getSkuId());
                skuMap.put(promotionGoodsSku.getSkuId(), promotionGoodsSku);
            }
            promotionOnLinePO.setSkuIdSet(skuIdSet);
            promotionOnLinePO.setSkuMap(skuMap);
            promotionOnLineList.add(promotionOnLinePO);
        }
        return promotionOnLineList;
    }
	
	/**
	 * 
	 * @Title: getOnlinePromotionGoodsSkuList
	 * @Description: 获取活动商品   
	 * @author: Ken    
	 * @date: 2015年10月14日 下午4:53:29       
	 * @version: 
	 *
	 * @param promotionId
	 * @return
	 *
	 */
    public List<PromotionGoodsSku> getOnlinePromotionGoodsSkuList(Integer promotionId){
        List<PromotionGoodsSku> skuList = new ArrayList<PromotionGoodsSku>();
        // 添加活动商品
        JsonMapper mapper = new JsonMapper();
        Set<Integer> promotionSkuIds = redisPromotionService.getPromotionSkus(promotionId);
        
        for(Integer skuId : promotionSkuIds){
            String promotionSkuStr = redisPromotionService.getPromotionGoodsSkuStr(promotionId, skuId);
            PromotionGoodsSku promotionSku = mapper.fromJson(promotionSkuStr, PromotionGoodsSku.class);
            skuList.add(promotionSku);
        }
        return skuList;
    }
    
    /**
     * 
     * @Title: getPromotionGoodsSku
     * @Description: 查找指定活动商品    
     * @author: Ken    
     * @date: 2015年10月19日 下午7:41:00       
     * @version: 
     *
     * @param promotionId
     * @param skuId
     * @return
     *
     */
    public PromotionGoodsSku getPromotionGoodsSku(Integer promotionId,Integer skuId){
        JsonMapper mapper = new JsonMapper();
        String jsonString = redisPromotionService.getPromotionGoodsSkuStr(promotionId, skuId);
        PromotionGoodsSku promotionGoodsSku = mapper.fromJson(jsonString, PromotionGoodsSku.class);
        return promotionGoodsSku;
    }

    
    /**
     * 
     * @Title: search
     * @Description: 多条件、分页查询直降活动     
     * @author: wangxt    
     * @date: 2015年10月14日 下午5:00:49       
     * @version: 
     * @param pageSize 
     * @param startIndex 
     *
     * @promotionType:活动类型
     * @key:活动名称 查询关键字
     * @status:活动状态 ，默认为-1:不限
     * @startTime:活动查询起始时间
     * @endTime:活动查询结束时间
     * @isDel:是否删除，默认为false:未删除
     * 
     * @pageIndex:当前页码，从1开始 
     * @pageSize:每页显示记录数
     * @sort:排序字段 
     * @order:asc/desc
     * @startIndex:查询起始行(计算值，无需赋值)
     *
     */
	public List<Promotion> search(PromotionSearch search, int startIndex, int pageSize) {
		// 查询
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startIndex", startIndex);
	    map.put("pageSize", pageSize);	
	    map.put("search", search);
		List<Promotion> list = promotionDao.getListByMulti(map);		
		return list;
	}
	
	/**
	 * 
	 * @Title: searchCount
	 * @Description: 多条件查询活动总数  wangxt 入参必须和上边search方法一致(可以没有分页参数和排序参数)    
	 * @author: wangxt    
	 * @date: 2015年10月14日 下午5:00:24       
	 * @version: 
	 *
	 * @param search
	 * @return
	 *
	 */
	public int searchCount(PromotionSearch search) {
		// 查询
		int count = promotionDao.getCountByMulti(search);
		return count;
	}
	/**判断活动是否添加商品
	 * @author: lyh
	 * @param id
	 * @return
	 */
	public Boolean checkPromotionGoodsIsAdd(int promotionId) {		
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("promotionId", promotionId);
        map.put("isDelete", Constants.IsDelete.NO.v());
		List<PromotionGoodsSku> list = promotionGoodsSkuMapperManual.searchByPromotionId(map);
		if(list !=null && list.size()>0){//说明已添加了活动商品
			return true;	
		}else{
			return false;
		}			
	}
	/**
	 * 红包活动启动
	 * @Title: startCouponPromotion
	 * @Description: TODO    ：    
	 * @author: ivan    
	 * @date: 2015年10月31日 下午2:45:18       
	 * @version: 
	 *
	 * @param promotionId
	 * @return
	 *
	 */
    @Transactional(readOnly = false)
	public Boolean startCouponPromotion (Integer promotionId) {
	    Boolean result = false;
        Promotion promotion = promotionDao.selectByPrimaryKey(promotionId);
        Map<String,Object> searchPromotionCoupon = new HashMap<String,Object>();
        searchPromotionCoupon.put("promotionId", promotionId);
        PromotionCoupon promotionCoupon = promotionCouponMapperManual.getPromotionCouponByPromotionId(searchPromotionCoupon);
        // 判断活动的合法性
        if(!validPromotion(promotion,promotionId)){
            return result;
        }
        
        // 获取活动商品
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put("promotionId", promotionId);
        searchParam.put("isDelete", Constants.PromotionGoodsSkuIsDelete.Enabled.v());
        List<PromotionGoodsSku> goodsSkus = promotionGoodsSkuMapperManual.searchByPromotionId(searchParam);
        if (goodsSkus == null || goodsSkus.size() == 0) {
            logger.error("活动，启动，失败，ID，{}，原因，{}", promotionId, "商品未添加");
            return result;
        }
        //校验活动时间
        if(!validPromotionCoupon(promotion,promotionId,promotionCoupon)){
            return result;
        }
        //在红包表中插入信息
        Coupon coupon = new Coupon();
        coupon.setCouponAmount(promotionCoupon.getCouponAmount());
        coupon.setDescription(promotion.getRemark());
        coupon.setPromotionId(promotionId);
        coupon.setValidEnd(promotion.getEndTime());
        coupon.setValidStart(promotion.getCreateTime());
        couponMapper.insert(coupon);
       
        // 修改数据库活动状态
        promotion.setStatus(Constants.PromotionStatus.Begin.v());
        promotion.setVersion(UUIDGenerator.getUUID());
        promotionDao.updateByPrimaryKeySelective(promotion);
        result = true;
        logger.info("活动，启动，成功，ID，{}", promotionId);
        return result;
	}
    @Transactional(readOnly = false)
	public Boolean endCouponPromotion(Integer promotionId) {
	    // 修改数据库活动状态
        logger.info("活动红包，停止，ID，{}", promotionId);
	    Boolean result = false;
	    Promotion promotion = promotionDao.selectByPrimaryKey(promotionId);
        promotion.setStatus(Constants.PromotionStatus.End.v());
        promotion.setVersion(UUIDGenerator.getUUID());
        promotionDao.updateByPrimaryKeySelective(promotion);
        result = true;
        return result;
	}
    /**
     * 查询活动结束时间小于系统当前时间并且在指定时间范围内的活动列表
     * @param map
     * @return
     */
	public List<Promotion> selectListByEndTime(Map<String, Object> map) {		
		return promotionDao.selectListByEndTime(map);
	}
	
}