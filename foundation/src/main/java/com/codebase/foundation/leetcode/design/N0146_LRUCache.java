package com.codebase.foundation.leetcode.design;


import java.util.*;

/**
 * Runtime: 72 ms, faster than 65.23% of Java online submissions for LRU Cache.
 * Memory Usage: 59.6 MB, less than 40.83% of Java online submissions for LRU Cache.
 *
 * @author Xiaojun.Cheng
 * @date 2019/3/22
 */
public class N0146_LRUCache {

    private final int capacity;
    private final LinkedHashMap<Integer, Integer> cache;

    public N0146_LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity);
    }

    public int get(int key) {
        Integer value = cache.remove(key);
        if (value == null) {
            return -1;
        }
        cache.put(key, value);
        return value.intValue();
    }

    public void put(int key, int value) {
        Integer oldValue = cache.remove(key);
        if (oldValue == null && cache.size() >= capacity) {
            Map.Entry<Integer, Integer> oldEntry = cache.entrySet().stream().findFirst().get();
            cache.remove(oldEntry.getKey());
        }
        cache.put(key, value);
    }

    public static void main(String[] args) {
        N0146_LRUCache cache = new N0146_LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));// returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

}
