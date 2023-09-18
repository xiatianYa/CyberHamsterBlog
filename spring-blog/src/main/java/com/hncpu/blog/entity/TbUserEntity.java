package com.hncpu.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class TbUserEntity {
    /** 用户ID */
    @TableId(type = IdType.AUTO)
    private Integer adminUserId;
    /** 用户头像 */
    private String avatar;
    /** 用户姓名 */
    private String loginUserName;
    /** 用户密码 */
    private String loginPassword;
    /** 用户级别 */
    private int level;
    /** 用户是否被锁定 0 未锁定 1 锁定 */
    private Integer locked;
}
