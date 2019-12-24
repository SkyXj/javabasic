package com.practice.test.shejimoshi.decorator2;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;

public class Mocha extends CondimentDecorator {

    private Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage=beverage;
        this.cost=beverage.getCost()+6;
        this.description=beverage.getDescription()+"Mocha";
    }

    public String getDescription() {
        return this.beverage.description+","+this.description;
    }

    public Integer getCost() {
        return this.beverage.cost+this.cost;
    }
}
