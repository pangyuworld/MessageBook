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


    @ManyToOne(cascade=CascadeType.ALL  ,fetch = FetchType.EAGER,optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="user_id")//设置在article表中的关联字段(外键)
    private User user;


    public User getUser() {
        return user;
    }

    public Message setUser(User user) {
        this.user = user;
        return this;
    }

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


}
