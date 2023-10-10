<template>
    <footer class="footer" :class="weather ? 'backgroundcolor-fffffb' : 'backgroundcolor-130c0e'">
        <div  :class="weather ? 'color-130c0e' : 'color-fffffb'">
            Copyright © 2023 - 2024 Summer
        </div>
        <div  :class="weather ? 'color-130c0e' : 'color-fffffb'">
            Powered by SpringBoot & Vue
        </div>
        <a href="https://beian.miit.gov.cn/" target="_blank"  :class="weather ? 'color-130c0e' : 'color-fffffb'">湘ICP备2023024567号</a>
    </footer>
</template>

<script>
import { mapState } from "vuex";
export default {
    computed: {
    ...mapState({
      weather: (state) => state.header.weather,
    }),
  },
}
</script>

<style lang="less">
.footer {
    width: 100%;
    display: flex;
    justify-content: center;
    flex-direction: column;
    flex-wrap: wrap;
    align-items: center;
    box-shadow: 0 0 10px 5px #5e5e5e4d;
    margin-bottom: 0;
    min-height: 10vh;
    div{
        margin-top: 8px;
    }
    a{
        margin-top: 8px;
        text-decoration: none;
        &:hover {
        color: #919edf;
      }
    }
}
</style>