/**
 * 
 */
package com.maqiao.was.luckActivity.service.activity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class Activity implements Serializable {
	private static final long serialVersionUID = 1L;
	long id = 0;
	int targetid = 0;
	String title = "";
	String title2 = "";
	int member_id = 0;
	Date add_time;
	int state = 0;
	int selectedpoint = 0;
	String selecttitle = "";

	public Activity() {

	}

	/**
	 * long id = 0;<br/>
	 * int targetid = 0;<br/>
	 * String title = "";<br/>
	 * String title2 = "";<br/>
	 * int member_id = 0;<br/>
	 * Date add_time;<br/>
	 * int state = 0;<br/>
	 * int selectedpoint = 0;<br/>
	 * String selecttitle = "";<br/>
	 * int level = 0;<br/>
	 * @param id long
	 * @param targetid int
	 * @param title String
	 * @param title2 String
	 * @param member_id int
	 * @param add_time Date
	 * @param state int
	 * @param selectedpoint int
	 * @param selecttitle String
	 * @param level int
	 */
	public Activity(long id, int targetid, String title, String title2, int member_id, Date add_time, int state, int selectedpoint, String selecttitle) {
		this.id = id;
		this.targetid = targetid;
		this.title = title;
		this.title2 = title2;
		this.member_id = member_id;
		if (add_time != null) this.add_time = add_time;
		this.state = state;
		this.selectedpoint = selectedpoint;
		this.selecttitle = selecttitle;
	}

	public final String getSelectTitle() {
		return selecttitle;
	}

	public final void setSelectTitle(String selectTitle) {
		this.selecttitle = selectTitle;
	}

	public final long getId() {
		return id;
	}

	public final void setId(long id) {
		this.id = id;
	}

	public final int getTargetid() {
		return targetid;
	}

	public final void setTargetid(int targetid) {
		this.targetid = targetid;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final String getTitle2() {
		return title2;
	}

	public final void setTitle2(String title2) {
		this.title2 = title2;
	}

	public final int getMember_id() {
		return member_id;
	}

	public final void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public final Date getAdd_time() {
		return add_time;
	}

	public final void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public final int getState() {
		return state;
	}

	public final void setState(int status) {
		this.state = status;
	}

	public final int getSelectedpoint() {
		return selectedpoint;
	}

	public final void setSelectedpoint(int selectedpoint) {
		this.selectedpoint = selectedpoint;
	}

	public final String getSelecttitle() {
		return selecttitle;
	}

	public final void setSelecttitle(String selecttitle) {
		this.selecttitle = selecttitle;
	}


	/**
	 * 得到匿名account
	 * @return String
	 */
	public final String getAnonymousTitle() {
		if (title == null || title.length() == 0) return "****";
		int len = title.length();
		if (len < 3) return title.substring(0, 1) + "***";
		char[] array = title.toCharArray();
		for (int i = 2; i < len - 1; i++)
			array[i] = '*';
		return new String(array);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Activity [id=");
		builder.append(id);
		builder.append(", targetid=");
		builder.append(targetid);
		builder.append(", title=");
		builder.append(title);
		builder.append(", title2=");
		builder.append(title2);
		builder.append(", member_id=");
		builder.append(member_id);
		builder.append(", add_time=");
		builder.append(add_time);
		builder.append(", state=");
		builder.append(state);
		builder.append(", selectedpoint=");
		builder.append(selectedpoint);
		builder.append(", selecttitle=");
		builder.append(selecttitle);
		builder.append("]");
		return builder.toString();
	}

}
