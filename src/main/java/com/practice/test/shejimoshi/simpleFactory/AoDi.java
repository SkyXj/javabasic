package com.practice.test.shejimoshi.simpleFactory;

public class AoDi implements Car {
    public void run() {
        System.out.println(AoDi.class.getName()+"run");
    }
    public void stop() {
        System.out.println(AoDi.class.getName()+"stop");
    }
    public static void main(String[] args) {
        String type="BenCi";
        Car car=null;
        if(type.equals("BenCi")){
            car=new BenCi();
        }else{
            car=new AoDi();
        }
        car.run();
        car.stop();
    }
}
