package com.pang.book.dao;

import com.pang.book.entity.Common;

public interface CommonMapper {
    int deleteByPrimaryKey(Integer commonId);

    int insert(Common record);

    int insertSelective(Common record);

    Common selectByPrimaryKey(Integer commonId);

    int updateByPrimaryKeySelective(Common record);

    int updateByPrimaryKeyWithBLOBs(Common record);

    int updateByPrimaryKey(Common record);
}