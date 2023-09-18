<template>
  <main class="main">
    <div class="EsSay">
      <div class="Blog_title" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
        <div class="white_modle" :class="weather ? 'color-130c0e' : 'color-fffffb'">
          <div class="wrapper">
            <div class="title">
              <p>
                {{ blog.blogTitle }}
              </p>
            </div>
            <div class="time">
              <span class="icon iconfont icon-rili">
                <i>{{ blog.createTime }}</i>
              </span>
            </div>
            <div class="categories">
              <span class="icon iconfont icon-tags" v-for="tag, index in blog.tbTagsEntityList" :key="index">
                <i>{{ tag.tagsName }}</i>
              </span>
            </div>
          </div>
        </div>
      </div>
      <div class="Blog_content">
        <div class="content">
          <MdEditor :content.sync="blog.blogContent"></MdEditor>
        </div>
      </div>
      <div class="Blog_info">
        <div class="module_wrapper">
          <div class="white_module" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
            <div class="module_title" :class="weather ? 'color-130c0e' : 'color-fffffb'">
              个人信息
            </div>
            <div class="wrapper">
              <div class="personal_info">
                <div class="img_wrapper">
                  <img :src="userInfo.avatar">
                </div>
                <div class="info_wrapper" :class="weather ? 'color-130c0e' : 'color-fffffb'">
                  <div class="name">{{ userInfo.name }}</div>
                  <div class="status">
                    <div class="status_item">
                      <div class="title">文章</div>
                      <div class="count">{{ countList.blogCount }}</div>
                    </div>
                    <div class="status_item">
                      <div class="title">标签</div>
                      <div class="count">{{ countList.classifyCount }}</div>
                    </div>
                    <div class="status_item">
                      <div class="title">归档</div>
                      <div class="count">{{ countList.tagsCount }}</div>
                    </div>
                  </div>
                  <div class="scoial">
                    <a href="https://github.com/xiatianYa" :class="weather ? 'color-130c0e' : 'color-fffffb'">
                      <span class="icon iconfont icon-github-fill">
                        <i>GitHub</i>
                      </span>
                    </a>
                    <a href="https://gitee.com/xiatianYa1003" :class="weather ? 'color-130c0e' : 'color-fffffb'">
                      <span class="icon iconfont icon-gitee1">
                        <i>Gitee</i>
                      </span>
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="module_wrapper">
          <div class="white_module" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
            <div class="module_title" :class="weather ? 'color-130c0e' : 'color-fffffb'">
              近期文章
            </div>
            <div class="wrapper">
              <div class="items">
                <a class="item" :class="weather ? 'color-130c0e' : 'color-fffffb'" v-for="blog in BlogList.slice(0, 3)"
                  :key="blog.blogId" @click="goEsSay(blog.blogId)">
                  <div class="title">{{ blog.blogTitle }}</div>
                  <div class="time">{{ blog.createTime }}</div>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import { reqGetUserInfo } from "@/api";
import { mapState } from "vuex";
export default {
  data() {
    return {
      userInfo:{}
    }
  },
  watch: {
    $route(to, from) {
      this.$store.dispatch("getBlogById", this.$route.query.blogId);
    }
  },
  created() {
    this.reqGetUserInfo()
  },
  methods: {
    //获取用户信息
    async reqGetUserInfo() {
      const result = await reqGetUserInfo()
      if (result.code == 20000) {
        this.userInfo = result.data
      }
    },
    //跳转至文章页面
    goEsSay(blogId) {
      this.$router.push({
        path: '/essay',
        query: {
          blogId: blogId
        }
      })
    }
  },
  computed: {
    ...mapState({
      weather: (state) => state.header.weather,
      //当前文章信息
      blog: (state) => state.essay.blog,
      //博客分类数量
      countList: (state) => state.essay.countList,
      //近期文章
      BlogList: (state) => state.essay.BlogList,
    }),
  },
  mounted() {
    this.$store.dispatch("getBlogById", this.$route.query.blogId);
    this.$store.dispatch("getCount");
    this.$store.dispatch("getEsSayBlog", { 'pageNum': 0, 'pageSize': 3 });
  },
};
</script>

