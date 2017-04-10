/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangku.was.member.dubbo.IRDubboMemberBasicService;

/**
 * 集合 IRDubboMemberBasicService接口中得到会员信息
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Service(value = "state_Member_MemberBasic")
public class State_Member_MemberBasic {
	@Autowired
	private IRDubboMemberBasicService iRDubboMemberBasicService;

	/**
	 * 通过iRDubboMemberBasicService得到某个key的值
	 * @param member_id long
	 * @param siteId int
	 * @param key String
	 * @return Object
	 */
	private final Object getMemberBasicJsonMemberId(long member_id, int siteId, final String key) {
		if (key == null || key.length() == 0) return null;
		String jsonStr = iRDubboMemberBasicService.getMemberBasicById(member_id, siteId);
		if (jsonStr == null || jsonStr.length() == 0) return null;
		return State_Member_Utils.getObject(jsonStr, key);
	}

	/**
	 * 通过member_id,siteid key值得到对象，为null时返回def值 String
	 * @param member_id long
	 * @param siteId int
	 * @param key String
	 * @param def String
	 * @return String
	 */
	private final String getDefault(long member_id, int siteId, String key, String def) {
		Object obj = getMemberBasicJsonMemberId(member_id, siteId, key);
		if (obj == null) return def;
		return obj.toString();
	}

	/**
	 * 得到会员的 corporationName 企业名称
	 * @param member_id long
	 * @param siteId int
	 * @return String
	 */
	final String getCorporationName(long member_id, int siteId) {
		return getDefault(member_id, siteId, "corporationName", "");
	}

	/**
	 * 得到会员的 categoryCode
	 * @param member_id long
	 * @param siteId int
	 * @return String
	 */
	final String getCategoryCode(final long member_id, final int siteId) {
		return getDefault(member_id, siteId, "categoryCode", "");
	}

	/**
	 * 得到会员的 logo
	 * @param member_id long
	 * @param siteId int
	 * @return String
	 */
	final String getLogo(long member_id, int siteId) {
		return getDefault(member_id, siteId, "logo", "");
	}

	/**
	 * 得到会员的 url
	 * @param member_id long
	 * @param siteId int
	 * @return String
	 */
	final String getMemberUrl(long member_id, int siteId) {
		return getDefault(member_id, siteId, "url", "");
	}
}
