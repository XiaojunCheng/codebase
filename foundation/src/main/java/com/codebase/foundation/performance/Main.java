package com.codebase.foundation.performance;

import com.codebase.foundation.performance.array.*;
import com.codebase.foundation.performance.counter.ConcurrentCAS;
import com.codebase.foundation.performance.counter.ConcurrentSync;
import com.codebase.foundation.performance.counter.ConcurrentVolatileCompact;
import com.codebase.foundation.performance.counter.ConcurrentVolatilePadding;
import com.codebase.foundation.performance.cpu.BranchPridictionRandom;
import com.codebase.foundation.performance.cpu.BranchPridictionSequential;
import com.codebase.foundation.performance.map.HashMapGetLong1;
import com.codebase.foundation.performance.map.HashMapGetLong2;
import com.codebase.foundation.performance.map.HashMapGetString1;
import com.codebase.foundation.performance.map.HashMapGetString2;

public class Main {

    public final static int REPEAT = 5;

    public static void main(String[] args) throws Exception {

        CaseRunner runner = new CaseRunner();

        for (int i = 0; i < REPEAT; ++i) {
            //CPU分支预测
            runner.addCase(new BranchPridictionRandom());
            runner.addCase(new BranchPridictionSequential());

            //内存访问，密集vs分散
            runner.addCase(new ArrayIndexCompact());
            runner.addCase(new ArrayIndexRandom());

            //HashMap在不同loadFactor和Key类型下的表现
            runner.addCase(new HashMapGetLong1());
            runner.addCase(new HashMapGetLong2());
            runner.addCase(new HashMapGetString1());
            runner.addCase(new HashMapGetString2());

            //数组操作：初始化、for循环复制、memcpy复制
            runner.addCase(new ArrayInitialize());
            runner.addCase(new ArrayForCopy());
            runner.addCase(new ArraySysCopy());

            //并发性能对比：sync，cas，线程独立counter在false sharing的情况（同步体缩减的例子）
            final long NUM_1KW = 10000000L;
            final long NUM_1E = 100000000L;
            runner.addCase(new ConcurrentSync(4, NUM_1KW));
            runner.addCase(new ConcurrentVolatilePadding(4, NUM_1E));
            runner.addCase(new ConcurrentVolatileCompact(4, NUM_1E));
            runner.addCase(new ConcurrentCAS(4, NUM_1KW));
        }

        runner.action();
        runner.report();
    }
}
