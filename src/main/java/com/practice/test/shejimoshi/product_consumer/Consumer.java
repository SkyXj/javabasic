package com.practice.test.shejimoshi.product_consumer;

public class Consumer implements Runnable {

    private SyncStack stack;

    public Consumer(SyncStack stack){
        this.stack = stack;
    }
    public void run(){
        for(int i=0;i<stack.pro().length;i++){
            String consumer = stack.pop();
            System.out.println("消费了：" + consumer );

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
