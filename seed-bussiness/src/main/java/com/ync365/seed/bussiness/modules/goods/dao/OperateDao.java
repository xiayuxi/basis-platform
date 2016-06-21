package com.ync365.seed.bussiness.modules.goods.dao;

import com.ync365.seed.bussiness.modules.goods.entity.Operate;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface OperateDao {
    int deleteByPrimaryKey(Integer operateId);

    int insert(Operate record);

    int insertSelective(Operate record);

    Operate selectByPrimaryKey(Integer operateId);

    /**
     * 根据商品主键查询记录
     * @Title: selectByGoodsId
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年10月10日 下午5:45:18       
     * @version: 
     *
     * @param goodsId
     * @return
     *
     */
    Operate selectByGoodsId(int goodsId);

    int updateByPrimaryKeySelective(Operate record);
    
    /**
     * 根据商品主键更新记录
     * @Title: updateByGoodsIdSelective
     * @Description: TODO    ：    
     * @author: liugl    
     * @date: 2015年10月10日 下午5:45:40       
     * @version: 
     *
     * @param record
     * @return
     *
     */
    int updateByGoodsIdSelective(Operate record);

    int updateByPrimaryKey(Operate record);

}