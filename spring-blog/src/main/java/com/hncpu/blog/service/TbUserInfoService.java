package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.entity.TbUserInfoEntity;

public interface TbUserInfoService extends IService<TbUserInfoEntity> {
    TbUserInfoEntity getUserInfo();
}
