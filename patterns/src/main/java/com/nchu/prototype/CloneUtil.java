package com.nchu.prototype;

import java.io.*;

/**
 * @Decription 深拷贝工具类
 * @Author yangsj
 * @Date 20191024 11:15
 **/
public class CloneUtil  {
    public static <T extends Serializable> T clone (T obj){
        T t = null;
        //字节数组输出流在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中。
        ByteArrayOutputStream baos = null;
        //ObjectOutputStream 将 Java 对象的基本数据类型写入 OutputStream。
        ObjectOutputStream oos = null;
        //字节数组输入流在内存中创建一个字节数组缓冲区，从输入流读取的数据保存在该字节数组缓冲区中。
        ByteArrayInputStream bais = null;
        //可以使用 ObjectInputStream 读取（重构）对象。
        ObjectInputStream ois = null;
        try {
            //序列化？ 输出流，将对象输出到流中
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            //反序列化？输入流，从流中读出对象
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            t = (T)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            //关闭流
            try {
                baos.close();
                oos.close();
                bais.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return t;

    }
}
