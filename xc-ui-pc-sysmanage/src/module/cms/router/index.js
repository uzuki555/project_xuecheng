import Home from '@/module/home/page/home.vue';
import page_list from '@/module/cms/page/page_list.vue';
import add_page from '@/module/cms/page/add_page.vue';
export default [{
  path: '/cms',
  component : Home,
  name: 'CMS管理页面',
  hidden : false,
  children: [{path : '/cms/page/show',hidden: false , name:'页面列表',component :page_list},
    {path : '/cms/page/add',hidden: true , name:'新增页面',component :add_page}
    ]
}]
