import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class BtraceScript {
    /* put your code here */
    @TLS
    static long beginTime;

    @OnMethod(clazz = "com.mysql.jdbc.*", method = "*")
    public static void begin() {
        println("==============================");
        println("start");
        beginTime = timeMillis();
    }

    @OnMethod(clazz = "com.mysql.jdbc.*", method = "*", location = @Location(Kind.RETURN))
    public static void after() {
        jstack();//打印方法栈

        println("done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - beginTime)), "ms"));
        println("==============================");
    }

    /* put your code here */
    @TLS
    static long druidBeginTime;

    @OnMethod(clazz = "com.alibaba.druid.*", method = "*")
    public static void druidBegin() {
        println("==============================");
        println("start");
        druidBeginTime = timeMillis();
    }

    @OnMethod(clazz = "com.alibaba.druid.*", method = "*", location = @Location(Kind.RETURN))
    public static void druidAfter() {
        jstack();//打印方法栈

        println("done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - druidBeginTime)), "ms"));
        println("==============================");
    }

    /* put your code here */
    @TLS
    static long saveOrUpdateBeginTime;

    @OnMethod(clazz = "com.youzan.sc.service.impl.ItemSkuServiceImpl", method = "saveOrUpdate")
    public static void saveOrUpdateBegin() {
        println("==============================");
        println("ItemSkuServiceImpl#saveOrUpdate start");
        saveOrUpdateBeginTime = timeMillis();
    }

    @OnMethod(clazz = "com.youzan.sc.service.impl.ItemSkuServiceImpl", method = "saveOrUpdate", location = @Location(Kind.RETURN))
    public static void saveOrUpdateAfter() {
        jstack();//打印方法栈

        println("ItemSkuServiceImpl#saveOrUpdate done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - saveOrUpdateBeginTime)), "ms"));
        println("==============================");
    }

    /* put your code here */
    @TLS
    static long saveMultiSkuAllBeginTime;

    @OnMethod(clazz = "com.youzan.sc.service.inner.ItemSkuInnerService", method = "saveMultiSkuAll")
    public static void saveMultiSkuAllBegin() {
        println("==============================");
        println("ItemSkuInnerService#saveMultiSkuAll start");
        saveMultiSkuAllBeginTime = timeMillis();
    }

    @OnMethod(clazz = "com.youzan.sc.service.inner.ItemSkuInnerService", method = "saveMultiSkuAll", location = @Location(Kind.RETURN))
    public static void saveMultiSkuAllAfter() {
        jstack();//打印方法栈

        println("ItemSkuInnerService#saveMultiSkuAll done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - saveMultiSkuAllBeginTime)), "ms"));
    }

    /* put your code here */
    @TLS
    static long insertMultiSkuBeginTime;

    @OnMethod(clazz = "com.youzan.sc.service.inner.ItemSkuInnerService", method = "insertMultiSku")
    public static void insertMultiSkuBegin() {
        println("==============================");
        println("ItemSkuInnerService#insertMultiSku start");
        insertMultiSkuBeginTime = timeMillis();
    }

    @OnMethod(clazz = "com.youzan.sc.service.inner.ItemSkuInnerService", method = "insertMultiSku", location = @Location(Kind.RETURN))
    public static void insertMultiSkuAfter() {
        jstack();//打印方法栈

        println("ItemSkuInnerService#insertMultiSku done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - insertMultiSkuBeginTime)), "ms"));
        println("==============================");
    }

    @TLS
    static long updateOrDelOrInsertSkuBeginTime;

    @OnMethod(clazz = "com.youzan.sc.service.inner.ItemSkuInnerService", method = "updateOrDelOrInsertSku")
    public static void updateOrDelOrInsertSkuBegin() {
        println("==============================");
        println("ItemSkuInnerService#updateOrDelOrInsertSku start");
        updateOrDelOrInsertSkuBeginTime = timeMillis();
    }

    @OnMethod(clazz = "com.youzan.sc.service.inner.ItemSkuInnerService", method = "updateOrDelOrInsertSku", location = @Location(Kind.RETURN))
    public static void updateOrDelOrInsertSkuAfter() {
        jstack();//打印方法栈

        println("ItemSkuInnerService#updateOrDelOrInsertSku done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - updateOrDelOrInsertSkuBeginTime)), "ms"));
        println("==============================");
    }

    @TLS
    static long createAndUpdateRelatedSkuBeginTime;

    @OnMethod(clazz = "com.youzan.sc.service.inner.ItemSkuInnerService", method = "createAndUpdateRelatedSku")
    public static void createAndUpdateRelatedSkuBeginTimeBegin() {
        println("==============================");
        println("ItemSkuInnerService#createAndUpdateRelatedSku start");
        createAndUpdateRelatedSkuBeginTime = timeMillis();
    }

    @OnMethod(clazz = "com.youzan.sc.service.inner.ItemSkuInnerService", method = "createAndUpdateRelatedSku", location = @Location(Kind.RETURN))
    public static void createAndUpdateRelatedSkuBeginTimeAfter() {
        jstack();//打印方法栈

        println("ItemSkuInnerService#createAndUpdateRelatedSku done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - createAndUpdateRelatedSkuBeginTime)), "ms"));
        println("==============================");
    }


    @TLS
    static long executeBeginTime;

    @OnMethod(clazz = "com.youzan.ic.transaction.logging.LoggingTransactionTemplate", method = "execute")
    public static void executeBegin() {
        println("==============================");
        println("LoggingTransactionTemplate#execute start");
        executeBeginTime = timeMillis();
    }

    @OnMethod(clazz = "com.youzan.ic.transaction.logging.LoggingTransactionTemplate", method = "execute", location = @Location(Kind.RETURN))
    public static void executeAfter() {
        jstack();//打印方法栈

        println("LoggingTransactionTemplate#execute done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - executeBeginTime)), "ms"));
        println("==============================");
    }

    @TLS
    static long diffMutiItemSkuDOBeginTime;

    @OnMethod(clazz = "com.youzan.sc.util.SkuSaveCheckUtil", method = "diffMutiItemSkuDO")
    public static void diffMutiItemSkuDOBegin() {
        println("==============================");
        println("SkuSaveCheckUtil#diffMutiItemSkuDO start");
        diffMutiItemSkuDOBeginTime = timeMillis();
    }

    @OnMethod(clazz = "com.youzan.sc.util.SkuSaveCheckUtil", method = "diffMutiItemSkuDO", location = @Location(Kind.RETURN))
    public static void diffMutiItemSkuDOAfter() {
        jstack();//打印方法栈

        println("SkuSaveCheckUtil#diffMutiItemSkuDO done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - diffMutiItemSkuDOBeginTime)), "ms"));
        println("==============================");
    }

    @TLS
    static long doInTransactionBeginTime;

    @OnMethod(clazz = "com.youzan.ic.transaction.logging.DefaultLoggingTransactionHandler", method = "doInTransaction")
    public static void doInTransactionBegin() {
        println("==============================");
        println("SkuSaveCheckUtil#diffMutiItemSkuDO start");
        doInTransactionBeginTime = timeMillis();
    }

    @OnMethod(clazz = "com.youzan.ic.transaction.logging.DefaultLoggingTransactionHandler", method = "doInTransaction", location = @Location(Kind.RETURN))
    public static void doInTransactionAfter() {
        jstack();//打印方法栈

        println("DefaultLoggingTransactionHandler#doInTransaction done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - doInTransactionBeginTime)), "ms"));
        println("==============================");
    }

    @TLS
    static long commitBeginTime;

    @OnMethod(clazz = "org.springframework.transaction.support.AbstractPlatformTransactionManager", method = "commit")
    public static void commitBegin() {
        println("==============================");
        println("AbstractPlatformTransactionManager#commit start");
        commitBeginTime = timeMillis();
    }

    @OnMethod(clazz = "org.springframework.transaction.support.AbstractPlatformTransactionManager", method = "commit", location = @Location(Kind.RETURN))
    public static void commitAfter() {
        jstack();//打印方法栈

        println("AbstractPlatformTransactionManager#commit done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - commitBeginTime)), "ms"));
        println("==============================");
    }

    @TLS
    static long dCommitBeginTime;

    @OnMethod(clazz = "org.springframework.jdbc.datasource.DataSourceTransactionManager", method = "commit")
    public static void dCommitBegin() {
        println("==============================");
        println("DataSourceTransactionManager#commit start");
        dCommitBeginTime = timeMillis();
    }

    @OnMethod(clazz = "org.springframework.jdbc.datasource.DataSourceTransactionManager", method = "commit", location = @Location(Kind.RETURN))
    public static void dCommitAfter() {
        jstack();//打印方法栈

        println("DataSourceTransactionManager#commit done");
        println(strcat(strcat("Test Execute Time:", str(timeMillis() - dCommitBeginTime)), "ms"));
        println("==============================");
    }
}