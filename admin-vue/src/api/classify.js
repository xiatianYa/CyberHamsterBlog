import request from '@/utils/request'

//获取归档列表
export function reqGetClassIfyListByPage() {
    return request({
        url: '/classify/classIfy/0/30',
        method: 'get',
    })
}
//获取归档信息
export function reqGetClassIfyById(Id) {
    return request({
      url: `/classify/queryClassIfyById/${Id}`,
      method: 'get',
    })
  }
//修改归档列表
export function reqUpdateClassIfy(ClassIfy) {
    return request({
        url: '/classify/updateClassIfyById',
        method: 'post',
        data:ClassIfy
    })
}

//新增归档
export function reqAddClassIfy(ClassIfy) {
    return request({
        url: '/classify/insertClassIfy',
        method: 'post',
        data:ClassIfy
    })
}
//删除归档
export function reqDeleteClassIfy(ClassIfy) {
    return request({
        url: `/classify/deleteClassIfyById/${ClassIfy}`,
        method: 'get',
    })
}