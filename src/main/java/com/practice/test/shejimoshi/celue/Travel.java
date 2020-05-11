package com.practice.test.shejimoshi.celue;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/4/29 15:21
 * @Version 1.0
 */
public class Travel {
    private TravelMethod travelMethod;

    private void setTravelMethod(TravelMethod travelMethod){
        this.travelMethod=travelMethod;
    };

    public static void main(String[] args) {
        Travel travel=new Travel();
        TravelMethod travelMethod=new Foot();
        travel.setTravelMethod(travelMethod);
        travel.travelMethod.method();
    }
}
