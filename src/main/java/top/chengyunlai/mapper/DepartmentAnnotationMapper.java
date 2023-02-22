package top.chengyunlai.mapper;

import org.apache.ibatis.annotations.*;
import top.chengyunlai.bean.Department;
import top.chengyunlai.bean.User;

import java.util.List;

/**
 * 注解开发
 */
@Mapper
public interface DepartmentAnnotationMapper {
    @Select("select * from tbl_department")
    List<Department> findAll();

    @Select("select * from tbl_department where id = #{id}")
    Department findDepartmentById(@Param("id") String idd);

    /**
     * @Description: 复杂SQL不建议使用注解的方式编写，很丑，很复杂
     * @Param: [example]
     * @return: java.util.List<top.chengyunlai.bean.Department>
     * @Author: chengyunlai
     * @Date: 2023/2/22
     */
    @Select("<script>select * from tbl_department "
            + "<where>"
            + "<if test='id != null'>and id = #{id} </if>"
            + "<if test='name != null'>and name like concat('%', #{name}, '%') </if>"
            + "<if test='tel != null'>and tel = #{id} </if>"
            + "</where>"
            + "</script>")
    List<Department> findAllByExample(Department example);

    @Select("select * from tbl_department")
    @Results(id = "departmentUseResultsId", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "tel"),
            @Result(property = "tel", column = "name")
    })
    List<Department> findAllByResults();

    @Select("select * from tbl_department")
    @ResultMap("departmentUseResultsId")
    List<Department> findAll2();

    @Insert("insert into tbl_department (id, name, tel) values (#{id}, #{name}, #{tel})")
    int save(Department department);

    /**
     * @Description: 针对主键自增的数据表，插入后可以将主键回填到实体类对象
     * @Param: [department]
     * @return: int
     * @Author: chengyunlai
     * @Date: 2023/2/22
     */
    @Insert("insert into tbl_dept2 (name, tel) values (#{name}, #{tel})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveUseGeneratedKeys(Department user);

    @Update("update tbl_department set name = #{name} where id = #{id}")
    int updateById(Department department);

    @Delete("delete from tbl_department where id = #{id}")
    @Options(timeout = 120,useCache = false)
    int deleteById(String id);
}
