/**
 * 
 */
package com.maqiao.was.luckActivity.web.luckProject;

import java.util.Map;
import org.springframework.web.servlet.ModelAndView;
import com.maqiao.was.luckActivity.web.AbstractActivityProject;
import com.maqiao.was.luckActivity.web.ActivityMember;
import com.maqiao.was.luckActivity.web.ProjectActivityItem;
import com.maqiao.was.luckActivity.web.State_Member.Output;

/**
 * [中国振动筛产业网精品抽奖专题] taretid:6 继承{@link AbstractActivityProject}
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public class Project_6_vibrationluck extends AbstractActivityProject {

	/*
	 * Project_6_vibrationluck() {
	 * super(6, "vibrationluck");
	 * }
	 */
	/**
	 * {@linkplain AbstractActivityProject#AbstractActivityProject}<br>
	 * @param targetid int
	 * @param projectName String
	 */
	protected Project_6_vibrationluck(int targetid, String projectName) {
		super(targetid, projectName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.web.AbstractActivityProject#role_Cash(java.util.Map,
	 * com.maqiao.was.luckActivity.web.ActivityMember)
	 */
	@Override
	public void role_Cash(Map<Object, Object> loginMap, ActivityMember amc) {
		/* 随机得到抽奖项目条目 */
		int selectedPoint = protectedGetSelectedPoint(amc);
		/* 如果抽中编号为-1，则抽出的奖项出现了问题 */
		if (selectedPoint == -1) return;
		/* 保存到数据库表中 */
		boolean isSaveActivity = static_block_activity.saveActivity(targetid, loginMap, selectedPoint);
		if (!isSaveActivity) return;
		/* 更新抽奖后状态，并发送相关邮件或站内信，抽中成功后运行 用于更新会员返回状态 */
		role_Cash_after(amc, loginMap, selectedPoint, isSaveActivity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.web.AbstractActivityProject#role_SendToMemberCoin(int, java.util.Map,
	 * com.maqiao.was.luckActivity.web.ActivityMember)
	 */
	@Override
	public boolean role_SendToMemberCoin(int selectedPoint, Map<Object, Object> loginMap, ActivityMember amc) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.web.AbstractActivityProject#role_SendToMemberEmail(int, java.util.Map,
	 * com.maqiao.was.luckActivity.web.ActivityMember)
	 */
	@Override
	public boolean role_SendToMemberEmail(int selectedPoint, Map<Object, Object> loginMap, ActivityMember amc) {
		/* 向中奖会员发送邮件 */
		if (!isSendMemberEmail()) return false;
		if (selectedPoint == -1) return false;
		ProjectActivityItem f = getActivityItem(selectedPoint);
		if (f == null) return false;
		/* 奖品名称 */
		String prizeTitle = f.getTitle();
		String[] titleArray = { prizeTitle };
		String[] contentArray = { prizeTitle };
		return sendEmailMember(pac, loginMap, titleArray, contentArray);
	}

	/*
	 * (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.web.AbstractActivityProject#role_SendToServiceEmail(int, java.util.Map,
	 * com.maqiao.was.luckActivity.web.ActivityMember)
	 */
	@Override
	public boolean role_SendToServiceEmail(int selectedPoint, Map<Object, Object> loginMap, ActivityMember amc) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.web.InterfaceMemberOutput#MemberOutput()
	 */
	@Override
	public long MemberOutput() {
		long value = 0L;
		value |= Output.sj_MemberId;
		value |= Output.sj_Account;
		value |= Output.sj_AddTime;
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.web.AbstractActivityProject#ExtendModelAndView(org.springframework.web.servlet.ModelAndView, java.util.Map,
	 * com.maqiao.was.luckActivity.web.ActivityMember)
	 */
	@Override
	public void ExtendModelAndView(ModelAndView mav, Map<Object, Object> loginMap, ActivityMember amc) {
		String url = static_block_activity.state_Member.getUrl(loginMap);
		mav.getModel().put("sj_activityFromSiteidUrl", url);
	}

	/*
	 * (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.web.AbstractActivityProject#role_CheckMember(java.util.Map, com.maqiao.was.luckActivity.web.ActivityMember)
	 */
	@Override
	public boolean role_CheckMember(Map<Object, Object> loginMap, ActivityMember amc) {
		int state = amc.getMemberState().getValue();
		/* 判断会员是否有资格 */
		if (state < 5) return false;
		/* 检测会员是否在活动期 */
		if (!amc.getMemberState().isBetween()) return false;
		return true;
	}

}
