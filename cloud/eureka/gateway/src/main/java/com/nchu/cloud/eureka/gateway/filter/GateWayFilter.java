package com.nchu.cloud.eureka.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * @Decription 全局自定义过滤器
 * @Author yangsj
 * @Date 2020/5/15 15:37
 **/
@Component
public class GateWayFilter implements GlobalFilter, Ordered {

    /**
     * @Description 过滤请求信息，请求必须携带参数name,否则拒绝请求
     * @Author yangsj
     * @Date 2020/5/15 18:10
     **/
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String name = request.getQueryParams().getFirst("name");
        if(name == null){
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 过滤器加载的顺序越小,优先级别越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
