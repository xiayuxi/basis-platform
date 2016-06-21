package com.ync365.seed.admin.quartz;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.ync365.seed.bussiness.modules.promotion.biz.PromotionBiz;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;

/**
 * 终止促销活动定时任务：每10秒执行一次
 * 此定时任务活动查询的开始时间默认设定为系统时间的前一个月时间，
 * 查询的活动列表是从指定的开始时间到系统当前时间，
 * 而终止动作只是针对活动结束即将在10分钟范围内的进行判断，
 * 一旦活动结束时间满足了条件，即会自动执行终止操作。
 * @author lyh
 *
 */
@Component
public class PromotionJob {
	@Autowired
	private PromotionBiz promotionBiz;
	private static final Logger logger = LoggerFactory.getLogger(PromotionJob.class);
	@Scheduled(cron="0/10 * * * * ? ")	
    public void endPromotion() throws ParseException{
        try {
			logger.info("定时任务，终止活动定时任务，参数 时间，{}",new Date());       
			long currentTimeMillis = System.currentTimeMillis();//系统当前时间的毫秒值 
			Date systemTime = new Date(currentTimeMillis);//系统当前时间
			currentTimeMillis -= 10 * 60 * 1000;
			Date systemTimeBef10 = new Date(currentTimeMillis);//当前系统时间的前10分钟的时间
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
			String systemTimeStr = format.format(systemTime);
			Map<String, Object> map = new HashMap<String, Object>();
			//自定义查询开始时间,目前暂设定为系统时间向前推进一个月     
			Calendar calendar = Calendar.getInstance();      
		    calendar.add(Calendar.MONTH, -1);//得到前一个月时间   					
			String queryBeginStr = format.format(calendar.getTime());
			map.put("queryBeginStr", queryBeginStr);        
			map.put("systemTimeStr", systemTimeStr);        
			List<Promotion> promotionList = promotionBiz.selectListByEndTime(map);        
			if(promotionList!=null&&promotionList.size()>0){
				 for (Promotion promotion : promotionList) {								
					Date promotionEndTime = promotion.getEndTime();//活动结束时间               	
		         	//判断活动结束时间是否在当前系统时间的前10分钟的时间范围之后
		         	if(promotionEndTime!=null&&promotionEndTime.after(systemTimeBef10)){             		
		     			if(promotionEndTime.equals(systemTime)||promotionEndTime.before(systemTime)){
		         			if(promotion.getPromotionType()==2){
		                 		promotionBiz.endCouponPromotion(promotion.getPromotionId());//终止红包活动
		                 	}/*else if(promotion.getPromotionType()==6){
		                 		promotionBiz.endSeckillPromotion(promotion.getPromotionId());//终止秒杀活动
		                 	}*/else{
		                 		promotionBiz.endPromotion(promotion.getPromotionId());//终止其他类型的活动
		                 	}    			
		         		}             		             		
		         	}					            	
				} 	
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}      
    }	
}

