import request from '@/utils/request'
export function getUserInfo() {
    return request({
      url: '/userInfo',
      method: 'get',
    })
  }