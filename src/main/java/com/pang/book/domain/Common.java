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

    @Column(name="common_from")
    private long fromId;

    @Column(name="message_id")
    private long messageId;

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

    public long getFromId() {
        return fromId;
    }

    public Common setFromId(long fromId) {
        this.fromId = fromId;
        return this;
    }

    public long getMessageId() {
        return messageId;
    }

    public Common setMessageId(long messageId) {
        this.messageId = messageId;
        return this;
    }
}
