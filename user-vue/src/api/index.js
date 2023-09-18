import requests from './ajax'
// 获取文章列表
export const reqGetBlog=(pageNum,pageSize)=>requests({url:`/blog/list/${pageNum}/${pageSize}`,method:'get'})
// 获取归档菜单
export const reqGetClassif=()=>requests({url:`/classify/list`,method:'get'})
// 获取标签菜单
export const reqGetTags=()=>requests({url:`/tags/list`,method:'get'})
// 获取友链列表
export const reqGetFriend=()=>requests({url:`/friend/list`,method:'get'})
// 根据归档ID 查询文章
export const reqGetBlogByClassIfyId=(Id)=>requests({url:`/blog/byClassId/${Id}`,method:'get'})
// 根据文章Id 查询文章
export const reqGetBlogByBlogId=(Id)=>requests({url:`/blog/queryByBlogId/${Id}`,method:'get'})
// 根据标签Id 查询文章列表
export const reqGetBlogByTagsId=(Id)=>requests({url:`/blog/byTagsId/${Id}`,method:'get'})
// 获取文章数量
export const reqGetBlogCount=()=>requests({url:`/blog/getBlogCount`,method:'get'})
// 获取归档数量
export const reqGetClassIfyCount=()=>requests({url:`/classify/getClassifyCount`,method:'get'})
// 获取标签数量
export const reqGetTagsCount=()=>requests({url:`/tags/getTagsCount`,method:'get'})
// 获取个人信息
export const reqGetUserInfo=()=>requests({url:`/userInfo`,method:'get'})
// 获取评论信息
export const reqGetMessageList=(data)=>requests({url:`/message`,method:'post',data:data})
//添加评论
export const reqGetinsertMessage=(data)=>requests({url:`/message/insertMessage`,method:'post',data:data})
//留言的留言评论
export const reqGetinsertReply=(data)=>requests({url:`/reply/insertReply`,method:'post',data:data})
//点爱心
export const reqGetAddLike=(Id)=>requests({url:`/message/addLike/${Id}`,method:'get'})
//举报留言
export const reqGetAddMessageReport=(Id)=>requests({url:`/message/report/${Id}`,method:'post'})
