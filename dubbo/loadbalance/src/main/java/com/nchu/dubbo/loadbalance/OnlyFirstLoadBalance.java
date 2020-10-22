package com.nchu.dubbo.loadbalance;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;

import java.util.Comparator;
import java.util.List;

/**
 * @Decription 根据IP 和 端口排序 选择第一个invoker
 * @Author yangsj
 * @Date 2020/10/22 5:18 下午
 **/
public class OnlyFirstLoadBalance implements LoadBalance {
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        // 先按ip排序,如果两个invoker的ip相同，再对端口排序
        return invokers.stream().
                sorted(Comparator.comparing( (Invoker<T> i) -> i.getUrl().getIp()).
                        thenComparingInt(i -> i.getUrl().getPort())).findFirst().get();
    }
}
