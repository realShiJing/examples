package com.nchu.learn.utils.netty.echo;

import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/2/15 16:50
 */
public class EchoServer {

    private  final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main (String[] args){
        int port = 8080;
        new EchoServer(8080);
    }

    public static void start (){
        NioEventLoopGroup group = new NioEventLoopGroup();
        
    }
}
