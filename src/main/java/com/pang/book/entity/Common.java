package com.pang.book.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
@ApiModel("评论回复")
public class Common {
    @ApiModelProperty("回复id")
    private Integer commonId;
    @ApiModelProperty("回复时间")
    private Date commonTime;
    @ApiModelProperty("receiver")
    private Integer commonTo;
    @ApiModelProperty("留言id")
    private Integer messageId;
    @ApiModelProperty("sender")
    private Integer commonFrom;
    @ApiModelProperty("回复内容")
    private String commonContent;

    public Integer getCommonId() {
        return commonId;
    }

    public void setCommonId(Integer commonId) {
        this.commonId = commonId;
    }

    public Date getCommonTime() {
        return commonTime;
    }

    public void setCommonTime(Date commonTime) {
        this.commonTime = commonTime;
    }

    public Integer getCommonTo() {
        return commonTo;
    }

    public void setCommonTo(Integer commonTo) {
        this.commonTo = commonTo;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getCommonFrom() {
        return commonFrom;
    }

    public void setCommonFrom(Integer commonFrom) {
        this.commonFrom = commonFrom;
    }

    public String getCommonContent() {
        return commonContent;
    }

    public void setCommonContent(String commonContent) {
        this.commonContent = commonContent == null ? null : commonContent.trim();
    }
}