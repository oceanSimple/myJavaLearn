<template>
  <div>
    <div style="height: 400px;">
      <el-input
          placeholder="请输入内容"
          prefix-icon="el-icon-search"
          v-model="input"
          style="width: 200px"
          @keyup.enter.native="getPageData">
      </el-input>
      <br>
      <br>
      <el-table
          :data="tableData"
          stripe
          style="width: 100%"
          max-height="400px">
          <el-table-column
              prop="fid"
              label="id"
              width="50">
            <template slot-scope="scope">
              <span>{{(page - 1) * pageSize + scope.$index + 1}}</span>
            </template>
          </el-table-column>

        <el-table-column
            prop="fname"
            label="名称"
            width="110">
        </el-table-column>
        <el-table-column
            prop="fpic"
            label="图片"
            width="100">
          <template slot-scope="scope">
            <img :src="tableData[scope.$index].fpic" width="50px" height="50px">
          </template>
        </el-table-column>
        <el-table-column
            prop="ftype.tname"
            label="种类"
            width="100">
        </el-table-column>
        <el-table-column
            prop="fprice"
            label="价格"
            width="100">
        </el-table-column>
        <el-table-column
            prop="fdesc"
            label="描述"
            width="400">
        </el-table-column>
        <el-table-column
            label="修改/删除"
            width="100">
          <template slot-scope="scope">
            <div class="modify">
              <div style="width: 40px;height: 35px"><el-button type="primary" icon="el-icon-edit" circle size="small" @click="modify(scope.$index)"></el-button></div>
              <div style="width: 40px;height: 35px"><el-button type="danger" icon="el-icon-delete" circle size="small" @click="deleteFood(scope.$index)"></el-button></div>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="block" style="margin-left: 25%;margin-top: 50px">
      <el-pagination
          layout="prev, pager, next"
          :total=this.dataCount
          :page-size="3"
          @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "FoodPage",
  data() {
    return {
      tableData: [],
      page: 1,
      pageSize: 3,
      dataCount: 0,
      input: '',
      imageUrl: '',
      image: '',
    }
  },
  methods: {
    getDataCount() {
      this.$axios({
        url: this.$store.state.url+'/food/count',
        method: 'get'
      }).then(
          res => {
            this.dataCount = res.data.data;
          },
          err => {
            console.log(err.message);
          }
      )
    },
    getPageData() {
      let data = {page: this.page, pageSize: this.pageSize, input: this.input}
      this.$axios({
        url: this.$store.state.url+"/food/page",
        method: 'get',
        params: data
      }).then(
          res => {
            this.tableData = res.data.data.records;
          }
      )
    },
    handleCurrentChange(val) {
      this.page = val;
      this.getPageData();
    },
    modify(index) {
      this.$router.push({
          name: 'modifyFood',
          params: {fid: this.tableData[index].fid}
      })
    },
    deleteFood(index) {
      this.$confirm("确定要删除\"" + this.tableData[index].fname + "\"吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      }).then(() => {
        this.$axios({
          method: "get",
          url: this.$store.state.url + "/food/deleteByFid",
          params: {fid: this.tableData[index].fid}
        }).then(
            res => {
              if (res.data.data === "删除成功") {
                this.$message({
                  type: "success",
                  message: "删除成功"
                })
                this.getDataCount();
                this.getPageData();
              }else {
                this.$message({
                  type: "error",
                  message: "删除失败"
                })
              }
            }
        )
      }).catch(() => {
        this.$message({
          type: "info",
          message: "已取消删除"
        })
      })
    }
  },
  mounted() {
    this.getDataCount();
    this.getPageData();
  }
}
</script>

<style scoped>
  .modify {
    display: flex;
  }
</style>