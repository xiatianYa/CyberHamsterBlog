import { reqGetFriend } from "@/api";
//仓库存储数据的地方
const state = {
    friends:''
};
//修改仓库数据的唯一手段
const mutations = {
    GETFRIEND(state,friends) {
            state.friends = friends
    },
};
//处理action
const actions = {
    async getFriend({commit}){
        let result = await reqGetFriend();
        if(result.code==20000){
            commit('GETFRIEND',result.data)
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