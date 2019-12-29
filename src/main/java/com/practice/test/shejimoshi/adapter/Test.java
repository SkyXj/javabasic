package com.practice.test.shejimoshi.adapter;

public class Test {

    public static void main(String[] args) {
        Ac ac110=new Ac110();
        ChargeAdapter chargeAdapter=new ChineseAdapter();
        if(chargeAdapter.isSupport(ac110)){
            System.out.println(chargeAdapter.outputDc(ac110));
        };
    }
}
