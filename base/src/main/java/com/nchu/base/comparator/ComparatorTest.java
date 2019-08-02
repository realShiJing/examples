package com.nchu.base.comparator;

import com.nchu.bean.Apple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * @Decription 使用Comparator进行排序
 * @Author yangsj
 * @Date 20190802 11:53
 **/
public class ComparatorTest {

    /**
     * @Description 排序
     * @Author yangsj
     * @Date 2019/8/2 15:22
     **/
    @Test
    public void sortTest(){
        ArrayList<Apple> list = new ArrayList<>();
        list.add(new Apple(1,"黄色"));
        list.add(new Apple(6,"绿色"));
        list.add(new Apple(5,"红色"));
        list.add(new Apple(2,"绿色"));
        list.add(new Apple(4,"黄色"));
        list.add(new Apple(3,"红色"));
        //匿名内部类方式
        Collections.sort(list, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.weight - o2.weight;
            }
        });
        //lambda表达式
        Collections.sort(list, (o1, o2) -> o1.weight - o2.weight);

        Collections.sort(list, Comparator.comparingInt(o -> o.weight));
        System.out.println(list);
    }


    /**
     * @Description 分组
     * @Author yangsj
     * @Date 2019/8/2 14:40
     **/
    @Test
    public void groupTest(){
        ArrayList<Apple> list = new ArrayList<>();
        list.add(new Apple(103,"黄色"));
        list.add(new Apple(232,"绿色"));
        list.add(new Apple(133,"红色"));
        list.add(new Apple(267,"绿色"));
        list.add(new Apple(455,"黄色"));
        list.add(new Apple(466,"红色"));
        //按颜色分组（自定义分组器）
        List<List<Apple>> result =   divide(list, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.color.equals(o2.color)?0:1;
            }
        });

        //divide(list, (o1, o2) -> o1.color.equals(o2.color)?0:1);
        System.out.println("--------按颜色分组--------");
        System.out.println(result);
        //按重量分组
        List<List<Apple>> result2 =   divide(list, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.weight/100 == o2.weight/100 ?0:1;
            }
        });
        //divide(list, (o1, o2) -> o1.weight/100 == o2.weight/100 ?0:1);
        System.out.println("--------按重量分组--------");
        System.out.println(result2);



    }

    /**
     * @Description 自定义分组函数
     * @Author yangsj
     * @Date 2019/8/2 15:10
     **/
    public <T> List<List<T>> divide(List<T> list ,Comparator<?  super T> c){
        //分组后的结果集
        List<List<T>> result = new ArrayList<List<T>>();

        for(T t : list){
            //同一组的标识
            boolean sameFlag = false;
            for(List<T> l : result){
                //自定义比较器，满足条件代表t属于l一组
                if(c.compare(t,l.get(0)) == 0){
                    l.add(t);
                    //同一组的标识设置为true
                    sameFlag = true;
                }
            }
            //如果遍历到最后，sameFlag同一组标识仍然为false,代表结果集result中还没有属于它的分组
            if(!sameFlag){
                //这时要新建分组
                List<T> tempList = new ArrayList<>();
                result.add(tempList);
                tempList.add(t);
            }
        }
        return  result;
    }
}
