package top.chengyunlai.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import top.chengyunlai.bean.Department2;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class Department2MapperTest {
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
    public void save() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Department2Mapper mapper = sqlSession.getMapper(Department2Mapper.class);
        Department2 department2 = new Department2();
        department2.setName("自增测试");
        department2.setTel("123");
        mapper.save(department2);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(department2); // Department2(id=7, name=自增测试, tel=123) 会回显
    }

    @Test
    public void save2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Department2Mapper mapper = sqlSession.getMapper(Department2Mapper.class);
        mapper.save2("没有自增测试","456");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void save3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Department2Mapper mapper = sqlSession.getMapper(Department2Mapper.class);
        Department2 department2 = new Department2();
        department2.setName("没有自增测试");
        department2.setTel("789");
        mapper.save3(department2);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(department2); // Department2(id=null, name=没有自增测试, tel=789) 不会回显
    }
}