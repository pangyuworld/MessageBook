package com.pang.book.dao;

import com.pang.book.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> selectAllUserPage(Map<String,Object> data);

    List<User> selectAllUser();

    User selectByUsername(String username);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}