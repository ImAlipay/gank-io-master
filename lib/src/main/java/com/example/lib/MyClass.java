package com.example.lib;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
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

        String s = "abcdef";
        String p = "e";
        int i = StringMatcher.ViolentMatch(s.toCharArray(), p.toCharArray());
        System.out.println("位置：" + i);


    }

    private static void classGen() {
        try {
            Class<?> aClass = Class.forName("com.example.lib.Node");
            //获取class对象的所有属性
            Field[] fields = aClass.getDeclaredFields();
            //获取class对象的public属性
            Field[] publicFields = aClass.getFields();
            //获取class对象的制定属性
            Field publicTestField = aClass.getDeclaredField("publicTest");
            //获取class对象指定的public属性
            Field testField = aClass.getField("test");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
