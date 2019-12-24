package com.practice.test.shejimoshi.decorator;

public class Bread extends Food{
    private Food food;

    public Bread(Food food){
        this.food=food;
    }
    @Override
    public String make() {
        return this.food.make()+"面包";
    }
}
