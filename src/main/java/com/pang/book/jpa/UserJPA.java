package com.pang.book.jpa;

import com.pang.book.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: MessageBook
 * @description: user表操作接口
 * @author: Pang
 * @create: 2018-12-21 23:49
 **/
public interface UserJPA extends JpaRepository<User,Long> {
    User findByUserName(String username);
}
