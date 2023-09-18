package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@TableName("tb_blog")
@AllArgsConstructor
@NoArgsConstructor
public class TbBlogEntity {
    /** 文章ID */
    @TableId(type = IdType.AUTO)
    private Integer blogId;
    /** 文章标题 */
    private String blogTitle;
    /** 文章内容 */
    private String blogContent;
    /** 文章归档ID */
    private Integer blogClassifyId;
    /** 文章状态 0 完成 1 编辑 */
    private Integer blogStatus;
    /** 文章是否删除 0 未删除 1 删除 */
    @TableLogic
    private Integer isDeleted;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
