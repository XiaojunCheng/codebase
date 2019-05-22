package com.codebase.framework.dynamicproxy.mockito;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/17
 */
@Data
public class MethodInfo {

    private final MyCglibInterceptor interceptor;
    private final Method method;
    private final Object[] args;

    @Override
    public String toString() {
        return "{interceptor: " + interceptor + ", Method: " + method + ", args: " + Arrays.toString(args) + "}";
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof MethodInfo) {
            final MethodInfo otherMethodInfo = (MethodInfo) other;
            return interceptor.equals(otherMethodInfo.interceptor) && method.equals(otherMethodInfo.method) && Arrays.equals(args, otherMethodInfo.args);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return interceptor.hashCode() + method.hashCode() + Arrays.hashCode(args);
    }

}
