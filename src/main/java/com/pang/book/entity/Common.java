package com.pang.book.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Common {
    private Integer commonId;

    private Date commonTime;

    private Integer commonTo;

    private Integer messageId;

    private Integer commonFrom;

    private String commonContent;

    private String sender;

    private String receiver;
}