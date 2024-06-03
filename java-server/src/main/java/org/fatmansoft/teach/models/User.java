package org.fatmansoft.teach.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
/**
 * User用户表实体类 保存每个允许登录的信息人员的账号信息，
 * Integer userId 用户表 user 主键 user_id
 * UserType userType 关联到用户类型对象
 * Person person 关联到该用户所用的Person对象，账户所对应的人员信息 person_id 关联 person 表主键 person_id
 * String userName 登录账号 和Person 中的num属性相同
 * String password 用户密码 非对称加密，这能加密，无法解码
 *
 */

@Entity
@Table(	name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userName"),
        })
public class User {
    @Id
    private String username;
    @NotBlank
    @Size(max = 60)
    private String password;
    @ManyToOne()
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    public User() {
    }

    public User(String username, String password,UserType usertype) {
        this.username = username;
        this.password = password;
        this.userType=usertype;
    }
    /**
     * 获取
     * @return username
     */
    public String getUserName() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUserName(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return userType
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * 设置
     * @param userType
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
