package com.nchu.api.redis;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/5/24 3:32 下午
 **/
public class BloomFilter  {

    // 字节数组
    private byte[] data;

    // 初始化字节数组的长度
    public BloomFilter(int initSize) {
        this.data = new byte[initSize];
    }

    /**
     * @Description  想布隆过滤器中添加元素
     * @Author yangsj
     * @Date 2020/9/28 4:35 下午
     **/
    public void add(Integer key){
        // 依次调用不同的哈希函数，得到key在字节数组中的位置
        int location1 =Math.abs(h1(key) % data.length) ;
        int location2 =Math.abs(h2(key) % data.length) ;
        int location3 =Math.abs(h3(key) % data.length) ;

        // 只是直观的展示布隆过滤起的原里，这里采用的是字节，实际布隆过滤器使用的是位
        data[location1] = data[location2] = data[location3] = 1;
    }

    /**
     * @Description  判断布隆过滤器中是否存在某个元素
     * @Author yangsj
     * @Date 2020/9/28 4:38 下午
     **/
    public boolean contains(Integer key){
        // 采用和添加元素相同的定位方法
        int location1 =Math.abs(h1(key) % data.length) ;
        int location2 =Math.abs(h2(key) % data.length) ;
        int location3 =Math.abs(h3(key) % data.length) ;
        // 如果每个位置上的值都是1 ，返回true，因为存在hash冲突，所以可能会误判
        return  data[location1] * data[location2] * data[location3] == 1;
    }

    /**
     * @Description  相互独立的 n 个hash函数
     * @Author yangsj
     * @Date 2020/9/28 4:39 下午
     **/
    private int h1(Integer key){
        return key.hashCode();
    }

    private int h2(Integer key){
        int hashCode = key.hashCode();

        return hashCode ^ (hashCode >>> 3);
    }
    private int h3(Integer key){
        int hashCode = key.hashCode();

        return hashCode ^ (hashCode >>> 16);
    }


}
