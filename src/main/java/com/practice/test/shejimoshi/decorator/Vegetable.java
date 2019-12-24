package com.practice.test.shejimoshi.decorator;

public class Vegetable extends Food{
    private Food food;

    public Vegetable(){

    }

    public Vegetable(Food food){
        this.food=food;
    }

    public String make() {
        return food.make()+"青菜";
    }
}
