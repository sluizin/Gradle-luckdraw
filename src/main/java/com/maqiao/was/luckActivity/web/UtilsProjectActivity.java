/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.util.List;

/**
 * 奖项的抽中一些静态方法
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class UtilsProjectActivity {

	/**
	 * 随机选中，按比例
	 * @param targetid int
	 * @return int
	 */
	static final int getSelectRndByangle(int targetid) {
		if (targetid <= 0) return -1;
		AbstractActivityProject f = Project.getAbstractActivityProject(targetid);
		return getSelectRndByangle(f);
	}

	/**
	 * 随机选中，按比例<br>
	 * @param f AbstractActivityProject
	 * @return int
	 */
	static final int getSelectRndByangle(AbstractActivityProject f) {
		if (f == null) return -1;
		return getSelectRndByangle(f.pac.itemList);
	}

	/**
	 * 随机选中，按比例(activity_luckdrawitemlist,0)<br>
	 * @param list List&lt;ProjectActivityItem&gt;
	 * @return int
	 */
	static final int getSelectRndByangle(List<ProjectActivityItem> list) {
		final int len = list.size();
		if (len == 0) return -1;
		int point = 0, i, sort = 0;
		sort = getActivityItemAngleSort(list);
		if (sort == 0) return -1;
		int selectValue = UtilsRnd.getRndInt(1, sort);
		point = len - 1;
		for (i = sort = 0; i < len; i++)
			if (selectValue <= (sort += list.get(i).getValue())) return i;
		return point;
	}

	/**
	 * 得到奖项列表概率总和
	 * @param list List&lt;ProjectActivityItem&gt;
	 * @return int
	 */
	static final int getActivityItemAngleSort(List<ProjectActivityItem> list) {
		final int len = list.size();
		if (len == 0) return 0;
		int sort = 0;
		for (int i = 0; i < len; i++)
			sort += list.get(i).getValue();
		return sort;
	}

	/**
	 * 得到某个奖项的百分比，保留2位小数
	 * @param list List&lt;ProjectActivityItem&gt;
	 * @param selectedpoint int
	 * @return String
	 */
	static final String getActivityItemAnglePercent(List<ProjectActivityItem> list, int selectedpoint) {
		final int len = list.size();
		if (len == 0 || selectedpoint < 0 || selectedpoint >= len) return "0.00%";
		int sort = getActivityItemAngleSort(list);
		if (sort <= 0) return "0.00%";
		int value = list.get(selectedpoint).angle;
		if (value <= 0) return "0.00%";
		return UtilsACC.getPercent(value, sort);
	}
}
