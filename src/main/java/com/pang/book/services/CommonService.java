package com.pang.book.services;

import com.pang.book.dao.CommonMapper;
import com.pang.book.dao.config.CommonDao;
import com.pang.book.entity.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pang
 * @version V1.0
 * @ClassName: CommonService
 * @Package com.pang.book.services.impl
 * @description:
 * @date 2019/1/2 20:53
 */
@Service
public class CommonService extends CommonDao {
    @Autowired
    private CommonMapper mapper;

    public int insert(Common record){
        return mapper.insert(record);
    }

    public int deleteById(int id){
        return mapper.deleteByPrimaryKey(id);
    }

    public Common findById(int id){
        return mapper.selectByPrimaryKey(id);
    }

    public int update(Common common){
        return mapper.updateByPrimaryKeySelective(common);
    }
}
