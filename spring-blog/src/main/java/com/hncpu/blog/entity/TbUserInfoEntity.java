package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("tb_userinfo")
@AllArgsConstructor
@NoArgsConstructor
public class TbUserInfoEntity {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String name;
    private String avatar;
    private String introduce;
    private String github;
    private String gitee;
    private String email;
    private String wechat;
}
