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
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Math.abs;

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
        InterFaceTest.test();
    }

    /**
     * @Description 增强for循环遍历删除会出现异常
     * @Author yangsj
     * @Date 2019/10/23 16:42
     **/
    @Test
    public void test30(){
       List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        for (String i : list) {
            if(i.equals("2")){
                list.remove(i);
            }
        }
    }

    /**
     * @Description  正序删除
     * 简单来说，移除元素后，后边的元素会往前移动，将空白填充上。
     * 第一次循环，i=0，移除了第一个元素后，i变为1，但是，同时，由于1的空出，2、3都往前移动一个位置，所以，索引位置不再是1、2，而是0、1
     * 第二次循环，i=1，3的索引是1，所以只将3加1，而2已经过去了。
     * @Author yangsj
     * @Date 2020/3/1 4:49 下午
     **/
    @Test
    public void test31(){
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        a.add("3");
        for (int i = 0; i < a.size(); i++) {
            String e = a.get(i);

            if("1".equals(e)){
                a.remove(i);
                // i=i-1; 修正index
            }else{
                a.set(i,e+"1");
            }

        }
        System.out.println(a);
    }
    /**
     * @Description   倒序删除不会出现数据遗漏的问题
     * @Author yangsj
     * @Date 2020/3/1 4:51 下午
     **/
    @Test
    public void test32() {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        a.add("3");

        for (int i = a.size() - 1; i >= 0; i--) {
            String e = a.get(i);
            if ("2".equals(e)) {
                a.remove(i);
            } else {
                a.set(i, e + "1");
            }
        }
        System.out.println(a);
    }

    /**
     * @Description LinkedHashMap 实现 LRU  缓存算法（Least Recently Used）
     * @Author yangsj
     * @Date 2020/3/6 16:41
     **/
    @Test
    public void testLinkedHashMap(){
        // 默认构造方法 accessOrder = false; 是按照插入顺序排序,所以要利用另外的构造方法传入 accessOrder = true;
        // 使 linkedHashMap 支持 按访问顺序排序，如从未被访问过，则依然按照插入书序排序
        LinkedHashMap<String, String> accessOrderedMap = new LinkedHashMap<String, String>(16,0.75f,true){
            // LinkedHashMap 默认添加元素时，不移除最老节点，通过重写removeEldestEntry 自定义，当链表的节点数量大于一定数目时，进行链表头结点的移除
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size()>3;
            }
        };

        accessOrderedMap.put("Project2", "Panama");
        accessOrderedMap.put("Project3", "Loom");
        accessOrderedMap.put("Project1", "Valhalla");

        // 此时链表的的顺序为插入的顺序
        accessOrderedMap.forEach((k,v)->{
            System.out.println(k + ":" + v);
        });

        // 模拟访问
        accessOrderedMap.get("Project3");
        accessOrderedMap.get("Project1");
        accessOrderedMap.get("Project3");
        System.out.println("被访问后，链表的顺序：");
        // 此时链表的顺序为被访问的顺序，未被访问的节点放在head，按访问顺序，最后被访问的节点放在尾部
        accessOrderedMap.forEach((k,v)->{
            System.out.println(k + ":" + v);
        });

        //触发删除
        accessOrderedMap.put("Project4", "Mission Control");
        // 如果该 linkedhashMap从未被访问，则链表的顺序为插入的顺序
        System.out.println("Oldest entry should be removed:");
        accessOrderedMap.forEach((k,v)->{
            System.out.println(k + ":" + v);
        });
    }

    /**
     * @Description   ArrayList去重
     * @Author yangsj
     * @Date 2020/3/1 5:07 下午
     **/
    @Test
    public void test33(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(4);
        list = new ArrayList<>(new HashSet<>(list));
        System.out.println(list);
    }
    @Test
    public void test34(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        for (String s : list) {
            if(s.equals("1")){
                list.remove(s);
            }
        }
    }


    /**
     * @Description 求字符串中数字之和
     * @Author yangsj
     * @Date 2020/3/26 13:55
     **/
    @Test
    public void test35(){
        String str = "我30你40他50";
        String[] s = str.split("[^0-9]");
        int sum = 0 ;
        for (String s1 : s) {
            if(!s1.equals("")){
                sum += Integer.parseInt(s1);
            }
        }
        System.out.println(sum);
    }


    /**
     * @Description 求二进制表示数的中的1的个数
     * @Author yangsj
     * @Date 2020/3/26 13:53
     **/
    @Test
    public void test36(){
      int i = 255;
      int sum = 0;
      while (i!=0){
          sum += i&1;
          i>>=1;
      }
    System.out.println(sum);
    }


    /**
     * @Description 开根号
     * @Author yangsj
     * @Date 2020/3/26 14:28
     **/
    @Test
    public void test37(){
        int n = 3;
        double x = 1.0;//设置初值
        double p = 1e-15;//设置精度
        while(abs(x*x - n) > p)
        {
            x = (x + n / x) / 2.0;
        }
        System.out.println(Math.sqrt(n));
        System.out.println(x);
    }


    /**
     * @Description 第1个人10，第2个比第1个人大2岁，依次递推，请用递归方式计算出第8个人多大？
     * @Author yangsj
     * @Date 2020/3/26 14:36
     **/
    @Test
    public void test38(){
        System.out.println(computeAge(8));
    }

    public int computeAge(int i){
        if( i == 1){
            return 10;
        }
        return  computeAge(i-1)+2;
    }


    /**
     * @Description 在一个二维数组中，每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。请完成一个函数，
     * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * @Author yangsj
     * @Date 2020/3/26 15:20
     **/
    @Test
    public void test39(){
        int[][] array = {{1,2,3},{4,5,6},{7,8,9}};
        boolean b = find(10, array);
        System.out.println(b);
    }

    /**
     * 对每一行进行二分查找
     * @param target
     * @param array
     * @return
     */
    public boolean find(int target, int [][] array) {
        //遍历每一行
        for(int i=0; i<array.length; i++){
            int left=0;//行内左指针
            int rigtht = array[i].length-1;//行内右指针
            while(left <= rigtht){
                int j= (left + rigtht)/2;//计算中间值
                if(target > array[i][j])
                    left = j+1;
                else if(target < array[i][j])
                    rigtht = j-1;
                else
                    return true;
            }
        }
        return false;
    }

    
    /**
     * @Description 一个人上台阶可以一次上1个，2个，问这个人上n层的台阶，总共有几种走法
     * @Author yangsj
     * @Date 2020/3/26 15:58
     **/
    @Test
    public void test40(){
        int sum = func(3);
        System.out.println(sum);

        /*int sum2 = func2(3);
        System.out.println(sum2);*/
    }
    public int func(int x ){
        int sum = 0;
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= x/2; j++) {
                if(i+2*j==x){
                    System.out.println("i1:"+i+",j2:"+j);
                    sum++;
                }
            }
        }
        return sum;
    }

   /* public int func2(int x){
        if(x == 1)
            return 1;
        if(x == 2)
            return 2;
        return func2(x - 1)+func2(x - 2);
    }*/

    /**
     * @Description 计算机的 CPU核数
     * @Author yangsj
     * @Date 2020/3/2 10:07 下午
     **/
    @Test
    public void test41(){
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void test42(){
            CyclicBarrier barrier = new CyclicBarrier(4,()->{
                System.out.println("--------------");
            });
            testCy(barrier);
    }

    public void testCy(CyclicBarrier barrier){
        new Thread(()->{
            System.out.println("A");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            System.out.println("B");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            System.out.println("C");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            System.out.println("D");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }

   /**
    * @Description 给定一个M*N的格子或棋盘，从左下角走到右上角的走法总数（每次只能向右或向上移动一个方格边长的距离）
    * @Author yangsj
    * @Date 2020/3/26 17:08
    **/
   @Test
   public void test43(){
       System.out.println(getTraversal(2,3));
    }

    public int getTraversal(int p, int q) {
        int num = 0;
        if (p == 1 && q == 1) {
            return 1;
        }
        if (p > 1) {
            num += getTraversal(p - 1, q);
        }
        if (q > 1) {
            num += getTraversal(p, q - 1);
        }
        return num;
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






