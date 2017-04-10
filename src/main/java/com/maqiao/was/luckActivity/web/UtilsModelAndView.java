/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import org.springframework.web.servlet.ModelAndView;

/**
 * 对ModelAndView进行操作
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class UtilsModelAndView {

	/**
	 * 设置 ModelAndView 信息 包括 系统信息
	 * @param mav ModelAndView
	 * @return ModelAndView
	 */
	static final ModelAndView putModelAndView(final ModelAndView mav) {
		putSystem(mav);
		return mav;
	}

	/**
	 * 设置系统信息[环境]
	 * @param mav ModelAndView
	 * @return ModelAndView
	 */
	private static final ModelAndView putSystem(final ModelAndView mav) {
		mav.getModel().put("env", LuckActivityConsts.ENV);
		return mav;
	}
}
