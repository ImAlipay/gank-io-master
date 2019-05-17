package com.example.lib;


/**
 * 排序算法
 */
public class Sort {

    public static int[] arr = {2, 4, 5, 6, 1, 3, 94, 23, 43, 61, 29, 48};

    public static void main(String[] args) {
//        bubbleSort();
//        selectSort();
//        insertSort();
        quickSort(0, arr.length - 1);
        print();
    }

    /**
     * 冒泡排序
     */
    static void bubbleSort() {
        int i, j;
        for (i = 0; i < arr.length; i++) {
            for (j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(i, j);
                }
            }
        }
        print();
    }

    /**
     * 简单选择排序算法
     */
    static void selectSort() {
        int i, j, min;
        for (i = 0; i < arr.length; i++) {
            min = i;
            for (j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (i != min) {
                swap(i, min);
            }
        }
        print();
    }

    /**
     * 直接插入排序算法
     */
    static void insertSort() {
        int i, j, insertNote;
        for (i = 1; i < arr.length; i++) {
            insertNote = arr[i];
            j = i - 1;
            while (j >= 0 && insertNote < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = insertNote;
        }
        print();
    }

    /**
     * 快速排序
     */
    static void quickSort(int start, int end) {
        if (start > end) {
            return;
        }
        int divide = divide(start, end);
        quickSort(start, divide - 1);
        quickSort(divide + 1, end);
    }

    static int divide(int start, int end) {
        //以最右边的元素作为基准值
        int base = arr[end];
        //start一旦等于end,就说明左右两个指针合并到同一个位置，可以结束此轮循环
        while (start < end) {
            while (start < end && arr[start] <= base) {
                start++;
            }
            //上边的while循环结束时，就说明当前的arr[start]的值比基准值大，应与基准值进行交换
            if (start < end) {
                swap(start, end);
                //交换后，此时那个被调动的值也同时掉到了正确的位置（基准值右边），因此右边同时也要向前移动一位
                end--;
            }
            while (start < end && arr[end] >= base) {
                //从右边开始遍历，如果比基准值大，就继续向左走
                end--;
            }
            //上边的循环结束，就说明当前的arr[end]的值比基准值小，应与基准值交换
            if (start < end) {
                swap(start, end);
                //交换后，此时的那个被调换的值也同时调到了正确的位置（基准值左边），因此左边也要同时向右移动一位
                start++;
            }
        }
        return end;
    }

    /**
     * 数组交互数据
     *
     * @param i
     * @param j
     */
    private static void swap(int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }


    static void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
