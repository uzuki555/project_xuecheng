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
        >新增页面</el-button>


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
    <el-table-column
      label="操作"
    >
      <template slot-scope="scope">

          <el-button
            size="small"
            type="primary"
            @click="edit(scope.row.pageId)">编辑页面</el-button>
        <el-button
          size="small"
          type="danger"
        @click="deleteByPageId(scope.row.pageId)">删除页面</el-button>
      </template>
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
      deleteByPageId(pageId){
        this.$confirm('将删除该记录', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          cmsApi.delete_page(pageId).then((res) =>{
            if(res.success){
              this.$message({
                type: 'success',
                message: '删除成功!'

              });
              this.query();
            }else {
              this.$message({
                type: 'info',
                message : '删除失败!'
              })
            }

          });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      edit(pageId){
        this.$router.push({
          path:'/cms/page/edit',query:{
            pageId: pageId,
            page:this.page
          }
        })
      },
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
    created(){
      this.page=this.$route.query.page || 1;
      this.params.siteId   =  this.$route.query.siteId || "";
    },
    mounted() {
      this.query();
      this.siteList = [{
        name: "门户主站",
        siteId: "5a751fab6abb5044e0d19ea1"
      },
        {
          name: "测试站",
          siteId: "102"
        }]
    }
  }
</script>

<style scoped>
</style>
