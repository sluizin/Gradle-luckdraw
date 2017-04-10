/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.io.Serializable;

/**
 * 设置中奖项目 编号、名称、概率、库币
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class ProjectActivityItem implements Serializable {
	private static final long serialVersionUID = 1L;
	int i = 0;
	String title_level = "";
	/** 显示名称 */
	String title = "";
	/** 显示名称2 */
	String title_1 = "";
	/** 会员概率 */
	int angle = 0;
	/** 会员价值库币 */
	int angleCoin = 0;

	/**
	 * 构造函数
	 */
	public ProjectActivityItem() {

	}

	/**
	 * <pre>
	 * this.i = i;
	 * this.title = title;
	 * this.title_1 = title_1;
	 * if (len &gt; 0) this.angle = array[0];
	 * if (len &gt; 1) this.angleCoin = array[1];
	 * </pre>
	 * @param i int
	 * @param title String
	 * @param title_1 String
	 * @param array int[]
	 */
	public ProjectActivityItem(int i, String title, String title_1, int... array) {
		int len = array.length;
		this.i = i;
		this.title = title;
		this.title_1 = title_1;
		if (len > 0) this.angle = array[0];
		if (len > 1) this.angleCoin = array[1];
	}

	/**
	 * <pre>
	 * this.i = i;
	 * this.title_level = title_level;
	 * this.title = title;
	 * this.title_1 = title_1;
	 * if (len &gt; 0) this.angle = array[0];
	 * if (len &gt; 1) this.angleCoin = array[1];
	 * </pre>
	 * @param i int
	 * @param title_level String
	 * @param title String
	 * @param title_1 String
	 * @param array int[]
	 */
	public ProjectActivityItem(int i, String title_level, String title, String title_1, int... array) {
		int len = array.length;
		this.i = i;
		this.title_level = title_level;
		this.title = title;
		this.title_1 = title_1;
		if (len > 0) this.angle = array[0];
		if (len > 1) this.angleCoin = array[1];
	}

	/**
	 * 得到是angle
	 * @return int
	 */
	public int getValue() {
		return this.angle;
	}

	public final int getI() {
		return i;
	}

	public final void setI(int i) {
		this.i = i;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final int getAngle() {
		return angle;
	}

	public final void setAngle(int angle) {
		this.angle = angle;
	}

	public final int getAngleCoin() {
		return angleCoin;
	}

	public final void setAngleCoin(int angleCoin) {
		this.angleCoin = angleCoin;
	}

	public final String getTitle_level() {
		return title_level;
	}

	public final void setTitle_level(String title_level) {
		this.title_level = title_level;
	}

	public final String getTitle_1() {
		return title_1;
	}

	public final void setTitle_1(String title_1) {
		this.title_1 = title_1;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProjectActivityItem [i=");
		builder.append(i);
		builder.append(", title_level=");
		builder.append(title_level);
		builder.append(", title=");
		builder.append(title);
		builder.append(", title_1=");
		builder.append(title_1);
		builder.append(", angle=");
		builder.append(angle);
		builder.append(", angleCoin=");
		builder.append(angleCoin);
		builder.append("]");
		return builder.toString();
	}

}
