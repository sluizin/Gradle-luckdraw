/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 抽奖系统测试Controller<br>
 * <br>
 * 对某个抽奖项目进行随机次数检测<br>
 * /luckactivityTest/getRndResult/7/1000<br>
 * <br>
 * <br>
 * <br>
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Controller
/* @RestController */
@RequestMapping(value = "/luckactivityTest")
public class Controller_Activity_Test {
	@Autowired
	Project luckActivityProject;

	@Autowired
	Static_block_Activity static_block_activity;

	/**
	 * 初始化
	 */
	@ModelAttribute
	private void init() {
		luckActivityProject.init();
	}
	/**
	 * 得到某个项目的随机中奖概率与原中奖概率进行比较
	 * @param targetid int
	 * @param num int
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/getRndResult/{targetid:[\\d]+}/{num:[\\d]+}", produces = "text/html;charset=UTF-8")
	public String getRndResult(@PathVariable int targetid, @PathVariable long num) {
		AbstractActivityProject f = Project.getAbstractActivityProject(targetid);
		if (f == null) return "No items found";
		List<ProjectActivityItem> itemList = f.pac.itemList;
		final int len = itemList.size();
		if (len == 0) return "No items found";
		if (num <= 0) return "num Must be greater than zero";
		List<Long> resultList = new ArrayList<Long>(len);
		/* 按奖项数量进行填充，填充结果list */
		for (int i = 0; i < len; i++)
			resultList.add(0L);
		int selectedPoint;
		for (long i = 0; i < num; i++) {
			selectedPoint = UtilsProjectActivity.getSelectRndByangle(itemList);
			long result = resultList.get(selectedPoint);
			resultList.set(selectedPoint, result + 1);
		}
		return list2StringBuilder(f.toString(), f.pac.itemList, resultList, num).toString();

	}

	/**
	 * 把结果list，按照奖项转成table用于输出
	 * @param title String
	 * @param itemList List&lt;ProjectActivityItem&gt;
	 * @param resultList List&lt;Long&gt;
	 * @param num long
	 * @return StringBuilder
	 */
	private StringBuilder list2StringBuilder(String title, List<ProjectActivityItem> itemList, List<Long> resultList, final long num) {
		final int len = resultList.size();
		StringBuilder sb = new StringBuilder();
		sb.append("<table border='1' width='100%'>");
		sb.append("<tr><td colspan='6'>" + title + "</td></tr>");
		sb.append("<tr>");
		sb.append("<td width='80' align='center'>编号</td>");
		sb.append("<td width='160' align='center'>奖品名称</td>");
		sb.append("<td width='160' align='center'>系统概率</td>");
		sb.append("<td width='160' align='center'>实际次数[" + num + "]</td>");
		sb.append("<td width='160' align='center'>测试概率</td>");
		sb.append("</tr>");
		for (int i = 0; i < len; i++) {
			ProjectActivityItem e = itemList.get(i);
			sb.append("<tr>");
			sb.append("<td align='center'>" + e.i + "</td>");
			sb.append("<td align='center'>" + e.title + "</td>");
			sb.append("<td align='center'>" + UtilsProjectActivity.getActivityItemAnglePercent(itemList, i) + "</td>");
			sb.append("<td align='center'>" + resultList.get(i) + "</td>");
			sb.append("<td align='center'>" + UtilsACC.getPercent(resultList.get(i), num) + "</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb;
	}
}
