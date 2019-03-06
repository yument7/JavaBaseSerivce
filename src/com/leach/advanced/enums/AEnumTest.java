package com.leach.advanced.enums;

import java.util.Scanner;

/**
 * @author Administrator
 * @name 概述：枚举类测试
 * @date 2019/2/19 19:10
 */
public class AEnumTest{
    public static void testState(){
        // 方式一获取实例
        VueState created = VueState.Created;

        String name = created.name();
        String str = created.toString();
        int ordinal = created.ordinal();
        System.out.println("name:" + name + ",str:" + str + ",ordinal" + ordinal);

        // 方式二 获取实例
        VueState updated = VueState.valueOf(VueState.class, "Updated");

        name = created.name();
        str = created.toString();
        ordinal = created.ordinal();
        System.out.println("name:" + name + ",str:" + str + ",ordinal" + ordinal);

        // 遍历
        for(VueState vs: VueState.values()){
            System.out.println("name:"+vs.name()+"\tordinal:"+vs.ordinal());
        }
    }

    public static void testBank(){
        for(Bank bk : Bank.values()){
            System.out.println("enum-name:"+bk.name()+"\tname:"+bk.getName()+"\t describle method:");
            bk.describle();
        }
    }

    public static void testCity(){
        for(City city: City.values()){
            System.out.println("enum-name:"+city.name()+"\nname:"+city.getName()+"\tzone:"+city.getZone()+"\t implements method describle:");
            city.describle();
        }
    }

    public static void testOperation(){
        for(Operation oper:Operation.values()){
            System.out.println("enum-name:"+oper.name()+"\n computed method:"+oper.computed(9,3));
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int number = Integer.parseInt(sc.next());
        switch(number){
            case 1:
                testState();
                break;
            case 2:
                testBank();
                break;
            case 3:
                testCity();
                break;
            case 4:
                testOperation();
                break;
        }
    }
}
