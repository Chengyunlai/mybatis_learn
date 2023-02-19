package top.chengyunlai.typeHandle;

import javafx.scene.input.DataFormat;
import org.apache.ibatis.type.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@MappedTypes(String.class)
@MappedJdbcTypes(JdbcType.TIMESTAMP)
public class DateTypeHandler extends BaseTypeHandler<String> {

    // 保存操作，数据入库之前时数据处理
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {

    }

    // //下面三个则是，从数据库加载数据后，vo对象封装前的数据处理
    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Timestamp sqTimestamp = resultSet.getTimestamp(s);
        if (null == sqTimestamp){
            return null;
        }else {
            Date date = new Date(sqTimestamp.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            String dateString = dateFormat.format(date);
            System.out.println(dateString);
            return dateString;
        }
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
