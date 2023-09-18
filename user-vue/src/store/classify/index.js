import { reqGetBlogByClassIfyId } from "@/api";
//仓库存储数据的地方
const state = {
    blogs:''
};
//修改仓库数据的唯一手段
const mutations = {
    GETBLOGSBYCLASSID(state,blogs){
        state.blogs=blogs
    },

};
//处理action
const actions = {
    async getBlogsByClassId({commit},Id){
        let result = await reqGetBlogByClassIfyId(Id);
        if(result.code==20000){
            commit('GETBLOGSBYCLASSID',result.data)
        }
    },
};
//理解为计算属性
const getters = {};
export default {
    state,
    mutations,
    actions,
    getters
};