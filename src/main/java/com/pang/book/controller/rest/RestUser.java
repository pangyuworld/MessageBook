package com.pang.book.controller.rest;

import com.pang.book.entity.RestJson;
import com.pang.book.entity.User;
import com.pang.book.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api(description = "用户表", tags = "User")
public class RestUser {
    @Autowired
    private UserService userDao;

    /**
     * 根据id获得User
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获得User", notes = "")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "int", required = true)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public RestJson<User> getUser(@PathVariable int id) {
        User user = userDao.findById(id);
        RestJson<User> restJson = new RestJson<>();
        if (user == null) {
            restJson.setMsg("没有找到该用户")
                    .setSuccess(false)
                    .setStatus(404);
        } else {
            restJson.setSuccess(true)
                    .setStatus(200)
                    .setData(user)
                    .setMsg("查找用户成功");
        }
        return restJson;
    }

    /**
     * 获得全部的User
     *
     * @return
     */
    @ApiOperation(value = "获得全部user", notes = "获得全部user（可分页）")
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "perpage", value = "每页数量", required = false, dataType = "int", paramType = "query")
    })
    public RestJson<List<User>> getUser(HttpServletRequest request) {
        List<User> userList;
        if (request.getParameter("page") != null) {
            userList = userDao.findAllUser(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("perpage")));
        } else {
            userList = userDao.findAllUser();
        }
        RestJson<List<User>> restJson = new RestJson<>();
        if (userList == null) {
            restJson.setSuccess(false)
                    .setMsg("没有找到用户")
                    .setStatus(404);
        } else {
            restJson.setStatus(200)
                    .setSuccess(true)
                    .setMsg("查找所有用户")
                    .setData(userList);
        }
        return restJson;
    }

    /**
     * 添加user
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
    public RestJson<User> addUser(User user) {
        RestJson<User> restJson = new RestJson<>();
        try {
            userDao.insert(user);
            restJson.setMsg("添加用户成功")
                    .setSuccess(true)
                    .setData(userDao.findById(user.getUserId()))
                    .setStatus(201);
        } catch (DataAccessException e) {
            restJson.setMsg("用户已经存在")
                    .setStatus(409)
                    .setSuccess(false);
        }
        return restJson;
    }

    /**
     * 删除user
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public RestJson<User> deleteUser(@PathVariable int id) {
        RestJson<User> restJson = new RestJson<>();
        User user = userDao.findById(id);
        if (user == null) {
            restJson.setSuccess(false)
                    .setMsg("用户不存在")
                    .setStatus(404);
        } else {
            userDao.deleteById(id);
            restJson.setSuccess(true)
                    .setMsg("删除用户成功")
                    .setStatus(200);
        }
        return restJson;
    }

    /**
     * 更新user
     *
     * @param user
     * @param id
     * @return
     */
    @ApiOperation(value = "更新用户", notes = "更新用户")
    @ApiImplicitParam(name = "id", value = "要更新的用户id", dataType = "int", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.PUT, produces = "application/json")
    public RestJson<User> updateUser(User user, @PathVariable int id) {
        User userTemp = userDao.findById(id);
        RestJson<User> restJson = new RestJson<>();
        if (userTemp == null) {
            restJson.setMsg("用户不存在")
                    .setSuccess(false)
                    .setStatus(404);
        } else {
            user.setUserId(id);
            userDao.updateUser(user);
            restJson.setStatus(200)
                    .setMsg("更新用户成功")
                    .setSuccess(true)
                    .setData(user);
        }
        return restJson;
    }

    /**
     * 登陆验证
     *
     * @param username
     * @param password
     * @param session
     * @return com.pang.book.entity.RestJson<com.pang.book.entity.User>
     * @author pang
     * @date 2019/1/9
     */
    @ApiOperation("登陆验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json")
    public RestJson<User> doLogin(String username, String password, HttpSession session) {
        /*通过username找用户*/
        User realUser = userDao.findByUsername(username);
        RestJson<User> restJson = new RestJson<>();
        if (realUser == null) {
            restJson.setSuccess(false)
                    .setStatus(404)
                    .setMsg("没有找到该用户");
            return restJson;
        }
        if (!realUser.getPassword().equals(password)) {
            restJson.setMsg("密码不正确")
                    .setSuccess(false)
                    .setStatus(403);
            return restJson;
        }
        restJson.setStatus(200)
                .setSuccess(true)
                .setMsg("登陆成功");
        session.setAttribute("user", userDao.findByUsername(username));
        return restJson;
    }
}
