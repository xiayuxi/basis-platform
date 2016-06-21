package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.entity.Blob;
import com.ync365.seed.commons.annotation.MyBatisRepository;

@MyBatisRepository
public interface BlobDao {
    int deleteByPrimaryKey(Long goodsId);

    int insert(Blob record);

    int insertSelective(Blob record);

    Blob selectByPrimaryKey(Integer goodId);

    int updateByPrimaryKeySelective(Blob record);

    int updateByPrimaryKeyWithBLOBs(Blob record);
    
    List<Blob> selectByMap(Map<String,String> map);
    
    /**
     * 根据skuId得到商品内容详情
     * @Title: selectBlobByskuId
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月4日 上午10:14:29       
     * @version: 
     *
     * @param skuId
     * @return
     *
     */
    Blob selectBlobByskuId(Integer skuId);
}