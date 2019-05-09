package com.example.lib;

public class Node<T> {
    //存放的数据
    private T data;
    //指向的下一个结点
    public Node next;

    public Node(){}

    public Node(T data,Node next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
