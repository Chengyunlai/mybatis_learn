package top.chengyunlai.plugin;

import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.beans.Statement;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;

/**
 * @ClassName
 * @Description性能分析插件
 *
 * 我们在执行 SQL 时难免会碰到慢 SQL ，这种情况下如果有一个比较好的机制，能帮
 * 我们把执行较慢的 SQL 都筛出来，那自然是极好的。所以我们来试着做一下这个性能分析的插件。
 *
 * @Signature 注解中指定 Statement.class 作为被拦截方法的参数类型时，表示拦截执行 SQL 语句的方法，比如 Statement.execute()、Statement.executeQuery() 和 Statement.executeUpdate() 等。
 *
 * 为什么选择拦截 StatementHandler 的 update 和 query 方法，检查 SQL 的性能好不好，最好是不要带入 MyBatis 框架本身的执行逻辑耗时，而且 StatementHandler 的 update 和 query 方法，
 * 在底层都有一个 Statement 对象的 execute 方法执行，而这个 execute 就是执行 SQL 的动作，所以拦截 StatementHandler 之后监控的执行时间更具有参考意义。
 *
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
// @Intercepts({
//         @Signature(type = Executor.class, method = "select", args = {MappedStatement.class, ResultHandler.class}),
//         @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,Object.class})
// })
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
})
public class PerformanceInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("PerformanceInterceptor intercept run ......");
        long startTime = System.currentTimeMillis();
        Object retVal = invocation.proceed(); // 执行
        long endTime = System.currentTimeMillis();
        // 此处我们先写死1000ms吧
        if (endTime - startTime > 10) {
            CachingExecutor statement = (CachingExecutor) invocation.getTarget();
            String statementToString = statement.toString();
            System.out.println("发现慢SQL：" + statementToString);
            System.out.println("执行时间：" + (endTime - startTime) + "ms");
        }
        return retVal;
    }
}
