<template>
    <main class="main">
        <div class="friend" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
            <div class="wrapper">
                <div class="list">
                    <h1 :class="weather ? 'color-130c0e' : 'color-fffffb'">友情链接</h1>
                    <div class="friends_list" v-for="friend,index in friends" :key="index">
                        <a class="friend_item" @click="go(friend.friendAddress)">
                            <div class="avatar_wrapper">
                                <img :src="friend.friendImage" alt="">
                            </div>
                            <div class="info" :class="weather ? 'color-130c0e' : 'color-fffffb'">
                                <div class="name">
                                    {{ friend.friendName }}
                                </div>
                                <div class="autograph">
                                    {{ friend.friendSignature }}
                                </div>
                            </div>
                        </a>
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
        }
    },
    methods: {
        go(url){
            window.location.href="http://"+url;
        }
    },
    computed: {
        ...mapState({
            weather: (state) => state.header.weather,
            friends: (state) => state.friend.friends,
        }),
    },
    mounted() {
        this.$store.dispatch("getFriend");
    },
}
</script>

<style lang="less" scoped>
.main {
    min-height: 92vh;

    .friend {
        width: 70%;
        box-shadow: 0 0 10px 5px #5e5e5e4d;
        border-radius: 1rem;
        margin: 3rem auto;

        .wrapper {
            width: 100%;
            border-radius: 25px;

            .list {
                display: flex;
                flex-direction:row;
                justify-content: space-evenly;
                flex-wrap: wrap;
                margin: 3rem 2rem;
                h1{
                    width: 100%;
                    margin-top: 20px;
                    margin-bottom: 20px;
                }
            }
        }
    }
}
//友链列表
.friends_list{
    width: 33.3%;
    display: flex;
    margin-bottom: 20px;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    .friend_item{
        width: 100%;
        height: 100%;
        padding: 0 20px;
        flex-wrap: nowrap;
        margin: 20px 0px;
        display: flex;
        align-items: center;
        .avatar_wrapper{
            width: 4rem;
            height: 4rem;
            overflow: hidden;
            img{
                width: 100%;
                height: 100%;
                border-radius: 50%;
            }
        }
        .info{
            width: 60%;
            padding-left: 15px;
            text-align: left;
            .name{
                width: 100%;
                font-weight: 700;
                white-space: nowrap;
                overflow: hidden;
            }
            .autograph{
                width: 100%;
                white-space: nowrap;
                overflow: hidden;
            }
        }
    }
}
@media screen and (max-width: 320px) {
    .friends_list{
        width:100%
    }
}

@media screen and (min-width: 320px) {
    .friends_list{
        width:100%
    }
}

@media screen and (min-width: 360px) {
    .friends_list{
        width:100%
    }
}

@media screen and (min-width: 375px) {
    .friends_list{
        width:100%
    }
}

@media screen and (min-width: 384px) {
    .friends_list{
        width:100%
    }
}

@media screen and (min-width: 400px) {
    .friends_list{
        width:100%
    }
}

@media screen and (min-width: 414px) {
    .friends_list{
        width:100%
    }
}

@media screen and (min-width: 424px) {
    .friends_list{
        width:100%
    }
}

@media screen and (min-width: 480px) {
    .friends_list{
        width:100%
    }
}

@media screen and (min-width: 540px) {
    .friends_list{
        width:50%
    }
}

@media screen and (min-width: 720px) {
    .friends_list{
        width:50%
    }
}

@media screen and (min-width: 750px) {
    .friends_list{
        width:50%
    }
}

@media screen and (min-width: 810px) {
    .friends_list{
        width:50%
    }
}

@media screen and (min-width: 990px) {
    .friends_list{
        width:50%
    }
}
@media screen and (min-width: 1000px) {
    .friends_list{
        width:33.3%
    }
}

@media screen and (min-width: 1200px) {
    .friends_list{
        width:30%
    }
}
</style>