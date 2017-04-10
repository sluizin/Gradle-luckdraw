/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 项目中奖后向管理员发送邮件配置
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public class ProjectActivityEmailService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 邮件发送地址 */
	String[] address = new String[0];
	/** 邮件标题 */
	String emailTitle = "";
	/** 邮件内容 */
	String emailContent = "";

	public final String[] getAddress() {
		if (LuckActivityConsts.ENV == EnumEnvironment.DEV || LuckActivityConsts.ENV == EnumEnvironment.TEST) {
			String[] arr1 = { LuckActivityConsts.ENV.email };
			return arr1;
		}
		return address;
	}

	public final void setAddress(String[] address) {
		this.address = address;
		for (int i = 0, len = this.address.length; i < len; i++)
			this.address[i] = this.address[i].trim();
	}

	public final String getEmailTitle() {
		if (emailTitle == null) return "";
		return emailTitle;
	}

	public final void setEmailTitle(String emailTitle) {
		if (emailTitle == null) return;
		this.emailTitle = emailTitle.trim();
	}

	public final String getEmailContent() {
		if (emailContent == null) return "";
		return emailContent;
	}

	public final void setEmailContent(String emailContent) {
		if (emailContent == null) return;
		this.emailContent = emailContent;
	}

	/**
	 * 返回设置状态
	 * @return boolean
	 */
	public final boolean isSafe() {
		if (address == null || address.length == 0 || emailTitle == null || emailTitle.length() == 0) return false;
		for (int i = 0, len = address.length; i < len; i++) {
			String add = address[i];
			if (add == null) continue;
			if (add.trim().length() > 0) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProjectActivityEmailService [address=");
		builder.append(Arrays.toString(address));
		builder.append(", emailTitle=");
		builder.append(emailTitle);
		builder.append(", emailContent=");
		builder.append(emailContent);
		builder.append("]");
		return builder.toString();
	}
}
