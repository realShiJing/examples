package com.nchu.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @Decription 享元工厂，用于共享组件
 * @Author yangshijing
 * @Date 20191114 10:26
 **/
public class FlyWeightFacotry  {
    //缓存池
    private  Map<String,FlyWeight> pool = null;


    /**
     * @Description 构造方法用于初始化缓存池
     * @Author yangsj
     * @Date 2019/11/14 10:36
     **/
    FlyWeightFacotry(){
        this.pool = new HashMap<>();
    }


    /**
     * @Description 从享元池中获取缓存对象
     * @Author yangsj
     * @Date 2019/11/14 10:39
     **/
    public FlyWeight getFlyweight(String key){
        //如果享元池为空，直接返回null
        if(pool == null){
            return null;
        }

        //如果享元池中不包含要获取的对象，创建新对象，并放到缓存池中
        if(!pool.containsKey(key)){
            FlyWeight flyWeight = new ConcreteFlyWeight(key);
            pool.put(key,flyWeight);
        }

        return pool.get(key);
    }
}
