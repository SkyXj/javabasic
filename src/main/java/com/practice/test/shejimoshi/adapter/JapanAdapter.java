package com.practice.test.shejimoshi.adapter;

public class JapanAdapter implements ChargeAdapter{
    private final int voltage=110;

    @Override
    public boolean isSupport(Ac ac) {
        return voltage==ac.output();
    }

    @Override
    public int outputDc(Ac ac) {
        int output = ac.output();
        int dcOutput=output/22;
        return dcOutput;
    }
}
