package com.practice.test.shejimoshi.proxy;

public class WeddingCompany implements ProxyInterface {
    private ProxyInterface proxyInterface;
    public WeddingCompany(ProxyInterface proxyInterface){
            this.proxyInterface=proxyInterface;
    }

    public void marry() {
        System.out.println("婚庆公司开始");
        proxyInterface.marry();
        System.out.println("婚庆公司结束");
    }
}
