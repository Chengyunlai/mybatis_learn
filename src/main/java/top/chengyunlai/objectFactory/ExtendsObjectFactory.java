package top.chengyunlai.objectFactory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import top.chengyunlai.bean.User;

/**
 * @ClassName
 * @Description
 * 封装对象
 *
 * 每次创建的 User 对象，如果数据库中的 age 属性为空，则初始化值为 0 。
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class ExtendsObjectFactory extends DefaultObjectFactory {
    @Override
    public <T> T create(Class<T> type) {
        T t = super.create(type);
        // 判断是否为User类型，如果是，则预初始化值
        if (User.class.equals(type)) {
            User user = (User) t;
            user.setAge(0);
        }
        return t;
    }
}
