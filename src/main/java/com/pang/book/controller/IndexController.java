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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    MessageJPA messageJPA;
    @Autowired
    UserJPA userJPA;
    @Autowired
    CommonJPA commonJPA;

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
        // return模板文件的名称，对应src/main/resources/templates/index.html
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
    @GetMapping("/addmessage")
    public String addMessage(Message message, HttpSession session) {
        User writer = (User) session.getAttribute("user");
        message.setUser(writer);
        System.out.println(message.getTime());
        messageJPA.save(message);
        return "redirect:/";
    }

    @GetMapping("/addcommon")
    public String addCommon(Common common, HttpSession session,HttpServletRequest request){
        User from= (User) session.getAttribute("user");
//        System.out.println(request.getParameter("messageid"));
//        System.out.println(common);
        long messageid=Long.parseLong(request.getParameter("messageid"));
        Message message=messageJPA.getOne(messageid);
        common.setMessage(message);
        common.setUser(from);
        commonJPA.save(common);
        return "redirect:/";
    }
}
