package com.leach.advance.enumeration;

/**
 * 具有属性，代参数的构造函数， 方法的枚举类
 */
public enum Bank {
    BC("BC", "中国银行"),
    ABC("ABC", "中国农业银行"),
    CBC("CBC", "中国建设银行"),
    ICBC("ICBC", "中国工商银行"),
    CMBC("CMBC", "招商银行"),
    CDB("CDB", "国家开发银行"),
    CMSB("CMSB", "民生银行"),
    HSBC("HSBC", "汇丰银行");

    // 属性
    private final String code;
    private final String name;

    // 带参构造函数
    Bank(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // 公共方法
    public void describle() {
        System.out.println("we are belong to china.");
    }

    // getter 方法
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
}
