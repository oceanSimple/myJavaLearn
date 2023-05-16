<template>
  <div id="container">
    <div id="aside" class="left">
      <el-row class="tac">
        <el-col :span="24">
          <el-menu
              default-active="1"
              class="el-menu-vertical-demo">
            <el-menu-item index="1" @click="routerJump('selectFood')">
              <i class="el-icon-menu"></i>
              <span slot="title">food</span>
            </el-menu-item>
            <el-menu-item index="2" @click="routerJump('addFood')">
              <i class="el-icon-menu"></i>
              <span slot="title">新增</span>
            </el-menu-item>
<!--            <el-menu-item index="3">
              <i class="el-icon-menu"></i>
              <span slot="title">导航三</span>
            </el-menu-item>-->
          </el-menu>
        </el-col>
      </el-row>
    </div>


    <div class="right">
      <div id="header" style="text-align: right"><h2>欢迎您，{{nickname}}</h2></div>
      <div id="main"><router-view></router-view></div>
    </div>

  </div>
</template>

<script>

export default {
  name: "MainDemo",
  data() {
    return {
      nickname: sessionStorage.getItem("nickname")
    }
  },
  methods: {
    routerJump(name) {
      this.$router.push({
        name: name
      })
    }
  }
}
</script>

<style scoped>
div {
  /*border: red 1px solid;*/
}

#container {
  display: flex;
}

#aside {

}

.left {
  width: 15%;
  height: 650px;
}

.right {
  flex: 1;
}

#header {
  height: 30px;
}

#main {
  height: 600px;
}
</style>