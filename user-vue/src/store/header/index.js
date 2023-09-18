import { reqGetClassif } from "@/api";
//仓库存储数据的地方
const state = {
    weather: true,
    classify:''
};
//修改仓库数据的唯一手段
const mutations = {
    USEWEATHER(state) {
        state.weather = !state.weather
    },
    GETWEATHER(state) {
        state.weather = true
    },
    GetClassify(state,classify){
        state.classify= classify
    }
};
//处理action
const actions = {
    SetWeather({commit}) {
        commit('USEWEATHER')
    },
    async GetClassify({commit}){
        let result = await reqGetClassif();
        if(result.code==20000){
            commit('GetClassify',result.data)
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