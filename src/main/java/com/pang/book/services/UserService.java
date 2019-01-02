package com.pang.book.services;

import com.pang.book.dao.UserMapper;
import com.pang.book.dao.config.CommonDao;
import com.pang.book.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pang
 * @version V1.0
 * @ClassName: UserServiceImpl
 * @Package com.pang.book.services
 * @Description: ${添加描述}
 * @date 2019/1/2 18:13
 */
@Service
public class UserService extends CommonDao  {

    @Autowired
    private UserMapper mapper;

    public User findById(int userId){
        return mapper.selectByPrimaryKey(userId);
    }

    public User findByUsername(String username){
        return mapper.selectByUsername(username);
    }

    public int insert(User record){
        return mapper.insertSelective(record);
    }

}
