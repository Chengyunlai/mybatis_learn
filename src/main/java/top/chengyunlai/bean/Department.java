package top.chengyunlai.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Set;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department implements Serializable {
    public Department(@Param("idd")String id) {
        this.id = id;
    }
    private String id;

    private String name;

    private String tel;

    // 一对多
    private Set<User> users;
}
