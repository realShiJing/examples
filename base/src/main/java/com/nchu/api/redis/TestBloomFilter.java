package com.nchu.api.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/5/24 3:57 下午
 **/
public class TestBloomFilter {

    public static  int  total = 1000000;

    public static BloomFilter<Integer> bf =  BloomFilter.create(Funnels.integerFunnel(),total);

    public  static void  main(String [] args){
        // 初始化1000000条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put(i);
        }

        // 测试存在过滤器中的数据，有没有可能存在误判
        for (int i = 0; i < total; i++) {
           if(!bf.mightContain(i)){
               System.out.println("存在的数据误判！");
           }
        }

        // 测试不存在过滤器中的数据，有没有可能存在误判
        int count = 0;
        for (int i = total; i < total + 10000; i++) {
            if(bf.mightContain(i)){
               count++;
            }
        }
        System.out.println("一万条数据里不存在的数据误判个数"+count);

    }
}
