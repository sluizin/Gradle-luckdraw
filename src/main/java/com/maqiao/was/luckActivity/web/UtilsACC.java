/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日常工具<br>
 * 判断日期字符串是否在两个日期字符串之间
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class UtilsACC {
	/**
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
	 * @param roleId String
	 * @param addTime String
	 * @param dateStart String
	 * @param dateEnd String
	 * @return int
	 */
	static final int getMemberRoleIdState(String roleId, String addTime, String dateStart, String dateEnd) {
		if (roleId == null || roleId.length() == 0) return 0;
		if (addTime == null || addTime.length() == 0) return 0;
		boolean onDate = UtilsDate.dateBetweenIn(addTime, dateStart, dateEnd, true);
		/* 个人 */
		if (roleId.equals("71")) {
			if (onDate) return 1;
			else return 2;
		}
		/* 个人会员 */
		if (roleId.equals("83")) {
			if (onDate) return 3;
			else return 4;
		}
		/* 免费会员 */
		if (roleId.equals("81")) {
			if (onDate) return 5;
			else return 6;
		}
		/* 单品通会员 */
		if (roleId.equals("84")) {
			if (onDate) return 7;
			else return 8;
		}
		/* 行业通 */
		if (roleId.equals("87")) {
			if (onDate) return 9;
			else return 10;
		}
		return 0;
	}

	/**
	 * 移位判断
	 * @param range int
	 * @param point int
	 * @return boolean
	 */
	static final boolean shift(final int range, final int point) {
		return (range & point) > 0;
	}

	/**
	 * 移位判断
	 * @param range long
	 * @param point long
	 * @return boolean
	 */
	static final boolean shift(final long range, final long point) {
		return (range & point) > 0L;
	}

	/**
	 * 判断是否是数字
	 * @param str String
	 * @return boolean
	 */
	static final boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) return false;
		return true;
	}

	/**
	 * 计算百分比
	 * @param num long
	 * @param total long
	 * @return String
	 */
	static final String getPercent(long num, long total) {
		Double p3 = 0.0;
		p3 = (total == 0) ? 0.0 : num * 1.0 / total;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);/* 控制保留小数点后几位，2：表示保留2位小数点 */
		return nf.format(p3);
	}
}
