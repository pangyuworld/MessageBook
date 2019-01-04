package com.pang.book.dao;

import com.pang.book.entity.Message;

import java.util.List;

public interface MessageMapper {
    List<Message> selectAll();

    int deleteByPrimaryKey(Integer messageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);
}