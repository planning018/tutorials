package com.planning.io.oio;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 本章讲解了 如何封装键盘录入，封装 文本输出到控制台。
 * @author planning
 *
 */
public class ScannerDemo {
	
	/**
	 * 键盘录入数据写入到文本文件中，over表示结束
	 * @throws IOException
	 */
	@Test
	public void test13() throws IOException{
		//键盘录入
		Scanner sc = new Scanner(System.in);
		System.out.println("please input something!");
		BufferedWriter bw = new BufferedWriter(new FileWriter("./test/test13.txt"));
		String line = null;
		while((line = sc.nextLine())!= null){
			if("over".equalsIgnoreCase(line)){
				break;
			}
		    bw.write(line);	
		    //换行
		    bw.newLine();
		    //每次循坏都要刷新
		    bw.flush();
		}
		bw.close();
		sc.close();
	}
	
	/**
	 * 把一个文本文件的数据通过标准的字节输出显示到控制台上
	 * @throws IOException
	 */
	@Test
	public void test14() throws IOException{
		//封装数据源
		BufferedReader br = new BufferedReader(new FileReader("./test/test13.txt"));
		//封装目的地
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line = null;
		while((line = br.readLine())!= null){
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	/**
	 * 数据源 System.in
	 * 目的源 System.out
	 * @throws IOException
	 */
	@Test
	public void test15() throws IOException{
		//封装输入源
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("please input something");
		//封装输出源
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line = null;
		while((line = br.readLine())!= null){
			if("over".equalsIgnoreCase(line)){
				break;
			}
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	/**
	 * 键盘录入数据写入到test16_1.txt,再将test16_1上的文件内容复制到test16_2上,再将test16_2上的内容输出到控制台上
	 * @throws IOException
	 */
	@Test
	public void test16() throws IOException{
		//第一步：键盘录入数据写入到test16_1.txt
		//封装输入流
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("please input something in test16_1.txt!");
		//封装输出流
		BufferedWriter bw = new BufferedWriter(new FileWriter("./test/test16_1.txt"));
		
		String line = null;
		while((line = br.readLine())!= null){
			if("over".equalsIgnoreCase(line)){
				break;
			}
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		br.close();
		bw.close();
		
		//第二步：将test16_1上的文件内容复制到test16_2上
		BufferedReader br2 = new BufferedReader(new FileReader("./test/test16_1.txt"));
		BufferedWriter bw2 = new BufferedWriter(new FileWriter("./test/test16_2.txt"));
		String line2 = null;
		while((line2 = br2.readLine())!= null){
			bw2.write(line2);
			bw2.newLine();
			bw2.flush();
		}
		br2.close();
		bw2.close();
		
		//第三步：将test16_2上的内容输出到控制台上
		BufferedReader br3 = new BufferedReader(new FileReader("./test/test16_2.txt"));
		System.out.println("将test16_2的内容输出到控制台上！");
		BufferedWriter bw3 = new BufferedWriter(new OutputStreamWriter(System.out));
		String line3 = null;
		while((line3 = br3.readLine())!= null){
			bw3.write(line3);
			bw3.newLine();
			bw3.flush();
		}
		bw3.close();
		br3.close();
		
	}
		

}
