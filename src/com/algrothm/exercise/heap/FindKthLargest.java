package com.algrothm.exercise.heap;

public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {

        // 创建大小为k的小顶堆
        int[] heap = new int[k];
        int heapLength = 0;

        for (int num : nums) {
            heapLength = heapPush(heap, heapLength, num);
        }

        return heap[0];
    }

    private int heapPush(int[] heap, int length, int num) {
        // 直接插入
        if (length < heap.length) {
            heap[length] = num;
            // 从下到上堆化
            int i = length;
            // i 的父节点为 (i-1)/2
            while (i > 0) {
                if (heap[(i-1)/2] > heap[i]) {
                    swap(heap, i, (i-1)/2);
                    i = (i-1)/2;
                } else {
                    break;
                }
            }
            return ++length;
        }

        // 对比堆顶元素
        if (heap[0] < num) {
            heap[0] = num;
            // 从上到下堆化
            int i = 0;
            while (i < length) {
                int minpos = i;
                if (2*i+1 < length && heap[minpos] > heap[2*i+1]) minpos = 2 * i + 1;
                if (2*i+2 < length && heap[minpos] > heap[2*i+2]) minpos = 2 * i + 2;
                if (minpos == i) break;
                swap(heap, i, minpos);
                i = minpos;
            }
        }
        return length;
    }

    private void swap(int[] nums, int a1, int a2) {
        int temp = nums[a1];
        nums[a1] = nums[a2];
        nums[a2] = temp;
    }

    public static void main(String[] args) {
        int[] array = {3,2,3,1,2,4,5,5,6};
        FindKthLargest obj = new FindKthLargest();
        System.out.println(obj.findKthLargest(array, 4));
    }
}
