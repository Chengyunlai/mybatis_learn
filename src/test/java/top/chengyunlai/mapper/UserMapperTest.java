package top.chengyunlai.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import top.chengyunlai.bean.Department;
import top.chengyunlai.bean.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class UserMapperTest {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        InputStream xml = null;
        try {
            xml = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
    }
    @Test
    public void findAll() {
    }

    @Test
    public void findAllLazy() {
    }

    @Test
    public void findAllByDepartmentId() {
    }

    @Test
    public void findAllUseTypeHandler() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> allUseTypeHandler = mapper.findAllUseTypeHandler();
        for (User user : allUseTypeHandler) {
            System.out.println(user);
        }
    }

    @Test
    public void saveUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId("1d1d1d");
        user.setName("Handle测试");
        Department department = new Department();
        department.setId("2d2d2d");
        user.setDepartment(department);
        // mapper.saveUser(user);
        // sqlSession.commit();
    }

    @Test
    public void findAllByDepartmentIdProvider() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findAllByDepartmentIdProvider("ee0e342201004c1721e69a99ac0dc0df");
        for (User user : users) {
            System.out.println(user);
        }

    }
}