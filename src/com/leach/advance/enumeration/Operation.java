package com.leach.advance.enumeration;

/**
 * 具有抽象函数的枚举类
 */
public enum Operation {

    addition() {
        @Override
        double computed(double x, double y) {
            return x + y;
        }
    },
    subtraction() {
        @Override
        double computed(double x, double y) {
            return x - y;
        }
    },
    multiply() {
        @Override
        double computed(double x, double y) {
            return x * y;
        }
    },
    divsion() {
        @Override
        double computed(double x, double y) {
            return x / y;
        }
    };

    // 抽象函数
    abstract double computed(double x, double y);
}
