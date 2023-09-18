import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout/index.vue'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index.vue'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404.vue'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [{
      path: 'home',
      name: 'Home',
      component: () => import('@/views/home/index.vue'),
      meta: { title: '首页', icon: '数据' }
    }]
  },

  {
    path: '/blog',
    component: Layout,
    redirect: '/blog/essay',
    name: 'Example',
    meta: { title: '博客管理', icon: '分类' },
    children: [
      {
        path: 'essay',
        name: 'Essay',
        component: () => import('@/views/essay/index.vue'),
        meta: { title: '文章管理', icon: '文章' }
      },
      {
        path: 'classify',
        name: 'ClassIfy',
        component: () => import('@/views/classify/index.vue'),
        meta: { title: '归档管理', icon: '档案' }
      },
      {
        path: 'tag',
        name: 'Tag',
        component: () => import('@/views/tag/index.vue'),
        meta: { title: '标签管理', icon: '标签' }
      },
      {
        path: 'friend',
        name: 'Friend',
        component: () => import('@/views/friend/index.vue'),
        meta: { title: '友链管理', icon: '朋友' }
      },
      {
        path: 'editessay',
        name: 'EditEssay',
        component: () => import('@/views/editessay/index.vue')
      }
    ]
  },

  {
    path: '/admin',
    component: Layout,
    children: [
      {
        path: 'admin',
        name: 'Admin',
        component: () => import('@/views/admin/index.vue'),
        meta: { title: '用户管理', icon: '用户' }
      }
    ]
  },
  {
    path: '/authority',
    component: Layout,
    children: [
      {
        path: 'authority',
        name: 'AuThority',
        component: () => import('@/views/authority/index.vue'),
        meta: { title: '权限管理', icon: '权限' }
      }
    ]
  },
  {
    path: '/message',
    component: Layout,
    children: [
      {
        path: 'message',
        name: 'Message',
        component: () => import('@/views/message/index.vue'),
        meta: { title: '留言管理', icon: '消息' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
