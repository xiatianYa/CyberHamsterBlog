<template>
    <main class="main">
        <div class="tags" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
            <div class="wrapper">
                <div class="list">
                    <a  v-for="value, index in tags" :key="index" @click="goClassify(value.tagsId,value.tagsName)">
                        {{ value.tagsName }}
                    </a>
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
        }
    },
    methods: {
        goClassify(tagsId,tagsName) {
            this.$router.push({
                path: '/tagspigeonhole',
                query: {
                    tagsId: tagsId,
                    tagsName:tagsName
                }
            })
        },
    },
    computed: {
        ...mapState({
            weather: (state) => state.header.weather,
            tags: (state) => state.tags.tags,
        }),
    },
    mounted() {
        this.$store.dispatch("getTags");
    },
}
</script>

<style lang="less" scoped>
.main {
    min-height: 92vh;

    .tags {
        width: 70%;
        display: flex;
        box-shadow: 0 0 10px 5px #5e5e5e4d;
        border-radius: 1rem;
        justify-content: center;
        margin: 3rem auto;

        .wrapper {
            border-radius: 25px;

            .list {
                display: flex;
                flex-direction: row;
                justify-content: center;
                flex-wrap: wrap;
                margin: 3rem 3rem;
            }

            a {
                color: #919edf;
                font-size: 18px;
                margin-bottom: 3rem;
                padding: 0 1rem;

                &:hover {
                    color: #3F5EFB;
                }
            }
        }
    }
}

@media screen and (max-width: 320px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 320px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 360px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 375px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 384px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 400px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 414px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 424px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 480px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 540px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 720px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 750px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 810px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 1000px) {
    .main .tags {
        width: 90%;
    }
}

@media screen and (min-width: 1200px) {
    .main .tags {
        width: 70%;
    }
}
</style>