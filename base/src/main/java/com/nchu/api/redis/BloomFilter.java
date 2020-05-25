package com.nchu.api.redis;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/5/24 3:32 下午
 **/
public class BloomFilter  {

    private byte[] data;

    public BloomFilter(int initSize) {
        this.data = new byte[initSize];
    }

    public void add(Integer key){
        int location1 =Math.abs(h1(key) % data.length) ;
        int location2 =Math.abs(h2(key) % data.length) ;
        int location3 =Math.abs(h3(key) % data.length) ;

        data[location1] = data[location2] = data[location3] = 1;
    }

    public boolean contains(Integer key){
        int location1 =Math.abs(h1(key) % data.length) ;
        int location2 =Math.abs(h2(key) % data.length) ;
        int location3 =Math.abs(h3(key) % data.length) ;

        return  data[location1] * data[location2] * data[location3] == 1;
    }

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
