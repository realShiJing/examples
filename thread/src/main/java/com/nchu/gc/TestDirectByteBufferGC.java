package com.nchu.gc;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

/**
 * @Decription 堆外内存泄漏问题排查
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * -XX:+PrintGCCause
 * -Xms4g
 * -Xmx4g
 * -Xmn2g
 * @Author yangsj
 * @Date 2020/6/5 21:47
 **/
public class TestDirectByteBufferGC {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024*1024*100];
        for(;;){
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024*1024*100);
            byteBuffer.put(bytes);
            ((DirectBuffer)byteBuffer).cleaner().clean();
        }
    }
}
