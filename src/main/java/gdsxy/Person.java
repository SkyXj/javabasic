package gdsxy;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/13 19:51
 * @Version 1.0
 */
public class Person {
    public String name;
    public double math;
    public double Chinese;

    public Person(){
        this.name="张三";
    }
    public Person(String name){
        this.name=name;
        System.out.println("调用了带参数的构造函数");
    }
    public void setScore(double math,double Chinese){
        this.math=math;
        this.Chinese=Chinese;
        System.out.println("调用了setScore");
    }
    public double getSumScore(){
        return math+Chinese;
    }
    public static void output(){
        System.out.println("这是一个静态方法");
    }
}


