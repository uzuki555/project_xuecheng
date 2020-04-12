<template>
  <div>
  <el-table
  :data="tableData"
  stripe
  style="width: 100%">
    <el-table-column
      type="index"
      width="50">
    </el-table-column>
  <el-table-column
    prop="pageName"
    label="页面名称"
    >
  </el-table-column>
  <el-table-column
    prop="pageAliase"
    label="页面别名"
   >
  </el-table-column>
    <el-table-column
      prop="pageType"
      label="页面类型"
      width="100"
    >
    </el-table-column>
    <el-table-column
      prop="pageWebPath"
      label="访问路径"
    >
    </el-table-column>
    <el-table-column
      prop="pagePhysicalPath"
      label="物理路径"
    >
    </el-table-column>

    <el-table-column
      prop="pageCreateTime"
      label="网页创建时间"
      >
    </el-table-column>
  </el-table>
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :page-size = "pageSize"
      @current-change="handleCurrentChange"
      :style="{'float':'right'}"
      :current-page="currentPage"
    >
    </el-pagination>
  </div>
</template>

<script>
  import * as cmsApi from '../api/cms'

  export default {

    data() {
      return {
        tableData: [],
        total: 0,
        pageSize: 10,
        currentPage : 1
      }
    },

    methods:{
      handleCurrentChange(val){
        this.currentPage=val;
        this.query();
      },
      query() {
        cmsApi.page_list(this.currentPage,this.pageSize).then(res=>{
          this.tableData=res.queryResult.list;
          this.total=res.queryResult.total;
        }).catch(error=>{
          alert("请求失败");
        });
      }
    },
    mounted() {
      this.query();
    }
  }
</script>

<style scoped>
</style>
