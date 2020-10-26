package com.algrothm.exercise.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给你一个由 n 个整数组成的数组 nums，和两个由 m 个整数组成的数组 l 和 r，后两个数组表示 m 组范围查询，其中第 i 个查询对应范围 [l[i], r[i]] 。所有数组的下标都是 从 0 开始 的。
//
//返回 boolean 元素构成的答案列表 answer 。如果子数组 nums[l[i]], nums[l[i]+1], ... , nums[r[i]] 可以 重新排列 形成 等差数列 ，answer[i] 的值就是 true；否则answer[i] 的值就是 false 。
public class Two {
    public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> result = new ArrayList<>(l.length);

        for (int i = 0; i < l.length; i++) {
            // 判断子数组nums[l[i]], nums[r[i]]是否是等差数组
            int[] subArray = new int[r[i] - l[i] + 1];
            for (int j = l[i], k = 0; j <= r[i]; j++, k++) {
                subArray[k] = nums[j];
            }
            Arrays.sort(subArray);
            int sub = subArray[1] - subArray[0];
            boolean isSubarray = true;
            for (int j = 1; j < subArray.length - 1; j++) {
                if (subArray[j+1] - subArray[j] != sub) {
                    isSubarray = false;
                    break;
                }
            }

            result.add(isSubarray);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(checkArithmeticSubarrays(new int[]{-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10}, new int[]{0,1,6,4,8,7}, new int[]{4,4,9,7,9,10}));
    }
}
