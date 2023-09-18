package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@TableName("tb_friend")
@AllArgsConstructor
@NoArgsConstructor
public class TbFriendEntity {
    /** 友链主键 */
    @TableId(type = IdType.AUTO)
    private Integer friendId;
    /** 友链姓名 */
    private String friendName;
    /** 友链网址 */
    private String friendAddress;
    /** 友链图片 */
    private String friendImage;
    /** 友链签名 */
    private String friendSignature;
    /** 文章是否删除 0 未删除 1 删除 */
    @TableLogic
    private Integer isDeleted;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
}
