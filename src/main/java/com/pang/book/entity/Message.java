package com.pang.book.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Message {
    private Integer messageId;

    private Date messageTime;

    private User user;

    private String messageContent;

    private List<Common> commons;
}