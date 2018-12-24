package com.pang.book.controller;

import com.pang.book.domain.User;
import com.pang.book.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private UserJPA userJPA;

    /**
     * 访问登陆主页
     * @author pang
     * @date 2018/12/24
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/login")
    public ModelAndView indexLogin() {
        return new ModelAndView("/login/login.html");
    }

    /**
     * 访问注册主页
     * @author pang
     * @date 2018/12/24
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/register")
    public ModelAndView indexRegister(){
        return new ModelAndView("/login/register");
    }

    /**
     *  真正的登陆
     * @author pang
     * @date 2018/12/24
     * @param user
     * @param model
     * @return java.lang.String
     */
    @PostMapping(value = "/login/dologin")
    public String doLogin(User user, Model model){
        User realUser = userJPA.findByUserName(user.getUserName());//通过username找用户
        if (realUser==null){
            model.addAttribute("error","不存在该用户，请检查用户名是否输入正确");
            return "login/login";
        }
        if (!realUser.getPassWord().equals(user.getPassWord())){
            model.addAttribute("error","用户名或密码输入错误");
            return "login/login";
        }
        return "redirect:/";
    }

    /**
     * 实际注册
     * @author pang
     * @date 2018/12/24
     * @param user
     * @param model
     * @return java.lang.String
     */
    @PostMapping("/register/doregister")
    public String doRegister(User user,String password2, Model model){
        User realUser=userJPA.findByUserName(user.getUserName());
        if (!password2.equals(user.getPassWord())){
            model.addAttribute("error","两次输入密码不一致");
            return "/register";
        }
        if (realUser!=null){
            model.addAttribute("error","该用户已存在");
            return "/register";
        }
        userJPA.saveAndFlush(user);
        return "redirect:/";
    }
}
