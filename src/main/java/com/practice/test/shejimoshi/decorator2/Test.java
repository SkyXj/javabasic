package com.practice.test.shejimoshi.decorator2;

public class Test {
    public static void main(String[] args) {
        Beverage beverage=new HouseBlend();
        beverage=new Soy(beverage);
        beverage=new Mocha(beverage);
//        Beverage beverage=new Soy(new HouseBlend());
        System.out.println(beverage.getDescription()+";"+beverage.getCost());
    }
}
