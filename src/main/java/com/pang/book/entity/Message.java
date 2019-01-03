package com.pang.book.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Message {
    private Integer messageId;

    private Date messageTime;

    private int userId;

    private String messageContent;

    private List<Common> commons;

    private String userName;


    public boolean enabSave(){
        if (userId<=0) {
            return false;
        }
        else if (messageContent==null) {
            return false;
        }else {
            return true;
        }
    }
}