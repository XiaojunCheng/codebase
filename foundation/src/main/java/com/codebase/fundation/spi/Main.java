package com.codebase.fundation.spi;

import com.codebase.fundation.spi.service.DictionaryService;

public class Main {

    public static void main(String[] args) {
        DictionaryService dictionary = DictionaryService.getInstance();
        System.out.println(Main.lookup(dictionary, "book"));
        System.out.println(Main.lookup(dictionary, "editor"));
    }

    public static String lookup(DictionaryService dictionary, String word) {
        String outputString = word + ": ";
        String definition = dictionary.getDefinition(word);
        if (definition == null) {
            return outputString + "Cannot find definition for this word.";
        } else {
            return outputString + definition;
        }
    }
}
