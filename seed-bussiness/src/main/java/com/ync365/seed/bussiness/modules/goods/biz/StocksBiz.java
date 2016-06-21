package com.ync365.seed.bussiness.modules.goods.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.goods.dao.SkuDao;
import com.ync365.seed.bussiness.modules.goods.dao.StocksDao;
import com.ync365.seed.bussiness.modules.goods.dao.StocksOrderDao;
import com.ync365.seed.bussiness.modules.goods.entity.Sku;
import com.ync365.seed.bussiness.modules.goods.entity.Stocks;
import com.ync365.seed.bussiness.modules.goods.entity.StocksOrder;
import com.ync365.seed.utils.Constants;

/**
 * 库存管理
 * 
 * @ClassName: StocksBiz
 * @Description: TODO
 * @author robo
 * @date 2015年9月22日 下午4:06:30
 *
 */
@Service
public class StocksBiz {

	@Autowired
	StocksDao stocksDao ;
	
	@Autowired
	StocksOrderDao stocksOrderDao ;
	
	@Autowired
    SkuDao skuDao;
	
	/***
	 * 插入
	 */
	public int insert(Stocks stocks){
	    return stocksDao.insertSelective(stocks);
	}
	/***
	 * 通过skuId查找库存
	 */
	public Stocks selectStocksBySkuId(Integer skuId){
	    return stocksDao.selectStocksBySkuId(skuId);
	}
	
