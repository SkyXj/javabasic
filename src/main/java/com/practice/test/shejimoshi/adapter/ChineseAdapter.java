package com.practice.test.shejimoshi.adapter;

public class ChineseAdapter implements ChargeAdapter{
    private final int voltage=220;

    @Override
    public boolean isSupport(Ac ac) {
        return voltage==ac.output();
    }

    @Override
    public int outputDc(Ac ac) {
        int output = ac.output();
        int dcOutput=output/44;
        return dcOutput;
    }
}
