package com.hncpu.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hncpu.blog.entity.TbFriendEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TbFriendMapper extends BaseMapper<TbFriendEntity> {
    @Select("SELECT friend_id,friend_name,friend_address,friend_image,friend_signature,is_deleted,create_time FROM tb_friend LIMIT #{pageNum},#{pageSize}")
    List<TbFriendEntity> selectFrienList(int pageNum, int pageSize);
    @Update("UPDATE tb_friend SET friend_name=#{friend.friendName},friend_address=#{friend.friendAddress},friend_image=#{friend.friendImage},friend_signature=#{friend.friendSignature},is_deleted=#{friend.isDeleted} WHERE friend_id=#{friend.friendId}")
    int updateFriendById(@Param("friend") TbFriendEntity tbFriendEntity);
    @Select("SELECT friend_id,friend_name,friend_address,friend_image,friend_signature,is_deleted,create_time FROM tb_friend WHERE friend_id=#{Id}")
    TbFriendEntity queryTagByFriendId(Integer id);
}
