package com.codebase.foundation.trick;

import java.sql.SQLException;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/11
 */
public class ExceptionTrick {

    //方法没有声明throws
    public static void main(String[] args) {
        doThrow(new SQLException());
    }

    static void doThrow(Exception e) {
        ExceptionTrick.doThrow0(e);
    }

    static <E extends Exception> void doThrow0(Exception e) throws E {
        throw (E) e;
    }

}
