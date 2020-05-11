package com.practice.test.shejimoshi.proxy;

public class SelfWedding implements ProxyInterface {

    @Override
    public void marry() {
        System.out.println("我们结婚了");
    }

    public static void main(String[] args) {
        ProxyInterface proxyInterface=new WeddingCompany(new SelfWedding());
        proxyInterface.marry();
    }
}
