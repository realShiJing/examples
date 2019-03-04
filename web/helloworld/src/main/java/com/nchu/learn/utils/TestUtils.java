package com.nchu.learn.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/1/30 16:52
 */
public class TestUtils {

    @Test
    public void LeetCodeLongestStr(){
        String str = "sadfdsf";
        if(str != null && str.length() > 0 ){
            int length = str.length();
            char temp = str.charAt(0);
            String longestStr = String.valueOf(temp);
            for (int i = 1 ; i < length ; i++){
                char temp2 = str.charAt(i);
                if(!longestStr.contains(String.valueOf(temp2))){
                    longestStr = longestStr + temp2;
                }
            }
            System.out.print(longestStr);
        }
    }
    @Test
    public void guavaTest(){
        String json =  ("{\"S/N\":3,\"SW\":4,\"2W\":14}");
        Map<String,Integer> map =  JSONObject.parseObject(json,Map.class);
        BiMap<String, Integer> bimap =  HashBiMap.create(map);
        List<Integer> list = new ArrayList<Integer>(map.values());
        Collections.sort(list);
        BiMap<Integer, String> inverse = bimap.inverse();
        Integer tmpDays = findTermByInterval(list,6);
        System.out.println(bimap.inverse().get(tmpDays));
    }
    public Integer findTermByInterval(List<Integer> list,Integer day){
        if(list.get(0) >= day){
            return list.get(0);
        }
        if (list.get(list.size()-1) <= day){
            return  list.get(list.size()-1);
        }
        for(int i = 0 ; i <list.size()-1 ; i++){
            Integer integer = list.get(i);
            Integer integer1 = list.get(i + 1);
            if (day >= integer && day < integer1){
                return  integer;
            }else {
                return integer1;
            }
        }
        return  null;
    }


}
