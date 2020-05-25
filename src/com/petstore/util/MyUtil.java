package com.petstore.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.Part;
public class MyUtil {
	/**
	 * Description 获取系统当前时间字符串
	 */
	public static String getStringID(){
		String id=null;
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		id=sdf.format(date);
		return id;
	}
	/**
	 * Description 从Part中获得原始文件名
	 */
	public static String getFileName(Part part){
		if(part == null)
			return null;
		//fileName形式为：form-data; name="resPath"; filename="20140920_110531.jpg"
		String fileName = part.getHeader("content-disposition");
		//没有选择文件
		if(fileName.lastIndexOf("=") + 2 == fileName.length() - 1)
			return null;
		return fileName.substring(fileName.lastIndexOf("=") + 2, fileName.length() - 1);
	}
}
