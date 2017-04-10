/**
 * 
 */
package com.maqiao.was.luckActivity.service.activity.service;

import java.util.List;
import java.util.Map;

import com.maqiao.was.luckActivity.service.activity.model.Activity;

/**
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public interface IActivityService {
	public long getCount(long targetid);
	public List<Activity> getHistory(Map<String,Object> map);
	public long getMemberNumber(Map<String,Object> map);
	public boolean save(Activity e);
	public long getActivityCount(Map<String,Object> map);
	public List<Activity> getActivityHistory(Map<String,Object> map);
}
