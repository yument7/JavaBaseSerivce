package com.leach.advance.stream.inputoutputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 节点流--字节流
 * @author Administrator
 *
 */
public class FileInputOutputStreamTest {
	/**
	 * 输入流：将磁盘或网络设备上的文件读取到当前程序中
	 */
	public void FileInputStreamTest1() throws IOException{
		//1.新建一个File对象
		File file1 = new File("javaEnvi.txt");
		//2.新建输入流对象
		FileInputStream fis = new FileInputStream(file1);
		//3.取流（第一种方式）
		int b ;
		while((b=fis.read())!=-1){
			System.out.print((char)b);
		}
		fis.close();
	}

	public void FileInputStreamTest2(){
		File file = new File("javaEnvi.txt");
		FileInputStream  fis = null;
		try {
			fis = new FileInputStream(file);
			//1.第二种读取方法
			byte[] b = new byte[10];
			int len;
			while((len=fis.read(b))!=-1){
				for(int i=0;i<len;i++){
					System.out.print((char)b[i]);
				}
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void FileInputStreamTest3(){
		//1.新建一个File对象
		File file1 = new File("javaEnvi.txt");
		//2.新建输入流对象
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file1);
			//3.取流（第三种方式）
			byte[] b = new byte[10];
			int len;
			while((len = fis.read(b))!=-1){
				String str = new String(b,0,len);
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 输出流
	 */
	public void FileOutputStreamTest1(){
		//1.新建文件对象用于输出文件
		File file = new File("ioEnvi.txt");
		//2.为文件对象建立输出流
		FileOutputStream fos =null;
		try {
			fos = new FileOutputStream(file);
			String str = "如果没有你，没有过去，就不会有伤心，但是有如果，还是要爱你...";
			byte[] b = str.getBytes();
			//3.从程序开始取流
			fos.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//4.关闭流
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 输入输出综合应用：实现文件的复制
	 */
	public void FileStreamDoCopyTest(){
		//1.新建两个文件对象，被复制文件真实存在，复制文件可不存在，若不存在，过程中自动创建
		File fileI = new File("ioEnvi.txt");
		File fileO = new File("ioEnviCopy.txt");
		//2.新建流对象
		FileInputStream fis=null;
		FileOutputStream fos=null;
		try {
			fis = new FileInputStream(fileI);
			fos = new FileOutputStream(fileO);
			//1.输入流，读取文件内容到程序
			byte[] b = new byte[10];
			int len;
			while((len=fis.read(b))!=-1){
				//2.输出流，从程序写内容到文件
				fos.write(b, 0, len);
			}
			System.out.println("文件复制完成......");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
