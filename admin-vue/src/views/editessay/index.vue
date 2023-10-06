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
            console.log(this.essay.blogContent.length)
            console.log(inSpectBlog.data.blogContent.length)
            if (this.essay.blogContent.length<inSpectBlog.data.blogContent.length){
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
          },5* 60 * 1000)
        },
      clearTime(){
          clearInterval(this.timer)
        }
    },
}
</script>
<style scoped>
@media screen and (max-height: 320px) {
    .md-editor {
        height: 370px;
    }
}

@media screen and (min-height: 320px) {
    .md-editor {
        height: 370px;
    }
}

@media screen and (min-height: 360px) {
    .md-editor {
        height: 410px;
    }
}

@media screen and (min-height: 375px) {
    .md-editor {
        height: 435px;
    }
}

@media screen and (min-height: 384px) {
    .md-editor {
        height: 434px;
    }
}

@media screen and (min-height: 400px) {
    .md-editor {
        height: 450px;
    }
}

@media screen and (min-height: 414px) {
    .md-editor {
        height: 464px;
    }
}

@media screen and (min-height: 424px) {
    .md-editor {
        height: 474px;
    }
}

@media screen and (min-height: 480px) {
    .md-editor {
        height: 530px;
    }
}

@media screen and (min-height: 540px) {
    .md-editor {
        height: 590px;
    }
}

@media screen and (min-height: 720px) {
    .md-editor {
        height: 770px;
    }
}

@media screen and (min-height: 750px) {
    .md-editor {
        height: 800px;
    }
}

@media screen and (min-height: 810px) {

    .md-editor {
        height: 860px;
    }
}

@media screen and (min-height: 1000px) {

    .md-editor {
        height: 1050px;
    }
}

@media screen and (min-height: 1200px) {
    .md-editor {
        height: 1250px;
    }
}
</style>
