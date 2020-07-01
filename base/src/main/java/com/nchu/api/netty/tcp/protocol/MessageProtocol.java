package com.nchu.api.netty.tcp.protocol;

import java.nio.charset.Charset;

/**
 * @Decription 自定义协议封装对象
 * @Author yangsj
 * @Date 2020/7/1 14:58
 **/
public class MessageProtocol {
    // 传输数据长度
    private int len;
    // 传输数据内容
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }


    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageProtocol{" +
                "len=" + len +
                ", content = " + new String(content, Charset.forName("utf-8")) +
                '}';
    }
}
