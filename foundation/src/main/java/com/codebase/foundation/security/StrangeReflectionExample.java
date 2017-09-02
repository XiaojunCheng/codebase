package com.codebase.foundation.security;

import java.lang.reflect.Field;

/**
 * @author Xiaojun.Cheng
 * @date 2017/7/12
 */
public class StrangeReflectionExample {

    public Character aCharacter;

    public static void main(String... args) throws Exception {
        StrangeReflectionExample instance = new StrangeReflectionExample();

        Field field = StrangeReflectionExample.class.getField("aCharacter");

        Field type = Field.class.getDeclaredField("type");
        type.setAccessible(true);
        type.set(field, String.class);

        field.set(instance, 'A');
        System.out.println(instance.aCharacter);
    }
}
