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


