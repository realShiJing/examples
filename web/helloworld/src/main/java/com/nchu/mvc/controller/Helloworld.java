package com.nchu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangshijing
 * @desc Spring SpringMvc mybatis Helloworld
 * @created 2018/5/17
 */
@Controller
@RequestMapping("/hello")
public class Helloworld {

    @RequestMapping("/world")
    public String  helloWorld(){
        return "helloworld";
    }



}
