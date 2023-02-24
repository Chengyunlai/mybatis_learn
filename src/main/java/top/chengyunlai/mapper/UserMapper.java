package top.chengyunlai.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.chengyunlai.bean.Department;
import top.chengyunlai.bean.User;

import java.util.List;
import java.util.Map;

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
    List<User> findAllUseDiscriminator();

    List<User> findAllLazy();
    List<User> findAllUser(User user);
    List<User> findAllUseTrim(User user);
    List<User> findByCondition(User user);
    List<User> findAllUseForeach(@Param("ids") List<String> ids);
    List<User> findAllUserUseBind(User user);
    List<User>findAllByDepartmentId(String departmentId);

    List<User> findAllByDepartmentIdProvider(String departmentId);

    List<User> findAllUseTypeHandler();

    List<User> findAllBySqlColumns();

    // void saveUser(User user);
    void updateUserByID(User user);
    void updateUserByMap(Map user);
    // 缓存
    int cleanCache();

    User findByIdUser(User user);

    void update(User user);

}
