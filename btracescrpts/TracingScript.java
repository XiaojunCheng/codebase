import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TracingScript {
	/* put your code here */
    @TLS
    static long beginTime;

    @OnMethod(clazz="com.codebase.framework.btrace.Main",method="print")
    public static void begin(){
        println("start");
        beginTime = timeMillis();
    }

    @OnMethod(clazz="com.codebase.framework.btrace.Main",method="print",location=@Location(Kind.RETURN))
    public static void after(){
        jstack();//打印方法栈
        println("done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - beginTime)), "ms"));
    }
}