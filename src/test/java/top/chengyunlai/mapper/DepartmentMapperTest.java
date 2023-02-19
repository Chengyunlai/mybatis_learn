package top.chengyunlai.mapper;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import top.chengyunlai.bean.Department;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DepartmentMapperTest {
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

    }

    @Test
    public void findAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        List<Department> all = mapper.findAll();
        for (Department department : all) {
            System.out.println(department);
        }
    }

    @Test
    public void findById() {
    }
}