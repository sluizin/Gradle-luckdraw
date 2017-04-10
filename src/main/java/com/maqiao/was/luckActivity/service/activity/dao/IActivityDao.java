/**
 * 
 */
package com.maqiao.was.luckActivity.service.activity.dao;

import com.maqiao.was.luckActivity.service.activity.model.Activity;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Repository
@MapperScan
public interface IActivityDao {
	
	public long getCount(@Param("targetid") long targetid);
	public List<Activity> getHistory(Map<String,Object> map);
	public long getMemberNumber(Map<String,Object> map);
	public void save(Activity e);
	public long getActivityCount(Map<String,Object> map);
	public List<Activity> getActivityHistory(Map<String,Object> map);
	
	
	
}
