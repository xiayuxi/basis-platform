package com.ync365.seed.bussiness.modules.goods.dao;

import java.util.List;
import java.util.Map;

import com.ync365.seed.bussiness.modules.goods.entity.PicInfo;
import com.ync365.seed.commons.annotation.MyBatisRepository;
@MyBatisRepository
public interface PicInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PicInfo record);

    int insertSelective(PicInfo record);

    PicInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PicInfo record);

    int updateByPrimaryKey(PicInfo record);
    
    List<PicInfo> selectPicInfoListByUuid(Map<String,Object> map);
    
    List<PicInfo> selectByPicInfo(PicInfo picInfo);
    
    PicInfo selectPicInfoByPicName(Map<String ,Object> map);
    
    /**
     * 查询商品详情页面的对应的图片列表
     * @Title: selectPicInfoByUuid
     * @Description: TODO    ：    
     * @author: robo   
     * @date: 2015年10月11日 下午6:19:05       
     * @version: 
     *
     * @param uuid
     * @return
     *
     */
    List<PicInfo> selectPicInfoByUuid(Map<String, Object> map);
}