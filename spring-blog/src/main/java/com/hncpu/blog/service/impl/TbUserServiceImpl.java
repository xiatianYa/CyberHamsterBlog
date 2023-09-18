package com.hncpu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncpu.blog.entity.TbUserEntity;
import com.hncpu.blog.mapper.TbUserMapper;
import com.hncpu.blog.service.TbUserService;
import com.hncpu.blog.exception.BlogException;
import com.hncpu.blog.utils.BlogProperty;
import com.hncpu.blog.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUserEntity> implements TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;
    /** 查询所有用户 */
    @Override
    public List<TbUserEntity> queryAllByPage(int pageNum, int pageSize) {
        return tbUserMapper.selectTbBlogByPage(pageNum, pageSize);
    }

    @Override
    public TbUserEntity queryTagByUserId(Integer id) throws BlogException {
        if (id==null){
            throw new BlogException("参数非法");
        }
        return baseMapper.selectById(id);
    }

    @Override
    public int updateByUserId(TbUserEntity tbUserEntity) throws BlogException {
        if (tbUserEntity.getLoginPassword().equals("") || tbUserEntity.getLoginUserName().equals("")){
            throw new BlogException("密码或用户名不允许未空");
        }
        TbUserEntity tbUser = tbUserMapper.selectById(tbUserEntity.getAdminUserId());
        tbUserEntity.setLoginPassword(Md5Utils.encryptPassword(tbUser.getLoginUserName(),tbUserEntity.getLoginPassword(), BlogProperty.MD5_SALT));
        return baseMapper.updateUserById(tbUserEntity);
    }

    @Override
    public int insertUser(TbUserEntity tbUserEntity) throws BlogException {
        LambdaQueryWrapper<TbUserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TbUserEntity::getLoginUserName,tbUserEntity.getLoginUserName());
        tbUserEntity.setLoginPassword(Md5Utils.encryptPassword(tbUserEntity.getLoginUserName(),tbUserEntity.getLoginPassword(), BlogProperty.MD5_SALT));
        if (baseMapper.selectCount(wrapper)>0){
            throw new BlogException("用户已存在");
        }
        return baseMapper.insert(tbUserEntity);
    }

    @Override
    public int deleteByUserId(Integer id) throws BlogException {
        LambdaQueryWrapper<TbUserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TbUserEntity::getAdminUserId,id);
        if (baseMapper.selectCount(wrapper)<0){
            throw new BlogException("用户不存在");
        }
        return baseMapper.deleteById(id);
    }

    @Override
    public int UpdateUserPwd(TbUserEntity tbUser) {
        return baseMapper.updateUserPwd(tbUser);
    }

    @Override
    public int updateUserLevelAndLocked(TbUserEntity tbUser) {
        return baseMapper.updateUserLevelAndLocked(tbUser);
    }
}
