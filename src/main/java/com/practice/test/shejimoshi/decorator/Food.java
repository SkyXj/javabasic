package com.practice.test.shejimoshi.decorator;

public class Food {
//    abstract String make();
    private String des;

    public Food(){

    }
    public Food(String des){
        this.des=des;
    }

    public String make(){
        return des;
    }
}
