package com.planning.io.oio;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 本章主要学习了 BufferedReader,BufferedWriter,BufferedInputStream,BufferedOutputStream的使用
 *          以及BufferedReader,BufferedWriter的特有功能
 * @author planning
 *
 */
public class BufferWriterDemo {
	
	/**
	 * 使用高效输入流 BufferedReader 读取文件
	 * @throws IOException
	 */
	@Test
	public void test8() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("./test/text7.txt"));
		int len = 0;
		//一次读取一个字符
//		while((len = br.read())!=-1){
//			System.out.print((char)len);
//		}
		
		//一次读取一个字符数组,更加高效
		char[] chs = new char[1024];
		while((len = br.read(chs)) != -1){
			System.out.print(String.valueOf(chs,0,len));
		}
		
		br.close();
	}
	
	/**
	 * 使用高效输出流 BufferWriter 写文件
	 * @throws IOException
	 */
	@Test
	public void test9() throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("./test/test9.txt"));
		bw.write("hello\r\n");
		bw.write("java\r\n");
		bw.flush();
		bw.close();
	}
	
	/**
	 * 使用高效字符流 复制文件
	 * @throws IOException
	 */
	@Test
	public void test10() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("./test/test9.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("./test/test10.txt"));
		//一次读取一个字符数组
		int len = 0;
		char[] chs = new char[1024];
		while((len = br.read(chs))!=-1){
			bw.write(String.valueOf(chs,0,len));
		}
		
		//释放流
		br.close();
		bw.close();
	}
	
	/**
	 * 使用高效字节流复制图片
	 * @throws IOException
	 */
	@Test
	public void test11() throws IOException{
		BufferedInputStream fis = new BufferedInputStream(new FileInputStream("planning.jpg"));
		BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("copy.jpg"));
		
		//一次读取一个字节数组
		byte[] bys = new byte[1024];
		int len = 0;
		while((len = fis.read(bys))!=-1){
			fos.write(bys, 0, len);
		}
		//释放流
		fis.close();
		fos.close();
	}
	
	/**
	 * 使用高效流的特有功能来复制文件
	 * @throws IOException
	 */
	@Test
	public void test12() throws IOException{
		/*
		 *   高效字符流流的特有功能：
		 *      BufferedReader:
		 *         public void newLine():  根据系统来决定写入不同的换行符
		 *      BufferedWriter:
		 *         public void readLine():   一次读取一行数据
		 */
		BufferedReader br = new BufferedReader(new FileReader("./test/test9.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("./test/test12.txt"));
		
		String line = null;
		while((line = br.readLine() )!= null){
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		//释放流
		br.close();
		bw.close();
	}
	
}
