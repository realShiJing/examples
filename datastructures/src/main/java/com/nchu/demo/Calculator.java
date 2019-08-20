package com.nchu.demo;

import com.nchu.stack.ArrayStack;

import com.nchu.stack.LinkedStack;
import com.nchu.stack.Stack;
import org.junit.Test;

/**
 * @Decription 栈的简单应用（初级计算器）
 * 不包含 小括号、只支持 + - * / 运算
 *
 * 使用栈完成表达式计算的思路
 *
 * 定义两个栈，数栈用于存放数据，符号栈用于存放运算符
 *
 *1. 通过一个 index  值（索引），来遍历表达式
 *2. 如果是一个数字,且下个值是符号， 就直接入数栈
 *   否则继续遍历，并组装成一个多位数值
 *3. 如果是一个符号,  就分如下情况
 *  3.1 如果发现当前的符号栈为空，就直接入栈
 *  3.2 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符，
 *  就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，
 *  然后将当前的操作符入符号栈， 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
 *4. 当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
 *5. 最后在数栈只有一个数字，就是表达式的结果
 * @Author yangsj
 * @Date 20190820 9:58
 **/
public class Calculator {

    @Test
    public void test(){
        String expression = "7*20+1-41";
        //遍历索引
        int index = 0;
        //数栈中弹出的两个数值
        int num1;
        int num2;
        //遍历得到的每个字节
        char val;
        //需入栈的数值
        String num = "";
        //数栈
        Stack numStack = new LinkedStack(10);
        //符号栈
        Stack operStack = new LinkedStack(10);
        while (index != expression.length()){
            val = expression.substring(index,index+1).charAt(0);
            if (isOper(val)){//如果是运算符
                if(operStack.isEmpty()){
                    operStack.push(val);
                }else{
                    //新的运算符的优先级，小于或等于符号栈中栈顶的优先级时，弹出两个数，进行计算
                    if(priority(val) <= priority(peek(operStack))){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        int oper = operStack.pop();
                        numStack.push(cal(num1,num2,oper));
                        operStack.push(val);
                    }else{
                        operStack.push(val);
                    }
                }
            }else{//如果是数值
                num +=val; //组装num

                //如果遍历到最后一个字节,直接入栈
                if(index == expression.length()-1){
                    numStack.push(Integer.valueOf(num));
                    break;
                }
                char next = expression.substring(index+1,index+2).charAt(0);
                if(isOper(next)){//下个字节为运算符，直接入栈
                    numStack.push(Integer.valueOf(num));
                    num = ""; //数值置空
                }//下个字节为数值时继续遍历
            }
            index++;
        }
        //表达式字符串遍历完成后，将数栈中的数据一一弹出进行计算
        while (!operStack.isEmpty()){
            int oper = operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            numStack.push(cal(num1,num2,oper));
        }
        int result = numStack.pop();
        System.out.println(expression+"=" +result);
    }

    /**
     * @Description 判断是否是运算符
     * @Author yangsj
     * @Date 2019/8/20 10:16
     **/
    public  boolean isOper(char val){
        return val == '+' || val == '-'|| val =='*' || val == '/';
    }


    /**
     * @Description 获取运算符的优先级
     * @Author yangsj
     * @Date 2019/8/20 10:20
     **/
    public int priority(int val){
        if(val == '+' || val == '-' ){//加减的优先级较低
            return 0;
        }else if(val == '*' || val == '/'){//乘除的优先级较高
            return 1;
        }else{
            return -1;
        }
    }


    /**
     * @Description 根据传入的运算符计算两个数的值
     * @Author yangsj
     * @Date 2019/8/20 14:59
     **/
    public int cal(int num1 ,int num2 ,int oper){
        int num = 0 ;//默认返回零
        switch (oper){
            case '+':
                num =  num2 + num1;
                break;
            case '-':
                num =  num2 - num1;
                break;
            case '*':
                num = num2 * num1;
                break;
            case '/':
                num = num2 / num1;
                break;
            default:
                break;
        }
        return num;
    }

    /**
     * @Description 获取符号栈的栈顶元素,不改变栈结构
     * @Author yangsj
     * @Date 2019/8/20 11:54
     **/
    public int peek(Stack stack){
        int val = stack.pop();
        stack.push(val);
        return val;
    }
}
