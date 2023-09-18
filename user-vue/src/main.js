import Vue from 'vue'
import App from './App.vue'
import VueParticles from 'vue-particles'
//引入图标icon
import '@/assets/icon/iconfont.css'
//引入element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/display.css';
import 'element-ui/lib/theme-chalk/index.css';
//引入路由
import router from '@/router'
//引入仓库
import store from '@/store'
/* v-md-editor 编辑器 start  */
/* 1、v-md-editor 基础引用  */
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import Prism from 'prismjs';
VueMarkdownEditor.use(vuepressTheme, {
  Prism,
});

/* 2、v-md-editor 代码块关键字高亮  */
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
// 引入所有语言包
import hljs from 'highlight.js';
VueMarkdownEditor.use(githubTheme, {
  Hljs: hljs,
});
Vue.use(VueMarkdownEditor);

/* 3、v-md-editor 二次封装  */
import mdEditor from '@/components/v-md-editor/index.vue';
Vue.component('MdEditor', mdEditor);
/* v-md-editor 编辑器 end  */
//使用element-ui
Vue.use(ElementUI);
//使用背景动画
Vue.use(VueParticles)
Vue.config.productionTip = false

new Vue({
    render: h => h(App),
    beforeCreate() {
        Vue.prototype.$bus = this;
    },
    //注册路由
    router,
    store
}).$mount('#app')
