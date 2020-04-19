<template>
  <div>
    <el-form ref="pageForm" :model="form" label-width="80px">
      <el-form-item label="所属站点">
        <el-select v-model="pageForm.siteId" placeholder="请选择站点">
          <el-option
            v-for="item in siteList"
            :key="item.siteId"
            :label="item.name"
            :value="item.siteId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择模板">
        <el-select v-model="pageForm.template" placeholder="请选择">
          <el-option
            v-for="item in templateList"
            :key="item.templateId"
            :label="item.templateName"
            :value="item.templateId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="页面名称">
        <el-input v-model="pageForm.pageName"></el-input>
      </el-form-item>
      <el-form-item label="别名">
        <el-input v-model="pageForm.pageAliase"></el-input>
      </el-form-item>
      <el-form-item label="访问路径">
        <el-input v-model="pageForm.pageWebPath"></el-input>
      </el-form-item>

      <el-form-item label="物理路径">
        <el-input v-model="pageForm.pagePhysicalPath"></el-input>
      </el-form-item>
      <el-form-item label="类型">
        <el-radio-group v-model="pageForm.pageType">
          <el-radio class="radio" label="0">静态</el-radio>
          <el-radio class="radio" label="1">动态</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="pageForm.pageCreateTime"
          type="datetime"
          placeholder="选择日期时间">
        </el-date-picker>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submit">提交</el-button>
    </div>
  </div>
</template>

<script>
  import * as cmsApi from '../api/cms'

  export default {
    data() {
      return {
        siteList: [],
        templateList: [],
        pageForm: {
          siteId: '',
          pageName: '',
          pageAliase: '',
          pageWebPath: '',
          pagePhysicalPath: '',
          pageType: '',
          pageCreateTime: new Date()
        }
      }
    },
    methods: {
      submit() {
        cmsApi.add_page(this.pageForm);
      }
    },
    created() {
      this.siteList = [
        {
          name: "门户主站",
          siteId: "5a751fab6abb5044e0d19ea1"
        },
        {
          name: "测试站",
          siteId: "102"
        }],
        this.templateList = [
          {
            templateId: '5a962b52b00ffc514038faf7',
            templateName: '首页'
          },
          {
            templateId: '5a962bf8b00ffc514038fafa',
            templateName: '轮播图'
          }
        ]
    }
  }
</script>

<style scoped>
</style>
