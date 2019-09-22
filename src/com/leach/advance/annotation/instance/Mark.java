package com.leach.advance.annotation.instance;

import java.util.Date;

/**
 * @author Administrator
 * @name 概述：注解应用实体类
 * @date 2019/2/20 21:11
 */
@Table(name = "Marks")
public class Mark {

    @SqlString(name = "code", constraint = @Constraint(primaryKey = true))
    private String code;

    @SqlNumber(name = "chinese")
    private double chinese;

    @SqlNumber(name = "math")
    private double math;

    @SqlNumber(name = "english")
    private double english;

    @SqlNumber(name = "physics")
    private double physics;

    @SqlNumber(name = "chemistry")
    private double chemistry;

    @SqlNumber(name = "biology")
    private double biology;

    @SqlTime(name = "record_time", constraint = @Constraint(allowNull = false))
    private Date recordTime;
}
