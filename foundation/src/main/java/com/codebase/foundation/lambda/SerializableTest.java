package com.codebase.foundation.lambda;

import org.junit.Test;

import java.io.*;
import java.util.function.Predicate;

/**
 * @author Xiaojun.Cheng
 * @date 2017/5/11
 * <p>
 * 序列化测试
 *
 *
 * 函数式编程特点:
 * 1. 函数式第一等公民
 * 2. 只用"表达式"，不用"语句"
 * 3. 没有"副作用"
 * 4. 不修改状态
 * 5. 引用透明
 *
 * 意义:
 * 1. 代码简洁，开发快速
 * 2. 接近自然语言，易于理解
 * 3. 更方便的代码管理
 * 4. 易于"并发编程"
 * 5. 代码的热升级
 *
 * <a href="http://www.ruanyifeng.com/blog/2012/04/functional_programming.html">阮一峰 函数式编程</a>
 * <a href="https://wizardforcel.gitbooks.io/java8-tutorials">Java 8教材汇总</a>
 */
public class SerializableTest {

    public String VALUE = "Bob";

    public interface SerializablePredicate<T> extends Predicate<T>, Serializable {
    }

    public <T> void filter(SerializablePredicate<T> sp, T value) throws IOException, ClassNotFoundException {
        sp.getClass().isLocalClass();
        File tempFile = File.createTempFile("labmda", "set");

        try (ObjectOutput oo = new ObjectOutputStream(new FileOutputStream(tempFile))) {
            oo.writeObject(sp);
        }

        try (ObjectInput oi = new ObjectInputStream(new FileInputStream(tempFile))) {
            SerializablePredicate<T> p = (SerializablePredicate<T>) oi.readObject();
            System.out.println(p.test(value));
        }
    }


    @Test(expected = NotSerializableException.class)
    public void testAnonymousDirect() throws IOException, ClassNotFoundException {

        final String value = VALUE;
        filter(new SerializablePredicate<String>() {

            @Override
            public boolean test(String t) {
                return value.length() > t.length();
            }
        }, "Bob");

    }

    @Test(expected = NotSerializableException.class)
    public void testLocalClass() throws IOException, ClassNotFoundException {

        class LocalPredicate implements SerializablePredicate<String> {
            @Override
            public boolean test(String t) {
                //TODO Implement this method
                return false;
            }
        }
        filter(new LocalPredicate(), "Bobby");
    }

    @Test(expected = NotSerializableException.class)
    public void testLambdaDirect() throws IOException, ClassNotFoundException {
        filter((String s) -> VALUE.length() > s.length(), "Bobby");
    }

    @Test
    public void testLambdaInDirect() throws IOException, ClassNotFoundException {
        String value = VALUE;
        filter((String s) -> value.length() > s.length(), "Bobby");
    }

    @Test
    public void testLambdaParameter() throws IOException, ClassNotFoundException {
        invokeWithParameter(VALUE);
    }

    private void invokeWithParameter(String value) throws java.lang.ClassNotFoundException, java.io.IOException {
        filter((String s) -> value.length() > s.length(), "Bobby");
    }


    static class Sample implements Serializable {
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }

    public interface SampleSerializableInterface extends Runnable, Serializable {
    }

    public static void serializeLambda() throws Exception {
        Sample s = new Sample();
        s.setStr("smallnest");
        SampleSerializableInterface r = () -> System.out.println("hello " + s.getStr());
        FileOutputStream fos = new FileOutputStream("Runnable.lambda");
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(r);
        s.setStr("colobu");
    }

    public static void deserializeLambda() throws Exception {
        FileInputStream fis = new FileInputStream("Runnable.lambda");
        ObjectInputStream is = new ObjectInputStream(fis);
        SampleSerializableInterface r = (SampleSerializableInterface) is.readObject();
        r.run();
    }

    @Test
    public void testLambda() throws Exception {
        serializeLambda();
        deserializeLambda();
    }
}
