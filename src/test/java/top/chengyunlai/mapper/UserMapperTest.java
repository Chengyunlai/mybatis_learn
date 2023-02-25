package top.chengyunlai.mapper;

import net.sf.cglib.beans.BeanMap;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import top.chengyunlai.bean.Department;
import top.chengyunlai.bean.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class UserMapperTest {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        InputStream xml = null;
        try {
            // MyBatis使用了Resources类来加载配置文件。Resources是一个工具类，它提供了一组静态方法来方便地读取类路径下的资源文件。
            // 其中getResourceAsStream方法可以读取类路径下的资源文件，并将其作为一个InputStream返回。
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
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> allLazy = mapper.findAllLazy();
        for (User user : allLazy) {
            System.out.println(user);
        }
        // sqlSession.clearCache(); // 手动清空缓存，直接清空一级缓存
        // List<User> allLazy2 = mapper.findAllLazy();
        // for (User user : allLazy2) {
        //     System.out.println(user);
        // }
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
        // userMapper.cleanCache(); // 注释该语句，则department3 == department4 为true
        Department department4 = departmentMapper2.findById("18ec781fbefd727923b0d35740b177ab");
        // 缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改,所以想下述的操作并不会发送SQL
        System.out.println("department3 == department4 : " + (department3 == department4)); // false

        sqlSession2.close();

        Department department = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
        // 所有 namespace 的一级缓存和当前 namespace 的二级缓存均会清除;flushCache="true"
        // userMapper.cleanCache(); // 并不影响其他的sqlSession
        // userMapper2.cleanCache();
        Department department2 = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
        System.out.println("department == department2 : " + (department == department2)); // true，没有开启二级缓存
        System.out.println("department == department4 : " + (department == department4)); // false

        // 进行select操作后，调用SqlSession.close()方法，会将其一级缓存的数据放进二级缓存中
        // 此时一级缓存随着SqlSession的关闭也就不存在了
        sqlSession.close();





    }

    @Test
    public void findAll2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all2 = mapper.findAll2();
        for (User user : all2) {
            System.out.println(user);
        }
    }

    @Test
    public void findAll3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all2 = mapper.findAll3();
        for (User user : all2) {
            System.out.println(user);
        }
    }

    /**
     * 阿程的deleted是1
     * User(id=09ec5fcea620c168936deee53a9cdcfb, name=阿熊, age=18, birthday=2003-08-08 10:00:00.0, version=0, department=Department(id=18ec781fbefd727923b0d35740b177ab, name=开发部, tel=456, users=null))
     * User(id=09ec5fcea620c168936deee53a9cdcff, name=阿程, age=50, birthday=2023年02月13日, version=0, department=null)
     * User(id=5d0eebc4f370f3bd959a4f7bc2456d89, name=老狗, age=30, birthday=1991-02-20 15:27:20.0, version=0, department=Department(id=ee0e342201004c1721e69a99ac0dc0df, name=设计部, tel=123, users=null))
     */
    @Test
    public void findAllUseDiscriminator() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all2 = mapper.findAllUseDiscriminator();
        for (User user : all2) {
            System.out.println(user);
        }

    }

    @Test
    public void testFindAllUseTypeHandler() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId("09ec5fcea620c168936deee53a9cdcfb");
        List<User> allUser = mapper.findAllUser(user);
        for (User user1 : allUser) {
            System.out.println(user1);
        }
    }

    @Test
    public void findAllUseTrim() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId("09ec5fcea620c168936deee53a9cdcfb");
        List<User> allUser = mapper.findAllUser(user);
        for (User user1 : allUser) {
            System.out.println(user1);
        }
    }

    @Test
    public void findByCondition() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        // user.setId("09ec5fcea620c168936deee53a9cdcfb");
        user.setName("阿");
        List<User> allUser = mapper.findAllUser(user);
        for (User user1 : allUser) {
            System.out.println(user1);
        }
    }

    @Test
    public void findAllUseForeach() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<String> list = new ArrayList<>();
        list.add("09ec5fcea620c168936deee53a9cdcfb");
        list.add("09ec5fcea620c168936deee53a9cdcff");
        List<User> allUseForeach = mapper.findAllUseForeach(list);
        for (User useForeach : allUseForeach) {
            System.out.println(useForeach);
        }
    }

    @Test
    public void findAllUserUseBind() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setName("阿");
        List<User> allUserUseBind = mapper.findAllUserUseBind(user);
        for (User user1 : allUserUseBind) {
            System.out.println(user1);
        }
    }

    @Test
    public void updateUserByID() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId("09ec5fcea620c168936deee53a9cdcff");
        user.setName("程云来");
        user.setAge(180);
        mapper.updateUserByID(user);
    }

    /**
     * @Description:实体类转Map
     *
     * 这里操作一手更新操作，将传入的实体类转为Map，然后用foreach进行更新
     *
     * 这样的好处是有设置元素的地方可以被更新到，而没有设置元素的地方不会被更新到
     *
     * @Param: []
     * @return: void
     * @Author: chengyunlai
     * @Date: 2023/2/22
     */
    @Test
    public void cglibTest(){
        User user = new User();
        user.setName("老四");
        BeanMap beanMap = BeanMap.create(user);
        System.out.println(beanMap);
    }

    @Test
    public void updateUserByMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setName("老四");
        BeanMap beanMap = BeanMap.create(user);
        System.out.println(beanMap);
        // {birthday=null, name=老四, id=null, department=null, version=null, age=null}

        Map<String, Object> departmentMap = new HashMap<>(2);
        departmentMap.put("id", "09ec5fcea620c168936deee53a9cdcff");
        departmentMap.put("beanMap", beanMap);
        mapper.updateUserByMap(departmentMap);
        sqlSession.commit();
    }

    @Test
    public void findAllBySqlColumns() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> allBySqlColumns = mapper.findAllBySqlColumns();
        for (User allBySqlColumn : allBySqlColumns) {
            System.out.println(allBySqlColumn);
        }
    }

    /**
     * 一级缓存固然好用，但小心一个比较危险的东西：一级缓存是存放到 SqlSession 中，
     * 如果我们在查询到数据后，直接在数据对象上作修改，修改之后又重新查询相同的数据，虽然此时一级缓存可以生效，
     * 但因为存放的数据其实是对象的引用，导致第二次从一级缓存中查询到的数据，就是我们刚刚改过的数据，这样可能会发生一些错误。
     */
    @Test
    public void findByIdUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId("09ec5fcea620c168936deee53a9cdcfb");
        User byIdUser = mapper.findByIdUser(user);
        byIdUser.setName("战三");
        System.out.println(byIdUser);
        System.out.println(user); // User(id=09ec5fcea620c168936deee53a9cdcfb, name=战三, age=18, birthday=2003年08月08日, version=0, department=null)
        User byIdUser1 = mapper.findByIdUser(user);
        System.out.println(byIdUser1); // User(id=09ec5fcea620c168936deee53a9cdcfb, name=战三, age=18, birthday=2003年08月08日, version=0, department=null)
        System.out.println(byIdUser1 == byIdUser);
    }
    @Test
    public void transactionManager(){
        // 开启事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // openSession(false)表示开启事务
        // openSession(true)表示关闭事务

        // 底层默认是开启事务的 : return openSessionFromDataSource(configuration.getDefaultExecutorType(), null, false);

        SqlSession sqlSession2 = sqlSessionFactory.openSession(false);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);

        User user = new User();
        user.setId("09ec5fcea620c168936deee53a9cdcfb");
        User user1 = userMapper2.findByIdUser(user);
        // 刚查出来的数据中，name为"测试产品部"
        user1.setName("事务控制开启");
        userMapper2.update(user1);

        List<User> userList = userMapper.findAll();
        userList.forEach(System.out::println);

        sqlSession.close();
        sqlSession2.close();
    }
}