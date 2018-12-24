package com.pang.book.domain;

import javax.persistence.*;

/**
 * @program: MessageBook
 * @description: 评论实体
 * @author: Pang
 * @create: 2018-12-21 23:42
 **/
@Entity
@Table(name="message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "message_id")
    private long id;

    @Column(name= "message_time")
    private String time;

    @Column(name= "message_content")
    private String content;

    @Column(name = "user_id")
    private long user_id;

    public long getId() {
        return id;
    }

    public Message setId(long id) {
        this.id = id;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Message setTime(String time) {
        this.time = time;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public long getUser_id() {
        return user_id;
    }

    public Message setUser_id(long user_id) {
        this.user_id = user_id;
        return this;
    }
}
