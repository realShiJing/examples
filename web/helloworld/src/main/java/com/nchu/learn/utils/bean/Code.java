package com.nchu.learn.utils.bean;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * 列表排序
 */
public class Code {

    public String name;

    public int code;
    public Code(String name) {
        this.name = name;
    }

    public String str = "{'1w':'1','2w':'2'}";

    @Test
    public  void Test(String[] args){
        Code test = new Code("1w",1);

        Code test1 = new Code("2w",2);

        List<Code> list = new  ArrayList<Code>();

        list.add(test);
        list.add(test1);

        Collections.sort(list, new Comparator<Code>() {
            JSONObject json = JSONObject.parseObject(str);
            @Override
            public int compare(Code o1, Code o2) {
                return json.getInteger(o1.name) - json.getInteger(o2.name) ;
            }
        });



        /*Collections.sort(list,(x,y) -> {
            JSONObject json = JSONObject.parseObject(str);
            return json.getInteger(x.getName()) - json.getInteger(y.getName());
        });*/

       /* System.out.println(list);
        Map<Integer,String> map = list.stream().filter(o -> o.name.length() > 0)
                .collect(Collectors.toMap(Test::getCode,Test::getName));

        map.forEach((x,y) -> System.out.println(x+"---"+y));*/

       String.valueOf(null);
    }



//---------------------------------------------//
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


    public Code(String name, int code) {
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
