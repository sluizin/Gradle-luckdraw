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
 * [2017鸡年大吉抽奖专题] taretid:7 继承{@link AbstractActivityProject}
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public class Project_7_registrationaward extends AbstractActivityProject {
	/**
	 * {@linkplain AbstractActivityProject#AbstractActivityProject}<br>
	 * @param targetid int
	 * @param projectName String
	 */
	protected Project_7_registrationaward(int targetid, String projectName) {
		super(7, "registrationaward");
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
		if (selectedPoint == -1) return false;
		/* f为抽出的结果项目 */
		ProjectActivityItem f = getActivityItem(selectedPoint);
		if (f == null) return false;
		/* 奖品名称 */
		String prizeTitle = f.getTitle();
		/* 奖品名称2 */
		String prizeTitle2 = f.getTitle_1();
		return protectedSendCoin(amc, selectedPoint, prizeTitle, prizeTitle2, "");
	}

	/*
	 * (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.web.AbstractActivityProject#role_SendToMemberEmail(int, java.util.Map,
	 * com.maqiao.was.luckActivity.web.ActivityMember)
	 */
	@Override
	public boolean role_SendToMemberEmail(int selectedPoint, Map<Object, Object> loginMap, ActivityMember amc) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.maqiao.was.luckActivity.web.AbstractActivityProject#role_SendToServiceEmail(java.util.Map,
	 * com.maqiao.was.luckActivity.web.ActivityMember)
	 */
	@Override
	public boolean role_SendToServiceEmail(int selectedPoint, Map<Object, Object> loginMap, ActivityMember amc) {
		/* 向管理员发送邮件 */
		if (!isSendServiceEmail()) return false;
		/* f为抽出的结果项目 */
		ProjectActivityItem f = getActivityItem(selectedPoint);
		if (f == null) return false;
		/* 奖品名称 */
		String prizeTitle = f.getTitle();
		String[] titleArray = { pac.getTitle(), amc.getMemberState().getTitle(), prizeTitle };
		String memberid = amc.getMember_id() + "";
		String account = static_block_activity.state_Member.getAccount(loginMap);
		String memberTitle = amc.getMemberState().getTitle();
		String corporationName = static_block_activity.state_Member.getCorporationName(loginMap);
		String[] contentArray = { memberid, account, memberTitle, corporationName, prizeTitle };
		return sendEmailService(pac, loginMap, titleArray, contentArray);
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
		value |= Output.sj_SiteId;
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
		/* 检测会员等级[活动期间注册的会员] */
		if (state < 1) return false;
		/* 检测会员是否在活动期 */
		if (!amc.getMemberState().isBetween()) return false;
		return true;
	}

}
