package com.practice.test.shejimoshi.decorator2;

import com.practice.test.shejimoshi.decorator.Bread;

//调料类
public abstract class CondimentDecorator extends Beverage {

    abstract public String getDescription();

    abstract public Integer getCost();
}
