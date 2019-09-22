package com.leach.advance.stream.readerwriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 节点流--字符流
 * @author Administrator
 *
 */
public class FileReaderWriterTest {
	/**
	 * 输入流
	 */
	public void fileReaderTest1(){
		File file = new File("javaEnvi.txt");
		FileReader frd = null;
		try {
			frd = new FileReader(file);
			//1.第一种读取方式： 实际是调用的inputStreamReader的read方法
			int b;
			while((b = frd.read())!=-1){
				System.out.print((char) b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(frd!=null){
				try {
					frd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void fileReaderTest2(){
		File file = new File("javaEnvi.txt");
		FileReader frd = null;
		try {
			frd = new FileReader(file);
			//1.第二种读取方式
			char[] b = new char[10];
			int len;
			int index=1;
			while((len = frd.read(b))!=-1){
				int flag = index%2;
				switch(flag){
				//1.使用字符串直接输出
				case 1: System.out.print(new String(b,0,len));
					break;
				//2.使用循环遍历
				case 0:	for(int i =0;i<len;i++){
							System.out.print(b[i]);
					}
				    break;
				}
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(frd!=null){
				try {
					frd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 输出流
	 */
	public void fileWriterTest1(){
		File file = new File("RWEnvi.txt");
		FileWriter rwt =null;
		int flag = 2;
		try {
			rwt = new FileWriter(file);
			String str = "我是一只小小鸟，想要飞也飞不高，我寻寻觅觅，寻寻觅觅一个温暖的怀抱......";
			char[] b = str.toCharArray();
			if(flag == 1){
				//1.第一种写流方法 : 写字符数据
				rwt.write(b);
			}else if(flag == 2){
				String str1 = "在这个花花世界，谁能主宰命运，上帝，请给我启示......";
				//2.第二种写流方法 ： 写字符串
				rwt.write(str1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(rwt!=null){
				try {
					rwt.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 输入输出的综合实例
	 */
	public void fileReaderWriterTest(){
		File file1 = new File("RWEnvi.txt");
		File file2 = new File("RWEnviCopy.txt");
		FileReader frd = null;
		FileWriter rwt = null;
		try {
			frd = new FileReader(file1);
			rwt = new FileWriter(file2);
			char[] b = new char[10];
			int len;
			while((len=frd.read(b))!=-1){
				rwt.write(b,0,len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(rwt!=null){
				try {
					rwt.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(frd!=null){
				try {
					frd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
