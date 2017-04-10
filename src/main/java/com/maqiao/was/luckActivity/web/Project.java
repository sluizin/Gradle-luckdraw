/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 工程项目基本配置，存储位置等
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Component(value = "projectLuckActivity")
public final class Project {
	/** 项目所在路径，用于搜索 */
	static final String[] ACC_PATHARRAY = { "com.maqiao.was.luckActivity.web.luckProject", "", "", "", "" };
	/** 项目文件名头部，以'_'连接 */
	static final String ACC_PROJECTNAMEHEAD = "Project";
	@Autowired
	Static_block_Activity static_block_activity;
	protected State_Member state_Member;
	@Autowired
	private HttpServletRequest request;

	/**
	 * 按项目名称搜索，得到targetid
	 * @param projectName String
	 * @return int
	 */
	static final int searchMapTargetid(String projectName) {
		if (projectName == null || projectName.length() == 0) return -1;
		for (Integer targetid : ACC_projectAllMap.keySet()) {
			AbstractActivityProject e = ACC_projectAllMap.get(targetid);
			if (e.projectName.equals(projectName)) return targetid;
		}
		return -1;
	}

	/**
	 * 按targetid得到AbstractActivityProject
	 * @param targetid int
	 * @return AbstractActivityProject
	 */
	static final AbstractActivityProject getAbstractActivityProject(int targetid) {
		if (targetid == 0) return null;
		return ACC_projectAllMap.get(targetid);
	}

	/** 项目保存位置 */
	static final Map<Integer, AbstractActivityProject> ACC_projectAllMap = new HashMap<Integer, AbstractActivityProject>();

	/**
	 * 得到项目对象
	 * @param targetid int
	 * @return AbstractActivityProject
	 */
	final AbstractActivityProject getActivityProject(int targetid) {
		init();
		return ACC_projectAllMap.get(targetid);
	}

	/**
	 * 如果ACC_projectAllMap为空则更新
	 */
	final void init() {
		if (ACC_projectAllMap.size() == 0) searchProject();
	}

	/**
	 * 搜索项目
	 */
	@Deprecated
	final void searchProjectDeprecated() {
		List<AbstractActivityProject> list = UtilsProject.getAbstractList();
		inputInitProject(list);
	}

	/**
	 * 搜索项目
	 */
	final void searchProject() {
		List<AbstractActivityProject> list = UtilsProject.getAbstractList(ACC_PATHARRAY);
		inputInitProject(list);
	}

	/**
	 * 把项目初始化保存
	 * @param list List&lt;AbstractActivityProject>
	 */
	final void inputInitProject(List<AbstractActivityProject> list) {
		String requestPath = request.getSession().getServletContext().getRealPath("");
		String head = requestPath + LuckActivityConsts.ACC_SOURCEPATH;
		String end = "/" + LuckActivityConsts.ACC_SOURCEFILENAME;
		for (int i = 0, len = list.size(); i < len; i++) {
			AbstractActivityProject e = list.get(i);
			/* 设置静态方法对象 */
			if (e.static_block_activity == null) e.static_block_activity = static_block_activity;
			String jsonPathFile = head + e.projectName + end;
			Static_block_Activity.initialization(e, jsonPathFile);
			static_block_activity.initHistroyListRnd(e);
			LuckActivityLogger.logger.error(e);
			LuckActivityLogger.logger.error(e.toString());
			ACC_projectAllMap.put(e.targetid, e);
		}
	}

}
