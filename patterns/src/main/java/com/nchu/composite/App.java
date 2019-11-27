package com.nchu.composite;

import org.junit.Test;

import java.util.List;

/**
 * @Decription 组合模式测试
 * @Author yangsj
 * @Date 20191127 19:33
 **/
public class App {

    @Test
    public void test(){
        File mainFolder = new Folder("主目录");

        File firstFolder = new Folder("一级目录");
        File firstDocument = new Document("一级文件");

        File secFolder = new Folder("二级目录");
        File secDocument = new Document("二级文件");

        File thirdFolder = new Folder("三级目录");
        File thirdDocument = new Document("三级文件");

        mainFolder.add(firstDocument);
        mainFolder.add(firstFolder);

        firstFolder.add(secDocument);
        firstFolder.add(secFolder);

        secFolder.add(thirdDocument);
        secFolder.add(thirdFolder);

        displayTree(mainFolder,0);
    }

    public static void displayTree(File rootFolder, int deep) {
        for(int i = 0; i < deep; i++) {
            System.out.print("\t");
        }
        //显示自身的名称
        rootFolder.display();
        //获得子文件集合
        List<File> files = rootFolder.getFile();
        //遍历子文件
        for(File file : files) {
            if(file instanceof Document) {
                for(int i = 0; i <= deep; i++) {
                    System.out.print("\t");
                }
                file.display();
            } else {
                displayTree(file,deep + 1);
            }
        }
    }
}
