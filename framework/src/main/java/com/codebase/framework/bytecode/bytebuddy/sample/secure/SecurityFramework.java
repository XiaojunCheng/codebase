package com.codebase.framework.bytecode.bytebuddy.sample.secure;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

public class SecurityFramework implements Framework {

    @Override
    public <T> T secure(Class<T> type) throws Exception {
        DynamicType.Unloaded<T> unloaded =
                new ByteBuddy().subclass(type)
                        .method(ElementMatchers.isAnnotatedWith(Secured.class))
                        .intercept(MethodDelegation.to(SecurityInterceptor.class)
                                .andThen(SuperMethodCall.INSTANCE)
                        )
                        .make();
//        File file = new File("generate");
//        System.out.println(file.getAbsolutePath());
//        Map<TypeDescription, File> map = unloaded.saveIn(file);
//        System.out.println(map.size());
        return unloaded.load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded().newInstance();
    }
}
