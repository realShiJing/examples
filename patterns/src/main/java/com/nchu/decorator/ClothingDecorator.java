package com.nchu.decorator;

/**
 * @description: 抽象装饰角色：包含一个组件的引用，并定义了与抽象组件一致的接口。
 * @auther: yangsj
 * @created: 2019/3/18 16:59
 */
public abstract  class ClothingDecorator extends Person {
    @Override
    public abstract String getDescription();
}
