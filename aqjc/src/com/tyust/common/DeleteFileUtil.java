package com.tyust.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * 根据文件的名字到对应的文件夹下面删除对应的文件(需要借助commons-io.jar和ResourceUtil)
 * 
 * @author QiaoLiQiang
 * @time 2018年2月6日下午2:22:40
 */
public class DeleteFileUtil {
	/**
	 * 删除文件(因为一个文件可能存在pdf,doc,docx三种格式，因此需要删除)
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		if (fileName == null) {
			return false;
		}
		String dir = ResourcesUtil.getValue("path", "file");// 获取文件的基本目录
		String baseName = FilenameUtils.getBaseName(fileName);// 获取文件的基本名字
		try {
			FileUtils.forceDeleteOnExit(new File(dir + baseName + ".pdf"));
			FileUtils.forceDeleteOnExit(new File(dir + baseName + ".doc"));
			FileUtils.forceDeleteOnExit(new File(dir + baseName + ".docx"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 到存放图片的文件夹下面删除图片
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deletePicture(String fileName) {
		if (fileName == null) {
			return false;
		}
		String dir = ResourcesUtil.getValue("path", "picture");// 获取文件图片的基本目录
		try {
			FileUtils.forceDeleteOnExit(new File(dir + fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
		DeleteFileUtil.deleteFile("ef0c4d250561413e9777fb439e8fbc27.doc");
		System.out.println("sss");
	}
}
