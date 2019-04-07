package utils;

import java.util.UUID;

/**
 * @author caizx
 *�ļ��ϴ�������
 *����ϴ��ļ���������
 */
public class UploadUtils {
	//����Ψһ���ļ���
	public static String getUUIDFileName(String fileName) {
		//ȡ�ļ��ĺ�׺��
		int i = fileName.lastIndexOf(".");
		String extention  = fileName.substring(i);
		
		//��ȡ���UUID���������е�-ɾȥ
		String uuidFileName = UUID.randomUUID().toString().replaceAll("-", "")+extention;
		return uuidFileName;
	}
	
	public static void main(String[] args) {
		System.out.println(getUUIDFileName("aaa.jpg"));
	}
}
