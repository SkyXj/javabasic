package com.practice.test.shejimoshi;

import com.practice.test.shejimoshi.adapter.Ac;
import com.practice.test.shejimoshi.adapter.Ac220;
import com.practice.test.shejimoshi.adapter.ChargeAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {
    @Autowired
    List<ChargeAdapter> list;

    @Test
    public void test(){
        Ac ac=new Ac220();
        for (ChargeAdapter chargeAdapter:
             list) {
            if(chargeAdapter.isSupport(ac)){
                System.out.println(chargeAdapter.outputDc(ac));
            }
        }
    }

}
