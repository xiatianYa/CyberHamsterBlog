//引入路由组件
import Home from '@/pages/Home/index.vue'
import Main from '@/pages/Main/index.vue'
import Essay from '@/pages/EsSay/index.vue'
import Classify from '@/pages/Classify/index.vue'
import Tags from '@/pages/Tags/index.vue'
import Friend from '@/pages/Friend/index.vue'
import About from '@/pages/About/index.vue'
import TagsPigeonhole from '@/pages/TagsPigeonhole/index.vue'
import Message from '@/pages/Message/index.vue'
import Search from '@/pages/Search/index.vue'
//配置路由
export default [
    {
        path: "/home",
        component: Home
    },
    {
        path: "/main",
        component: Main
    },
    {
        path: '/essay',
        component: Essay
    },
    {
        path: '/classify',
        component: Classify
    },
    {
        path: '/tags',
        component: Tags
    },
    {
        path: '/friend',
        component: Friend
    },
    {
        path: '/about',
        component: About
    },  
    {
        path: '/tagspigeonhole',
        component: TagsPigeonhole
    },
    {
        path:'/message',
        component:Message
    },
    {
        path:'/search',
        component:Search
    },
    //重定向
    {
        path: '/',
        redirect: "/home"
    }
]