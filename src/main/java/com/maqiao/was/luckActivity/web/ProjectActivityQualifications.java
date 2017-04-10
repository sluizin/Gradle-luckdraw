/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.io.Serializable;

/**
 * 会员资格：某级会员有多少次机会，机会不会累加，只有一个基础值<br/>
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class ProjectActivityQualifications implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 未登陆 */
	int unLogin = 0;
	/** 1:个人-活动期间[71] */
	int personalBetween = 0;
	/** 2:个人-过期[71] */
	int personalOverranging = 0;
	/** 3:个人会员-活动期间[83] */
	int memberIndividualBetween = 0;
	/** 个人会员-过期[83] */
	int memberIndividualOverranging = 0;
	/** 免费会员-活动期间[81] */
	int memberFreeBetween = 0;
	/** 6:免费会员-过期[81] */
	int memberFreeOverranging = 0;
	/** 7:单品通会员-活动期间[84] */
	int memberDptBetween = 0;
	/** 8:单品通会员-过期[84] */
	int memberDptOverranging = 0;
	/** 9:行业通-活动期间[87] */
	int memberHytBetween = 0;
	/** 10:行业通-过期[87] */
	int memberHytOverranging = 0;

	public final int getUnLogin() {
		return unLogin;
	}

	public final void setUnLogin(int unLogin) {
		this.unLogin = unLogin;
	}

	public final int getPersonalBetween() {
		return personalBetween;
	}

	public final void setPersonalBetween(int personalBetween) {
		this.personalBetween = personalBetween;
	}

	public final int getPersonalOverranging() {
		return personalOverranging;
	}

	public final void setPersonalOverranging(int personalOverranging) {
		this.personalOverranging = personalOverranging;
	}

	public final int getMemberIndividualBetween() {
		return memberIndividualBetween;
	}

	public final void setMemberIndividualBetween(int memberIndividualBetween) {
		this.memberIndividualBetween = memberIndividualBetween;
	}

	public final int getMemberIndividualOverranging() {
		return memberIndividualOverranging;
	}

	public final void setMemberIndividualOverranging(int memberIndividualOverranging) {
		this.memberIndividualOverranging = memberIndividualOverranging;
	}

	public final int getMemberFreeBetween() {
		return memberFreeBetween;
	}

	public final void setMemberFreeBetween(int memberFreeBetween) {
		this.memberFreeBetween = memberFreeBetween;
	}

	public final int getMemberFreeOverranging() {
		return memberFreeOverranging;
	}

	public final void setMemberFreeOverranging(int memberFreeOverranging) {
		this.memberFreeOverranging = memberFreeOverranging;
	}

	public final int getMemberDptBetween() {
		return memberDptBetween;
	}

	public final void setMemberDptBetween(int memberDptBetween) {
		this.memberDptBetween = memberDptBetween;
	}

	public final int getMemberDptOverranging() {
		return memberDptOverranging;
	}

	public final void setMemberDptOverranging(int memberDptOverranging) {
		this.memberDptOverranging = memberDptOverranging;
	}

	public final int getMemberHytBetween() {
		return memberHytBetween;
	}

	public final void setMemberHytBetween(int memberHytBetween) {
		this.memberHytBetween = memberHytBetween;
	}

	public final int getMemberHytOverranging() {
		return memberHytOverranging;
	}

	public final void setMemberHytOverranging(int memberHytOverranging) {
		this.memberHytOverranging = memberHytOverranging;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProjectActivityQualifications [unLogin=");
		builder.append(unLogin);
		builder.append(", personalBetween=");
		builder.append(personalBetween);
		builder.append(", personalOverranging=");
		builder.append(personalOverranging);
		builder.append(", memberIndividualBetween=");
		builder.append(memberIndividualBetween);
		builder.append(", memberIndividualOverranging=");
		builder.append(memberIndividualOverranging);
		builder.append(", memberFreeBetween=");
		builder.append(memberFreeBetween);
		builder.append(", memberFreeOverranging=");
		builder.append(memberFreeOverranging);
		builder.append(", memberDptBetween=");
		builder.append(memberDptBetween);
		builder.append(", memberDptOverranging=");
		builder.append(memberDptOverranging);
		builder.append(", memberHytBetween=");
		builder.append(memberHytBetween);
		builder.append(", memberHytOverranging=");
		builder.append(memberHytOverranging);
		builder.append("]");
		return builder.toString();
	}
	/**
	 * 按照state得到指定数量
	 * @param state int
	 * @return int
	 */
	public int getNumber(int state){
		switch (state) {
		case 0:
			return unLogin;
		case 1:
			return personalBetween;
		case 2:
			return personalOverranging;
		case 3:
			return memberIndividualBetween;
		case 4:
			return memberIndividualOverranging;
		case 5:
			return memberFreeBetween;
		case 6:
			return memberFreeOverranging;
		case 7:
			return memberDptBetween;
		case 8:
			return memberDptOverranging;
		case 9:
			return memberHytBetween;
		case 10:
			return memberHytOverranging;
		}
		return 0;
		
	}
}
