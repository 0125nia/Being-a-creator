package com.nia.pojo.arraylist;

import com.nia.pojo.MList;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 实现自定义ArrayList
 *
 * @param <T> 使用泛型代表MArraylist的类型
 */
public class MArrayList<T> implements Serializable, MList<T>, Iterable<T> {

    @Serial
    private static final long serialVersionUID = 5152224678220125L;

    //最大容量
    private static final int MAX_CAPACITY = 1 << 30;//1*2^30即2的30次方
    //最小容量
    private static final int MIN_CAPACITY = 1 << 4;//1*2^4即16
    //数组
    private T[] array;
    //数组内元素的个数
    private int size;
    //数组容量
    private int capacity;

    /**
     * 无参构造方法
     * 生成容量为最小容量的数组
     */
    public MArrayList() {
        this.capacity = MIN_CAPACITY;
        array = (T[]) new Object[capacity];
        //初始化数组个数
        size = 0;
    }

    /**
     * 有参构造方法
     * @param array 数组参数
     */
    public MArrayList(T[] array) {
        size = array.length;//长度为数组长度
        this.array = array;
        capacity = (int) (size * 1.5);//容量设置为长度的1.5倍
    }

    /**
     * 自动扩容方法
     */
    private void AutoExpandCapacity() {
        //当数组长度的1.5倍是否大于数组容量且数组长度不超过最大容量
        if (size * 1.5 > capacity && size < MAX_CAPACITY) {
            //将新容量扩大到原容量的1.5倍
            int NewCapacity = capacity + (capacity >> 1);
            //若原容量大于最大容量,则新容量赋值为最大容量
            if (capacity < MIN_CAPACITY)
                NewCapacity = MIN_CAPACITY;
            //若原容量小于最小容量,则新容量赋值为最小容量
            if (capacity > MAX_CAPACITY)
                NewCapacity = MAX_CAPACITY;
            //定义新容量的数组
            T[] newArray = (T[]) new Object[NewCapacity];
            //遍历赋值 完成扩容
            int i = 0;
            for (T t : array) {
                newArray[i] = t;
                i++;
            }
            array = newArray;
            capacity = NewCapacity;
        }
    }

    /**
     * 获取数组中元素的个数
     *
     * @return 返回元素的个数
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 判断是否为空
     *
     * @return 返回为空结果
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断list中是否存在该元素
     *
     * @param t 需要判断的元素
     * @return 是否存在该元素的结果
     */
    @Override
    public boolean contains(T t) {
        //若索引小于0则表示不存在该元素
        return indexOf(t) >= 0;
    }

    /**
     * 添加元素到MArrayList中
     *
     * @param t 添加的单个元素
     * @return 返回Boolean值表示是否添加成功
     */
    @Override
    public boolean add(T t) {
        //查看是否进行扩容
        this.AutoExpandCapacity();

        array[size] = t;
        size++;
        return true;
    }

    /**
     * 删除下标对应的元素
     * @param index 需要删除元素的下标
     * @return 返回是否删除成功的标志1
     */
    public boolean remove(int index) {
        if (index < size && index >= 0) {
            T[] newArray = (T[]) new Object[capacity];
            int j = 0;
            //除了要删除的元素,其他元素放到一个新的数组中
            for (int i = 0; i < array.length; i++) {
                if (i == index)
                    continue;
                newArray[j] = array[i];
                j++;
            }
            //将新数组赋值给成员变量数组
            array = newArray;
            size--;//变化长度
            return true;
        }
        return false;
    }

    /**
     * 在list中移除该元素
     *
     * @param t 需要移除的元素
     * @return 返回移除成功与否结果
     */
    @Override
    public boolean remove(T t) {
        return remove(indexOf(t));
    }

    /**
     * 根据下标获取元素
     * @param index 下标
     * @return 返回下标对应的元素
     */
    public T get(int index) {
        if (index < size && index >= 0) {
            return array[index];
        } else {
            throw new NoSuchElementException();
        }
    }


    /**
     * 获取元素的索引
     *
     * @param t 需要查询的元素
     * @return 返回索引
     */
    public int indexOf(T t) {
        //应注意数组中可能存在null的情况
        //若查询的元素为null,则不能使用equals方法判断
        //故需要分情况查询
        if (t == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (t.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 重写toString方法
     *
     * @return 返回自定义ArrayList成员变量的值
     */
    @Override
    public String toString() {
        return "MArrayList{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    /**
     * 获取迭代器对象
     *
     * @return 返回内部类迭代器的对象
     */
    @Override
    public Iterator<T> iterator() {
        return new MArrayListIterator();
    }

    private class MArrayListIterator implements Iterator<T> {

        private int currentIndex;//迭代器当前指向的下标

        public MArrayListIterator() {
            currentIndex = 0;//初始下标为0
        }

        /**
         * 检查是否还有下一个元素可迭代
         *
         * @return 返回是否有下一个元素的boolean值
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size();//判断是否为最后一个元素
        }

        /**
         * 返回当前元素，并将迭代器移动到下一个位置
         *
         * @return 返回当前结点元素
         */
        @Override
        public T next() {
            if (hasNext()) {
                T element = get(currentIndex);
                currentIndex++;
                return element;
            }
            throw new NoSuchElementException();
        }

    }
}



