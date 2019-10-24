package com.nchu.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.nchu.base.common.Week;
import com.nchu.bean.Parent;
import com.nchu.bean.Person;
import com.nchu.bean.Son;
import com.nchu.test.OutSide.Inside;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/25 17:23
 */
public class APP {


    /**
     *@description: LeetCode 测试， 无重复字符串
     *@auther: yangsj
     *@created: 2019/3/28 11:31
     *
     */
    @Test
    public void LeetCodeLongestStr() {
        String str = "sadfdsf";
        if (str != null && str.length() > 0) {
            int length = str.length();
            char temp = str.charAt(0);
            String longestStr = String.valueOf(temp);
            for (int i = 1; i < length; i++) {
                char temp2 = str.charAt(i);
                if (!longestStr.contains(String.valueOf(temp2))) {
                    longestStr = longestStr + temp2;
                }
            }
            System.out.print(longestStr);
        }
    }

    /**
     *@description: Map 键值对翻转
     *@auther: yangsj
     *@created: 2019/3/28 11:17
     *
     */
    @Test
    public void guavaTest(){
        String json =  "{\"S/N\":3,\"SW\":4,\"2W\":14}";
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


    /**
     *@description: 测试 map遍历方法 耗时
     *@auther: yangsj
     *@created: 2019/3/27 16:33
     *
     */
    @Test
    public void Test(){
        List<String> list = new ArrayList<>();
        for(int i = 0 ; i<10 ; i++){
            list.add(""+i);
        }
        Map<String,List<String>> map = new HashMap<>();
        for(int i = 0 ; i< 1000; i++){
            map.put("list"+i,list);
        }

        Map<String,Map<String,List<String>>> tempMap = new HashMap<>();
        for(int i = 0 ; i< 10000 ; i++){
            tempMap.put("Map"+i,map);
        }


        /*tempMap.entrySet().stream().forEach(s ->
            System.out.println(s+"***"+s.getValue()+"==="+s.getKey())
        );
        */

        //1、使用迭代器Iterator遍历
        long begin = System.currentTimeMillis();
        System.out.println("map.entrySet().iterator : ------------->" + begin);
        Iterator<Map.Entry<String, Map<String, List<String>>>> iterator = tempMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Map<String, List<String>>> next = iterator.next();
            Map<String, List<String>> value = next.getValue();
            Iterator<Map.Entry<String, List<String>>> iterator1 = value.entrySet().iterator();
            while(iterator1.hasNext()){
                Map.Entry<String, List<String>> next1 = iterator1.next();
                List<String> value1 = next1.getValue();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("map.entrySet().iterator : -------------> "+end);

        System.out.println(end-begin);

        //2、在for-each循环中使用map.entrySet()来遍历
        begin = System.currentTimeMillis();
        System.out.println("map.entrySet() : ------------->" +begin);
        for(Map.Entry<String,Map<String,List<String>>> entry: tempMap.entrySet()){
            Map<String, List<String>> value = entry.getValue();
            for (Map.Entry<String,List<String>> entry1 : value.entrySet()){
                List<String> value1 = entry1.getValue();
            }
        }
        end = System.currentTimeMillis();
        System.out.println("map.entrySet() : -------------> "+end);
        System.out.println(end-begin);
        //3、在for-each循环中遍历map.keySet()或map.values()
        begin = System.currentTimeMillis();
        System.out.println("map.values() : ------------->" +begin);
        for(Map<String,List<String>> map1 : tempMap.values()){
            for(List<String> lists : map1.values()){
            }
        }
        end = System.currentTimeMillis();
        System.out.println("map.values() : -------------> " +end);
        System.out.println(end-begin);
        // 4、通过键找值遍历（效率低）
        begin = System.currentTimeMillis();
        System.out.println("map.keySet() : ------------->"+begin );
        for(String str :tempMap.keySet()){
            Map<String, List<String>> stringListMap = tempMap.get(str);
            for(String str1 : stringListMap.keySet()){
                List<String> strings = stringListMap.get(str1);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("map.keySet() : -------------> " +end );
        System.out.println(end-begin);
    }

    /**
     *@description: lambda表达式入门
     *@auther: yangsj
     *@created: 2019/3/28 10:32
     *
     */
    @Test
    public void Test2(){
        List<String> lists = new ArrayList<>();
        for(int i = 0 ; i<100 ; i++){
            lists.add(String.valueOf(i));
        }
        lists.forEach(s -> {
            System.out.println(s);
        });
      /*  lists.stream().forEach(s -> {
            System.out.println(s);
        });*/
    }

    @Test
    public void Test3(){
        String str = "{'1w':'1','2w':'2'}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        Object parse = JSONObject.parse(str);
        Integer integer = jsonObject.getInteger("1w");
       /* Double aDouble = jsonObject.getDouble("1w");
        Date date = jsonObject.getDate("1w");*/
        System.out.print(integer);

    }

    /**
     *@description: 自定义集合排序规则
     *@auther: yangsj
     *@created: 2019/3/28 10:53
     *
     */
    @Test
    public void Test4(){
        Person yangsj = new Person("yangsj",24);

        Person wayfo = new Person("wayfo",22);

        List<Person> list = new ArrayList<>();

        list.add(yangsj);

        list.add(wayfo);

        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        // 从小到大 排序 简化
        // Collections.sort(list, Comparator.comparingInt(Person::getAge));
        // 从大到小 排序
        //Collections.sort(list,(x,y)-> y.getAge() - x.getAge());

        System.out.print(list);
    }

    /**
     *@description: Array List常用API
     *@auther: yangsj
     *@created: 2019/3/28 11:14
     *
     */
    @Test
    public void Test5(){
        List<String > list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if("2".equals(next)){
                iterator.remove();
            }
        }
        boolean contains = list.contains("2");

        System.out.print(contains);
        list.forEach(s -> System.out.print(s));
    }




    @Test
    public void Test6(){
        StringBuffer sb = new StringBuffer("my");
        change(sb);
        System.out.println(sb.hashCode() + sb.toString());
    }
    public void change(StringBuffer str ){
        System.out.println("形式参数 str -》》》》"+str.hashCode());
        str.append(" 12324");
        System.out.println("形式参数 str -》》》》"+str.hashCode());
    }

    /**
     *@description: 自动装箱和拆箱
     *@auther: yangsj
     *@created: 2019/4/2 15:48
     *
     */
    @Test
    public void Test7(){
        Integer numb1 = 130;
        Integer numb2 = 130;

        // -128~127 输出true
        // 超过该范围，输出false
        System.out.print(numb1==numb2);
    }
    @Test
    public void Test8(){
        System.out.println(Week.Friday.getName());
    }


    @Test
    public void test11(){
        String s = "123231";

        try {
            if(1/2 == 2){
                throw new RuntimeException();
            }
            else{
                throw new Exception();
            }
        } catch (MyException e) {
            System.out.println("自定义异常");

        }catch (Exception e){
            System.out.println("系统异常");
        }

        System.out.println(22222);
    }

    @Test
    public void test12(){
        String str = "1212";
        ArrayList<String> list = new ArrayList<>();

        ArrayList<String> list1 = list;

    }
    @Test
    public void test13(){
        OutSide outSide = new OutSide();
        OutSide.Inside inside = outSide.new Inside();
        Inside inside2 = outSide.new Inside();
    }
    @Test
    public void test14(){
        int x = 20 ,y = 5;
        System.out.print(x+y+""+(x+y)+y);

    }
    @Test
    public void test15(){
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        operator(a,b);
        System.out.print(a + "" +b);

    }
    public void operator (StringBuffer x , StringBuffer y){
        x.append(y);
        y = x;
    }
    @Test
    public void test16(){
       /* byte b1=1,b2=2,b3,b6,b8;
        final byte b4=4,b7;
       final byte b5=6;
        b3=(b1+b2);  *//*语句1*//*
        b6=b4+b5;    *//*语句2*//*
        b8=(b1+b4);  *//*语句3*//*
        b7=(b2+b5);  *//*语句4*//*
        System.out.println(b3+b6);*/
    }

    /**
     *@description: 公积金可贷额度计算
     *@auther: yangsj
     *@created: 2019/6/13 17:40
     */
    @Test
    public void test17(){
         Integer months = 1;//缴纳公积金月数
         Integer multiple = 15;//倍数，杭州市主城区、萧山区、余杭区、富阳区倍数是15
         Integer aFund = 1960;//每个月的公积金缴存,单位元
         Integer mount = 500000;//可贷额度，职工本人符合贷款申请条件的，最高可贷50万
         Integer aFundSum =0;//公积金总额
         Integer sum = 0;//月均缴存余额总值
         Integer balance = 17000 ; //公积金账户余额
         Integer value = Math.floorDiv(mount,multiple); //月均缴存余额阀值
         System.out.println("--------》月均缴存余额阀值："+value);
         Integer remainingSum = 0 ;
         while (remainingSum < value) {
             aFundSum = Math.multiplyExact(months, aFund) + balance;
             if(months >=12){
                 for (int i = 1; i <= 12; i++) {
                     Integer monthAmount = Math.subtractExact(aFundSum, Math.multiplyExact(aFund, i-1));
                     System.out.println("第" + (13 - i) + "个月汇缴：" + monthAmount);
                     sum = Math.addExact(monthAmount, sum);
                 }
             }
             months++;
             remainingSum = Math.floorDiv(sum, 12);
             sum = 0;
         }
         System.out.print("贷款50万，还需每个月缴纳公积金：" + aFund+"；共计："+(months-1)+"个月！");
    }
    @Test
    public void  calendarTest(){
        //Calendar ---> Date
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        System.out.println(date);

        //Date ---> Calendar
        Date date1 = new Date();
        Calendar calendar1  = Calendar.getInstance();
        calendar.setTime(date1);

        System.out.println(calendar1);

        System.out.println(calendar1.get(Calendar.YEAR)+"年"+(calendar1.get(Calendar.MONTH)+1)+"月"+calendar1.get(Calendar
                .DATE)+"日");

        ArrayList arrayList = new ArrayList();

        LinkedList<String> linkedList = new LinkedList<>();
    }

    /**
     * @Description 将字符串输出到文件
     * @Author yangsj
     * @Date 2019/8/12 17:23
     **/
    @Test
    public void test18() {
        Map<String,Integer> map = new HashMap<>();
        map.put("1",1);
        try{
            FileWriter fw = new FileWriter("1.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("4214321342134214");
            bw.newLine();
            bw.write("12121");
            bw.close();
            fw.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * @Description 自定义对象作为key存入对象时，是否需要重写equals
     * @Author yangsj
     * @Date 2019/8/14 10:18
     **/
    @Test
    public void test19(){
        Person p1 = new Person("yangsj",24);
        Person p2 = new Person("yangsj",24);
        System.out.println(p1.equals(p2));
        HashMap<Person,Integer> map = new HashMap<>();
        map.put(p1,2);
        System.out.println(map.get(p2));
    }
    
    
    /**
     * @Description String StringBuffer StringBuilder
     * @Author yangsj
     * @Date 2019/8/15 10:54
     **/
    @Test
    public void test20(){
       /* String str = new String();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();*/
       String str = "+232+23";
       str = str.replaceAll("\\+","");
       System.out.println(str);
    }


    /**
     * @Description char 字节和int
     * @Author yangsj
     * @Date 2019/8/20 10:00
     **/
    @Test
    public void test21(){
        char i = '.';
        System.out.println(Integer.valueOf(i));//49
        //字节 用ASCII码表示，均对应一个int值，可直接赋值给int类型的变量
        System.out.println(i ==46);
        int num = i;
        System.out.println(num);
        String val = "1";
        System.out.println(val.charAt(0));
    }


    /**
     * @Description 测试 正则表达式
     * @Author yangsj
     * @Date 2019/8/23 14:52
     **/
    @Test
    public void test22(){
        //匹配多位整数
        System.out.println("\\d+");
        boolean matches = "1".matches("\\d+");
        System.out.println(matches);
        //匹配多位小数
        matches = "10.098".matches("^([0-9]+[.][0-9]*)$");
        System.out.println(matches);
        BigDecimal bigDecimal = new BigDecimal(1.11111111);
        System.out.println(bigDecimal);
        Double d1 = new Double(1.1111);
        Double d2 = new Double(1.1111);

        System.out.println();
    }
    
    
    /**
     * @Description 时间复杂度的一些测试
     * @Author yangsj
     * @Date 2019/8/30 10:32
     **/
    @Test
    public void test23(){
        int  total  = 0 ;
        int  end  = 100;
        for(int i = 1 ; i <= end ; i++){
            total +=i;
        }
        total = (1 + end) * end / 2;

    }


    /**
     * @Description finally 测试
     * @Author yangsj
     * @Date 2019/9/3 19:16
     **/
    @Test
    public void test24(){
        try{
            System.exit(1);
        }finally {
            System.out.println("Print From Finally");
        }
    }

    /**
     * @Description 取模
     * @Author yangsj
     * @Date 2019/9/9 19:16
     **/
    @Test
    public void test25(){
        Double num = 748.2;
        System.out.println(748 % 10);
    }


    /**
     * @Description 日期格式化
     * @Author yangsj
     * @Date 2019/10/14 17:57
     **/
    @Test
    public void test26() throws ParseException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        simpleDateFormat.parse("2019-10-14");
        System.out.println(format);
    }


    /**
     * @Description String split
     * @Author yangsj
     * @Date 2019/10/15 10:42
     **/
    @Test
    public  void test27(){
        String pod = "10001|111111";
        String[] split = pod.split("\\|");
        System.out.println(Arrays.toString(split));
    }
    /**
     * @Description List.sort()
     * @Author yangsj
     * @Date 2019/10/15 10:31
     **/
    @Test
    public void listSort(){
        List<String> list = Arrays.asList("123","456","789","1011","nachanghangkong","nchu","realshi","138989898");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        list.sort((o1, o2) -> o1.length() - o2.length());


        list.sort(Comparator.comparingInt(String::length));
        System.out.println(list);
    }


    /**
     * @Description static final修饰方法测试
     * @Author yangsj
     * @Date 2019/10/15 11:16
     **/
    @Test
    public void test28(){
        Parent son = new Son();
        //子类实现父类非静态方法，输出 son
        son.test1();
        //如果在子类中写一个和父类中一样的静态方法,那么该静态方法由该子类特有，两者不构成重写关系,输出 parent
        son.test2();

    }
   
   /**
    * @Description Collection
    * @Author yangsj
    * @Date 2019/10/23 16:42
    **/
    public void test29(){
        List<Object> objects = Collections.synchronizedList(new ArrayList<>());
    }


}


class MyException extends Exception{

}

interface InterFaceTest{
    //int value ; 接口中不能报包含变量

    //Modifier 'public' is redundant for interface fields
    //Modifier 'static' is redundant for interface fields
    //Modifier 'final' is redundant for interface fields
    //只能包含常量成员，且默认的是public static final 修饰
    public  static  final  int value = 1;

    static  void test(){
        System.out.print("1");
    }

    /**
     * @Description JDK1.8开始支持 default method实现
     * @Author yangsj
     * @Date 2019/10/15 10:49
     **/
    default void test2(){
        System.out.print("2");
    }

}

class OutSide{
    public class Inside{

    }
}






