package com.pang.book.services;

import com.pang.book.dao.UserMapper;
import com.pang.book.dao.config.CommonDao;
import com.pang.book.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: UserServiceImpl
 * @Package com.pang.book.services
 * @Description: ${添加描述}
 * @date 2019/1/2 18:13
 */
@Service
public class UserService extends CommonDao {

    /**
     * 持久层
     */
    @Autowired
    private UserMapper mapper;

    /**
     * 根据id获得User
     * @param userId
     * @return user
     */
    public User findById(int userId) {
        return mapper.selectByPrimaryKey(userId);
    }

    /**
     * 根据用户名获得user
     * @param username
     * @return user
     */
    public User findByUsername(String username) {
        return mapper.selectByUsername(username);
    }

    /**
     * 插入用户
     * @param record
     * @return 插入用户的id
     */
    public int insert(User record) {
        return mapper.insertSelective(record);
    }

    /**
     * 获得全部user列表
     * @return user列表
     */
    public List<User> findAllUser() {
        return mapper.selectAllUser();
    }

    /**
     * 根据id删除user
     * @param id
     * @return
     */
    public int deleteById(int id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新user
     * @param user
     * @return
     */
    public int updateUser(User user) {
        return mapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 分页获得所有user
     * @param cuurIndex 页码
     * @param pageSize  每页显示数量
     * @return user列表
     */
    public List<User> findAllUser(int cuurIndex,int pageSize){
        Map<String,Object> page=new HashMap<>();
        page.put("currIndex",(cuurIndex-1)*pageSize);
        page.put("pageSize",pageSize);
        return mapper.selectAllUserPage(page);
    }
}
