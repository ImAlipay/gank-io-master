package com.student.achievemnt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 学生成绩
 */
public class StudentAchievement {
    int studentAchievement;
    public static final String STUDENT_ACHIEVEMENT = "s";

    private void studentAchievement() {

    }

    public static void main(String[] args) {
//        fileReader();
//        int i = computeStairs(10);
////        System.out.println(i);

        int arr[] = {1,2,3,4,5};
        getTwoSum(arr,6);
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    /**
     * 读取文件
     */
    public static void fileReader() {
        try {
            FileReader fileReader = new FileReader("./lib/test.txt");
            int i = 0;
            while ((i = fileReader.read()) != -1) {
                System.out.println((char) i);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 走台阶
     *
     * @param stair
     * @return
     */
    private static int computeStairs(int stair) {
        if (stair <= 0) {
            return 0;
        }
        if (stair == 1) {
            return 1;
        }
        if (stair == 2) {
            return 2;
        }
        return computeStairs(stair - 1) + computeStairs(stair - 2);
    }

    /**
     * 一个已经排序好的有序数组，给出一个数，需要从数组中找出两个元素相加等于这个元素，要求时间复杂度为O（n）
     * @param array
     * @param sum
     */
    public static void getTwoSum(int[] array, int sum) {
        if (array == null || array.length < 1) {
            return;
        }
        int front = 0;
        int end = array.length - 1;
        int currentSum = 0;
        while (front < end) {
            currentSum = array[front] + array[end];
            if (sum == currentSum) {
                System.out.println(array[front] + " " + array[end]);
                break;
            }
            //由于数组有序，位置判断
            if (sum > currentSum) {
                front++;
            } else {
                end--;
            }
        }
    }

    /**
     * 从1000万个数中找出前100个最大的数；
     *
     * 1.算法如下：根据快速排序划分的思想
     * (1) 递归对所有数据分成[a,b）b（b,d]两个区间，(b,d]区间内的数都是大于[a,b)区间内的数
     * (2) 对(b,d]重复(1)操作，直到最右边的区间个数小于100个。注意[a,b)区间不用划分
     * (3) 返回上一个区间，并返回此区间的数字数目。接着方法仍然是对上一区间的左边进行划分，分为[a2,b2）b2（b2,d2]两个区间，
     * 取（b2,d2]区间。如果个数不够，继续(3)操作，如果个数超过100的就重复1操作，直到最后右边只有100个数为止。
     *
     *
     * 3.分块查找
     * 先把100w个数分成100份，每份1w个数。
     * 先分别找出每1w个数里面的最大的数，然后比较。找出100个最大的数中的最大的数和最小的数，取最大数的这组的第二大的数，与最小的数比较。。。。
     */

    /**
     * 系统出现了异常缓慢，如何定位问题，如何解决缓慢问题?
     *
     * 代码某个位置有阻塞性的操作，导致该功能调用整体比较耗时，但出现是比较随机的；
     * 某个线程由于某种原因而进入WAITING状态，此时该功能整体不可用，但是无法复现；
     * 由于锁使用不当，导致多个线程进入死锁状态，从而导致系统整体比较缓慢。
     */
}
