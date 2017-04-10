/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.lang.reflect.Field;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Unsafe;

/**
 * 常量池
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class LuckActivityConsts {
	/** 程序运行环境 */
	static EnumEnvironment ENV = EnumEnvironment.DEV;
	/** 配置资源文件所在的目录 */
	static final String ACC_SOURCEPATH = "/template/ACCzhuanti/LuckActivity/";
	/** freemarker的html文件目录 */
	static final String ACC_SOURCEHTTPPATH = "/ACCzhuanti/LuckActivity/";
	/** 配置资源文件的文件名 */
	static final String ACC_SOURCEFILENAME = "activityDB.json";
	/** 静态服务器上的总系统目录 */
	static final String ACC_PROJECTCATALOG = "luckactivity";
	/** 是否启用特殊会员 */
	static final boolean ACC_USESPECIALMEMBER = true;
	/** 随机企业编号最小值 用于随机中奖企业的调取 */
	static final int ACC_RNDMEMBERIDMIN = 1;
	/** 随机企业编号最大值 用于随机中奖企业的调取 */
	static final int ACC_RNDMEMBERIDMAX = 47931802;
	/** unsafe对象 */
	static final Unsafe UNSAFE;
	/**
	 * ModelAndView对象中ModelMap地址偏移量
	 */
	static long ACC_OffsetModelAndView_ModelMap = 0L;
	static {
		try {
			final Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			UNSAFE = (Unsafe) field.get(null);
			ACC_OffsetModelAndView_ModelMap = UNSAFE.objectFieldOffset(ModelAndView.class.getDeclaredField("model"));/* 得到ModelAndView对象中model的偏移量 */
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
			throw new RuntimeException(e);
		}
	}

}
