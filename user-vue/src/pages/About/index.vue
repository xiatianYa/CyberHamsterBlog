<template>
    <main class="main">
        <div class="about" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
            <MdEditor class="MdEditor" :content.sync="userInfo.introduce"></MdEditor>
        </div>
    </main>
</template>

<script>
import { reqGetUserInfo } from "@/api";
import { mapState } from "vuex";
export default {
    data() {
        return {
            userInfo: {}
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
        }
    },
    computed: {
        ...mapState({
            weather: (state) => state.header.weather,
        }),
    },
    components: {
    },
}
</script>

<style lang="less" scoped>
.main {
    width: 100%;
    margin-bottom: 3rem;
    min-height: 92vh;

    .about {
        width: 70%;
        height: 100%;
        margin: 3rem auto;
        padding: 20px;
        box-shadow: 0 0 10px 5px #5e5e5e4d;
        border-radius: 1rem;
        justify-content: center;
        overflow: hidden;
        display: flex;
        z-index: -99;

        .MdEditor {
            width: 100%;
        }
    }
}
</style>