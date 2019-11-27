package com.nchu.composite;

import java.util.List;

/**
 * @Decription 文件（文件目录的叶子节点）
 * @Author yangsj
 * @Date 20191127 19:15
 **/
public class Document implements File{

    /**
     * 文件名
     */
    public String name;

    Document(String name){
        this.name = name;
    }
    @Override
    public void display() {
        System.out.println(name);
    }

    /**
     * 叶子节点无子节点及添加和删除操作，实现方法体默认为空
     */
    @Override
    public boolean add(File file) {
        return false;
    }

    @Override
    public boolean remove(File file) {
        return false;
    }

    @Override
    public List<File> getFile() {
        return null;
    }
}
