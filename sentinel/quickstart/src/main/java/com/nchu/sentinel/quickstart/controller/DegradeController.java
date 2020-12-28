package com.nchu.sentinel.quickstart.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Decription 熔断降级
 * @Author yangsj
 * @Date 2020/12/28 15:30
 **/
@RestController
@RequestMapping("/degrade")
public class DegradeController {
    public static String BLOCK_MESSAGE = "系统繁忙，请稍后！";

    @GetMapping("/hello")
    @SentinelResource(value = "HelloDegrade",blockHandler = "exceptionHandler" )
    public String hello(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Hello Degrade Sentinel";
    }

    /**
     * @Description 限流处理函数
     * @Author yangsj
     * @Date 2020/12/28 14:34
     **/
    public String  exceptionHandler (BlockException b){
        b.printStackTrace();
        return BLOCK_MESSAGE;
    }

    /**
     * @Description 手动配置降级规则
     * @Author yangsj
     * @Date 2020/12/28 14:22
     **/
    @PostConstruct//构造器执行完之后执行
    private void initDegradeRule(){
        //1. 创建降级规则集合
        List<DegradeRule> rules = new ArrayList<>();
        //2. 配置降级规则
        DegradeRule rule = new DegradeRule();
        //  配置资源名
        rule.setResource("HelloDegrade");
        //  配置熔断策略 RT,平均响应时间
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        // 当1s内有5个请求的响应时间大于200ms时触发熔断降级
        rule.setCount(200);
       // rule.setRtSlowRequestAmount();
        // 配置熔断降级的时间窗口,在接下来的10s内，程序都会熔断，走降级策略
        rule.setTimeWindow(10);

        rules.add(rule);
        // 加载降级规则
        DegradeRuleManager.loadRules(rules);

    }
}
