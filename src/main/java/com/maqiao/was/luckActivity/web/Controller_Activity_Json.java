/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * ajax使用的json  Controller集<br>
 * 会员状态：<br>
 * /luckactivityJson/activityStateJson/7<br>
 * 会员抽完奖状态<br>
 * /luckactivityJson/activityCashJson/7<br>
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Controller/*@RestController*/
@RequestMapping(value = "/luckactivityJson")
public class Controller_Activity_Json extends SiteBaseController {
	@Autowired
	Project luckActivityProject;

	@Autowired
	Static_block_Activity static_block_activity;

	/**
	 * ModelAttribute注解，所有此Controller都要提取项目信息
	 * @param mav ModelAndView
	 * @return ModelAndView
	 */
	@ModelAttribute
	private ModelAndView init(ModelAndView mav) {
		luckActivityProject.init();
		return mav;
	}

	/**
	 * 通过ajax调用，得到会员资格、状态等信息[Json]<br>
	 * /luckactivityJson/activityStateJson/7
	 * @param targetid int
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/activityStateJson/{targetid:[\\d]+}", produces = "application/json;charset=utf-8")
	public String getActivityStateJson(@PathVariable int targetid) {
		AbstractActivityProject aap = luckActivityProject.getActivityProject(targetid);
		ActivityMember acm = getActivityMember(aap);
		return acm.toJson();
	}

	/**
	 * 通过ajax调用，得到会员进行抽奖后的信息，包括会员资格、状态、中奖信息[Json]<br>
	 * /luckactivityJson/activityCashJson/7
	 * @param targetid int
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/activityCashJson/{targetid:[\\d]+}", produces = "application/json;charset=utf-8")
	public String cashActivityJson(@PathVariable int targetid) {
		AbstractActivityProject aap = luckActivityProject.getActivityProject(targetid);
		ActivityMember acm = getActivityMember(aap);
		if (aap == null) return acm.toJson();
		Map<Object, Object> loginMap = super.getLoginUser();
		/* 判断会员资格 */
		if(!aap.role_CheckMember(loginMap, acm))return acm.toJson();;
		aap.role_Cash(loginMap, acm);
		return acm.toJson();
	}
	/**
	 * 得到会员信息，并计次
	 * @param aap AbstractActivityProject
	 * @return ActivityMember
	 */
	ActivityMember getActivityMember(AbstractActivityProject aap) {
		Map<Object, Object> loginMap = super.getLoginUser();
		ActivityMember acm = new ActivityMember();
		if (aap == null) return acm;
		static_block_activity.getActivityMemberState(aap.pac, loginMap, acm);
		static_block_activity.initLuckSortOwnt(aap.pac, acm);
		return acm;
	}
}
