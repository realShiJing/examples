package com.nchu.learn.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 列表排序
 */
public class Test {
    public String name;

    public int code;
    public Test(String name) {
        this.name = name;
    }

    static String str = "{'1w':'1','2w':'2'}";


    public static void main(String[] args){
        Test test = new Test("1w",1);

        Test test1 = new Test("2w",2);

        List<Test> list = new  ArrayList<Test>();

        list.add(test);
        list.add(test1);
//
//        Collections.sort(list, new Comparator<Test>() {
//            JSONObject json = JSONObject.parseObject(str);
//            @Override
//            public int compare(Test o1, Test o2) {
//                return json.getInteger(o1.name) - json.getInteger(o2.name) ;
//            }
//        });

        Collections.sort(list,(x,y) -> {
            JSONObject json = JSONObject.parseObject(str);
            return json.getInteger(x.getName()) - json.getInteger(y.getName());
        });

       /* System.out.println(list);
        Map<Integer,String> map = list.stream().filter(o -> o.name.length() > 0)
                .collect(Collectors.toMap(Test::getCode,Test::getName));

        map.forEach((x,y) -> System.out.println(x+"---"+y));*/

       String.valueOf(null);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static String getStr() {
        return str;
    }

    public static void setStr(String str) {
        Test.str = str;
    }

    public Test(String name, int code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }


}
