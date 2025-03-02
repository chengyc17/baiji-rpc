package com.baiji.common.util;

import java.util.*;

public class CollectionUtils {

    /**
     * 判断集合是否为空
     *
     * @param collection 待判断的集合
     * @return 如果集合为 null 或者集合大小为 0，则返回 true，否则返回 false
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是否不为空
     *
     * @param collection 待判断的集合
     * @return 如果集合不为 null 且集合大小不为 0，则返回 true，否则返回 false
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断 Map 是否为空
     *
     * @param map 待判断的 Map
     * @return 如果 Map 为 null 或者 Map 大小为 0，则返回 true，否则返回 false
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断 Map 是否不为空
     *
     * @param map 待判断的 Map
     * @return 如果 Map 不为 null 且 Map 大小不为 0，则返回 true，否则返回 false
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 获取集合的大小，如果集合为 null，则返回 0
     *
     * @param collection 待获取大小的集合
     * @return 集合的大小，如果集合为 null，则返回 0
     */
    public static int size(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * 获取 Map 的大小，如果 Map 为 null，则返回 0
     *
     * @param map 待获取大小的 Map
     * @return Map 的大小，如果 Map 为 null，则返回 0
     */
    public static int size(Map<?, ?> map) {
        return map == null ? 0 : map.size();
    }

    /**
     * 获取两个集合的交集
     *
     * @param collection1 第一个集合
     * @param collection2 第二个集合
     * @param <T>         集合元素的类型
     * @return 两个集合的交集
     */
    public static <T> Set<T> intersection(Collection<T> collection1, Collection<T> collection2) {
        if (isEmpty(collection1) || isEmpty(collection2)) {
            return Collections.emptySet();
        }
        Set<T> set1 = new HashSet<>(collection1);
        set1.retainAll(collection2);
        return set1;
    }

    /**
     * 获取两个集合的并集
     *
     * @param collection1 第一个集合
     * @param collection2 第二个集合
     * @param <T>         集合元素的类型
     * @return 两个集合的并集
     */
    public static <T> Set<T> union(Collection<T> collection1, Collection<T> collection2) {
        Set<T> set = new HashSet<>();
        if (isNotEmpty(collection1)) {
            set.addAll(collection1);
        }
        if (isNotEmpty(collection2)) {
            set.addAll(collection2);
        }
        return set;
    }

    /**
     * 获取两个集合的差集（存在于 collection1 但不存在于 collection2 的元素）
     *
     * @param collection1 第一个集合
     * @param collection2 第二个集合
     * @param <T>         集合元素的类型
     * @return 两个集合的差集
     */
    public static <T> Set<T> subtract(Collection<T> collection1, Collection<T> collection2) {
        if (isEmpty(collection1)) {
            return Collections.emptySet();
        }
        Set<T> set = new HashSet<>(collection1);
        if (isNotEmpty(collection2)) {
            set.removeAll(collection2);
        }
        return set;
    }

    /**
     * 安全地向集合中添加元素
     *
     * @param collection 集合
     * @param element    要添加的元素
     * @param <T>        集合元素的类型
     * @return 如果集合不为 null 且元素添加成功，则返回 true，否则返回 false
     */
    public static <T> boolean addSafely(Collection<T> collection, T element) {
        return collection != null && collection.add(element);
    }

    /**
     * 安全地向集合中添加另一个集合的所有元素
     *
     * @param collection 目标集合
     * @param elements   要添加的元素集合
     * @param <T>        集合元素的类型
     * @return 如果集合不为 null 且元素添加成功，则返回 true，否则返回 false
     */
    public static <T> boolean addAllSafely(Collection<T> collection, Collection<T> elements) {
        return collection != null && elements != null && collection.addAll(elements);
    }

    /**
     * 安全地从集合中移除元素
     *
     * @param collection 集合
     * @param element    要移除的元素
     * @param <T>        集合元素的类型
     * @return 如果集合不为 null 且元素移除成功，则返回 true，否则返回 false
     */
    public static <T> boolean removeSafely(Collection<T> collection, T element) {
        return collection != null && collection.remove(element);
    }

    /**
     * 安全地从集合中移除另一个集合的所有元素
     *
     * @param collection 目标集合
     * @param elements   要移除的元素集合
     * @param <T>        集合元素的类型
     * @return 如果集合不为 null 且元素移除成功，则返回 true，否则返回 false
     */
    public static <T> boolean removeAllSafely(Collection<T> collection, Collection<T> elements) {
        return collection != null && elements != null && collection.removeAll(elements);
    }

    /**
     * 安全地获取集合中指定索引的元素
     *
     * @param list  列表
     * @param index 索引
     * @param <T>   集合元素的类型
     * @return 如果列表不为 null 且索引在有效范围内，则返回指定索引的元素，否则返回 null
     */
    public static <T> T getSafely(List<T> list, int index) {
        return list != null && index >= 0 && index < list.size() ? list.get(index) : null;
    }
}
