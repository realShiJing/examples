package com.nchu.stack;


/**
 * @Description 自定义栈
 * @Author yangsj
 * @Date 2019/8/19 17:44
 **/
public  interface Stack {


   /**
   * @Description 判断栈是否满
   * @Author yangsj
   * @Date 2019/8/19 17:43
   **/
    boolean isFull();


    /**
     * @Description 判断栈是否空
     * @Author yangsj
     * @Date 2019/8/19 17:43
     **/
    boolean isEmpty();


    /**
     * @Description 入栈
     * @Author yangsj
     * @Date 2019/8/19 17:43
     **/
    void push(int n);



   /**
    * @Description 出栈
    * @Author yangsj
    * @Date 2019/8/19 17:44
    **/
    int pop();


   /**
    * @Description 显示栈中元素
    * @Author yangsj
    * @Date 2019/8/19 17:44
    **/
    void show();

}
