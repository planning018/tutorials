package com.planning.io.oio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用FileOutputStream,FileInputStream 读写文件，以及复制文件
 * @author planning
 *
 */
public class FileOutputStreamDemo {
	
	/**
	 * 字符输出流  --- FileOutputStream
	 * @throws IOException
	 */
	@Test
	public void test5() throws IOException{
		FileOutputStream fos = new FileOutputStream("./test/test5.txt");
		fos.write("hello".getBytes());
		fos.close();
	}
	
	/**
	 * 字符输入流 ---FileInputStream
	 * @throws IOException
	 */
	@Test
	public void test6() throws IOException{
		FileInputStream fis = new FileInputStream("./test/test5.txt");
		byte[] by = new byte[1024];
		int len = 0;
		while((len = fis.read(by))!=-1){
			System.out.println(new String(by,0,len));
		}
	}
	
	/**
	 * 将项目下的test5.txt 使用字节流  复制到 test7.txt
	 * @throws IOException 
	 */
	@Test
	public void test7() throws IOException {
		FileInputStream fis = new FileInputStream("./test/test5.txt");
		FileOutputStream fos = new FileOutputStream("./test/text7.txt");
		
		//一次读取一个字节数组
		byte[] bys = new byte[1024];
		int len = 0;
		while((len = fis.read(bys))!=-1){
			fos.write(bys, 0, len);
		}
		
		//一次读取一个字符
		while((len = fis.read())!= -1){
			fos.write(len);
		}
		//结论：一次读取一个字节数组 效率更高
		
		fis.close();
		fos.close();
	}
		
}
