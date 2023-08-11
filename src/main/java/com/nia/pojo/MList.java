package com.nia.pojo;

/**
 * 自定义MList接口
 */
public interface MList<T> {
    /**
     * 获取List的长度
     * @return 返回list的长度
     */
    int size();

    /**
     * 判断是否为空
     * @return 返回是否为空
     */
    boolean isEmpty();

    /**
     * 判断该元素是否存在与list中
     * @param t 需要判断的元素
     * @return 返回是否存在该元素
     */
    boolean contains(T t);

    /**
     * 添加元素
     * @param t 需要添加的元素
     * @return 返回是否添加成功
     */
    boolean add(T t);

    /**
     * 移除元素
     * @param t 需要移除的元素
     * @return 返回是否移除成功
     */
    boolean remove(T t);




}
