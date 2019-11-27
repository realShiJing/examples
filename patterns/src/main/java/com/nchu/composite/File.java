package com.nchu.composite;


import java.util.List;

/**
 * @Description 文件节点抽象（文件和目录的总接口）
 * @Author yangsj
 * @Date 2019/11/26 20:13
 **/
public interface File {

    /**
     * 显示文件或文件夹名称
     */
     void display();

    /**
     * 向文件夹中添加文件或目录
     */
    boolean add(File file);

    /**
     * 删除当前目录下的文件或者文件夹
     */
    boolean remove(File file);

    /**
     * 获取当前目录下的文件或文件夹
     * @return
     */
    List<File> getFile();
}
