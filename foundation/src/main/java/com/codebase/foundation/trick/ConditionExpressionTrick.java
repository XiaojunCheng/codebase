package com.codebase.foundation.trick;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/11
 */
public class ConditionExpressionTrick {

    public static void main(String[] args) {
        Object o1 = true ? new Integer(1) : new Double(2.0);

        Object o2;
        if (true) {
            o2 = new Integer(1);
        } else {
            o2 = new Double(2.0);
        }


        System.out.println(o1);
        System.out.println(o2);

        Integer i = new Integer(1);
        if (i.equals(1)) {
            i = null;
        }
        Double d = new Double(2.0);
        Object o = true ? i : d; // NullPointerException!
        System.out.println(o);
    }

}
