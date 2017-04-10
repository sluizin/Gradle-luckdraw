/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.util.HashMap;
import java.util.Map;

/**
 * 中奖后发送邮件
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class UtilsEmail {
	/**
	 * 是否向管理员发送邮件
	 * @param pac ProjectActivityClass
	 * @return boolean
	 */
	static final boolean isSendServiceEmail(ProjectActivityClass pac) {
		return pac.emailService != null && pac.emailService.isSafe();
	}

	/**
	 * 是否向中奖会员发送邮件
	 * @param pac ProjectActivityClass
	 * @return boolean
	 */
	static final boolean isSendMemberEmail(ProjectActivityClass pac) {
		return pac.emailMember != null && pac.emailMember.isSafe();
	}

	/**
	 * 得到邮件标题<br>
	 * @deprecated 暂时无用
	 * @param pac ProjectActivityClass
	 * @param arrays Object[]
	 * @return String
	 */
	@Deprecated
	static final String getEmailTitle(ProjectActivityClass pac, Object... arrays) {
		if (pac == null) return "";
		String[] newArray = new String[arrays.length];
		for (int i = 0, len = arrays.length; i < len; i++)
			newArray[i] = arrays[i].toString();
		return getServiceEmailTitle(pac, newArray);
	}

	/**
	 * 得到邮件标题
	 * @param pac ProjectActivityClass
	 * @param arrays String[]
	 * @return String
	 */
	static final String getServiceEmailTitle(final ProjectActivityClass pac, String... arrays) {
		if (pac == null) return "";
		return getStringReplaceToValue(pac.emailService.emailTitle, arrays);
	}

	/**
	 * 依次以{arg0/arg1/arg2}，替换字符串数组
	 * @param str String
	 * @param arrays String[]
	 * @return String
	 */
	static final String getStringReplaceToValue(final String str, String... arrays) {
		if (str == null || str.length() == 0) return str;
		String newString = str;
		for (int i = 0, len = arrays.length; i < len; i++)
			newString = newString.replaceAll("\\{arg" + i + "\\}", arrays[i]);
		return newString;

	}

	/**
	 * 得到邮件地址数组 当在开发或测试环境时，邮件地址为环境邮箱
	 * @param pac ProjectActivityClass
	 * @return String[]
	 */
	@Deprecated
	static final String[] getServiceEmailAddress(ProjectActivityClass pac) {
		if (!isSendServiceEmail(pac)) return new String[0];
		if (LuckActivityConsts.ENV == EnumEnvironment.DEV || LuckActivityConsts.ENV == EnumEnvironment.TEST) {
			String[] arr1 = { LuckActivityConsts.ENV.email };
			return arr1;
		}
		return pac.emailService.address;
	}

	/**
	 * 得到邮件内容
	 * @param map Map&lt;String,String&gt;
	 * @return String
	 */
	@Deprecated
	static final String getEmailContent(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		sb.append("<style>.table-b table{border:1px solid #F00}.table-b table td{border:1px solid #F00}</style> ");
		sb.append("<table class='able-b' border='1' cellspacing='0' cellpadding='0'>");
		for (String key : map.keySet()) {
			sb.append("<tr height='45'>");
			sb.append("<td width='130' align='right'>" + key + "：</td>");
			sb.append("<td width='700'>" + map.get(key) + "</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	/**
	 * 得到邮件内容
	 * @param arrays String[]
	 * @return String
	 */
	@Deprecated
	static final String getEmailContent(String... arrays) {
		return getEmailContent(ArrayToMap(arrays));
	}

	/**
	 * String[]转成map成对出现
	 * @param arrays String
	 * @return Map&lt;String,String>
	 */
	@Deprecated
	private static final Map<String, String> ArrayToMap(String... arrays) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0, ii = 0, len = arrays.length / 2; i < len; i++)
			map.put(arrays[ii++], arrays[ii++]);
		return map;
	}
}
