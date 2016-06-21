package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.user.dao.SysReceiveGoodsInfoMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysReceiveGoodsInfo;
import com.ync365.seed.utils.Constants;



@Service
public class SysReceiveGoodsInfoBiz {
	@Autowired
	SysReceiveGoodsInfoMapper sysReceiveGoodsInfoMapper;
	
	/**
	 * 查询对象
	 * @param id
	 * @return
	 */
	public SysReceiveGoodsInfo selectByPrimaryKey(Integer id) {
		return sysReceiveGoodsInfoMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(Integer id){
		return sysReceiveGoodsInfoMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 添加信息
	 * 
	 * @param record
	 * @return
	 */
	public int insert(SysReceiveGoodsInfo record){
		//收货地址默认为不设为默认 收货地址   0 禁止
		record.setSysReceiveGoodsIsDefault(Constants.Status.Disabled.v());
		return sysReceiveGoodsInfoMapper.insertSelective(record);
	}
	
	/**
	 * 通过 id 编号修改信息
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(SysReceiveGoodsInfo record){
		return sysReceiveGoodsInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 多功能查询 所有List<SysReceiveGoodsInfo>
	 * 
	 * @param map
	 * @return
	 */
	public List<SysReceiveGoodsInfo> selectPageByMap(Map<String, Object> map){
		return sysReceiveGoodsInfoMapper.selectPageByMap(map);
	}
	
	/**
	 * 条件获取数量
	 * 
	 * @param map
	 * @return
	 */
	public int selectPageCount(Map<String, Object> map){
		return sysReceiveGoodsInfoMapper.selectPageCount(map);
	}

	/**
	 * us 修改默认收货地址   通过 userNum查询所有的收货地址 把原来的设为默认的 修改否    在更新需要 默认的收货地址
	 * @param record
	 * @return
	 */
	@Transactional
	public int updateSysReceiveGoodsIsDefaultById(SysReceiveGoodsInfo record) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userNum", record.getUserNum());
		List<SysReceiveGoodsInfo> list = sysReceiveGoodsInfoMapper.selectPageByMap(map);
		if(null != list && list.size() > 0){
			for(SysReceiveGoodsInfo temp : list){
				
				//Constants.Status. 是否默认 0 否 1 是 
				if((temp.getSysReceiveGoodsIsDefault()) == Constants.Status.Enabled.v()){
					SysReceiveGoodsInfo info = new SysReceiveGoodsInfo();
					info.setSysReceiveGoodsIsDefault(Constants.Status.Disabled.v());
					info.setId(temp.getId());
					sysReceiveGoodsInfoMapper.updateByPrimaryKeySelective(info);
					break;
				}
			}
		}
		record.setSysReceiveGoodsIsDefault(Constants.Status.Enabled.v());
		return sysReceiveGoodsInfoMapper.updateByPrimaryKeySelective(record);
	}
}
