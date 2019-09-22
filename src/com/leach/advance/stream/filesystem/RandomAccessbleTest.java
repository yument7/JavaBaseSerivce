package com.leach.advance.stream.filesystem;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessbleTest{
    public void TestRandomAccess(){
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try{
            raf1 = new RandomAccessFile(new File("hello.txt"), "r");
            raf2 = new RandomAccessFile(new File("hello1.txt"), "rw");
            byte[] b = new byte[25];
            int len;
            while((len = raf1.read(b)) != -1){
                //1.字符读取方式
                for(int i = 0; i < len; i++){
                    System.out.print((char) b[i]);
                }
                //2.字符串读取方式
                System.out.print(new String(b, 0, len));
                raf2.write(b, 0, len);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(raf2 != null){
                try{
                    raf2.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(raf1 != null){
                try{
                    raf1.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    //实习现的是覆盖效果
    public void TestRandomAccess1(){
        String cc = "i love you";
        RandomAccessFile raf = null;
        try{
            raf = new RandomAccessFile(new File("hello.txt"), "rw");
            raf.seek(5);
            raf.write(cc.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(raf != null){
                try{
                    raf.close();
                }catch(IOException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    //实现插入效果
    public void TestRandomAccess2(){
        String cc = "i love you";
        RandomAccessFile raf = null;
        try{
            raf = new RandomAccessFile(new File("hello.txt"), "rw");
            raf.seek(5);
            String str = raf.readLine();
            raf.seek(5);
            raf.write(cc.getBytes());
            raf.write(str.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(raf != null){
                try{
                    raf.close();
                }catch(IOException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    //实现复杂的插入效果
    public void TestRandomAccess3(){
        String cc = "i love you";
        RandomAccessFile raf = null;
        try{
            raf = new RandomAccessFile(new File("hello.txt"), "rw");
            raf.seek(6);
            byte[] b = new byte[20];
            int len;
            StringBuffer sb = new StringBuffer();
            while((len = raf.read(b)) != -1){
                sb.append(new String(b, 0, len));
            }
            raf.seek(6);
            raf.write(cc.getBytes());
            raf.write(sb.toString().getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(raf != null){
                try{
                    raf.close();
                }catch(IOException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
