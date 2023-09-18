package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.entity.TbFriendEntity;

import java.util.List;

public interface TbFriendService extends IService<TbFriendEntity> {
    List<TbFriendEntity> queryAll();

    List<TbFriendEntity> selectFrienList(int pageNum, int pageSize);

    int updateByFriendById(TbFriendEntity tbFriendEntity);

    int deleteByFriendId(Integer id);

    int insertFriend(TbFriendEntity tbFriendEntity);

    TbFriendEntity queryTagByFriendId(Integer id);
}
