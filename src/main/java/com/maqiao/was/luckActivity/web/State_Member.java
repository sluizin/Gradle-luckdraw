/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员信息通用调用
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Service(value = "state_Member")
public final class State_Member {
	@Autowired
	private State_Member_LoginMap state_Member_LoginMap;
	@Autowired
	private State_Member_MemberBasic state_Member_MemberBasic;
	@Autowired
	private State_Member_SiteInfo state_Member_SiteInfo;
	@Autowired
	private State_Member_MemberService state_Member_MemberService;
	@Autowired
	private State_Member_CompanyService state_Member_CompanyService;
	@Autowired
	private State_Member_CoinService state_Member_CoinService;

	/**
	 * 得到会员的siteid
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return int
	 */
	public int getSiteId(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getSiteId(loginMap);
	}

	/**
	 * 得到会员的member_id
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return long
	 */
	public long getMemberId(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getMemberId(loginMap);
	}

	/**
	 * 得到会员的roleId [String]
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getRoleId(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getRoleId(loginMap);
	}

	/**
	 * 得到会员的roleId [int]
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return int
	 */
	public int getRoleIdint(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getSiteId(loginMap);
	}

	/**
	 * 得到会员的 account 登陆名
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getAccount(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getAccount(loginMap);
	}

	/**
	 * 得到会员的 linkMan
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getLinkMan(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getLinkMan(loginMap);
	}

	/**
	 * 得到会员的 mobile
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getMobile(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getMobile(loginMap);
	}

	/**
	 * 得到会员的 email
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */

	public String getEmail(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getEmail(loginMap);
	}

	/**
	 * 得到会员的 address
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */

	public String getAddress(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getAddress(loginMap);
	}

	/**
	 * 得到会员的 department
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getDepartment(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getDepartment(loginMap);
	}

	/**
	 * 得到会员的 fax
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getFax(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getFax(loginMap);
	}

	/**
	 * 得到会员的 nickname 昵称
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getNickname(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getNickname(loginMap);
	}

	/**
	 * 得到会员的 postCode
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getPostCode(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getPostCode(loginMap);
	}

	/**
	 * 得到会员的 qq
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getQq(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getQq(loginMap);
	}

	/**
	 * 得到会员的 status 状态
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getStatus(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getStatus(loginMap);
	}

	/**
	 * 得到会员的 telephone
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getTelephone(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getTelephone(loginMap);
	}

	/**
	 * 得到会员的 userUrl
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getUserUrl(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getUserUrl(loginMap);
	}

	/**
	 * 得到会员的 modifyTime 修改时间
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getModifyTime(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getModifyTime(loginMap);
	}

	/**
	 * 得到会员的 addTime 会员注册时间
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getAddTime(final Map<Object, Object> loginMap) {
		return state_Member_LoginMap.getAddTime(loginMap);
	}

	/**
	 * 得到会员的 corporationName 企业名称
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getCorporationName(final Map<Object, Object> loginMap) {
		if (loginMap == null || loginMap.size() == 0) return "";
		long member_id = getMemberId(loginMap);
		int siteId = getSiteId(loginMap);
		return getCorporationName(member_id, siteId);
	}

	/**
	 * 得到会员的 corporationName 企业名称
	 * @param member_id long
	 * @param siteId int
	 * @return String
	 */
	public String getCorporationName(long member_id, int siteId) {
		return state_Member_MemberBasic.getCorporationName(member_id, siteId);
	}

	/**
	 * 得到站点的 url
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getUrl(final Map<Object, Object> loginMap) {
		if (loginMap == null || loginMap.size() == 0) return "";
		int siteId = getSiteId(loginMap);
		return getUrl(siteId);
	}

	/**
	 * 得到站点的 url
	 * @param siteId int
	 * @return String
	 */
	public String getUrl(final int siteId) {
		return state_Member_SiteInfo.getUrl(siteId);
	}

	/**
	 * 得到会员的 businessSupply
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @return String
	 */
	public String getBusinessSupply(final Map<Object, Object> loginMap) {
		if (loginMap == null || loginMap.size() == 0) return "";
		long member_id = getMemberId(loginMap);
		int siteId = getSiteId(loginMap);
		return getBusinessSupply(member_id, siteId);
	}

