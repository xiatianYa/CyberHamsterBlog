package com.hncpu.blog.dto;

import com.hncpu.blog.entity.TbReplyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbMessageQueryDTO {
    private Integer messageId;
    private String messageName;
    private String messageAvatar;
    private Integer messageType;
    private String messageColor;
    private String messageContent;
    private Integer messageLike;
    private List<TbReplyEntity> replyList;
    private Date createTime;
}
