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
    网页名称:
    <el-input
      placeholder="请输入内容"
      style="width: auto"
      v-model="params.pageName"
      @keyup.enter.native="query"
      clearable>
    </el-input>
    页面类型:
    <el-select v-model="params.pageType" placeholder="请选择类型">
      <el-option
        v-for="item in typeList"
        :key="item.pageType"
        :label="item.name"
        :value="item.pageType">
      </el-option>
    </el-select>
    <el-button
      size="small"
      type="primary"
      @click="query">查询</el-button>
    <el-button
      size="small"
      type="primary"
      @click="clearKeywords">清空查询</el-button>
    <router-link :to="{path:'/cms/page/add',query:{
        page:this.page,
        siteId:this.params.siteId,
        pageAliase : this.params.pageAliase,
        pageName : this.params.pageName,
        pageType : this.params.pageType
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
      :current-page.sync="page"
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
        page: 1,
        params: {
          pageAliase: '',
          siteId: '',
          pageName: '',
          pageType: ''
        },
        siteList: [],
        typeList: [],
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
            page:this.page,
            siteId:this.params.siteId,
            pageAliase : this.params.pageAliase,
            pageName : this.params.pageName,
            pageType : this.params.pageType
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
      },
      clearKeywords(){
        Object.assign(this.$data.params,this.$options.data().params);
      }
    },
    created(){
      this.page=this.$route.query.page || 1;
      this.params.siteId   =  this.$route.query.siteId || "";
      this.params.pageAliase   =  this.$route.query.pageAliase || "";
      this.params.pageName  =  this.$route.query.pageName || "";
      this.params.pageType  =  this.$route.query.pageType || "";
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
        }];
      this.typeList = [{
        name: "静态",
        pageType : '0'
      },{
        name: "动态",
        pageType : '1'
      },]

    }
  }
</script>

<style scoped>
</style>
