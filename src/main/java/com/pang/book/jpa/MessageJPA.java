package com.pang.book.jpa;

import com.pang.book.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: MessageBook
 * @description: message操作接口
 * @author: Pang
 * @create: 2018-12-21 23:52
 **/
public interface MessageJPA extends JpaRepository<Message,Long> {
    List<Message> findByUserId(long userId);
}
