<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm">
      <div class="content">
        <div class="login-wrapper">
          <div class="left-img">
            <div class="glass">
              <div class="tips">
                <div class="title">
                  赛 博 仓 鼠
                </div>
                <h1>探 索 宇 宙</h1>
                <span>什 么 臭 鱼 烂 虾.</span>
                <span>我 亦 无 他 , 唯 手 熟 尔!</span>
              </div>
            </div>
          </div>
          <div class="right-login-form">
            <div class="form-wrapper">
              <h1>Log in</h1>
              <div class="input-items">
                        <span class="input-tips" v-model="loginForm.username">
                            账 号
                        </span>
                <input type="text" class="inputs" placeholder="请输入账号" v-model="loginForm.username">
              </div>
              <div class="input-items">
                        <span class="input-tips">
                            密 码
                        </span>
                <input type="password" class="inputs" placeholder="请输入密码" v-model="loginForm.password">
                <span class="forgot">忘记密码</span>
              </div>
              <el-button class="btn" type="primary"
              @click.native.prevent="handleLogin">Login</el-button>
              <div class="siginup-tips">
                <span>没有账号 ? </span>
                <span> 注册</span>
              </div>
              <div class="other-login">
                <div class="divider">
                  <span class="line"></span>
                  <span class="divider-text">or</span>
                  <span class="line"></span>
                </div>
                <div class="other-login-wrapper">
                  <div class="other-login-item">
                    <img src="https://edu-9556.oss-cn-hangzhou.aliyuncs.com/2023/10/04/QQ.png" alt="QQ">
                  </div>
                  <div class="other-login-item">
                    <img src="https://edu-9556.oss-cn-hangzhou.aliyuncs.com/2023/10/04/WeChat.png" alt="WeChat">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-form>
  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'

export default {
  name: 'Login',
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
    }
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || '/' })
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>
<style scoped>
* {
  padding: 0;
  margin: 0;
  font-family: "微软雅黑", Courier, monospace;
}

.content {
  width: 100vw;
  height: 100vh;
  background-color: rgb(186, 175, 221);
  position: relative;
}
.content .login-wrapper {
  width: 70vw;
  height: 80vh;
  background-color: #fff;
  border-radius: 40px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  display: flex;
}
.content .login-wrapper .left-img {
  border-radius: 40px;
  flex: 1;
  background: url('https://edu-9556.oss-cn-hangzhou.aliyuncs.com/2023/10/04/bg.jpg') no-repeat;
  background-size: cover;
  position: relative;
}
.content .login-wrapper .left-img .glass {
  width: 60%;
  padding: 20px;
  color: #fff;
  position: absolute;
  top: 20%;
  left: 50%;
  transform: translate(-50%, -20%);
  background: rgba(255, 255, 255, 0.1);
  box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
  border-radius: 15px;
  border: 1px solid rgba(255, 255, 255, 0.48);
}
.content .login-wrapper .left-img .glass .tips .title {
  width: 35%;
  font-weight: 600;
  font-size: 15px;
  text-align: center;
  padding: 10px;
  overflow: hidden;
  background: rgba(255, 255, 255, 0.1);
  box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
  border-radius: 15px;
  border: 1px solid rgba(255, 255, 255, 0.48);
}
.content .login-wrapper .left-img .glass .tips h1 {
  margin: 15px 0;
}
.content .login-wrapper .left-img .glass .tips span {
  margin: 5px 0;
  display: block;
  font-weight: 100;
}
.content .login-wrapper .right-login-form {
  flex: 1;
  position: relative;
}
.content .login-wrapper .right-login-form .form-wrapper {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.content .login-wrapper .right-login-form .form-wrapper h1 {
  font-size: 30px;
  text-align: center;
}
.content .login-wrapper .right-login-form .form-wrapper .input-items {
  margin: 20px 0;
}
.content .login-wrapper .right-login-form .form-wrapper .input-items .input-tips {
  display: inline-block;
  font-weight: 600;
  font-size: 20px;
  margin: 10px 0;
}
.content .login-wrapper .right-login-form .form-wrapper .input-items .inputs {
  width: 100%;
  height: 50px;
  border-radius: 10px;
  border: 1px solid rgb(227, 227, 227);
  padding: 20px;
  box-sizing: border-box;
  outline: none;
  transition: 0.5s;
}
.content .login-wrapper .right-login-form .form-wrapper .input-items .inputs:focus {
  border: 1px solid rgb(128, 106, 196);
}
.content .login-wrapper .right-login-form .form-wrapper .input-items .forgot {
  float: right;
  font-weight: 600;
  text-align: right;
  margin: 5px 0;
  font-size: 13px;
  cursor: pointer;
}
.content .login-wrapper .right-login-form .form-wrapper .btn {
  width: 100%;
  height: 50px;
  background-color: rgb(128, 106, 196);
  border: 0;
  border-radius: 10px;
  color: #fff;
  font-size: 25px;
  margin: 20px 0;
  cursor: pointer;
}
.content .login-wrapper .right-login-form .form-wrapper .siginup-tips {
  text-align: center;
  font-weight: 600;
  margin: 10px 0;
}
.content .login-wrapper .right-login-form .form-wrapper .siginup-tips span:last-child {
  color: rgb(110, 87, 179);
  cursor: pointer;
}
.content .login-wrapper .right-login-form .form-wrapper .other-login .divider {
  width: 100%;
  margin: 20px 0;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.content .login-wrapper .right-login-form .form-wrapper .other-login .divider .line {
  display: inline-block;
  max-width: 35%;
  width: 35%;
  flex: 1;
  height: 1px;
  background-color: rgb(162, 172, 185);
}
.content .login-wrapper .right-login-form .form-wrapper .other-login .divider .divider-text {
  vertical-align: middle;
  margin: 0 20px;
  display: inline-block;
  width: 50px;
  color: rgb(162, 172, 185);
  white-space: normal;
}
.content .login-wrapper .right-login-form .form-wrapper .other-login .other-login-wrapper {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.content .login-wrapper .right-login-form .form-wrapper .other-login .other-login-wrapper .other-login-item {
  width: 70px;
  padding: 10px;
  text-align: center;
  border-radius: 10px;
  cursor: pointer;
  font-weight: 600;
  color: rgb(51, 49, 116);
  margin: 0 10px;
  transition: 0.4s;
}
.content .login-wrapper .right-login-form .form-wrapper .other-login .other-login-wrapper .other-login-item img {
  width: 40px;
  height: 40px;
  vertical-align: middle;
}
.content .login-wrapper .right-login-form .form-wrapper .other-login .other-login-wrapper .other-login-item span {
  vertical-align: middle;
}
.content .login-wrapper .right-login-form .form-wrapper .other-login .other-login-wrapper .other-login-item:hover {
  width: 80px;
  height: 50%;
  background-color: #fff;
  border: 0;
  box-shadow: 1px 10px 32px 1px rgba(186, 175, 221, 0.37);
}

@media (max-width: 1024px) {
  .login-wrapper {
    padding: 20px;
  }
  .login-wrapper .left-img {
    display: none;
  }
  .login-wrapper .right-login-form {
    padding: 20px;
  }
}/*# sourceMappingURL=style.css.map */
</style>
