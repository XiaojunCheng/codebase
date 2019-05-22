package com.codebase.foundation.spi.service.impl;

import java.util.SortedMap;
import java.util.TreeMap;

import com.codebase.foundation.spi.service.Dictionary;

/**
 * 英英词典
 */
public class En2EnDictionary implements Dictionary {

    private static SortedMap<String, String> dict = new TreeMap<>();

    static {
        dict.put("book", "a set of written or printed pages, usually bound with " + "a protective cover");
        dict.put("editor", "a person who edits");
    }

    @Override
    public String getDefinition(String word) {
        return dict.get(word);
    }

}
