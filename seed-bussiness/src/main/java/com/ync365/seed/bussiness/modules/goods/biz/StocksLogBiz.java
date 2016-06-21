package com.ync365.seed.bussiness.modules.goods.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.bussiness.modules.goods.dao.StocksLogDao;
import com.ync365.seed.bussiness.modules.goods.entity.StocksLog;

@Service
public class StocksLogBiz {
	@Autowired
	private StocksLogDao stocksLogDao;
	
	/***
	 * 插入日志
	 */
	public int add(StocksLog stocksLog){
		return stocksLogDao.insert(stocksLog);
	}
	
	/***
	 * 编辑日志
	 */
	public int edit(StocksLog stocksLog){
		return stocksLogDao.updateByPrimaryKey(stocksLog);
	}
	
	/***
	 * 
	 * 日志列表
	 */
	public  List<StocksLog> selectPage(int startIndex,int pageSize){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pageIndex", startIndex);
		map.put("pageSize", pageSize);
	
		return  stocksLogDao.selectPage(map);
	} 
	
	public int selectPageCount(){
		return stocksLogDao.selectPageCount();
	}
}
