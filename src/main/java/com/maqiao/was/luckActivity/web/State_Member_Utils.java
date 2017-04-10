/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public class State_Member_Utils {
	/** 当接收到json值是为空 */
	private static final String ACC_NULLMEMBERJSON = "{\"jsonValue\":[],\"status\":0}";
	/** 当接收到json值，提出的信息的关键字 */
	private static final String ACC_MEMBERJSONHEAD = "jsonValue";

	/**
	 * 判断json信息串是否为空
	 * @param jsonStr String
	 * @return boolean
	 */
	private static final boolean isJsonStrNull(final String jsonStr) {
		if (jsonStr == null || jsonStr.length() == 0 || jsonStr.equals(ACC_NULLMEMBERJSON)) return true;
		return false;
	}

	/**
	 * 从jsonStr中得到key中的值
	 * @param jsonStr String
	 * @param key String
	 * @return JSONObject
	 */
	static final JSONObject getJSonObject(final String jsonStr, final String key) {
		if (isJsonStrNull(jsonStr)) return null;
		JSONObject objJson = (JSONObject) JSONObject.parse(jsonStr);
		if (objJson == null || objJson.isEmpty()) return null;
		JSONArray arrayJson = (JSONArray) objJson.get(ACC_MEMBERJSONHEAD);
		if (arrayJson == null || arrayJson.size() == 0) return null;
		JSONObject jsonObject = arrayJson.getJSONObject(0);
		return jsonObject;
	}

	/**
	 * 从jsonStr中得到key中的值
	 * @param jsonStr String
	 * @param key String
	 * @return Object
	 */
	static final Object getObject(final String jsonStr, final String key) {
		JSONObject jsonObject = getJSonObject(jsonStr, key);
		if (jsonObject == null) return null;
		return jsonObject.get(key);
	}

	/**
	 * 从jsonStr中得到key中的值
	 * @param jsonStr String
	 * @param key String
	 * @return String
	 */
	static final String getString(final String jsonStr, final String key) {
		JSONObject jsonObject = getJSonObject(jsonStr, key);
		return jsonObject.getString(key);
	}

	/**
	 * 从jsonStr中得到key中的值
	 * @param jsonStr String
	 * @param key String
	 * @return int
	 */
	static final int getInt(final String jsonStr, final String key) {
		JSONObject jsonObject = getJSonObject(jsonStr, key);
		return jsonObject.getIntValue(key);

	}

	/**
	 * 从jsonStr中得到key中的值
	 * @param jsonStr String
	 * @param key String
	 * @return boolean
	 */
	static final boolean getBool(final String jsonStr, final String key) {
		JSONObject jsonObject = getJSonObject(jsonStr, key);
		return jsonObject.getBooleanValue(key);
	}
}
