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

    @Test
    public void cleanCache() {
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        DepartmentMapper departmentMapper2 = sqlSession2.getMapper(DepartmentMapper.class);
        UserMapper userMapper = sqlSession2.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession.getMapper(UserMapper.class);

        Department department3 = departmentMapper2.findById("18ec781fbefd727923b0d35740b177ab");
        userMapper.cleanCache(); // 注释该语句，则department3 == department4 为true
        Department department4 = departmentMapper2.findById("18ec781fbefd727923b0d35740b177ab");
        // 缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改,所以想下述的操作并不会发送SQL
        System.out.println("department3 == department4 : " + (department3 == department4)); // false


        Department department = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
        // 所有 namespace 的一级缓存和当前 namespace 的二级缓存均会清除;flushCache="true"
        // userMapper.cleanCache(); // 并不影响其他的sqlSession
        // userMapper2.cleanCache();
        Department department2 = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
        System.out.println("department == department2 : " + (department == department2)); // true，没有开启二级缓存

        // 进行select操作后，调用SqlSession.close()方法，会将其一级缓存的数据放进二级缓存中
        // 此时一级缓存随着SqlSession的关闭也就不存在了
        sqlSession.close();
        sqlSession2.close();

        System.out.println("department == department4 : " + (department == department4)); // false


    }
}