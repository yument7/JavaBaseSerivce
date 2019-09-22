package com.leach.advance.stream.readerwriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 缓冲流
 * @author Administrator
 *
 */
public class BufferedReaderWriterTest {
	public void BufferedRWTest(){
		long start = System.currentTimeMillis();
		File src = new File("javaEnvi.txt");
		File dest = new File("javaEnviBRWcopy.txt");
		BufferedReader brd =null;
		BufferedWriter bwt =null;
		try {
			FileReader frd = new FileReader(src);
			FileWriter fwt = new FileWriter(dest);
			brd = new BufferedReader(frd);
			bwt = new BufferedWriter(fwt);
			int flag = 1;
			if(flag == 1){
				//第一种方式，readline读流方法
				String s =null;
				while((s=brd.readLine())!=null){
					System.out.println(s);
					bwt.write(s);
					bwt.flush();
				}
				long end = System.currentTimeMillis();
				long needTime = end - start;
				System.out.println(needTime);
			}
			if(flag == 2){
				//第二种方式：字符读取
				char[] b = new char[1024];
				int len;
				while((len = brd.read(b))!=-1){
					String s = new String(b,0,len);
					System.out.println(s);
					bwt.write(b, 0, len);
					bwt.flush();
				}
				long end = System.currentTimeMillis();
				long needTime = end - start;
				System.out.println(needTime);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bwt!=null){
				try {
					bwt.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(brd!=null){
				try {
					brd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
