package com.hncpu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncpu.blog.entity.TbFriendEntity;
import com.hncpu.blog.mapper.TbFriendMapper;
import com.hncpu.blog.service.TbFriendService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TbFriendServiceImpl extends ServiceImpl<TbFriendMapper, TbFriendEntity> implements TbFriendService {
    /** 查询友链列表 */
    @Override
    public List<TbFriendEntity> queryAll() {
        return baseMapper.selectList(new LambdaQueryWrapper<>());
    }
    /** 查询友链列表 后台 */
    @Override
    public List<TbFriendEntity> selectFrienList(int pageNum, int pageSize) {
        return baseMapper.selectFrienList(pageNum,pageSize);
    }

    @Override
    public int updateByFriendById(TbFriendEntity tbFriendEntity) {
        return baseMapper.updateFriendById(tbFriendEntity);
    }

    @Override
    public int deleteByFriendId(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int insertFriend(TbFriendEntity tbFriendEntity) {
        tbFriendEntity.setCreateTime(new Date());
        return baseMapper.insert(tbFriendEntity);
    }

    @Override
    public TbFriendEntity queryTagByFriendId(Integer id) {
        return baseMapper.queryTagByFriendId(id);
    }


}
