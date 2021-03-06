package com.nchu.cloudalibaba.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nchu.cloudalibaba.sentinel.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Decription 流量控制&熔断降级
 * @Author yangsj
 * @Date 2020/5/22 9:55
 **/
@RestController
public class FlowSlotController {

    /**
     * @Description  流控
     * 阈值类型：QPS,每秒请求数；线程，每秒线程并发数
     * 流控模式：
     *        直接：针对当前请求
     *        关联：关联其他请求，当前请求达到流量控制阈值后，触发关联请求的限流
     *        链路：
     * 流控效果：
     *        快速失败：当QPS超过任意规则的阈值后，新的请求就会被立即拒绝
     *        Warm up:
     *              预热时长：配置为N秒，在N秒内，只允许1/3阈值的流量通过，在N秒后，可以允许100%的阈值的请求访问通过
     *        排队等待：阈值QPS = 2 时，每隔500ms才允许通过下一个请求，严格控制请求通过的间隔时间，也即是让请求以均匀的速度通过
     * @Author yangsj
     * @Date 2020/5/31 3:07 下午
     **/
    @GetMapping("/testA")
    public String testA() throws InterruptedException {
        // 测试线程数时放开，模拟一个请求耗时0.8秒，并发线程数大于1
        Thread.sleep(800);
        return Thread.currentThread().getName()+"--------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        System.out.println(new Date(System.currentTimeMillis())+">>>>"+Thread.currentThread().getName());
        return "--------testB";
    }

    /**
     * @Description  熔断降级，降级策略，平均响应时间RT
     * 配置 RT 200 毫秒，时间窗口：60秒
     * 使用 postman 压测，每秒发送10个请求，每个请求的响应时间超过200毫秒
     * 触发熔断降级，在接下来60秒的时间内，服务不可用，60秒后，服务恢复
     * @Author yangsj
     * @Date 2020/5/31 3:00 下午
     **/
    @GetMapping("/testRT")
    public String testRT() throws InterruptedException {
        Thread.sleep(1000);
        return Thread.currentThread().getName()+"--------testRT";
    }
    /**
     * @Description   测试异常比例，异常比例为100%
     * 配置：
     *      异常比例：0.2
     *      时间窗口：60s
     * 表示当资源的每秒请求量 >= N（可配置），
     * 并且每秒异常总数占通过量的比值超过阈值（0.2）之后，
     * 资源进入降级状态，即在接下的时间窗口（60，以 s 为单位）之内，
     * 对这个方法的调用都会自动地返回降级结果
     *
     * 注意：如果没有达到上述要求，该请求会直接返回异常信息
     * @Author yangsj
     * @Date 2020/5/31 2:58 下午
     **/
    @GetMapping("/testER")
    public String testER(){
        System.out.println(new Date(System.currentTimeMillis())+">>>>"+Thread.currentThread().getName());
        int age = 10 / 0;
        return "--------test_EXCEPTION_COUNT";
    }

    /**
     * @Description  测试异常数
     * 配置： 异常数 5，时间窗口 60 s
     * 表示在一分钟内，请求的异常数达到5个，则触发熔断降级，在接下来的60s内服务均不可访问
     * 
     * 当资源近 1 分钟的异常数目超过阈值之后会进行熔断。
     * 注意统计时间窗口是分钟级别的
     * @Author yangsj
     * @Date 2020/5/31 3:36 下午
     **/
    @GetMapping("/testEC")
    public String testEC(){
        System.out.println(new Date(System.currentTimeMillis())+">>>>"+Thread.currentThread().getName());
        int age = 10 / 0;
        return "--------test_EXCEPTION_RATIO";
    }

    /**
     * @Description  热点key限流，必须制定兜底方法，如果没指定，达到限流规则后，前台页面直接返回 Error Page
     *
     *  资源名：hotKey,必须用resource 配置
     *  限流模式：QPS
     *  参数索引：0,表示第一个参数
     *  单机阈值：1 ，统计时间窗口 ：1 ，表示接下来1秒内的QPS达到了1就会触发限流
     *  高级选项
     *      参数类型：八种基本类型，热点key的类型，这里是java.lang.String
     *      参数值：200(例外项)，限流阈值 10，表示当热点key为200时，QPS做10处理
     *
     * @Author yangsj
     * @Date 2020/6/3 12:43 上午
     **/
    @GetMapping("/testHotKey")
    @SentinelResource(value = "hotKey",blockHandler = "testHotKeyHandle")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        //int age = 10 /0 ;//不满足限流直接抛出异常，满足限流规则直接进入，blockHandler方法
        return "--------testHotKey";
    }

    public String testHotKeyHandle (String p1, String p2, BlockException bl){
        return "--------blockHandler";
    }


    /**
     * @Description  由资源名称进行限流配置，可以走自定义兜底方法
     * @Author yangsj
     * @Date 2020/6/4 11:09 下午
     **/
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public String byResource()
    {
        return "------------byResource";
    }
    public String handleException(BlockException exception)
    {
        return "------------服务不可用";
    }

    /**
     * @Description  由url进行限流配置，不会走自定义兜底方法，会默认 Blocked by Sentinel (flow limiting)
     * @Author yangsj
     * @Date 2020/6/4 11:10 下午
     **/
    @GetMapping("/flowslot/byUrl")
    @SentinelResource(value = "byUrl",blockHandler = "handleException")//这边虽然配置了 blockHandler 但是没有作用
    public String byUrl()
    {
        return "------------byUrl";
    }

    /**
     * @Description  为了解决代码耦合度过高的问题，自己将兜底方法放到兜底类中
     * @Author yangsj
     * @Date 2020/6/4 11:12 下午
     **/
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,
    blockHandler = "handlerException")
    public String customerBlockHandler(){
        return "------------ customerBlockHandler";
    }
}
