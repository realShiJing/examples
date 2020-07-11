package com.nchu.cas;

/**
 * @Decription CAS 模拟代码
 * @Author yangsj
 * @Date 2020/2/5 3:07 下午
 **/
class SimulatedCAS{
    volatile int count; // 实现count+=1
    int newValue;
     void addOne(){
            do {
                newValue = count+1; //①
            }while(count != cas(count,newValue)); //②
        }
    synchronized int cas(int expect, int newValue){
        // 读目前count的值
        int curValue = count;
        // 比较目前count值是否==期望值
        if(curValue == expect){
            // 如果是，则更新count的值
            count = newValue;
        }
        // 返回写入前的值
        return curValue;
    }
}