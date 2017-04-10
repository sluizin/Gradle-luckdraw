/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wangku.was.member.dubbo.IRDubboMemberRegisterService;
import com.wangku.was.membermsg.dubbo.IRDubboMemberMsgcenterService;
import com.maqiao.was.luckActivity.service.activity.model.Activity;
import com.maqiao.was.luckActivity.service.activity.service.IActivityService;
import com.wangku.wjf.message.sender.CustomMessageSender;

/**
 * 静态化通用对象
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Service(value = "static_block_activity")
public class Static_block_Activity {
	@Autowired
	private IActivityService activityService;
	@Autowired
	protected IRDubboMemberMsgcenterService iRDubboMsgMemberService;
	@Autowired
	private IRDubboMemberRegisterService iRDubboMemberRegisterService;
	@Autowired
	public State_Member state_Member;

	@Autowired
	CustomMessageSender supplySender;

	/**
	 * 中奖mybatis需要检测的参数汇集<br/>
	 * 变量名，变量类型一一对应<br/>
	 * 如不输入则使用 0/-1/null/0L 进行填充<br>
	 * @deprecated 暂时不使用
	 * @param targetid int
	 * @param state int
	 * @param dateStart String
	 * @param dateEnd String
	 * @param member_id long
	 * @param num int
	 * @return Map&lt;String, Object&gt;
	 */
	@Deprecated
	public static final Map<String, Object> getMybatisMap(int targetid, int state, String dateStart, String dateEnd, long member_id, int num) {
		/* getMap(targetid, state, dateStart, dateEnd,member_id , num) */
		Map<String, Object> map = new HashMap<String, Object>(6);
		if (targetid > 0) map.put("targetid", targetid);
		if (state > 0) map.put("state", state);
		if (dateStart != null) map.put("date1", dateStart);
		if (dateEnd != null) map.put("date2", dateEnd);
		if (member_id > 0L) map.put("member_id", member_id);
		if (num > 0) map.put("num", num);
		return map;
	}

	/**
	 * state<br>
	 * -1:ERROR<br>
	 * 0:未登陆<br>
	 * 1:个人-活动期间[71]<br>
	 * 2:个人-过期[71]<br>
	 * 3:个人会员-活动期间[83]<br>
	 * 4:个人会员-过期[83]<br>
	 * 5:免费会员-活动期间[81]<br>
	 * 6:免费会员-过期[81]<br>
	 * 7:单品通会员-活动期间[84]<br>
	 * 8:单品通会员-过期[84]<br>
	 * 9:行业通-活动期间[87]<br>
	 * 10:行业通-过期[87]<br>
	 * @param pac ProjectActivityClass
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param amc ActivityMemberClass
	 */
	public void getActivityMemberState(final ProjectActivityClass pac, final Map<Object, Object> loginMap, ActivityMember amc) {
		if (pac == null) {
			amc.memberState = EnumMemberState.Error;
			return;
		}
		if (pac.openProjectAmount) amc.projectAmount = getCountActivity(pac.targetid) + pac.sortBase;
		if (loginMap == null || loginMap.size() == 0) {
			amc.memberState = EnumMemberState.UnLogin;
			return;
		}
		long memberid = state_Member.getMemberId(loginMap);
		if (memberid == 0L) {
			amc.memberState = EnumMemberState.UnLogin;
			return;
		}
		amc.member_id = memberid;
		String roleId = state_Member.getRoleId(loginMap);
		String account = state_Member.getAccount(loginMap);
		String addTime = state_Member.getAddTime(loginMap);
		EnumMemberState e = EnumMemberState.getEnumBySpecialMember(account);
		amc.memberState = (e != null) ? e : EnumMemberState.getEnumByRoldId(roleId, addTime, pac.dateStart, pac.dateEnd);
		/* 计算出已抽奖次数，有记录的数量 */
		amc.luckSelected = (int) getMemberNumberActivity(pac.targetid, memberid, 1);
		if (pac.openSubCount1) amc.subCount1 = getSubmemberCount(pac, memberid, 1);
		if (pac.openSubCount2) amc.subCount2 = getSubmemberCount(pac, memberid, 2);
	}

	/**
	 * 得到此会员有多少子会员，有抽奖类活动期间
	 * @param pac ProjectActivityClass
	 * @param member_id long
	 * @param state int
	 * @return int
	 */
	public int getSubmemberCount(final ProjectActivityClass pac, final long member_id, int state) {
		if (pac == null) return 0;
		/* 结束日期+1天 */
		Date dateend = new Date(pac.getDate(1).getTime() + (long) 1 * 24 * 60 * 60 * 1000);
		return iRDubboMemberRegisterService.getMemberInviteRecord(member_id, state, pac.getDate(0), dateend);
	}

	/**
	 * 得到抽奖活动中指定会员的数量
	 * @param targetid int
	 * @param member_id long
	 * @param state int
	 * @return long
	 */
	public long getMemberNumberActivity(final int targetid, final long member_id, final int state) {
		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put("targetid", targetid);
		map.put("member_id", member_id);
		map.put("state", state);
		return activityService.getMemberNumber(map);
	}

	/**
	 * 得到某个项目的中奖总数
	 * @param f AbstractActivityProject
	 * @param state int
	 * @return long
	 */
	public long getActivityCount(final AbstractActivityProject f, int state) {
		if (f == null) return 0L;
		return getActivityCountByTargetid(f.targetid, state, f.pac.dateStart, f.pac.dateEnd);
	}

	/**
	 * 得到某个项目的中奖总数
	 * @param targetid int
	 * @param state int
	 * @param dateStart String
	 * @param dateEnd String
	 * @return long
	 */
	private long getActivityCountByTargetid(int targetid, int state, String dateStart, String dateEnd) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("targetid", targetid);
		map.put("state", state);
		map.put("date1", dateStart);
		map.put("date2", dateEnd);
		return activityService.getActivityCount(map);

	}

	/**
	 * 得到某个抽奖工程的所有记录(针对某个会员)
	 * @param f AbstractActivityProject
	 * @param member_id long
	 * @return List&lt;Activity&gt;
	 */
	public List<Activity> getActivityListByMember_id(final AbstractActivityProject f, long member_id) {
		int targetid = f.targetid;
		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put("targetid", targetid);
		map.put("member_id", member_id);
		map.put("state", 1);
		return activityService.getActivityHistory(map);
	}

	/**
	 * 得到某个抽奖工程的所有记录
	 * @param f AbstractActivityProject
	 * @return List&lt;Activity&gt;
	 */
	public List<Activity> getActivityListAAP(final AbstractActivityProject f) {
		List<Activity> historyList = new ArrayList<Activity>();
		if (f.pac.showListMax <= 0) return historyList;
		historyList = getActivityList(f, f.pac.showListMax, 1);
		if (!f.pac.rndAllow) return historyList;
		int count = historyList.size();
		if (count < f.pac.showListMax) {
			int len = f.pac.showListMax - count;
			historyList.addAll(f.histroyListRnd.subList(0, len - 1));
		}
		return historyList;
	}

	/**
	 * 得到某个抽奖工程的所有记录
	 * @param f AbstractActivityProject
	 * @param num int
	 * @param state int
	 * @return List&lt;Activity&gt;
	 */
	public List<Activity> getActivityList(final AbstractActivityProject f, int num, int state) {
		List<Activity> historyList = new ArrayList<Activity>();
		if (f == null) return historyList;
		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put("targetid", f.targetid);
		map.put("num", num);
		map.put("state", state);
		historyList = activityService.getHistory(map);
		return historyList;
	}

	/**
	 * 选中的结果是否有效
	 * @param f AbstractActivityProject
	 * @param selectedPoint int
	 * @return boolean
	 */
	public static boolean isSafeSelectPoint(AbstractActivityProject f, int selectedPoint) {
		if (f == null) return false;
		return (selectedPoint > -1 && f.pac.itemList.size() > selectedPoint);
	}

	/**
	 * 从资源文件[json]得到奖项
	 * @param f AbstractActivityProject
	 * @param jsonPathFile String
	 */
	static void initialization(AbstractActivityProject f, String jsonPathFile) {
		if (jsonPathFile == null) return;
		String str = UtilsFile.readFile(jsonPathFile, "", false).toString().trim();
		ProjectActivityClass e = JSON.parseObject(str, ProjectActivityClass.class);
		f.pac = e;
	}

	/**
	 * 调出剩余随机记录
	 * @param f AbstractActivityProject
	 */
	void initHistroyListRnd(AbstractActivityProject f) {
		if (!f.pac.rndAllow) return;
		int count = (int) getActivityCount(f, 1);
		if (count >= f.pac.showListMax) return;
		int len = f.pac.showListMax - count;
		if (len > 0) f.histroyListRnd = addRadmonActivity(f, len);
	}

	/**
	 * 设置多个随机单元。不进入数据库，只存在于表中
	 * @param f AbstractActivityProject
	 * @param len int
	 * @return List&lt;Activity&gt;
	 */
	public List<Activity> addRadmonActivity(AbstractActivityProject f, int len) {
		List<Activity> historyList = new ArrayList<Activity>();
		if (f == null || (!f.pac.rndAllow) || len <= 0) return historyList;
		for (int i = 0; i < len; i++) {
			/* 得到随机企业编号 */
			int member_id = UtilsRnd.getRndInt(LuckActivityConsts.ACC_RNDMEMBERIDMIN, LuckActivityConsts.ACC_RNDMEMBERIDMAX);
			String corporationName = state_Member.getCorporationName(member_id, 1);
			if (corporationName != null && corporationName.length() > 0) {
				Activity e = new Activity();
				e.setId(i);
				e.setMember_id(member_id);
				e.setTargetid(f.targetid);
				e.setTitle(corporationName);
				int selectedpoint = UtilsProjectActivity.getSelectRndByangle(f.pac.itemList);
				e.setSelectedpoint(selectedpoint);
				e.setSelectTitle(f.pac.itemList.get(selectedpoint).title);
				e.setState(1);
				e.setAdd_time(UtilsDate.getRndNowBefore(f.pac.rndListBeforeDay));
				historyList.add(e);
				continue;
			}
			i--;
		}
		return historyList;
	}

	/**
	 * 得到抽奖活动的总数
	 * @param targetid int
	 * @return long
	 */
	public long getCountActivity(int targetid) {
		return activityService.getCount(targetid);
	}

	/**
	 * 保存抽奖记录
	 * @param targetid int
	 * @param loginMap Map&lt;Object, Object&gt;
	 * @param selectedPoint int
	 * @return boolean
	 */
	public boolean saveActivity(int targetid, final Map<Object, Object> loginMap, int selectedPoint) {
		if (loginMap == null || loginMap.size() == 0) return false;
		Activity e = new Activity();
		long member_id = state_Member.getMemberId(loginMap);
		e.setTargetid(targetid);
		String corporationName = state_Member.getCorporationName(loginMap);
		e.setTitle(corporationName);
		e.setMember_id((int) member_id);
		e.setSelectedpoint(selectedPoint);
		AbstractActivityProject f = Project.getAbstractActivityProject(targetid);
		if (f == null) return false;
		EnumMemberState ems = state_Member.getEnumMemberState(loginMap, f.pac.dateStart, f.pac.dateEnd);
		e.setTitle2(ems.title);
		ProjectActivityItem ff = f.pac.itemList.get(selectedPoint);
		if (ff == null) return false;
		String selecttitle = ff.title_1;
		e.setSelecttitle(selecttitle);
		e.setState(1);
		return saveActivity(e);
	}

	/**
	 * 保存抽奖记录
	 * @param e Activity
	 * @return boolean
	 */
	public boolean saveActivity(final Activity e) {
		if (e == null) return false;
		return activityService.save(e);
	}

	/**
	 * 向会员发送站内信
	 * @param member_id long
	 * @param msgCode String
	 * @param key1 String
	 * @param key2 String
	 * @param key3 String
	 * @return boolean
	 */
	public boolean sendDiyParamSysMsg(long member_id, String msgCode, String key1, String key2, String key3) {
		String result = iRDubboMsgMemberService.sendDiyParamSysMsg(member_id, 1, key1, key2, key3, msgCode, 5);
		if (result == null) return false;
		return result.length() > 0;
	}

	/**
	 * 把库币存入帐号，并返回状态值
	 * @param pac ProjectActivityClass
	 * @param loginMap Map&lt;Object,Object&gt;
	 * @param selectedPoint int
	 * @return boolean
	 */
	public boolean cashCoinSelectActivity(final ProjectActivityClass pac, final Map<Object, Object> loginMap, final int selectedPoint) {
		long member_id = state_Member.getMemberId(loginMap);
		if (selectedPoint == -1) return false;
		ProjectActivityItem f = pac.itemList.get(selectedPoint);
		int value = f.angleCoin;
		if (value > 0) return sendCoin(pac, member_id, value);
		return false;
	}

	/**
	 * 把库币存入帐号，并返回状态值
	 * @param pac ProjectActivityClass
	 * @param member_id long
	 * @param selectedPoint int
	 * @return boolean
	 */
	public boolean cashCoinSelectActivity(final ProjectActivityClass pac, long member_id, final int selectedPoint) {
		if (selectedPoint == -1) return false;
		ProjectActivityItem f = pac.itemList.get(selectedPoint);
		int value = f.angleCoin;
		if (value > 0) return sendCoin(pac, member_id, value);
		return false;
	}

	/**
	 * 发送库币
	 * @param pac ProjectActivityClass
	 * @param member_id long
	 * @param value int
	 * @return boolean
	 */
	public final boolean sendCoin(final ProjectActivityClass pac, final long member_id, final int value) {
		return state_Member.sendCoin(member_id, value, pac.projectName, pac.title);
	}

	/**
	 * 判断会员状态在不在活动期间
	 * @param state int
	 * @return boolean
	 */
	@Deprecated
	public boolean isActivePeriod(int state) {
		return state % 2 == 1;
	}

	/**
	 * 邮件发送接口
	 * @param key String
	 * @param emailTitle String
	 * @param emailContent String
	 * @param address String[]
	 * @return String
	 */
	public String sendHtmlMail(String key, String emailTitle, String emailContent, String... address) {
		if (address == null || address.length == 0) return "fail";
		supplySender.sendMessageMail(emailContent, emailTitle, key, address);
		return "success";
	}

	/**
	 * 邮件发送接口
	 * @param key String
	 * @param emailTitle String
	 * @param emailContent String
	 * @param address String[]
	 * @return boolean
	 */
	public boolean sendHtmlMailbool(String key, String emailTitle, String emailContent, String... address) {
		String t = sendHtmlMail(emailContent, emailTitle, key, address);
		return ("success" == t || "success".equals(t));
	}

	/**
	 * 向管理员发送中奖会员的信息邮件
	 * @param pac ProjectActivityClass
	 * @param loginMap Map&lt;Object,Object&gt;
	 * @param titleArray String[]
	 * @param contentArray String[]
	 * @return boolean
	 */
	public boolean sendEmailService(final ProjectActivityClass pac, final Map<Object, Object> loginMap, final String[] titleArray, final String[] contentArray) {
		if (pac == null) return false;
		if (loginMap == null || loginMap.size() == 0) return false;
		ProjectActivityEmailService emailService = pac.getEmailService();
		if (emailService == null || (!emailService.isSafe())) return false;
		String[] eAdress = pac.emailService.getAddress();
		if (eAdress.length == 0) return false;
		String title = UtilsEmail.getStringReplaceToValue(emailService.getEmailTitle(), titleArray);
		String content = UtilsEmail.getStringReplaceToValue(emailService.getEmailContent(), contentArray);
		return sendHtmlMailbool("1", title, content, eAdress);
	}

	/**
	 * 向中奖会员发送邮件
	 * @param pac ProjectActivityClass
	 * @param loginMap Map&lt;Object,Object&gt;
	 * @param titleArray String[]
	 * @param contentArray String[]
	 * @return boolean
	 */
	boolean sendEmailMember(final ProjectActivityClass pac, final Map<Object, Object> loginMap, final String[] titleArray, final String[] contentArray) {
		if (pac == null) return false;
		if (loginMap == null || loginMap.size() == 0) return false;
		ProjectActivityEmailMember emailMember = pac.getEmailMember();
		if (emailMember == null || (!emailMember.isSafe())) return false;
		String email = getMemberEmail(loginMap);
		if (email == null || email.length() == 0) return false;
		String title = UtilsEmail.getStringReplaceToValue(emailMember.getEmailTitle(), titleArray);
		String content = UtilsEmail.getStringReplaceToValue(emailMember.getEmailContent(), contentArray);
		return sendHtmlMailbool("1", title, content, email);
	}

	/**
	 * 得到默认的会员地址
	 * @param loginMap Map&lt;Object,Object&gt;
	 * @return String
	 */
	private String getMemberEmail(final Map<Object, Object> loginMap) {
		if (LuckActivityConsts.ENV == EnumEnvironment.DEV || LuckActivityConsts.ENV == EnumEnvironment.TEST) return LuckActivityConsts.ENV.email;
		return state_Member.getEmail(loginMap);

	}

	/**
	 * 项目资格数量指定，依据会员状态得到资格数量
	 * @param quali ProjectActivityQualifications
	 * @param es EnumMemberState
	 * @return int
	 */
	public int getStateQualifications(ProjectActivityQualifications quali, EnumMemberState es) {
		if (quali == null) return -1;
		return quali.getNumber(es.value);

	}

	/**
	 * 项目资格数量指定，依据会员状态得到资格数量
	 * @param quali ProjectActivityQualifications
	 * @param state int
	 * @return int
	 */
	@Deprecated
	public int getStateQualifications(ProjectActivityQualifications quali, int state) {
		if (quali == null) return -1;
		return quali.getNumber(state);
	}

	/**
	 * 通过json得到会员抽奖次数，包括剩余次数
	 * @param amc ActivityMember
	 */
	public void initLuckSortOwnt(ProjectActivityClass pac, ActivityMember amc) {
		int luckSort = privateCheckOutQualificationsTime(pac, amc);
		luckSort = luckSort > -1 ? luckSort : 0;
		luckSort += pac.overflowTimes;
		/* 剩余机会 */
		if (amc.getLuckSelected() < luckSort) amc.setLuckAmount(luckSort - amc.getLuckSelected());
		else amc.setLuckAmount(0);
		amc.setLuckSort(luckSort);
	}

	/**
	 * 得到会员资格数量，不累加，只得基础值<br>
	 * -1:有错误
	 * @param amc ActivityMember
	 * @return int
	 */
	private int privateCheckOutQualificationsTime(ProjectActivityClass pac, ActivityMember amc) {
		if (amc == null) return -1;
		return getStateQualifications(pac.qualifications, amc.memberState);
	}
}
