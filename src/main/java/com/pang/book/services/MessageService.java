package com.pang.book.services;

import com.pang.book.dao.MessageMapper;
import com.pang.book.dao.config.CommonDao;
import com.pang.book.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: MessageServiceImpl
 * @Package com.pang.book.services.impl
 * @description:
 * @date 2019/1/2 20:53
 */
@Service
public class MessageService extends CommonDao {
    @Autowired
    private MessageMapper mapper;
    public List<Message> findAllMessages(){
        return mapper.selectAll();
    }

    public int insert(Message message){
        return mapper.insert(message);
    }

    public int update(Message message){
        return mapper.updateByPrimaryKeySelective(message);
    }

    public Message findById(int id){
        return mapper.selectByPrimaryKey(id);
    }
    public int deleteById(int id){
        return mapper.deleteByPrimaryKey(id);
    }
}
