package com.pang.book.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @program: MessageBook
 * @description: 用户表实体
 * @author: Pang
 * @create: 2018-12-21 23:36
 **/
@Entity
@Data
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

}
