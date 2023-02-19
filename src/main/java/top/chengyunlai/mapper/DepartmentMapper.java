package top.chengyunlai.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.chengyunlai.bean.Department;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@Mapper
public interface DepartmentMapper {
    void save(Department department);

    List<Department> findAll();

    Department findById(String id);
}
