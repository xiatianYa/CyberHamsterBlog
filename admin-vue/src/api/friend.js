import request from '@/utils/request'
//查询友链列表
export function reqGetFriendList(param) {
    return request({
        url: '/friend/0/30',
        method: 'get',
    })
}
//获取标签信息
export function  reqGetFriendById(Id) {
    return request({
      url: `/friend/queryTagByFriendId/${Id}`,
      method: 'get',
    })
  }
//修改友链列表
export function reqUpdateFriend(tags) {
    return request({
        url: '/friend/updateByFriendId',
        method: 'post',
        data:tags
    })
}

//新增友链
export function reqAddFriend(tags) {
    return request({
        url: '/friend/insertFriend',
        method: 'post',
        data:tags
    })
}

//删除友链
export function reqDeleteFriend(Id) {
    return request({
        url: `/friend/deleteByFriendId/${Id}`,
        method: 'get',
    })
}
