/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 资源文件的读取工作
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public final class UtilsFile {

	/**
	 * 通过RandomAccessFile读文件 按行读 randomFile.readLine<br>
	 * 是否过滤#右侧数据
	 * @param filenamepath String
	 * @param enterStr String
	 * @param delnotes boolean
	 * @return StringBuilder
	 */
	static final StringBuilder readFile(String filenamepath, String enterStr, boolean delnotes) {
		StringBuilder sb = new StringBuilder(400);
		File file = new File(filenamepath);
		boolean bool = file.exists();
		if (!bool) return sb;
		try (RandomAccessFile randomFile = new RandomAccessFile(file, "r"); FileChannel filechannel = randomFile.getChannel();) {
			randomFile.seek(0);
			FileLock lock;
			do {
				lock = filechannel.tryLock(0L, Long.MAX_VALUE, true);
			} while (null == lock);
			Thread.sleep(10);
			while (randomFile.getFilePointer() < randomFile.length()) {
				String str = changedLine(randomFile.readLine());
				if (str != null && delnotes && (str = str.trim()).indexOf('#') >= 0) str = str.substring(0, str.indexOf('#'));
				if (str == null || str.length() == 0) continue;
				sb.append(str);
				if (randomFile.getFilePointer() < randomFile.length()) sb.append(enterStr);
			}
			lock.release();
		} catch (Exception e) {
			LuckActivityLogger.logger.error(e);
		}
		return sb;
	}

	/**
	 * RandomAccessFile RandomAccessFile读出时，转换成UTF-8
	 * @param line String
	 * @return String
	 */
	static final String changedLine(final String line) {
		if (line == null) return null;
		byte buf[] = new byte[1];
		byte[] byteArray = new byte[line.length()];
		int character, i = 0;
		StringReader aStringReader = new StringReader(line);
		try {
			while ((character = aStringReader.read()) != -1)
				byteArray[i++] = buf[0] = (byte) character;
			return new String(byteArray, "UTF-8");
		} catch (IOException e) {
			LuckActivityLogger.logger.error(e);
		}
		return null;
	}

}
