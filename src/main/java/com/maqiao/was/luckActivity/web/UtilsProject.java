/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 项目工程的检索工作
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class UtilsProject {

	/**
	 * 从多个包路径下检索项目工程
	 * @param packArrays String[]
	 * @return List&lt;AbstractActivityProject&gt;
	 */
	static final List<AbstractActivityProject> getAbstractList(String... packArrays) {
		List<AbstractActivityProject> list = new ArrayList<AbstractActivityProject>(2);
		String path = "";
		for (int i = 0, len = packArrays.length; i < len; i++) {
			path = packArrays[i];
			if (path == null) continue;
			path = path.trim().replace('/', '.');
			if (path.length() == 0) continue;
			list.addAll(getAbstractActivityProjectList(path));
		}
		return list;
	}

	/**
	 * 从AbstractActivityProject类的目录下的Project.luckProjectPackName目录下检索项目工程
	 * @return List&lt;AbstractActivityProject&gt;
	 */
	static final List<AbstractActivityProject> getAbstractList() {
		String thisPackagePath = AbstractActivityProject.class.getPackage().getName();
		String targetPackagePath = thisPackagePath + ".luckProject";
		return getAbstractActivityProjectList(targetPackagePath);
	}

	/**
	 * 从包路径下得到 AbstractActivityProject对象集并进行类名的判断
	 * @param packName String
	 * @return List&lt;AbstractActivityProject&gt;
	 */
	private static final List<AbstractActivityProject> getAbstractActivityProjectList(String packName) {
		List<AbstractActivityProject> list = new ArrayList<AbstractActivityProject>();
		List<Class<?>> listall = getClasses(packName);
		AbstractActivityProject e = null;
		for (int i = 0, len = listall.size(); i < len; i++)
			if ((e = getAbstractActivityProject(listall.get(i))) != null) list.add(e);
		return list;
	}

	/**
	 * 把一个对象转成项目，并进行赋值，如果不正确，则返回null
	 * @param classzz Class&lt;?&gt;
	 * @return AbstractActivityProject
	 */
	private static final AbstractActivityProject getAbstractActivityProject(Class<?> classzz) {
		try {
			if (AbstractActivityProject.class.isAssignableFrom(classzz)) {
				int constructorsType = getConstructorsType(classzz);
				/* 第一种方式判断 无 构造函数[查文件名] 因在抽象类中定义了构造函数，所以此方法丢弃 */
				if (constructorsType == 0) {
					AbstractActivityProject e = (AbstractActivityProject) classzz.newInstance();
					/* 构造方式：无 构造函数[查文件名] */
					if (isSplitClassFileName(e)) return e;
				}
				/* 第二种方式判断 有 无参数构造函数 显性赋值 */
				if (UtilsACC.shift(constructorsType, 1)) {
					AbstractActivityProject e = getObjToProject_Constructors1(classzz);
					/* 构造方式：有 无参数构造函数 显性赋值 */
					if (e != null) return e;
				}
				/* 第三种方式判断 有 双参数构造函数 */
				if (UtilsACC.shift(constructorsType, 2)) {
					/* 显性赋值 */
					AbstractActivityProject e = getObjToProject_Constructors2(classzz);
					/* 构造方式：有 双参数构造函数 显性赋值 */
					if (isSafeAbstractActivityProject(e)) return e;
					/* 隐性赋值 */
					/* 构造方式：有 双参数构造函数 隐性赋值 */
					if (e != null && isSplitClassFileName(e)) return e;
				}
			}
		} catch (Exception e1) {
			LuckActivityLogger.logger.error(e1);
		}
		return null;
	}

	/**
	 * 得到某个类有多个构造函数<br>
	 * 返回:<br>
	 * 0：无构造函数<br>
	 * 1：有无参数构造函数<br>
	 * 2：有有参数构造函数(int,String)<br>
	 * @param classzz Class&lt;?&gt;
	 * @return int
	 */
	static final int getConstructorsType(Class<?> classzz) {
		Constructor<?>[] array = classzz.getDeclaredConstructors();
		int result = 0;
		for (int i = 0, count = 0, len = array.length; i < len; i++) {
			Constructor<?> con = array[i];
			count = con.getParameterCount();
			if (count == 0) result |= 1;
			if (count == 2) {
				Class<?> clazzs[] = con.getParameterTypes();
				if (clazzs[0] == int.class && clazzs[1] == String.class) result |= 2;
			}
		}
		return result;
	}

	/**
	 * 有参数构造函数(int,String)，显性赋值构造<br>
	 * 生成时使用构造参数(0,"")
	 * @param classzz Class&lt;?&gt;
	 * @return AbstractActivityProject
	 */
	private static final AbstractActivityProject getObjToProject_Constructors2(Class<?> classzz) {
		if (!UtilsACC.shift(getConstructorsType(classzz), 2)) return null;
		try {
			/* 以下调用带参的、私有构造函数 */
			Constructor<?> constructor = classzz.getDeclaredConstructor(new Class[] { int.class, String.class });
			constructor.setAccessible(true);
			AbstractActivityProject e = (AbstractActivityProject) constructor.newInstance(new Object[] { 0, "" });
			return e;
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
		}
		return null;
	}

	/**
	 * 有 无参数构造函数，显性赋值构造<br>
	 * @param classzz Class&lt;?&gt;
	 * @return AbstractActivityProject
	 */
	private static final AbstractActivityProject getObjToProject_Constructors1(Class<?> classzz) {
		if (!UtilsACC.shift(getConstructorsType(classzz), 1)) return null;
		try {
			/* 以下调用无参的、私有构造函数 */
			Constructor<?> c0 = classzz.getDeclaredConstructor();
			c0.setAccessible(true);
			AbstractActivityProject e = (AbstractActivityProject) c0.newInstance();
			if (isSafeAbstractActivityProject(e)) return e;
			return null;
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
		}
		return null;
	}

	/**
	 * 判断此项目是否安全的项目
	 * @param e AbstractActivityProject
	 * @return boolean
	 */
	static final boolean isSafeAbstractActivityProject(AbstractActivityProject e) {
		if (e.targetid != 0 && e.projectName != null && e.projectName.length() > 0) return true;
		return false;
	}

	/**
	 * 对类名进分解，并进行二次赋值<br>
	 * 对类名进行判断得到targetid和projectName<br>
	 * 例：Project_6_vibrationluck<br>
	 * targetid:6<br>
	 * projectName:vibrationluck
	 * @param e AbstractActivityProject
	 * @return boolean
	 */
	private static final boolean isSplitClassFileName(AbstractActivityProject e) {
		String fileName = e.getClass().getSimpleName();
		String[] fileSplit = fileName.split("_");
		if (fileSplit.length != 3) return false;
		if (!fileSplit[0].equals(Project.ACC_PROJECTNAMEHEAD)) return false;
		String targetStr = fileSplit[1];
		if (!UtilsACC.isNumeric(targetStr)) return false;
		if (fileSplit[2].length() == 0) return false;
		e.targetid = Integer.parseInt(targetStr);
		e.projectName = fileSplit[2].trim();
		return isSafeAbstractActivityProject(e);
	}

	/**
	 * 判断 AbstractActivityProject 对象，按文件名 头部_targetid_projectName 组成<br>
	 * 文件名正确，则把targetid,projectName保存到对象中<br>
	 * 如果文件名不正确，则返回null<br>
	 * @param e AbstractActivityProject
	 * @return boolean
	 */
	@Deprecated
	static final boolean isAbstractActivityProject(AbstractActivityProject e) {
		/* 判断第一种方式 通过调用无参数构造函数进行了赋值 */
		if (e.targetid != 0 && (e.projectName != null && e.projectName.length() > 0)) return true;
		/* 对类名进行判断 规则： Project_7_registrationaward ，如果规则不正确，则返回null */
		return isSplitClassFileName(e);
	}

	/**
	 * 从包package中获取所有的Class
	 * @param packageName String
	 * @return List&lt;Class&lt;?&gt;&gt;
	 */
	private static final List<Class<?>> getClasses(String packageName) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		boolean recursive = true;
		String packageDirName = packageName.replace('.', '/');
		try {
			Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				searchFromFile(classes, url, recursive, packageName);
				searchFromJar(classes, url, recursive, packageName, packageDirName);
			}
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
		}
		return classes;
	}

	/**
	 * 从file文件中得到类
	 * @param classes List&lt;Class&lt;?&gt;&gt;
	 * @param url URL
	 * @param recursive boolean
	 * @param packageName Stkring
	 */
	private static final void searchFromFile(List<Class<?>> classes, URL url, boolean recursive, String packageName) {
		if (url == null) { return; }
		String protocol = url.getProtocol();
		if (!"file".equals(protocol)) { return; }
		try {
			String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
			findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
		}

	}

	/**
	 * 从jar文件中得到类
	 * @param classes List&lt;Class&lt;?&gt;&gt;
	 * @param url URL
	 * @param recursive boolean
	 * @param packageName Stkring
	 * @param packageDirName String
	 */
	private static final void searchFromJar(List<Class<?>> classes, URL url, boolean recursive, String packageName, String packageDirName) {
		if (url == null) { return; }
		String protocol = url.getProtocol();
		if (!"jar".equals(protocol)) { return; }
		try {
			JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				String name = entry.getName();
				if (name.charAt(0) == '/') name = name.substring(1);
				if (name.startsWith(packageDirName)) {
					int idx = name.lastIndexOf('/');
					if (idx != -1) packageName = name.substring(0, idx).replace('/', '.');
					if (((idx != -1) || recursive) && ((name.endsWith(".class") && !entry.isDirectory()))) {
						String className = name.substring(packageName.length() + 1, name.length() - 6);
						classes.add(Class.forName(packageName + '.' + className));
					}
				}
			}
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
		}
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * @param packageName String
	 * @param packagePath String
	 * @param recursive boolean
	 * @param classes List&lt;Class&lt;?&gt;&gt;
	 */
	private static final void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes) {
		File dir = new File(packagePath);
		if (!dir.exists() || !dir.isDirectory()) { return; }
		File[] dirfiles = dir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		try {
			for (File file : dirfiles) {
				if (file.isDirectory()) {
					findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
					continue;
				}
				String className = file.getName().substring(0, file.getName().length() - 6);
				classes.add(Class.forName(packageName + '.' + className));
			}
		} catch (ClassNotFoundException e) {
			LuckActivityLogger.logger.error(e);
		}
	}
}
