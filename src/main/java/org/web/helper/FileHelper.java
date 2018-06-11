package org.web.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FileHelper {

	public static void executeFile(List<String> directoryList, List<String> fileList, String folderName, Boolean onlyCurrent) {
		File file = new File(folderName);
		if (file.exists()) {
			if (file.isFile()) {
				fileList.add(file.getAbsolutePath());
			} else if (file.isDirectory()) {
				directoryList.add(file.getAbsolutePath());
				String[] list = file.list();
				int fileNum = file.list().length;
				for (int i = 0; i < fileNum; i++) {
					String separator = System.getProperty("file.separator");
					File tempFile = new File(file.getAbsolutePath() + separator + list[i]);
					if (onlyCurrent) {
						executeFile(directoryList, fileList, tempFile.getAbsolutePath(), false);
					} else if (tempFile.isFile()) {
						fileList.add(tempFile.getAbsolutePath());
					} else {
						directoryList.add(tempFile.getAbsolutePath());
					}
				}
			}
		}
	}

	public static List<String> getAllFile(String folderName) {
		List<String> directoryList = new ArrayList<String>();
		List<String> fileList = new ArrayList<String>();
		executeFile(directoryList, fileList, folderName, false);
		return fileList;
	}

	public static List<String> getAllFolder(String folderName) {
		List<String> directoryList = new ArrayList<String>();
		List<String> fileList = new ArrayList<String>();
		executeFile(directoryList, fileList, folderName, false);
		return directoryList;
	}

	public static List<String> getCurrentFile(String folderName) {
		List<String> directoryList = new ArrayList<String>();
		List<String> fileList = new ArrayList<String>();
		executeFile(directoryList, fileList, folderName, true);
		return fileList;
	}

	public static List<String> getCurrentFolder(String folderName) {
		List<String> directoryList = new ArrayList<String>();
		List<String> fileList = new ArrayList<String>();
		executeFile(directoryList, fileList, folderName, true);
		return directoryList;
	}
	
	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(is, "utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line).append("\n");
		}
		return buffer.toString();
	}
	
	

	public static String readFile(File file) throws IOException {
		final int COUNT_PER_READ = 512;
		BufferedReader in = null;
		try {
			final long filesize = file.length();

			in = new BufferedReader(new FileReader(file));

			StringBuffer strbuf = new StringBuffer((int) filesize);
			char[] chrbuf = new char[COUNT_PER_READ];
			int len;

			while ((len = in.read(chrbuf, 0, COUNT_PER_READ)) == COUNT_PER_READ) {
				strbuf.append(chrbuf);
			}

			if (len != -1) {
				strbuf.append(chrbuf, 0, len);
			}
			return strbuf.toString();
		} catch (IOException e) {
			throw e;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
