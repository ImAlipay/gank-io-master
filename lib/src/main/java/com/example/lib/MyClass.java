package com.example.lib;

import java.util.Queue;
import java.util.Stack;

public class MyClass {

   static int[] arr = new int[5];

    public static void main(String[] args) {
        //单链表
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addAtTail(1);
        linkedList.addAtTail(3);
        linkedList.addAtTail(4);
        linkedList.addAtTail(7);
        linkedList.addAtTail(9);
        linkedList.addAtTail(2);
        linkedList.addAtHead(-2);
        System.out.println(linkedList.toString());

        //栈
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        Integer pop = stack.pop();
        System.out.println(pop);


    }


}
