package com.pang.book.controller.rest;

import com.pang.book.entity.Message;
import com.pang.book.entity.RestJson;
import com.pang.book.services.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api(description = "留言", tags = "Message")
public class RestMessage {
    /**
     * 持久化层
     */
    @Autowired
    private MessageService messageDao;

    /**
     * 根据id查找message
     *
     * @param id
     * @return message
     */
    @ApiOperation(value = "根据ID查找留言", notes = "根据ID查找留言")
    @ApiImplicitParam(name = "id", value = "留言id", dataType = "int", paramType = "path", required = true)
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
     *
     * @param request 请求
     * @return
     */
    @ApiOperation(value = "查找所有留言内容", notes = "查找所有留言内容（可分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "perpage", value = "每页数", required = false, dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "message", method = RequestMethod.GET, produces = "application/json")
    public RestJson<List<Message>> getAllMessage(HttpServletRequest request) {
        List<Message> messageList;
        if (request.getParameter("page") != null) {
            messageList = messageDao.findAllMessages(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request
                    .getParameter("perpage")));
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

    /**
     * 添加留言
     *
     * @param message 留言内容
     * @param session        认证
     * @return com.pang.book.entity.RestJson<com.pang.book.entity.Message>
     * @author pang
     * @date 2019/1/3
     */
    @ApiOperation("添加留言")
    @RequestMapping(value = "/message", method = RequestMethod.POST, produces = "application/json")
    public RestJson<Message> addMessage(Message message, HttpSession session) {
        RestJson<Message> restJson = new RestJson<>();
        Message messageTemp = new Message();
        messageTemp.setMessageContent(message.getMessageContent());
        /*message.setUserId(((User)session.getAttribute("user")).getUserId());*/
        /*开发阶段添加默认user_id*/
        messageTemp.setUserId(22);
        if (messageDao.insert(messageTemp) > 0) {
            restJson.setMsg("insert one message")
                    .setSuccess(true)
                    .setStatus(200)
                    .setData(messageDao.findById(messageTemp.getMessageId()));
        } else {
            restJson.setStatus(404)
                    .setSuccess(false)
                    .setMsg("can not insert message");
        }
        return restJson;
    }

    /**
     * 更新留言
     *
     * @param id      要更新的留言id
     * @param content 更新内容
     * @return com.pang.book.entity.RestJson<com.pang.book.entity.Message>
     * @author pang
     * @date 2019/1/4
     */
    @ApiOperation("更新留言")
    @ApiImplicitParam(name = "id", value = "要更新的留言id", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/message/{id}", method = RequestMethod.PUT, produces = "application/json")
    public RestJson<Message> editMessage(@PathVariable int id, String content) {
        RestJson<Message> restJson = new RestJson<>();
        Message message = messageDao.findById(id);
        if (message == null) {
            restJson.setMsg("fount no message")
                    .setStatus(404)
                    .setSuccess(false);
        } else {
            message.setMessageContent(content);
            messageDao.update(message);
            restJson.setMsg("update one message")
                    .setStatus(200)
                    .setSuccess(true)
                    .setData(message);
        }
        return restJson;
    }

    /**
     * 删除留言
     *
     * @param id 留言id
     * @return com.pang.book.entity.RestJson<com.pang.book.entity.Message>
     * @author pang
     * @date 2019/1/4
     */
    @ApiOperation("删除留言")
    @ApiImplicitParam(name = "id", value = "留言id", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/message/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public RestJson<Message> deleteMessage(@PathVariable int id) {
        RestJson<Message> restJson = new RestJson<>();
        Message message = messageDao.findById(id);
        if (messageDao.deleteById(id) < 1) {
            restJson.setSuccess(false)
                    .setMsg("found no message")
                    .setStatus(404);
        } else {
            restJson.setStatus(200)
                    .setMsg("delete one message")
                    .setSuccess(true);
        }
        return restJson;
    }
}
