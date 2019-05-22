package com.codebase.framework.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.util.Date;

/**
 * @author cheng.xiaojun.seu@gmail.com
 * @date 17/3/16
 */
public class GroovyShellMain {

    public static void main(String[] args) {
        evaluate();
        evaluateByMainMethod();
        evaluateByClass();
    }

    private static void evaluateByClass() {
        System.out.println("====================== evaluateByClass ======================");

        String classContent = "class User {"
                + "\tString name;"
                + "\tInteger age;"
                + "\n"
                //.append("UserDO(String name,Integer age){this.name = name;this.age = age};")
                + "\tString sayHello() { "
                + "\t\treturn 'Hello,I am ' + name + ', age ' + age;"
                + "\t}"
                + "}\n"
                + "\n"
                + "def user = new User(name:'computer', age:1);"
                + "user.sayHello();";
        //groovy.lang.Binding
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);
        String message = (String) shell.evaluate(classContent);
        System.out.println(message);
        //重写main方法,默认执行
        String mainMethod = "static void main(String[] args){def user = new User(name:'lisi', age:12);print(user.sayHello());}";
        shell.evaluate(mainMethod);

        System.out.println();
    }

    private static void evaluateByMainMethod() {
        System.out.println("====================== evaluateByMainMethod ======================");
        String[] args = new String[]{"bigBang", "10"};//main(String[] args)
        Binding binding = new Binding(args);
        GroovyShell shell = new GroovyShell(binding);
        shell.evaluate("static void main(String[] args){ if(args.length != 2) return;println('Hello,I am ' + args[0] + ', age ' + args[1])}");
        System.out.println();
    }

    private static void evaluate() {
        System.out.println("====================== evaluate ======================");
        //groovy.lang.Binding
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);

        binding.setVariable("name", "jack");
        shell.evaluate("println 'Hello World! I am ' + name;");

        //在script中,声明变量,不能使用def,否则scrope不一致.
        shell.evaluate("date = new Date();");
        Date date = (Date) binding.getVariable("date");
        System.out.println("Date:" + date);

        //以返回值的方式,获取script内部变量值,或者执行结果
        //一个shell实例中,所有变量值,将会在此"session"中传递下去."date"可以在此后的script中获取
        Long time = (Long) shell.evaluate("def time = date.getTime(); return time;");
        System.out.println("Time:" + time);

        binding.setVariable("list", new String[]{"A", "B", "C"});
        //invoke method
        String joinString = (String) shell.evaluate("def call(){return list.join('-')};call();");
        System.out.println("Array join:" + joinString);

        System.out.println();
    }

}
