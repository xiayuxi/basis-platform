package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysUserAttentionGoodsMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysUserAttentionGoods;

@Service
public class SysUserAttentionGoodsBiz {
    @Autowired
    SysUserAttentionGoodsMapper sysUserAttentionGoodsMapper;

    /**
     * 添加信息
     * 
     * @param record
     * @return
     */
    public int insert(SysUserAttentionGoods record) {
        return sysUserAttentionGoodsMapper.insertSelective(record);
    }

    /**
     * 物理  通过 userNum删除数据
     * 
     * @param userNum
     * @return
     */
    public int deleteByUserNumm(String userNum) {
        return sysUserAttentionGoodsMapper.deleteByUserNumm(userNum);
    }

    /**
     * 通过map 查询 List<SysUserAttentionBrand>
     * @param map
     * @return
     */
    public List<SysUserAttentionGoods> selectPageByMap(Map<String, Object> map) {
        return sysUserAttentionGoodsMapper.selectPageByMap(map);
    }

    /**查询当前用户下收藏的产品
     * @author xieang
     * 2015年9月28日
     * @param userNum
     * @return
     */
    public List<SysUserAttentionGoods> selectPageByMap(String userNum) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        //TODO 查询出来id  查询goods详情没有
        return sysUserAttentionGoodsMapper.selectPageByMap(map);
    }

    /**
     * 物理  根据条件删除收藏
     * 
     * @param userNum
     * @return
     */
    public int deleteByBean(SysUserAttentionGoods sysUserAttentionGoods) {
        return sysUserAttentionGoodsMapper.deleteByBean(sysUserAttentionGoods);
    }

    public Boolean checkUserAttentionGoods(String userNum, Integer goodsId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userNum", userNum);
        map.put("goodsId", goodsId);
        List<SysUserAttentionGoods> list = sysUserAttentionGoodsMapper.selectPageByMap(map);
        return (list != null && list.size() > 0) ? true : false;
    }
}
