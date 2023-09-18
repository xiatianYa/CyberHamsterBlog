import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex)
//引入仓库
import home from './home'
import main from './main'
import header from './header'
import essay from './essay'
import tags from './tags'
import friend from './friend'
import classify from './classify'
import tagspigeonhole from './tagspigeonhole'
import message from './message'

export default new Vuex.Store({
    modules: {
        home,
        main,
        header,
        essay,
        tags,
        friend,
        classify,
        tagspigeonhole,
        message
    }
})