/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangku.was.system.admin.dubbo.service.IRDubboSiteService;

/**
 * 按照siteId得到企业信息
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Service(value = "state_Member_SiteInfo")
public class State_Member_SiteInfo {
	@Autowired
	private IRDubboSiteService iRDubboSiteService;

	/**
	 * iRDubboSiteService.getSiteById(siteId)得到JSONObject对象
	 * @param siteId int
	 * @param key String
	 * @return Object
	 */
	private final Object getSiteBasicJsonValObj(final int siteId, final String key) {
		String jsonStr = iRDubboSiteService.getSiteById(siteId);
		return State_Member_Utils.getObject(jsonStr, key);
	}

	/**
	 * 通过 iRDubboSiteService.getSiteById(siteId)按照key得到对应的对象，有默认值<br>
	 * 输出String
	 * @param siteId int
	 * @param key String
	 * @param def String
	 * @return String
	 */
	private final String getDefaultString(final int siteId, final String key, final String def) {
		if (siteId <= 0 || key == null || key.length() == 0) return def;
		Object obj = getSiteBasicJsonValObj(siteId, key);
		if (obj == null) return def;
		String result = (String) obj;
		return result;
	}

	/**
	 * 通过 iRDubboSiteService.getSiteById(siteId)按照key得到对应的对象，有默认值<br>
	 * 输出int
	 * @param siteId int
	 * @param key String
	 * @param def int
	 * @return int
	 */
	private final int getDefaultInt(final int siteId, final String key, final int def) {
		if (siteId <= 0 || key == null || key.length() == 0) return def;
		Object obj = getSiteBasicJsonValObj(siteId, key);
		if (obj == null) return def;
		return Integer.parseInt(String.valueOf(obj));
	}

	/**
	 * 通过 iRDubboSiteService.getSiteById(siteId)按照key得到对应的对象，有默认值<br>
	 * 输出boolean
	 * @param siteId int
	 * @param key String
	 * @param def boolean
	 * @return boolean
	 */
	private final boolean getDefaultBoolean(final int siteId, final String key, final boolean def) {
		if (siteId <= 0 || key == null || key.length() == 0) return def;
		Object obj = getSiteBasicJsonValObj(siteId, key);
		if (obj == null) return def;
		return Boolean.parseBoolean(obj.toString());
	}

	/**
	 * 得到站点的 addTime
	 * @param siteId int
	 * @return String
	 */
	final String getAddTime(final int siteId) {
		return getDefaultString(siteId, "addTime", "");
	}

	/**
	 * 得到站点的 address
	 * @param siteId int
	 * @return String
	 */
	final String getAddress(final int siteId) {
		return getDefaultString(siteId, "address", "");
	}

	/**
	 * 得到站点的 alias
	 * @param siteId int
	 * @return String
	 */
	final String getAlias(final int siteId) {
		return getDefaultString(siteId, "alias", "");
	}

	/**
	 * 得到站点的 analysisCode
	 * @param siteId int
	 * @return String
	 */
	final String getAnalysisCode(final int siteId) {
		return getDefaultString(siteId, "analysisCode", "");
	}

	/**
	 * 得到站点的 areaCode
	 * @param siteId int
	 * @return String
	 */
	final String getAreaCode(final int siteId) {
		return getDefaultString(siteId, "areaCode", "");
	}

	/**
	 * 得到站点的 baiduUnionCode
	 * @param siteId int
	 * @return String
	 */
	final String getBaiduUnionCode(final int siteId) {
		return getDefaultString(siteId, "baiduUnionCode", "");
	}

	/**
	 * 得到站点的 cause
	 * @param siteId int
	 * @return String
	 */
	final String getCause(final int siteId) {
		return getDefaultString(siteId, "cause", "");
	}

	/**
	 * 得到站点的 csPhone
	 * @param siteId int
	 * @return String
	 */
	final String getCsPhone(final int siteId) {
		return getDefaultString(siteId, "csPhone", "");
	}

	/**
	 * 得到站点的 email
	 * @param siteId int
	 * @return String
	 */
	final String getEmail(final int siteId) {
		return getDefaultString(siteId, "email", "");
	}

	/**
	 * 得到站点的 exInt
	 * @param siteId int
	 * @return String
	 */
	final int getExInt(final int siteId) {
		return getDefaultInt(siteId, "exInt", 0);
	}

	/**
	 * 得到站点的 fax
	 * @param siteId int
	 * @return String
	 */
	final String getFax(final int siteId) {
		return getDefaultString(siteId, "fax", "");
	}

	/**
	 * 得到站点的 helpIdOne
	 * @param siteId int
	 * @return String
	 */
	final String getHelpIdOne(final int siteId) {
		return getDefaultString(siteId, "helpIdOne", "");
	}

	/**
	 * 得到站点的 helpIdTwo
	 * @param siteId int
	 * @return String
	 */
	final String getHelpIdTwo(final int siteId) {
		return getDefaultString(siteId, "helpIdTwo", "");
	}

	/**
	 * 得到站点的 icp
	 * @param siteId int
	 * @return String
	 */
	final String getIcp(final int siteId) {
		return getDefaultString(siteId, "icp", "");
	}

	/**
	 * 得到站点的 id
	 * @param siteId int
	 * @return String
	 */
	final int getId(final int siteId) {
		return getDefaultInt(siteId, "id", 0);
	}

	/**
	 * 得到站点的 initial
	 * @param siteId int
	 * @return String
	 */
	final String getInitial(final int siteId) {
		return getDefaultString(siteId, "initial", "");
	}

	/**
	 * 得到站点的 isselled
	 * @param siteId int
	 * @return boolean
	 */
	final boolean getIsselled(final int siteId) {
		return getDefaultBoolean(siteId, "isselled", false);
	}

	/**
	 * 得到站点的 linkman
	 * @param siteId int
	 * @return String
	 */
	final String getLinkman(final int siteId) {
		return getDefaultString(siteId, "linkman", "");
	}

	/**
	 * 得到站点的 logo
	 * @param siteId int
	 * @return String
	 */
	final String getLogo(final int siteId) {
		return getDefaultString(siteId, "logo", "");
	}

	/**
	 * 得到站点的 mdfyTime
	 * @param siteId int
	 * @return String
	 */
	final String getMdfyTime(final int siteId) {
		return getDefaultString(siteId, "mdfyTime", "");
	}

	/**
	 * 得到站点的 mobile
	 * @param siteId int
	 * @return String
	 */
	final String getMobile(final int siteId) {
		return getDefaultString(siteId, "mobile", "");
	}

	/**
	 * 得到站点的 name
	 * @param siteId int
	 * @return String
	 */
	final String getName(final int siteId) {
		return getDefaultString(siteId, "name", "");
	}

	/**
	 * 得到站点的 platformType
	 * @param siteId int
	 * @return String
	 */
	final String getPlatformType(final int siteId) {
		return getDefaultString(siteId, "platformType", "");
	}

	/**
	 * 得到站点的 postcode
	 * @param siteId int
	 * @return String
	 */
	final String getPostcode(final int siteId) {
		return getDefaultString(siteId, "postcode", "");
	}

	/**
	 * 得到站点的 siteIco
	 * @param siteId int
	 * @return String
	 */
	final String getSiteIco(final int siteId) {
		return getDefaultString(siteId, "siteIco", "");
	}

	/**
	 * 得到站点的 status
	 * @param siteId int
	 * @return int
	 */
	final int getStatus(final int siteId) {
		return getDefaultInt(siteId, "status", 0);
	}

	/**
	 * 得到站点的 telephone
	 * @param siteId int
	 * @return String
	 */
	final String getTelephone(final int siteId) {
		return getDefaultString(siteId, "telephone", "");
	}

	/**
	 * 得到站点的 url
	 * @param siteId int
	 * @return String
	 */
	final String getUrl(final int siteId) {
		return getDefaultString(siteId, "url", "");
	}

}
