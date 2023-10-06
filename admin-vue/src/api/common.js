import request from '@/utils/request'

//上传图片
export function reqUploadFile(file) {
    return request({
      url: `/fileoss/uploadOssFile`,
      method: 'post',
      data:file
    })
  }