package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysUserAttentionPopStoreMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysUserAttentionGoods;
import com.ync365.seed.bussiness.modules.user.entity.SysUserAttentionPopStore;

@Service
public class SysUserAttentionPopStoreBiz {
    @Autowired
    SysUserAttentionPopStoreMapper SysUserAttentionPopStoreMapper;

    /**
     * 添加对象 insert
     * @param record
     * @return
     */
    public int insert(SysUserAttentionPopStore record) {
        return SysUserAttentionPopStoreMapper.insertSelective(record);
    }

    /**
     * 通过userNum删除数据
     * @param userNum
     * @return
     */
    public int deleteByUserNumm(String userNum) {
        return SysUserAttentionPopStoreMapper.deleteByUserNumm(userNum);
    }

    /**
     * 通过map查询 List<SysUserAttentionPopStore>
     * @param map
     * @return
     */
    public List<SysUserAttentionPopStore> selectPageByMap(Map<String, Object> map) {
        return SysUserAttentionPopStoreMapper.selectPageByMap(map);
    }

    /**
     * 通过userNum查询 List<SysUserAttentionPopStore>
     * @param map
     * @return
     */
    public List<SysUserAttentionPopStore> selectPageByMap(String userNum) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userNum", userNum);
        //TODO 查询出来id  查询商铺详情内容
        return SysUserAttentionPopStoreMapper.selectPageByMap(map);
    }

    /**
     * 物理  根据条件删除收藏
     * 
     * @param userNum
     * @return
     */
    public int deleteByBean(SysUserAttentionPopStore sysUserAttentionPopStore) {
        return SysUserAttentionPopStoreMapper.deleteByBean(sysUserAttentionPopStore);
    }

    public Boolean checkUserAttentionGoods(String userNum, String popStoreNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("userNum", userNum);
        map.put("popStoreNum", popStoreNum);
        List<SysUserAttentionPopStore> list = SysUserAttentionPopStoreMapper.selectPageByMap(map);
        return (list != null && list.size() > 0) ? true : false;
    }
}
