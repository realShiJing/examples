package com.nchu.learn.utils.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/8 19:03
 */
public class APP {
    @Test
    public  void test(){
        JedisPool pool = JedisUtils.getPool();
        Jedis jedis = pool.getResource();
        jedis.set("yangsj","helloworld");
        String value = jedis.get("yangsj");
        System.out.println(value);
    }
}
