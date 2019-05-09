package com.example.lib;

/**
 * 单链表
 * @param <T> 存储的元素
 */
public class LinkedList<T> {

    //头结点
    private Node head;
    //尾结点
    private Node tail;
    //链表长度
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
    }

    //链表长度
    public int getLength() {
        return size;
    }

    //是否含有元素
    public boolean isEmpty() {
        return size == 0;
    }

    //清空链表
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    //通过索引获取节点
    public Node getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node node = head;
        for (int i = 0; i < size; i++, node = node.next) {
            if (i == index) {
                return node;
            }
        }
        return null;
    }

    //头插入
    public void addAtHead(T element) {
        head = new Node<T>(element, head);
        //如果是空链表就让尾指针指向头指针
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    //尾插入
    public void addAtTail(T element) {
        if (head == null) {
            head = new Node<T>(element, null);
            tail = head;
        } else {
            Node<T> node = new Node<>(element, null);
            tail.next = node;
            //尾节点后移
            tail = node;
        }
        size++;
    }

    //在指定位置插入
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        if (index == 0) {
            addAtHead(element);
        }
        if (index == size) {
            addAtTail(element);
        }
        //中间插入
        if (index > 0 && index < size) {
            Node insNode = new Node<T>(element, null);
            Node nextNode = getNodeByIndex(index);
            Node preNode = getNodeByIndex(index - 1);
            preNode.next = insNode;
            insNode.next = nextNode;
            size++;
        }
    }

    public void delete(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        int flag = index -1;
        Node node = getNodeByIndex(index);
        if (flag<0) {
            //删除的为头节点
            head=head.next;
            node =null;
        } else {
            Node preNode = getNodeByIndex(index - 1);
            preNode.next = node.next;
            //如果删除的是最后一个元素，尾节点前移
            if (index==size-1) {
                tail = preNode;
            }
        }
        size--;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node node = head;
        for (int i = 0; i < size; i++) {
            builder=builder.append(node.getData()+"->");
            node=node.next;
        }
        return String.valueOf(builder);
    }

    //获取指定位置的元素
    public T get(int index) {
        return (T)getNodeByIndex(index).getData();
    }

    //获取指定元素索引
    public int locate(T element) {
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (element.equals(getNodeByIndex(i).getData())) {
                return i;
            }
        }
        return -1;
    }
}
