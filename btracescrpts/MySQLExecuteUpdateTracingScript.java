import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class MySQLExecuteUpdateTracingScript {
	/* put your code here */
    @TLS
    static long beginTime;

    @OnMethod(clazz="com.mysql.jdbc.StatementImpl",method="executeUpdate")
    public static void begin(){
        println("==============================");
        println("start");
        beginTime = timeMillis();
    }

    @OnMethod(clazz="com.mysql.jdbc.StatementImpl",method="executeUpdate",location=@Location(Kind.RETURN))
    public static void after(String sql, int returnGeneratedKeys, @Return int result){
        jstack();//打印方法栈

        println("done");
        println(strcat("SQL: ", sql));
        println(strcat("returnGeneratedKeys: ", str(returnGeneratedKeys)));
        println(strcat("result: ", str(result)));
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - beginTime)), "ms"));
        println("==============================");
    }
}