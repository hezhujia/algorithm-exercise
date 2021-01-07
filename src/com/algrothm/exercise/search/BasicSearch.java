package com.algrothm.exercise.search;

import java.util.List;

public class BasicSearch {

    private BasicSearch(){}

    // 顺序数组查询符合条件的元素
    public static int BinarySearchEqual(List<Integer> sortedArray, int key, int start, int end) {
        if (end < start) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (sortedArray.get(mid) == key) {
            return mid;
        } else if (sortedArray.get(mid) < key) {
            return BinarySearchEqual(sortedArray, key, mid+1, end);
        } else {
            return BinarySearchEqual(sortedArray, key, start, mid-1);
        }
    }

    // 查找比key小的最大的数的位置
    public static int binarySearchLess(List<Integer> sortedArray, int key, int start, int end) {
        if (end <= start) {
            return sortedArray.get(start) < key ? start : start-1;
        }

        int mid = (start + end) / 2;
        if (sortedArray.get(mid) >= key) {
            return binarySearchLess(sortedArray, key, start, mid-1);
        } else {
            return binarySearchLess(sortedArray, key, mid+1, end);
        }
    }

    // 查找比key大的最小的数的位置
    public static int binarySearchLarge(List<Integer> sortedArray, int key, int start, int end) {
        if (end <= start) {
            if (sortedArray.get(end) > key) {
                return end;
            } else if (end == sortedArray.size()-1) {
                return -1;
            } else {
                return end+1;
            }
        }

        int mid = (start + end) / 2;
        if (sortedArray.get(mid) <= key) {
            return binarySearchLarge(sortedArray, key, mid+1, end);
        } else {
            return binarySearchLarge(sortedArray, key, start, mid-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(binarySearchLarge(List.of(1, 2, 3, 3, 4, 4, 5), 5, 0, 6));
    }
}
