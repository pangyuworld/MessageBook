package com.pang.book.controller.rest;

import com.pang.book.entity.Message;
import com.pang.book.entity.RestJson;
import com.pang.book.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestMessage {
    /**
     * 持久化层
     */
    @Autowired
    private MessageService messageDao;

    /**
     * 根据id查找message
     * @param id
     * @return message
     */
    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET, produces = "application/json")
    public RestJson<Message> getMessage(@PathVariable int id) {
        RestJson<Message> restJson = new RestJson<>();
        Message message = messageDao.findById(id);
        if (message == null) {
            restJson.setMsg("not found message")
                    .setSuccess(false)
                    .setStatus(404);
        } else {
            restJson.setSuccess(true)
                    .setStatus(200)
                    .setMsg("find one message")
                    .setData(message);
        }
        return restJson;
    }

    /**
     * 分页查询所有message
     * @param page 页码
     * @param perpage 每页数量
     * @return
     */
    @RequestMapping(value = "message/all", method = RequestMethod.GET, produces = "application/json")
    public RestJson<List<Message>> getAllMessage(Object page, Object perpage) {
        List<Message> messageList;
        if (page == null) {
            messageList = messageDao.findAllMessages((int)page, (int)perpage);
        } else {
            messageList = messageDao.findAllMessages();
        }
        RestJson<List<Message>> restJson = new RestJson<>();
        if (messageList == null) {
            restJson.setMsg("not found message")
                    .setStatus(404)
                    .setSuccess(false);
        } else {
            restJson.setSuccess(true)
                    .setStatus(200)
                    .setMsg("find all messages")
                    .setData(messageList);
        }
        return restJson;
    }

    @RequestMapping(value = "/message/add",method = RequestMethod.POST,produces = "application/json")
    public RestJson<Message> addMessage(Message message){
        RestJson<Message> restJson=new RestJson<>();
        if (message.enabSave()){
            int messageid=messageDao.insert(message);
            restJson.setData(messageDao.findById(messageid))
                    .setMsg("insert one message")
                    .setSuccess(true)
                    .setStatus(200);
        }else{
            restJson.setStatus(404)
                    .setSuccess(false)
                    .setMsg("can not insert message");
        }
        return restJson;
    }
}
