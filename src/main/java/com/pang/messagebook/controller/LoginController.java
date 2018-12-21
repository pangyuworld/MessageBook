package com.pang.messagebook.controller;

import com.pang.messagebook.domain.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
    /**
     * 登录界面
     *
     * @return 登录界面
     */
    @RequestMapping("/login")
    public ModelAndView index() {
        return new ModelAndView("/login/login.html");
    }

    @RequestMapping("/login/login")
    public JsonResult<ArrayList<String>> login(String username, String password) {
        if (username.length()>0 && password.length()>0 ) {
            ArrayList<String> list = new ArrayList<>();
            list.add(username);
            list.add(password);
            return new JsonResult<ArrayList<String>>()
                    .setA(list)
                    .setCode("200")
                    .setMsg("登录成功")
                    .setSuccess(true);
        } else {
            return new JsonResult<ArrayList<String>>()
                    .setCode("404")
                    .setMsg("登录失败")
                    .setSuccess(false);
        }
    }
}
