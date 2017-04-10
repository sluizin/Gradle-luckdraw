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
import org.springframework.web.servlet.ModelAndView;


/**
 * 页面Controller集，主要是抽奖项目的所有控制器<br>
 * 项目1：<br>
 * /luckactivity/vibrationluck/<br>
 * 项目2：<br>
 * /luckactivity/registrationaward/siteid/11401<br>
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Controller
@RequestMapping("/luckactivity")
public class Controller_Activity_Project extends SiteBaseController {
	@Autowired
	Project luckActivityProject;
	@Autowired
	Controller_Activity_Json specialLuckActivity;

	/**
	 * ModelAttribute注解
	 * @param mav ModelAndView
	 * @return ModelAndView
	 */
	@ModelAttribute
	private ModelAndView init(ModelAndView mav) {
		luckActivityProject.init();
		UtilsModelAndView.putModelAndView(mav);
		return mav;
	}

	/**
	 * 引导/XXXXX/AAAAA/
	 * @param project String
	 * @param mav ModelAndView
	 * @return ModelAndView
	 */
	@RequestMapping({ "/{project}/" })
	public ModelAndView Controller_Project(@PathVariable String project, ModelAndView mav) {
		return putControllerProject(project, "index", mav, 1);
	}

	/**
	 * 引导/XXXXX/AAAAA/zzzz.html<br>
	 * 注意："/dpt/{project}/{file}.html" 项没有 show_Paradim方法 <br>
	 * @param project String
	 * @param file String
	 * @param mav ModelAndView
	 * @return ModelAndView
	 */
	@RequestMapping({ "/{project}/{file}.html" })
	public ModelAndView Controller_Project_file(@PathVariable String project, @PathVariable String file, ModelAndView mav) {
		return putControllerProject(project, file, mav, 1);
	}

	/**
	 * 引导/XXXXX/AAAAA/siteid/6
	 * @param project String
	 * @param siteid Integer
	 * @param mav ModelAndView
	 * @return ModelAndView
	 */
	@RequestMapping({ "/{project}/siteid/{siteid:[\\d]+}" })
	public ModelAndView Controller_Project_Siteid(@PathVariable String project, @PathVariable Integer siteid, ModelAndView mav) {
		return putControllerProject(project, "index", mav, siteid);
	}

	/**
	 * 所有页面控制都汇总到这里
	 * @param project String
	 * @param file String
	 * @param mav ModelAndView
	 * @param siteid Integer
	 * @return ModelAndView
	 */
	private ModelAndView putControllerProject(String project, String file, ModelAndView mav, Integer siteid) {
		int targetid = Project.searchMapTargetid(project);
		if (targetid == -1) return mav;
		if (siteid > -1) mav.getModel().put("sj_activityFromSiteid", siteid);
		AbstractActivityProject aap = luckActivityProject.getActivityProject(targetid);
		if (aap == null) return mav;
		ActivityMember amc = specialLuckActivity.getActivityMember(aap);
		mav.getModel().put("sj_activityMemberOwn", amc);
		putParadigmModel(aap, amc, mav, project, file);
		return mav;
	}

	/**
	 * 泛型通用Model注入
	 * @param aap AbstractActivityProject
	 * @param amc ActivityMember
	 * @param mav ModelAndView
	 * @param project String
	 * @param file String
	 */
	private final void putParadigmModel(AbstractActivityProject aap, ActivityMember amc, ModelAndView mav, final String project, String file) {
		putMember(aap, mav);
		mav.getModel().put("sj_sourcePath", LuckActivityConsts.ENV.getSj_sourceBasicPath() + "/" + project);
		putSystem(mav);
		String viewNamePath = LuckActivityConsts.ACC_SOURCEHTTPPATH + project + "/" + file;
		aap.ExtendModelAndView(mav, this.getLoginUser(), amc);
		mav.setViewName(viewNamePath);
	}

	/**
	 * 设置系统信息
	 * @param mav ModelAndView
	 */
	private final void putSystem(final ModelAndView mav) {
		/* 在系统里已经设置了 */
		
	}

	/**
	 * 导入会员信息通过接口得到信息编码值
	 * @param aap AbstractActivityProject
	 * @param mav ModelAndView
	 */
	private final void putMember(AbstractActivityProject aap, ModelAndView mav) {
		Map<Object, Object> loginMap = super.getLoginUser();
		/* 先进行判断是否登陆，再依次提取会员信息 */
		if (loginMap == null || loginMap.size() == 0) {
			mav.getModel().put("intLogin", 0);
			mav.getModel().put("isLogin", false);
		} else {
			mav.getModel().put("intLogin", 1);
			mav.getModel().put("isLogin", true);
		}
		if (aap instanceof InterfaceMemberOutput) {
			InterfaceMemberOutput f = (InterfaceMemberOutput) aap;
			state_Member.setMemberOutput(loginMap, mav.getModel(), f);
		}
	}

	@Autowired
	public State_Member state_Member;

}
