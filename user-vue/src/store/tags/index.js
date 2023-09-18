import { reqGetTags } from "@/api";
//仓库存储数据的地方
const state = {
    tags:''
};
//修改仓库数据的唯一手段
const mutations = {
    GETTAGS(state,tags) {
            state.tags = tags
    },
};
//处理action
const actions = {
    async getTags({commit}){
        let result = await reqGetTags();
        if(result.code==20000){
            commit('GETTAGS',result.data)
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