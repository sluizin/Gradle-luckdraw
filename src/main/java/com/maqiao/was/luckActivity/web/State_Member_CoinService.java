/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wangku.was.member.dubbo.IRDubboMemberCoinService;

/**
 * 集合库币操作[中库币奖]
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Service(value = "state_Member_CoinService")
public class State_Member_CoinService {
	@Autowired
	private IRDubboMemberCoinService iRDubboMemberCoinService;

	/**
	 * 终极发送库币
	 * @param member_id long
	 * @param value int
	 * @param arg2 String
	 * @param arg3 String
	 * @return boolean
	 */
	final boolean sendCoin(final long member_id, final int value, String arg2, String arg3) {
		try {
			BigDecimal coinNum = new BigDecimal(value);
			iRDubboMemberCoinService.saveCoin(member_id, coinNum, arg2, arg3, 6, 4);
			return true;
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
		}
		return false;
	}
}
