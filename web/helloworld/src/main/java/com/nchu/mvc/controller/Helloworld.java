package com.nchu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

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

    public static void main(String[] args) {
       /* String str = "abc";
        boolean status = str.contains("ab");
        if(status){
            System.out.println("包含");
        }else{
            System.out.println("不包含");
        }*/

        List<String>  list = new ArrayList<String>();

    }

}
