package com.nchu.dubbo.filter;


import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * @Decription 自定义 Dubbo 过滤器
 * @Author yangsj
 * @Date 2020/10/13 4:48 下午
 **/
// 条件满足注解的value值时，激活该过滤器
@Activate(group = Constants.PROVIDER)
public class DubboInvokerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("before invoker");
        try {
            // 执行目标方法
            return invoker.invoke(invocation);
        }finally {
            System.out.println("after invoker");
        }
    }
}
