package gdsxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/13 19:55
 * @Version 1.0
 */
public class MainClass{

    public static void main(String[] args) {
        try {
            Class<?> c= Class.forName("gdsxy.Person");//获得想要操作的Person类的Class对象c
            Constructor<?> cc= c.getConstructor(String.class);//通过反 射获得Person类中带参数的构造函数,构造函数对象为cc
//            Person obj= (Person) cc.newInstance("张三");//利用Constructor对象cc创建Person类对象 ,构造函数的参数为"张三”Method m1 =_第 4题//通过反射获得Person类的有 参数方法setScore()
            Person obj= (Person) c.newInstance();//利用Constructor对象cc创建Person类对象 ,构造函数的参数为"张三”Method m1 =_第 4题//通过反射获得Person类的有 参数方法setScore()
            Method m1= c.getMethod("setScore",double.class,double.class);
            m1.invoke(obj,90,80);
            //        第5题_ //通过反射调用obj对象的setScore() ,将setScore()的参数值设置为90,80
            Field name=c.getField("name");//通过反射获取Person类中的name属性
            Field Chinese=c.getField("Chinese");//通过反 射获取Person类中的Chinese属性
            System.out.println("姓名:"+name.get(obj)+",语文成绩 : "+Chinese.get(obj));//通过反射输出obj对象的name属性值和Chinese属性值
            Method m2=c.getMethod("getSumScore"); //通过反 射获得Person类的无参数方法getSumScore()
            Object ret=m2.invoke(obj); //通过反 射调用obj对象的getSumScore)，将getSumScore()的返回值作为invoke()的返回值赋值
//            给ret
            System.out.println("张三的总分为" +ret);//输出getSumScore(的返回值
            // }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
