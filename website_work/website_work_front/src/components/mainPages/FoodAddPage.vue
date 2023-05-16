<template>
  <div class="outer">
    <el-button type="info" plain style="margin-left: -240px" size="small" @click="routerJump">返回</el-button>
    <div class="title"><span>新增食物</span></div>
    <div class="name"><span>食物名称</span><el-input v-model="foodInfo.fname" style="margin-top: 5px;width: 150px" @blur="checkFname"></el-input><span class="justify">{{this.checkTip.fname}}</span></div>
    <div class="name"><span>价格</span><el-input v-model="foodInfo.fprice" style="margin-top: 5px;width: 150px" @blur="checkFprice"></el-input><span class="justify">{{ this.checkTip.fprice }}</span></div>
    <div class="type">
      <span>食物种类</span>
      <el-select style="margin-top: 5px;width: 150px;margin-left: 20px" v-model="foodInfo.tid" placeholder="种类" @blur="checkTid">
        <template v-for="item in ftype">
          <el-option v-if="item.tid === foodInfo.tid" :key="item.tid" :label="item.tname" :value="item.tid" selected="selected"></el-option>
          <el-option v-if="item.tid !== foodInfo.tid" :key="item.tid" :label="item.tname" :value="item.tid" ></el-option>
        </template>
      </el-select>
      <span class="justify" style="margin-left: -2px">{{ this.checkTip.tid }}</span>
    </div>
    <div class="desc">
      <span style="margin-left: 5px">食物描述</span>
      <el-input
          @blur="this.checkFdesc"
          type="textarea"
          :rows="4"
          style="margin-left: 25px"
          v-model="foodInfo.fdesc">
      </el-input>
      <span class="justify">{{ this.checkTip.fdesc }}</span>
    </div>
    <div class="desc">
      <span>图片</span>
      <div class="image" style="margin-left: 20px"><img :src="foodInfo.fpic" alt="未添加图片"></div>
      <input ref="file" type="file" name="file" style="height: 30px;margin-top: 77px">
      <el-button type="primary" style="height: 35px;margin-top: 70px" @click="submitPic">上传<i class="el-icon-upload el-icon--right"></i></el-button>
    </div>
    <el-button type="success" round style="margin-left: 45%;margin-top: 50px" @click="modifyByFid">添加</el-button>
  </div>
</template>

<script>

export default {
  name: "FoodAddPage",
  data() {
    return {
      foodInfo: {
        tid: "",
        fname: "",
        fpic: "",
        fprice: "",
        fdesc: ""
      },
      checkTip: {
        fname: '',
        fprice: '',
        tid: '',
        fdesc: '',
        fpic: ''
      },
      ftype: [],
      selectedFtype: "",
    }
  },
  methods: {
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
      if (this.checkFname() && this.checkFprice() && this.checkTid() && this.checkFdesc() && this.checkFpic()) {
        this.$axios({
          url: this.$store.state.url + "/food/addFood",
          method: "post",
          contentType : "application/json",
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
              if (res.data.data === "添加成功") {
                this.$message({
                  message: "添加成功",
                  type: "success"
                })
              }else {
                alert("添加失败！！")
              }
            }
        );
      }else {
        alert("error")
      }
    },
    checkFname() {
      if (this.foodInfo.fname === '') {
        this.checkTip.fname = '*名称不为空！'
        return false;
      }else {
        this.checkTip.fname = ''
        return true;
      }
    },
    checkFprice() {
      if (this.foodInfo.fprice === '') {
        this.checkTip.fprice = '*价格不为空！'
        return false;
      }else {
        this.checkTip.fprice = ''
        return true;
      }
    },
    checkTid() {
      if (this.foodInfo.tid === '') {
        this.checkTip.tid = '*种类未选！'
        return false;
      }else {
        this.checkTip.tid = ''
        return true;
      }
    },
    checkFdesc() {
      if (this.foodInfo.fdesc === '') {
        this.checkTip.fdesc = '*描述不为空！'
        return false;
      }else {
        this.checkTip.fdesc = ''
        return true;
      }
    },
    checkFpic() {
      if (this.foodInfo.fpic === '') {
        this.$message({
          message: "图片还未上传",
          type: 'error'
        })
        return false;
      }else {
        return true;
      }
    },
    routerJump() {
      this.$router.push({
        name: 'selectFood'
      })
    }
  },
  mounted() {
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
.name,.type,.desc{
  margin-left: 50px;
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
.justify {
  color: red;
  width: 200px;
  font-size: 10px;
}
</style>