package com.practice.test.shejimoshi.config;

import com.practice.test.shejimoshi.adapter.ChineseAdapter;
import com.practice.test.shejimoshi.adapter.JapanAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Beans {
    @Bean
    public ChineseAdapter chineseAdapter(){
        return new ChineseAdapter();
    }

    @Bean
    public JapanAdapter japanAdapter(){
        return new JapanAdapter();
    }
}
