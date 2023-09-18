import request from '@/utils/request'
//查询用户列表
export function reqGetUserList(param) {
    return request({
        url: '/user/0/30',
        method: 'get',
    })
}
//获取用户信息
export function  reqGetUserById(Id) {
    return request({
      url: `/user/queryTagByUserId/${Id}`,
      method: 'get',
    })
  }

  //修改权限
export function  reqUpdateUser(user) {
    return request({
      url: `/user/updateUserLevelAndLocked`,
      method: 'post',
      data:user
    })
  }
  
