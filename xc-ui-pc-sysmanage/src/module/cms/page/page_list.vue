<template>
  <div>
    <el-select v-model="params.siteId" placeholder="请选择站点">
      <el-option
        v-for="item in siteList"
        :key="item.siteId"
        :label="item.name"
        :value="item.siteId">
      </el-option>
    </el-select>
    网页别名:
    <el-input
      placeholder="请输入内容"
      style="width: auto"
      v-model="params.pageAliase"
      @keyup.enter.native="query"
      clearable>
    </el-input>
    <el-button
      size="small"
      type="primary"
      @click="query">查询</el-button>
    <router-link :to="{path:'/cms/page/add',query:{
        page:this.page,
        siteId:this.params.siteId
    }}">
      <el-button
        size="small"
        type="primary"
        @click="addPage">新增页面</el-button>


    </router-link>

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
      :page-size = "size"
      @current-change="handleCurrentChange"
      :style="{'float':'right'}"
      :current-page="page"
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
        size: 10,
        page: 1 ,
        params: {
          pageAliase : '',
          siteId : ''
        },
      siteList:[]
      }
    },

    methods: {
      handleCurrentChange(val) {
        this.page = val;
        this.query();
      },
      query() {
        cmsApi.page_list(this.page,this.size,this.params).then(res => {
          this.tableData = res.queryResult.list;
          this.total = res.queryResult.total;
        }).catch(error => {
          alert("请求失败");
        });
      }
    },
    mounted() {
      this.query();
      this.siteList = [{
        name: "门户主站",
        siteId: "5a751fab6abb5044e0d19ea1"
      },
        {
          name: "测试站",
          siteId: "002"
        }]
    }
  }
</script>

<style scoped>
</style>
