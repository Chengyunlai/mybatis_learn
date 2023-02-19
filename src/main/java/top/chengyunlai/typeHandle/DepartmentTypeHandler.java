package top.chengyunlai.typeHandle;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import top.chengyunlai.bean.Department;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @ClassName
 * @Description 我们自己定义的 TypeHandler ，都需要实现 TypeHandler 接口，并声明其泛型，泛型就是要处理的目标类型。
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class DepartmentTypeHandler implements TypeHandler<Department> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Department department, JdbcType jdbcType) throws SQLException {
        System.out.println(i);
        preparedStatement.setString(i, department.getId());
    }

    @Override
    public Department getResult(ResultSet resultSet, String s) throws SQLException {

        Department department = new Department();
        // 获取表的结构
        ResultSetMetaData metaData = resultSet.getMetaData();
        // 获取数据的字段数
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            // 获取列名
            String columnName = metaData.getColumnName(i);
            System.out.println(columnName);
            Object object = resultSet.getObject(columnName);
            try {
                Field declaredField = Department.class.getDeclaredField(columnName);
                declaredField.setAccessible(true);
                declaredField.set(department,object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return department;

        // // 获取表的结构
        // ResultSetMetaData metaData = resultSet.getMetaData();
        // // 获取数据的字段数
        // int columnCount = metaData.getColumnCount();
        //
        // for (int i = 1; i <= columnCount; i++) {
        //     // 获取列名
        //     String columnName = metaData.getColumnName(i);
        //     System.out.println(columnName);
        //     System.out.println("---");
        //     // // 通过列名获取值
        //     Object object = resultSet.getObject(columnName);
        //     // 通过反射获取该类的属性
        //     Field field = null;
        //     try {
        //         field = Department.class.getDeclaredField(columnName);
        //         // 给属性注入内容，需要先开启Set
        //         field.setAccessible(true);
        //         // 注入内容
        //         field.set(department, object);
        //     } catch (Exception e) {
        //         throw new RuntimeException(e);
        //     }
        // }
    }

    @Override
    public Department getResult(ResultSet resultSet, int i) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getString(i));
        return department;
    }

    @Override
    public Department getResult(CallableStatement callableStatement, int i) throws SQLException {
        Department department = new Department();
        department.setId(callableStatement.getString(i));
        return department;
    }
}
