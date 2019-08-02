package com.nchu.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Decription 流操作
 * @Author yangshijing
 * @Date 20190726 11:09
 **/
public class StreamDemo {

    /**
     * @Description filter方法是过滤器方法，针对的是流中所有元素，满足条件的元素将会被保留以组成新的流。
     * @Author yangsj
     * @Date 2019/7/26 14:12
     **/
    @Test
    public void filterTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        list.stream().filter(l -> l.length() > 3 && l.length() < 7)//过滤掉长度大于3 小于7的字符串
                     .peek(System.out::println)//中间流输出
                     .collect(Collectors.toList());
    }


    /**
     * @Description map方法可以理解为函数，需要针对流中的每个元素执行，然后将执行的结果组成新的流返回。
     * @Author yangsj
     * @Date 2019/7/26 14:51
     **/
    @Test
    public void mapTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        //map方法的参数类型为Function，该函数式接口用于接受一个参数，返回一个结果。
        list.stream().map(l -> l + "@163.com")//为每个字符串添加邮箱后缀
                     .peek(System.out::println)
                     .collect(Collectors.toList());
    }

    
    /**
     * @Description mapToInt、mapToLong、mapToDouble方法是map方法的扩展，
     * 其参数分别为ToIntFunction、ToLongFunction、ToDoubleFunction，
     * 返回指定类型的值，分别为int、long、double
     * 那么定义方法的时候就要注意返回值的类型了，必须一致，
     * 最后组成的新流就是一个int或long或double元素流（IntStream、LongStream、DoubleStream）。
     * @Author yangsj
     * @Date 2019/7/26 14:57
     * @Param
     * @return
     **/
    @Test
    public void mapToIntTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        list.stream().mapToInt(l -> l.length())//以字符串的长度为结果返回一个新流
                .peek(System.out::println)
                .toArray();
    }


    /**
     * @Description flatMap与map的区别在于
     * flatMap是将一个流中的每个值都转成一个个流，然后再将这些流扁平化成为一个流
     * @Author yangsj
     * @Date 2019/7/26 14:57
     **/
    @Test
    public void mapFlatTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        list.stream().filter(l -> l.length() > 4 && l.length() < 10)//过滤出符合条件的字符串
                .peek(System.out::println)
                .map(l -> l.split(""))//将每个字符串元素分割成一个数组
                .peek(System.out::println)
                .flatMap(Arrays::stream)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }



    /**
     * @Description  map 和 flatMap 的区别
     * @Author yangsj
     * @Date 2019/7/26 16:05
     **/
    @Test
    public void mapAndFlatMap(){
        List<String> strs = Arrays.asList("好,好,学,习", "天,天，向,上");

        List<String[]> strArray = strs.stream().map(str -> str.split(","))
                .collect(Collectors.toList());
        System.out.println("strList => " + strArray);
        // flatMap与map的区别在于 flatMap是将一个流中的每个值都转成一个个流，然后再将这些流扁平化成为一个流
        List<String> strList = strs.stream().map(str -> str.split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        System.out.println("strList => " + strList);

    }


    /**
     * @Description distinct方法用于去重
     * @Author yangsj
     * @Date 2019/7/26 17:09
     **/
    @Test
    public void distinctTest(){
        int[] int1 = {1,2,3,3,4};
        int[] int2 = {5,5,5,6,6};
        List<int[]> list = Arrays.asList(int1,int2);
        list.stream().flatMapToInt(Arrays::stream)
                .distinct()
                .peek(System.out::print)
                .toArray();

    }


    /**
     * @Description 排序
     * @Author yangsj
     * @Date 2019/7/26 17:21
     **/
    @Test
    public void sortedTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        //当调用无参的sorted方法是自然排序
        System.out.println("-------自然排序------");
        list.stream().sorted()
                .peek(System.out::println)
                .collect(Collectors.toList());
        //当使用指定比较器的方式时，可以指定排序规则
        System.out.println("------长度排序-------");
        list.stream().sorted((a,b) -> a.length() - b.length())
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
    
    /**
     * @Description  limit可用于从首个元素开始截取N个元素，组成新流返回。
     * @Author yangsj
     * @Date 2019/7/26 17:30
     **/
    @Test
    public void limitTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        list.stream().limit(2)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }


    /**
     * @Description  skip表示放弃前N个元素，将剩余元素组成新流返回。
     * @Author yangsj
     * @Date 2019/7/26 17:33
     **/
    @Test
    public void skipTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        list.stream().skip(2)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

}
