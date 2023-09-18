package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("tb_message")
@AllArgsConstructor
@NoArgsConstructor
public class TbMessageEntity {
    @TableId(type = IdType.AUTO)
    private Integer messageId;
    private String messageName;
    private String messageAvatar;
    private Integer messageType;
    private String messageColor;
    private String messageContent;
    private Integer messageLike;
    private Date createTime;

}
