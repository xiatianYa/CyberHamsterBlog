import request from '@/utils/request'

//获取文章列表
export function reqGetEssayListByPage(pageNum,pageSize) {
  return request({
    url: `/blog/essay/${pageNum}/${pageSize}`,
    method: 'get',
  })
}

//根据文章Id查询文章信息
export function reqGetEssayById(blogId) {
  return request({
    url: `/blog/queryEssayById/${blogId}`,
    method: 'get',
  })
}
//提交修改文章信息
export function reqUpdateEssay(essay){
  return request({
    url: `/blog/updateBlogById`,
    method: 'post',
    data:essay,
  })
}
//删除文章
export function reqDeleteEssay(blogId){
  return request({
    url: `/blog/deleteBlogById/${blogId}`,
    method: 'get',
  })
}
//新增文章
export function reqAddEssay(essay){
  return request({
    url: `/blog/insertEssay`,
    method: 'post',
    data:essay,
  })
}
//删除文章
export function reqRemoveEssay(Ids){
  return request({
    url: `/blog/deleteEssayByIds`,
    method: 'post',
    data:Ids,
  })
}
//Oss文件上传
export function reqUploadOssFile(file){
  return request({
    url: `/fileoss/uploadOssFile`,
    method: 'post',
    data:file,
  })
}