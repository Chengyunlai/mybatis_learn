package top.chengyunlai.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.chengyunlai.bean.Department;
import top.chengyunlai.bean.User;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@Mapper
public interface UserMapper {
    List<User> findAll();
    List<User> findAll2();
    List<User> findAll3();

    List<User> findAllLazy();

    List<User>findAllByDepartmentId(String departmentId);

    List<User> findAllByDepartmentIdProvider(String departmentId);

    List<User> findAllUseTypeHandler();

    // void saveUser(User user);

    // 缓存
    int cleanCache();

}
