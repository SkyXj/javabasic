package com.practice.test.shejimoshi;


import java.lang.reflect.Constructor;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/13 19:38
 * @Version 1.0
 */
public class TempClass {
    public static void main(String[] args) {
        try {
            Class c=TempClass.class;
            Constructor cc=c.getConstructor(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
