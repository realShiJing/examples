package com.spring.test;

import org.junit.Test;
import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Decription Spring相关测试
 * @Author yangsj
 * @Date 20191022 13:56
 **/
public class App {

    /**
     * @Description 各种location对应的资源类型
     * @Author yangsj
     * @Date 2019/10/22 13:56
     **/
    @Test
    public void test1(){
        //Spring默认资源加载器
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        //常见的磁盘路劲资源
        Resource fileResource1 = resourceLoader.getResource("D:/Users/chenming673/Documents/spark.txt");
        //fileResource1 is FileSystemResource:false 并不是文件资源？
        System.out.println("fileResource1 is FileSystemResource:" + (fileResource1 instanceof FileSystemResource));
        //fileResource1 is ClassPathRresource:true
        System.out.println("fileResource1 is ClassPathRresource:" + (fileResource1 instanceof ClassPathResource));

        //classPath资源
        Resource fileResource2 = resourceLoader.getResource("/Users/chenming673/Documents/spark.txt");
        //fileResource2 is ClassPathRresource:true
        System.out.println("fileResource2 is ClassPathRresource:" + (fileResource2 instanceof ClassPathResource));

        //Url资源
        Resource urlResource1 = resourceLoader.getResource("file:/Users/chenming673/Documents/spark.txt");
        //urlResource1 is UrlResource:true
        System.out.println("urlResource1 is UrlResource:" + (urlResource1 instanceof  UrlResource));

        //Url资源 网络资源
        Resource urlResource2 = resourceLoader.getResource("http://www.baidu.com");
        //urlResource2 is UrlResource:true
        System.out.println("urlResource2 is UrlResource:" + (urlResource2 instanceof  UrlResource));

        //由FileSystemResourceLoader加载的资源
        ResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource fileResource3 = fileSystemResourceLoader.getResource("D:/Users/chenming673/Documents/spark.txt");
        //fileResource3 is FileSystemResource:true
        System.out.println("fileResource3 is FileSystemResource:" + (fileResource3 instanceof FileSystemResource));

    }


    /**
     * @Description Java所支持的 URl类型
     * @Author yangsj
     * @Date 2019/10/22 14:38
     **/
    @Test
    public void test2(){
        String host = "www.google.com";
        String file = "/index.html";
        String[] schemes = {"http","https","ftp","mailto","telnet","file","jdbc","rmi","jar","doc","netdoc",};

        for (int i = 0; i < schemes.length; i++) {
            try {
                URL url = new URL(schemes[i],host,file);
                System.out.println(schemes[i] + " is support");
            } catch (MalformedURLException e) {
                System.out.println(schemes[i] + " is not support");
            }
        }
    }

    /**
     * @Description ResourcePatternResolver 加载多个资源
     * @Author yangsj
     * @Date 2019-11-14 21:45
     **/
    @Test
    public void ResourcePatternResolverTest() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        //classpath: 表示加载当前类路径中所有匹配的资源
        Resource[] resources = resourcePatternResolver.getResources("classpath:org/springframework/**/*.xml");
        for (Resource resource : resources) {
            System.out.println(resource.getFilename());
        }
        System.out.println("---------------------------");
        //classpath*: 表示加载类路径中所有匹配的资源(包括jar包中的内容)
        resources = resourcePatternResolver.getResources("classpath*:**/*.xml");
        for (Resource resource : resources) {
            System.out.println(resource.getURL().getPath()); //文件绝对路径
        }
    }
}
