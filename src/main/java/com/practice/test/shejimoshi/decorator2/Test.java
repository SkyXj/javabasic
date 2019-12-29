package com.practice.test.shejimoshi.decorator2;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        Beverage beverage=new HouseBlend();
        beverage=new Soy(beverage);
        beverage=new Mocha(beverage);
//        Beverage beverage=new Soy(new HouseBlend());
        System.out.println(beverage.getDescription()+";"+beverage.getCost());

        try {
            FileInputStream fileInputStream=new FileInputStream(new File("sdf"));
            BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
//            LineNumberInputStream lineNumberInputStream=new Li
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
