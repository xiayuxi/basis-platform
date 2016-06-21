package com.ync365.seed.bussiness.modules.promotion.biz;

import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.seed.bussiness.modules.promotion.dao.PromotionDao;
import com.ync365.seed.bussiness.modules.promotion.entity.Promotion;
import com.ync365.seed.utils.Constants;
import com.ync365.seed.utils.UUIDGenerator;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PromotionDownBiz {
	/** 日志 */
	private static final Logger log = Logger.getLogger(PromotionDownBiz.class);

	@Autowired
	private PromotionDao promotionDao;

	/** 创建 活动 */
	public int create(Promotion record) {
		int res = -5;
		if (record == null || record.getPromotionName() == null || record.getPromotionName().isEmpty()
				|| record.getStartTime() == null || record.getEndTime() == null
				|| record.getStartTime().after(record.getEndTime())) {
			return res;
		}
		record.setPromotionType(Constants.PromotionType.Down.v());// 活动类型
		record.setStatus(Constants.PromotionStatus.New.v()); // 活动状态
		record.setIsDelete(Constants.PromotionIsDelete.Enabled.v());// 是否删除(默认0不删除)
		record.setVersion(UUIDGenerator.getUUID());
		record.setCreateTime(new Date());
		// record.setCreateUserNum("-1");// TODO wangxt
		record.setUpdateTime(new Date());
		// record.setUpdateUserNum("-1");// TODO wangxt
		try {
			res = promotionDao.insertSelective(record);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("创建直降活动失败，新活动名称：" + record.getPromotionName());
		}
		return res;
	}

	/** 根据活动id，进行逻辑删除 */
	public int deleteById(int id) {
		return promotionDao.delByIdLogic(id);
	}

	/** 修改
	 */
	@Transactional
	public int update(Promotion po) {
		if (po == null || po.getPromotionId() == null || po.getPromotionId() < 1 || po.getPromotionName() == null
				|| po.getPromotionName().trim().isEmpty() || po.getStartTime() == null || po.getEndTime() == null
				|| po.getStartTime().after(po.getEndTime()) || po.getRemark() == null || po.getRemark().trim().isEmpty()
				|| po.getVersion() == null) {
			return -5;// 入参有误
		}

		// 获取数据库中原数据
		Promotion oldPo = promotionDao.selectById(po.getPromotionId());
		if (oldPo.getStatus().intValue() == 1) {
			return -11;// 保存失败:活动已启动
		}
		if (!oldPo.getVersion().equals(po.getVersion())) {
			return -12;// 保存失败:活动信息已被他人修改
		}

		po.setVersion(UUIDGenerator.getUUID());
		po.setUpdateTime(new Date());
		// TODO po.setUpdateUserNum(updateUserNum);

		int res = 0;
		try {
			res = promotionDao.updateByPrimaryKeySelective(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		res = res == 1 ? 1 : -1;
		return res;
	}

	/** 根据活动id，获取活动信息
	 */
	public Promotion getById(int id) {
		return promotionDao.selectById(id);
	}

}