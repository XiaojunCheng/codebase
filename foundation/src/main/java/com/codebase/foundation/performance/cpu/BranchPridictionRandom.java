package com.codebase.foundation.performance.cpu;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

import java.util.Random;

public class BranchPridictionRandom implements TestCase {

    public String getName() {
        return name;
    }

    final String name = "branch-prediction-random-10x" + Constants.name(Constants.INT_ARRAY_3.length);

    final int[] b = Constants.INT_ARRAY_3;
    final int range = Constants.INT_ARRAY_3.length;

    public Object action() throws Exception {
        int k = range >> 1;
        int t = 0;
        for (int j = 0; j < 10; ++j) {
            for (int i = 0; i < range; ++i) {
                if (b[i] < k) {
                    t += 1;
                }
            }
        }
        return t;
    }

    public void init() throws Exception {
        Random r = new Random();
        for (int i = 0; i < range; ++i) {
            b[i] = i;
        }
        //shuffle
        for (int i = 0; i < range; ++i) {
            int x = r.nextInt(range - i) + i;
            int t = b[i];
            b[i] = b[x];
            b[x] = t;
        }
    }



}
