package com.hncpu.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hncpu.blog.entity.TbUserEntity;
import com.hncpu.blog.exception.BlogException;

import java.util.List;

public interface TbUserService extends IService<TbUserEntity>{
    List<TbUserEntity> queryAllByPage(int pageNum,int pageSize);

    TbUserEntity queryTagByUserId(Integer id) throws BlogException;

    int updateByUserId(TbUserEntity tbUserEntity) throws BlogException;

    int insertUser(TbUserEntity tbUserEntity) throws BlogException;

    int deleteByUserId(Integer id) throws BlogException;

    int UpdateUserPwd(TbUserEntity tbUser);

    int updateUserLevelAndLocked(TbUserEntity tbUser);
}
