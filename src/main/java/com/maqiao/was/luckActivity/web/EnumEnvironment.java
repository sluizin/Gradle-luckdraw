/**
 * 
 */
package com.maqiao.was.luckActivity.web;

/**
 * 开发环境枚举 dev/test/online
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public enum EnumEnvironment {
	/** 本地开发环境 */
	DEV(0, "本地开发", "dev", "192.168.1.98", "cn", "http://192.168.1.98:90/static/zhuanti/", "sunjian@99114.com"),
	/** 测试环境 */
	TEST(1, "测试环境", "test", "zhuanti.99114.cn", "cn", "http://static.99114.cn/static/zhuanti/", "test.99114.com"),
	/** 线上环境 */
	ONLINE(2, "线上环境", "online", "zhuanti.99114.com", "com", "http://static.99114.com/static/zhuanti/", "");
	/** 编号 */
	int value;
	/** 中文名称 */
	String name;
	/** 关键字:dev/test/online */
	String key;
	/** 测试网址"192.168.1.98"/"zhuanti.99114.cn"/"zhuanti.99114.com" */
	String mainUrl;
	/** 域名后缀 cn/com */
	String navSuffix;
	/** 静态路径 http://192.168.1.98:90/static/zhuanti/ */
	String staticPath;
	/** 邮件 */
	String email;
	/**
	 * 构造函数
	 * @param value int
	 * @param name String
	 * @param key String
	 * @param mainUrl String
	 * @param navSuffix String
	 * @param staticPath String
	 * @param email String
	 */
	EnumEnvironment(int value, String name, String key, String mainUrl, String navSuffix, String staticPath, String email) {
		this.value = value;
		this.name = name;
		this.key = key;
		this.mainUrl = mainUrl;
		this.navSuffix = navSuffix;
		this.staticPath = staticPath;
		this.email = email;
	}

	/**
	 * 测试url中是否含有域名的mainUrl
	 * @param url String
	 * @return EnumEnvironment
	 */
	public final EnumEnvironment test(final String url) {
		for (EnumEnvironment s : EnumEnvironment.values())
			if (url.indexOf(s.mainUrl) > -1) return s;
		return DEV;
	}

	/**
	 * 得到sj_sourceBasicPath<br>
	 * http://192.168.1.98:90/static/zhuanti/luckactivity
	 * @return String
	 */
	public final String getSj_sourceBasicPath() {
		return staticPath + LuckActivityConsts.ACC_PROJECTCATALOG;
	}

	/**
	 * 测试dev/test/online key值
	 * @param key String
	 * @return EnumEnvironment
	 */
	public final EnumEnvironment testENVKey(String key) {
		if (key == null) return null;
		key = key.toLowerCase();
		for (EnumEnvironment s : EnumEnvironment.values())
			if (key.equals(s.key.toLowerCase())) return s;
		return DEV;
	}

	public final int getValue() {
		return value;
	}

	public final void setValue(int value) {
		this.value = value;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getKey() {
		return key;
	}

	public final void setKey(String key) {
		this.key = key;
	}

	public final String getMainUrl() {
		return mainUrl;
	}

	public final void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public final String getNavSuffix() {
		return navSuffix;
	}

	public final void setNavSuffix(String navSuffix) {
		this.navSuffix = navSuffix;
	}

	public final String getStaticPath() {
		return staticPath;
	}

	public final void setStaticPath(String staticPath) {
		this.staticPath = staticPath;
	}

}
