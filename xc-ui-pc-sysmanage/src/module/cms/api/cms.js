import http from '../../../base/api/public';
import  querystring from 'querystringify'
let sysConfig = require('../../../../config/sysConfig')
let xcApiUrlPre = sysConfig.xcApiUrlPre;

export  const  page_list =function (page,size,params) {
  let query = querystring.stringify(params);

  return http.requestQuickGet(xcApiUrlPre+"/cms/manage/list/"+ page+"/"+size+"/?"+query);
}
export  const  add_page=function (pageForm) {
  return http.requestPost(xcApiUrlPre+"/cms/manage/add",pageForm);
}
export  const  getCmspage=function (pageId) {
  return http.requestQuickGet(xcApiUrlPre+"/cms/manage/get/"+pageId);
}
export  const  edit_page=function (pageId,pageForm) {
  return http.requestPut(xcApiUrlPre+"/cms/manage/edit"+"/"+pageId,pageForm);
}
export  const  delete_page=function (pageId) {
  return http.requestQuickGet(xcApiUrlPre+"/cms/manage/delete"+"/"+pageId);
}
