package com.pang.book.dao;

import com.pang.book.entity.User;

public interface UserMapper {
    User selectByUsername(String username);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}