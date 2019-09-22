package com.leach.advance.annotation.custom;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/10 15:17
 */
@CsData
public class DesignModel{

    private String name;

    private String attribute;

    public DesignModel(String name, String attribute){
        this.name = name;
        this.attribute = attribute;
    }
}
