package com.nchu.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Decription 文件夹
 * @Author yangsj
 * @Date 20191127 19:12
 **/
public class Folder implements File {

    /**
     * 文件夹名
     */
    public String name;
    /**
     * 当前文件夹下的目录和文件集合
     */
    public List<File> files;

    /**
     * 构造方法初始化子节点
     */
    Folder(String name){
        this.name = name;
        files = new ArrayList<>();
    }

    @Override
    public void display() {
       System.out.println(name);
    }

    /**
     * 向文件夹中添加文件
     * @param file
     * @return
     */
    @Override
    public boolean add(File file) {
        return files.add(file);
    }

    /**
     * 移除文件夹下的文件
     * @param file
     * @return
     */
    @Override
    public boolean remove(File file) {
        return file.add(file);
    }

    @Override
    public List<File> getFile() {
        return files;
    }
}
