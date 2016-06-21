package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.promotion.dao.PromotionGiftGoodsDao;
import com.ync365.seed.bussiness.modules.promotion.entity.PromotionGiftGoods;

/**
 * 
 *     
 * @Title：PromotionGiftGoodsBiz  
 * @Description: TODO   
 * @author: sunyf
 * @date: 2015年9月21日 下午5:28:30      
 * @version     
 *
 */
@Service
public class PromotionGiftGoodsBiz {

    private static final Logger log = Logger.getLogger(PromotionGiftGoodsBiz.class);

    @Resource
    private PromotionGiftGoodsDao promotionGiftGoodsDao;

    /**
    * 
    * @Title: find
    * @Description: TODO    ：    
    * @author: sunyf    
    * @date: 2015年9月21日 下午5:20:09       
    * @version: 
    *
    * @param id
    * @return
    *
    */
    public PromotionGiftGoods find(int id) {
        return promotionGiftGoodsDao.find(id);
    }

    /**
     * 
     * @Title: findAll
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年9月21日 下午5:28:06       
     * @version: 
     *
     * @return
     *
     */
    public List<PromotionGiftGoods> findAll() {
        return promotionGiftGoodsDao.findAll();
    }

    /**
     * 
     * @Title: create
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年9月21日 下午5:28:13       
     * @version: 
     *
     * @param po
     * @throws Exception
     *
     */
    public void create(PromotionGiftGoods po) throws Exception {

        if (po != null)
            promotionGiftGoodsDao.create(po);
    }

    /**
     * 
     * @Title: update
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年9月21日 下午5:28:18       
     * @version: 
     *
     * @param po
     * @throws Exception
     *
     */
    public void update(PromotionGiftGoods po) throws Exception {

        if (po != null)
            promotionGiftGoodsDao.update(po);

    }

    /**
     * 
     * @Title: delete
     * @Description: TODO    ：    
     * @author: sunyf    
     * @date: 2015年9月21日 下午5:28:21       
     * @version: 
     *
     * @param id
     * @throws Exception
     *
     */
    public void delete(int id) throws Exception {
        promotionGiftGoodsDao.delete(id);
    }

}