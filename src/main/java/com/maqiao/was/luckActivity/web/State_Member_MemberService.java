/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangku.was.member.dubbo.IRDubboMemberService;

/**
 * 集合 IRDubboMemberService接口中得到会员信息
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Service(value = "state_Member_MemberService")
public class State_Member_MemberService {

	@Autowired
	private IRDubboMemberService iRDubboMemberService;

	/**
	 * 通过iRDubboMemberService得到某个key的值
	 * @param loginMap Map&lt;Object, Object>
	 * @param key String
	 * @return Object
	 */
	private final Object getMemberJsonVal(final long member_id, int siteId, final String key) {
		if (key == null || key.length() == 0) return null;
		String jsonStr = iRDubboMemberService.getMemberById(member_id, siteId);
		return State_Member_Utils.getObject(jsonStr, key);
	}

	/**
	 * 从loginMap中通过key值得到对象，为null时返回def值 String
	 * @param loginMap Map&lt;Object, Object>
	 * @param key String
	 * @param def String
	 * @return String
	 */
	private final String getDefault(final long member_id, final int siteId, final String key, final String def) {
		Object obj = getMemberJsonVal(member_id, siteId, key);
		if (obj == null) return def;
		return obj.toString();
	}

	/**
	 * 得到会员的 areaCode 地区编号 例:101101101102
	 * @param member_id long
	 * @param siteId int
	 * @return String
	 */
	final String getAreaCode(final long member_id, final int siteId) {
		return getDefault(member_id, siteId, "areaCode", "");
	}

	/**
	 * 得到会员的 password
	 * @param member_id long
	 * @param siteId int
	 * @return String
	 */
	final String getPassword(final long member_id, final int siteId) {
		return getDefault(member_id, siteId, "password", "");
	}

}
