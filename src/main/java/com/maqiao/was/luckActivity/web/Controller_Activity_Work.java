/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通用中奖信息操作功能 work Controller<br>
 * <br>
 * 重置抽奖系统，清空缓存，从json依次读取各个抽奖项目<br>
 * /luckactivityWork/resetAll<br>
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Controller
@RequestMapping("/luckactivityWork")
public class Controller_Activity_Work extends SiteBaseController {
	@Autowired
	Project projectLuckActivity;
	@Autowired
	HttpServletRequest request;
	@Autowired
	State_Member state_Member;

	/**
	 * 初始化
	 */
	@ModelAttribute
	private void init() {
		projectLuckActivity.init();
	}
	/**
	 * 重置项目组，用于运行时修改json配置文件，使用 /luckactivityWork/resetAll
	 * @return boolean
	 */
	@ResponseBody
	@RequestMapping(value = "/resetAll")
	public boolean reset() {
		Project.ACC_projectAllMap.clear();
		projectLuckActivity.init();
		return true;
	}

	@Autowired
	Static_block_Activity static_block_activity;
}
