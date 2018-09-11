package com.codebase.framework.asm.core.method;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/11
 */
public class C {

//    public static long timer;

    public void m() throws Exception {
//        try {
//        timer = System.currentTimeMillis();
        Thread.sleep(100);
//        timer = System.currentTimeMillis() - timer;
//        } catch (Exception e) {
//            throw e;
//        }
    }

    public String returnStr() {
        System.out.println("returnStr");
        return "returnStr";
    }
}
