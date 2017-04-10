/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.io.Serializable;

/**
 * 抽奖会员信息，会员等级，会员的状态，抽奖情况<br>
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class ActivityMember implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 登陆输出状态 */
	@Deprecated
	int state = 0;
	/** 登陆输出状态 */
	EnumMemberState memberState = EnumMemberState.Error;
	/** 会员编号 */
	long member_id = 0;

	/** 会员还剩多少次抽奖机会 */
	int luckAmount = 0;

	/** 总机会 */
	int luckSort = 0;

	/** 表中有多少个已抽奖记录数量 */
	int luckSelected = 0;

	/** 此项目共抽中多少人 */
	long projectAmount = 0;
	/** 抽中的奖项编号 */
	int selectedPoint = -1;
	/** 抽中的奖项名称 */
	String selectedPointTitle = "";
	/** 抽中的奖项名称[简称] */
	String selectedPointTitle_1 = "";

	/** 多个子免费会员 */
	int subCount1 = 0;
	/** 多个子企业会员 */
	int subCount2 = 0;

	/**
	 * 输出ajax中需要的json串
	 * @return String
	 */
	public String toJson() {
		StringBuilder sb = new StringBuilder(250);
		sb.append('{');
		sb.append("\"state\":\"" + memberState.value + "\"");
		sb.append(',');
		sb.append("\"member_id\":\"" + member_id + "\"");
		sb.append(',');
		sb.append("\"luckAmount\":\"" + luckAmount + "\"");
		sb.append(',');
		sb.append("\"luckSort\":\"" + luckSort + "\"");
		sb.append(',');
		sb.append("\"luckSelected\":\"" + luckSelected + "\"");
		sb.append(',');
		sb.append("\"selectedPoint\":\"" + selectedPoint + "\"");
		sb.append(',');
		sb.append("\"selectedPointTitle\":\"" + selectedPointTitle + "\"");
		sb.append(',');
		sb.append("\"selectedPointTitle_1\":\"" + selectedPointTitle_1 + "\"");
		sb.append(',');
		sb.append("\"projectAmount\":\"" + projectAmount + "\"");
		sb.append(',');
		sb.append("\"subCount1\":\"" + subCount1 + "\"");
		sb.append(',');
		sb.append("\"subCount2\":\"" + subCount2 + "\"");
		sb.append('}');
		return sb.toString();
	}

	/**
	 * -1:ERROR<br>
	 * 0:未登陆<br>
	 * 1:个人-活动期间[71]<br>
	 * 2:个人-过期[71]<br>
	 * 3:个人会员-活动期间[83]<br>
	 * 4:个人会员-过期[83]<br>
	 * 5:免费会员-活动期间[81]<br>
	 * 6:免费会员-过期[81]<br>
	 * 7:单品通会员-活动期间[84]<br>
	 * 8:单品通会员-过期[84]<br>
	 * 9:行业通-活动期间[87]<br>
	 * 10:行业通-过期[87]<br>
	 * @return int
	 */
	@Deprecated
	public final int getState() {
		return state;
	}

	/**
	 * 得到中文的会员状态
	 * @return String
	 */
	@Deprecated
	public final String getStateChn() {
		return getStateChn(state);
	}

	/**
	 * 按照state数值得到中文的会员状态
	 * @param state int
	 * @return String
	 */
	@Deprecated
	public static final String getStateChn(int state) {
		switch (state) {
		case -1:
			return "ERROR";
		case 0:
			return "未登陆";
		case 1:
			return "个人-活动期间";
		case 2:
			return "个人-过期";
		case 3:
			return "个人会员-活动期间";
		case 4:
			return "个人会员-过期";
		case 5:
			return "免费会员-活动期间";
		case 6:
			return "免费会员-过期";
		case 7:
			return "单品通会员-活动期间";
		case 8:
			return "单品通会员-过期";
		case 9:
			return "行业通-活动期间";
		case 10:
			return "行业通-过期";
		default:
			return "ERROR";
		}
	}

	public final void setState(int state) {
		this.state = state;
	}

	public final long getMember_id() {
		return member_id;
	}

	public final void setMember_id(long member_id) {
		this.member_id = member_id;
	}

	public final int getLuckAmount() {
		return luckAmount;
	}

	public final void setLuckAmount(int luckAmount) {
		this.luckAmount = luckAmount;
	}

	public final int getLuckSort() {
		return luckSort;
	}

	public final void setLuckSort(int luckSort) {
		this.luckSort = luckSort;
	}

	public final int getLuckSelected() {
		return luckSelected;
	}

	public final void setLuckSelected(int luckSelected) {
		this.luckSelected = luckSelected;
	}

	public final long getProjectAmount() {
		return projectAmount;
	}

	public final void setProjectAmount(long projectAmount) {
		this.projectAmount = projectAmount;
	}

	public final int getSelectedPoint() {
		return selectedPoint;
	}

	public final void setSelectedPoint(int selectedPoint) {
		this.selectedPoint = selectedPoint;
	}

	public final String getSelectedPointTitle() {
		return selectedPointTitle;
	}

	public final void setSelectedPointTitle(String selectedPointTitle) {
		this.selectedPointTitle = selectedPointTitle;
	}

	public final String getSelectedPointTitle_1() {
		return selectedPointTitle_1;
	}

	public final void setSelectedPointTitle_1(String selectedPointTitle_1) {
		this.selectedPointTitle_1 = selectedPointTitle_1;
	}

	public final int getSubCount1() {
		return subCount1;
	}

	public final void setSubCount1(int subCount1) {
		this.subCount1 = subCount1;
	}

	public final int getSubCount2() {
		return subCount2;
	}

	public final void setSubCount2(int subCount2) {
		this.subCount2 = subCount2;
	}

	public final EnumMemberState getMemberState() {
		return memberState;
	}

	public final void setMemberState(EnumMemberState memberState) {
		this.memberState = memberState;
	}

	/**
	 * 抽中一次，状态改变
	 * @return boolean
	 */
	public boolean addActivityPlus() {
		if (luckAmount > 0) {
			luckAmount--;
			luckSort++;
			luckSelected++;
			projectAmount++;
			return true;
		}
		return false;
	}

	/**
	 * 抽中多次，状态改变
	 * @param num int
	 * @return boolean
	 */
	public boolean addActivityPlus(int num) {
		if (num <= 0) return false;
		if (luckAmount > num) {
			luckAmount -= num;
			luckSort += num;
			luckSelected += num;
			projectAmount += num;
			return true;
		}
		return false;
	}
}
