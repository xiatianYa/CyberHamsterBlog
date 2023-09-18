<template>
  <header class="Header default_box_shadow" @mouseleave="Item = false">
    <el-row class="row" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
      <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="PC hidden-xs-only hidden-sm-only">
        <div class="AlNav" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
          <div class="title" :class="weather ? 'color-130c0e' : 'color-fffffb'">
            博客 - Blog
          </div>
          <div class="Directory">
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'">
              <span class="icon iconfont icon-home" @click="go('/home')">
                <i>首页</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="go('/main')">
              <span class="icon iconfont icon-detail">
                <i>博客</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="Item = true">
              <span class="GD icon iconfont icon-read">
                <i>归档</i>
                <span class="iconfont icon-down"></span>
                <!-- 归档菜单 -->
                <div class="list_item" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'"
                  v-show="Item" @mouseleave="Item = false">
                  <a class="astro-75AJHNZY" :class="weather ? 'color-130c0e' : 'color-fffffb'"
                    @click="goClassify(value.classifyId, value.classifyName)" v-for="value, index in classify"
                    :key="index">{{ value.classifyName }}</a>
                </div>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="go('/tags')">
              <span class="icon iconfont icon-tags">
                <i>标签</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="go('/friend')">
              <span class="icon iconfont icon-link">
                <i>友链</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="go('/message')">
              <span class="icon iconfont icon-xinxi">
                <i>留言</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="go('/about')">
              <span class="icon iconfont icon-user">
                <i>关于</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'">
              <span class="icon iconfont icon-search">
              </span>
            </a>
            <a @click="ChangeWeather" :class="weather ? 'color-130c0e' : 'color-fffffb'">
              <span class="icon iconfont iconfont" :class="weather
                ? 'icon-taiyangtianqi'
                : 'icon-yejianmoshishenyemoshiyueliang'
                ">
              </span>
            </a>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" class="hidden-xs-only hidden-sm-only">
        <div class="Nav">
          <div class="title" :class="weather ? 'color-130c0e' : 'color-fffffb'">博客 - Blog</div>
        </div>
      </el-col>
      <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3" class="Mobile hidden-md-only hidden-lg-only hidden-xl-only">
        <div class="MobileMenu" :class="MobileMenu ? 'openMobileMenu' : 'closeMobileMenu'">
          <div class="LeftMenu">
            <div class="title" :class="weather ? 'color-130c0e' : 'color-fffffb'">
              Mobile - Blog
            </div>
          </div>
          <div class="RightMenu" @click="openMobileMenu" :disable="isLock">
            <div class="line1" :class="weather ? 'backgroundcolor-130c0e' : 'backgroundcolor-fffffb'"></div>
            <div class="line2" :class="weather ? 'backgroundcolor-130c0e' : 'backgroundcolor-fffffb'">
              <div class="openline" :class="weather ? 'backgroundcolor-130c0e' : 'backgroundcolor-fffffb'"></div>
            </div>
            <div class="line3" :class="weather ? 'backgroundcolor-130c0e' : 'backgroundcolor-fffffb'"></div>
          </div>
          <div class="Drop_List animate__animated animate__fadeInDown" v-show="MobileMenu"
            :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'" style="animation-duration: 400ms">
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'">
              <span class="icon iconfont icon-home" @click="go('/home')">
                <i>首页</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'">
              <span class="icon iconfont icon-detail" @click="go('/main')">
                <i>博客</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="openMobileGD">
              <span class="icon iconfont icon-read">
                <i>归档</i>
                <span class="iconfont icon-down"></span>
                <!-- 归档菜单 -->
                <div class="list_item" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'"
                  v-show="Item">
                  <a class="astro-75AJHNZY" :class="weather ? 'color-130c0e' : 'color-fffffb'"
                    @click="goClassify(value.classifyId, value.classifyName)" v-for="value, index in classify"
                    :key="index">{{ value.classifyName }}</a>
                </div>
              </span>

            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="go('/tags')">
              <span class="icon iconfont  icon-tags">
                <i>标签</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="go('/friend')">
              <span class="icon iconfont icon-link">
                <i>友链</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="go('/message')">
              <span class="icon iconfont icon-xinxi">
                <i>留言</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="go('/about')">
              <span class="icon iconfont icon-user">
                <i>关于</i>
              </span>
            </a>
            <a :class="weather ? 'color-130c0e' : 'color-fffffb'">
              <span class="icon iconfont icon-search">
              </span>
            </a>
            <a @click.prevent="ChangeWeather" :class="weather ? 'color-130c0e' : 'color-fffffb'">
              <span class="icon iconfont iconfont" :class="weather
                ? 'icon-taiyangtianqi'
                : 'icon-yejianmoshishenyemoshiyueliang'
                ">
              </span>
            </a>
          </div>
        </div>
      </el-col>
    </el-row>
  </header>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      Item: false,
      MobileMenu: false,
      isLock: false
    };
  },
  components: {},
  methods: {
    go(path) {
      this.isLock = true
      this.MobileMenu = !this.MobileMenu
      setTimeout(() => {
        this.isLock = false
      }, 2000)
      this.$router.push(path)
    },
    Search() {
      // console.log("搜索");
    },
    ChangeWeather() {
      this.$store.dispatch("SetWeather");
    },
    openMobileMenu() {
      this.isLock = true
      this.MobileMenu = !this.MobileMenu
      setTimeout(() => {
        this.isLock = false
      }, 2000)
    },
    openMobileGD() {
      this.Item = !this.Item
    },
    goClassify(classifyId, classifyName) {
      this.isLock = true
      this.MobileMenu = !this.MobileMenu
      setTimeout(() => {
        this.isLock = false
      }, 2000)
      this.$router.push({
        path: '/classify',
        query: {
          classifyId: classifyId,
          classifyName: classifyName
        }
      })
    }
  },
  computed: {
    ...mapState({
      weather: (state) => state.header.weather,
      classify: (state) => state.header.classify,
    }),
  },
  mounted() {
    this.$store.dispatch("GetClassify");
  },
};
</script>
<style lang="less" scoped>
//旋转动画
@keyframes rotateLine2 {
  from {
    transform: rotate(0deg);
  }

  to {
    transform: rotate(45deg);
  }
}

