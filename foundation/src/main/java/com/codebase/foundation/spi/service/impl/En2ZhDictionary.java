package com.codebase.foundation.spi.service.impl;

import java.util.SortedMap;
import java.util.TreeMap;

import com.codebase.foundation.spi.service.Dictionary;

/**
 * 英中词典
 */
public class En2ZhDictionary implements Dictionary {

    private static final SortedMap<String, String> dict = new TreeMap<>();

    static {
        dict.put("book", "书籍，著作");
        dict.put("editor", "编辑，编者");
    }

    @Override
    public String getDefinition(String word) {
        return dict.get(word);
    }

}
