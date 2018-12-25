package com.pang.book.domain;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @program: MessageBook
 * @description: 用户表实体
 * @author: Pang
 * @create: 2018-12-21 23:36
 **/
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id",nullable = false, length = 20)
    private long id;

    @Column(name = "username",nullable = false, length = 20)
    private String userName;

    @Column(name = "password",nullable = false, length = 20)
    private String passWord;

    @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
    private Set<Message> messages;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Common> commons;

    public Set<Common> getCommons() {
        return commons;
    }

    public User setCommons(Set<Common> commons) {
        this.commons = commons;
        return this;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public User setMessages(Set<Message> messages) {
        this.messages = messages;
        return this;
    }



    public long getId() {
        return id;
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
