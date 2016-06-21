package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.promotion.dao.PromotionDao;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionGoodsSkuMapper;
import com.ync365.seed.bussiness.modules.promotion.dao.PromotionGoodsSkuMapperManual;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGoodsSku;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.UUIDGenerator;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PromotionGoodsSkuBiz {

    private static final Logger log = Logger.getLogger(PromotionGoodsSkuBiz.class);

    @Autowired
    private PromotionGoodsSkuMapperManual promotionGoodsSkuMapperManual;
    
    @Autowired
    private PromotionGoodsSkuMapper promotionGoodsSkuMapper;

    @Autowired
    private PromotionDao promotionDao;

    /**
     * 
     * @Title: searchByPromotionId
     * @Description: TODO    ：     根据活动ID 分页查询活动商品
     * @author: sunyf    
     * @date: 2015年9月29日 下午3:17:19       
     * @version: 
     *
     * @param promotionId
     * @param isDelete
     * @return
     *
     */
    public List<PromotionGoodsSku> searchByPromotionId(Integer promotionId, Integer isDelete) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("promotionId", promotionId);
        map.put("isDelete", isDelete);
        return promotionGoodsSkuMapperManual.searchByPromotionId(map);
    }

    /**
     * 
     * @Title: searchPageByPromotionId
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年10月8日 下午3:44:26       
     * @version: 
     *
     * @param promotionId
     * @param isDelete
     * @param pageIndex
     * @param pageSize
     * @return
     *
     */
    public List<PromotionGoodsSku> searchPageByPromotionId(Integer promotionId, Integer isDelete, Integer pageIndex,
            Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("promotionId", promotionId);
        map.put("isDelete", isDelete);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        return promotionGoodsSkuMapperManual.searchPageByPromotionId(map);
    }

    /**
     * 
     * @Title: searchPageCount
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年9月26日 下午6:46:55       
     * @version: 
     *
     * @param promotionId
     * @return
     *
     */
    @Transactional(readOnly = false)
    public int searchPageCount(int promotionId, int isDelete) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("promotionId", promotionId);
        map.put("isDelete", isDelete);
        return promotionGoodsSkuMapperManual.searchPageCount(map);
    }

    /**
     * 
     * @Title: updateIsdeleteByPromotionId
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年9月26日 下午7:02:43       
     * @version: 
     *
     * @param promotionId
     * @param proGoodslist
     *
     */
    @Transactional(readOnly = false)
    public void updateIsdeleteByPromotionId(Integer promotionId, List<PromotionGoodsSku> proGoodslist, String version) {
        Map<String, Object> updateMap = new HashMap<String, Object>();
        updateMap.put("promotionId", promotionId);
        updateMap.put("idDelete", Constants.Whether.Yes.v());

        Promotion promotion = promotionDao.selectById(promotionId);
        if (!promotion.getVersion().equals(version)) {
        }
        promotionGoodsSkuMapperManual.updateIsdeleteByPromotionId(updateMap);
        if (proGoodslist.size() > 0) {
            promotionGoodsSkuMapperManual.insertPromotionGoodsSkuBatch(proGoodslist);
        }

    }
    /**
     * 
     * @Title: searchIsPromotionSku
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年10月8日 下午4:12:09       
     * @version: 
     *
     * @return
     *
     */
    @Transactional(readOnly = false)
    public List<String> searchIsPromotionSku() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDelete", 0);
        return promotionGoodsSkuMapperManual.searchIsPromotionSku(map);

    }

    /**
     * 
     * @Title: insertPromotionGood
     * @Description: TODO    ：    插入活动商品
     * @author: sunyf    
     * @date: 2015年10月1日 下午3:40:34       
     * @version: 
     *
     * @param promotionId
     * @param promotionGoodsSku
     * @param version
     * @return
     *
     */
    @Transactional(readOnly = false)
    public String insertPromotionGood(Integer promotionId, PromotionGoodsSku promotionGoodsSku, String version) {
        //跟新活动主键
        Map<String, Object> updateVerMap = new HashMap<String, Object>();
        updateVerMap.put("version", version);
        updateVerMap.put("newVersion", UUIDGenerator.getUUID());
        updateVerMap.put("promotionId", promotionId);
        updateVerMap.put("status", Constants.PromotionStatus.New.v());
        int result = promotionDao.updatePromotionVersion(updateVerMap);
        if (result == 1) {
            Map<String, Object> getGoodMap = new HashMap<String, Object>();
            getGoodMap.put("promotionId", promotionId);
            getGoodMap.put("skuId", promotionGoodsSku.getSkuId());
            PromotionGoodsSku pro = promotionGoodsSkuMapperManual.selectByPromotionIdAndSkuId(getGoodMap);
            //当存在活动商品时更新删除标志位 else插入新的活动商品信息
            if (pro != null && pro.getPromotionGoodsSkuId() != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("promotionGoodsSkuId", pro.getPromotionGoodsSkuId());
                map.put("isDelete", 0);
                map.put("discount", promotionGoodsSku.getDiscount());
                promotionGoodsSkuMapperManual.updatePromotionGoodIsDel(map);
            } else {
                promotionGoodsSkuMapper.insert(promotionGoodsSku);
            }
        }
        return promotionDao.selectById(promotionId).getVersion();

    }

    /**
     * 
     * @Title: updatePromotionGoodIsDel
     * @Description: TODO    ：    根据promotionGoodSkuId 修改记录“删除”状态
     * @author: sunyf    
     * @date: 2015年10月1日 下午3:13:47       
     * @version: 
     *
     * @param promotionId
     * @param promotionGoodsSkuId
     * @param isDelete
     * @param version
     * @return
     *
     */
    @Transactional(readOnly = false)
    public String updatePromotionGoodIsDel(Integer promotionId, Integer promotionGoodsSkuId, Integer isDelete,
            String version) {

        Map<String, Object> updateVerMap = new HashMap<String, Object>();
        updateVerMap.put("version", version);
        updateVerMap.put("newVersion", UUIDGenerator.getUUID());
        updateVerMap.put("promotionId", promotionId);
        updateVerMap.put("status", Constants.PromotionStatus.New.v());

        int result = promotionDao.updatePromotionVersion(updateVerMap);

        if (result == 1) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("promotionGoodsSkuId", promotionGoodsSkuId);
            map.put("isDelete", isDelete);
            promotionGoodsSkuMapperManual.updatePromotionGoodIsDel(map);
        }
        return promotionDao.selectById(promotionId).getVersion();

    }
    /**
     * 
     * @Title: updateHoldGold
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年10月8日 下午4:12:48       
     * @version: 
     *
     * @param promotionGoodsSkuId
     * @param version
     * @param promotionId
     * @param map
     * @return
     *
     */
    @Transactional(readOnly = false)
    public String updateHoldGold(Integer promotionGoodsSkuId, String version, Integer promotionId,
            Map<String, Object> map) {
        Map<String, Object> updateVerMap = new HashMap<String, Object>();
        updateVerMap.put("version", version);
        updateVerMap.put("newVersion", UUIDGenerator.getUUID());
        updateVerMap.put("promotionId", promotionId);
        updateVerMap.put("status", Constants.PromotionStatus.New.v());

        int result = promotionDao.updatePromotionVersion(updateVerMap);

        if (result == 1) {
            promotionGoodsSkuMapperManual.updateHoldGold(map);
        }
        return promotionDao.selectById(promotionId).getVersion();

    }
    /**
     * 
     * @Title: searchIsPromotionSkuByDate
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年10月8日 下午4:13:01       
     * @version: 
     *
     * @param startTime
     * @param endTime
     * @return
     *
     */
    public List<PromotionGoodsSku> searchIsPromotionSkuByDate(Date startTime,Date endTime){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("status", Constants.PromotionStatus.Begin.v());
        map.put("status1", Constants.PromotionStatus.New.v());
        map.put("isDelete", Constants.Whether.No.v());
        return promotionGoodsSkuMapperManual.searchIsPromotionSkuByDate(map);
    }
    
    public List<PromotionGoodsSku> findPromGoodsSkusBySkuIds(Integer promotionId,List <Integer> skuIds){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("promotionId", promotionId);
        param.put("isDelete", Constants.Whether.No.v());
        param.put("skuIds", skuIds);
        return promotionGoodsSkuMapperManual.findPromGoodsSkusBySkuIds(param);
    }
}