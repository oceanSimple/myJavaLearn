<template>
  <div id="background">
    <div id="form">
      <div id="title">注册</div>
      <div id="input">
        <div class="check">{{check.usercode}}</div>
        <div style="display: flex">
          <span style="width: 20%">账号</span>
          <el-input  placeholder="请输入账号" v-model="usercode" @blur="checkUsercode"></el-input>
        </div>

        <div class="check">{{check.userpsw}}</div>
        <div style="display: flex">
          <span style="width: 20%">密码</span>
          <el-input placeholder="请输入密码" v-model="userpsw" show-password @blur="checkUserpsw"></el-input>
        </div>

        <div class="check">{{check.checkPsw}}</div>
        <div style="display: flex">
          <span style="width: 20%">确认</span>
          <el-input placeholder="请再次输入密码" v-model="checkPsw" show-password @blur="checkCheckpsw"></el-input>
        </div>

        <div class="check">{{check.nickname}}</div>
        <div style="display: flex">
          <span style="width: 20%">昵称</span>
          <el-input  placeholder="请输入昵称" v-model="nickname"></el-input>
        </div>
      </div>
      <el-button type="info" plain style="margin-left: 40%;margin-top: 25px" @click="register">注册</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "RegisterDemo",
  data() {
    return {
      usercode: '',
      userpsw: '',
      checkPsw: '',
      nickname: '',
      check: {
        usercode: '',
        userpsw: '',
        checkPsw: '',
      }
    }
  },
  methods: {
    register() {
      let data = {
        usercode: this.usercode,
        userpsw: this.userpsw,
        nickname: this.nickname
      };
      if (this.canRegister()) {
        this.$axios.post(this.$store.state.url + '/userInfo/register',data).then(
            res => {
              if (res.data.code === 1) {
                alert("注册成功");
                this.$router.push({
                  name: 'login'
                })
              }
              else {
                // 注册失败，usercode已存在
                alert(res.data.msg);
                this.usercode = '';
              }
            },
            error => {
              console.log(error.message);
            }
        )
      }
    },

    checkUsercode() {
      let regex = /^\d{10,10}$/;
      let flag = regex.test(this.usercode);
      if (flag)
        this.check.usercode = '';
      else
        this.check.usercode = '账号应为十位数字';
      return flag;
    },
    checkUserpsw() {
      let regex = /^\w{6,30}$/;
      let flag = regex.test(this.userpsw);
      if (flag)
        this.check.userpsw= '';
      else
        this.check.userpsw = '密码应为6-30位数字或字母';
      return flag;
    },
    checkCheckpsw() {
      let flag = (this.userpsw === this.checkPsw)
      if (flag)
        this.check.checkPsw= '';
      else
        this.check.checkPsw = '两次输入的密码应该相同';
      return flag;
    },
    canRegister() {
      return this.checkUsercode() && this.checkUserpsw() && this.checkCheckpsw();
    }
  }
}
</script>

<style scoped>
#background {
  background: url("@/assets/background/registerBackground.png") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

#background > div >div {
  /*border: red 1px solid;*/
}

#form {
  width: 30%;
  height: 500px;
  margin-left: 65%;
  margin-top: 50px;
  background:rgba(250, 240, 250, 0.7);
  border-radius: 10%;
}

#title {
  text-align: center;
  font-family: '华文行楷';
  font-size: 50px;
}

span {
  text-align: center;
  font-family: '华文行楷';
  font-size: 25px;
  line-height: 40px;
}

.check {
  height: 20px;
  margin-left: 20%;
  color: red;
}
</style>