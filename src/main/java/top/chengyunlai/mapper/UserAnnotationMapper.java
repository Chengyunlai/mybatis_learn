package top.chengyunlai.mapper;

import org.apache.ibatis.annotations.*;
import top.chengyunlai.bean.Department;
import top.chengyunlai.bean.User;
import top.chengyunlai.mapper.provider.UserMapperProvider;

import java.util.List;

/**
 * @Description: 学习Provider注解的使用
 * @Param: * @Param null:
 * @return: * @return: null
 * @Author: chengyunlai
 * @Date: 2023/2/22
 */
@Mapper
public interface UserAnnotationMapper {
    @SelectProvider(type = UserMapperProvider.class, method = "findAll")
    List<User> findAll();

    @SelectProvider(type = UserMapperProvider.class, method = "findAllByExample")
    List<User> findAllByExample(User user);

    @InsertProvider(type = UserMapperProvider.class, method = "save")
    void save(User user);

    @UpdateProvider(type = UserMapperProvider.class, method = "updateByExample")
    int updateByExample(User user);

    @DeleteProvider(type = UserMapperProvider.class, method = "deleteById")
    int deleteById(String id);
}
