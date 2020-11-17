package com.nchu.boot.starter.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/17 下午2:11
 **/
@EnableConfigurationProperties(User.class)//开启属性注入功能
@ConfigurationProperties(prefix = "starteruser")// 指定配置文件注入属性前缀
public class User {
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
