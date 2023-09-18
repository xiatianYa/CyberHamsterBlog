import request from '@/utils/request'

//获取标签列表 带分页
export function reqGetTagList(pageInfo) {
    return request({
        url: `/tags/tag/${pageInfo.current}/${pageInfo.size}`,
        method: 'get',
    })
}
//根据Id获取标签信息
export function reqGetTagById(Id) {
    return request({
        url: `/tags/queryTagByTagId/${Id}`,
        method: 'get',
    })
}
//获取所有标签 无分页
//获取标签信息
export function reqGetEssayTagList() {
    return request({
      url: `/tags/list`,
      method: 'get',
    })
  }
//修改标签列表
export function reqUpdateTag(tags) {
    return request({
        url: '/tags/updateTagByTagId',
        method: 'post',
        data:tags
    })
}

//新增标签
export function reqAddTag(tags) {
    return request({
        url: '/tags/insertTag',
        method: 'post',
        data:tags
    })
}

//删除标签
export function reqDeleteTag(Id) {
    return request({
        url: `/tags/deleteTagByTagId/${Id}`,
        method: 'get',
    })
}