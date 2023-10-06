import request from '@/utils/request'
//获取文章数量
export function reqGetBlogCount() {
  return request({
    url: '/blog/getBlogCount',
    method: 'get',
  })
}
//获取归档数量
export function reqGetClassIfyCount() {
  return request({
    url: '/classify/getClassifyCount',
    method: 'get',
  })
}
//获取标签数量
export function reqGetTagsCount() {
  return request({
    url: '/tags/getTagsCount',
    method: 'get'
  })
}
//获取留言数量
export function getMessageCount() {
  return request({
    url: '/message/getMessageCount',
    method: 'get'
  })
}
//获取归档下文章数量
export function getClassIfyAndBlogCount() {
  return request({
    url: '/classify/getClassIfyAndBlogCount',
    method: 'get'
  })
}
//获取近期文章
export function getRecentBlogList() {
  return request({
    url: '/blog/list/0/10',
    method: 'get'
  })
}
//获取近期留言
export function getMessageList() {
  return request({
    url: '/message',
    method: 'post',
    data: { pageNum: 0, pageSize: 10, type: -1 }
  })
}
//获取访问最多的文章
export function getAccessBlogList() {
  return request({
    url: '/blogCount',
    method: 'get',
  })
}
//获取文章访问统计图
export function getBlogCountByYear() {
  return request({
    url: '/classIfyCount',
    method: 'get',
  })
}