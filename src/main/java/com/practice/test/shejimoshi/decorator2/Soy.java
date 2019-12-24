package com.practice.test.shejimoshi.decorator2;

public class Soy extends CondimentDecorator {
    private Beverage beverage;

    public Soy(Beverage beverage){
        this.beverage=beverage;
        this.cost=beverage.getCost()+6;
        this.description=beverage.getDescription()+"soy";
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getCost() {
        return this.cost;
    }
}
