package com.practice.test.shejimoshi.simpleFactory;

public class BenCi implements Car {
    public void run() {
        System.out.println(BenCi.class.getName()+"run");
    }

    public void stop() {
        System.out.println(BenCi.class.getName()+"stop");
    }
}
