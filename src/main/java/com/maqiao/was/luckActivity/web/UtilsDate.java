/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期型数据操作 一些静态方法
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class UtilsDate {
	/** yyyy-MM-dd */
	private static final DateFormat ACC_DF = new SimpleDateFormat("yyyy-MM-dd");
	/** yyyy-MM-dd HH:mm:ss */
	private static final DateFormat ACC_DFCOMPLETE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 判断项目活动日期之间多少天
	 * @return int
	 */
	static final int daysBetween(String dateStart, String dateEnd) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(ACC_DF.parse(dateStart));
			long time1 = cal.getTimeInMillis();
			cal.setTime(ACC_DF.parse(dateEnd));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
			return 0;
		}
	}

	/**
	 * 判断此日期是否在后面两个日期的之间。精确到日<br>
	 * 格式："yyyy-MM-dd"
	 * @deprecated 没有解决结束日期一天之内的是否在日期之内[暂时保留]<br>
	 * @param checkdate String
	 * @param dateStart String
	 * @param dateEnd String
	 * @return boolean
	 */
	@Deprecated
	static final boolean dateBetweenIn(String checkdate, String dateStart, String dateEnd) {
		try {
			Date dtnow = ACC_DF.parse(checkdate);
			Date dt1 = ACC_DF.parse(dateStart);
			Date dt2 = ACC_DF.parse(dateEnd);
			if (dt1.getTime() <= dtnow.getTime() && dtnow.getTime() <= dt2.getTime()) return true;
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
		}
		return false;
	}

	/**
	 * 判断此日期是否在后面两个日期的之间。精确到日[是否含有结束日期当天]<br>
	 * 格式："yyyy-MM-dd"
	 * @param checkdate String
	 * @param dateStart String
	 * @param dateEnd String
	 * @param containDateEnd boolean
	 * @return boolean
	 */
	static final boolean dateBetweenIn(final String checkdate, final String dateStart, final String dateEnd, boolean containDateEnd) {
		try {
			final String extHMS = " 00:00:00";
			Date dtnow = ACC_DFCOMPLETE.parse(checkdate);
			Date dt1 = ACC_DFCOMPLETE.parse(dateStart + extHMS);
			Date dt2 = ACC_DFCOMPLETE.parse(dateEnd + (containDateEnd ? " 23:59:59" : extHMS));
			if (dt1.getTime() <= dtnow.getTime() && dtnow.getTime() <= dt2.getTime()) return true;
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
		}
		return false;
	}

	/**
	 * 计算时间与当前时间的间隔
	 * @param thisDateTime Date
	 * @return String
	 */
	static final String getDateDiffNow(Date thisDateTime) {
		return getDateDiffNow(thisDateTime.getTime());
	}

	/**
	 * 计算时间与当前时间的间隔
	 * @param thisDateTime long
	 * @return String
	 */
	static final String getDateDiffNow(long thisDateTime) {
		long nowlong = (Calendar.getInstance()).getTimeInMillis();
		long dateDiffLong = nowlong - thisDateTime;
		if (dateDiffLong > 0) return formatDuring(dateDiffLong, false);
		return "0秒";
	}

	/**
	 * 把差值数值转成间隔：XX天XX小时XX分钟XX秒
	 * @param mss long
	 * @param isEnglish boolean
	 * @return String
	 */
	static final String formatDuring(final long mss, final boolean isEnglish) {
		final long days = mss / (1000 * 60 * 60 * 24);
		final long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		final long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		final long seconds = (mss % (1000 * 60)) / 1000;
		StringBuilder sb = new StringBuilder(40);
		if (days > 0) sb.append(days + (isEnglish ? "days" : "天"));
		if (hours > 0) sb.append(hours + (isEnglish ? "hours" : "小时"));
		if (minutes > 0) sb.append(minutes + (isEnglish ? "minutes" : "分钟"));
		if (seconds > 0) sb.append(seconds + (isEnglish ? "seconds" : "秒"));
		if (sb.length() == 0) sb.append(0 + (isEnglish ? "seconds" : "秒"));
		return sb.toString();
	}

	/**
	 * 得到date1到现在之间的随机日期 或置换<br>
	 * date1:"2016-09-04"
	 * @param date1 String
	 * @return Date
	 */
	static final Date getRndNowBefore(String date1) {
		try {
			long dt1 = ACC_DF.parse(date1).getTime();
			long dtnow = ACC_DF.parse(ACC_DF.format(new Date())).getTime();
			if (dt1 > dtnow) return getRnd(date1, ACC_DF.format(new Date()));
			else return getRnd(ACC_DF.format(new Date()), date1);
		} catch (ParseException e) {
			LuckActivityLogger.logger.error(e);
		}
		return getRnd(date1, ACC_DF.format(new Date()));
	}

	/**
	 * 得到现在日期的前几天的之内的随机日期
	 * @param day int
	 * @return Date
	 */
	static final Date getRndNowBefore(int day) {
		Calendar rightNow = java.util.Calendar.getInstance();
		rightNow.add(Calendar.DATE, -day);
		return getRnd(ACC_DF.format(rightNow.getTime()), ACC_DF.format(new Date()));
	}

	/**
	 * 得到date1与date2之间的随机时间<br>
	 * 格式:"yyyy-MM-dd"
	 * @param date1 String
	 * @param date2 String
	 * @return Date
	 */
	static final Date getRnd(final String date1, final String date2) {
		try {
			long dt1 = ACC_DF.parse(date1).getTime();
			long dt2 = ACC_DF.parse(date2).getTime();
			long dt = getRndDate(dt1, dt2);
			return new Date(dt);
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
		}
		return new Date();
	}

	/**
	 * 得到两个日期之间的数值
	 * @param dt1 long
	 * @param dt2 long
	 * @return long
	 */
	static final long getRndDate(final long dt1, final long dt2) {
		if (dt1 < dt2) return UtilsRnd.getRndLong(dt1, dt2);
		return UtilsRnd.getRndLong(dt2, dt1);
	}
}
