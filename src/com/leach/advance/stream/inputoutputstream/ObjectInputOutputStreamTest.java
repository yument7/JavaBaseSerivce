package com.leach.advance.stream.inputoutputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



/**
 * 对象流，只有字节流的对象流
 * @author Administrator
 *
 */
//1.对象流必须实现对象的序列化，如何实现，通过实现serializable 接口
//2.对象的属性也必需是可序列化的
//3.保证对象的反序列化，需要显示申明序列版本号，即定义private static final long serialVersionUID 为固定值;
//4.对象的属性或方法有static关键字标识，不能使用对象流，是非序列化的

public class ObjectInputOutputStreamTest {
	public void ObjectOutputStreamTest(){
		Pet p1 = new Pet("dog",1);
		Pet p2 = new Pet("cat",2);
		Person prs1 = new Person("小师弟",21,p1);
		Person prs2 = new Person("大师兄",22,p2);
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("testobj.txt")));
			oos.writeObject(prs1);
			oos.flush();
			oos.writeObject(prs2);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(oos!=null){
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void ObjectInputStreamTest(){
		ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("testobj.txt")));
			Person p1=(Person) ois.readObject();
			System.out.println(p1);
			Person p2 =(Person) ois.readObject();
			System.out.println(p2);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ois!=null){
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	String name;
	int age;
	Pet pet;
	public Person(String name,int age,Pet pet){
		this.name = name;
		this.age = age;
		this.pet = pet;
	}
	public void autoDescrible(){
		System.out.println("my name is"+name+" and odd is"+age);
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", pet=" + pet + "]";
	}
	
}

class Pet implements Serializable{
	/**
	 * 版本序列号，显示申明，便于序列化与反序列的正确性
	 */
	private static final long serialVersionUID = 1L;
	String name;
	int age;
	public Pet(String name,int age){
		this.age = age;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Pet [name=" + name + ", age=" + age + "]";
	}
	
}
