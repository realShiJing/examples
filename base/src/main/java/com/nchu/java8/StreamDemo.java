package com.nchu.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    /**
     * @Description  二者都是遍历操作，从结果是可以看出来，如果是单线程（也就是不加parallel方法的情况）那么二者结果是一致的，
     * 但是如果采用并行遍历，那么就有区别了，
     * forEach并行遍历不保证顺序（顺序随机）,
     * forEachOrdered却是保证顺序来进行遍历的
     * @Author yangsj
     * @Date 2019/8/6 17:33
     **/
    @Test
    public void forEachTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        list.stream().parallel().forEach(System.out::println);
        System.out.println("--------------");
        list.stream().parallel().forEachOrdered(System.out::println);
    }

    /**
     * @Description  toArray有两个方法，一个是无参方法，一个有参方法。
     * 无参方法返回的只能是Object[]数组类型，而有参方法，可以指定结果数组类型，此乃二者区别。
     * @Author yangsj
     * @Date 2019/8/6 17:33
     **/
    @Test
    public void toArrayTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        Object[] objects = list.stream().filter(l -> l.length() > 4).toArray();
        String[] ss = list.stream().filter(l -> l.length() > 4).toArray(String[]::new);
    }


    
    /**
     * @Description 通过给定的比较器，得出流中最大\最小的元素，
     * 为避免null返回，这里使用Optional来封装返回值
     * @Author yangsj
     * @Date 2019/8/6 16:07
     **/
    @Test
    public void maxMinTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        Optional<String> max = list.stream().max(Comparator.comparingInt(String::length));
        Optional<String> min = list.stream().min(Comparator.comparingInt(String::length));
        System.out.println(max);
        System.out.println("---------");
        System.out.println(min);
    }

    
    /**
     * @Description count是无参方法，用于计数，返回流中元素个数。
     * @Author yangsj
     * @Date 2019/8/6 16:17
     **/
    @Test
    public void countTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        long count = list.stream().count();
        System.out.println(count);
    }

    
    /**
     * @Description 该方法需要一个Predicate参数，用于校验流中的元素，
     * 只要有一个满足规则，则返回true，全不满足，返回false。
     * @Author yangsj
     * @Date 2019/8/6 16:18
     **/
    @Test
    public void anyMatchTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        boolean b1 = list.stream().anyMatch(l -> l.length() > 2);
        System.out.println(b1);
        boolean b2 = list.stream().anyMatch(l -> l.length() > 20);
        System.out.println(b2);
    }
    
    /**
     * @Description 用于校验流中的所有元素，
     * 只有全部满足规则才能返回true，只要有一个不满足则返回false。
     * @Author yangsj
     * @Date 2019/8/6 16:20
     **/
    @Test
    public void allMatchTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        boolean b1 = list.stream().allMatch(l -> l.length() > 0);
        System.out.println(b1);
        boolean b2 = list.stream().allMatch(l -> l.length() > 3);
        System.out.println(b2);
    }

    
    /**
     * @Description 用于校验流中的所有元素,
     * 只有所有元素都不满足规则的情况下返回true，否则返回false。
     * @Author yangsj
     * @Date 2019/8/6 16:27
     **/
    @Test
    public void noneMatchTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        boolean b1 = list.stream().noneMatch(l -> l.length() > 30);
        System.out.println(b1);
        boolean b2 = list.stream().noneMatch(l -> l.length() > 3);
        System.out.println(b2);
    }

    
    /**
     * @Description 该方法无参数，主要用于获取流中的第一个元素
     * @Author yangsj
     * @Date 2019/8/6 16:28
     **/
    @Test
    public void findFirstTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        Optional<String> first = list.stream().parallel().findFirst();
        System.out.println(first);
    }


    /**
     * @Description 该方法无参数，主要用于获取流中的任一元素。
     * @Author yangsj
     * @Date 2019/8/6 16:33
     **/
    @Test
    public void findanyTest(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        Optional<String> any = list.stream().parallel().findAny();
        System.out.println(any);
    }


}
