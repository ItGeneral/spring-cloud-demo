package com.example.demo;

import java.util.Arrays;

/**
 * @author SongJiuHua.
 * @date Created on 2018/7/16.
 * @description 冒泡排序 相邻两个元素之间进行比较，小的排前面，大的排后面
 */
public class BubbleSort {

    /**
     * 基础版
     * @param array
     */
    public static void sort(int[] array){
        int temp;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length - i - 1; j++){
                if (array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    /**
     * 优化版 如果数据没有交换（说明已经是有序了的），则结束后续的排序
     * @param array
     */
    public static void sortUpgrade(int[] array){
        int temp;
        for (int i = 0; i < array.length; i++){
            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++){
                if (array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted){
                break;
            }
        }
    }

    /**
     * 持续优化：后面的数据已经有序了后，不需要再遍历循环后续数据了
     * @param array
     */
    public static void sortUpgradeImprove(int[] array){
        int temp;
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里位置
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++){
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++){
                if (array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    isSorted = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5,5,4,4,3,1,2,6,8,9};
        long t1 = System.nanoTime();
        sort(array);
        long t2 = System.nanoTime();
        System.out.println(t2 - t1);
        System.out.println("sort:" + Arrays.toString(array));

        long t3 = System.nanoTime();
        sortUpgrade(array);
        long t4 = System.nanoTime();
        System.out.println(t4 - t3);
        System.out.println("sortUpgrade:" + Arrays.toString(array));

        long t5 = System.nanoTime();
        sortUpgradeImprove(array);
        long t6 = System.nanoTime();
        System.out.println(t6 - t5);
        System.out.println("sortUpgradeImprove:" + Arrays.toString(array));

    }

}
