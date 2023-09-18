package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("tb_reply")
@AllArgsConstructor
@NoArgsConstructor
public class TbReplyEntity {
    @TableId(type = IdType.AUTO)
    private Integer replyId;
    private String messageId;
    private String replyName;
    private String replyAvatar;
    private String replyContent;
    private Date createTime;
}
