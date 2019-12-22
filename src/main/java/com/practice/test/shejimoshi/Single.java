package com.practice.test.shejimoshi;

public class Single {
//    饿汉式
//    private static Single single=new Single();
//    public static Single getSingle(){
//        return single;
//    }
//    懒汉式
//    private static Single single;
//    public static synchronized Single getSingle(){
//        if(single==null){
//            single=new Single();
//        }
//        return single;
//    }

//双重锁校验 volatile 修饰变量对其他线程可变 参考链接
//    https://www.cnblogs.com/zhengbin/p/5654805.html
    private volatile static Single single;
    public static Single getSingle(){
        if(single==null){
            synchronized (Single.class){
                if(single==null){
                    single=new Single();
                }
            }
        }
        return single;
    }

}
