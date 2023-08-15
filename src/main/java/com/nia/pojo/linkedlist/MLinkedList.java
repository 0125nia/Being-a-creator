package com.nia.pojo.linkedlist;

import com.nia.pojo.MList;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 自定义双向链表类
 *
 * @param <T> 泛型
 */
public class MLinkedList<T> implements MList<T>, Iterable<T>, Serializable {

    private Node<T> first;//头结点
    private Node<T> last;//尾结点
    private int size;//链表长度

    /**
     * 无参构造方法
     */
    public MLinkedList() {
        size = 0;
    }

    /**
     * @return 返回双向链表的长度
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 检查双向链表是否为空
     *
     * @return 返回检查结果
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断双向链表中是否有该元素
     *
     * @param t 需要判断的元素
     * @return 返回元素是否存在的结果
     */
    @Override
    public boolean contains(T t) {
        for (T next : this) {
            if (next.equals(t))
                return true;
        }
        return false;
    }

    /**
     * 在双向链表的最前端添加元素
     *
     * @param t 添加的元素
     */
    public void addFirst(T t) {
        final Node<T> f = first;
        final Node<T> newNode = new Node<>(null, t, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.setPrevious(newNode);
        size++;
    }

    /**
     * 在双向链表的最后端添加元素
     *
     * @param t 添加的元素
     */
    public void addLast(T t) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(l, t, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.setNext(newNode);
        size++;
    }

    /**
     * 添加元素
     *
     * @param t 需要添加的元素
     * @return 返回添加是否成功的结果
     */
    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    /**
     * 删除链表中的第一个元素
     *
     * @return 返回删除的元素
     */
    public T removeFirst() {
        final Node<T> f = first;//获取头结点
        if (f == null)//若第一个结点为null 则抛出异常
            throw new NoSuchElementException();
        T t = f.getT();//获取第一个元素
        Node<T> next = f.getNext();//获取第二个元素
        f.setPrevious(null);
        f.setNext(null);
        first = next;//将第二个元素设置为头结点
        if (next == null) {
            //如果下一个元素即第二个元素为null 则表示链表中删除前只有一个元素
            //故将尾结点赋值为null
            last = null;
        } else {
            //若删除前不止一个元素
            //则将删除前的第二个结点作为头结点
            next.setPrevious(null);
        }
        //返回删除的元素
        return t;
    }

    /**
     * 删除最后一个元素
     *
     * @return 返回最后一个元素
     */
    public T removeLast() {
        final Node<T> l = last;//获取最后一个结点
        if (l == null)
            throw new NoSuchElementException();
        T t = l.getT();//获取最后一个元素
        Node<T> previous = l.getPrevious();
        l.setPrevious(null);
        l.setNext(null);
        last = previous;
        //判断删除前是否只有一个元素
        if (previous == null)
            first = null;
        else
            previous.setNext(null);
        //返回删除前的元素
        return t;
    }

    /**
     * 移除元素
     *
     * @param t 需要移除的元素
     * @return 返回移除是否成功的结果
     */
    @Override
    public boolean remove(T t) {
        if (isEmpty())//判断是否为空
            return false;
        Iterator<T> it = this.iterator();//获取迭代器对象
        while (it.hasNext()) {
            if (t.equals(it.next())) {//判断是否存在该元素
                it.remove();//移除该元素
                size--;//长度减少
                return true;
            }
        }
        return false;
    }

    /**
     * 根据索引查找元素
     *
     * @param index 索引
     * @return 元素
     */
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node;
        //判断索引对应的元素在链表的靠前部分还是靠后部分
        if (size - index > size / 2) {
            node = first;
            for (int i = 0; i < index; i++)
                node = node.getNext();
        } else {
            node = last;
            for (int i = size; i > index; i--)
                node = node.getPrevious();
        }
        return node.getT();
    }


    /**
     * 获取双向链表的迭代器方法
     *
     * @return 返回该链表的迭代器
     */
    @Override
    public Iterator<T> iterator() {
        return new MLinkedListIterator();
    }

    /**
     * 自定义迭代器内部类
     * 便于遍历使用
     */
    private class MLinkedListIterator implements Iterator<T> {

        private Node<T> current = first;//当前迭代器所指向的结点

        /**
         * 检查是否还有下一个元素可迭代
         *
         * @return 返回是否有下一个元素的boolean值
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * 返回当前元素，并将迭代器移动到下一个位置
         *
         * @return 返回当前结点元素
         */
        @Override
        public T next() {
            if (!hasNext()) {
                //若不存在下一个元素则抛出异常
                throw new NoSuchElementException();
            }
            T t = current.getT();
            //迭代到下一个元素
            current = current.getNext();
            return t;
        }

        @Override
        public void remove() {
            //判断迭代器是否指向结点
            if (current == null) {
                throw new IllegalStateException("迭代器没有指向任何结点");
            }

            //先获取该结点的前一个结点与后一个结点
            Node<T> previous = current.getPrevious();
            Node<T> next = current.getNext();
            //分别判断是否为null
            if (previous == null) {
                //若前一个结点为null,则表示当前结点为头结点
                //则将下一个结点当做头结点即可
                next.setPrevious(null);
                first = next;
            } else {
                //若不为null
                //则将前一个结点的后地址赋值为后一个结点
                previous.setNext(next);
            }

            if (next == null) {
                //若后一个结点为null,则表示当前结点为尾结点
                //则将上一个结点当做尾结点
                previous.setNext(null);
                last = previous;
            } else {
                //若不为null
                //则将下一个结点的后地址赋值给前一个结点
                next.setPrevious(previous);
            }

            current = next;
        }
    }


}
