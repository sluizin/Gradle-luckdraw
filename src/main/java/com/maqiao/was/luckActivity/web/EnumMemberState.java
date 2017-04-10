/**
 * 
 */
package com.maqiao.was.luckActivity.web;

/**
 * 会员状态等级<br>
 * 在各个等级下，允许一个特殊帐号，用于测试使用[暂时预留] 功能已测，暂不启用<br>
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public enum EnumMemberState {
	Error(-1, "Error", "Error", "错误", -1, false, ""),
	/**  */
	UnLogin(0, "UnLogin", "未登陆", "未登陆", 0, false, ""),
	/**  */
	PersonalIn(1, "PersonalIn", "个人-活动期间[71]", "个人", 71, true, "specialmember1"),
	/**  */
	PersonalOut(2, "PersonalOut", "个人-过期[71]", "个人", 71, false, "specialmember2"),
	/**  */
	IndividualIn(3, "IndividualIn", "个人会员-活动期间[83]", "个人会员", 83, true, "specialmember3"),
	/**  */
	IndividualOut(4, "IndividualOut", "个人会员-过期[83]", "个人会员", 83, false, "specialmember4"),
	/**  */
	FreeIn(5, "FreeIn", "免费会员-活动期间[81]", "免费会员", 81, true, "specialmember5"),
	/**  */
	FreeOut(6, "FreeOut", "免费会员-过期[81]", "免费会员", 81, false, "specialmember6"),
	/**  */
	DptIn(7, "DptIn", "单品通会员-活动期间[84]", "单品通会员", 84, true, "specialmember7"),
	/**  */
	DptOut(8, "DptOut", "单品通会员-过期[84]", "单品通会员", 84, false, "specialmember8"),
	/**  */
	HytIn(9, "HytIn", "行业通-活动期间[87]", "行业通", 87, true, "specialmember9"),
	/**  */
	HytOut(10, "HytOut", "行业通-过期[87]", "行业通", 87, false, "specialmember10")

	;
	/** state:0/1/2/3/4/5/6/7/8/9/10 */
	int value = -1;
	/** DptIn/DptOut */
	String key = "";
	/** 单品通会员-过期[84] */
	String title = "";
	/** 行业通 */
	String name = "";
	/** 84 */
	int roldId = -1;
	/** true/false */
	boolean isBetween = false;
	/** 特殊会员 */
	String specialMember = "";

	/**
	 * 构造函数
	 * @param value int
	 * @param key String
	 * @param title String
	 * @param name String
	 * @param roldId int
	 * @param isBetween boolean
	 * @param specialMember String
	 */
	EnumMemberState(int value, String key, String title, String name, int roldId, boolean isBetween, String specialMember) {
		this.value = value;
		this.key = key;
		this.title = title;
		this.name = name;
		this.roldId = roldId;
		this.isBetween = isBetween;
		this.specialMember = specialMember;
	}

	public final int getValue() {
		return value;
	}

	public final void setValue(int value) {
		this.value = value;
	}

	public final String getKey() {
		return key;
	}

	public final void setKey(String key) {
		this.key = key;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final int getRoldId() {
		return roldId;
	}

	public final void setRoldId(int roldId) {
		this.roldId = roldId;
	}

	/**
	 * 会员是否在活动期注册
	 * @return boolean
	 */
	public final boolean isBetween() {
		return isBetween;
	}

	public final void setBetween(boolean isBetween) {
		this.isBetween = isBetween;
	}

	public final String getSpecialMember() {
		return specialMember;
	}

	public final void setSpecialMember(String specialMember) {
		this.specialMember = specialMember;
	}

	/**
	 * 通过会员名称找到某个状态的特殊会员信息
	 * @param account String
	 * @return EnumMemberState
	 */
	public static final EnumMemberState getEnumBySpecialMember(String account) {
		if (!LuckActivityConsts.ACC_USESPECIALMEMBER) return null;
		if (account == null || account.length() == 0) return null;
		for (EnumMemberState s : EnumMemberState.values())
			if (account.equals(s.specialMember)) return s;
		return null;
	}

	/**
	 * 通过 roldId得到会员状态<br>
	 * 格式："yyyy-MM-dd"<br>
	 * @param roldId int
	 * @param addTime String
	 * @param dateStart String
	 * @param dateEnd String
	 * @return EnumMemberState
	 */
	public static final EnumMemberState getEnumByRoldId(final String roldId, String addTime, String dateStart, String dateEnd) {
		int rold = 0;
		try {
			rold = Integer.valueOf(roldId).intValue();
		} catch (NumberFormatException e) {
			LuckActivityLogger.logger.error(e);
		}
		return getEnumByRoldId(rold, addTime, dateStart, dateEnd);
	}

	/**
	 * 通过 roldId得到会员状态<br>
	 * 格式："yyyy-MM-dd"<br>
	 * @param roldId int
	 * @param addTime String
	 * @param dateStart String
	 * @param dateEnd String
	 * @return EnumMemberState
	 */
	public static final EnumMemberState getEnumByRoldId(final int roldId, String addTime, String dateStart, String dateEnd) {
		if (roldId == 0) return UnLogin;
		boolean isbetween = UtilsDate.dateBetweenIn(addTime, dateStart, dateEnd, true);
		for (EnumMemberState s : EnumMemberState.values())
			if (s.roldId == roldId && s.isBetween == isbetween) return s;
		return Error;
	}

	/**
	 * 通过 state 得到会员状态<br>
	 * @param state int
	 * @return EnumMemberState
	 */
	public static final EnumMemberState getEnumByState(final int state) {
		for (EnumMemberState s : EnumMemberState.values())
			if (s.value == state) return s;
		return Error;

	}

}
