package com.codebase.fundation.leetcode.unclassified;


import java.util.concurrent.atomic.AtomicBoolean;

public class _0050_Pow_x_n {

    public static double myPow(double x, int n) {
        if (x == 0) return 0.0;
        if (Math.abs(x) == 1.0) return (n % 2 == 0) ? 1.0 : x;
        if (n == 0) return 1.1;
        if (n == 1) return x;
        if (x == Double.MIN_VALUE && n < 0) return 0.0;
        if (x == Double.MAX_VALUE && n > 0) return 0.0;

        boolean negativeFlag = (x < 0 && (n % 2 != 0)) ? true : false;


        AtomicBoolean maxFlag = new AtomicBoolean(false);
        AtomicBoolean minFlag = new AtomicBoolean(false);
        if (n > 0 && x > 1.0) {//1
            double result = pow(x, n, maxFlag, minFlag);
            if (maxFlag.get()) {
                return 0.0;
            }
            return result;
        } else if (n > 0 && x > 0 && x < 1.0) {//2
            double result = pow(x, n, maxFlag, minFlag);
            if (minFlag.get()) {
                return 0.0;
            }
            return result;
        } else if (n < 0 && x > 1.0) {//3
            double result = pow(x, -n, maxFlag, minFlag);
            if (maxFlag.get()) {
                return 0.0;
            }
            return 1 / result;
        } else if (n < 0 && x > 0 && Math.abs(x) < 1.0) {//4
            double result = pow(x, -n, maxFlag, minFlag);
            if (result == 0.0) {
                return 0.0;
            }
            if (1 / Double.MAX_VALUE > result) {
                return 0.0;
            }
            return 1 / result;
        } else {
            return 0;
        }

//        else if (n > 0 && x > 0 && Math.abs(x) > 1.0) return pow(x, n);
//        else if (n > 0 && x > 0 && Math.abs(x) > 1.0) return pow(x, n);
//        else if (n > 0 && x > 0 && Math.abs(x) > 1.0) return pow(x, n);
//        else (n > 0 && x > 0 && Math.abs(x) > 1.0) return pow(x, n);
    }

    public static double pow(double x, int n, AtomicBoolean maxFlag, AtomicBoolean minFlag) {

        int shiftLength = 1;
        while ((n >> shiftLength) > 0) {
            shiftLength++;
        }

        double[] powValues = new double[shiftLength];
        powValues[0] = Math.abs(x);
        for (int i = 1; i < shiftLength; i++) {
            if (Double.MAX_VALUE / powValues[i - 1] < powValues[i - 1]) {
                maxFlag.set(true);
                return 0.0;
            }
            if (powValues[i - 1] * powValues[i - 1] == 0.0) {
                minFlag.set(true);
                return 0.0;
            }
            powValues[i] = powValues[i - 1] * powValues[i - 1];
        }

        double value = 1;
        int shiftValue = 2;
        for (int i = 1; i < shiftLength; i++) {
            if ((shiftValue & n) == shiftValue) {
                if (Double.MAX_VALUE / powValues[i - 1] < powValues[i - 1]) {
                    maxFlag.set(true);
                    return 0.0;
                }
                if (powValues[i - 1] * powValues[i - 1] == 0.0) {
                    minFlag.set(true);
                    return 0.0;
                }
                value *= powValues[i];
            }
            shiftValue = shiftValue << 1;
        }
        value *= powValues[0];

        return value;
    }

    public static void main(String[] args) {

//        System.out.println("n > 0 && x > 1 ===========================");
//        System.out.println("3^5000 = " + myPow(3, 5000));//0
//        System.out.println("3^5 = " + myPow(3, 5));//243
//        System.out.println();
//
//        System.out.println("n > 0 && 0 < x < 1 ===========================");
//        System.out.println("0.3^5000 = " + myPow(0.3, 5000));//0
//        System.out.println("0.3^5 = " + myPow(0.3, 5));//0.00243
//        System.out.println();

//        System.out.println("n < 0 && x > 1 ===========================");
//        System.out.println("3^-5000 = " + myPow(3, -5000));//0
//        System.out.println("3^-5 = " + myPow(3, -5));//0.00411522633744856
//        System.out.println();

//        System.out.println("n < 0 && 0 < x < 1 ===========================");
//        System.out.println("0.3^-5000 = " + myPow(0.3, -5000));//0
//        System.out.println("0.3^-5 = " + myPow(0.3, -5));//32
//        System.out.println();

//        System.out.println(myPow(2, -5));//0.03125
//
//        System.out.println(myPow(2, 0));//1
        System.out.println(myPow(2, -2147483648));//0


        System.out.println("0. limit ===========================");
        System.out.println("max: " + Double.MAX_VALUE);
        System.out.println("1/max: " + 1 / Double.MAX_VALUE);
        System.out.println("min: " + Double.MIN_VALUE);
        System.out.println("1/min: " + 1 / Double.MIN_VALUE);
        System.out.println();

//        System.out.println("1. x=0 ===========================");
//        System.out.println(myPow(0, -2));
//        System.out.println(myPow(0, 2));
//        System.out.println();
//
//        System.out.println("2. x=1 ===========================");
//        System.out.println(myPow(1, -32352));//1
//        System.out.println(myPow(1, -32351));//1
//        System.out.println(myPow(1, 32352));//1
//        System.out.println(myPow(1, 32351));//1
//        System.out.println();
//
//        System.out.println("3. x=-1 ===========================");
//        System.out.println(myPow(-1, -32352));//1
//        System.out.println(myPow(-1, -32351));//-1
//        System.out.println(myPow(-1, 32352));//1
//        System.out.println(myPow(-1, 32351));//-1
//        System.out.println();
    }
}
