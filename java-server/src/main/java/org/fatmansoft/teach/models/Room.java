package org.fatmansoft.teach.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
@Table(	name = "room",
        uniqueConstraints = {
        })
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;
    @NotBlank
    private String hostname;
    @NotBlank
    private String username1;
    private String username2;
    private String content;


    public Room() {
    }

    public Room(Integer roomId, String hostname, String username1, String username2, String content) {
        this.roomId = roomId;
        this.hostname = hostname;
        this.username1 = username1;
        this.username2 = username2;
        this.content = content;
    }

    /**
     * 获取
     * @return roomId
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * 设置
     * @param roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * 获取
     * @return hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * 设置
     * @param hostname
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * 获取
     * @return username1
     */
    public String getUsername1() {
        return username1;
    }

    /**
     * 设置
     * @param username1
     */
    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    /**
     * 获取
     * @return username2
     */
    public String getUsername2() {
        return username2;
    }

    /**
     * 设置
     * @param username2
     */
    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Room{roomId = " + roomId + ", hostname = " + hostname + ", username1 = " + username1 + ", username2 = " + username2 + ", content = " + content + "}";
    }
}
