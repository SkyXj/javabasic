package com.practice.test.DCS;

import java.io.File;
import java.util.*;

import static java.util.Objects.hash;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/1/3 12:32
 * @Version 1.0
 */
public class HashTest {
    public static void main(String[] args) {
        File file=new File("F:\\备份\\2019-11-21\\手机相册");
        Map<Integer,Integer> map=new HashMap<>();
        if(file.isDirectory()){
            File[] files=file.listFiles();
            for (File filetmp :files ) {
                int i = filetmp.getName().hashCode()%3;
                int abs = Math.abs(i);
                if(map.containsKey(abs)){
                    map.put(abs,map.get(abs)+1);
                }else{
                    map.put(abs,1);
                }
            }
        }
        List<Integer> keys=new ArrayList<>(map.keySet());
        Collections.sort(keys);
        Integer sum=map.values().stream().mapToInt(t->t).sum();
        for (Integer integer:keys ) {
            System.out.println(integer+":"+map.get(integer));
        }
        System.out.println("合计："+sum);
    }
}
