package com.codebase.framework.bytebuddy;

import com.codebase.framework.asm.core.write.HelloWorldClassWriter;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author Xiaojun.Cheng
 * @date 2018/9/17
 */
public class HelloWorldGenerator {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        {
            ByteBuddy buddy = new ByteBuddy();
            String className = "HelloCxj";
            byte[] bytes = buddy.subclass(Object.class).name(className)
                    .method(ElementMatchers.named("toString"))
                    .intercept(FixedValue.value("Hello World!"))
                    .make().getBytes();
            HelloWorldClassWriter.writeByteCode2ClassFile(className, bytes);
        }

        {
            Class<?> dynamicType = new ByteBuddy()
                    .subclass(Object.class)
                    .method(ElementMatchers.named("toString"))
                    .intercept(FixedValue.value("Hello World!"))
                    .make()
                    .load(Thread.currentThread().getContextClassLoader())
                    .getLoaded();
            Object clazzInstance = dynamicType.newInstance();
            System.out.println(clazzInstance.getClass().getName());
            System.out.println(clazzInstance.toString());
        }
    }

}
