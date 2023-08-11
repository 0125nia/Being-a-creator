package com.nia.pojo.linkedlist;

/**
 * 结点类
 * @param <T> 泛型
 */
public class Node<T> {

    private Node<T> previous;//前一个结点
    private T t;//当前结点元素
    private Node<T> next;//后一个结点

    public Node() {
        previous = null;
        next = null;
    }

    public Node(Node<T> previous, T t, Node<T> next) {
        this.previous = previous;
        this.t = t;
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

}
