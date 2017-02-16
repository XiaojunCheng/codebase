package com.codebase.foundation.spi;

import com.codebase.foundation.spi.service.DictionaryService;

public class Main {

    public static void main(String[] args) {
        DictionaryService dictService = DictionaryService.getInstance();
        System.out.println(lookup(dictService, "book"));
        System.out.println(lookup(dictService, "editor"));
    }

    public static String lookup(DictionaryService dictService, String word) {
        String outputString = word + ": ";
        String definition = dictService.getDefinition(word);
        if (definition == null) {
            return outputString + "Cannot find definition for this word.";
        } else {
            return outputString + definition;
        }
    }
}
