package com.pang.book.dao;

import com.pang.book.entity.Common;

import java.util.List;

public interface CommonMapper {

    List<Common> selectAllCommon();
    List<Common> selectCommonByMessageId(Integer messageId);

    int deleteByPrimaryKey(Integer commonId);

    int insert(Common record);

    int insertSelective(Common record);

    Common selectByPrimaryKey(Integer commonId);

    int updateByPrimaryKeySelective(Common record);

    int updateByPrimaryKeyWithBLOBs(Common record);

    int updateByPrimaryKey(Common record);
}