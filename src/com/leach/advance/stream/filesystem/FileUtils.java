package com.leach.advance.stream.filesystem;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author Administrator
 * @name 概述：java 对文件目录的操作
 *            1.创建文件对象
 *            2.获取文件名，目录名，文件路径，绝对路径，上一级目录，文件最后修改时间
 *            3.判断文件，文件目录是否存在， 判断是否可读可写，判断是文件还是目录
 *            4.文件、目录的创建，删除，遍历
 *
 * @date 2019/3/25 20:31
 */
public class FileUtils{

    public void TestCreatObjectFile(){
        /**
         * 1. 从文件路径创建File实例，不管当前路径下的文件或文件目录是否存在，都会创建基于该文件名或文件目录的file实例
         */
        File filefrompath1 = new File("d:/iofile/myfristfile.txt");
        File filefrompath2 = new File("d:\\iofile1");
        File filefrompath3 = new File("relativepathfile.txt");

        /**
         * 2. 根据parent实例所在目录，创建该目录下的child文件:File file = new File(File parent,String child);
         *    注意：parent 必须是文件目录，child为文件名，parent本地目录必须存在，child不能为空
         */
        File filefromParentF = new File(new File("d:/iofile"),"comefpfile.txt");

        /**
         * 根据parent目录名，创建该目录下的child文件:File file = new File(String parent, String child);
         */
        File filefromParentS = new File("d:/iofile","comefpsfile.txt");

        //4.File file = new File(URI uri);从网络地址的文件创建file实例
		/*URI uri;
		try {
			uri = new URI("www.baidu.com/hello.txt");
			File filefromUri = new File(uri);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

		/**
         * 获取文件名、文件目录名，文件路径，绝对路径，上一级目录,文件最后一次修改时间
         */
        String fileName1 = filefrompath1.getName();
        String pathNamea = filefrompath1.getAbsolutePath();
        String pathNamer = filefrompath1.getPath();
        String pathNamep = filefrompath1.getParent();
        long x = filefrompath1.lastModified();
        Date date = new Date(filefrompath1.lastModified());
        System.out.println(x+"\n"+date);
        System.out.println("文件名："+fileName1+"绝对路径："+pathNamea+"路径："+pathNamer+"上一级目录："+pathNamep);

        String fileName2 = filefrompath2.getName();
        String pathNamea2 = filefrompath2.getAbsolutePath();
        String pathNamer2 = filefrompath2.getPath();
        System.out.println("文件目录名："+fileName2+"绝对路径："+pathNamea2+"路径："+pathNamer2);

        String fileName3 = filefrompath3.getName();
        String pathNamea3 = filefrompath3.getAbsolutePath();
        String pathNamer3 = filefrompath3.getPath();
        System.out.println("文件目录名："+fileName3+"绝对路径："+pathNamea3+"路径："+pathNamer3);
        /**
         * 判断文件，文件目录是否存在，判断文件是否可读可写，判断是文件还是文件目录
         */
        boolean judgeF1 = filefrompath1.exists();
        boolean judgeI1 = filefrompath1.isFile();
        boolean judgeI2 = filefrompath1.isDirectory();
        boolean judgeC1 = filefrompath1.canRead();
        boolean judgeC2 = filefrompath1.canWrite();
        System.out.println("是否存在："+judgeF1+"\n是文件："+judgeI1+"\n是目录："+judgeI2+"\n可读："+judgeC1+"\n可写："+judgeC2);
        /**
         * 文件、文件目录的创建，删除，遍历
         */
        boolean cnf;
        try {
            cnf = filefrompath1.createNewFile();
            System.out.println("是否创建成功："+cnf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean del = filefrompath2.delete();
        System.out.println("是否删除成功："+del);


        boolean mkd = filefrompath2.mkdir();
        System.out.println("目录是否创建成功："+mkd);

        File filemkds = new File("d:/iofile3/iofile1/io");
        boolean mkds = filemkds.mkdirs();
        System.out.println("创建文件当前和上一级目录："+mkds);

        File filelist = new File("d:/BaiduYunDownload");
        String[] lists = filelist.list();
        for(String list: lists){
            System.out.println(list);
        }
        File[] listf = filelist.listFiles();
        for(File list: listf){
            System.out.println("文件名："+list.getName());
            System.out.println("路径："+list.getPath());
        }
    }
}