	/**
	 * 批量修改库存
	 * @Title: stocksEdit
	 * @Description: TODO
	 * @param stockNum       数量 
	 * @param skuIds         sku集合
	 * @param opertorType    操作：1设置为新的库存，2增加库存，3减少库存
	 * @return   int    
	 * @throws
	 */
	public int stocksEdit(Integer stockNum,String skuIds,int opertorType){
		int rtn = 0 ;
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			List<Integer> list = new ArrayList<Integer>();
			String [] ids = skuIds.split(",");
			for(String id:ids){
				list.add(Integer.parseInt(id));
			}
			map.put("skuIds", list);
			map.put("stockNum", stockNum);
			
			if(opertorType ==Constants.StocksOpertor.NEW.getIndex()){
				rtn = stocksDao.updateStocksNew(map);
			}else if(opertorType == Constants.StocksOpertor.ADD.getIndex()){
				rtn = stocksDao.updateStocksAdd(map);
			}else {
				rtn = stocksDao.updateStocksReduce(map);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return rtn ;
	}
	
	
	/**
	 * 锁定库存新增
	 * @Title: lockQyAdd
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月20日 下午3:02:40       
	 * @version: 
	 *
	 * @param list
	 * @return
	 *
	 */
	@Transactional
	public int lockQyAdd(List<Map<String,Object>> list ){
		int update = Constants.StockReduceStatus.UPDATE_FAIL.v() ; 
		if(list != null && list.size() >  0 ){
			
			for(Map<String, Object> map:list){
				
				Integer  skuId = (Integer)map.get("skuId");
				Integer  orderId = (Integer)map.get("orderId");
				Integer num = (Integer)map.get("num");
				
				Map<String,Object> stocksMap = new HashMap<String,Object>();
				stocksMap.put("orderId", orderId);
				stocksMap.put("skuId", skuId);
				stocksMap.put("status", Constants.OrderStatus.NotPay.v());
				//先查询stockOrder表里是否有数据
				StocksOrder stocksOrder =  stocksOrderDao.selectByOrderId(stocksMap);
				if(stocksOrder != null ){
					update = Constants.StockReduceStatus.UPDATE_SUCCESS.v() ; 
					continue ;
				}
		 
				//查询库存表
				Stocks stocks = stocksDao.selectStocksBySkuId(skuId);
				 
				int lockQty = 0 ;
				int frontLockQty = 0 ;
				if(stocks != null ){
					frontLockQty = stocks.getLockQty() ;
					lockQty = frontLockQty+num ;
				}
				
				//库存大于等 于锁定库存才能锁库存
				if(stocks.getStockNum().intValue() >= lockQty ){
				
					stocks.setLockQty(lockQty);
					
					//更新
					update = stocksDao.updateStocksLockQty(stocks);
					
					stocksOrder = new StocksOrder() ;
					stocksOrder.setOrderId(orderId);
					stocksOrder.setSkuId(stocks.getSkuId());
					stocksOrder.setFrontStockNum(frontLockQty);
					stocksOrder.setBackStockNum(lockQty);
					stocksOrder.setStockNum(stocks.getStockNum());
					stocksOrder.setStatus(Constants.OrderStatus.NotPay.v().intValue());
					stocksOrder.setCreateTime(new Date());
					update = stocksOrderDao.insertSelective(stocksOrder);
				}else {
					
					//超出库存
					update = Constants.StockReduceStatus.STOCK_LESS.v() ;;
				}
				
			}
		}
		return update;
	}
	
	
	/**
	 * 锁定库存减
	 * @Title: lockQtyReduction
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月20日 下午3:02:49       
	 * @version: 
	 *
	 * @param list
	 * @return
	 *
	 */
	@Transactional
	public int lockQtyReduction(List<Map<String,Object>> list){
		int update = Constants.StockReduceStatus.UPDATE_FAIL.v()  ;
		if(list != null && list.size() >  0 ){
			
			for(Map<String, Object> map:list){
				
				Integer  skuId = (Integer)map.get("skuId");
				Integer  orderId = (Integer)map.get("orderId");
				Integer num = (Integer)map.get("num");
				
				Map<String,Object> stocksMap = new HashMap<String,Object>();
				stocksMap.put("orderId", orderId);
				stocksMap.put("skuId", skuId);
				
				//先查询stockOrder表里是否有数据
				StocksOrder stocksOrder =  stocksOrderDao.selectByOrderId(stocksMap);
				if(stocksOrder != null && stocksOrder.getStatus().intValue() == Constants.OrderStatus.Cancled.v().intValue() ){
					update = 1 ;
					continue ;
				}

				//查询库存表
				Stocks stocks = stocksDao.selectStocksBySkuId(skuId);
				 
				int lockQty = stocks.getLockQty() - num ;
				if(lockQty  >= 0 ){
					stocks.setLockQty(lockQty);
					//更新
					update = stocksDao.updateStocksLockQty(stocks);
				}else{
					update = Constants.StockReduceStatus.STOCK_LESS.v() ;
					break;
				}
				
				stocksOrder.setStatus( Constants.OrderStatus.Cancled.v().intValue() );
				update = stocksOrderDao.updateByPrimaryKeySelective(stocksOrder);
	
			}
		}
		return update;
	}
	
	/**
	 * 减库存
	 * @Title: stockNumReduction
	 * @Description: TODO    ：    
	 * @author: robo   
	 * @date: 2015年10月20日 下午3:03:37       
	 * @version: 
	 *
	 * @param list
	 * @return
	 *
	 */
	public int stockNumByOrder(List<Map<String,Object>> list){
		int update = Constants.StockReduceStatus.UPDATE_FAIL.v() ;
		if(list != null && list.size() >  0 ){
			
			try{
			
				for(Map<String, Object> map:list){
					
					Integer  skuId = (Integer)map.get("skuId");
					Integer  orderId = (Integer)map.get("orderId");
					Integer num = (Integer)map.get("num");
					
					Map<String,Object> stocksMap = new HashMap<String,Object>();
					stocksMap.put("orderId", orderId);
					stocksMap.put("skuId", skuId);
					 
					//先查询stockOrder表里是否有数据
					StocksOrder stocksOrder =  stocksOrderDao.selectByOrderId(stocksMap);
					if(stocksOrder != null && stocksOrder.getStatus().intValue() == Constants.OrderStatus.Paid.v().intValue() ){
						update =  Constants.StockReduceStatus.UPDATE_SUCCESS.v()  ;
						continue ;
					}
			 
					//查询库存表
					Stocks stocks = stocksDao.selectStocksBySkuId(skuId);
	 
					int lockQty = stocks.getLockQty() - num ;
					
					//减去后的库存
					int stockNum = stocks.getStockNum() - num ;
					if(lockQty  >= 0 && stockNum >=  0 ){
						stocks.setLockQty(lockQty);
						stocks.setStockNum(stockNum);
						
						//库存减少，锁定库存减少
						update = stocksDao.updateStocksNumByOrder(stocks);
						
						//更新订单库存状态
						stocksOrder.setStatus( Constants.OrderStatus.Paid.v().intValue() );
						stocksOrder.setIsSuccess(update);
						update = stocksOrderDao.updateByPrimaryKeySelective(stocksOrder);
						
						//更新已售出数量 
						Sku sku = skuDao.selectByPrimaryKey(skuId);
						Long salesCount = sku.getSalesCount() +num ;
						sku.setSalesCount(salesCount);
						update = skuDao.updateByPrimaryKey(sku);
	  
					}else{
						
						//库存不足
						update = Constants.StockReduceStatus.STOCK_LESS.v()  ; ;
						break;
					}
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
				for(Map<String, Object> map:list){
					
					Integer  skuId = (Integer)map.get("skuId");
					Integer  orderId = (Integer)map.get("orderId");

					Map<String,Object> stocksMap = new HashMap<String,Object>();
					stocksMap.put("orderId", orderId);
					stocksMap.put("skuId", skuId);
					 
					//先查询stockOrder表里是否有数据
					StocksOrder stocksOrder =  stocksOrderDao.selectByOrderId(stocksMap);
					if(stocksOrder != null ) {
						//更新库存与订单表对应的失败状态
						stocksOrder.setIsSuccess(Constants.StockReduceStatus.UPDATE_FAIL.v()  );
						update = stocksOrderDao.updateByPrimaryKeySelective(stocksOrder);
					}
				}
			}
		}
		return update;
	}
	
	
	
	
	
}
