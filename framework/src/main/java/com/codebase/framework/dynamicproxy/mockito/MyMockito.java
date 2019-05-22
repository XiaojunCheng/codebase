package com.codebase.framework.dynamicproxy.mockito;

import lombok.Data;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.mockito.Mockito;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/17
 */
public class MyMockito {

    public static final Map<MethodInfo, Object> MOCKED_METHODS = new HashMap<>();

    public static MockInjector when(Object methodCall) {
        return new MockInjector((MethodInfo) methodCall);
    }

    public static <T> T mock(Class<T> classToMock) {
        MyCglibInterceptor interceptor = new MyCglibInterceptor();
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(classToMock);
        enhancer.setCallback(interceptor);
        return (T) enhancer.create();
    }

    public static void main(String[] args) {
        /**
         * 模拟实现
         */
        {
            final List<String> myMockList = MyMockito.mock(List.class);
            MyMockito.when(myMockList.get(0)).thenReturn("Hello, I am James");
            MyMockito.when(myMockList.get(2)).thenReturn("Hello, I am Billy");
            System.out.println("myMockList1.get(0) = " + myMockList.get(0));
            System.out.println("myMockList1.get(2) = " + myMockList.get(2));

            final Map<Integer, String> myMockMap = MyMockito.mock(Map.class);
            MyMockito.when(myMockMap.get(10)).thenReturn("Hello, I am Bob");
            System.out.println("myMockMap.get(10) = " + myMockMap.get(10));
        }

        /**
         * Mockito实现
         */
        System.out.println("============= 分隔符");
        {
            List<String> mockList = Mockito.mock(List.class);
            Mockito.when(mockList.get(0)).thenReturn("hello, index 0");
            System.out.println("mockList.get(0) = " + mockList.get(0));
        }
    }

}

@Data
class MockInjector {

    private final MethodInfo methodInfo;

    public void thenReturn(final Object mockResult) {
        MyMockito.MOCKED_METHODS.put(methodInfo, mockResult);
    }

}

class MyCglibInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        final MethodInfo key = new MethodInfo(this, method, args);
        final boolean hasMocked = MyMockito.MOCKED_METHODS.containsKey(key);
        if (!hasMocked) {
            // When called for the first time (by MyMockito.when(...)),
            // return a MethodInfo object used as key,
            // so that the later MethodInfo.thenReturn(...) will use this key
            // to insert mock result into the MyMockito.MOCKED_METHODS.
            System.out.println("Initializing the mock for " + key.toString());
            return key;
        } else {
            // Now that MyMockito.MOCKED_METHODS already contains the mock result
            // for this method call, just return the mock result.
            System.out.println("Returns the mock result:");
            return MyMockito.MOCKED_METHODS.get(key);
        }
    }


}
