/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.maqiao.was.luckActivity.service.activity.model.Activity;

/**
 * 通用中奖信息的查询 Controller<br>
 * <br>
 * 得到项目列表[某个会员] 需要template参数<br>
 * /luckactivityHistroy/getActivityOwnHtmlHistroy/7<br>
 * <br>
 * 得到项目列表 需要template参数<br>
 * /luckactivityHistroy/getActivityHtmlHistroy/7<br>
 * <br>
 * 使用分页显示某个抽奖项目的结果<br>
 * /luckactivityHistroy/getActivityHistroyResult/7/10/1<br>
 * <br>
 * <br>
 * <br>
 * <br>
 * <br>
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Controller
@RequestMapping("/luckactivityHistroy")
public class Controller_Activity_Histroy extends SiteBaseController {
	@Autowired
	Project projectLuckActivity;
	@Autowired
	HttpServletRequest request;
	@Autowired
	State_Member state_Member;

	@Autowired
	Static_block_Activity static_block_activity;

	/**
	 * 初始化
	 */
	@ModelAttribute
	private void init() {
		projectLuckActivity.init();
	}

	/**
	 * 得到项目列表 需要template参数
	 * /luckactivityWork/getActivityHtmlHistroy/7
	 * @param targetid int
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/getActivityHtmlHistroy/{targetid:[\\d]+}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getActivityHtmlHistroy(@PathVariable int targetid, @RequestParam(value = "template") String template) {
		AbstractActivityProject f = Project.getAbstractActivityProject(targetid);
		if (f == null) return "No items found";
		if (template == null || template.trim().length() == 0) return "No template found";
		List<Activity> prizedList = static_block_activity.getActivityListAAP(f);
		return UtilsHtml.ListToTemplate(prizedList, template);
	}

	/**
	 * 得到项目列表自己中奖记录 需要template参数
	 * /luckactivityWork/getActivityHtmlHistroy/7
	 * @param targetid int
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/getActivityOwnHtmlHistroy/{targetid:[\\d]+}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getActivityOwnHtmlHistroy(@PathVariable int targetid, @RequestParam(value = "template") String template) {
		AbstractActivityProject f = Project.getAbstractActivityProject(targetid);
		long member_id = state_Member.getMemberId(super.getLoginUser());
		if (member_id == 0) return "";
		List<Activity> prizedList = static_block_activity.getActivityListByMember_id(f, member_id);
		return UtilsHtml.ListToTemplate(prizedList, template);
	}

	/**
	 * 得到某个项目的信息，以及中奖记录，使用 /luckactivityWork/getActivityHistroyResult/7/10/1<br>
	 * 提示：targetid:[\\d]+}/{pagesize:[\\d]+}/{pageid:[\\d]+}
	 * @param targetid int
	 * @param pagesize long
	 * @param pageid long
	 * @return String
	 */
	@RequestMapping(value = "/getActivityHistroyResult/{targetid:[\\d]+}/{pagesize:[\\d]+}/{pageid:[\\d]+}")
	public ModelAndView getActivityHistroyResult(@PathVariable int targetid, @PathVariable long pagesize, @PathVariable long pageid, ModelAndView mav) {
		int min = (int) ((pageid - 1) * pagesize);
		int max = (int) (pageid * pagesize - 1);
		AbstractActivityProject f = Project.getAbstractActivityProject(targetid);
		mav.getModel().put("env", LuckActivityConsts.ENV);
		if (f == null) return mav;
		List<Activity> prizedList = static_block_activity.getActivityList(f, 1000, 1);
		mav.getModel().put("projectF", f);
		List<Activity> prizedListNew = new ArrayList<Activity>();
		int len = prizedList.size();
		if (max >= len) max = len - 1;
		if (min <= max) prizedListNew.addAll(prizedList.subList(min, max));
		/*
		 * for (int i = 0; i < len; i++) {
		 * if ((i + 1) > max) break;
		 * if ((i + 1) >= min) prizedListNew.add(prizedList.get(i));
		 * }
		 */
		mav.getModel().put("prizedListNew", prizedListNew);
		String viewNamePath = LuckActivityConsts.ACC_SOURCEHTTPPATH + "project/histroyResult";
		mav.setViewName(viewNamePath);
		return mav;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String defaultExceptionHandler(Exception exception) {
		return "exception";
	}
}
