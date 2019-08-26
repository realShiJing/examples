package com.nchu.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Decription 逆波兰表达式 实现计算器
 *
 * 输入一个逆波兰表达式(后缀表达式)，使用栈(Stack), 计算其结果
 * 支持小括号和多位数整数
 * @Author yangsj
 * @Date 20190823 14:34
 **/
public class PolandNotation {


    /**
     * @Description 测试验证方法
     * @Author yangsj
     * @Date 2019/8/23 14:37
     **/
    @Test
    public void  test(){
        //先定义给逆波兰表达式
        //(30+4)×5-6  => 30 4 + 5 × 6 - => 164
        //1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -  =>16
        String expression = "1+((2+3)*4)-5";
        //中缀表达式 链表
        List<String> infixList = strToList(expression);
        System.out.println("中缀表达式："+infixList);
        //中缀表达式 转后缀表达式
        List<String> suffixList = infixToSuffix(infixList);
        // String expression = "30 4 + 5 * 6 -";
        System.out.println("后缀表达式："+suffixList);
        /* //将String转换为list
        List<String> list = expToList(expression);*/
        //对转换成list的逆波兰表达式进行计算
        Integer result = cal(suffixList);
        //控制台输出结果
        System.out.println(result);

    }
    /**
     * @Description 获取运算符的优先级
     * @Author yangsj
     * @Date 2019/8/20 10:20
     **/
    public int priority(String val){
        if(val.equals("+")|| val.equals("-")){//加减的优先级较低
            return 0;
        }else if(val.equals("*")|| val.equals("/")){//乘除的优先级较高
            return 1;
        }else{
            return -1;
        }
    }

    /**
     * @Description 中缀表达式 转 后缀表达式
     *
     *1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     *2) 从左至右扫描中缀表达式；
     *3) 遇到操作数时，将其压s2；
     *4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
     *  1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     *  2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     *  3.否则，将s1栈顶的运算符弹出并压入到s2中，并将运算符入栈 再次转到(4.1)与s1中新的栈顶运算符相比较；
     *5) 遇到括号时：
     * (1) 如果是左括号“(”，则直接压入s1
     * (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     *6) 重复步骤2至5，直到表达式的最右边
     *7) 将s1中剩余的运算符依次弹出并压入s2
     *8) 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     * @Author yangsj
     * @Date 2019/8/26 15:15
     **/
    public List<String> infixToSuffix(List<String> list){
        Stack<String> operStack = new Stack();
        ArrayList<String> result = new ArrayList<>();
        for(String str : list) {
            if(str.matches("\\d+")){//匹配多位数
                //遇到操作数时，将其存入结果集
                result.add(str);
            }else if(str.equals("(")){
                //遇到左括号时，将其压入符号栈
                operStack.push(str);
            }else if(str.equals(")")){
                //遇到右括号时，将符号栈的符号依次弹出，并存入结果集
                while (!operStack.peek().equals("(")){
                    result.add(operStack.pop());
                }
                //遍历完之后将 左括号 舍弃
                operStack.pop();
            }else{
                //不为空或不为左括号 且 优先级 比栈顶的 运算符优先级低 时 将符号栈顶的运算符弹出并存入结果集
                if(!(operStack.empty()||operStack.peek().equals("("))&&priority(str)<priority(operStack.peek())){
                    result.add(operStack.pop());
                }

                //最后将 符号 压入符号栈
                operStack.push(str);

            }
        }
        //将符号栈中剩余的运算符依次弹出并存入结果集
        while (!operStack.empty()){
            result.add(operStack.pop());
        };

        return result;
    }

    /**
     * @Description 中缀表达式分拆为ArrayList
     * @Author yangsj
     * @Date 2019/8/26 14:42
     **/
    public List<String> strToList(String expression){
        int index = 0 ;//遍历索引
        StringBuffer num = new StringBuffer() ;//数字字符串，用于拼接多位数
        ArrayList<String> list = new ArrayList<>();
        while (index != expression.length()){
            if(expression.charAt(index)>= 48 && expression.charAt(index)<=57){//数字0-9
                //继续往后遍历，如果仍是数字，就拼接
                while (index !=expression.length()&&expression.charAt(index)>= 48 && expression.charAt(index)<=57){
                    num.append(expression.charAt(index));
                    index ++;
                }
                list.add(num.toString());
                num.setLength(0);//用于拼接的字符串置空
            }else{//符号
                list.add(String.valueOf(expression.charAt(index)));
                index++;
            }
        }
        return list;
    }
    /**
     * @Description 将逆波兰表达式转换为 ArrayList便于后续处理
     * @Author yangsj
     * @Date 2019/8/23 14:41
     **/
    public List<String> expToList(String expression){
        List<String > list = new ArrayList<>();
        //将字符串表达式通过 空格 分割 转换为字符串数组
        String[] items = expression.split(" ");
        for (String item : items){
            list.add(item);
        }
        return list;
    }
    
    
    /**
     * @Description 后缀表达式的计算机求值
     *
     * 从左至右扫描表达式，遇到数字时，将数字压入堆栈，
     * 遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算(次顶元素 和 栈顶元素)
     * 并将结果入栈；重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果
     * @Author yangsj
     * @Date 2019/8/23 14:48
     **/
    public Integer cal(List<String> list){
        //用自定义实现的栈存储数据
        Stack<Integer> stack = new Stack();
        for(String val :list){
            if(val.matches("\\d+")){//如果是数字，就将数值入栈
                stack.push(Integer.parseInt(val));
            }else {//如果是运算符，弹出栈顶的两个数，用运算符对它们做相应地计算
                Integer num1 = Integer.valueOf(stack.pop());
                Integer num2 = Integer.valueOf(stack.pop());
                Integer num;
                switch (val){
                    case "+":
                        num = num2 + num1;
                        break;
                    case "-":
                        num = num2 - num1;
                        break;
                    case "*":
                        num = num2 * num1;
                        break;
                    case "/":
                        num = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("运算符非法！");
                }
                //运算的结果入栈
                stack.push(num);
            }
        }
        return Integer.valueOf(stack.pop());
    }
}
