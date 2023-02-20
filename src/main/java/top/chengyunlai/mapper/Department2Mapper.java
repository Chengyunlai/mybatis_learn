package top.chengyunlai.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.chengyunlai.bean.Department2;


@Mapper
public interface Department2Mapper{
    // #{name}, #{tel}
    void save(Department2 department2);
    void save2(@Param("name") String name,@Param("tel") String tel);
    void save3(Department2 department2);
}
