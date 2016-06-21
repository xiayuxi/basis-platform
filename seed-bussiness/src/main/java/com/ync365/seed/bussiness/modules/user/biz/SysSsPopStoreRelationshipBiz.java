package com.ync365.seed.bussiness.modules.user.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.user.dao.SysSsPopStoreRelationshipMapper;
import com.ync365.seed.bussiness.modules.user.entity.SysSsPopStoreRelationship;

@Service
public class SysSsPopStoreRelationshipBiz {
	
	@Autowired
	private SysSsPopStoreRelationshipMapper relationshipMapper;
	
	public SysSsPopStoreRelationship selectSsRelationByUserNumAndPopStoreNum(String userNum, String popStoreNum){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ssNum", userNum);
		map.put("popStoreNum", popStoreNum);
		List<SysSsPopStoreRelationship> list = relationshipMapper.selectPageByMap(map);
		return list != null ? list.get(0) : new SysSsPopStoreRelationship() ;
	}
	
}
