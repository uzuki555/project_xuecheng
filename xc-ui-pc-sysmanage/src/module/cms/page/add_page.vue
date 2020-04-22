<template>
  <div>
    <el-form ref="pageForm" :rules="pageFormRules" :model="pageForm" label-width="80px">
      <el-form-item label="所属站点" prop="siteId">
        <el-select v-model="pageForm.siteId" placeholder="请选择站点"  >
          <el-option
            v-for="item in siteList"
            :key="item.siteId"
            :label="item.name"
            :value="item.siteId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择模板"  prop="templateId">
        <el-select v-model="pageForm.templateId" placeholder="请选择">
          <el-option
            v-for="item in templateList"
            :key="item.templateId"
            :label="item.templateName"
            :value="item.templateId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="页面名称"  prop="pageName">
        <el-input v-model="pageForm.pageName"></el-input>
      </el-form-item>
      <el-form-item label="页面别名" prop="pageAliase">
        <el-input v-model="pageForm.pageAliase"></el-input>
      </el-form-item>
      <el-form-item label="访问路径" prop="pageWebPath">
        <el-input v-model="pageForm.pageWebPath"></el-input>
      </el-form-item>

      <el-form-item label="物理路径"  prop="pagePhysicalPath">
        <el-input v-model="pageForm.pagePhysicalPath"></el-input>
      </el-form-item>
      <el-form-item label="页面类型" prop="pageType">
        <el-radio-group v-model="pageForm.pageType">
          <el-radio class="radio" label="0">静态</el-radio>
          <el-radio class="radio" label="1">动态</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="创建时间" prop="pageCreateTime">
        <el-date-picker
          v-model="pageForm.pageCreateTime"
          type="datetime"
          placeholder="选择日期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="数据连接"  prop="dataUrl">
        <el-input v-model="pageForm.dataUrl"></el-input>
      </el-form-item>
    </el-form>

    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submit()">提交</el-button>

      <el-button type="primary" @click="goBack">返回</el-button>
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
          templateId:'',
          siteId: '',
          pageName: '',
          pageAliase: '',
          pageWebPath: '',
          pagePhysicalPath: '',
          pageType: '',
          pageCreateTime: new Date(),
          dataUrl : ''
        },
        pageFormRules:{
          siteId:[
            {required:true,message :'请选择站点',trigger:'change'}
          ],
          templateId:[
            {required:true,message :'请选择模板',trigger:'change'}
          ],
          pageName:[
            {required:true,message :'请输入页面名称',trigger:'blur'}
          ],
          pageAliase:[
            {required:true,message :'请输入页面别名',trigger:'blur'}
          ],
          pageWebPath:[
            {required:true,message :'请输入访问路径',trigger:'blur'}
          ],
          pagePhysicalPath:[
            {required:true,message :'请输入物理路径',trigger:'blur'}
          ],
          pageType:[
            {required:true,message :'请选择页面类型',trigger:'change'}
          ],
          pageCreateTime:[
            {required:true,message :'请选择日期',trigger:'change'}
          ],
          dataUrl:[
            {required:true,message :'请输入数据链接',trigger:'change'}
          ]
        }

      }
    },
    methods: {
          submit() {
            this.$refs.pageForm.validate((valid) => {
              if (valid) {
                this.$confirm('将提交到数据库', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning'
                }).then(() => {
                  cmsApi.add_page(this.pageForm).then((res) =>{
                    if(res.success){
                      this.$message({
                        type: 'success',
                        message: '提交成功!'

                      });
                      this.$refs['pageForm'].resetFields();
                    }else {
                      this.$message({
                        type: 'info',
                        message : '提交失败:'+res.message
                      })
                    }

                  });

                }).catch(() => {
                  this.$message({
                    type: 'info',
                    message: '已取消提交'
                  });
                });

              } else {
                console.log('提交失败！');
                return false
              }
            });
  },
      goBack(){
        this.$router.push({
          path:'/cms/page/show',
          query:{
            page:this.$route.query.page,
            siteId:this.$route.query.siteId,
            pageAliase:this.$route.query.pageAliase,
            pageName:this.$route.query.pageName,
            pageType:this.$route.query.pageType
          }
        })
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
  .el-message-box__message{
    margin-left: 50px;
  }
</style>
