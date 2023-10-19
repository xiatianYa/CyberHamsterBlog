<template>
  <main class="main">
    <div class="blog">
      <div class="wrapper">
        <div class="left_list" :class="weather ? 'color-130c0e' : 'color-fffffb'">
          <ul class="list" v-infinite-scroll="load" infinite-scroll-disabled="disabled">
            <li v-for="value, index in blog" class="list-item" :key="index">
              <div class="item" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
                <div class="info">
                  <a class="title" :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="goEsSay(value.blogId)">{{
                    value.blogTitle }}</a>
                  <div class="class_info">
                    <div class="time">
                      <a :class="weather ? 'color-130c0e' : 'color-fffffb'">
                        <span class="icon iconfont icon-calendar">
                        </span>
                        <p>{{ value.createTime }}</p>
                      </a>
                    </div>
                    <div class="tags">
                      <a :class="weather ? 'color-130c0e' : 'color-fffffb'" v-for="value, index in value.tbTagsEntityList"
                        :key="index">
                        <span class="icon iconfont icon-tags">
                        </span>
                        <p>{{ value.tagsName }}</p>
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </li>
          </ul>
          <p v-if="loading" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">加载中...</p>
          <p v-if="noMore" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">没有更多了</p>
        </div>
        <div class="right_info hidden-md-and-down">
          <div class="Blog_info" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
            <div class="module_wrapper" :class="weather ? 'color-130c0e' : 'color-fffffb'">
              <div class="white_module">
                <div class="module_title">个人信息</div>
                <div class="wrapper">
                  <div class="personal_info">
                    <div class="img_wrapper">
                      <img :src="userInfo.avatar" />
                    </div>
                    <div class="info_wrapper">
                      <div class="name">{{ userInfo.name }}</div>
                      <div class="status">
                        <div class="status_item">
                          <div class="title">文章</div>
                          <div class="count">{{ blogCount.blogCount }}</div>
                        </div>
                        <div class="status_item">
                          <div class="title">标签</div>
                          <div class="count">{{ blogCount.tagsCount }}</div>
                        </div>
                        <div class="status_item">
                          <div class="title">归档</div>
                          <div class="count">{{ blogCount.classifyCount }}</div>
                        </div>
                      </div>
                      <div class="social">
                        <a href="https://github.com/xiatianYa" target="_blank" class="GitHub"
                          :class="weather ? 'color-130c0e' : 'color-fffffb'">
                          <span class="icon iconfont icon-github-fill">
                          </span>
                        </a>
                        <a href="https://gitee.com/xiatianYa1003" target="_blank" class="Gitee"
                          :class="weather ? 'color-130c0e' : 'color-fffffb'">
                          <span class="icon iconfont icon-gitee1">
                          </span>
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="Recent_info" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
            <div class="module_wrapper" :class="weather ? 'color-130c0e' : 'color-fffffb'">
              <div class="white_module">
                <div class="module_title">
                  近期文章
                </div>
                <div class="items">
                  <a class="item" v-for="(item, index) in blog.slice(0, 5)" @click="goEsSay(item.blogId)">
                    <div class="title">{{ item.blogTitle }}</div>
                    <div class="time">{{ item.createTime }}</div>
                  </a>
                </div>
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
      pageNum: 0,
      pageSize: 30,
      loading: false,
      userInfo: {}
    };
  },
  mounted() {
    this.$store.dispatch("getBlog", { 'pageNum': this.pageNum, 'pageSize': this.pageSize });
    this.$store.dispatch("getCount");
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
    //文章加载定时器
    load() {
      this.loading = true
      setTimeout(() => {
        this.pageNum = this.pageSize;
        this.pageSize += 5
        this.$store.dispatch("getBlog", { 'pageNum': this.pageNum, 'pageSize': this.pageSize });
        this.loading = false
      }, 2000)
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
      blog: (state) => state.main.blog,
      blogCount: (state) => state.main.count,
    }),
    noMore() {
      return this.pageNum >= this.blogCount.blogCount
    },
    disabled() {
      return this.loading || this.noMore
    }
  },
};
</script>

