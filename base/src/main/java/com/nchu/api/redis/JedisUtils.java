package com.nchu.api.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/8 19:00
 */
public class JedisUtils {
    private static JedisPool pool = null;

    /**
     * 构建redis连接池
     * @return JedisPool
     */
    public static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            //最大连接数
            config.setMaxTotal(1024);
            //最大空闲连接数, 默认10个
            config.setMaxIdle(10);
            //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
            config.setMaxWaitMillis(10000);
            pool = new JedisPool(config, "127.0.0.1", 6379);
        }
        return pool;
    }


    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public static String get(String key){
        String value = null;

        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        return value;
    }
}
