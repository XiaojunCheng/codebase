package com.codebase.framework.groovy;

import javax.script.*;
import java.util.Date;

/**
 * @author cheng.xiaojun.seu@gmail.com
 * @date 17/3/17
 */
public class ScriptEngineManagerMain {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {

        ScriptEngineManager factory = new ScriptEngineManager();

        //create instance
        ScriptEngine engine = factory.getEngineByName("groovy");
        System.out.println(engine.toString());

        assert engine != null;

        //javax.script.Bindings
        Bindings binding = engine.createBindings();
        binding.put("date", new Date());

        engine.eval("def getTime(){return date;}", binding);
        engine.eval("def sayHello(name,age){return 'Hello,I am ' + name + ', age' + age;}");

        Date time = (Date) ((Invocable) engine).invokeFunction("getTime", null);
        System.out.println(time);

        String message = (String) ((Invocable) engine).invokeFunction("sayHello", "zhangsan", new Integer(12));
        System.out.println(message);
    }

}
