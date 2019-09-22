package com.leach.advance.annotation.instance;

import java.lang.reflect.Field;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/2/20 21:27
 */
public class ConvertProcessor {
    public static String processor(Class<?> clazz) {
        StringBuilder builder = new StringBuilder("create table ");
        StringBuilder primaryKey = new StringBuilder();

        //
        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getDeclaredAnnotation(Table.class);
            builder.append(table.name()).append("(\n\t");
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(SqlString.class)) {
                SqlString str = field.getDeclaredAnnotation(SqlString.class);
                builder.append(str.name()).append("\t").append(str.type()).
                        append("(").append(str.length()).append(")");
                Constraint ct = str.constraint();
                if (ct.primaryKey()) {
                    primaryKey.append("primary key(").append(str.name()).append(")");
                }
                if (!ct.allowNull())
                    builder.append(" not null");
                if (ct.unique())
                    builder.append(" unique");
                builder.append(",\n\t");
            }
            if (field.isAnnotationPresent(SqlNumber.class)) {
                SqlNumber num = field.getDeclaredAnnotation(SqlNumber.class);
                builder.append(num.name()).append("\t").append(num.type()).append("(").
                        append(num.percsion()).append(",").append(num.scale()).append(")");
                Constraint ct = num.constraint();
                if (ct.primaryKey()) {
                    primaryKey.append("primary key(").append(num.name()).append(")");
                }
                if (!ct.allowNull())
                    builder.append(" not null");
                if (ct.unique())
                    builder.append(" unique");
                builder.append(",\n\t");
            }
            if (field.isAnnotationPresent(SqlTime.class)) {
                SqlTime date = field.getDeclaredAnnotation(SqlTime.class);
                builder.append(date.name()).append("\t").append(date.type());
                Constraint ct = date.constraint();
                if (!ct.allowNull())
                    builder.append(" not null");
                if (ct.unique())
                    builder.append(" unique");
                builder.append(",\n\t");
            }
        }
        builder.append(primaryKey);
        builder.append(")");
        int index = builder.lastIndexOf(")");
        if (builder.charAt(index - 1) == ',')
            builder.deleteCharAt(index - 1);

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(processor(Mark.class));
    }
}
