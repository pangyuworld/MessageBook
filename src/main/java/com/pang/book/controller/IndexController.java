package com.pang.book.controller;

import com.pang.book.domain.Message;
import com.pang.book.domain.User;
import com.pang.book.jpa.MessageJPA;
import com.pang.book.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    MessageJPA messageJPA;
    @Autowired
    UserJPA userJPA;

    /**
     *  主页
     * @author pang
     * @date 2018/12/24
     * @param map
     * @param session
     * @return java.lang.String
     */
    @RequestMapping("/")
    public String index(ModelMap map, HttpSession session) {
        // 加入一个属性，用来在模板中读取
        User user = (User) session.getAttribute("user");
        List<Message> messageList = null;
        if (user != null) {
            map.addAttribute("user", user.getUserName());
            messageList=messageJPA.findByUserId(user.getId());
        }
        else
            map.addAttribute("user", "");
        if(messageList!=null)
            map.addAttribute("messagelist",messageList);
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    @GetMapping("/addmessage")
    public String addMessage(Message message,HttpSession session){
        User writer= (User) session.getAttribute("user");
        message.setUser(writer);
        messageJPA.save(message);
        return "redirect:/";
    }
}
