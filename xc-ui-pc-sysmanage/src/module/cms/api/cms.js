import http from '../../../base/api/public';
let sysConfig = require('../../../../config/sysConfig')
let xcApiUrlPre = sysConfig.xcApiUrlPre;
export  const  page_list =function (page,size) {
  return http.requestQuickGet(xcApiUrlPre+"/cms/mange/list/"+ page+"/"+size);
}


