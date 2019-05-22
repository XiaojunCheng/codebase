package com.codebase.framework.bytecode.bytebuddy.sample.typerebasing;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class TypeRebasingTest {

    @Test
    public static void main(String[] args) throws IOException {
        ByteBuddyAgent.install();
        Foo foo = new Foo();
        System.out.println(foo.m());
        Bar bar = new Bar();
        System.out.println(bar.m());
        DynamicType.Unloaded<Bar> unloaded = new ByteBuddy()
                .redefine(Bar.class)
                .name(Foo.class.getName())
                .make();
        File file = new File("generate");
        System.out.println(file.getAbsolutePath());
        Map<TypeDescription, File> map = unloaded.saveIn(file);
        System.out.println(map.size());
        unloaded.load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        System.out.println(foo.m());
        System.out.println(bar.m());

        System.out.println("======");
        Foo foo2 = new Foo();
        System.out.println(foo2.m());
    }

}
