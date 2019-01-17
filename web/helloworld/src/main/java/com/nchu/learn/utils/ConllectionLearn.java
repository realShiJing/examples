package com.nchu.learn.utils;

import java.util.*;


public class ConllectionLearn {

    public static void main (String [] args){
        List<String > list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if("1".equals(next)){
                iterator.remove();
            }
        }
        boolean contains = list.contains("2");
        boolean empty = list.isEmpty();
        
        System.out.print(contains);
        list.iterator().forEachRemaining(s -> System.out.print(s));
    }
}
