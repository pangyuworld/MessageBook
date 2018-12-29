package com.pang.book.controller;

import com.pang.book.domain.User;
import com.pang.book.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String indexLogin() {
        return "login/login";
    }

    /**
     * 访问注册主页
     * @author pang
     * @date 2018/12/24
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/register")
    public String indexRegister(){
        return "login/register";
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
    public String doLogin(User user, Model model, HttpSession session){
        User realUser = userJPA.findByUserName(user.getUserName());//通过username找用户
        if (realUser==null){
            model.addAttribute("error","不存在该用户，请检查用户名是否输入正确");
            return "login/login";
        }
        if (!realUser.getPassWord().equals(user.getPassWord())){
            model.addAttribute("error","用户名或密码输入错误");
            return "login/login";
        }
        session.setAttribute("user",userJPA.findByUserName(user.getUserName()));
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
    public String doRegister(User user, Model model, HttpServletRequest request){
        User realUser=userJPA.findByUserName(user.getUserName());
        String password2=request.getParameter("password2");
        if(user.getUserName()==null||user.getUserName().length()<1){
            model.addAttribute("error","请输入用户名");
            return "login/register";
        }
        if (!password2.equals(user.getPassWord())){
            model.addAttribute("error","两次输入密码不一致");
            return "login/register";
        }
        if (realUser!=null){
            model.addAttribute("error","该用户已存在");
            return "login/register";
        }
        userJPA.saveAndFlush(user);
        return "redirect:/login";
    }

    /**
     *  注销
     * @author pang
     * @date 2018/12/25
     * @param session
     * @return java.lang.String
     */
    @GetMapping("/cancel")
    public String cancel(HttpSession session){
        session.setAttribute("user",null);
        return "redirect:/";
    }
}
