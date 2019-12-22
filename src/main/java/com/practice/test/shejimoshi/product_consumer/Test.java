package com.practice.test.shejimoshi.product_consumer;

public class Test {
    public static void main(String[] args) {
        SyncStack stack = new SyncStack();
        Producter p = new Producter(stack);
        Consumer c = new Consumer(stack);

        new Thread(p).start();
        new Thread(c).start();

    }
}
