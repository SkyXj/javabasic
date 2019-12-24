package com.practice.test.shejimoshi.decorator2;

public class HouseBlend extends Beverage {


    public HouseBlend(){
        this.description="HouseBlend coffe";
        this.cost=5;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getCost() {
        return this.cost;
    }
//    public static void main(String[] args) {
//        HouseBlend houseBlend=new HouseBlend();
//        System.out.println(houseBlend.cost);
//    }
}
