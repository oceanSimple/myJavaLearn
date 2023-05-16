<template>
  <div class="outer">
    <el-button type="info" plain style="margin-left: -240px" size="small" @click="routerJump">返回</el-button>
    <div class="title"><span>修改食物信息</span></div>
    <div class="name"><span>食物名称</span><el-input v-model="foodInfo.fname" style="margin-top: 5px"></el-input></div>
    <div class="name"><span>价格</span><el-input v-model="foodInfo.fprice" style="margin-top: 5px"></el-input></div>
    <div class="type">
      <span>食物种类</span>
      <el-select style="margin-top: 5px" v-model="foodInfo.tid" placeholder="种类">
        <template v-for="item in ftype">
          <el-option v-if="item.tid === foodInfo.tid" :key="item.tid" :label="item.tname" :value="item.tid" selected="selected"></el-option>
          <el-option v-if="item.tid !== foodInfo.tid" :key="item.tid" :label="item.tname" :value="item.tid" ></el-option>
        </template>
      </el-select>
    </div>
    <div class="desc">
      <span>食物描述</span>
      <el-input
          type="textarea"
          :rows="4"
          v-model="foodInfo.fdesc">
      </el-input>
    </div>
    <div class="desc">
      <span>图片</span>
      <div class="image"><img :src="foodInfo.fpic" alt="未找到图片"></div>
      <input ref="file" type="file" name="file" style="height: 30px;margin-top: 77px">
      <el-button type="primary" style="height: 35px;margin-top: 70px" @click="submitPic">上传<i class="el-icon-upload el-icon--right"></i></el-button>
    </div>
    <el-button type="success" round style="margin-left: 45%;margin-top: 50px" @click="modifyByFid">修改</el-button>
  </div>
</template>

<script>

export default {
  name: "FoodModifyPage",
  data() {
    return {
      foodInfo: {
        fid: this.$route.params.fid,
        tid: "",
        fname: "ocean",
        fpic: "",
        fprice: "",
        fdesc: ""
      },
      ftype: [],
      selectedFtype: "",
    }
  },
  methods: {
    getFoodInfo() {
      this.$axios({
        url: this.$store.state.url + "/food/selectByFid",
        method: 'get',
        params: {fid: this.foodInfo.fid}
      }).then(
          res => {
            this.foodInfo = res.data.data
          }
      )
    },
    getFtypeInfo() {
      this.$axios({
        url: this.$store.state.url + "/ftype/selectAll",
        method: 'get'
      }).then(
          res => {
            this.ftype = res.data.data
          }
      )
    },
    submitPic() {
      let formData = new FormData();
      formData.append("file", this.$refs.file.files[0])
      this.$axios({
        url: this.$store.state.url + '/common/food/upload',
        method: 'post',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: formData
      }).then(
          res => {
            this.foodInfo.fpic = res.data.data;
          }
      )
    },
    modifyByFid() {
      let formData = new FormData();
      formData.append("food", JSON.stringify(this.foodInfo));
      this.$axios({
        url: this.$store.state.url + "/food/modifyByFid",
        method: "post",
        contentType: "application/json",
        data: {
          fid: this.foodInfo.fid,
          fpic: this.foodInfo.fpic,
          tid: this.foodInfo.tid,
          fdesc: this.foodInfo.fdesc,
          fname: this.foodInfo.fname,
          fprice: this.foodInfo.fprice
        }
      }).then(
          res => {
            if (res.data.data === "修改成功") {
              this.$router.push({
                name: 'selectFood'
              });
              this.$message({
                message: "修改成功",
                showClose: "true",
                type: "success"
              })
            } else {
              alert("修改失败！！")
            }
          }
      );
    },
    routerJump() {
      this.$router.push({
        name: 'selectFood'
      })
    }
  },
  mounted() {
    this.getFoodInfo();
    this.getFtypeInfo();
  }
}
</script>

<style scoped>
div{
  /*border: red 1px solid;*/
}
.outer {
  width: 50%;
  height: 500px;
  margin-left: 25%;
}
.title {
  height: 50px;
}
.title > span {
  font-weight: bold;
  font-size: 30px;
  margin-left: 30%;
}
.name {
  height: 50px;
  display: flex;
}
.name > span {
  width: 100px;
  text-align: center;
  line-height: 50px;
}
.type {
  height: 50px;
  display: flex;
}
.type > span {
  width: 80px;
  text-align: center;
  line-height: 50px;
}
.image {
  height: 100px;
}
.image > img {
  width: 100px;
  height: 100px;
  margin-left: -15px;
}
.desc {
  height: 100px;
  display: flex;
}
.desc > span {
  width: 100px;
  text-align: center;
}
.desc > form > input {
  margin-top: 75px;
}
</style>