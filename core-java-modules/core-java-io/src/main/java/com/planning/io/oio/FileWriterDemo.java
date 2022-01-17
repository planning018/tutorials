package com.planning.io.oio;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 本章主要学习 FileWriter,FileReader 如何读写文件，如何copy文件。
 * 
 * @author planning
 * 
 */
public class FileWriterDemo {

	/**
	 * 使用IO流写文件 --- FileWriter
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {

		File file = new File("./test/test.txt");
		// 使用fileWriter写文件，如果文件存在，则覆盖写的内容，如果文件不存在，则创建文件
		FileWriter fw = new FileWriter(file);
		// 可以使用write直接写
		// fw.write("hello");

		// 参数还可以是一个char[] 型数组
		char[] ch = { 'a', 'b', 'c', 'd', 'e', '中' };
		fw.write(ch);
		// 还可以设置起始位置
		fw.write(ch, 0, 3);

		fw.flush();
		fw.close();
	}

	/**
	 * 本实例讲解如何添加数据换行
	 * 
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		// 换行符: windows: \r\n ;linux: \n ;mac: \r;
		FileWriter fw = new FileWriter("./test/test2.txt", true);
		for (int i = 0; i < 10; i++) {
			fw.write(i + "\r\n");
		}
		fw.close();

	}

	/**
	 * 使用FileReader输入流读取文件
	 * 
	 * @throws IOException
	 */
	@Test
	public void test3() throws IOException {
		FileReader fr = new FileReader("./test/test2.txt");
		// int i = fr.read();
		// System.out.println((char)i);
		// i = fr.read();
		// System.out.println((char)i);

		// 结论：如果读取一个文件，返回值是-1，说明就没有数据了。
		int ch = 0;
		// while ((ch = fr.read()) != -1) {
		// System.out.print((char) ch);
		// }
		FileWriter fw = new FileWriter("./test/test3.txt");

		// 也可以使用如下方法
		// public int read(char[] chs); 返回的是实际的读取长度，把数据读取到了数组中
		char[] chs = new char[1024];
		while ((ch = (fr.read(chs))) != -1) {
			System.out.println(String.valueOf(chs, 0, ch));
			fw.write(chs, 0, ch);
		}

		fr.close();
		fw.close();
	}

	/**
	 * 复制文件的标准代码
	 */
	@Test
	public void test4() {
		FileWriter fw = null;
		FileReader fr = null;
		try {
			fr = new FileReader("./test/test3.txt");
			fw = new FileWriter("./test/test4.txt");
			int len = 0;
			while ((len = fr.read()) != -1) {
				fw.write(len);
			}
		} catch (FileNotFoundException e) {
			System.out.println("找不到文件");
		} catch (IOException e) {
			System.out.println("读写文件错误");
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					System.out.println("释放输出流错误");
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					System.out.println("释放输入流错误");
				}
			}

		}
	}
}
