package com.leach.advance.stream.inputoutputstream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 特殊流，也叫数据流，同样只有字节流的数据流
 * @author Administrator
 *
 */
public class DataInputOutputStreamTest {
	/**
	 * 测试DataInputStream和DataOutputSteam
	 */
	public void DataOutputStreamTest(){
		String name = "Linda";
		int age = 23;
		long number = 12345673;
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new FileOutputStream(new File("test.txt")));
			dos.writeUTF(name);
			dos.writeInt(age);
			dos.writeLong(number);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(dos!=null){
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void DataInputStreamTest(){
		DataInputStream dis = null;
		String name;
		int age;
		long number;
		try {
			dis = new DataInputStream(new FileInputStream(new File("test.txt")));
			name = dis.readUTF();
			age = dis.readInt();
			number = dis.readLong();
			System.out.println("name:"+name+"age:"+age+"number:"+number);
		}  catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(dis!=null){
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
