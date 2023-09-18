import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from './routes'
//使用插件
Vue.use(VueRouter)
//重写 push / replace
let originPush = VueRouter.prototype.push
let originReplace = VueRouter.prototype.replace
VueRouter.prototype.push = function (location, resolve, reject) {
    if (resolve && reject) {
        originPush.call(this, location, resolve, reject)
    } else {
        originPush.call(this, location, resolve => {
        }, reject => {
        })
    }
}
VueRouter.prototype.replace = function (location, resolve, reject) {
    if (resolve && reject) {
        originReplace.call(this, location, resolve, reject)
    } else {
        originReplace.call(this, location, resolve => {
        }, reject => {
        })
    }
}
let router = new VueRouter({
    routes,
    mode: 'hash',
})
//全局路由守卫
router.beforeEach(async (to, from, next) => {
    next()
})
export default router;
