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

import static org.junit.Assert.*;

public class UserAnnotationMapperTest {
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
    public void findAllByExample() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
        mapper.findAllByExample(new User());
    }

    @Test
    public void findAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
        for (User user : mapper.findAll()) {
            System.out.println(user);
        }
    }

    @Test
    public void save() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
        User user = new User();
        user.setName("Provider插入注解测试");
        user.setAge(3);
        Department department = new Department();
        department.setId("18ec781fbefd727923b0d35740b177ab");
        user.setDepartment(department);
        mapper.save(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateByExample() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
        User user = new User();
        user.setId("94ef753c1a744cc7b179f27aff329fb1");
        user.setName("Provider更新");
        mapper.updateByExample(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserAnnotationMapper mapper = sqlSession.getMapper(UserAnnotationMapper.class);
        mapper.deleteById("fa3fdb1bfd84407c9df5eeedbec65952");
        sqlSession.commit();
        sqlSession.close();
    }
}