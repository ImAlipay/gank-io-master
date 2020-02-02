package com.example.lib;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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

    private static void proxy() {

    }

    /**
     * 动态代理
     */
    public class MyINvocationHandler implements InvocationHandler {

        // 被代理的实例
        Object obj = null;

        public MyINvocationHandler(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            return method.invoke(this.obj, objects);
        }
    }

    interface Subject {
        public void doSomething(String str);
    }

    public class RealSubject implements Subject {

        @Override
        public void doSomething(String str) {
            System.out.println("do something!------------>" + str);
        }
    }

    public class DynamicProxy<T> {
        public  <T> T newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h) {
            if(true) {
                // 执行前置通知
            }
            return (T)Proxy.newProxyInstance(loader,interfaces,h);
        }
    }


}