	/**
	 * 得到会员的 businessSupply
	 * @param member_id long
	 * @param siteId int
	 * @return String
	 */
	public String getBusinessSupply(long member_id, int siteId) {
		return state_Member_CompanyService.getBusinessSupply(member_id, siteId);
	}

	/**
	 * 得到某个会员在活动期间或过期的会员状态
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param dateStart String
	 * @param dateEnd String
	 * @return EnumMemberState
	 */
	public EnumMemberState getEnumMemberState(final Map<Object, Object> loginMap, String dateStart, String dateEnd) {
		String account = getAccount(loginMap);
		String roleId = getRoleId(loginMap);
		String addTime = getAddTime(loginMap);
		EnumMemberState e = EnumMemberState.getEnumBySpecialMember(account);
		if (e != null) return e;
		return EnumMemberState.getEnumByRoldId(roleId, addTime, dateStart, dateEnd);
	}

	/**
	 * 终极发送库币
	 * @param member_id long
	 * @param value int
	 * @param arg2 String
	 * @param arg3 String
	 * @return boolean
	 */
	public final boolean sendCoin(final long member_id, final int value, String arg2, String arg3) {
		return state_Member_CoinService.sendCoin(member_id, value, arg2, arg3);
	}

	/**
	 * 会员信息编号:<br>
	 * MemberId = 1L;<br>
	 * SiteId = 2L;<br>
	 * RoleId = 4L;<br>
	 * Account = 8L;<br>
	 * LinkMan = 16L;<br>
	 * Mobile = 32L;<br>
	 * Email = 64L;<br>
	 * AddTime = 128L;<br>
	 * CorporationName = 256L;<br>
	 * BusinessSupply = 512L;<br>
	 * @author Sunjian
	 * @version 1.0
	 * @since jdk1.7
	 */
	public final static class Output {
		public static final long sj_MemberId = 1L;
		public static final long sj_SiteId = 2L;
		public static final long sj_RoleId = 4L;
		public static final long sj_Account = 8L;
		public static final long sj_LinkMan = 16L;
		public static final long sj_Mobile = 32L;
		public static final long sj_Email = 64L;
		public static final long sj_AddTime = 128L;
		public static final long sj_CorporationName = 256L;
		public static final long sj_BusinessSupply = 512L;
	}

	/**
	 * 设置会员信息输出到map中[通过接口]
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param map Map&lt;String, Object&gt;
	 * @param e InterfaceMemberOutput
	 */
	public void setMemberOutput(final Map<Object, Object> loginMap, final Map<String, Object> map, InterfaceMemberOutput e) {
		setMemberOutput(loginMap, map, e.MemberOutput());
	}

	/**
	 * 设置会员信息输出到map中
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param map Map&lt;String, Object&gt;
	 * @param value long
	 */
	public void setMemberOutput(final Map<Object, Object> loginMap, final Map<String, Object> map, long value) {
		if (UtilsACC.shift(value, Output.sj_MemberId)) map.put("sj_MemberId", getMemberId(loginMap));
		if (UtilsACC.shift(value, Output.sj_SiteId)) map.put("sj_SiteId", getSiteId(loginMap));
		if (UtilsACC.shift(value, Output.sj_RoleId)) map.put("sj_RoleId", getRoleId(loginMap));
		if (UtilsACC.shift(value, Output.sj_Account)) map.put("sj_Account", getAccount(loginMap));
		if (UtilsACC.shift(value, Output.sj_LinkMan)) map.put("sj_LinkMan", getLinkMan(loginMap));
		if (UtilsACC.shift(value, Output.sj_Mobile)) map.put("sj_Mobile", getMobile(loginMap));
		if (UtilsACC.shift(value, Output.sj_Email)) map.put("sj_Email", getEmail(loginMap));
		if (UtilsACC.shift(value, Output.sj_AddTime)) map.put("sj_AddTime", getAddTime(loginMap));
		if (UtilsACC.shift(value, Output.sj_CorporationName)) map.put("sj_CorporationName", getCorporationName(loginMap));
		if (UtilsACC.shift(value, Output.sj_BusinessSupply)) map.put("sj_BusinessSupply", getBusinessSupply(loginMap));
	}

}
