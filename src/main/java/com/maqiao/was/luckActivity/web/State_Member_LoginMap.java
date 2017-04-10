/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 通过Map&lt;Object, Object&gt; loginMap得到会员的详细信息
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Service(value = "state_Member_LoginMap")
public class State_Member_LoginMap {

	/*
	 * ======================================================================================================
	 * final Map<Object, Object> loginMap
	 * ======================================================================================================
	 */
	/**
	 * 从loginMap中通过key值得到对象，为null时返回def值 String
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param key String
	 * @return String
	 */
	private final Object getLoginMapObject(final Map<Object, Object> loginMap, final String key) {
		if (loginMap == null || loginMap.size() == 0) return null;
		return loginMap.get(key);
	}

	/**
	 * 得到会员的siteid
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return int
	 */
	final int getSiteId(final Map<Object, Object> loginMap) {
		Object obj = getLoginMapObject(loginMap, "siteId");
		if (obj == null) return 0;
		return (int) Long.parseLong(obj.toString());
	}

	/**
	 * 得到会员的member_id
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return long
	 */
	final long getMemberId(final Map<Object, Object> loginMap) {
		Object obj = getLoginMapObject(loginMap, "memberId");
		if (obj == null) return 0;
		return Long.parseLong(obj.toString());
	}

	/**
	 * 得到会员的roleId [String]
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getRoleId(final Map<Object, Object> loginMap) {
		Object obj = getLoginMapObject(loginMap, "roleId");
		if (obj == null) return "";
		return obj.toString();
	}

	/**
	 * 得到会员的roleId [int]
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return int
	 */
	final int getRoleIdint(final Map<Object, Object> loginMap) {
		String str = getRoleId(loginMap);
		if (str == null) return 0;
		return Integer.parseInt(str);
	}

	/**
	 * 从loginMap中通过key值得到对象，为null时返回def值 String
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param key String
	 * @param def String
	 * @return String
	 */
	private final String getMapDefault(final Map<Object, Object> loginMap, String key, String def) {
		Object obj = getLoginMapObject(loginMap, key);
		if (obj == null) return def;
		return obj.toString();
	}

	/**
	 * 得到会员的 account 登陆名
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getAccount(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "account", "");
	}

	/**
	 * 得到会员的 linkMan
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getLinkMan(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "linkMan", "");
	}

	/**
	 * 得到会员的 mobile
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getMobile(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "mobile", "");
	}

	/**
	 * 得到会员的 email
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */

	final String getEmail(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "email", "");
	}

	/**
	 * 得到会员的 address
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */

	final String getAddress(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "address", "");
	}

	/**
	 * 得到会员的 department
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getDepartment(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "department", "");
	}

	/**
	 * 得到会员的 fax
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getFax(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "fax", "");
	}

	/**
	 * 得到会员的 nickname 昵称
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getNickname(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "nickname", "");
	}

	/**
	 * 得到会员的 postCode
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getPostCode(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "postCode", "");
	}

	/**
	 * 得到会员的 qq
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getQq(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "qq", "");
	}

	/**
	 * 得到会员的 status 状态
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getStatus(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "status", "");
	}

	/**
	 * 得到会员的 telephone
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getTelephone(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "telephone", "");
	}

	/**
	 * 得到会员的 userUrl
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getUserUrl(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "userUrl", "");
	}

	/**
	 * 得到会员的 modifyTime 修改时间
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getModifyTime(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "modifyTime", "");
	}

	/**
	 * 得到会员的 addTime 会员注册时间
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	final String getAddTime(final Map<Object, Object> loginMap) {
		return getMapDefault(loginMap, "addTime", "");
	}
}
