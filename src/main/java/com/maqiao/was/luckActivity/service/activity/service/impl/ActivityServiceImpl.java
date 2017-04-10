/**
 * 
 */
package com.maqiao.was.luckActivity.service.activity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maqiao.was.luckActivity.service.activity.dao.IActivityDao;
import com.maqiao.was.luckActivity.service.activity.model.Activity;
import com.maqiao.was.luckActivity.service.activity.service.IActivityService;
import com.maqiao.was.luckActivity.web.LuckActivityLogger;

/**
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Service("activityService")
public class ActivityServiceImpl implements IActivityService{
	@Autowired
	IActivityDao iActivityDao;

	/* (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.service.activity.service.IActivityService#getCount(long)
	 */
	@Override
	public long getCount(long targetid) {
		try {
			return iActivityDao.getCount(targetid);
		} catch (Exception e) {
			LuckActivityLogger.logger.error("得到某个项目中的总数失败", e);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.service.activity.service.IActivityService#getHistory(java.util.Map)
	 */
	@Override
	public List<Activity> getHistory(Map<String, Object> map) {
		try {
			return iActivityDao.getHistory(map);
		} catch (Exception e) {
			LuckActivityLogger.logger.error("得到某个项目的指定数量的记录失败", e);
		}
		return new ArrayList<Activity>();
	}

	/* (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.service.activity.service.IActivityService#getMemberNumber(java.util.Map)
	 */
	@Override
	public long getMemberNumber(Map<String, Object> map) {
		try {
			return iActivityDao.getMemberNumber(map);
		} catch (Exception e) {
			LuckActivityLogger.logger.error("得到某个项目中的某个会员的总数失败", e);
		}
		return 0;
	}
	//@Autowired
	//private SequenceUtil sequenceUtil;

	/* (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.service.activity.service.IActivityService#save(com.maqiao.was.luckActivity.service.activity.model.Activity)
	 */
	@Override
	public boolean save(Activity e) {
		try {
			long  id=0;//sequenceUtil.getSeqNextVal("seq_special_activity");
			e.setId(id);
			iActivityDao.save(e);
			return true;
		} catch (Exception f) {
			LuckActivityLogger.logger.error("保存记录失败", f);
		}
		return false;		
	}

	/* (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.service.activity.service.IActivityService#getActivityCount(java.util.Map)
	 */
	@Override
	public long getActivityCount(Map<String, Object> map) {
		try {
			return iActivityDao.getActivityCount(map);
		} catch (Exception e) {
			LuckActivityLogger.logger.error("得到会员的总数失败", e);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.service.activity.service.IActivityService#getActivityHistory(java.util.Map)
	 */
	@Override
	public List<Activity> getActivityHistory(Map<String, Object> map) {
		try {
			return iActivityDao.getActivityHistory(map);
		} catch (Exception e) {
			LuckActivityLogger.logger.error("得到数量的记录失败", e);
		}
		return new ArrayList<Activity>();
	}
}
