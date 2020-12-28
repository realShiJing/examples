package com.nchu.sentinel.quickstart.controller;

import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.nchu.sentinel.quickstart.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Decription 限流规则测试
 * Sentinel定义资源的方式
 *   抛出异常的方式定义资源
 *   返回布尔值的方式定义资源
 *   异步调用支持
 *   注解方式定义资源
 *   主流框架的默认适配
 * @Author yangsj
 * @Date 2020/12/28 11:53
 **/
@RestController
public class FLowRulesController {

    public static String BLOCK_MESSAGE = "系统繁忙，请稍后！";

    /**
     * @Description 1. API 限流
     * @Author yangsj
     * @Date 2020/12/28 14:32
     **/
    @GetMapping("/hello")
    public String hello(){
        //使用限流规则
        //定义资源，访问该方法后，Sentinel控制台会显示该资源，此时可动态配置限流规则
        try (Entry entry =  SphU.entry("HelloWorld")){
            // 通过请求
            return "Hello Sentienl!";
        } catch (BlockException e) {
            // 拒绝请求
            e.printStackTrace();
            return BLOCK_MESSAGE;
        }
    }

    /**
     * @Description 手动配置流控规则
     * @Author yangsj
     * @Date 2020/12/28 14:22
     **/
    @PostConstruct//构造器执行完之后执行
    private void initFlowRules(){
        //1. 创建流控规则集合
        List<FlowRule> rules = new ArrayList<>();
        //2. 配置流程规则
        FlowRule rule = new FlowRule();
        // 配置流控规则引用资源
        rule.setResource("HelloWorld");
        // 配置限流规则类型：QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 配置QPS,每秒能通过的请求个数
        rule.setCount(2);
        rules.add(rule);
        //3. 加载流控规则
        FlowRuleManager.loadRules(rules);
    }

    /**
     * @Description 2.返回布尔值的方式定义资源
     * @Author yangsj
     * @Date 2020/12/28 15:09
     **/
    @GetMapping("/boolean")
    public String returnBoolean(){
        if (SphO.entry("Sentinel-boolean")){
            try {
               return  "Hello Sentinel";
            }finally {
                SphO.exit();//限流的出口
            }
        }else {
            //限流后进行的操作
          return BLOCK_MESSAGE;
        }
    }

    
    /**
     * @Description 3.异步调用的支持
     * 1.在本地引导类中添加EnableAsync，表示SpringBoot项目开启异步调用支持
     * 2.创建AsyncService编写异步调用的方法
     * @Author yangsj
     * @Date 2020/12/28 15:13
     **/
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public String  async(){
        //1.进行限流控制
        AsyncEntry asyncEntry = null;
        try {
            asyncEntry = SphU.asyncEntry("Sentinel_Async"); //限流入口
            asyncService.hello(); //异步调用方法
            return "异步调用测试！";
        } catch (BlockException e) {
            e.printStackTrace();
            return BLOCK_MESSAGE;
        } finally {
            if (asyncEntry != null){
                asyncEntry.exit(); //限流出口
            }
        }
    }


    /**
     * @Description 4. 注解开启限流
     *  value 资源名
     *  blockHandler 限流处理函数名
     * @Author yangsj
     * @Date 2020/12/28 14:33
     **/
    @SentinelResource(value = "Anno",blockHandler = "exceptionHandler")
    @GetMapping("/anno")
    public String anno(){
        return "Hello Anno Sentinel！";
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
     * @Description 5. 整合 Spring MVC 既不需要注解，也不需要API编程
     * 只需在控制台中为资源 GET:/mvc 手动添加流控规则
     * @Author yangsj
     * @Date 2020/12/28 14:33
     **/
    @GetMapping("/mvc")
    public String mvc(){
        return "Hello MVC Sentinel！";
    }

    /**
     * @Description  Sentinel 整合 Apollo 数据源进行动态规则配置
     * @Author yangsj
     * @Date 2020/12/28 下午10:26
     **/
    @GetMapping("/apollo")
    public String echo() {
        return "Hello Apollo Sentinel";
    }

}
