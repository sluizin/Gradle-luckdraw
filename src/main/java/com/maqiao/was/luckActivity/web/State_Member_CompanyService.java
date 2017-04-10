/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangku.was.member.dubbo.IRDubboMemberCompanyService;

/**
 * 集合 IRDubboMemberCompanyService得到企业信息
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Service(value = "state_Member_CompanyService")
public class State_Member_CompanyService {
	@Autowired
	private IRDubboMemberCompanyService iRDubboMemberCompanyService;

	/**
	 * 通过 iRDubboMemberCompanyService 得到某个key的值
	 * @param loginMap Map&lt;Object, Object>
	 * @param key String
	 * @return Object
	 */
	private final Object getMemberCompany(long member_id, int siteId, final String key) {
		if (key == null || key.length() == 0) return null;
		String jsonStr = iRDubboMemberCompanyService.getCompanyFiles(member_id, siteId);
		return State_Member_Utils.getObject(jsonStr, key);
	}

	/**
	 * 得到会员的 businessSupply
	 * @param member_id long
	 * @param siteId int
	 * @return String
	 */
	final String getBusinessSupply(long member_id, int siteId) {
		Object obj = getMemberCompany(member_id, siteId, "businessSupply");
		if (obj == null) return "";
		return obj.toString();
	}
}
