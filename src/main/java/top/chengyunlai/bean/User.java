package top.chengyunlai.bean;

import lombok.Data;
import lombok.ToString;
import top.chengyunlai.bean.Department;

import java.util.Date;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@Data
@ToString
public class User {
    private String id;

    private String name;

    private Integer age;

    private Date birthday;

    private Integer version;
    // 多对一
    private Department department;
}
