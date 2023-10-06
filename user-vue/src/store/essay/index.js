import { reqGetBlogByBlogId, reqGetBlogCount, reqGetBlog } from "@/api";
//仓库存储数据的地方
const state = {
    blog: '',
    countList: [{}],
    BlogList: {}
};
//修改仓库数据的唯一手段
const mutations = {
    GETBLOGBYID(state, blog) {
        state.blog = blog
    },
    GETCOUNT(state, countList) {
        state.countList = countList
    },
    GETBLOG(state, BlogList) {
        state.BlogList = BlogList
    }
};
//处理action
const actions = {
    async getBlogById({ commit }, blogId) {
        let result = await reqGetBlogByBlogId(blogId);
        if (result.code == 20000) {
            commit('GETBLOGBYID', result.data)
        }
    },
    async getCount({ commit }) {
        let result = await reqGetBlogCount();
        if (result.code == 20000) {
            commit('GETCOUNT', result.data)
        }
    },
    async getEsSayBlog({ commit }, { pageNum, pageSize }) {
        let result = await reqGetBlog(pageNum, pageSize);
        if (result.code == 20000) {
            commit('GETBLOG', result.data)
        }
    }
};
//理解为计算属性
const getters = {};
export default {
    state,
    mutations,
    actions,
    getters,
};