@keyframes rotateOpenline {
  from {
    transform: rotate(0deg);
  }

  to {
    transform: rotate(-90deg);
  }
}

//移动端菜单Open
.openMobileMenu {
  .line1 {
    opacity: 0;
  }

  .line2 {
    margin-top: 10px;
    width: 23px;
    height: 4px;
    animation-name: rotateLine2;
    animation-duration: 0.5s;
    animation-fill-mode: forwards
  }

  .openline {
    margin-top: 10px;
    position: absolute;
    top: 0;
    bottom: 0;
    right: 0;
    bottom: 0;
    width: 23px;
    height: 4px;
    margin: auto;
    animation-name: rotateOpenline;
    animation-duration: 0.5s;
    animation-fill-mode: forwards
  }

  .line3 {
    opacity: 0;
  }
}

//移动端菜单Close
.closeMobileMenu {
  .line1 {
    margin-top: 3px;
    width: 100%;
    opacity: 1;
  }

  .line2 {
    margin-top: 3px;
    width: 100%;
    opacity: 1;
  }

  .line3 {
    margin-top: 3px;
    width: 100%;
    opacity: 1;
  }
}

.Header {
  width: 100%;
  min-height: 64px;
  min-width: 320px;

  &:hover .AlNav {
    transform: translateY(0%);
  }

  .row {
    width: 100%;
    z-index: 99;
    position: fixed;
    box-shadow: 0 0 10px 5px #5e5e5e4d;
  }
}

//背景阴影
.default_box_shadow {
  box-shadow: 0 2px 5px #00000029, 0 2px 10px #0000001f;
}

// 移动列表
.AlNav {
  width: 100%;
  height: 5vh;
  min-height: 64px;
  display: flex;
  position: absolute;
  transform: translateY(-100%);
  transition: all 0.3s ease 0s;

  .title {
    width: 35%;
    line-height: 64px;
    margin-left: 5%;
    font-size: 1.2rem;
    font-weight: 700;
    letter-spacing: 2px;
  }

  .Directory {
    display: flex;
    position: fixed;
    width: 45%;
    height: 64px;
    right: 0;

    >a:nth-child(4) {
      padding-left: 10px;
    }

    >a {
      display: block;
      line-height: 64px;
      padding-left: 2%;

      &:hover {
        color: #919edf;
      }

      .GD {
        position: relative;
        z-index: 999;

        .list_item {
          width: 150%;
          top: 41px;
          left: -30%;
          position: absolute;
          border-radius: 7px;
          text-align: center;
          box-shadow: 0 0 10px 5px #5e5e5e4d;

          .astro-75AJHNZY {
            &:hover {
              color: #919edf;
            }

            line-height: 25px;
            height: 25px;
            display: block;
            margin: 0;
            width: 100%;
            font-size: 14px;
          }
        }
      }

      .icon {
        width: 100%;
        font-size: 18px;

        &:hover .list_item {
          display: block;
        }
      }

      i {
        padding-left: 3px;
        font-size: 16px;
        font-style: normal;
      }
    }
  }
}

// 列表
.Nav {
  z-index: -99;
  height: 5vh;
  min-height: 64px;
  display: flex;

  .title {
    width: 100%;
    line-height: 64px;
    text-align: center;
    font-size: 1.2rem;
    font-weight: 700;
    letter-spacing: 2px;
  }
}

//移动端样式
.Mobile {
  width: 100%;
  height: 64px;
}

.MobileMenu {
  width: 100%;
  height: 5vh;
  min-height: 64px;
  min-width: 320px;
  display: flex;
  position: relative;

  height: 100%;
  width: 100%;

  .LeftMenu {
    width: 100%;
    height: 64px;

    .title {
      padding-left: 15%;
      line-height: 64px;
      text-align: center;
      font-size: 1.2rem;
      font-weight: 700;
    }
  }

  .RightMenu {
    line-height: 64px;
    right: 0;
    margin-top: 20px;
    margin-right: 7%;
    width: 23px;
    height: 23px;

    .line1 {
      height: 4px;
    }

    .line2 {
      height: 4px;
    }

    .line3 {
      height: 4px;
    }
  }

  .Drop_List {
    position: absolute;
    display: flex;
    width: 100%;
    top: 64px;
    flex-wrap: wrap;

    >a {
      font-size: 16px;
      display: flex;
      justify-content: center;
      margin-top: 20px;
      width: 100%;

      &:hover {
        color: #919edf;
      }

      .icon {
        font-size: 18px;

        .list_item {
          a {
            font-size: 16px;
            display: flex;
            justify-content: center;
            margin-top: 10px;
            width: 100%;

            &:hover {
              color: #919edf;
            }
          }
        }

        i {
          padding-left: 3px;
          font-size: 16px;
          font-style: normal;
        }
      }
    }
  }
}
</style>