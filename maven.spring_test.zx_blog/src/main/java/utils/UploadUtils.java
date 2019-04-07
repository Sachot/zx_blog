package utils;

import java.util.UUID;

/**
 * @author caizx
 *文件上传工具类
 *解决上传文件重名问题
 */
public class UploadUtils {
	//生成唯一的文件名
	public static String getUUIDFileName(String fileName) {
		//取文件的后缀名
		int i = fileName.lastIndexOf(".");
		String extention  = fileName.substring(i);
		
		//获取随机UUID，并把其中的-删去
		String uuidFileName = UUID.randomUUID().toString().replaceAll("-", "")+extention;
		return uuidFileName;
	}
	
	public static void main(String[] args) {
		System.out.println(getUUIDFileName("aaa.jpg"));
	}
}
