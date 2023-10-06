<template>
  <div class="Home">
    <div class="home">
      <div class="index">
        <div class="slogan" id="slogan">
          <p class="slogan2" id="slogan2">{{ text+'|' }}</p>
        </div>
        <div class="buttons">
          <button class="button1" @click="go('/about')"><a >关于我</a></button>
          <button class="button2"  @click="go('/main')"><a >进入博客</a></button>
          <button class="button3">
            <a href="https://github.com/xiatianYa" target="_blank">GitHub</a>
          </button>
        </div>
      </div>
      <vue-particles
        class="animate"
        color="#fff"
        :particleOpacity="0.7"
        :particlesNumber="80"
        shapeType="circle"
        :particleSize="4"
        linesColor="#fff"
        :linesWidth="1"
        :lineLinked="true"
        :lineOpacity="0.4"
        :linesDistance="80"
        :moveSpeed="2"
        :hoverEffect="true"
        hoverMode="grab"
        :clickEffect="false"
        clickMode="bubble"
      >
      </vue-particles>
    </div>
    <div class="bg1 hidden-xs-only hidden-sm-only"></div>
    <div class="bg2 hidden-xs-only hidden-md-only hidden-lg-only hidden-xl-only"></div>
    <div class="bg3 hidden-sm-and-up"></div>
  </div>
</template>
  
  <script>
export default {
  name: "Home",
  data() {
    return {
      initialText: "我亦无他,唯手熟尔",
      index: 0,
      text: "",
    };
  },
  mounted() {
    setInterval(() => {
      this.autoTyping();
    }, 700);
  },
  methods: {
    autoTyping() {
      this.index++;
      this.text = this.initialText.slice(0, this.index);
      this.index =
        this.text.length === this.initialText.length ? 0 : this.index;
    },
    go(path){
      this.$router.push(path)
    },
  },
};
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.Home{
  max-height: 100vh;
}
.animate {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  position: absolute;
  z-index: -50;
  display: flex;
  justify-content: center;
  align-items: center;
}
.bg1 {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  min-width: 360px;
  background: url("./images/bg01.png") no-repeat center center;
  background-size: 100vw 100vh;
  position: absolute;
  z-index: -5;
}
.bg2 {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  min-width: 360px;
  background: url("./images/bg02.png") no-repeat center center;

  position: absolute;
  z-index: -5;
}
.bg3 {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  min-width: 360px;
  background: url("./images/bg03.png") no-repeat center center;
  background-size: 70vh;
  position: absolute;
  z-index: -5;
}
.home {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  min-height: 100vh;
  position: absolute;
  z-index: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
.index {
  min-width: 320px;
  transition: all 10s;
}
@keyframes rotate {
  0% {
    transform: rotate(0);
  }
  100% {
    transform: rotate(360deg);
  }
}
.index .name:hover {
  text-shadow: 0px 0px 30px backgroundcolor-130c0e;
}
.slogan {
  width: 300px;
  margin-top: 25px;
  border-radius: 30px;
}
.slogan .slogan2 {
  height: 50px;
  font-size: 26px;
  color: #fff;
  text-align: center;
  border-radius: 30px;
  line-height: 50px;
  letter-spacing: 3px;
}
.buttons {
  margin-top: 25px;
}
.buttons .button1 {
  width: 90px;
  height: 50px;
  border: 1px solid rgba(255, 255, 255, 0.4);
  border-radius: 25px;
  background-color: rgba(255, 255, 255, 0);
  color: backgroundcolor-130c0e;
  font-size: 18px;
}
.buttons .button2 {
  margin-left: 15px;
  height: 50px;
  width: 90px;
  border: 1px solid rgba(255, 255, 255, 0.4);
  border-radius: 25px;
  background-color: rgba(255, 255, 255, 0);
  color: backgroundcolor-130c0e;
  font-size: 18px;
}
.buttons .button3 {
  margin-left: 15px;
  width: 90px;
  height: 50px;
  border: 1px solid rgba(255, 255, 255, 0.4);
  border-radius: 25px;
  background-color: rgba(255, 255, 255, 0);
  color: backgroundcolor-130c0e;
  font-size: 18px;
}
.buttons a {
  text-decoration: none;
  color: #fff;
}
.buttons .button1:hover {
  background-color: rgb(64, 158, 255, 0.4);
  box-shadow: 0px 0px 25px #409eff;
}
.buttons .button2:hover {
  background-color: rgb(103, 194, 58, 0.4);
  box-shadow: 0px 0px 25px #67c23a;
}
.buttons .button3:hover {
  background-color: rgb(144, 147, 153, 0.6);
  box-shadow: 0px 0px 25px #909399;
}
</style>
  