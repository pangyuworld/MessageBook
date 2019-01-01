package com.pang.book.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: MessageBook
 * @description: 评论实体
 * @author: Pang
 * @create: 2018-12-21 23:42
 **/
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "message_id")
    private long id;

    @CreatedDate
    @Column(name= "message_time")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm")
    private Timestamp time;

    @Column(name= "message_content")
    private String content;

    @OneToMany(mappedBy = "message",fetch = FetchType.LAZY)
    private List<Common> commons;

    @ManyToOne(fetch = FetchType.EAGER,optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="user_id")//设置在article表中的关联字段(外键)
    private User user;

}
