package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("tb_message_report")
@AllArgsConstructor
@NoArgsConstructor
public class TbMessageReportEntity {
    @TableId(type = IdType.AUTO)
    private Integer reportId;
    private Integer messageId;
    private Date createTime;
}
