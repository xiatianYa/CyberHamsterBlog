<template>
    <main class="main">
        <div class="list" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
            <div class="TagsPigeonhole">
                <div class="wrapper">
                    <h2 :class="weather ? 'color-130c0e' : 'color-fffffb'">标签
                        <span>{{this.$route.query.tagsName}}</span>
                        下的所有文章
                    </h2>
                    <div class="list_item">
                        <h3 :class="weather ? 'color-130c0e' : 'color-fffffb'">
                            共计 {{ blogs.length }} 篇文章
                        </h3>
                        <div class="infinite-list-wrapper">
                            <ul class="list" v-infinite-scroll="load" infinite-scroll-disabled="disabled">
                                <li v-for="blog, index in blogs" class="list-item" :key="index">
                                    <a  :class="weather ? 'color-130c0e' : 'color-fffffb'" @click="goEsSay(blog.blogId)">
                                        <span>{{blog.blogTitle}}</span>
                                        <div class="time">{{blog.createTime.substring(0,10)}}</div>
                                    </a>
                                </li>
                            </ul>
                            <p v-if="loading" :class="weather ? 'color-130c0e' : 'color-fffffb'">加载中...</p>
                            <p v-if="noMore" :class="weather ? 'color-130c0e' : 'color-fffffb'">没有更多了</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</template>

<script>
import { mapState } from "vuex";
export default {
    data() {
        return {
            count: 20,
            loading: false,
        }
    },
    methods: {
        load() {
            this.loading = true
            setTimeout(() => {
                this.count += 2
                this.loading = false
            }, 2000)
        },
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
            blogs: (state) => state.tagspigeonhole.blogs,
        }),
        noMore() {
            return this.count >= this.blogs.length
        },
        disabled() {
            return this.loading || this.noMore
        }
    },
    mounted(){
        this.$store.dispatch("getBlogsByTagsId", this.$route.query.tagsId);
    }
}
</script>

<style lang="less" scoped>
@keyframes fontcolor{
    to{

    }
    from{
        background-color: #5e5e5e4d;
        color: #2a5caa;
    }
}
.main {
    min-height: 92vh;

    >.list {
        margin: 3rem auto;
        border-radius: 1rem;
        box-shadow: 0 0 10px 5px #5e5e5e4d;
        width: 70%;
    }

    .TagsPigeonhole {
        display: flex;
    }
}

.wrapper {
    width: 100%;
    border-radius: 20px;

    .list_item {
        width: 90%;
        margin: 2rem auto;

        .infinite-list-wrapper {
            width: 100%;
            max-height: 55vh;
            overflow: auto;

            ul {
                margin-bottom: 1rem;
                li {
                    padding-left: 5%;
                    list-style: none;

                    a {
                        display: flex;
                        padding: .6rem 0;
                        width: 90%;

                        &:hover {
                            animation: fontcolor 1s forwards;
                        }

                        span {
                            width: 70%;
                        }

                        .time {
                            width: 30%;
                        }
                    }
                }
            }

            p {
                width: 90%;
                height: 40px;
                line-height: 40px;
                font-weight: 600;
                text-align: center;
            }
        }

        h3 {
            margin-bottom: 2%;
        }
    }

    h2 {
        margin-top: 1rem;
        width: 100%;
        text-align: center;

        span {
            text-decoration: underline;
        }
    }

}

@media screen and (max-width: 320px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 320px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 360px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 375px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 384px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 400px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 414px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 424px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 480px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 540px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 720px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 750px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 810px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 1000px) {
    .main >.list {
        width: 90%;
    }
}

@media screen and (min-width: 1200px) {
    .main >.list {
        width: 70%;
    }
}
</style>