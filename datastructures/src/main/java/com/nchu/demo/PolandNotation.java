package com.nchu.demo;

import com.nchu.stack.LinkedStack;
import com.nchu.stack.Stack;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        String expression = "30 4 + 5 * 6 -";

        //将String转换为list
        List<String> list = expToList(expression);
        //对转换成list的逆波兰表达式进行计算
        Integer result = cal(list);
        //控制台输出结果
        System.out.println(result);

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
        Stack stack = new LinkedStack(100);
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
