package com.pang.book.domain;

import javax.persistence.*;

/**
 * @program: MessageBook
 * @description: 回复实体
 * @author: Pang
 * @create: 2018-12-21 23:46
 **/
@Entity
@Table(name="common")
public class Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="common_id")
    private long id;

    @Column(name= "common_time")
    private String time;

    @Column(name="common_content")
    private String content;

    @Column(name="common_to")
    private long toId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="common_from")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="message_id")
    private Message message;

    public long getId() {
        return id;
    }

    public Common setId(long id) {
        this.id = id;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Common setTime(String time) {
        this.time = time;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Common setContent(String content) {
        this.content = content;
        return this;
    }

    public long getToId() {
        return toId;
    }

    public Common setToId(long toId) {
        this.toId = toId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Common setUser(User user) {
        this.user = user;
        return this;
    }

    public Message getMessage() {
        return message;
    }

    public Common setMessage(Message message) {
        this.message = message;
        return this;
    }
}
