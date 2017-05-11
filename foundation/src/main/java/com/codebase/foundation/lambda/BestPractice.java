package com.codebase.foundation.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Xiaojun.Cheng
 * @date 2017/5/11
 */
public class BestPractice {

    public static void useAnonymousClass() {
        //Before Java 8:
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8 ");
            }
        }).start();

        //Java 8 way:
        new Thread(() -> System.out.println("In Java8!")).start();
    }

    public static void usePredicate() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;

        languages.stream().filter(startsWithJ.and(fourLetterLong)).forEach(System.out::println);
    }

    public static void useStream() {
        List<Integer> prices = Arrays.asList(19, 20, 21, 22);

        final int totalOfDiscountedPrices = prices.stream()
                .filter(price -> (price > 20))
                .map(price -> price * 2)
                .reduce(0, (a, b) -> a + b);

        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
    }

    public static void main(String[] args) {
        useAnonymousClass();
        usePredicate();
        useStream();
    }

}
