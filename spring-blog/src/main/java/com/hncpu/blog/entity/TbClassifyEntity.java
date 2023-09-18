package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@TableName("tb_classify")
@AllArgsConstructor
@NoArgsConstructor
public class TbClassifyEntity {
    /** 归档ID */
    @TableId(type = IdType.AUTO)
    private Integer classifyId;
    /** 文章名称 */
    private String classifyName;
    /** 标签是否删除 0 未删除 1 删除 */
    @TableLogic
    private Integer isDeleted;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
