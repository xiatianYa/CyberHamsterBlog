package com.hncpu.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncpu.blog.entity.TbUserInfoEntity;
import com.hncpu.blog.mapper.TbUserInfoMapper;
import com.hncpu.blog.service.TbUserInfoService;
import org.springframework.stereotype.Service;

@Service
public class TbUserInfoServiceImpl extends ServiceImpl<TbUserInfoMapper, TbUserInfoEntity> implements TbUserInfoService {

    @Override
    public TbUserInfoEntity getUserInfo() {
        return baseMapper.selectById(1);
    }
}
