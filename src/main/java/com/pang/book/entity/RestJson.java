package com.pang.book.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 返回的状态
 * @param <T> 任意数据类型
 */
@Data
@Accessors(chain=true)
public class RestJson<T> {
    private T data;
    private int status;
    private String msg;
    private boolean success;

    public RestJson() {
    }

}