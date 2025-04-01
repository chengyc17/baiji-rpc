package com.baiji.common.util;

import java.util.Arrays;


public class ArrayUtils {

    /**
     * 判断数组是否为空
     *
     * @param array 要检查的数组
     * @return 如果数组为空或为null，则返回true，否则返回false
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否不为空
     *
     * @param array 要检查的数组
     * @return 如果数组不为空且不为null，则返回true，否则返回false
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    /**
     * 反转数组
     *
     * @param array 要反转的数组
     */
    public static <T> void reverse(T[] array) {
        if (isEmpty(array)) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * 将数组转换为字符串
     *
     * @param array 要转换的数组
     * @return 数组的字符串表示
     */
    public static <T> String toString(T[] array) {
        if (isEmpty(array)) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 合并两个数组
     *
     * @param first  第一个数组
     * @param second 第二个数组
     * @return 合并后的新数组
     */
    public static <T> T[] concat(T[] first, T[] second) {
        if (isEmpty(first)) {
            return second;
        }
        if (isEmpty(second)) {
            return first;
        }
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * 查找数组中指定元素的索引
     *
     * @param array 要搜索的数组
     * @param key   要查找的元素
     * @return 元素的索引，如果未找到则返回-1
     */
    public static <T> int indexOf(T[] array, T key) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 检查数组是否包含指定元素
     *
     * @param array 要搜索的数组
     * @param key   要查找的元素
     * @return 如果数组包含该元素，则返回true，否则返回false
     */
    public static <T> boolean contains(T[] array, T key) {
        return indexOf(array, key) != -1;
    }

    /**
     * 数组去重
     *
     * @param array 要去重的数组
     * @return 去重后的新数组
     */
    public static <T> T[] distinct(T[] array) {
        if (isEmpty(array)) {
            return array;
        }
        return Arrays.stream(array).distinct().toArray(size -> Arrays.copyOf(array, size));
    }

    /**
     * 数组排序
     *
     * @param array 要排序的数组
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        if (isEmpty(array)) {
            return;
        }
        Arrays.sort(array);
    }

    /**
     * 数组排序（自定义比较器）
     *
     * @param array      要排序的数组
     * @param comparator 自定义比较器
     */
    public static <T> void sort(T[] array, java.util.Comparator<? super T> comparator) {
        if (isEmpty(array)) {
            return;
        }
        Arrays.sort(array, comparator);
    }
}
