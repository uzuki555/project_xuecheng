import Home from '@/module/home/page/home.vue';
import page_list from '@/module/cms/page/page_list.vue';
export default [{
  path: '/cms',
  component : Home,
  name: 'CMS管理页面',
  hidden : false,
  children: [{path : '/cms/page/page_list',hidden: false , name:'页面列表',component :page_list}]
}]
