<template>
  <div id="app">
    <Header v-if="$route.path != '/home'" />
    <BackGround v-if="$route.path != '/home'" class="hidden-md-and-down" />
    <router-view />
    <Footer v-if="$route.path != '/home'" class="hidden-md-and-down" />
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import BackGround from "@/components/BackGround";
import { mapState } from "vuex";
export default {
  name: "App",
  components: {
    Header,
    Footer,
    BackGround
  },
  created() {
  },
};
</script>
<style lang="less" >
@color-130c0e: #1A1A1A;
@color-fffffb: #FFFFFF;

/* 配色通用样式 */
.color-fffffb {
  color: @color-fffffb;
}

.color-130c0e {
  color: @color-130c0e;
}

.backgroundcolor-fffffb {
  background-color: @color-fffffb;
}

.backgroundcolor-130c0e {
  background-color: @color-130c0e;
}

html {
  font-family: Avenir,Helvetica,Arial,sans-serif;
}

* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

body {
  min-height: 100vh;
  background: #0c192c;
  min-width: 320px;
  max-width: 2560px;
}

/** 清除a标签样式 **/
a {
  text-decoration: none;
}

/*正常的未被访问过的链接*/
a:link {
  text-decoration: none;
}

/*已经访问过的链接*/
a:visited {
  text-decoration: none;
}

/*鼠标划过(停留)的链接*/
a:hover {
  text-decoration: none;
}

/* 正在点击的链接*/
a:active {
  text-decoration: none;
}

a:hover {
  cursor: pointer
}
</style>