<style lang="less" scoped>
.main {
  width: 100%;
  min-height: 93vh;
  display: flex;
  justify-content: center;

  .EsSay {
    display: flex;
    flex-wrap: wrap;

    // 文章头部
    .Blog_title {
      width: 100%;
      display: flex;
      justify-content: center;
      margin-top: 3%;
      box-shadow: 0 0 10px 5px #5e5e5e4d;
      border-radius: 20px;


      .white_modle {
        width: 90%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        min-height: 20vh;

        .wrapper {
          display: flex;
          justify-content: center;
          flex-wrap: wrap;
          width: 100%;
          height: 80%;
          line-height: 100%;

          .title {
            display: flex;
            align-items: center;
            font-size: 24px;
            font-weight: 700;

            p {
              width: 100%;
              text-align: center;
            }
          }

          .time {
            width: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;

            .icon {
              font-size: 16px;

              i {
                padding-left: 5px;
                font-style: normal;
              }
            }
          }

          .categories {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            justify-content: center;

            .icon {
              margin-left: 5px;
              font-size: 16px;


              i {
                padding-left: 5px;
                font-style: normal;
              }
            }
          }
        }
      }
    }

    //文章内容
    .Blog_content {
      width: 100%;
      margin-top: 2%;
      margin-bottom: 2%;
      min-height: 55vh;
      justify-content: center;
      overflow: hidden;
      display: flex;

      .content {
        width: 100%;
      }
    }

    .Blog_info {
      width: 100%;
      justify-content: space-between;
      margin-bottom: 2%;
      display: flex;
      flex-wrap: wrap;

      .module_wrapper {
        margin-bottom: 20px;

        .white_module {
          display: flex;
          flex-flow: column nowrap;
          width: 100%;
          height: 200px;
          padding: 20px;
          box-shadow: 0 2px 5px #0003, 0 2px 10px #0003;
          border-radius: 20px;

          .module_title {
            height: 15%;
            margin-bottom: .3rem;
            text-align: center;
          }

          .wrapper {
            flex: 1;
            height: 85%;

            .personal_info {
              display: flex;
              height: 100%;

              .img_wrapper {
                width: 20;
                padding: 0 1rem;

                img {
                  max-width: 130px;
                  max-height: 130px;
                  width: 100%;
                  height: 100%;
                  border-radius: 50%;
                }
              }

              .info_wrapper {
                flex: 1;
                display: flex;
                flex-flow: column nowrap;
                justify-content: space-between;
                gap: .8rem;
                padding-top: 1rem;

                .name {
                  font-size: 1.2rem;
                  font-weight: 700;
                }

                .status {
                  display: flex;
                  justify-content: space-between;
                  align-items: center;
                  width: 100%;

                  .status_item {
                    text-align: center;
                  }
                }

                .scoial {
                  font-size: 22px;

                  a:last-child {
                    padding-left: 4%;
                  }

                  .icon {
                    &:hover {
                      color: #919edf;
                    }

                    font-size: 20px;

                    i {
                      padding-left: 3px;
                      font-size: 16px;
                      font-style: normal;
                    }
                  }
                }
              }
            }

            .items {
              flex: 1;
              flex-flow: column nowrap;
              height: 100%;

              .item {
                display: flex;
                flex-flow: column nowrap;
                justify-content: space-between;
                height: 33.33%;
                padding: .4rem;
                transition: .1s;

                &:hover {
                  color: #919edf;
                }

                .title {}

                .time {
                  font-size: 14px;
                }
              }
            }
          }
        }
      }
    }
  }
}

@media screen and (max-width: 320px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 320px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 360px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 375px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 384px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 400px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 414px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 424px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 480px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 540px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 720px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 750px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 810px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 990px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 100%;
    }
  }
}

@media screen and (min-width: 1000px) {
  .EsSay {
    width: 90%;
  }

  .Blog_info {
    .module_wrapper {
      width: 49%;
    }
  }
}

@media screen and (min-width: 1200px) {
  .EsSay {
    width: 70%;
  }

  .Blog_info {
    .module_wrapper {
      width: 49%;
    }
  }
}
</style>