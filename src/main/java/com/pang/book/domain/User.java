package com.pang.book.domain;


import javax.persistence.*;

/**
 * @program: MessageBook
 * @description: 用户表实体
 * @author: Pang
 * @create: 2018-12-21 23:36
 **/
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id",nullable = false, length = 20)
    private long id;

    @Column(name = "username",nullable = false, length = 20)
    private String userName;

    @Column(name = "password",nullable = false, length = 20)
    private String passWord;

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassWord() {
        return passWord;
    }

    public User setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }


}
