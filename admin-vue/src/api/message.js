import request from '@/utils/request'
//查询举报留言列表
export function reqGetMessageReportList(param) {
    return request({
        url: `/message/report/${param.current}/${param.size}`,
        method: 'get',
    })
}

//删除举报留言
export function reqDeleteMessageReport(Ids) {
    return request({
        url: `/message/report/deleteMessageReport`,
        method: 'post',
        data:Ids
    })
}
//移除举报留言
export function reqYesMessageReport(Ids) {
    return request({
        url: `/message/report/yesMessageReport`,
        method: 'post',
        data:Ids
    })
}