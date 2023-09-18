import { reqGetBlog,reqGetBlogCount,reqGetClassIfyCount,reqGetTagsCount } from "@/api";
//仓库存储数据的地方
const state = {
    blog:[],
    count:0,
};
//修改仓库数据的唯一手段
const mutations = {
    GETBLOG(state,blog){
            if(blog.length!=0){
                state.blog=blog
            }
    },
    GETCOUNT(state,count){
        state.count=count
    },
};
//处理action
const actions = {
    async getBlog({commit},{pageNum,pageSize}){
        let result = await reqGetBlog(pageNum,pageSize);
        if(result.code==20000){
            commit('GETBLOG',result.data)
        }
    },
    async getCount({commit}){
        let resultBlog = await reqGetBlogCount();
        let resultClassIfy = await reqGetClassIfyCount();
        let resultTags = await reqGetTagsCount();
        const count={
            blogCount:resultBlog.data,
            classifyCount:resultClassIfy.data,
            tagsCount:resultTags.data
        }
        if(resultBlog.code==20000 || resultClassIfy.code == 20000 || resultTags== 20000){
            commit('GETCOUNT',count)
        }
    }
};
//理解为计算属性
const getters = {};
export default {
    state,
    mutations,
    actions,
    getters

};