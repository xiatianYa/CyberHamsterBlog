package com.hncpu.blog.config;

import cn.dev33.satoken.stp.StpInterface;
import com.hncpu.blog.entity.TbUserEntity;
import com.hncpu.blog.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    private TbUserService tbUserService;

    /**
     * 返回一个账号所拥有的权限码集合 
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        TbUserEntity TbUser = tbUserService.getById(Integer.parseInt(loginId.toString()));
        int level = TbUser.getLevel();
        switch (level){
            //普通用户 拥有修改 添加 查询
            case 0:
                list.add("user.get");
                break;
            //开发人员 拥有修改 添加 查询 删除
            case 1:
                list.add("user.add");
                list.add("user.update");
                list.add("user.get");
                list.add("user.delete");
                break;
            //管理人员 拥有修改 添加 查询 删除 用户管理
            case 2:
                list.add("user.add");
                list.add("user.update");
                list.add("user.get");
                list.add("user.delete");
                list.add("user.manager");
                break;
        }
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("super-admin");
        return list;
    }
}