package com.hncpu.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hncpu.blog.entity.TbUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TbUserMapper extends BaseMapper<TbUserEntity> {
    @Select("select * from tb_user limit #{pageNum},#{pageSize}")
    List<TbUserEntity> selectTbBlogByPage(int pageNum, int pageSize);
    @Update("UPDATE tb_user SET avatar=#{user.avatar}, login_user_name=#{user.loginUserName} WHERE admin_user_id=#{user.adminUserId}")
    int updateUserById(@Param("user") TbUserEntity tbUserEntity);
    @Update("UPDATE tb_user SET login_password=#{user.loginPassword} WHERE admin_user_id=#{user.adminUserId}")
    int updateUserPwd(@Param("user") TbUserEntity tbUser);
    @Update("UPDATE tb_user SET level=#{user.level},locked=#{user.locked} WHERE admin_user_id=#{user.adminUserId}")
    int updateUserLevelAndLocked(@Param("user") TbUserEntity tbUser);
}
