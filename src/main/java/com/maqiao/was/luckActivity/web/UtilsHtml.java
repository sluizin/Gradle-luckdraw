/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.maqiao.was.luckActivity.service.activity.model.Activity;

/**
 * 使用ajax得到某项目中的记录html，用于输出到div
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class UtilsHtml {
	/**
	 * 把List&lt;Activity&gt; list按照template转成多行字符串总和
	 * @param list List&lt;Activity&gt;
	 * @param template String
	 * @return String
	 */
	static final String ListToTemplate(List<Activity> list, final String template) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0, len = list.size(); i < len; i++)
			sb.append(ActivityToTemplate(list.get(i), template));
		return sb.toString();
	}

	/**
	 * 把template中的{arg0}转成Activity中的值<br>
	 * id[0],targetid[1],title[2],title2[3],member_id[4],<br>
	 * add_time[5],state[6],selectedpoint[7],selecttitle[8],<br>
	 * getAnonymousTitle()[9],xxx分钟XX秒[10]<br>
	 * @param e Activity
	 * @param template String
	 * @return String
	 */
	static final String ActivityToTemplate(Activity e, String template) {
		if (e == null || template == null || template.length() == 0) return "";
		template = inputTemplate(template, 0, Long.toString(e.getId()));
		template = inputTemplate(template, 1, e.getTargetid() + "");
		template = inputTemplate(template, 2, e.getTitle());
		template = inputTemplate(template, 3, e.getTitle2());
		template = inputTemplate(template, 4, e.getMember_id() + "");
		template = inputTemplate(template, 5, DateToString(e.getAdd_time()));
		template = inputTemplate(template, 6, e.getState() + "");
		template = inputTemplate(template, 7, e.getSelectedpoint() + "");
		template = inputTemplate(template, 8, e.getSelecttitle());
		template = inputTemplate(template, 9, e.getAnonymousTitle());
		template = inputTemplate(template, 10, UtilsDate.getDateDiffNow(e.getAdd_time()));
		return template;
	}

	/**
	 * 按照某个位置替换变量
	 * @param template String
	 * @param i int
	 * @param value String
	 * @return String
	 */
	static final String inputTemplate(String template, int i, String value) {
		return template.replaceAll("\\{arg" + i + "\\}", value);
	}

	/**
	 * 按照数组替换变量
	 * @param template String
	 * @param arrays String[]
	 * @return String
	 */
	static final String inputTemplate(String template, String... arrays) {
		for (int i = 0, len = arrays.length; i < len; i++)
			template = template.replaceAll("\\{arg" + i + "\\}", arrays[i]);
		return template;
	}

	/**
	 * 日期转成字符串
	 * @param now Date
	 * @return String
	 */
	static final String DateToString(Date now) {
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日");
		return myFmt.format(now);
	}
}
