package top.chengyunlai.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import top.chengyunlai.bean.Department;
import top.chengyunlai.bean.User;

import javax.annotation.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class DepartmentAnnotationMapperTest {

    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void findAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentAnnotationMapper mapper = sqlSession.getMapper(DepartmentAnnotationMapper.class);
        List<Department> all = mapper.findAll();
        for (Department department : all) {
            System.out.println(department);
        }
    }

    @Test
    public void findUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentAnnotationMapper mapper = sqlSession.getMapper(DepartmentAnnotationMapper.class);
        Department department = mapper.findDepartmentById("ee0e342201004c1721e69a99ac0dc0df");
        System.out.println(department);

    }

    @Test
    public void findAllByExample() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentAnnotationMapper mapper = sqlSession.getMapper(DepartmentAnnotationMapper.class);
        Department department = new Department();
        department.setName("老四");
        mapper.findAllByExample(department);
    }

    // 下面两个方法是注解的结果集映射
    @Test
    public void findAllByResults() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentAnnotationMapper mapper = sqlSession.getMapper(DepartmentAnnotationMapper.class);
        List<Department> allByResults = mapper.findAllByResults();
        for (Department allByResult : allByResults) {
            System.out.println(allByResult);
        }
    }

    @Test
    public void findAll2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentAnnotationMapper mapper = sqlSession.getMapper(DepartmentAnnotationMapper.class);
        List<Department> allByResults = mapper.findAll2();
        for (Department allByResult : allByResults) {
            System.out.println(allByResult);
        }
    }

    @Test
    public void save() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentAnnotationMapper mapper = sqlSession.getMapper(DepartmentAnnotationMapper.class);
        Department department = new Department();
        System.out.println(UUID.randomUUID().toString());
        department.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        department.setName("测试部门~");
        department.setTel("123456789");
        mapper.save(department);
        // 提交事务才会生效
        sqlSession.commit();
        sqlSession.close();
        System.out.println(department);
    }

    @Test
    public void saveUseGeneratedKeys() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentAnnotationMapper mapper = sqlSession.getMapper(DepartmentAnnotationMapper.class);
        Department department = new Department();
        // @Options(useGeneratedKeys = true, keyProperty = "id")
        department.setName("回显测试");
        department.setTel("回显测试");
        mapper.saveUseGeneratedKeys(department);
        System.out.println(department);


    }
}