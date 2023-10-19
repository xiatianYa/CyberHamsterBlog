<template>
    <v-md-editor class="md-editor" v-model="essay.blogContent" :disabled-menus="[]" @upload-image="handleUploadImage"
        @save="UpdateEssay"></v-md-editor>
</template>

<script>
import {reqGetEssayById, reqUploadOssFile, reqUpdateEssay,} from '@/api/blog'
export default {
    data() {
        return {
            essay: '',
            listLoading: true,
            timer:'',
        }
    },
    created() {
      //监听路由变化 关闭定时器
      this.$router.beforeEach( async (to, from, next) => {
        this.clearTime()
        next()
      })
        this.GetEssayListByID()
        //启动定时保存定时器
        this.startTime()
    },
    methods: {
        async GetEssayListByID() {
            this.listLoading = true
            const result = await reqGetEssayById(this.$route.query.blogId)
            if (result.code === 20000) {
                this.essay = result.data
                this.listLoading = false
            }
        },
        async handleUploadImage(event, insertImage, file) {
            let formData = new FormData()
            formData.append('file', file[0])
            const result = await reqUploadOssFile(formData)
            if (result.code === 20000) {
                // 此处只做示例
                insertImage({
                    url: result.data,
                    desc: '',
                    width: '100%',
                    height: 'auto',
                });
                this.$message({
                    type: 'success',
                    message: '上传成功'
                });
                this.UpdateEssay();
            } else {
                this.$message({
                    type: 'error',
                    message: '上传失败'
                });
            }
        },
        async UpdateEssay() {
          //提示是否清空内容
          if (!this.essay.blogContent.length){
            this.$confirm('此操作将清空内容', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(async () => {
              const result = await reqUpdateEssay(this.essay)
              if (result.code === 20000) {
                this.$message({
                  type: 'success',
                  message: result.msg
                });
                await this.GetEssayListByID()
              } else {
                this.$message({
                  type: 'error',
                  message: result.msg
                });
              }
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消成功'
              });
              this.GetEssayListByID()
            });
          }else{
            const inSpectBlog = await reqGetEssayById(this.$route.query.blogId)
            //判断当前字符数量是否小于保存数量(500个字符)
            if (this.essay.blogContent.length<inSpectBlog.data.blogContent.length-500){
              this.$message({
                type: 'info',
                message: '为确保误删,你不能删除文章过多内容!'
              });
              await this.GetEssayListByID();
              return;
            }
            const result = await reqUpdateEssay(this.essay)
            if (result.code === 20000) {
              this.$message({
                type: 'success',
                message: result.msg
              });
              await this.GetEssayListByID()
            } else {
              this.$message({
                type: 'error',
                message: result.msg
              });
            }
          }
        },
        async startTime(){
          this.timer=setInterval(async ()=>{
            this.$message({
              type: 'success',
              message: '定时保存中...'
            });
            const inSpectBlog = await reqGetEssayById(this.$route.query.blogId)
            //定时保存字符必须大于之前字符数
            if (this.essay.blogContent.length>=inSpectBlog.data.blogContent.length){
              this.$message({
                type: 'error',
                message: '定时保存失败(请手动保存)!'
              });
              return;
            }else{
              const result = await reqUpdateEssay(this.essay)
              if (result.code === 20000) {
                this.$message({
                  type: 'success',
                  message: result.msg
                });
                await this.GetEssayListByID()
              } else {
                this.$message({
                  type: 'error',
                  message: result.msg
                });
              }
            }
          },10* 60 * 1000)
        },
      clearTime(){
          clearInterval(this.timer)
        }
    },
}
</script>
<style scoped>
.md-editor{
  height: 100vh;
}
</style>
