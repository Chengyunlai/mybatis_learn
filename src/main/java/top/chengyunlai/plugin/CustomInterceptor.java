package top.chengyunlai.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Arrays;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
// Intercepts:用于声明要拦截哪个组件的哪个方法（或者哪些组件的哪些方法）：
// Executor:它是执行 statement 的核心组件，它负责整体的【执行】把控
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
// 就意味着，我们这个 CustomInterceptor 要在 Executor 的 update 方法执行之前拦截。

// @Intercepts(@Signature(type = Executor.class, method = "query",
//                        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class CustomInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("CustomInterceptor intercept run ......");
        // 顺便，把这个Invocation中的东西也打印出来吧

        // org.apache.ibatis.executor.CachingExecutor@3745e5c6
        System.out.println(invocation.getTarget());

        // update
        System.out.println(invocation.getMethod().getName());

        // [org.apache.ibatis.mapping.MappedStatement@5e4c8041, User(id=09ec5fcea620c168936deee53a9cdcff, name=程云来, age=180, birthday=null, version=null, department=null)]
        System.out.println(Arrays.toString(invocation.getArgs()));

        return invocation.proceed();
    }


}