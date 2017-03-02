package com.codebase.foundation.performance;

import java.text.DecimalFormat;
import java.util.*;

public class CaseRunner {

    private ArrayList<TestCase> cases = new ArrayList<>();

    public void addCase(TestCase tc) throws Exception {
        // tc.init();
        cases.add(tc);
    }

    private HashMap<String, Long> bestTimeMap = new HashMap<>();
    private TreeMap<String, Long> timeMap = new TreeMap<>();
    private HashMap<String, Long> countMap = new HashMap<>();

    private void min(HashMap<String, Long> map, String key, long value) {
        Long v = map.get(key);
        if (v == null) {
            v = new Long(value);
        } else {
            if (value < v) {
                v = value;
            }
        }
        map.put(key, v);
    }

    private void add(Map<String, Long> map, String key, long value) {
        Long v = map.get(key);
        if (v == null) {
            v = new Long(value);
        } else {
            v += value;
        }
        map.put(key, v);
    }

    public void action() throws Exception {

        Collections.sort(cases, new Comparator<TestCase>() {
            public int compare(TestCase o1, TestCase o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        int count = 0;
        DecimalFormat format = new DecimalFormat("0000");

        for (TestCase tc : cases) {

            System.out.print("[" + (++count) + "/" + cases.size() + "] init[");
            // System.out.print(" init ... ");
            long start = System.currentTimeMillis();

            tc.init();
            System.out.print(format.format(System.currentTimeMillis() - start));
            System.out.print("ms] ");

            start = System.currentTimeMillis();
            Object result = tc.action();
            long finish = System.currentTimeMillis();
            long duration = finish - start;

            String name = tc.getName();

            add(timeMap, name, duration);
            min(bestTimeMap, name, duration);
            add(countMap, name, 1);

            System.out.print(name);
            System.out.print("\t");
            System.out.print(duration);
            System.out.print("\t");
            System.out.print(result);
            System.out.print("\t");

            start = System.currentTimeMillis();
            System.gc();
            System.out.println(" gc[" + (System.currentTimeMillis() - start) + "ms]");
        }

    }

    public void report() {
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();

        for (String name : timeMap.keySet()) {
            long time = timeMap.get(name);
            long min = bestTimeMap.get(name);
            long count = countMap.get(name);
            System.out.println(name + "\t" + count + "\t" + (time / count) + "ms" + "\t" + min + "ms");
        }
    }
}
