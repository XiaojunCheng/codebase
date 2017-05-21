import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TracingScript {
	/* put your code here */
    @TLS
    static long beginTime;

    @OnMethod(clazz="com.codebase.framework.btrace.SayHelloImpl",method="say")
    public static void begin(){
        println("start");
        beginTime = timeMillis();
    }

    @OnMethod(clazz="com.codebase.framework.btrace.SayHelloImpl",method="say",location=@Location(Kind.RETURN))
    public static void after(){
        jstack();//打印方法栈
        println("done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - beginTime)), "ms"));
    }

    @TLS
    static long beginTime2;

    @OnMethod(clazz="com.codebase.framework.btrace.SayHelloImpl",method="print")
    public static void begin2(){
        println("start");
        beginTime2 = timeMillis();
    }

    @OnMethod(clazz="com.codebase.framework.btrace.SayHelloImpl",method="print",location=@Location(Kind.RETURN))
    public static void after2(){
        jstack();//打印方法栈
        println("done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - beginTime2)), "ms"));
    }
}