/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.io.Serializable;

/**
 * 项目中奖后向会员自己发送邮件配置
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public class ProjectActivityEmailMember implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 邮件标题 */
	String emailTitle = "";
	/** 邮件内容 */
	String emailContent = "";

	final String getEmailTitle() {
		return emailTitle;
	}

	final void setEmailTitle(String emailTitle) {
		if (emailTitle == null) return;
		this.emailTitle = emailTitle.trim();
	}

	final String getEmailContent() {
		return emailContent;
	}

	final void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	/**
	 * 返回设置状态
	 * @return boolean
	 */
	final boolean isSafe() {
		if (emailTitle == null || emailTitle.length() == 0 || emailContent == null) return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProjectActivityMemberemail [emailTitle=");
		builder.append(emailTitle);
		builder.append(", emailContent=");
		builder.append(emailContent);
		builder.append("]");
		return builder.toString();
	}

}
