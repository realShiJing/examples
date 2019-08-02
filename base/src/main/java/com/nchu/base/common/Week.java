package com.nchu.base.common;
/**
 *@description: 星期,枚举
 *@auther: yangsj
 *@created: 2019/4/2 15:54
 *
 */
public enum Week {
    Monday("星期一",1),TuesDay("星期二",2),WednesDay("星期三",3),
    ThursDay("星期四",4),Friday("星期五",5),SaturDay("星期六",6),Sunday("星期日",7);

    Week(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    //枚举类的成员变量 必须为 私有的
    private String name;

    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
