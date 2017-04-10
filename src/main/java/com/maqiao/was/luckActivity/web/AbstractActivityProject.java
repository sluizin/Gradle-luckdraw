/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.maqiao.was.luckActivity.service.activity.model.Activity;

/**
 * 抽奖项目抽象类，实现{@link InterfaceMemberOutput}接口<br>
 * 配置文件:<br>
 * /template/ACCzhuanti/paradigm/activity/XXXXX/activityDB.json<br>
 * <br>
 * <b>初始化时，请使用两种方式进行构造[使用抽类构造函数super(targetid, projectName)]</b><br>
 * <br>
 * <b>一：无参数构造函数显示赋值</b><br>
 * 添加无参数构造函数，并在无参数构造函数中显示定义targetid与名称<br>
 * Project_xxxxyyyzzz() {super(7, "registrationaward");}<br>
 * [对类名无规则]<br>
 * <br>
 * <b>二：有参数构造函数显示赋值</b><br>
 * 添加无参数构造函数，并在无参数构造函数中显示定义targetid与名称<br>
 * protected Project_7_registrationaward(int targetid, String projectName) {super(7, "registrationaward");}<br>
 * [对类名无规则]<br>
 * <br>
 * <b>三：有参数构造函数隐示赋值[类名]</b><br>
 * 1:项目类名命名:public class Project_7_registrationaward extends AbstractActivityProject{}<br>
 * 2:不修改抽象构造函数:Project_7_registrationaward(int targetid, String projectName) {super(targetid, projectName);}<br>
 * [对类名有规则]<br>
 * <br>
 * <p style="color:red">
 * 优先级：第一种初始方式 &gt; 第二种初始方式
 * </p>
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public abstract class AbstractActivityProject implements InterfaceMemberOutput {
	/** 静态方法集合 */
	protected Static_block_Activity static_block_activity;
	/** 抽奖项目编号 */
	protected int targetid = 0;
	/** 抽奖项目名称 */
	protected String projectName = "";
	/** 抽奖项目具体奖项信息 */
	protected ProjectActivityClass pac;
	/** 缓存随机中奖人员 */
	List<Activity> histroyListRnd = new ArrayList<Activity>();

	/**
	 * 为抽奖项目编号和名称 赋值<br>
	 * 在类的生成了使用<br>
	 * (显性)如在子类中此构造函数直接赋值 Project_6_vibrationluck(){super(6, "vibrationluck");}<br>
	 * (隐性)在子类中此构造函数无值可赋，则通过文件名得到值<br>
	 * @param targetid int
	 * @param projectName String
	 */
	protected AbstractActivityProject(int targetid, String projectName) {
		this.targetid = targetid;
		this.projectName = projectName;
	}

	/**
	 * 更新抽奖后状态，抽中成功后运行 更新会员状态信息<br>
	 * 并且发送相关邮件或站内信
	 * @param amc ActivityMember
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param selectedPoint int
	 * @param isSaveActivity boolean
	 */
	protected void role_Cash_after(ActivityMember amc, Map<Object, Object> loginMap, int selectedPoint, boolean isSaveActivity) {
		/* 判断是否保存了中奖记录 */
		if (!isSaveActivity) return;
		/* 判断中奖记录编号是否正确 */
		if (!Static_block_Activity.isSafeSelectPoint(this, selectedPoint)) return;
		/* 更新会员状态值 */
		amc.setSelectedPoint(selectedPoint);
		amc.addActivityPlus();
		ProjectActivityItem f = pac.itemList.get(selectedPoint);
		amc.setSelectedPointTitle(f.getTitle());
		amc.setSelectedPointTitle_1(f.getTitle_1());
		/* 更新会员状态值完成 */
		/* 发送库币，并发送站内信 */
		role_SendToMemberCoin(selectedPoint, loginMap, amc);
		/* 向管理员发送邮件 */
		role_SendToServiceEmail(selectedPoint, loginMap, amc);
		/* 向中奖会员发送邮件 */
		role_SendToMemberEmail(selectedPoint, loginMap, amc);
	}

	/**
	 * 兑换规则 - &gt; 项目<br>
	 * 已通过会员检测<br>
	 * 虽然可把此方法提炼出来，暂时保留，利于后期程序上的扩展<br>
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param amc ActivityMember
	 */
	public abstract void role_Cash(final Map<Object, Object> loginMap, ActivityMember amc);

	/**
	 * 会员检查规则 - &gt; 项目<br>
	 * 只有此会员资格的会员可以进行抽奖<br>
	 * 如果资格返回false，则不进入抽奖步骤
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param amc ActivityMember
	 * @return boolean
	 */
	public abstract boolean role_CheckMember(final Map<Object, Object> loginMap, ActivityMember amc);

	/**
	 * 发送库币并发送站内信，如果发送库币完成，则发送站内信<br>
	 * 此抽象方法用于设置站内信中所需要的key值
	 * @param selectedPoint int
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param amc ActivityMember
	 * @return boolean
	 */
	public abstract boolean role_SendToMemberCoin(int selectedPoint, Map<Object, Object> loginMap, ActivityMember amc);

	/**
	 * 向会员发送中奖电子邮件<br>
	 * 此抽象方法用于设置邮件的标题与内容的{arg0}等的值<br>
	 * @param selectedPoint int
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param amc ActivityMember
	 * @return boolean
	 */
	public abstract boolean role_SendToMemberEmail(int selectedPoint, Map<Object, Object> loginMap, ActivityMember amc);

	/**
	 * 向管理员发送中奖电子邮件<br>
	 * 此抽象方法用于设置邮件的标题与内容的{arg0}等的值<br>
	 * @param selectedPoint int
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param amc ActivityMember
	 * @return boolean
	 */
	public abstract boolean role_SendToServiceEmail(int selectedPoint, Map<Object, Object> loginMap, ActivityMember amc);

	/**
	 * 在Controller中对ModelAndView mav进行扩展<br>
	 * 在ModelAndView.setViewName()之前<br>
	 * @param mav ModelAndView
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param amc ActivityMember
	 */
	public abstract void ExtendModelAndView(final ModelAndView mav, final Map<Object, Object> loginMap, ActivityMember amc);

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractActivityProject [static_block_activity=");
		builder.append(static_block_activity);
		builder.append(", targetid=");
		builder.append(targetid);
		builder.append(", projectName=");
		builder.append(projectName);
		builder.append(", pac=");
		builder.append(pac);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * 发送库币并发送站内信，如果发送库币完成，则发送站内信
	 * @param amc ActivityMember
	 * @param selectedPoint int
	 * @param key1 String
	 * @param key2 String
	 * @param key3 String
	 * @return boolean
	 */
	protected boolean protectedSendCoin(ActivityMember amc, int selectedPoint, String key1, String key2, String key3) {
		return privateSendCoin(amc.getMember_id(), selectedPoint, key1, key2, key3);
	}

	/**
	 * 发送库币并发送站内信，如果发送库币完成，则发送站内信
	 * @param member_id long
	 * @param selectedPoint int
	 * @param key1 String
	 * @param key2 String
	 * @param key3 String
	 * @return boolean
	 */
	private boolean privateSendCoin(long member_id, int selectedPoint, String key1, String key2, String key3) {
		if (selectedPoint == -1) return false;
		ProjectActivityItem f = pac.itemList.get(selectedPoint);
		if (f.getAngleCoin() <= 0) return false;
		boolean t = static_block_activity.cashCoinSelectActivity(pac, member_id, selectedPoint);
		if (t) privateSendMsg(member_id, key1, key2, key3);
		return t;
	}

	/**
	 * 发送站内信
	 * @param memberId long
	 * @param key1 String
	 * @param key2 String
	 * @param key3 String
	 * @return boolean
	 */
	private boolean privateSendMsg(long memberId, String key1, String key2, String key3) {
		String msgCode = pac.msgCoinCode;
		if (msgCode != null && msgCode.length() > 0) return static_block_activity.sendDiyParamSysMsg(memberId, msgCode, key1, key2, key3);
		return false;
	}

	/**
	 * 抽中奖后给管理人员发送邮件<br>
	 * titleArray:用于依次替换json中邮箱标题中的变量 {arg0}/{arg1}/{arg2}<br>
	 * @param titleArray String[]
	 * @param contentMap Map&lt;String,String>
	 * @return boolean
	 */
	@Deprecated
	protected final boolean protectedSendEmail(String[] titleArray, Map<String, String> contentMap) {
		if (!UtilsEmail.isSendServiceEmail(pac)) return false;
		/* 发送邮件 */
		String emailTitle = UtilsEmail.getServiceEmailTitle(pac, titleArray);
		String[] address = pac.emailService.getAddress();
		String emailContent = UtilsEmail.getEmailContent(contentMap);
		return static_block_activity.sendHtmlMailbool("1", emailTitle, emailContent, address);
	}

	/**
	 * 得到随机数，如果项目不正确或没有抽奖机会，则返回-1
	 * @param amc ActivityMember
	 * @return int
	 */
	protected int protectedGetSelectedPoint(ActivityMember amc) {
		/* 判断抽奖项目与会员的项目编号是否完整 */
		if (pac == null || pac.getTargetid() != targetid) return -1;
		/* 得到会员抽奖次数 */
		int luckAmount = amc.getLuckAmount();
		/* 如果抽奖次数小于0，则没有抽奖机会 */
		if (luckAmount <= 0) return -1;
		/* 随机得到抽奖项目条目 */
		return UtilsProjectActivity.getSelectRndByangle(pac.getItemList());
	}
	/**
	 * 是否向中奖会员发送邮件
	 * @return boolean
	 */
	protected boolean isSendMemberEmail() {
		return UtilsEmail.isSendMemberEmail(pac);
	}
	/**
	 * 向中奖会员发送邮件
	 * @param pac ProjectActivityClass
	 * @param loginMap Map&lt;Object,Object&gt;
	 * @param titleArray String[]
	 * @param contentArray String[]
	 * @return boolean
	 */
	protected boolean sendEmailMember(final ProjectActivityClass pac, final Map<Object, Object> loginMap, final String[] titleArray, final String[] contentArray) {
		return static_block_activity.sendEmailMember(pac, loginMap, titleArray, contentArray);
	}
	/**
	 * 是否向管理员发送邮件
	 * @return boolean
	 */
	protected boolean isSendServiceEmail() {
		return UtilsEmail.isSendServiceEmail(pac);
	}

	/**
	 * 向管理员发送中奖会员的信息邮件
	 * @param pac ProjectActivityClass
	 * @param loginMap Map&lt;Object,Object&gt;
	 * @param titleArray String[]
	 * @param contentArray String[]
	 * @return boolean
	 */
	protected boolean sendEmailService(final ProjectActivityClass pac, final Map<Object, Object> loginMap, final String[] titleArray, final String[] contentArray) {
		return static_block_activity.sendEmailService(pac, loginMap, titleArray, contentArray);
	}
	/**
	 * 通过selectedPoint得到中奖项目
	 * @param selectedPoint int
	 * @return ProjectActivityItem
	 */
	protected ProjectActivityItem getActivityItem(int selectedPoint) {
		if (pac == null) return null;
		List<ProjectActivityItem> list = pac.getItemList();
		if (list == null) return null;
		if (selectedPoint < 0 || selectedPoint >= list.size()) return null;
		ProjectActivityItem e = list.get(selectedPoint);
		return e;
	}
}
