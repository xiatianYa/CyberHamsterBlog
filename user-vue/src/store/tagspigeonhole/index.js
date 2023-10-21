import { reqGetBlogByTagsId } from "@/api";
//仓库存储数据的地方
const state = {
    blogs:''
};
//修改仓库数据的唯一手段
const mutations = {
    GETBLOGSBYTAGSID(state,blogs){
        state.blogs=blogs
    }
};
//处理action
const actions = {
    async getBlogsByTagsId({commit},TagsId){
        let result = await reqGetBlogByTagsId(TagsId);
        if(result.code===20000){
            commit('GETBLOGSBYTAGSID',result.data)
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