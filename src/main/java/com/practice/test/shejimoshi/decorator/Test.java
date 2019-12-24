package com.practice.test.shejimoshi.decorator;

public class Test {
    public static void main(String[] args) {
        Food food=new Bread(new Vegetable(new Food("三明治")));
        System.out.println(food.make());
    }
}
