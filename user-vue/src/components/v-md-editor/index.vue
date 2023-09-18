<template>
    <div :class="weather ? 'color-130c0e' : 'color-fffffb'">
        <v-md-editor class="editor" :class="weather ? 'white' : 'black'" v-model="content" mode="preview" :disabled-menus="[]"
            @upload-image="handleUploadImage"></v-md-editor>
    </div>
</template>
  
<script>
import { mapState } from "vuex";
export default {
    name: 'mdEditor',
    data() {
        return {
            editorValue: this.content != null ? this.content : this.content,
        }
    },
    props: {
        // 接收值父组件传递值
        content: String
    },
    computed: {
        ...mapState({
            weather: (state) => state.header.weather,
        }),
    },
    methods: {
        // v-md-editor 文件上传
        handleUploadImage(event, insertImage, files) {
            // console.log(files);
            // 上传
            for (let i = 0; i < files.length; i++) {
                this.crud.upload(files[i], "image/vMdEditor/").then(res => {
                    // 获取返回数据
                    let data = res.data.data;
                    // 添加图片到内容
                    insertImage({
                        url: data.url,
                        desc: data.name
                    });
                });
            }
        },
    },
    watch: {
        editorValue: function (newNum, oldNum) {
            // 修改调用者传入的值
            this.$emit('update:content', newNum)
        }
    },
}
</script>
  
<style lang="less" scoped>
.white{
    background-color: #FFFFFF;
}
.black{
    background-color: #1A1A1A;
}
.editor{
    border-radius: 20px;
}
</style>