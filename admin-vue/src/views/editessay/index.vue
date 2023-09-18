<template>
    <v-md-editor class="md-editor" v-model="essay.blogContent" :disabled-menus="[]" @upload-image="handleUploadImage"
        @save="UpdateEssay"></v-md-editor>
</template>
  
<script>
import { reqGetEssayById, reqUploadOssFile, reqUpdateEssay } from '@/api/blog'
export default {
    data() {
        return {
            essay: '',
            listLoading: true,
        }
    },
    created() {
        this.GetEssayListByPage()
    },
    methods: {
        async GetEssayListByPage() {
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
            const result = await reqUpdateEssay(this.essay)
            if (result.code === 20000) {
                this.$message({
                    type: 'success',
                    message: result.msg
                });
                this.GetEssayListByPage()
            } else {
                this.$message({
                    type: 'error',
                    message: result.msg
                });
            }
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
  