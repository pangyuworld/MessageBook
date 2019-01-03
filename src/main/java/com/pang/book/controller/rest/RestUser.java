package com.pang.book.controller.rest;

import com.pang.book.entity.RestJson;
import com.pang.book.entity.User;
import com.pang.book.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestUser {
    @Autowired
    private UserService userDao;

    /**
     * 根据id获得User
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public RestJson<User> getUser(@PathVariable int id) {
        User user = userDao.findById(id);
        RestJson<User> restJson = new RestJson<>();
        if (user == null) {
            restJson.setMsg("not found user")
                    .setSuccess(false)
                    .setStatus(404);
        } else {
            restJson.setSuccess(true)
                    .setStatus(200)
                    .setData(user)
                    .setMsg("find one user");
        }
        return restJson;
    }

    /**
     * 获得全部的User
     *
     * @return
     */
    @RequestMapping(value = "/user/all", method = RequestMethod.GET, produces = "application/json")
    public RestJson<List<User>> getUser(int page, int perpage) {
        List<User> userList;
        if (page > 0) {
            userList = userDao.findAllUser(page, perpage);
        } else {
            userList = userDao.findAllUser();
        }
        RestJson<List<User>> restJson = new RestJson<>();
        if (userList == null) {
            restJson.setSuccess(false)
                    .setMsg("not found user")
                    .setStatus(404);
        } else {
            restJson.setStatus(200)
                    .setSuccess(true)
                    .setMsg("find all users")
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
    @RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = "application/json")
    public RestJson<User> addUser(User user) {
        RestJson<User> restJson = new RestJson<>();
        try {
            userDao.insert(user);
            restJson.setMsg("add one user")
                    .setSuccess(true)
                    .setData(userDao.findByUsername(user.getUsername()))
                    .setStatus(201);
        } catch (DataAccessException e) {
            restJson.setMsg("the user is existing")
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
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public RestJson<User> deleteUser(@PathVariable int id) {
        RestJson<User> restJson = new RestJson<>();
        User user = userDao.findById(id);
        if (user == null) {
            restJson.setSuccess(false)
                    .setMsg("the user is not existing")
                    .setStatus(404);
        } else {
            userDao.deleteById(id);
            restJson.setSuccess(true)
                    .setMsg("delete one user")
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
    @RequestMapping(value = "user/{id}", method = RequestMethod.PUT, produces = "application/json")
    public RestJson<User> updateUser(User user, @PathVariable int id) {
        User userTemp = userDao.findById(id);
        RestJson<User> restJson = new RestJson<>();
        if (userTemp == null) {
            restJson.setMsg("the user is not existing")
                    .setSuccess(false)
                    .setStatus(404);
        } else {
            user.setUserId(id);
            userDao.updateUser(user);
            restJson.setStatus(200)
                    .setMsg("update one user")
                    .setSuccess(true)
                    .setData(user);
        }
        return restJson;
    }
}
