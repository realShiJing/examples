package com.nchu.gc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Decription ThreadLocal 引起的堆外内存泄漏
 * 配合 JDK 自带工具  Java VisualVM BufferMonitor 插件 排查
 * @Author yangsj
 * @Date 2020/6/9 11:54
 **/
public class ReadByHeapByteBufferTest {

    /**
     * @Description  NIO中的FileChannel读取文件，默认使用了堆内的HeapByteBuffer来给FileChannel作为读取文件的缓冲，
     * FileChannel读取文件使用的IOUtil的read方法，针对HeapByteBuffer底层还用到一个临时的DirectByteBuffer来和操作系统进行直接的交互。
     * 默认情况下这个临时的DirectByteBuffer会被缓存在一个ThreadLocal的bufferCache里，每一个bufferCache有一个DirectByteBuffer的数组，
     * 每次当前线程需要使用到临时DirectByteBuffer时会取出自己bufferCache里的DirectByteBufferS，选取一个不小于所需size的，
     * 如果bufferCache为空或者没有符合的，就会调用Bits重新创建一个，使用完之后再缓存到bufferCache里。
     *
     * 这里的问题在于 ：这个bufferCache是ThreadLocal的，意味着极端情况下有N个调用线程就会有N组 bufferCache，
     * 就会有N组DirectByteBuffer被缓存起来不被释放，而且不同于在IO时直接使用DirectByteBuffer，这N组DirectByteBuffer在线程活跃时，由于与GCRoot有连接，所以
     * 连GC时都不会回收。因此在并发数比较多的时候，会引起堆外内存泄漏
     * @Author yangsj
     * @Date 2020/6/9 19:09
     **/
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        URL url = ReadByHeapByteBufferTest.class.getResource("/file.txt");
        File file = new File(url.getPath());
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024 * 4);
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(1000);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        fileChannel.read(byteBuffer);
                        byteBuffer.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }
}
