/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * 抽奖专题项目类<br>
 * 包括基础总数、显示总数、Targetid、开始与结束日期、奖项等<br>
 * 抽中奖后发送到邮箱<br>
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class ProjectActivityClass implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 项目名称 */
	String projectName = "";
	/** 抽奖名称 */
	String title = "抽奖活动";
	/** 设置基础总数 */
	int sortBase = 1700;
	/** 设置显示总数 */
	int showListMax = 40;
	/** 设置项目编号 targetid 用于入库 */
	int targetid = 0;
	/** 设置活动开始日期 */
	String dateStart = "2016-06-26";
	/** 设置活动结束日期 */
	String dateEnd = "2016-06-26";
	/** 允许随机中奖人数 */
	boolean rndAllow = true;
	/** 设置随机中奖人员的日期，当前日期的前几天 */
	int rndListBeforeDay = 3;
	/** 设置中奖项目 */
	List<ProjectActivityItem> itemList = new ArrayList<ProjectActivityItem>(8);
	/** 设置库币消息代码 */
	String msgCoinCode = "";
	/** 设置实物消息代码 */
	String msgGoodsCode = "";
	/** 设置溢出次数 */
	int overflowTimes = 0;
	/** 设置是否开通下线状态1 */
	boolean openSubCount1 = false;
	/** 设置是否开通下线状态2 */
	boolean openSubCount2 = false;
	/** 设置是否开通项目总数的记录 */
	boolean openProjectAmount = false;
	/** 会员资格对象 */
	ProjectActivityQualifications qualifications = new ProjectActivityQualifications();

	/** 抽中奖后向管理员发送邮件 */
	ProjectActivityEmailService emailService = new ProjectActivityEmailService();
	/** 抽中奖后向中奖会员发送邮件 */
	ProjectActivityEmailMember emailMember = new ProjectActivityEmailMember();

	/**
	 * 判断会员注意日期是否在活动期间
	 * @param addTime String "2016-06-26"
	 * @return boolean
	 */
	 final boolean isNewMember(final String addTime) {
		return UtilsDate.dateBetweenIn(addTime, dateStart, dateEnd, true);
	}

	/**
	 * 得到中文日期<br>
	 * datetype:0 dateStart<br>
	 * datetype:1 dateEnd<br>
	 * @param datetype int
	 * @return String
	 */
	 final String getDateChn(final int datetype) {
		Date date2 = getDate(datetype);
		if (date2 == null) return "";
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date2);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(c1.getTime());
	}

	/**
	 * 得到日期型日期 2010-01-01<br>
	 * datetype:0 dateStart<br>
	 * datetype:1 dateEnd<br>
	 * @param datetype int
	 * @return Date
	 */
	 final Date getDate(final int datetype) {
		String date = (datetype == 0 ? this.dateStart : this.dateEnd);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date datenew = sdf.parse(date);
			return datenew;
		} catch (ParseException e) {
			LuckActivityLogger.logger.error(e);
		}
		return null;
	}

	/**
	 * 判断是否发布站内信--库币
	 * @return boolean
	 */
	public final boolean ismsgCoin() {
		if (msgCoinCode == null || msgCoinCode.length() == 0) return false;
		return true;
	}

	/**
	 * 判断是否发布站内信--实物
	 * @return boolean
	 */
	public final boolean ismsgGoods() {
		if (msgGoodsCode == null || msgGoodsCode.length() == 0) return false;
		return true;
	}

	/**
	 * 判断项目活动日期之间多少天
	 * @return int
	 */
	public final int daysBetween() {
		return UtilsDate.daysBetween(this.dateStart, this.dateEnd);
	}

	/**
	 * 得到项目的Li 的template<br>
	 * templatetime:几分钟之前<br>
	 * templatetitle:获得中奖的名称<br>
	 * @param targetid int
	 * @return String
	 */
	public final String getUITemplateList(int targetid) {
		if (targetid == 5) { return "<li class='clearfix'><span class='date'>templatetime前</span><span class='huojiang'>templatetitle</span></li>"; }
		return "";
	}

	public final String getProjectName() {
		return projectName;
	}

	public final void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final int getSortBase() {
		return sortBase;
	}

	public final void setSortBase(int sortBase) {
		this.sortBase = sortBase;
	}

	public final int getShowListMax() {
		return showListMax;
	}

	public final void setShowListMax(int showListMax) {
		this.showListMax = showListMax;
	}

	public final int getTargetid() {
		return targetid;
	}

	public final void setTargetid(int targetid) {
		this.targetid = targetid;
	}

	public final String getDateStart() {
		return dateStart;
	}

	public final void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public final String getDateEnd() {
		return dateEnd;
	}

	public final void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public final boolean isRndAllow() {
		return rndAllow;
	}

	public final void setRndAllow(boolean rndAllow) {
		this.rndAllow = rndAllow;
	}

	public final int getRndListBeforeDay() {
		return rndListBeforeDay;
	}

	public final void setRndListBeforeDay(int rndListBeforeDay) {
		this.rndListBeforeDay = rndListBeforeDay;
	}

	public final List<ProjectActivityItem> getItemList() {
		return itemList;
	}

	public final void setItemList(List<ProjectActivityItem> itemList) {
		this.itemList = itemList;
	}

	public final String getMsgCoinCode() {
		return msgCoinCode;
	}

	public final void setMsgCoinCode(String msgCoinCode) {
		this.msgCoinCode = msgCoinCode;
	}

	public final String getMsgGoodsCode() {
		return msgGoodsCode;
	}

	public final void setMsgGoodsCode(String msgGoodsCode) {
		this.msgGoodsCode = msgGoodsCode;
	}

	public final int getOverflowTimes() {
		return overflowTimes;
	}

	public final void setOverflowTimes(int overflowTimes) {
		this.overflowTimes = overflowTimes;
	}

	public final ProjectActivityQualifications getQualifications() {
		return qualifications;
	}

	public final void setQualifications(ProjectActivityQualifications qualifications) {
		this.qualifications = qualifications;
	}

	public final ProjectActivityEmailService getEmailService() {
		return emailService;
	}

	public final void setEmailService(ProjectActivityEmailService emailService) {
		this.emailService = emailService;
	}

	public final ProjectActivityEmailMember getEmailMember() {
		return emailMember;
	}

	public final void setEmailMember(ProjectActivityEmailMember emailMember) {
		this.emailMember = emailMember;
	}

	public final boolean isOpenSubCount1() {
		return openSubCount1;
	}

	public final void setOpenSubCount1(boolean openSubCount1) {
		this.openSubCount1 = openSubCount1;
	}

	public final boolean isOpenSubCount2() {
		return openSubCount2;
	}

	public final void setOpenSubCount2(boolean openSubCount2) {
		this.openSubCount2 = openSubCount2;
	}

	public final boolean isOpenProjectAmount() {
		return openProjectAmount;
	}

	public final void setOpenProjectAmount(boolean openProjectAmount) {
		this.openProjectAmount = openProjectAmount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProjectActivityClass [projectName=");
		builder.append(projectName);
		builder.append(", title=");
		builder.append(title);
		builder.append(", sortBase=");
		builder.append(sortBase);
		builder.append(", showListMax=");
		builder.append(showListMax);
		builder.append(", targetid=");
		builder.append(targetid);
		builder.append(", dateStart=");
		builder.append(dateStart);
		builder.append(", dateEnd=");
		builder.append(dateEnd);
		builder.append(", rndAllow=");
		builder.append(rndAllow);
		builder.append(", rndListBeforeDay=");
		builder.append(rndListBeforeDay);
		builder.append(", itemList=");
		builder.append(itemList);
		builder.append(", msgCoinCode=");
		builder.append(msgCoinCode);
		builder.append(", msgGoodsCode=");
		builder.append(msgGoodsCode);
		builder.append(", overflowTimes=");
		builder.append(overflowTimes);
		builder.append(", openSubCount1=");
		builder.append(openSubCount1);
		builder.append(", openSubCount2=");
		builder.append(openSubCount2);
		builder.append(", openProjectAmount=");
		builder.append(openProjectAmount);
		builder.append(", qualifications=");
		builder.append(qualifications);
		builder.append(", emailService=");
		builder.append(emailService);
		builder.append(", emailMember=");
		builder.append(emailMember);
		builder.append("]");
		return builder.toString();
	}

}
