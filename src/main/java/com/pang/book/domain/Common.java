package com.pang.book.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @program: MessageBook
 * @description: 回复实体
 * @author: Pang
 * @create: 2018-12-21 23:46
 **/
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="common_id")
    private long id;

    @Column(name= "common_time")
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd hh:mm")
    private Timestamp time;

    @Column(name="common_content")
    private String content;

    @Column(name="common_to")
    private long toId;

    @ManyToOne(fetch = FetchType.EAGER,optional=false)
    @JoinColumn(name="common_from")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER,optional=false)
    @JoinColumn(name="message_id")
    private Message message;

}
