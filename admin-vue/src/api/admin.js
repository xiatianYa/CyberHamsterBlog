import request from '@/utils/request'
//查询用户列表
export function reqGetUserList(param) {
    return request({
        url: '/user/0/30',
        method: 'get',
    })
}
//获取用户信息
export function reqGetUserById(Id) {
    return request({
      url: `/user/queryTagByUserId/${Id}`,
      method: 'get',
    })
  }
//修改用户列表
export function reqUpdateUser(User) {
    return request({
        url: '/user/updateByUserId',
        method: 'post',
        data:User
    })
}
//修改用户密码
export function reqUpdateUserPwd(User) {
    return request({
        url: '/user/updateUserPwd',
        method: 'post',
        data:User
    })
}

//新增用户
export function reqAddUser(User) {
    return request({
        url: '/user/insertUser',
        method: 'post',
        data:User
    })
}

//删除用户
export function reqDeleteUser(Id) {
    return request({
        url: `/user/deleteByUserId/${Id}`,
        method: 'get',
    })
}
