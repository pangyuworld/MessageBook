package com.pang.book.controller;

import com.pang.book.domain.Common;
import com.pang.book.domain.Message;
import com.pang.book.domain.User;
import com.pang.book.jpa.CommonJPA;
import com.pang.book.jpa.MessageJPA;
import com.pang.book.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private MessageJPA messageJPA;
    @Autowired
    private UserJPA userJPA;
    @Autowired
    private CommonJPA commonJPA;

    /**
     * 主页
     *
     * @param map
     * @param session
     * @return java.lang.String
     * @author pang
     * @date 2018/12/24
     */
    @RequestMapping("/")
    public String index(ModelMap map, HttpSession session) {
        // 加入一个属性，用来在模板中读取
        User user = (User) session.getAttribute("user");
        List<Message> messageList = messageJPA.findAll(new Sort(Sort.Direction.DESC,"id"));
        if (user != null) {
            map.addAttribute("user", user.getUserName());
        } else {
            map.addAttribute("user", "");
        }
        if (messageList != null) {
            map.addAttribute("messagelist", messageList);
        }
        map.addAttribute("author","全部");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    /**
     * 返回个人评论
     * @param userId
     * @param map
     * @return
     */
    @RequestMapping("/{userid}")
    public String userIndex(@PathVariable(name = "userid")Long userId,ModelMap map){
        List<Message> messages=messageJPA.findByUserId(userId);
        if (messages!=null){
            map.addAttribute("messagelist", messages);
        }
        map.addAttribute("author",userJPA.getOne(userId).getUserName());
        return "index";
    }


    /**
     * 添加评论
     *
     * @param message
     * @param session
     * @return java.lang.String
     * @author pang
     * @date 2018/12/24
     */
    @GetMapping("/message/add")
    public String addMessage(Message message, HttpSession session) {
        User writer = (User) session.getAttribute("user");
        message.setUser(writer);
        System.out.println(message.getTime());
        messageJPA.save(message);
        return "redirect:/";
    }

    /**
     *  添加回复
     * @author pang
     * @date 2018/12/25
     * @param common
     * @param session
     * @param messageId
     * @return java.lang.String
     */
    @GetMapping("/common/add/{id}")
    public String addCommon(Common common, HttpSession session, @PathVariable(name="id" ) Long messageId){
        User from= (User) session.getAttribute("user");
        Message message=messageJPA.getOne(messageId);
        common.setMessage(message);
        common.setUser(from);
        commonJPA.save(common);
        return "redirect:/";
    }

    /**
     *  删除回复
     * @author pang
     * @date 2018/12/25
     * @param commonId
     * @return java.lang.String
     */
    @GetMapping("/common/delete/{id}")
    public String deleteCommon(@PathVariable(name = "id") Long commonId){
        commonJPA.deleteById(commonId);
        return "redirect:/";
    }

    /**
     *  删除评论
     * @author pang
     * @date 2018/12/25
     * @param msgId
     * @return java.lang.String
     */
    @GetMapping("/message/delete/{msgId}")
    public String deleteMessage(@PathVariable(name = "msgId") Long msgId){
        messageJPA.deleteById(msgId);
        return "redirect:/";
    }

    /**
     * 编辑评论
     * @param msgId
     * @param text
     * @return
     */
    @GetMapping("/message/edit/{msgId}")
    public String editMessage(@PathVariable(name = "msgId") Long msgId,String text){
        Message message=messageJPA.getOne(msgId);
        message.setContent(text);
        messageJPA.save(message);
        return "redirect:/";
    }

    /**
     * 编辑回复
     * @param commonId
     * @param text
     * @return
     */
    @GetMapping("/common/edit/{commonId}")
    public String editCommon(@PathVariable(name = "commonId") Long commonId,String text){
        Common common=commonJPA.getOne(commonId);
        common.setContent(text);
        commonJPA.save(common);
        return "redirect:/";
    }
}