<style lang="less" scoped>
.main {
  min-height: 92vh;
  height: 100%;

  .blog {
    padding: 20px;
    justify-content: center;

    .wrapper {
      position: relative;
      display: flex;
      flex-flow: row wrap;
      justify-content: space-between;
      margin: 0 auto;

      .left_list {
        overflow: auto;

        .list {
          list-style: none;
        }

        .item {
          display: flex;
          margin-bottom: 1.4rem;
          padding: 0;
          border-radius: 0.6rem;
          box-shadow: 0 0 10px 5px #5e5e5e4d;
          transition: box-shadow 0.3s;
          margin: 10px 15px 10px 15px;

          .info {
            display: flex;
            flex-flow: column nowrap;
            justify-content: space-between;
            width: 100%;
            min-height: 7rem;
            padding: 0.8rem 1.2rem;

            .title {
              font-size: 22px;
              font-weight: 700;
              padding-top: 2px;
            }

            .class_info {
              .time {
                padding-bottom: 0.5%;
                font-size: 16px;

                a {
                  .icon {
                    font-size: 20px;
                  }

                  p {
                    padding-left: 4px;
                    display: inline;
                  }

                  &:hover {
                    color: #919edf;
                  }
                }
              }

              .tags {
                display: flex;
                flex-wrap: wrap;
                font-size: 16px;

                a:nth-child(1) {
                  padding-left: 0px;
                }

                a {
                  padding-left: 6px;

                  .icon {
                    font-size: 20px;
                  }

                  p {
                    padding-left: 3px;
                    display: inline;
                  }

                  &:hover {
                    color: #919edf;
                  }
                }
              }
            }
          }
        }

        >p {
          margin: 0px 15px 10px 15px;
          border-radius: 0.6rem;
          box-shadow: 0 0 10px 5px #5e5e5e4d;
          text-align: center;
          font-size: 17px;
          font-weight: 700;
        }
      }

      .right_info {
        width: 26%;
        margin-left: 2%;

        .Blog_info {
          display: flex;
          flex-flow: column wrap;
          justify-content: center;
          align-items: center;
          align-content: center;
          width: 100%;
          border-radius: 1rem;
          box-shadow: 0 0 10px 5px #5e5e5e4d;
          box-shadow: 0 2px 5px #0003, 0 2px 10px #0003;
          box-shadow: 0 0 10px 5px #5e5e5e4d;

          .white_module {
            display: flex;
            flex-flow: column nowrap;

            .module_title {
              height: 15%;
              margin-top: 0.3rem;
              margin-bottom: 0.3rem;
              text-align: center;
            }

            .wrapper {
              flex: 1;
              height: 85%;
              width: 100%;
              margin: 0;

              .personal_info {
                flex-flow: column nowrap;
                text-align: center;
                width: 100%;
                display: flex;
                height: 100%;

                .img_wrapper {
                  padding: 0 1rem;

                  img {
                    max-width: 155px;
                    max-height: 155px;
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
                  padding: 1 1rem 0 1rem;

                  .name {
                    font-size: 1.2rem;
                    font-weight: 700;
                  }

                  .status {
                    margin-left: 5%;
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    width: 90%;
                  }

                  .social {
                    display: flex;
                    justify-content: center;
                    margin: 10px auto;

                    .GitHub {
                      padding-left: -3%;

                      .icon {
                        font-size: 42px;

                        &:hover {
                          color: #919edf;
                        }
                      }
                    }

                    .Gitee {
                      width: 42px;
                      line-height: 45.6px;
                      height: 45.6px;
                      padding-left: 30%;

                      .icon {
                        box-sizing: content-box;
                        font-size: 38px;

                        &:hover {
                          color: #919edf;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }

        .Recent_info {
          margin-top: 10px;
          display: flex;
          flex-flow: column wrap;
          justify-content: center;
          align-items: center;
          align-content: center;
          width: 100%;
          border-radius: 1rem;
          box-shadow: 0 0 10px 5px #5e5e5e4d;
          box-shadow: 0 2px 5px #0003, 0 2px 10px #0003;
          box-shadow: 0 0 10px 5px #5e5e5e4d;

          .module_wrapper {
            width: 100%;

            .white_module {

              .module_title {
                height: 15%;
                margin-top: 0.3rem;
                margin-bottom: 0.3rem;
                text-align: center;
              }

              .items {
                width: 100%;
                height: 100%;
                padding-bottom: 10px;

                .item {
                  display: flex;
                  flex-flow: column nowrap;
                  justify-content: space-between;
                  height: 33.33%;
                  padding-left: 1rem;
                  padding-top: 0.2rem;
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
}

@media screen and (max-width: 320px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 320px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 360px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 375px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 384px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 400px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 414px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 424px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 480px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 540px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 720px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 750px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 810px) {
  .main .blog .wrapper {
    width: 90%;
  }

  .left_list {
    width: 100%;
    height: 90vh;
  }
}

@media screen and (min-width: 1000px) {
  .main .blog .wrapper {
    width: 90%;

    .left_list {
      width: 100%;
      height: 90vh;
    }
  }
}

@media screen and (min-width: 1200px) {
  .main .blog .wrapper {
    width: 55%;

    .left_list {
      width: 72%;
      height: 85vh;
    }
  }
}
</style>