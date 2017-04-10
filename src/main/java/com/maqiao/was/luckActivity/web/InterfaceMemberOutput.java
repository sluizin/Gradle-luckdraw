/**
 * 
 */
package com.maqiao.was.luckActivity.web;

/**
 * 设置哪些会员资料会被输出到freemarker中<br>
 * 例：Output.MemberId | Output.Account<br>
 * 或：
 * value |= Output.sj_MemberId;<br>
 * value |= Output.sj_LinkMan;<br>
 * 则：输出<br>
 * MemberId == ${sj_MemberId}<br>
 * Account == ${sj_Account}<br>
 * 两个会员信息<br>
 * 
 * <pre>
 * sj_MemberId			会员编号<br>
 * sj_SiteId			会员站点编号<br>
 * sj_RoleId			会员等级<br>
 * sj_Account			会员用户名<br>
 * sj_LinkMan			会员联系人<br>
 * sj_Mobile			会员手机<br>
 * sj_Email				会员电子邮件<br>
 * sj_AddTime			会员注册时间<br>
 * sj_CorporationName	会员企业名称<br>
 * sj_BusinessSupply	会员BusinessSupply<br>
 * </pre>
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public interface InterfaceMemberOutput {
	/**
	 * 设置哪些会员资料会被输出到freemarker<br>
	 * 例：<br>
	 * value |= Output.sj_MemberId;<br>
	 * value |= Output.sj_LinkMan;<br>
	 * 则：输出MemberId和Account 两个会员信息<br>
	 * Freemarker变量如下 ${XXXXX!}：
	 * 
	 * <pre>
	 * sj_MemberId			会员编号<br>
	 * sj_SiteId			会员站点编号<br>
	 * sj_RoleId			会员等级<br>
	 * sj_Account			会员用户名<br>
	 * sj_LinkMan			会员联系人<br>
	 * sj_Mobile			会员手机<br>
	 * sj_Email				会员电子邮件<br>
	 * sj_AddTime			会员注册时间<br>
	 * sj_CorporationName	会员企业名称<br>
	 * sj_BusinessSupply	会员BusinessSupply<br>
	 * </pre>
	 * @return long
	 */
	public long MemberOutput();
}
