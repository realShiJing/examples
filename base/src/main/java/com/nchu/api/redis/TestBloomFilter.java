package com.nchu.api.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Decription guava 实现布隆过滤器测试
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

        /**
         * @Description  已经添加到布隆过滤器中的元素，是不可能存在误判的
         * @Author yangsj
         * @Date 2020/9/28 4:43 下午
         **/
        for (int i = 0; i < total; i++) {
           if(!bf.mightContain(i)){
               System.out.println("存在的数据误判！");
           }
        }

        /**
         * @Description  不存在布隆过滤器中的元素有可能存在误判
         * 因为不存在的元素，调用 n 次哈希函数，有可能得到和已存在元素相同的位置
         * @Author yangsj
         * @Date 2020/9/28 4:43 下午
         **/
        int count = 0;
        for (int i = total; i < total + 10000; i++) {
            if(bf.mightContain(i)){
               count++;
            }
        }
        System.out.println("一百万条数据里不存在的数据误判个数"+count);

    }
}
