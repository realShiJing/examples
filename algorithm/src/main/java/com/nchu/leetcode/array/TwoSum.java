package com.nchu.leetcode.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Decription 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例：
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @Author yangsj
 * @Date 20190919 16:53
 **/
public class TwoSum {


    /**
     * @Description 测试
     * @Author yangsj
     * @Date 2019/9/19 17:05
     **/
    @Test
    public void test(){
        //初始数组
        int[] nums = {2,7,11,15};
        //目标值（和）
        int target = 9;
        int[] result = test3(nums, target);
        System.out.println("结果为："+Arrays.toString(result));
    }


    /**
     * @Description 暴力破解法
     * 两层循环，时间复杂度为：O(n^2)
     * @Author yangsj
     * @Date 2019/9/19 16:56
     **/
    public  int[] test1(int[] nums, int target){
        //暴力遍历出每种组合
        for(int i = 0 ; i < nums.length ; i++){
            for(int j = i + 1 ; j < nums.length ; j++){
                if(target - nums[i] == nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("no tow sum!");
    }


    /**
     * @Description Map 两次遍历
     * 第一次循环遍历的时间复杂度为O(n)
     * 第二次循环遍历的时间复杂度为O(n)
     * map查找值的速度较快
     * 所以综合的时间复杂度为O(n)
     * @Author yangsj
     * @Date 2019/9/19 17:09
     **/
    public int[] test2(int[] nums, int target){
        Map map = new HashMap();
        //遍历数组，将元素放入map哈希表中，key为数组值，value为索引
        for(int i = 0 ; i < nums.length ; i++){
            map.put(nums[i],i);
        }
        //从map中查找
        for(int i = 0 ; i < nums.length ; i++){
            //需要查找的值
            int val = target - nums[i];
            //如果map包含目标值，而且目标值的索引不是i(防止出现4-2=2,返回结果是同一个值的情况)
            if(map.containsKey(val)&& !map.get(val).equals(i)){
             return  new int[]{i, (int) map.get(val)};
            }
        }
        throw new  IllegalArgumentException("no two sum");
    }


    /**
     * @Description Map一次遍历
     * 给定 nums = [2, 7, 11, 15], target = 26
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 26
     * 所以返回 [0, 1]
     *
     * 0,2
     *    false
     * 1,7
     *    19
     *    false
     * 2,11
     *    15
     *    false
     * 3,15
     *    11
     *    true
     * @Author yangsj
     * @Date 2019/9/19 17:49
     **/
    public int[] test3(int[] nums ,int target){
        Map map = new HashMap();
        for(int i = 0 ; i < nums.length ;i++){
            int val = target - nums[i];
            if(map.containsKey(val)){
                return new int[]{(int) map.get(val), i };
            }
            map.put(nums[i], i);
        }
        throw new  IllegalArgumentException("no two sum!");
    }
}
